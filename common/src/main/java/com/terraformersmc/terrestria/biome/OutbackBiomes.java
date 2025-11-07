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
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class OutbackBiomes {
	public static Biome create(Registerable<Biome> registerable) {
		return new Biome.Builder()
				.generationSettings(createGenerationSettings(registerable))
				.spawnSettings(createSpawnSettings())
				.precipitation(false)
				.temperature(1.8F)
				.downfall(0.3F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x3f76e4)
						.build()
				)
				.addEnvironmentAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.with(EnvironmentAttributes.BACKGROUND_MUSIC_AUDIO, new BackgroundMusic(SoundEvents.MUSIC_OVERWORLD_BADLANDS))
						.with(EnvironmentAttributes.SNOW_GOLEM_MELTS_GAMEPLAY, true)
						.with(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, 0x50533)
						.build()
				)
				.build();
	}

	private static GenerationSettings createGenerationSettings(Registerable<Biome> registerable) {
		RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
		RegistryEntryLookup<PlacedFeature> placedFeatures = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

		GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
		DefaultBiomeFeatures.addFossils(builder);
		addBasicFeatures(builder, true);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addClayOre(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.RARE_YUCCA_PALM_TREES));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.PATCH_DEAD_GRASS));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.ACACIA_DOT_SHRUBS));
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH_2);
		DefaultBiomeFeatures.addSavannaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder, true);
		DefaultBiomeFeatures.addDesertFeatures(builder);
		return builder.build();
	}

	private static SpawnSettings createSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.CREATURE,  1, new SpawnSettings.SpawnEntry(EntityType.HORSE, 2, 6));
		builder.spawn(SpawnGroup.CREATURE,  1, new SpawnSettings.SpawnEntry(EntityType.DONKEY, 1, 1));
		builder.spawn(SpawnGroup.CREATURE, 10, new SpawnSettings.SpawnEntry(EntityType.ARMADILLO, 2, 3));
		builder.creatureSpawnProbability(0.03F);
		return builder.build();
	}
}
