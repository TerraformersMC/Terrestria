package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatureConfigs;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class HemlockRainforestBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
				.temperature(0.6F)
				.downfall(0.9F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY, FROZEN_TOP_LAYER)
				.addGrassFeature(Blocks.GRASS.getDefaultState(), 4)
				.addGrassFeature(Blocks.FERN.getDefaultState(), 12)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.FOX, 8, 2, 4))
		);

		TerrestriaBiomes.HEMLOCK_CLEARING = TerrestriaBiomes.register("hemlock_clearing", template.builder()
				.depth(0.95F)
				.scale(0.2F)
				.addTreeFeature(TerrestriaFeatures.HEMLOCK_TREE.configure(TerrestriaFeatureConfigs.HEMLOCK), 1)
				.addTreeFeature(Feature.NORMAL_TREE.configure(TerrestriaFeatureConfigs.TINY_HEMLOCK), 1)
				.addTreeFeature(TerrestriaFeatures.FALLEN_HEMLOCK_LOG.configure(TerrestriaFeatureConfigs.FALLEN_HEMLOCK_LOG), 2)
				.build());

		TerrestriaBiomes.HEMLOCK_RAINFOREST = TerrestriaBiomes.register("hemlock_rainforest", template.builder()
				.depth(0.95F)
				.scale(0.55F)
				.addTreeFeature(TerrestriaFeatures.MEGA_HEMLOCK_TREE.configure(TerrestriaFeatureConfigs.MEGA_HEMLOCK), 8)
				.addTreeFeature(TerrestriaFeatures.HEMLOCK_TREE.configure(TerrestriaFeatureConfigs.HEMLOCK), 8)
				.addTreeFeature(TerrestriaFeatures.FALLEN_HEMLOCK_LOG.configure(TerrestriaFeatureConfigs.FALLEN_HEMLOCK_LOG), 4)
				.addTreeFeature(Feature.NORMAL_TREE.configure(TerrestriaFeatureConfigs.TINY_HEMLOCK), 1)
				.build());
	}
}
