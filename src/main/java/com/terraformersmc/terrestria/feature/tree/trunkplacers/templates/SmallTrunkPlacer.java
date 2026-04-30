package com.terraformersmc.terrestria.feature.tree.trunkplacers.templates;

import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.function.BiConsumer;

public abstract class SmallTrunkPlacer extends TrunkPlacer {

	public SmallTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	protected void setBlockStateAndUpdate(TreeConfiguration config, RandomSource random, BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel world, BlockPos origin, Direction direction) {
		//Place the block
		checkAndPlaceSpecificBlockState(world, origin, replacer, config.trunkProvider.getState(world, random, origin).setValue(getPropertyFromDirection(direction.getOpposite()), true));

		// Fix the one behind it to connect if it's a BareSmallLogBlock
		addSmallLogConnection(config, random, replacer, world, origin.relative(direction.getOpposite()), direction);
	}

	protected void addSmallLogConnection(TreeConfiguration config, RandomSource random, BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel world, BlockPos origin, Direction direction) {
		if (world.isStateAtPosition(origin, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			placeSpecificBlockState(world, replacer, origin, getOriginalState(config, world, origin, random).setValue(getPropertyFromDirection(direction), true));
		}
	}

	protected static void checkAndPlaceSpecificBlockState(LevelSimulatedReader testableWorld, BlockPos blockPos, BiConsumer<BlockPos, BlockState> replacer, BlockState blockState) {
		if (TreeFeature.validTreePos(testableWorld, blockPos)) {
			placeSpecificBlockState(testableWorld, replacer, blockPos, blockState);
		}
	}

	protected static void placeSpecificBlockState(LevelSimulatedReader testableWorld, BiConsumer<BlockPos, BlockState> replacer, BlockPos blockPos, BlockState blockState) {
		replacer.accept(blockPos.immutable(), blockState);
	}

	protected BlockState getOriginalState(TreeConfiguration config, WorldGenLevel world, BlockPos pos, RandomSource random) {

		if (!world.isStateAtPosition(pos, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			return null;
		}

		return config.trunkProvider.getState(world, random, pos)
				.setValue(BareSmallLogBlock.NORTH, world.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.NORTH)))
				.setValue(BareSmallLogBlock.SOUTH, world.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.SOUTH)))
				.setValue(BareSmallLogBlock.EAST, world.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.EAST)))
				.setValue(BareSmallLogBlock.WEST, world.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.WEST)))
				.setValue(BareSmallLogBlock.UP, world.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.UP)))
				.setValue(BareSmallLogBlock.DOWN, world.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.DOWN)));
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
