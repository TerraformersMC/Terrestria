package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.biome.builder.TerrestriaBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;

import static com.terraformersmc.terrestria.biome.builder.TerrestriaBiome.DefaultFeature.*;
import static net.minecraft.world.gen.feature.MineshaftFeature.Type.NORMAL;

public class CanyonBiomes {
	public static void register() {
		TerrestriaBiomes.CANYON_CLIFFS = TerrestriaBiomes.register("canyon_cliffs", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(TerrestriaSurfaces.SANDSTONE_CLIFF, TerrestriaSurfaces.SANDSTONE_CONFIG)
				.precipitation(Biome.Precipitation.NONE).category(Biome.Category.DESERT)
				.depth(0.0F)
				.scale(0.2F)
				.temperature(0.9F)
				.downfall(0.1F)
				.waterColor(0x4da5e3)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DESERT_DEAD_BUSHES,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DESERT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, NORMAL))
				.addDefaultSpawnEntries()
				.build());

		TerrestriaBiomes.CANYON = TerrestriaBiomes.register("canyon", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(TerrestriaSurfaces.SANDSTONE_CLIFF, TerrestriaSurfaces.SANDSTONE_CONFIG)
				.precipitation(Biome.Precipitation.NONE).category(Biome.Category.DESERT)
				.depth(0.0F)
				.scale(0.2F)
				.temperature(0.9F)
				.downfall(0.1F)
				.waterColor(0x4da5e3)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DESERT_DEAD_BUSHES,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DESERT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, NORMAL))
				.addStructureFeature(TerrestriaFeatures.CANYON_ARCH_STRUCTURE)
				.addDefaultSpawnEntries()
				.build());
	}
}
