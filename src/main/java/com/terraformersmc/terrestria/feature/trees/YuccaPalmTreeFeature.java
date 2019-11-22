package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.feature.trees.components.Branches;
import com.terraformersmc.terrestria.feature.trees.components.Trunk;
import com.terraformersmc.terrestria.feature.trees.templates.SmallLogTree;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class YuccaPalmTreeFeature extends SmallLogTree implements Trunk, Branches {

	public YuccaPalmTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer, boolean updateNeighbors, BlockState log, BlockState leaves) {
		super(configDeserializer, updateNeighbors, log, leaves);
	}

	@Override
	protected boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		int height = rand.nextInt(3) + 6;

		// If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		if (!isNaturalDirtOrGrass(world, below) && !world.testBlockState(below, state -> state.matches(BlockTags.SAND))) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, 2)) {
			return false;
		}

		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);
		return true;
	}

	public YuccaPalmTreeFeature sapling() {
		return new YuccaPalmTreeFeature(DefaultFeatureConfig::deserialize, true, this.getLog(), this.getLeaves());
	}

	// This method is so that I cont have infinite recursive branches, it's rare to happen but oh lawd when it does.
	public void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox, boolean cont) {
		pos.setOffset(direction);
		if (cont) {
			placeBranch(blocks, world, pos, length, direction, boundingBox);
			return;
		}
		for (int i = 0; i < length; i++) {
			if (!isAir(world, pos)) {
				return;
			}
			if (i == 0) {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), direction, boundingBox);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, boundingBox);
			}
			pos.setOffset(Direction.UP);
		}
		//Place the leaves on the end of the branches
		tryPlaceLeaves(blocks, world, pos.setOffset(Direction.DOWN), boundingBox);
	}

	@Override
	public void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		pos.setOffset(direction);
		Random random = new Random();
		Direction armDir = randomHorizontalDirection(random);
		for (int i = 0; i < length; i++) {
			if (!isAir(world, pos)) {
				return;
			}
			if (i == 0) {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), direction, boundingBox);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, boundingBox);
			}
			if (i == length - 1) {
				if (random.nextBoolean()) {
					placeBranch(blocks, world, new BlockPos.Mutable(pos), random.nextInt(1) + 2, armDir, boundingBox, false);
					placeBranch(blocks, world, new BlockPos.Mutable(pos), random.nextInt(1) + 2, randomHorizontalDirectionAwayFrom(random, armDir), boundingBox, false);
				}
			}
			pos.setOffset(Direction.UP);
		}
		// Place the leaves on the end of the branches
		placeLeaves(blocks, world, pos.setOffset(Direction.DOWN, 2), boundingBox);
	}

	private void placeLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {
		tryPlaceLeaves(blocks, world, pos, boundingBox);
		pos.setOffset(Direction.UP);
		Shapes.circle(new BlockPos.Mutable(pos.toImmutable()), 1.0, position -> tryPlaceLeaves(blocks, world, position, boundingBox));
		pos.setOffset(Direction.UP);
		tryPlaceLeaves(blocks, world, pos, boundingBox);
	}

	@Override
	public void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		Random random = new Random();
		Direction armDir = randomHorizontalDirection(random);
		for (int i = 0; i < height; i++) {

			if (i == 0) {
				setBlockState(blocks, world, pos, this.getLog().with(SmallLogBlock.DOWN, true), boundingBox);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, boundingBox);
			}

			// Always place an arm half way up ish
			if (i == ((int) height / 2) - 1) {
				placeBranch(blocks, world, new BlockPos.Mutable(pos), random.nextInt(1) + 3, armDir, boundingBox);
			}
			// Half of the time place a second arm one block higher in a different direction
			if (i == ((int) height / 2)) {
				if (random.nextBoolean()) {
					placeBranch(blocks, world, new BlockPos.Mutable(pos), random.nextInt(2) + 4, randomHorizontalDirectionAwayFrom(random, armDir), boundingBox);
				}
			}
			pos.setOffset(Direction.UP);
		}
		placeLeaves(blocks, world, pos.setOffset(Direction.DOWN, 2), boundingBox);
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int dY = origin.getY(); dY < height; dY++) {
			for (int dZ = -radius; dZ <= radius; dZ++) {
				for (int dX = -radius; dX <= radius; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		pos.set(origin.getX(), origin.getY() + height, origin.getZ());

		for (int i = 0; i < 4; i++) {
			if (!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		return true;
	}
}
