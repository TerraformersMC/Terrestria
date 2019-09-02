package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PillagerOutpostFeatureConfig;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class OutbackBiomes {
	public static void register() {
		//TODO this needs to be using a template
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
			.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY,
					DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
			.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.CACTUS, FeatureConfig.DEFAULT, Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(2)))
			.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(TerrestriaFeatures.OUTBACK_SCRUB, DefaultFeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(2, 0.1F, 2)))
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
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.build());
		
		TerrestriaBiomes.OUTBACK_ULURU = TerrestriaBiomes.register("outback_uluru", BetterBiomeBuilder.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.ULURU_BUILDER, TerrestriaSurfaces.OUTBACK_ULURU_CONFIG)
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
			.depth(2.2F)
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
			.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(TerrestriaFeatures.OAK_SHRUB, DefaultFeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(3, 0.1F, 1)))
			.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(TerrestriaFeatures.OUTBACK_SCRUB, DefaultFeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(5, 0.1F, 1)))
			.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.SAVANNA_TREE, DefaultFeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(2, 0.1F, 1)))
			.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION, Biome.configureFeature(Feature.FANCY_TREE, DefaultFeatureConfig.DEFAULT, Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.1F, 1)))
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
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.addGrassFeature(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
			.build());
	}
}
