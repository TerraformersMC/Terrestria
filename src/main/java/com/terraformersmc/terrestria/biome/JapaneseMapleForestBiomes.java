package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
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

public class JapaneseMapleForestBiomes {
	public static Biome create(BootstrapContext<Biome> registerable) {
		return new Biome.BiomeBuilder()
				.generationSettings(createGenerationSettings(registerable))
				.mobSpawnSettings(createSpawnSettings())
				.hasPrecipitation(true)
				.temperature(0.8F)
				.downfall(0.5F)
				.specialEffects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x3f76e4)
						.grassColorOverride(0x7aab1a)
						.foliageColorOverride(0x7aab1a)
						.build()
				)
				.putAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))
						.set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
						.build()
				)
				.build();
	}

	private static BiomeGenerationSettings createGenerationSettings(BootstrapContext<Biome> registerable) {
		HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = registerable.lookup(Registries.CONFIGURED_CARVER);
		HolderGetter<PlacedFeature> placedFeatures = registerable.lookup(Registries.PLACED_FEATURE);

		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		addBasicFeatures(builder, true);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSE_JAPANESE_MAPLE_TREES));
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSE_DARK_JAPANESE_MAPLE_TREES));
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.DENSE_JAPANESE_MAPLE_SHRUBS));
		BiomeDefaultFeatures.addForestGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
		return builder.build();
	}

	private static MobSpawnSettings createSpawnSettings() {
		MobSpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityTypes.WOLF, 4, 4));
		return builder.build();
	}
}
