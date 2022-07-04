package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class CanyonBiomes {

	public static void register() {
		final Biome.Builder template = new Biome.Builder()
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.DESERT)
			.temperature(0.9F)
			.downfall(0.1F)
			.effects(TerrestriaBiomes.createDefaultBiomeEffects()
				.waterColor(0x4da5e3)
				.waterFogColor(0x24a0b0)
				.foliageColor(0xbdea62)
				.build()
			);

		TerrestriaBiomes.CANYON = TerrestriaBiomes.register("canyon", template
			.generationSettings(canyonGenerationSettings().build())
			.spawnSettings(defaultSpawnSettings().build())
			.build());
	}

	private static GenerationSettings.Builder canyonGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.RARE_BRYCE_TREES);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDesertDeadBushes(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addDesertFeatures(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		return builder;
	}
}
