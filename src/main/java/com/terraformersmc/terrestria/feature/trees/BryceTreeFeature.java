package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.feature.trees.components.Branches;
import com.terraformersmc.terrestria.feature.trees.components.SmallRoots;
import com.terraformersmc.terrestria.feature.trees.templates.SmallLogTree;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class BryceTreeFeature extends SmallLogTree implements Branches, SmallRoots {

	public BryceTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> configDeserializer, boolean updateNeighbor, BlockState log, BlockState leaves) {
		super(configDeserializer, updateNeighbor, log, leaves);
	}

	public BryceTreeFeature sapling() {
		return new BryceTreeFeature(BranchedTreeFeatureConfig::deserialize, true, this.getLog(), this.getLeaves());
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> wood, Set<BlockPos> leaves, BlockBox blockBox, BranchedTreeFeatureConfig treeFeatureConfig) {

		int height = rand.nextInt(3) + 7; 			// Total tree height
		double maxRadius = 1.5 + 1.5 * rand.nextDouble(); 	// Maximum leaf/branch radius.

		// If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		if (!isNaturalDirtOrGrass(world, below) && !world.testBlockState(below, state -> state.matches(BlockTags.SAND))) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, (int) Math.ceil(maxRadius))) {
			return false;
		}

		// Grows a trunk with roots and branches
		growTrunk(wood, world, new BlockPos.Mutable(origin), height, randomHorizontalDirection(rand), blockBox, treeFeatureConfig);
		return true;
	}

	public void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, int height, Direction direction, BlockBox boundingBox, BranchedTreeFeatureConfig config) {
		Random rand = new Random();
		Direction randDir;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		for (int i = 1; i <= height; i++) {

			if (i == 1) {
				setBlockState(world, pos, this.getLog().with(SmallLogBlock.DOWN, true), boundingBox);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, boundingBox);
			}

			// Randomly change direction
			if (rand.nextInt(4) == 0) {
				randDir = randomHorizontalDirectionAwayFrom(rand, direction.getOpposite());
				pos.setOffset(randDir);
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), randDir, boundingBox);
			}

			// Randomly generate a branch if the height is greater than half
			if (i > 4 && rand.nextInt(2) == 1) {
				placeBranch(world, rand, new BlockPos.Mutable(pos.toImmutable()), blocks, blocks, boundingBox, config, direction, 2 + rand.nextInt(3));
			}

			// Randomly generate roots if the height is less than 4 blocks above the origin
			if (i < 4 && rand.nextInt(3) < 3) {
				placeRoot(blocks, world, new BlockPos.Mutable(pos.toImmutable()), rand.nextInt(5), boundingBox);
			}

			pos.setOffset(Direction.UP);
		}
	}

	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox boundingBox, TreeFeatureConfig config, Direction direction, int length) {
		Direction offset = null;
		for (int i = 0; i < length; i++) {
			offset = randomHorizontalDirectionAwayFrom(rand, direction.getOpposite());
			pos.setOffset(offset);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(logs, world, pos, this.getLog(), offset, boundingBox);
				if (rand.nextBoolean()) {
					pos.setOffset(Direction.UP);
					if (isAir(world, pos)) {
						setBlockStateAndUpdate(logs, world, pos, this.getLog(), Direction.UP, boundingBox);
					} else {
						pos.setOffset(Direction.DOWN);
					}
				}
			}
			if (rand.nextBoolean()) {
				placeLeaves(world, rand, new BlockPos.Mutable(pos.toImmutable()), leaves, boundingBox, config);
			}
		}
	}

	@Override
	public void placeRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int rootLength, BlockBox boundingBox) {
		Random random = new Random();
		Direction originalDirection = randomHorizontalDirection(random);
		Direction direction = null;
		for (int i = 0; i < rootLength; i++) {
			// Place block and block down and to the side
			direction = randomHorizontalDirectionAwayFrom(random, originalDirection.getOpposite());
			pos.setOffset(direction);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), direction, boundingBox);
				if (random.nextBoolean()) {
					pos.setOffset(Direction.DOWN);
					setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.DOWN, boundingBox);
				}
			}
		}

		for (int j = 0; j < 3; j++) {
			pos.setOffset(Direction.DOWN);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.DOWN, boundingBox);
			} else {
				break;
			}
		}
	}

	private void placeLeaves(ModifiableTestableWorld world, Random random, BlockPos.Mutable pos, Set<BlockPos> blocks, BlockBox boundingBox, TreeFeatureConfig config) {
		for (int i = 0; i < 2; i++) {
			Shapes.circle(new BlockPos.Mutable(pos.toImmutable()), 1.0, position -> {
				tryPlaceLeaves(world, position, random, blocks, boundingBox, config);
			});
			pos.setOffset(Direction.UP);
		}
		tryPlaceLeaves(world, pos, random, blocks, boundingBox, config);
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
