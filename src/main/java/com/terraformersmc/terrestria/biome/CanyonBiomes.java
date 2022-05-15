package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.addBasicFeatures;

public class CanyonBiomes {

	public static void register() {
		final Biome.Builder template = new Biome.Builder()
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.DESERT)
			//.depth(0.2F)
			//.scale(0.1F)
			.temperature(0.9F)
			.downfall(0.1F)
			.effects(TerrestriaBiomes.createDefaultBiomeEffects()
				.waterColor(0x4da5e3)
				.waterFogColor(0x24a0b0)
				.foliageColor(0xbdea62)
				.build()
			);

		TerrestriaBiomes.CANYON_ARCHES = TerrestriaBiomes.register("canyon_arches", template
			.generationSettings(canyonGenerationSettings().build())
			.spawnSettings(defaultSpawnSettings().build())
			//.configureSurfaceBuilder(TerrestriaSurfaces.SANDSTONE_CLIFF, TerrestriaSurfaces.SANDSTONE_CONFIG)
			//.addStructureFeature(TerrestriaStructures.CANYON_ARCH)
			.build());

		TerrestriaBiomes.CANYON_CLIFFS = TerrestriaBiomes.register("canyon_cliffs", template
			.generationSettings(canyonCliffsGenerationSettings().build())
			.spawnSettings(defaultSpawnSettings().build())
			//.configureSurfaceBuilder(TerrestriaSurfaces.CANYON_CLIFF, TerrestriaSurfaces.SANDSTONE_CLIFF_CONFIG)
			.build());

		TerrestriaBiomes.CANYON_EDGE = TerrestriaBiomes.register("canyon_edge", template
			.generationSettings(canyonGenerationSettings().build())
			.spawnSettings(defaultSpawnSettings().build())
			//.configureSurfaceBuilder(TerrestriaSurfaces.SANDSTONE_CLIFF, TerrestriaSurfaces.SANDSTONE_CONFIG)
			.build());
	}

	private static GenerationSettings.Builder canyonGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDesertDeadBushes(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		//DefaultBiomeFeatures.addDesertLakes(builder);  // (vv addDesertFeatures instead?)
		DefaultBiomeFeatures.addDesertFeatures(builder);
		return builder;
	}

	private static GenerationSettings.Builder canyonCliffsGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.RARE_BRYCE_TREES);
		DefaultBiomeFeatures.addDefaultGrass(builder);
		DefaultBiomeFeatures.addDesertDeadBushes(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		//DefaultBiomeFeatures.addDesertLakes(builder);  // (vv addDesertFeatures instead?)
		DefaultBiomeFeatures.addDesertFeatures(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = TerrestriaBiomes.createDefaultSpawnSettings();
		return builder;
	}
}
