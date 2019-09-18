package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.BareSmallLogBlock;
import com.terraformersmc.terraform.block.SmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class SmallLogTree extends AbstractTreeFeature<DefaultFeatureConfig> {

	private BlockState log;
	private BlockState leaves;

	public SmallLogTree(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, boolean boolean_1, BlockState log, BlockState leaves) {
		super(function_1, boolean_1);

		this.log = log;
		this.leaves = leaves;
	}

	protected BooleanProperty getStateFromDirection(Direction direction) {
		switch (direction) {
			case SOUTH:
				return BareSmallLogBlock.SOUTH;
			case NORTH:
				return BareSmallLogBlock.NORTH;
			case WEST:
				return BareSmallLogBlock.WEST;
			case EAST:
				return BareSmallLogBlock.EAST;
			case DOWN:
				return BareSmallLogBlock.DOWN;
			case UP:
				return BareSmallLogBlock.UP;
		}
		return null;
	}

	protected void setBlockStateAndUpdate(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, BlockState state, Direction direction, MutableIntBoundingBox boundingBox, boolean bareSmallLog) {
		setBlockState(blocks, world, pos, state, boundingBox);
		if (bareSmallLog) {
			correctBlockState(blocks, world, pos, direction, boundingBox);
		} else {
			correctLogOnlyState(blocks, world, pos, direction, boundingBox);
		}
	}

	protected void setBlockStateAndUpdate(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, BlockState state, Direction direction, MutableIntBoundingBox boundingBox) {
		setBlockStateAndUpdate(blocks, world, pos, state, direction, boundingBox, false);
	}

	private void correctBlockState(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, Direction direction, MutableIntBoundingBox boundingBox) {
		boolean leaves = world.testBlockState(origin, tested -> tested.getBlock() instanceof SmallLogBlock && tested.get(SmallLogBlock.HAS_LEAVES));
		Predicate<BlockState> tester = tested -> tested.getBlock() instanceof SmallLogBlock || (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();

		switch (direction) {
			case SOUTH:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.SOUTH, world.testBlockState(origin.south(), tester)).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
				break;
			case NORTH:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.NORTH, world.testBlockState(origin.north(), tester)).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
				break;
			case WEST:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.WEST, world.testBlockState(origin.west(), tester)).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
				break;
			case EAST:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.EAST, world.testBlockState(origin.east(), tester)).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
				break;
			case DOWN:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.DOWN, world.testBlockState(origin.down(), tester)).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
				break;
			case UP:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.UP, world.testBlockState(origin.up(), tester)).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
				break;
		}
	}

	private void correctLogOnlyState(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, Direction direction, MutableIntBoundingBox boundingBox) {
		Predicate<BlockState> tester = tested -> tested.getBlock() instanceof BareSmallLogBlock || tested.isOpaque();

		switch (direction) {
			case SOUTH:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.SOUTH, world.testBlockState(origin.south(), tester)), boundingBox);
				break;
			case NORTH:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.NORTH, world.testBlockState(origin.north(), tester)), boundingBox);
				break;
			case WEST:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.WEST, world.testBlockState(origin.west(), tester)), boundingBox);
				break;
			case EAST:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.EAST, world.testBlockState(origin.east(), tester)), boundingBox);
				break;
			case DOWN:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.DOWN, world.testBlockState(origin.down(), tester)), boundingBox);
				break;
			case UP:
				setBlockState(blocks, world, origin, getOriginalState(world, origin).with(SmallLogBlock.UP, world.testBlockState(origin.up(), tester)), boundingBox);
				break;
		}
	}

	@Override
	protected boolean generate(Set<BlockPos> set, ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos, MutableIntBoundingBox mutableIntBoundingBox) {
		return false;
	}

	public BlockState getLog() {
		return log;
	}

	public BlockState getLeaves() {
		return leaves;
	}

	protected static Direction randomHorizontalDirectionAwayFrom(Random rand, Direction direction) {
		Direction out = randomHorizontalDirection(rand);
		return out == direction ? direction.getOpposite() : out;
	}

	protected static Direction randomHorizontalDirection(Random rand) {
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

	protected BlockState getOriginalState(ModifiableTestableWorld world, BlockPos.Mutable pos) {
		BlockState blockStateBuidler = this.getLog();
		if (world.testBlockState(pos, direction -> direction.get(BareSmallLogBlock.NORTH))) {
			blockStateBuidler.with(BareSmallLogBlock.NORTH, true);
		}
		if (world.testBlockState(pos, direction -> direction.get(BareSmallLogBlock.SOUTH))) {
			blockStateBuidler.with(BareSmallLogBlock.SOUTH, true);
		}
		if (world.testBlockState(pos, direction -> direction.get(BareSmallLogBlock.EAST))) {
			blockStateBuidler.with(BareSmallLogBlock.EAST, true);
		}
		if (world.testBlockState(pos, direction -> direction.get(BareSmallLogBlock.WEST))) {
			blockStateBuidler.with(BareSmallLogBlock.WEST, true);
		}
		if (world.testBlockState(pos, direction -> direction.get(BareSmallLogBlock.UP))) {
			blockStateBuidler.with(BareSmallLogBlock.UP, true);
		}
		if (world.testBlockState(pos, direction -> direction.get(BareSmallLogBlock.DOWN))) {
			blockStateBuidler.with(BareSmallLogBlock.DOWN, true);
		}
		if (this.getLog().getBlock() instanceof SmallLogBlock) {
			if (world.testBlockState(pos, leaves -> leaves.get(SmallLogBlock.HAS_LEAVES))) {
				blockStateBuidler.with(SmallLogBlock.HAS_LEAVES, true);
			}
		}
		return blockStateBuidler;
	}

	protected void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {

		boolean leaves = world.testBlockState(pos, tested -> tested.getBlock() instanceof SmallLogBlock && tested.get(SmallLogBlock.HAS_LEAVES));
		Predicate<BlockState> tester = tested -> tested.getBlock() instanceof SmallLogBlock || (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();
		Predicate<BlockState> leafTester = tested -> (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();

		if (world.testBlockState(pos, leafTester)) {
			setBlockState(blocks, world, pos, this.leaves, boundingBox);
		} else {
			if (world.testBlockState(pos, tester)) {
				setBlockState(blocks, world, pos, getOriginalState(world, pos).with(SmallLogBlock.HAS_LEAVES, leaves), boundingBox);
			}
		}
	}
}
