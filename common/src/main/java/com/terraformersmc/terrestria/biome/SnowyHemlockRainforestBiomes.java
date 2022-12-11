package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class SnowyHemlockRainforestBiomes {
	public static Biome create(FabricDynamicRegistryProvider.Entries entries, boolean sparse) {
		return new Biome.Builder()
				.generationSettings(createGenerationSettings(entries, sparse))
				.spawnSettings(createSpawnSettings())
				.precipitation(Biome.Precipitation.SNOW)
				.temperature(-0.5F)
				.downfall(1.0F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
						.waterColor(0x3d57d6)
						.waterFogColor(0x50533)
						.grassColor(0x42a584)
						.build()
				)
				.build();
	}

	private static GenerationSettings createGenerationSettings(FabricDynamicRegistryProvider.Entries entries, boolean sparse) {
		GenerationSettings.LookupBackedBuilder builder = new GenerationSettings.LookupBackedBuilder(entries.placedFeatures(), entries.configuredCarvers());
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		if (sparse) {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.FALLEN_HEMLOCK_LOGS));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.SPARSE_MEGA_HEMLOCK_TREES));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES));
		} else {
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.DENSEST_MEGA_HEMLOCK_TREES));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.DENSEST_HEMLOCK_TREES));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.DENSE_FALLEN_HEMLOCK_LOGS));
			builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(TerrestriaPlacedFeatures.SPARSE_SMALL_HEMLOCK_TREES));
		}
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL);
		DefaultBiomeFeatures.addTaigaGrass(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSweetBerryBushesSnowy(builder);
		return builder.build();
	}

	private static SpawnSettings createSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4));
		return builder.build();
	}
}
