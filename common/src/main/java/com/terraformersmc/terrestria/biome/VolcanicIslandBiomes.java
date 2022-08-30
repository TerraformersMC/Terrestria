package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

public class VolcanicIslandBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN)
				.temperature(0.9F)
				.downfall(0.9F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x54d3c0)
					.waterFogColor(0x24a0b0)
					.build()
				);

		TerrestriaBiomes.VOLCANIC_ISLAND = TerrestriaBiomes.register("volcanic_island", template
				.generationSettings(volcanicIslandGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build());
	}

	private static GenerationSettings.Builder volcanicIslandGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		DefaultBiomeFeatures.addLandCarvers(builder);
		DefaultBiomeFeatures.addAmethystGeodes(builder);
		DefaultBiomeFeatures.addDungeons(builder);
		DefaultBiomeFeatures.addMineables(builder);
		// ^^ addBasicFeatures(builder); EXCEPT addSprings and addFrozenTopLayer
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.JUNGLE_PALM_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.RARE_DUM_DUM_HEADS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSER_JUNGLE_PALM_TREES);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.PATCH_VOLCANIC_ISLAND_GRASS);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM);
		DefaultBiomeFeatures.addSeagrassOnStone(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 3, 1, 4));
		builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.SALMON, 15, 3, 6));
		builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.COD, 15, 1, 5));
		return builder;
	}
}
