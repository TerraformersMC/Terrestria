package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class SnowyHemlockRainforestBiomes {
	public static Biome create(Registerable<Biome> registerable, boolean sparse) {
		return new Biome.Builder()
				.generationSettings(createGenerationSettings(registerable, sparse))
				.spawnSettings(createSpawnSettings())
				.precipitation(true)
				.temperature(-0.5F)
				.downfall(1.0F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
						.music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST))
						.waterColor(0x3d57d6)
						.waterFogColor(0x50533)
						.grassColor(0x42a584)
						.build()
				)
				.build();
	}

	private static GenerationSettings createGenerationSettings(Registerable<Biome> registerable, boolean sparse) {
		RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
		RegistryEntryLookup<PlacedFeature> placedFeatures = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

		GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		if (sparse) {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.FALLEN_HEMLOCK_LOGS));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_MEGA_HEMLOCK_TREES));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES));
		} else {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSEST_MEGA_HEMLOCK_TREES));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSEST_HEMLOCK_TREES));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSE_FALLEN_HEMLOCK_LOGS));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES));
		}
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL);
		DefaultBiomeFeatures.addTaigaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder, true);
		DefaultBiomeFeatures.addSweetBerryBushesSnowy(builder);
		return builder.build();
	}

	private static SpawnSettings createSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.CREATURE, 8, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, 4, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 3));
		builder.spawn(SpawnGroup.CREATURE, 8, new SpawnSettings.SpawnEntry(EntityType.FOX, 2, 4));
		return builder.build();
	}
}
