package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatureConfigs;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class SnowyHemlockRainforestBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.TAIGA)
				.depth(0.95F)
				.scale(0.55F)
				.temperature(-0.5F)
				.downfall(1.0F)
				.waterColor(0x3d57d6)
				.waterFogColor(0x50533)
				.grassColor(0x44bf3b)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY, FROZEN_TOP_LAYER)
				.addGrassFeature(Blocks.GRASS.getDefaultState(), 4)
				.addStructureFeature(DefaultBiomeFeatures.STRONGHOLD)
				.addStructureFeature(DefaultBiomeFeatures.NORMAL_MINESHAFT)
				.addStructureFeature(DefaultBiomeFeatures.MOUNTAIN_RUINED_PORTAL)
				.addStructureFeature(DefaultBiomeFeatures.TAIGA_VILLAGE)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.FOX, 8, 2, 4))
		);

		TerrestriaBiomes.SNOWY_HEMLOCK_FOREST = TerrestriaBiomes.register("snowy_hemlock_forest", template.builder()
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.MEGA_HEMLOCK_TREE), 8)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.HEMLOCK_TREE), 8)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.FALLEN_HEMLOCK_LOG), 4)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.SMALL_HEMLOCK_TREE), 1)
				.build());

		TerrestriaBiomes.SNOWY_HEMLOCK_CLEARING = TerrestriaBiomes.register("snowy_hemlock_clearing", template.builder()
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.HEMLOCK_TREE), 1)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.FALLEN_HEMLOCK_LOG), 2)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.SMALL_HEMLOCK_TREE), 1)
				.build());
	}
}
