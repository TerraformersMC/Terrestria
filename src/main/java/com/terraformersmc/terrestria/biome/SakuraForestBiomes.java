package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatureConfigs;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class SakuraForestBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.temperature(0.8F)
				.downfall(1.0F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DEFAULT_MUSHROOMS,
						DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.SAKURA_TREE.configure(TerrestriaFeatureConfigs.SAKURA), 6)
				.addTreeFeature(Feature.JUNGLE_GROUND_BUSH.configure(TerrestriaFeatureConfigs.JAPANESE_MAPLE_SHRUB), 4)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
		);

		TerrestriaBiomes.SAKURA_FOREST = TerrestriaBiomes.register("sakura_forest", template.builder()
				.depth(0.05F)
				.scale(0.1F)
				.build());

		TerrestriaBiomes.WOODED_SAKURA_HILLS = TerrestriaBiomes.register("wooded_sakura_hills", template.builder()
				.depth(0.4F)
				.scale(0.3F)
				.build());
	}
}
