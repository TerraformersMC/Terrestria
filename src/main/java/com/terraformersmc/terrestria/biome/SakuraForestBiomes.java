package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.biome.builder.DefaultFeature;
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
		TerrestriaBiome.Frozen template = TerrestriaBiome.freeze(TerrestriaBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.temperature(0.8F)
				.downfall(1.0F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(DefaultFeature.LAND_CARVERS, DefaultFeature.STRUCTURES, DefaultFeature.LAKES, DefaultFeature.DUNGEONS, DefaultFeature.MINEABLES, DefaultFeature.ORES, DefaultFeature.DEFAULT_MUSHROOMS,
						DefaultFeature.DEFAULT_VEGETATION, DefaultFeature.SPRINGS, DefaultFeature.FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.SAKURA_TREE, 6)
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_SHRUB, 4)
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
