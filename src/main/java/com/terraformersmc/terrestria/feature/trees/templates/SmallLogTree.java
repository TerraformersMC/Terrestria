package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.BareSmallLogBlock;
import com.terraformersmc.terraform.block.SmallLogBlock;
import net.minecraft.block.BlockState;
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

public class SmallLogTree extends AbstractTreeFeature<DefaultFeatureConfig> {

	private BlockState log;
	private BlockState leaves;

	public SmallLogTree(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configDeserializer, boolean updateNeighbor, BlockState log, BlockState leaves) {
		super(configDeserializer, updateNeighbor);

		this.log = log;
		this.leaves = leaves;
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

	protected void setBlockStateAndUpdate(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, BlockState state, Direction direction, MutableIntBoundingBox boundingBox) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin.offset(direction.getOpposite()));
		if (getOriginalState(world, pos) != null) {
			// Fix the previous block
			setBlockState(blocks, world, pos, getOriginalState(world, pos).with(getPropertyFromDirection(direction), true), boundingBox);
		}
		pos.setOffset(direction);
		// Place a new block and connect it to the previous block
		setBlockState(blocks, world, pos, log.with(getPropertyFromDirection(direction.getOpposite()), true), boundingBox);
	}

	protected void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {
		if (world.testBlockState(pos, BlockState::isAir)) {
			setBlockState(blocks, world, pos, this.leaves, boundingBox);
		} else {
			if (world.testBlockState(pos, isLog -> isLog.getBlock() instanceof SmallLogBlock)) {
				setBlockState(blocks, world, pos, getOriginalState(world, pos)
					.with(SmallLogBlock.HAS_LEAVES, true), boundingBox);
			}
		}
	}

	protected BlockState getOriginalState(ModifiableTestableWorld world, BlockPos.Mutable pos) {

		if (!world.testBlockState(pos, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			return null;
		}

		return this.getLog()
			.with(BareSmallLogBlock.NORTH, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.NORTH)))
			.with(BareSmallLogBlock.SOUTH, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.SOUTH)))
			.with(BareSmallLogBlock.EAST, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.EAST)))
			.with(BareSmallLogBlock.WEST, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.WEST)))
			.with(BareSmallLogBlock.UP, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.UP)))
			.with(BareSmallLogBlock.DOWN, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.DOWN)));
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

	protected BooleanProperty getPropertyFromDirection(Direction direction) {
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
}
