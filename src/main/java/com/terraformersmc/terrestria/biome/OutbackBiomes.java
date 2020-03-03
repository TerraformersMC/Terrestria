package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.*;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class OutbackBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.SAVANNA)
			.temperature(1.8F)
			.downfall(0.3F)
			.waterColor(4159204)
			.waterFogColor(329011)
			.addDefaultSpawnEntries()
			.setSpawnChance(0.03F)
			.addDefaultFeatures(LAND_CARVERS, STRUCTURES, DESERT_LAKES, DUNGEONS, MINEABLES, ORES, CLAY, SAVANNA_GRASS,
					DEFAULT_MUSHROOMS, DESERT_DEAD_BUSHES, SPRINGS, FOSSILS, FROZEN_TOP_LAYER)
		);

		TerrestriaBiomes.OUTBACK = TerrestriaBiomes.register("outback", template.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
			.depth(0.125F)
			.scale(0.05F)
			.addTreeFeature(TerrestriaFeatures.OUTBACK_SCRUB.configure(TerrestriaFeatureConfigs.OUTBACK_SCRUB), 3)
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.build());

		TerrestriaBiomes.OUTBACK_ULURU = TerrestriaBiomes.register("outback_uluru", template.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.ULURU_BUILDER, TerrestriaSurfaces.OUTBACK_ULURU_CONFIG)
			.depth(2.2F)
			.scale(0.18F)
			.build());

		TerrestriaBiomes.OUTBACK_BUSHLAND = TerrestriaBiomes.register("outback_bushland", template.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OUTBACK_CONFIG)
			.depth(0.125F)
			.scale(0.05F)
			.addTreeFeature(TerrestriaFeatures.OAK_SHRUB.configure(TerrestriaFeatureConfigs.OAK_SHRUB), 2)
			.addTreeFeature(TerrestriaFeatures.OUTBACK_SCRUB.configure(TerrestriaFeatureConfigs.OUTBACK_SCRUB), 3)
			.addTreeFeature(Feature.FANCY_TREE.configure(DefaultBiomeFeatures.FANCY_TREE_CONFIG), 1)
			.addTreeFeature(Feature.ACACIA_TREE.configure(DefaultBiomeFeatures.ACACIA_TREE_CONFIG), 2)
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
			.addGrassFeature(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
			.build());
	}
}
