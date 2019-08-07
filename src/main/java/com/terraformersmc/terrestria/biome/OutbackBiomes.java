package com.terraformersmc.terrestria.biome;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.CLAY;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.DEFAULT_MUSHROOMS;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.DESERT_DEAD_BUSHES;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.DESERT_LAKES;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.DUNGEONS;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.FOSSILS;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.FROZEN_TOP_LAYER;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.LAND_CARVERS;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.MINEABLES;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.ORES;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.SAVANNA_GRASS;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.SPRINGS;
import static com.terraformersmc.terraform.biome.builder.DefaultFeature.STRUCTURES;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PillagerOutpostFeatureConfig;

public class OutbackBiomes {
	public static void register() {
		TerrestriaBiomes.OUTBACK = TerrestriaBiomes.register("outback", BetterBiomeBuilder.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
				.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
				.depth(0.125F)
				.scale(0.05F)
				.temperature(1.8F)
				.downfall(0.3F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.setSpawnChance(0.03F)
				.addStructureFeature(Feature.PILLAGER_OUTPOST, new PillagerOutpostFeatureConfig(0.012D))
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY, SAVANNA_GRASS,
						DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
				.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.CACTUS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 10, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.HORSE, 1, 1, 1))
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
		
		TerrestriaBiomes.OUTBACK_ULURU = TerrestriaBiomes.register("outback_uluru", BetterBiomeBuilder.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
				.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
				.depth(1.9F)
				.scale(0.18F)
				.temperature(1.8F)
				.downfall(0.2F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.setSpawnChance(0.03F)
				.addStructureFeature(Feature.PILLAGER_OUTPOST, new PillagerOutpostFeatureConfig(0.012D))
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
		TerrestriaBiomes.OUTBACK_BUSHLAND = TerrestriaBiomes.register("outback_bushland", BetterBiomeBuilder.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
				.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
				.depth(0.125F)
				.scale(0.05F)
				.temperature(1.8F)
				.downfall(0.3F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.setSpawnChance(0.03F)
				.addStructureFeature(Feature.PILLAGER_OUTPOST, new PillagerOutpostFeatureConfig(0.012D))
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY, SAVANNA_GRASS,
						DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
				.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.CACTUS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 10, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.HORSE, 1, 1, 1))
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
	}
}
