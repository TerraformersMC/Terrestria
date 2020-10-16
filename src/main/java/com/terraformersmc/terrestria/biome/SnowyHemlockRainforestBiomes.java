package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaDecoratedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class SnowyHemlockRainforestBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.TAIGA)
				.depth(0.95F)
				.scale(0.55F)
				.temperature(-0.5F)
				.downfall(1.0F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3d57d6)
					.waterFogColor(0x50533)
					.grassColor(0x42a584)
				)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY, FROZEN_TOP_LAYER)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_GRASS_NORMAL)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN)
				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_TAIGA)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4))
		);

		TerrestriaBiomes.SNOWY_HEMLOCK_FOREST = TerrestriaBiomes.register("snowy_hemlock_forest", template.builder()
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSEST_MEGA_HEMLOCK_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSEST_HEMLOCK_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_FALLEN_HEMLOCK_LOGS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_HEMLOCK_TREES)
				.build());

		TerrestriaBiomes.SNOWY_HEMLOCK_CLEARING = TerrestriaBiomes.register("snowy_hemlock_clearing", template.builder()
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_MEGA_HEMLOCK_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.FALLEN_HEMLOCK_LOGS)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_HEMLOCK_TREES)
				.build());
	}
}
