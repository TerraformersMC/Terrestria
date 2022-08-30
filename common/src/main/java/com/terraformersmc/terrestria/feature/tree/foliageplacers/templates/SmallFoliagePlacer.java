package com.terraformersmc.terrestria.feature.tree.foliageplacers.templates;

import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.block.SmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.function.BiConsumer;

public abstract class SmallFoliagePlacer extends FoliagePlacer {

	public SmallFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	protected void tryPlaceLeaves(TestableWorld world, BlockPos pos, Random random, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config) {
		if (world.testBlockState(pos, isLog -> isLog.getBlock() instanceof SmallLogBlock)) {
			replacer.accept(pos, getOriginalState(config, world, pos, random).with(SmallLogBlock.HAS_LEAVES, true));
			return;
		}
		if (world.testBlockState(pos, BlockState::isAir)) {
			replacer.accept(pos, config.foliageProvider.getBlockState(random, pos));
		}
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
}
