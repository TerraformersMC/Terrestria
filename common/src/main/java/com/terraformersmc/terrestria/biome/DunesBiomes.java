package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class DunesBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.DESERT)
			.temperature(0.9F)
			.downfall(0.1F)
			.effects(TerrestriaBiomes.createDefaultBiomeEffects()
				.waterColor(0x4da5e3)
				.waterFogColor(0x24a0b0)
				.build()
			);

		TerrestriaBiomes.DUNES = TerrestriaBiomes.register("dunes", template
			.generationSettings(dunesGenerationSettings().build())
			.spawnSettings(defaultSpawnSettings().build())
			.build()
		);
	}

	private static GenerationSettings.Builder dunesGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		return builder;
	}
}
