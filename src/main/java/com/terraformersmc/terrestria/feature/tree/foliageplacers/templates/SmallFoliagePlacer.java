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

	protected void tryPlaceLeaves(WorldGenLevel level, BlockPos pos, RandomSource random, FoliageSetter setter, TreeConfiguration config) {
		if (level.isStateAtPosition(pos, isLog -> isLog.getBlock() instanceof SmallLogBlock)) {
			setter.set(pos, getOriginalState(config, level, pos, random).setValue(SmallLogBlock.HAS_LEAVES, true));
			return;
		}
		if (level.isStateAtPosition(pos, BlockState::isAir)) {
			setter.set(pos, config.foliageProvider.getState(level, random, pos));
		}
	}

	protected BlockState getOriginalState(TreeConfiguration config, WorldGenLevel level, BlockPos pos, RandomSource random) {

		if (!level.isStateAtPosition(pos, tester -> tester.getBlock() instanceof BareSmallLogBlock)) {
			return null;
		}

		return config.trunkProvider.getState(level, random, pos)
				.setValue(BareSmallLogBlock.NORTH, level.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.NORTH)))
				.setValue(BareSmallLogBlock.SOUTH, level.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.SOUTH)))
				.setValue(BareSmallLogBlock.EAST, level.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.EAST)))
				.setValue(BareSmallLogBlock.WEST, level.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.WEST)))
				.setValue(BareSmallLogBlock.UP, level.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.UP)))
				.setValue(BareSmallLogBlock.DOWN, level.isStateAtPosition(pos, test -> test.getValue(BareSmallLogBlock.DOWN)));
	}
}
