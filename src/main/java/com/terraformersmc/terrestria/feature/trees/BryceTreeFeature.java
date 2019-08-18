package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.feature.trees.components.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class BryceTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> implements Branches, Roots, Trunk, Leaves, SmallLogs {
	private BlockState log;
	private BlockState leaves;

	public BryceTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, boolean boolean_1, BlockState log, BlockState leaves) {
		super(function_1, boolean_1);
		
		this.log = log;
		this.leaves = leaves;
	}

	public BryceTreeFeature sapling() {
		return new BryceTreeFeature(DefaultFeatureConfig::deserialize, true, log, leaves);
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

	@Override
	protected boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total tree height
		int height = rand.nextInt(5) + 8;

		// Maximum leaf/branch radius.
		double maxRadius = 1.5 + 1.5 * rand.nextDouble();

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

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);

		//Grows a trunk with roots and branches
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);
		growLeaves(blocks, world, new BlockPos.Mutable(origin), height, maxRadius, boundingBox);

		return true;
	}

	@Override
	public void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		Random rand = new Random();
		Direction offset = randomHorizontalDirectionAwayFrom(rand, direction.getOpposite());
		pos.setOffset(direction);
		for (int i = 0; i < length; i++) {
			//50% chance of changing direction otherwise place single block
			if (rand.nextBoolean()) {
				//Place block and block to the side
				if (isAir(world, pos)) {
					setBlockStateAndUpdate(blocks, world, pos, this.log, direction, boundingBox);
				}
				pos.setOffset(randomHorizontalDirectionAwayFrom(rand, direction.getOpposite()));
				if (isAir(world, pos)) {
					setBlockStateAndUpdate(blocks, world, pos, this.log, direction, boundingBox);
				}
			} else {
				if (isAir(world, pos)) {
					setBlockStateAndUpdate(blocks, world, pos, this.log, direction, boundingBox);
				}
			}
			pos.setOffset(offset);
		}
	}

	@Override
	public void growRoots(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) { }

	@Override
	public void tryGrowRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		pos.setOffset(Direction.UP);
		Direction direction = randomHorizontalDirection(rand);
		for (int i = 0; i < baseTrunkHeight; i++) {
			//Place block and block down and to the side
			pos.setOffset(direction);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(blocks, world, pos, this.log, direction, boundingBox);
			}
			for (int d = 0; d < 5; d++) {
				pos.setOffset(Direction.DOWN);
				if (isAir(world, pos)) {
					setBlockStateAndUpdate(blocks, world, pos, this.log, direction, boundingBox);
				}
			}
		}
	}

	@Override
	public void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, double maxRadius, MutableIntBoundingBox boundingBox) {

	}

	@Override
	public void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, int height, MutableIntBoundingBox boundingBox) {
		Random rand = new Random();
		int numRoots = rand.nextInt(4);
		Direction randDir;
		BlockPos branchOrigin;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		if (numRoots > 0) {
			pos.setOffset(Direction.UP);
		}
		for (int i = 1; i <= height; i++) {

			if (i == 1) {
				setBlockStateAndUpdate(blocks, world, pos, this.log, Direction.UP, boundingBox);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.log, Direction.UP, boundingBox);
			}

			if (rand.nextInt(3) == 0) {
				randDir = randomHorizontalDirection(rand);
				pos.setOffset(randDir);
				setBlockStateAndUpdate(blocks, world, pos, this.log, randDir, boundingBox);
			}

			if (i == height) {
				continue;
			}

			if (rand.nextInt((int) height / 4) == 0 && i > height / 2) {
				branchOrigin = pos.toImmutable();
				placeBranch(blocks, world, new BlockPos.Mutable(branchOrigin), 1 + rand.nextInt(3), randomHorizontalDirection(rand), boundingBox);
			}

			pos.setOffset(Direction.UP);
		}

		for (int i = 0; i < numRoots; i++) {
			tryGrowRoot(blocks, world, origin, 1, rand, boundingBox);
		}
	}

	protected void setBlockStateAndUpdate(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, BlockState state, Direction direction, MutableIntBoundingBox boundingBox) {
		setBlockState(blocks, world, pos, state, boundingBox);
		correctBlockState(blocks, world, pos, direction, state, boundingBox);
	}

	private void correctBlockState(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, Direction direction, BlockState state, MutableIntBoundingBox boundingBox) {
		World world1 = (World) world;
		BlockPos pos = origin.toImmutable();
		switch (direction) {
			case SOUTH:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.NORTH, true), boundingBox);
				setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.SOUTH, true), boundingBox);
				break;
			case NORTH:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.SOUTH, true), boundingBox);
				setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.NORTH, true), boundingBox);
				break;
			case WEST:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.EAST, true), boundingBox);
				setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.WEST, true), boundingBox);
				break;
			case EAST:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.WEST, true), boundingBox);
				setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.EAST, true), boundingBox);
				break;
			case DOWN:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.UP, true), boundingBox);
				setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.DOWN, true), boundingBox);
				break;
			case UP:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.DOWN, true), boundingBox);
				setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.UP, true), boundingBox);
				break;
		}
	}

	private static Direction randomHorizontalDirectionAwayFrom(Random rand, Direction direction) {
		Direction out = randomHorizontalDirection(rand);
		return out == direction ? direction.getOpposite() : out;
	}

	private static Direction randomHorizontalDirection(Random rand) {
		switch (rand.nextInt(4)) {
			case 0:
				return Direction.NORTH;
			case 1:
				return Direction.EAST;
			case 2:
				return Direction.WEST;
			case 3:
				return Direction.SOUTH;
		}
		return Direction.NORTH;
	}

	@Override
	public void correctLogStates(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox) {

	}
}
