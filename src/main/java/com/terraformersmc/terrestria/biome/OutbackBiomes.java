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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class OutbackBiomes {
	public static Biome create(BootstrapContext<Biome> registerable) {
		return new Biome.BiomeBuilder()
				.generationSettings(createGenerationSettings(registerable))
				.mobSpawnSettings(createSpawnSettings())
				.hasPrecipitation(false)
				.temperature(1.8F)
				.downfall(0.3F)
				.specialEffects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x3f76e4)
						.build()
				)
				.putAttributes(TerrestriaBiomes.createDefaultEnvironmentAttributes()
						.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
						.set(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
						.set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
						.build()
				)
				.build();
	}

	private static BiomeGenerationSettings createGenerationSettings(BootstrapContext<Biome> registerable) {
		HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = registerable.lookup(Registries.CONFIGURED_CARVER);
		HolderGetter<PlacedFeature> placedFeatures = registerable.lookup(Registries.PLACED_FEATURE);

		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		BiomeDefaultFeatures.addFossilDecoration(builder);
		addBasicFeatures(builder, true);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addLushCavesSpecialOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.RARE_YUCCA_PALM_TREES));
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.PATCH_DEAD_GRASS));
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TerrestriaPlacedFeatures.ACACIA_DOT_SHRUBS));
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
		BiomeDefaultFeatures.addSavannaExtraGrass(builder);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(builder, true);
		BiomeDefaultFeatures.addDesertExtraDecoration(builder);
		return builder.build();
	}

	private static MobSpawnSettings createSpawnSettings() {
		net.minecraft.world.level.biome.MobSpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.addSpawn(MobCategory.CREATURE,  1, new MobSpawnSettings.SpawnerData(EntityType.HORSE, 2, 6));
		builder.addSpawn(MobCategory.CREATURE,  1, new MobSpawnSettings.SpawnerData(EntityType.DONKEY, 1, 1));
		builder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 2, 3));
		builder.creatureGenerationProbability(0.03F);
		return builder.build();
	}
}
