package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
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
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class CalderaBiomes {
	public static Biome create(Registerable<Biome> registerable) {
		return new Biome.Builder()
				.generationSettings(createGenerationSettings(registerable))
				.spawnSettings(createSpawnSettings())
				.precipitation(true)
				.temperature(0.2F)
				.downfall(0.7F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x54d3c0)
						.build()
				)
				.addEnvironmentAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.with(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, new BackgroundMusic(SoundEvents.MUSIC_OVERWORLD_SNOWY_SLOPES))
						.with(EnvironmentAttributes.INCREASED_FIRE_BURNOUT_GAMEPLAY, true)
						.with(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 0x232317)
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
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.CALDERA_HEMLOCK_TREES));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.CALDERA_REDWOOD_TREES));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.CALDERA_SMALL_HEMLOCK_TREES));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.CALDERA_SMALL_REDWOOD_TREES));
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder, true);
		return builder.build();
	}

	private static SpawnSettings createSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.CREATURE, 5, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, 1, new SpawnSettings.SpawnEntry(EntityType.GOAT, 1, 4));
		builder.spawn(SpawnGroup.WATER_CREATURE, 3, new SpawnSettings.SpawnEntry(EntityType.SQUID, 1, 4));
		builder.spawn(SpawnGroup.WATER_AMBIENT, 15, new SpawnSettings.SpawnEntry(EntityType.SALMON, 3, 6));
		builder.spawn(SpawnGroup.WATER_AMBIENT, 15, new SpawnSettings.SpawnEntry(EntityType.COD, 1, 5));
		return builder.build();
	}
}
