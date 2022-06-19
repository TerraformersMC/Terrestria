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

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class LushRedwoodForestBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				//.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				//.depth(1.2F)
				//.scale(0.3F)
				.temperature(0.9F)
				.downfall(0.9F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
					.build()
				);

		TerrestriaBiomes.LUSH_REDWOOD_FOREST = TerrestriaBiomes.register("lush_redwood_forest", template
				.generationSettings(lushRedwoodForestGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				//.playerSpawnFriendly()
				.build());
	}

	private static GenerationSettings.Builder lushRedwoodClearingGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_FALLEN_REDWOOD_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_FALLEN_HEMLOCK_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.PATCH_LUSH_FERNS);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addTaigaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSweetBerryBushesSnowy(builder);
		return builder;
	}

	private static GenerationSettings.Builder lushRedwoodForestGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.MEGA_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.FALLEN_REDWOOD_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.FALLEN_HEMLOCK_LOGS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.PATCH_LUSH_FERNS);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addTaigaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSweetBerryBushesSnowy(builder);
		return builder;
	}

	private static GenerationSettings.Builder lushRedwoodForestEdgeGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.SMALL_HEMLOCK_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.PATCH_LUSH_FERNS);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
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
