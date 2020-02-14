package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.*;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class OutbackBiomes {
	public static void register() {
		//TODO this needs to be using a template
		TerrestriaBiomes.OUTBACK = TerrestriaBiomes.register("outback", TerraformBiome.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
			.depth(0.125F)
			.scale(0.05F)
			.temperature(1.8F)
			.downfall(0.3F)
			.waterColor(4159204)
			.waterFogColor(329011)
			.setSpawnChance(0.03F)
			.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY,
					DEFAULT_MUSHROOMS, DESERT_VEGETATION, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
			.addTreeFeature(TerrestriaFeatures.OUTBACK_SCRUB.configure(TerrestriaFeatureConfigs.OUTBACK_SCRUB), 3)
			.addDefaultSpawnEntries()
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.build());

		TerrestriaBiomes.OUTBACK_ULURU = TerrestriaBiomes.register("outback_uluru", TerraformBiome.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.ULURU_BUILDER, TerrestriaSurfaces.OUTBACK_ULURU_CONFIG)
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
			.depth(2.2F)
			.scale(0.18F)
			.temperature(1.8F)
			.downfall(0.2F)
			.waterColor(4159204)
			.waterFogColor(329011)
			.setSpawnChance(0.03F)
			.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY, SAVANNA_GRASS,
					DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 10, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.HUSK, 50, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE, 45, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1))
			.build());

		//TODO
		TerrestriaBiomes.OUTBACK_BUSHLAND = TerrestriaBiomes.register("outback_bushland", TerraformBiome.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
			.depth(0.125F)
			.scale(0.05F)
			.temperature(1.8F)
			.downfall(0.3F)
			.waterColor(4159204)
			.waterFogColor(329011)
			.setSpawnChance(0.03F)
			.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY, SAVANNA_GRASS,
					DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
			.addTreeFeature(TerrestriaFeatures.OAK_SHRUB.configure(TerrestriaFeatureConfigs.OAK_SHRUB), 2)
			.addTreeFeature(TerrestriaFeatures.OUTBACK_SCRUB.configure(TerrestriaFeatureConfigs.OUTBACK_SCRUB), 3)
			.addTreeFeature(Feature.FANCY_TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG), 1)
			.addTreeFeature(Feature.ACACIA_TREE.configure(DefaultBiomeFeatures.ACACIA_TREE_CONFIG), 2)
			.addDefaultSpawnEntries()
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.addGrassFeature(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
			.build());
	}
}
