package com.terraformersmc.terrestria.feature.tree.treeconfigs;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class QuarteredMegaTreeConfig extends TreeFeatureConfig {

	public final BlockState quarterLogBlock;
	public final BlockState logBlock;
	public final BlockState woodBlock;

	public QuarteredMegaTreeConfig(TreeFeatureConfig config, BlockState quarterLogBlock, BlockState logBlock, BlockState woodBlock) {
		super(config.trunkProvider, config.leavesProvider, config.foliagePlacer, config.trunkPlacer, config.minimumSize, config.decorators, config.maxWaterDepth, config.ignoreVines, config.heightmap);

		this.quarterLogBlock = quarterLogBlock;
		this.logBlock = logBlock;
		this.woodBlock = woodBlock;
	}
}
