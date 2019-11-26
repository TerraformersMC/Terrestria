package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class LushDesertBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
			.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
			.waterColor(4159204)
			.waterFogColor(329011)
			.temperature(0.7F)
			.downfall(0.7F)
			.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
				DEFAULT_GRASS, SPRINGS, FROZEN_TOP_LAYER)
			.addStructureFeature(Feature.STRONGHOLD)
			.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
			.addDefaultSpawnEntries()
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.SQUID, 3, 1, 4))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.SALMON, 15, 3, 6))
			.addSpawnEntry(new Biome.SpawnEntry(EntityType.COD, 15, 1, 5))
		);

		TerrestriaBiomes.LUSH_DESERT = TerrestriaBiomes.register("lush_desert", template.builder()
			.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.SAND_CONFIG)
			.depth(0.4F)
			.scale(0.05F)
			.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 2)
			.addGrassFeature(Blocks.DEAD_BUSH.getDefaultState(), 1)
			.addGrassFeature(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
			.addTreeFeature(TerrestriaFeatures.SAGUARO_CACTUS.sapling(), 2)
			.build()
		);

		TerrestriaBiomes.OASIS = TerrestriaBiomes.register("oasis", template.builder()
			.configureSurfaceBuilder(TerrestriaSurfaces.RANDOM_BUILDER, TerrestriaSurfaces.OASIS_CONFIG)
			.depth(-0.2F)
			.scale(0.07F)
			.addTreeFeature(TerrestriaFeatures.JUNGLE_PALM_TREE, 2)
			.addGrassFeature(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
			.addGrassFeature(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
			.addGrassFeature(TerrestriaBlocks.ALOE_VERA.getDefaultState(), 1)
			.addGrassFeature(Blocks.TALL_GRASS.getDefaultState(), 2)
			.addGrassFeature(Blocks.FERN.getDefaultState(), 1)
			.build()
		);
	}
}
