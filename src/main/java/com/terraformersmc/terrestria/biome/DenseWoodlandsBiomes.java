package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaConfiguredFeatures;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class DenseWoodlandsBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				.depth(0.125F)
				.scale(0.05F)
				.temperature(0.9F)
				.downfall(0.3F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x3f76e4)
					.waterFogColor(0x50533)
				)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, PLAINS_TALL_GRASS, MINEABLES, ORES, DISKS,
						PLAINS_FEATURES, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addDefaultSpawnEntries()
		);

		TerrestriaBiomes.DENSE_WOODLANDS = TerrestriaBiomes.register("dense_woodlands", template.builder()
				.addTreeFeature(ConfiguredFeatures.FANCY_OAK, 7)
				.addTreeFeature(TerrestriaConfiguredFeatures.OAK_SHRUB, 1)
				.build()
		);

		TerrestriaBiomes.DENSE_WOODLANDS_EDGE = TerrestriaBiomes.register("dense_woodlands_edge", template.builder()
				.addTreeFeature(ConfiguredFeatures.FANCY_OAK, 3)
				.addTreeFeature(TerrestriaConfiguredFeatures.OAK_SHRUB, 1)
				.build()
		);
	}
}
