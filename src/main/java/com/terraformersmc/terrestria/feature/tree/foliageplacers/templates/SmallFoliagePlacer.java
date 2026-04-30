package com.terraformersmc.terrestria.feature.tree.foliageplacers.templates;

import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.api.block.SmallLogBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

public abstract class SmallFoliagePlacer extends FoliagePlacer {

	public SmallFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	protected void tryPlaceLeaves(WorldGenLevel world, BlockPos pos, RandomSource random, FoliageSetter placer, TreeConfiguration config) {
		if (world.isStateAtPosition(pos, isLog -> isLog.getBlock() instanceof SmallLogBlock)) {
			placer.set(pos, getOriginalState(config, world, pos, random).setValue(SmallLogBlock.HAS_LEAVES, true));
			return;
		}
		if (world.isStateAtPosition(pos, BlockState::isAir)) {
			placer.set(pos, config.foliageProvider.getState(world, random, pos));
		}
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
}
