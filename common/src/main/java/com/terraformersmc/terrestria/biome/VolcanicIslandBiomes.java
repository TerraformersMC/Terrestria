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
import net.minecraft.world.gen.feature.*;

public class VolcanicIslandBiomes {
	public static Biome create(Registerable<Biome> registerable) {
		return new Biome.Builder()
				.generationSettings(createGenerationSettings(registerable))
				.spawnSettings(createSpawnSettings())
				.precipitation(true)
				.temperature(0.9F)
				.downfall(0.9F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x54d3c0)
						.build()
				)
				.addEnvironmentAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.with(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, new BackgroundMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE))
						.with(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 0x24a0b0)
						.build()
				)
				.build();
	}

	private static GenerationSettings createGenerationSettings(Registerable<Biome> registerable) {
		RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
		RegistryEntryLookup<PlacedFeature> placedFeatures = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

		GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
		DefaultBiomeFeatures.addLandCarvers(builder);
		DefaultBiomeFeatures.addAmethystGeodes(builder);
		DefaultBiomeFeatures.addDungeons(builder);
		DefaultBiomeFeatures.addMineables(builder);
		// ^^ addBasicFeatures(builder); EXCEPT addSprings and addFrozenTopLayer
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.JUNGLE_PALM_TREES));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.RARE_DUM_DUM_HEADS));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSER_JUNGLE_PALM_TREES));
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.PATCH_VOLCANIC_ISLAND_GRASS));
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder, true);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM);
		return builder.build();
	}

	private static SpawnSettings createSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.WATER_CREATURE, 3, new SpawnSettings.SpawnEntry(EntityType.SQUID, 1, 4));
		builder.spawn(SpawnGroup.WATER_AMBIENT, 15, new SpawnSettings.SpawnEntry(EntityType.SALMON, 3, 6));
		builder.spawn(SpawnGroup.WATER_AMBIENT, 15, new SpawnSettings.SpawnEntry(EntityType.COD, 1, 5));
		return builder.build();
	}
}
