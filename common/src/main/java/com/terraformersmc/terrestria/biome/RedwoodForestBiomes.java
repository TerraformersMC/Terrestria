package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class RedwoodForestBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				.temperature(0.9F)
				.downfall(0.9F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
					.build()
				);

		TerrestriaBiomes.REDWOOD_FOREST = TerrestriaBiomes.register("redwood_forest", template
				.generationSettings(redwoodForestGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build());

		TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST = TerrestriaBiomes.register("windswept_redwood_forest", template
				.generationSettings(windsweptRedwoodForestGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build());
	}

	private static GenerationSettings.Builder redwoodForestGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSEST_MEGA_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_FALLEN_REDWOOD_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES);
		DefaultBiomeFeatures.addTaigaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSweetBerryBushesSnowy(builder);
		return builder;
	}

	private static GenerationSettings.Builder windsweptRedwoodForestGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_FALLEN_REDWOOD_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_FALLEN_HEMLOCK_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.PATCH_LUSH_FERNS);
		DefaultBiomeFeatures.addTaigaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSweetBerryBushesSnowy(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4));
		return builder;
	}
}
