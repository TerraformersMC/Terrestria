package com.terraformersmc.terrestria.feature.tree.trunkplacers.templates;

import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.Random;
import java.util.Set;

public abstract class SmallTrunkPlacer extends TrunkPlacer {

	public SmallTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	protected void setBlockStateAndUpdate(TreeFeatureConfig config, Random random, Set<BlockPos> set, ModifiableTestableWorld world, BlockPos origin, Direction direction, BlockBox blockBox) {
		//Place the block
		checkAndPlaceSpecificBlockState(world, origin, set, blockBox, config.trunkProvider.getBlockState(random, origin).with(getPropertyFromDirection(direction.getOpposite()), true));

		// Fix the one behind it to connect if it's a BareSmallLogBlock
		addSmallLogConnection(config, random, set, world, origin.offset(direction.getOpposite()), direction, blockBox);
	}

	protected void addSmallLogConnection(TreeFeatureConfig config, Random random, Set<BlockPos> set, ModifiableTestableWorld world, BlockPos origin, Direction direction, BlockBox blockBox) {
		if (world.testBlockState(origin, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			placeSpecificBlockState(world, origin, set, blockBox, getOriginalState(config, world, origin, random).with(getPropertyFromDirection(direction), true));
		}
	}

	protected static void checkAndPlaceSpecificBlockState(ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos, Set<BlockPos> set, BlockBox blockBox, BlockState blockState) {
		if (TreeFeature.canReplace(modifiableTestableWorld, blockPos)) {
			placeSpecificBlockState(modifiableTestableWorld, blockPos, set, blockBox, blockState);
		}
	}

	protected static void placeSpecificBlockState(ModifiableTestableWorld modifiableTestableWorld, BlockPos blockPos, Set<BlockPos> set, BlockBox blockBox, BlockState blockState) {
		setBlockState(modifiableTestableWorld, blockPos, blockState, blockBox);
		set.add(blockPos.toImmutable());
	}

	protected BlockState getOriginalState(TreeFeatureConfig config, ModifiableTestableWorld world, BlockPos pos, Random random) {

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
