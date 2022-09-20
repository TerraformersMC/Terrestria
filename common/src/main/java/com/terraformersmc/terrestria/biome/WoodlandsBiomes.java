package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class WoodlandsBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN)
				.temperature(0.9F)
				.downfall(0.3F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
					.build()
				);

		TerrestriaBiomes.DENSE_WOODLANDS = TerrestriaBiomes.register("dense_woodlands", template
				.generationSettings(denseWoodlandsGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build()
		);

		TerrestriaBiomes.WINDSWEPT_WOODLANDS = TerrestriaBiomes.register("windswept_woodlands", template
				.generationSettings(windsweptWoodlandsGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build()
		);
	}

	private static GenerationSettings.Builder denseWoodlandsGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addPlainsTallGrass(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		// TODO: "oak spruce" is a placeholder until we get cedar trees
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_OAK_SPRUCE_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_OAK_SHRUBS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSEST_DENSE_WOODLANDS_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_FALLEN_OAK_LOGS);
		DefaultBiomeFeatures.addPlainsFeatures(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static GenerationSettings.Builder windsweptWoodlandsGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addPlainsTallGrass(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		// TODO: "oak spruce" is a placeholder until we get cedar trees
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_OAK_SPRUCE_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_OAK_SHRUBS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_DENSE_WOODLANDS_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_FALLEN_OAK_LOGS);
		DefaultBiomeFeatures.addPlainsFeatures(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		return builder;
	}
}
