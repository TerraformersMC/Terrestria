package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;

public class ChalkIslandBiomes {
	public static void register() {
		final Biome.Builder template = new Biome.Builder()
				.precipitation(Biome.Precipitation.RAIN)
				.temperature(0.5F)
				.downfall(0.5F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.foliageColor(0x71a74d)
					.grassColor(0x8eb971)
					.waterColor(0x3f76e4)
					.waterFogColor(0x050533)
					.build()
				);

		TerrestriaBiomes.CHALK_ISLAND = TerrestriaBiomes.register("chalk_island", template
				.generationSettings(chalkIslandGenerationSettings().build())
				.spawnSettings(defaultSpawnSettings().build())
				.build());
	}

	private static GenerationSettings.Builder chalkIslandGenerationSettings() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
		builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE_EXTRA_UNDERGROUND);
		builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
		DefaultBiomeFeatures.addAmethystGeodes(builder);
		DefaultBiomeFeatures.addDungeons(builder);
		DefaultBiomeFeatures.addMineables(builder);
		builder.feature(GenerationStep.Feature.FLUID_SPRINGS, MiscPlacedFeatures.SPRING_WATER);
		DefaultBiomeFeatures.addFrozenTopLayer(builder);
		// ^^ TerrestriaBiomes.addBasicFeatures() without lava lakes and springs ^^
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FOREST_FLOWERS);
		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BIRCH_TALL);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.DENSE_CYPRESS_TREES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.SUPER_BIRCH_BEES);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TreePlacedFeatures.SUPER_BIRCH_BEES_0002);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_BIRCH);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaPlacedFeatures.PATCH_CHALK_ISLAND_GRASS);
		DefaultBiomeFeatures.addDefaultFlowers(builder);
		DefaultBiomeFeatures.addDefaultMushrooms(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addSweetBerryBushes(builder);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_DEEP);
		DefaultBiomeFeatures.addSeagrassOnStone(builder);
		return builder;
	}

	private static SpawnSettings.Builder defaultSpawnSettings() {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 1, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.DONKEY, 1, 1, 2));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 6, 2, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.GOAT, 20, 1, 3));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 2, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4, 4));
		TerrestriaBiomes.addDefaultAmbientSpawnEntries(builder);
		TerrestriaBiomes.addDefaultMonsterSpawnEntries(builder);
		builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, 3, 1, 4));
		builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.COD, 15, 1, 5));

		return builder;
	}
}
