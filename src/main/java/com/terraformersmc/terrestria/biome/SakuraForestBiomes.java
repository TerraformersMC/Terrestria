package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.biome.builder.TerrestriaBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class SakuraForestBiomes {
	public static void register() {
		TerrestriaBiomes.SAKURA_FOREST = TerrestriaBiomes.register("sakura_forest", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.05F)
				.scale(0.1F)
				.temperature(0.8F)
				.downfall(1.0F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(TerrestriaBiome.DefaultFeature.LAND_CARVERS, TerrestriaBiome.DefaultFeature.STRUCTURES, TerrestriaBiome.DefaultFeature.LAKES, TerrestriaBiome.DefaultFeature.DUNGEONS, TerrestriaBiome.DefaultFeature.MINEABLES, TerrestriaBiome.DefaultFeature.ORES, TerrestriaBiome.DefaultFeature.DEFAULT_MUSHROOMS,
						TerrestriaBiome.DefaultFeature.DEFAULT_VEGETATION, TerrestriaBiome.DefaultFeature.SPRINGS, TerrestriaBiome.DefaultFeature.FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.SAKURA_TREE, 6)
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_SHRUB, 4)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());

		TerrestriaBiomes.WOODED_SAKURA_HILLS = TerrestriaBiomes.register("wooded_sakura_hills", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.4F)
				.scale(0.3F)
				.temperature(0.8F)
				.downfall(1.0F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(TerrestriaBiome.DefaultFeature.LAND_CARVERS, TerrestriaBiome.DefaultFeature.STRUCTURES, TerrestriaBiome.DefaultFeature.LAKES, TerrestriaBiome.DefaultFeature.DUNGEONS, TerrestriaBiome.DefaultFeature.MINEABLES, TerrestriaBiome.DefaultFeature.ORES, TerrestriaBiome.DefaultFeature.DEFAULT_MUSHROOMS,
						TerrestriaBiome.DefaultFeature.DEFAULT_VEGETATION, TerrestriaBiome.DefaultFeature.SPRINGS, TerrestriaBiome.DefaultFeature.FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.SAKURA_TREE, 6)
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_SHRUB, 4)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());
	}
}
