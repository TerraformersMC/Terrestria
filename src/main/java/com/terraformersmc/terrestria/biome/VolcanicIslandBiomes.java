package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.*;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class VolcanicIslandBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.configureSurfaceBuilder(TerrestriaSurfaces.BASALT_CLIFF, TerrestriaSurfaces.BASALT_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
				.temperature(0.9F)
				.downfall(0.9F)
				.waterColor(0x54d3c0)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addGrassFeature(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
				.addGrassFeature(Blocks.GRASS.getDefaultState(), 1)
				.addGrassFeature(Blocks.FERN.getDefaultState(), 1)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_JUNGLE)
				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_DESERT)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SQUID, 3, 1, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SALMON, 15, 3, 6))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.COD, 15, 1, 5))
				.addStructureFeature(TerrestriaStructures.VOLCANO)
		);

		TerrestriaBiomes.VOLCANIC_ISLAND = TerrestriaBiomes.register("volcanic_island", template.builder()
				.category(Biome.Category.EXTREME_HILLS)
				.depth(0.1F)
				.scale(0.2F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.JUNGLE_PALM_TREE), 5)
				.build());

		TerrestriaBiomes.VOLCANIC_ISLAND_SHORE = TerrestriaBiomes.register("volcanic_island_shore", template.builder()
				.depth(0.05F)
				.scale(0.05F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.JUNGLE_PALM_TREE), 2)
				.addRareTreeFeature(TerrestriaFeatures.DUM_DUM_HEAD.configure(new DefaultFeatureConfig()), 15)
				.build());

		TerrestriaBiomes.VOLCANIC_ISLAND_BEACH = TerrestriaBiomes.register("volcanic_island_beach", template.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.BASALT_BEACH, TerrestriaSurfaces.BASALT_CONFIG)
				.depth(0F)
				.scale(0.05F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.JUNGLE_PALM_TREE), 2)
				.build());
	}
}
