package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class LushDesertBiomes {
	public static Biome create(BootstrapContext<Biome> registerable, boolean oasis) {
		return new Biome.BiomeBuilder()
				.generationSettings(createGenerationSettings(registerable, oasis))
				.mobSpawnSettings(createSpawnSettings())
				.hasPrecipitation(true)
				.temperature(0.7F)
				.downfall(0.7F)
				.specialEffects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x3f76e4)
						.build()
				)
				.putAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
						.set(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
						.set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
						.build()
				)
				.build();
	}

	private static BiomeGenerationSettings createGenerationSettings(BootstrapContext<Biome> registerable, boolean oasis) {
		HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = registerable.lookup(Registries.CONFIGURED_CARVER);
		HolderGetter<PlacedFeature> placedFeatures = registerable.lookup(Registries.PLACED_FEATURE);

		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		addBasicFeatures(builder, true);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		if (oasis) {
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.JUNGLE_PALM_TREES));
		} else {
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.RARE_YUCCA_PALM_TREES));
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.SAGUARO_CACTUSES));
		}
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addDefaultGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		if (oasis) {
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.PATCH_OASIS_VEGETATION));
		} else {
			builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.PATCH_LUSH_DESERT_VEGETATION));
		}
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
		return builder.build();
	}

	private static MobSpawnSettings createSpawnSettings() {
		net.minecraft.world.level.biome.MobSpawnSettings.Builder builder = new net.minecraft.world.level.biome.MobSpawnSettings.Builder();
		TerrestriaBiomes.addDefaultCaveSpawnEntries(builder);
		builder.addSpawn(MobCategory.CREATURE, 4, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 4, 4));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 4, 4));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 4, 4));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 4, 4));
		builder.addSpawn(MobCategory.MONSTER,  10, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4));
		builder.addSpawn(MobCategory.MONSTER,   5, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1));
		builder.addSpawn(MobCategory.MONSTER,  19, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 4, 4));
		builder.addSpawn(MobCategory.MONSTER,   1, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 1, 1));
		builder.addSpawn(MobCategory.MONSTER,  80, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 4, 4));
		return builder.build();
	}
}
