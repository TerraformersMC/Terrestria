package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.components.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class BryceTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> implements Branches, Roots, Trunk, Leaves, SmallLogs {
	private TreeDefinition.Basic tree;

	public BryceTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, boolean boolean_1, TreeDefinition.Basic tree) {
		super(function_1, boolean_1);

		this.tree = tree;
	}

	public BryceTreeFeature sapling() {
		return new BryceTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
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
		int height = rand.nextInt(5) + 12;

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

		//Fix up the log connections
		correctLogStates(blocks, world, boundingBox);
		return true;
	}

	@Override
	public void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		Random rand = new Random();
		pos.setOffset(direction);
		for (int i = 0; i < length; i++) {
			//50% chance of changing direction otherwise place single block
			if (rand.nextBoolean()) {
				//Place block and block to the side
				if (isAir(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
				}
				pos.setOffset(randomHorizontalDirection(rand));
				if (isAir(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
				}
			} else {
				if (isAir(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
				}
			}
			pos.setOffset(Direction.UP);
		}
	}

	@Override
	public void growRoots(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) { }

	@Override
	public void tryGrowRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		pos.setOffset(Direction.UP);
		for (int i = 0; i < baseTrunkHeight; i++) {
			//Place block and block down and to the side
			pos.setOffset(randomHorizontalDirection(rand));
			if (isAir(world, pos)) {
				setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			}
			for (int d = 0; d < 5; d++) {
				pos.setOffset(Direction.DOWN);
				if (isAir(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
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
		int numBranches = 1 + rand.nextInt(2);
		int numRoots = rand.nextInt(3);
		int chance;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		if (numRoots > 0) {
			pos.setOffset(Direction.UP);
		}
		for (int i = 1; i <= height; i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);

			chance = rand.nextInt(4);
			if (chance == 0) {
				pos.setOffset(randomHorizontalDirection(rand));
			}
		}
		BlockPos.Mutable topPos = new BlockPos.Mutable(pos);
		for (int i = 0; i < numBranches; i++) {
			placeBranch(blocks, world, topPos, rand.nextInt(5), randomHorizontalDirection(rand), boundingBox);
		}
		for (int i = 0; i < numRoots; i++) {
			tryGrowRoot(blocks, world, origin, 1, rand, boundingBox);
		}
	}

	@Override
	public void correctLogStates(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox) {
		for (BlockPos log : blocks) {
			boolean leaves = world.testBlockState(log, tested -> tested.getBlock() instanceof SmallLogBlock && tested.get(SmallLogBlock.HAS_LEAVES));

			Predicate<BlockState> tester = tested -> tested.getBlock() instanceof SmallLogBlock || (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();

			setBlockState(
				blocks,
				world,
				log,
				tree.getLog()
					.with(SmallLogBlock.UP, world.testBlockState(log.up(), tester))
					.with(SmallLogBlock.DOWN, world.testBlockState(log.down(), tester))
					.with(SmallLogBlock.NORTH, world.testBlockState(log.north(), tester))
					.with(SmallLogBlock.EAST, world.testBlockState(log.east(), tester))
					.with(SmallLogBlock.SOUTH, world.testBlockState(log.south(), tester))
					.with(SmallLogBlock.WEST, world.testBlockState(log.west(), tester))
					.with(SmallLogBlock.HAS_LEAVES, leaves),
				boundingBox
			);
		}
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
}
