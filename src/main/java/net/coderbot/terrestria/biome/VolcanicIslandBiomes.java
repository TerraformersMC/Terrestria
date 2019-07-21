package net.coderbot.terrestria.biome;

import net.coderbot.terrestria.biome.builder.TerrestriaBiome;
import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.coderbot.terrestria.init.TerrestriaFeatures;
import net.coderbot.terrestria.init.TerrestriaSurfaces;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

import static net.coderbot.terrestria.biome.builder.TerrestriaBiome.DefaultFeature.*;

public class VolcanicIslandBiomes {
	public static void register() {
		TerrestriaBiomes.VOLCANIC_ISLAND = TerrestriaBiomes.register("volcanic_island", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(TerrestriaSurfaces.CLIFF, TerrestriaSurfaces.BASALT_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS)
				.depth(0.1F)
				.scale(0.2F)
				.temperature(0.9F)
				.downfall(0.9F)
				.waterColor(0x54d3c0)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addGrassFeature(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
				.addTreeFeature(TerrestriaFeatures.PALM_TREE, 5)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SQUID, 3, 1, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SALMON, 15, 3, 6))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.COD, 15, 1, 5))
				.build());

		TerrestriaBiomes.VOLCANIC_ISLAND_SHORE = TerrestriaBiomes.register("volcanic_island_shore", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(TerrestriaSurfaces.CLIFF, TerrestriaSurfaces.BASALT_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
				.depth(0.05F)
				.scale(0.05F)
				.temperature(0.9F)
				.downfall(0.9F)
				.waterColor(0x54d3c0)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addGrassFeature(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
				.addTreeFeature(TerrestriaFeatures.PALM_TREE, 2)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SQUID, 3, 1, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SALMON, 15, 3, 6))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.COD, 15, 1, 5))
				.build());

		TerrestriaBiomes.VOLCANIC_ISLAND_BEACH = TerrestriaBiomes.register("volcanic_island_beach", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(TerrestriaSurfaces.BASALT_BEACH, TerrestriaSurfaces.BASALT_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
				.depth(0F)
				.scale(0.05F)
				.temperature(0.9F)
				.downfall(0.9F)
				.waterColor(0x54d3c0)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addGrassFeature(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
				.addTreeFeature(TerrestriaFeatures.PALM_TREE, 2)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SQUID, 3, 1, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SALMON, 15, 3, 6))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.COD, 15, 1, 5))
				.build());
	}
}
