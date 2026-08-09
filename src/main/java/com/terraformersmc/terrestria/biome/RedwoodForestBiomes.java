package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class RedwoodForestBiomes {
	public static Biome create(BootstrapContext<Biome> registerable, boolean sparse) {
		return new Biome.BiomeBuilder()
				.generationSettings(createGenerationSettings(registerable, sparse))
				.mobSpawnSettings(createSpawnSettings())
				.hasPrecipitation(true)
				.temperature(0.9F)
				.downfall(0.9F)
				.specialEffects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x3f76e4)
						.build()
				)
				.putAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA))
						.set(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
						.set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
						.build()
				)
				.build();
	}

	private static BiomeGenerationSettings createGenerationSettings(BootstrapContext<Biome> registerable, boolean sparse) {
		HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = registerable.lookup(Registries.CONFIGURED_CARVER);
		HolderGetter<PlacedFeature> placedFeatures = registerable.lookup(Registries.PLACED_FEATURE);

		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		addBasicFeatures(builder, false);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS);
		BiomeDefaultFeatures.addFerns(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		if (sparse) {
			// windswept redwood forest
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_FALLEN_REDWOOD_LOGS));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_FALLEN_HEMLOCK_LOGS));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_HEMLOCK_TREES));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_REDWOOD_TREES));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.PATCH_LUSH_FERNS));
		} else {
			// redwood forest
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSEST_MEGA_REDWOOD_TREES));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSE_FALLEN_REDWOOD_LOGS));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SPARSE_SMALL_REDWOOD_TREES));
		}
		BiomeDefaultFeatures.addTaigaGrass(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
		BiomeDefaultFeatures.addRareBerryBushes(builder);
		return builder.build();
	}

	private static MobSpawnSettings createSpawnSettings() {
		MobSpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.WOLF, 4, 4));
		builder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityTypes.RABBIT, 2, 3));
		builder.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.FOX, 2, 4));
		return builder.build();
	}
}
