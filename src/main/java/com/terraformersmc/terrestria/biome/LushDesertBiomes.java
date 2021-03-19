package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class LushDesertBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.DESERT)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
				)
				.temperature(0.7F)
				.downfall(0.7F)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_DESERT)
				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_DESERT)
				.addStructureFeature(ConfiguredStructureFeatures.DESERT_PYRAMID)
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 19, 4, 4))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 1, 1, 1))
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.HUSK, 80, 4, 4))
		);

		TerrestriaBiomes.LUSH_DESERT = TerrestriaBiomes.register("lush_desert", template.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_CONFIG)
				.depth(0.4F)
				.scale(0.05F)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.PATCH_LUSH_DESERT_VEGETATION)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.RARE_YUCCA_PALM_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SAGUARO_CACTUSES)
				.build()
		);

		TerrestriaBiomes.OASIS = TerrestriaBiomes.register("oasis", template.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.THREE_LAYER_OUTLINE, TerrestriaSurfaces.OASIS_CONFIG)
				.depth(-0.2F)
				.scale(0.07F)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.JUNGLE_PALM_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.PATCH_OASIS_VEGETATION)
				.build()
		);
	}
}
