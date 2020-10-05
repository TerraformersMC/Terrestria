package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class OutbackBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
				.temperature(1.8F)
				.downfall(0.3F)
				.waterColor(0x3f76e4)
				.waterFogColor(0x50533)
				.addDefaultSpawnEntries()
				.setSpawnChance(0.03F)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_DESERT)
				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_DESERT)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY, SAVANNA_GRASS,
					DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
		);

		TerrestriaBiomes.OUTBACK = TerrestriaBiomes.register("outback", template.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.PATCHY_GRASS, TerrestriaSurfaces.OUTBACK_CONFIG)
			.depth(0.125F)
			.scale(0.05F)
			.addRareTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.YUCCA_PALM_TREE), 12)
			.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.ACACIA_DOT_SHRUB), 3)
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.build());

		TerrestriaBiomes.OUTBACK_ULURU = TerrestriaBiomes.register("outback_uluru", template.builder()
			.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, TerrestriaSurfaces.OUTBACK_ULURU_CONFIG)
			.depth(2.2F)
			.scale(0.18F)
			.build());

		TerrestriaBiomes.OUTBACK_BUSHLAND = TerrestriaBiomes.register("outback_bushland", template.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.PATCHY_GRASS, TerrestriaSurfaces.OUTBACK_CONFIG)
			.depth(0.125F)
			.scale(0.05F)
			.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.YUCCA_PALM_TREE), 1)
			.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.OAK_DOT_SHRUB), 2)
			.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.ACACIA_DOT_SHRUB), 3)
			.addRareTreeFeature(ConfiguredFeatures.FANCY_OAK, 24)
			.addTreeFeature(ConfiguredFeatures.ACACIA, 2)
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.addGrassFeature(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
			.build());
	}
}
