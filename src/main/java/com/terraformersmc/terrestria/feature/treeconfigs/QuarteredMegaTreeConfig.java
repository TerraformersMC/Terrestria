package com.terraformersmc.terrestria.feature.treeconfigs;

import com.terraformersmc.terrestria.feature.treeconfigs.helpers.TerrestriaTreeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class QuarteredMegaTreeConfig extends TerrestriaTreeFeatureConfig {

	public BlockState quarterLogBlock;

	public QuarteredMegaTreeConfig(TreeFeatureConfig config, BlockState quarterLogBlock) {
		super(config);
		this.quarterLogBlock = quarterLogBlock;
	}

	public BlockState getQuarterLogBlock() {
		return quarterLogBlock;
	}
}
