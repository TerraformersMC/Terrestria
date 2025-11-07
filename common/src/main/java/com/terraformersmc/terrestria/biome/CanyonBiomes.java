package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.*;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class CanyonBiomes {
	public static Biome create(Registerable<Biome> registerable) {
		return new Biome.Builder()
				.generationSettings(createGenerationSettings(registerable))
				.spawnSettings(createSpawnSettings())
				.precipitation(false)
				.temperature(0.9F)
				.downfall(0.1F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x4da5e3)
						.foliageColor(0xbdea62)
						.build()
				)
				.addEnvironmentAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.with(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, new BackgroundMusic(SoundEvents.MUSIC_OVERWORLD_BADLANDS))
						.with(EnvironmentAttributes.SNOW_GOLEM_MELTS_GAMEPLAY, true)
						.with(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 0x24a0b0)
						.build()
				)
				.build();
	}

	private static GenerationSettings createGenerationSettings(Registerable<Biome> registerable) {
		RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
		RegistryEntryLookup<PlacedFeature> placedFeatures = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

		GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
		addBasicFeatures(builder, true);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.RARE_BRYCE_TREES));
		DefaultBiomeFeatures.addDefaultGrass(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH_2);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder, true);
		DefaultBiomeFeatures.addDesertFeatures(builder);
		return builder.build();
	}

	private static SpawnSettings createSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		return builder.build();
	}
}
