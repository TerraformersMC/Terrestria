package com.terraformersmc.terrestria.feature.tree.treeconfigs.helpers;

import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class TerrestriaTreeFeatureConfig extends TreeFeatureConfig {
	public TerrestriaTreeFeatureConfig(TreeFeatureConfig config) {
		super(config.trunkProvider, config.leavesProvider, config.foliagePlacer, config.trunkPlacer, config.minimumSize, config.decorators, config.maxWaterDepth, config.ignoreVines, config.heightmap);
	}
}
