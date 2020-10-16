package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaDecoratedFeatures;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class CypressSwampBiomes {
	public static void register() {
		TerrestriaBiomes.CYPRESS_SWAMP = TerrestriaBiomes.register("cypress_swamp", TerraformBiomeBuilder.create()
				.configureSurfaceBuilder(SurfaceBuilder.SWAMP, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.SWAMP)
				.depth(-0.25F)
				.scale(0.05F)
				.temperature(0.7F)
				.downfall(0.7F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x2c7f32)
					.waterFogColor(0x053305)
					.grassColor(0x699e3c)
					.foliageColor(0x619137)
				)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, CLAY, DEFAULT_GRASS,
						DEFAULT_MUSHROOMS, SPRINGS, FOSSILS, FROZEN_TOP_LAYER, SWAMP_VEGETATION,
						DESERT_VEGETATION)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.MEGA_CYPRESS_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_RUBBER_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_WILLOW_TREES)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.CATTAILS_WARM)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SEAGRASS_WARM)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_WATERLILLY)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_GRASS_FOREST)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.BROWN_MUSHROOM_SWAMP)
				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_TALL_GRASS)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.COD, 8, 2, 4))
				.build());
	}
}
