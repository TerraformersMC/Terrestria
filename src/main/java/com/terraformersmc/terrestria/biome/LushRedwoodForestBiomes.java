package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaDecoratedFeatures;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class LushRedwoodForestBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				.depth(1.2F)
				.scale(0.3F)
				.temperature(0.9F)
				.downfall(0.9F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
				)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY,
						FROZEN_TOP_LAYER, DEFAULT_FLOWERS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.PATCH_LUSH_FERNS)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN)
				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4))
		);

		TerrestriaBiomes.LUSH_REDWOOD_CLEARING = TerrestriaBiomes.register("lush_redwood_clearing", template.builder()
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_FALLEN_REDWOOD_LOGS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_FALLEN_HEMLOCK_LOGS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_REDWOOD_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_HEMLOCK_TREES)
				.build());

		TerrestriaBiomes.LUSH_REDWOOD_FOREST = TerrestriaBiomes.register("lush_redwood_forest", template.builder()
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.FOUR_HEMLOCK_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.MEGA_REDWOOD_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.FALLEN_REDWOOD_LOGS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.FALLEN_HEMLOCK_LOGS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_REDWOOD_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_HEMLOCK_TREES)
				.build());

		TerrestriaBiomes.LUSH_REDWOOD_FOREST_EDGE = TerrestriaBiomes.register("lush_redwood_forest_edge", template.builder()
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_HEMLOCK_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.REDWOOD_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_REDWOOD_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_HEMLOCK_TREES)
				.build());
	}
}
