package com.terraformersmc.terrestria.feature.tree.treeconfigs;

import com.terraformersmc.terrestria.feature.tree.treeconfigs.helpers.ExtendedTreeGeneration;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.helpers.TerrestriaTreeFeatureConfig;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SandyTreeConfig extends TerrestriaTreeFeatureConfig implements ExtendedTreeGeneration {
	public SandyTreeConfig(TreeFeatureConfig config) {
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
