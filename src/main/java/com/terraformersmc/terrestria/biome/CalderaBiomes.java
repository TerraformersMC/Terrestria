package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class CalderaBiomes {

	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x54d3c0)
					.waterFogColor(0x24a0b0)
					.build()
				);

		TerrestriaBiomes.CALDERA = TerrestriaBiomes.register("caldera", template
				.generationSettings(calderaGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				//.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
				//.depth(1.5F)
				//.scale(0.05F)
				.temperature(0.7F)
				.downfall(0.7F)
				.build());

		TerrestriaBiomes.CALDERA_BEACH = TerrestriaBiomes.register("caldera_beach", template
				.generationSettings(calderaGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				//.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
				//.depth(2.25F)
				//.scale(0F)
				.temperature(0.7F)
				.downfall(0.7F)
				.build());

		TerrestriaBiomes.CALDERA_FOOTHILLS = TerrestriaBiomes.register("caldera_foothills", template
				.generationSettings(calderaFoothillsGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings()
					.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4))
					.build())
				//.configureSurfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.EXTREME_HILLS)
				//.depth(1.7F)
				//.scale(0.4F)
				.temperature(0.0F)
				.downfall(0.1F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
					.build()
				)
				.build());

		TerrestriaBiomes.CALDERA_RIDGE = TerrestriaBiomes.register("caldera_ridge", template
				.generationSettings(calderaRidgeGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				//.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.MOUNTAIN)
				//.depth(4F)
				//.scale(0F)
				.temperature(0F)
				.downfall(1F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
					.build()
				)
				.build());
	}

	private static GenerationSettings.Builder calderaGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static GenerationSettings.Builder calderaFoothillsGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_CONIFER_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static GenerationSettings.Builder calderaRidgeGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 3, 1, 4));
		builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.SALMON, 15, 3, 6));
		builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.COD, 15, 1, 5));
		return builder;
	}
}
