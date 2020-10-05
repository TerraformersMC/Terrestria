package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class LushDesertBiomes {
	public static void register() {
		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
				.waterColor(0x3f76e4)
				.waterFogColor(0x50533)
				.temperature(0.7F)
				.downfall(0.7F)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(DefaultBiomeFeatures.STRONGHOLD)
				.addStructureFeature(DefaultBiomeFeatures.NORMAL_MINESHAFT)
				.addStructureFeature(DefaultBiomeFeatures.DESERT_RUINED_PORTAL)
				.addStructureFeature(DefaultBiomeFeatures.DESERT_VILLAGE)
				.addStructureFeature(DefaultBiomeFeatures.DESERT_PYRAMID)
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
				.addGrassFeature(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 2)
				.addGrassFeature(Blocks.DEAD_BUSH.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
				.addRareTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.YUCCA_PALM_TREE), 12)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.SAGUARO_CACTUS_FEATURE), 2)
				.build()
		);

		TerrestriaBiomes.OASIS = TerrestriaBiomes.register("oasis", template.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.THREE_LAYER_OUTLINE, TerrestriaSurfaces.OASIS_CONFIG)
				.depth(-0.2F)
				.scale(0.07F)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.JUNGLE_PALM_TREE), 2)
				.addGrassFeature(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
				.addGrassFeature(TerrestriaBlocks.ALOE_VERA.getDefaultState(), 1)
				.addGrassFeature(Blocks.TALL_GRASS.getDefaultState(), 2)
				.addGrassFeature(Blocks.FERN.getDefaultState(), 1)
				.build()
		);
	}
}
