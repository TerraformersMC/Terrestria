package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.BareSmallLogBlock;
import com.terraformersmc.terraform.block.SmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SmallLogTree extends AbstractTreeFeature<DefaultFeatureConfig> {

	private BlockState log;
	private BlockState leaves;

	public SmallLogTree(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1, boolean boolean_1, BlockState log, BlockState leaves) {
		super(function_1, boolean_1);

		this.log = log;
		this.leaves = leaves;
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
				if (world1.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BareSmallLogBlock) {
					setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.SOUTH, true), boundingBox);
				}
				break;
			case NORTH:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.SOUTH, true), boundingBox);
				if (world1.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BareSmallLogBlock) {
					setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.NORTH, true), boundingBox);
				}
					break;
			case WEST:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.EAST, true), boundingBox);
				if (world1.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BareSmallLogBlock) {
					setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.WEST, true), boundingBox);
				}
				break;
			case EAST:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.WEST, true), boundingBox);
				if (world1.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BareSmallLogBlock) {
					setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.EAST, true), boundingBox);
				}
				break;
			case DOWN:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.UP, true), boundingBox);
				if (world1.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BareSmallLogBlock) {
					setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.DOWN, true), boundingBox);
				}
				break;
			case UP:
				setBlockState(blocks, world, pos, state.with(SmallLogBlock.DOWN, true), boundingBox);
				if (world1.getBlockState(pos.offset(direction.getOpposite())).getBlock() instanceof BareSmallLogBlock) {
					setBlockState(blocks, world, pos.offset(direction.getOpposite()), world1.getBlockState(origin.offset(direction.getOpposite())).with(SmallLogBlock.UP, true), boundingBox);
				}
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

	protected void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, BlockState blockState, MutableIntBoundingBox boundingBox) {
		BlockState originalBlockState = ((World) world).getBlockState(pos);
		if (originalBlockState.getBlock() instanceof SmallLogBlock) {
			setBlockState(blocks, world, pos, originalBlockState.with(SmallLogBlock.HAS_LEAVES, true), boundingBox);
		} else {
			if (isAirOrLeaves(world, pos)) {
				setBlockState(blocks, world, pos, blockState, boundingBox);
			}
		}
	}
}
