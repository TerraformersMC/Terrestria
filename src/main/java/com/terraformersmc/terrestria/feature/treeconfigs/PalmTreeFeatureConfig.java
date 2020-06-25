package com.terraformersmc.terrestria.feature.treeconfigs;

import com.terraformersmc.terrestria.feature.treeconfigs.helpers.ExtendedTreeGeneration;
import com.terraformersmc.terrestria.feature.treeconfigs.helpers.TerrestriaTreeFeatureConfig;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class PalmTreeFeatureConfig extends TerrestriaTreeFeatureConfig implements ExtendedTreeGeneration {
	public PalmTreeFeatureConfig(TreeFeatureConfig config) {
		super(config);
	}

	@Override
	public boolean canGenerateOn(ModifiableTestableWorld world, BlockPos pos) {
		return (world.testBlockState(pos, (state) -> {
			Block block = state.getBlock();
			return block == Blocks.SAND || block == Blocks.RED_SAND || block == TerrestriaBlocks.BASALT_SAND;
		})) || Feature.isSoil(world, pos);
	}
}
