package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatureConfigs;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class RedwoodForestBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.GIANT_TREE_TAIGA, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				.temperature(0.9F)
				.downfall(0.9F)
				.waterColor(0x3f76e4)
				.waterFogColor(0x50533)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY,
						FROZEN_TOP_LAYER)
				.addStructureFeature(DefaultBiomeFeatures.STRONGHOLD)
				.addStructureFeature(DefaultBiomeFeatures.NORMAL_MINESHAFT)
				.addStructureFeature(DefaultBiomeFeatures.MOUNTAIN_RUINED_PORTAL)
				.addStructureFeature(DefaultBiomeFeatures.PLAINS_VILLAGE)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.FOX, 8, 2, 4))
		);

		TerrestriaBiomes.REDWOOD_FOREST = TerrestriaBiomes.register("redwood_forest", template.builder()
				.depth(1.2F)
				.scale(0.3F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.MEGA_REDWOOD_TREE), 7)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.FALLEN_REDWOOD_LOG), 3)
				.addRareTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.SMALL_REDWOOD_TREE), 1)
				.addGrassFeature(Blocks.FERN.getDefaultState(), 4)
				.addDoubleGrassFeature(Blocks.LARGE_FERN.getDefaultState(), 7)
				.addDoubleGrassFeature(Blocks.TALL_GRASS.getDefaultState(), 7)
				.build());

		TerrestriaBiomes.REDWOOD_FOREST_EDGE = TerrestriaBiomes.register("redwood_forest_edge", template.builder()
				.depth(0.5F)
				.scale(0.3F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.REDWOOD_TREE), 3)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.SMALL_REDWOOD_TREE), 2)
				.addDoubleGrassFeature(Blocks.LARGE_FERN.getDefaultState(), 4)
				.addDoubleGrassFeature(Blocks.TALL_GRASS.getDefaultState(), 4)
				.build());

		TerrestriaBiomes.REDWOOD_CLEARING = TerrestriaBiomes.register("redwood_clearing", template.builder()
				.depth(1.2F)
				.scale(0.3F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.FALLEN_REDWOOD_LOG), 2)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.SMALL_REDWOOD_TREE), 2)
				.addDoubleGrassFeature(Blocks.LARGE_FERN.getDefaultState(), 4)
				.addDoubleGrassFeature(Blocks.TALL_GRASS.getDefaultState(), 4)
				.build());
	}
}
