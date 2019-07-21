package net.coderbot.terrestria.biome;

import net.coderbot.terrestria.biome.builder.TerrestriaBiome;
import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.coderbot.terrestria.init.TerrestriaFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static net.coderbot.terrestria.biome.builder.TerrestriaBiome.DefaultFeature.*;
import static net.coderbot.terrestria.biome.builder.TerrestriaBiome.DefaultFeature.FROZEN_TOP_LAYER;

public class CypressForestBiomes {
	public static void register() {
		TerrestriaBiomes.CYPRESS_FOREST = TerrestriaBiomes.register("cypress_forest", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.1F)
				.scale(0.2F)
				.temperature(0.7F)
				.downfall(0.8F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, FOREST_FLOWERS, MINEABLES, ORES, DISKS,
						DEFAULT_FLOWERS, DEFAULT_MUSHROOMS, FOREST_GRASS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addTreeFeature(TerrestriaFeatures.CYPRESS_TREE, 9)
				.addTreeFeature(TerrestriaFeatures.TALLER_BIRCH_TREE, 4)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());

		TerrestriaBiomes.WOODED_CYPRESS_HILLS = TerrestriaBiomes.register("wooded_cypress_hills", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.45F)
				.scale(0.3F)
				.temperature(0.7F)
				.downfall(0.8F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, FOREST_FLOWERS, MINEABLES, ORES, DISKS,
						DEFAULT_FLOWERS, DEFAULT_MUSHROOMS, FOREST_GRASS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addTreeFeature(TerrestriaFeatures.CYPRESS_TREE, 9)
				.addTreeFeature(TerrestriaFeatures.TALLER_BIRCH_TREE, 4)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());
	}
}
