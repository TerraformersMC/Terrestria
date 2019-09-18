package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.feature.trees.components.Branches;
import com.terraformersmc.terrestria.feature.trees.components.SmallRoots;
import com.terraformersmc.terrestria.feature.trees.templates.SmallLogTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class BryceTreeFeature extends SmallLogTree implements Branches, SmallRoots {

	ArrayList<BlockPos> leafOrigins = new ArrayList<>();

	public BryceTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, boolean boolean_1, BlockState log, BlockState leaves) {
		super(function_1, boolean_1, log, leaves);
	}

	public BryceTreeFeature sapling() {
		return new BryceTreeFeature(DefaultFeatureConfig::deserialize, true, this.getLog(), this.getLeaves());
	}

	@Override
	protected boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {

		int height = rand.nextInt(3) + 7; 			// Total tree height
		double maxRadius = 1.5 + 1.5 * rand.nextDouble(); 	// Maximum leaf/branch radius.

		//If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, (int) Math.ceil(maxRadius))) {
			return false;
		}

		//Grows a trunk with roots and branches
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, randomHorizontalDirection(rand), boundingBox);
		placeLeaves(blocks, world, boundingBox);
		return true;
	}

	public void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, int height, Direction direction, MutableIntBoundingBox boundingBox) {
		Random rand = new Random();
		Direction randDir;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		for (int i = 1; i <= height; i++) {

			if (i == 1) {
				setBlockState(blocks, world, pos, this.getLog().with(SmallLogBlock.DOWN, true), boundingBox);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, boundingBox);
			}

			//Randomly change direction
			if (rand.nextInt(4) == 0) {
				randDir = randomHorizontalDirectionAwayFrom(rand, direction.getOpposite());
				pos.setOffset(randDir);
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), randDir, boundingBox);
			}

			//Randomly generate a branch if the height is greater than half
			if (i > 4 && rand.nextInt(2) == 1) {
				placeBranch(blocks, world, new BlockPos.Mutable(pos.toImmutable()), 2 + rand.nextInt(3), randomHorizontalDirection(rand), boundingBox);
			}

			//Randomly generate roots if the height is less than 4 blocks above the origin
			if (i < 4 && rand.nextInt(3) < 3) {
				placeRoot(blocks, world, new BlockPos.Mutable(pos.toImmutable()), rand.nextInt(5), boundingBox);
			}

			pos.setOffset(Direction.UP);
		}
	}

	@Override
	public void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		Random rand = new Random();
		Direction offset = null;
		for (int i = 0; i < length; i++) {
			offset = randomHorizontalDirectionAwayFrom(rand, direction.getOpposite());
			pos.setOffset(offset);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), offset, boundingBox);
				if (rand.nextBoolean()) {
					pos.setOffset(Direction.UP);
					if (isAir(world, pos)) {
						setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, boundingBox);
					} else {
						pos.setOffset(Direction.DOWN);
					}
				}
			}
			if (rand.nextBoolean()) {
				leafOrigins.add(pos.toImmutable());
			}
		}
	}

	@Override
	public void placeRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int rootLength, MutableIntBoundingBox boundingBox) {
		Random random = new Random();
		Direction originalDirection = randomHorizontalDirection(random);
		Direction direction = null;
		for (int i = 0; i < rootLength; i++) {
			//Place block and block down and to the side
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
				pos.setOffset(Direction.UP);
				if (((World) world).getBlockState(pos).getBlock() instanceof SmallLogBlock) {
					setBlockState(blocks, world, pos, ((World) world).getBlockState(pos).with(SmallLogBlock.DOWN, true), boundingBox);
				}
				break;
			}
		}
	}

	private void placeLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox) {
		Random random = new Random();
		BlockPos.Mutable mPos;
		for (BlockPos pos : leafOrigins) {
			mPos = new BlockPos.Mutable(pos);
			for (int i = 0; i < 2; i++) {
				Shapes.circle(new BlockPos.Mutable(mPos.toImmutable()), 1.0, position -> {
					tryPlaceLeaves(blocks, world, position, boundingBox);
				});
				mPos.setOffset(Direction.UP);
			}
			tryPlaceLeaves(blocks, world, mPos, boundingBox);
		}
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
