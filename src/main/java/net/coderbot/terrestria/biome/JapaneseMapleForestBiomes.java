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
import static net.coderbot.terrestria.biome.builder.TerrestriaBiome.DefaultFeature.FOREST_GRASS;

public class JapaneseMapleForestBiomes {
	public static void register() {
		TerrestriaBiomes.JAPANESE_MAPLE_FOREST = TerrestriaBiomes.register("japanese_maple_forest", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.05F)
				.scale(0.2F)
				.temperature(0.8F)
				.downfall(0.5F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DEFAULT_MUSHROOMS,
						DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER, FOREST_GRASS)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_TREE, 3)
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_SHRUB, 3)
				.addTreeFeature(TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE, 3)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());

		TerrestriaBiomes.WOODED_JAPANESE_MAPLE_HILLS = TerrestriaBiomes.register("wooded_japanese_maple_hills", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.45F)
				.scale(0.5F)
				.temperature(0.8F)
				.downfall(0.5F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DEFAULT_MUSHROOMS,
						DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER, FOREST_GRASS)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_TREE, 3)
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_SHRUB, 3)
				.addTreeFeature(TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE, 3)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());
	}
}
