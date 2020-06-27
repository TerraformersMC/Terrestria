package com.terraformersmc.terrestria.feature.tree.foliageplacers.templates;

import com.terraformersmc.terraform.block.BareSmallLogBlock;
import com.terraformersmc.terraform.block.SmallLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.Random;
import java.util.Set;

public abstract class SmallFoliagePlacer extends FoliagePlacer {

	public SmallFoliagePlacer(int radius, int randomRadius, int offset, int randomOffset) {
		super(radius, randomRadius, offset, randomOffset);
	}

	protected void tryPlaceLeaves(ModifiableTestableWorld world, BlockPos pos, Random random, Set<BlockPos> leaves, TreeFeatureConfig config) {
		if (world.testBlockState(pos, isLog -> isLog.getBlock() instanceof SmallLogBlock)) {
			world.setBlockState(pos, getOriginalState(config, world, pos, random).with(SmallLogBlock.HAS_LEAVES, true), 0);
			leaves.add(pos);
			return;
		}
		if (world.testBlockState(pos, BlockState::isAir)) {
			world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 0);
			leaves.add(pos);
		}
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
}
