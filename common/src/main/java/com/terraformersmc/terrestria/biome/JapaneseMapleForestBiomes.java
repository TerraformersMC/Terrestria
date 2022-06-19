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

public class JapaneseMapleForestBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.temperature(0.8F)
				.downfall(0.5F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
					.grassColor(0x7aab1a)
					.foliageColor(0x7aab1a)
					.build()
				);

		TerrestriaBiomes.JAPANESE_MAPLE_FOREST = TerrestriaBiomes.register("japanese_maple_forest", template
				.generationSettings(japaneseMapleForestGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build());
	}

	private static GenerationSettings.Builder japaneseMapleForestGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_JAPANESE_MAPLE_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_DARK_JAPANESE_MAPLE_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_JAPANESE_MAPLE_SHRUBS);
		DefaultBiomeFeatures.addForestGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
		return builder;
	}
}
