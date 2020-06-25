package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.feature.foliageplacers.PalmFanFoliagePlacer;
import com.terraformersmc.terrestria.feature.treeconfigs.PalmTreeFeatureConfig;
import com.terraformersmc.terrestria.feature.trunkplacers.BentTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class TerrestriaFeatureConfigs {

	public static TreeFeatureConfig JUNGLE_PALM_FEATURE_CONFIG;

	public static void init() {
		JUNGLE_PALM_FEATURE_CONFIG = new PalmTreeFeatureConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.JUNGLE_WOOD.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState()),
				new PalmFanFoliagePlacer(3, 0, 0, 0),
				new BentTrunkPlacer(15, 15, 15),
				new TwoLayersFeatureSize(1, 0, 2))

				.ignoreVines()
				.build());
	}
}
