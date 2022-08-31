package com.terraformersmc.terrestria.feature.tree.trunkplacers.templates;

import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.function.BiConsumer;

public abstract class SmallTrunkPlacer extends TrunkPlacer {

	public SmallTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	protected void setBlockStateAndUpdate(TreeFeatureConfig config, Random random, BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos origin, Direction direction) {
		//Place the block
		checkAndPlaceSpecificBlockState(world, origin, replacer, config.trunkProvider.getBlockState(random, origin).with(getPropertyFromDirection(direction.getOpposite()), true));

		// Fix the one behind it to connect if it's a BareSmallLogBlock
		addSmallLogConnection(config, random, replacer, world, origin.offset(direction.getOpposite()), direction);
	}

	protected void addSmallLogConnection(TreeFeatureConfig config, Random random, BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos origin, Direction direction) {
		if (world.testBlockState(origin, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			placeSpecificBlockState(world, replacer, origin, getOriginalState(config, world, origin, random).with(getPropertyFromDirection(direction), true));
		}
	}

	protected static void checkAndPlaceSpecificBlockState(TestableWorld testableWorld, BlockPos blockPos, BiConsumer<BlockPos, BlockState> replacer, BlockState blockState) {
		if (TreeFeature.canReplace(testableWorld, blockPos)) {
			placeSpecificBlockState(testableWorld, replacer, blockPos, blockState);
		}
	}

	protected static void placeSpecificBlockState(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> replacer, BlockPos blockPos, BlockState blockState) {
		replacer.accept(blockPos.toImmutable(), blockState);
	}

	protected BlockState getOriginalState(TreeFeatureConfig config, TestableWorld world, BlockPos pos, Random random) {

		if (!world.testBlockState(pos, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			return null;
		}

		return config.trunkProvider.getBlockState(random, pos)
				.with(BareSmallLogBlock.NORTH, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.NORTH)))
				.with(BareSmallLogBlock.SOUTH, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.SOUTH)))
				.with(BareSmallLogBlock.EAST, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.EAST)))
				.with(BareSmallLogBlock.WEST, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.WEST)))
				.with(BareSmallLogBlock.UP, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.UP)))
				.with(BareSmallLogBlock.DOWN, world.testBlockState(pos, test -> test.get(BareSmallLogBlock.DOWN)));
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
