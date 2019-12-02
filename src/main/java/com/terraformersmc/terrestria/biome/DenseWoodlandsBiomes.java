package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class DenseWoodlandsBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				.depth(0.125F)
				.scale(0.05F)
				.temperature(0.9F)
				.downfall(0.3F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, PLAINS_TALL_GRASS, MINEABLES, ORES, DISKS,
						PLAINS_FEATURES, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addDefaultSpawnEntries()
		);

		TerrestriaBiomes.DENSE_WOODLANDS = TerrestriaBiomes.register("dense_woodlands", template.builder()
				.addTreeFeature(Feature.FANCY_TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG), 7)
				.build()
		);

		TerrestriaBiomes.DENSE_WOODLANDS_EDGE = TerrestriaBiomes.register("dense_woodlands_edge", template.builder()
				.addTreeFeature(Feature.FANCY_TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG), 3)
				.build()
		);
	}
}
