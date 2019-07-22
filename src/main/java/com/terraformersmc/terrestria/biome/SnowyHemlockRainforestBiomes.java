package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terrestria.biome.builder.TerrestriaBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class SnowyHemlockRainforestBiomes {
	public static void register() {
		TerrestriaBiomes.SNOWY_HEMLOCK_FOREST = TerrestriaBiomes.register("snowy_hemlock_forest", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.TAIGA)
				.depth(0.95F)
				.scale(0.55F)
				.temperature(-0.5F)
				.downfall(1.0F)
				.waterColor(4020182)
				.waterFogColor(329011)
				.addDefaultFeatures(TerrestriaBiome.DefaultFeature.LAND_CARVERS, TerrestriaBiome.DefaultFeature.STRUCTURES, TerrestriaBiome.DefaultFeature.LAKES, TerrestriaBiome.DefaultFeature.DUNGEONS, TerrestriaBiome.DefaultFeature.LARGE_FERNS, TerrestriaBiome.DefaultFeature.MINEABLES, TerrestriaBiome.DefaultFeature.ORES, TerrestriaBiome.DefaultFeature.DISKS,
						TerrestriaBiome.DefaultFeature.TAIGA_GRASS, TerrestriaBiome.DefaultFeature.DEFAULT_MUSHROOMS, TerrestriaBiome.DefaultFeature.DEFAULT_VEGETATION, TerrestriaBiome.DefaultFeature.SPRINGS, TerrestriaBiome.DefaultFeature.SWEET_BERRY_BUSHES_SNOWY, TerrestriaBiome.DefaultFeature.FROZEN_TOP_LAYER)
				.addTreeFeature(TerrestriaFeatures.MEGA_HEMLOCK_TREE, 8)
				.addTreeFeature(TerrestriaFeatures.HEMLOCK_TREE, 8)
				.addTreeFeature(TerrestriaFeatures.FALLEN_HEMLOCK_LOG, 4)
				.addTreeFeature(TerrestriaFeatures.TINY_HEMLOCK_TREE, 1)
				.addGrassFeature(Blocks.GRASS.getDefaultState(), 4)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.FOX, 8, 2, 4))
				.build());

		TerrestriaBiomes.SNOWY_HEMLOCK_CLEARING = TerrestriaBiomes.register("snowy_hemlock_clearing", TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.TAIGA)
				.depth(0.95F)
				.scale(0.55F)
				.temperature(-0.5F)
				.downfall(1.0F)
				.waterColor(4020182)
				.waterFogColor(329011)
				.addDefaultFeatures(TerrestriaBiome.DefaultFeature.LAND_CARVERS, TerrestriaBiome.DefaultFeature.STRUCTURES, TerrestriaBiome.DefaultFeature.LAKES, TerrestriaBiome.DefaultFeature.DUNGEONS, TerrestriaBiome.DefaultFeature.LARGE_FERNS, TerrestriaBiome.DefaultFeature.MINEABLES, TerrestriaBiome.DefaultFeature.ORES, TerrestriaBiome.DefaultFeature.DISKS,
						TerrestriaBiome.DefaultFeature.TAIGA_GRASS, TerrestriaBiome.DefaultFeature.DEFAULT_MUSHROOMS, TerrestriaBiome.DefaultFeature.DEFAULT_VEGETATION, TerrestriaBiome.DefaultFeature.SPRINGS, TerrestriaBiome.DefaultFeature.SWEET_BERRY_BUSHES_SNOWY, TerrestriaBiome.DefaultFeature.FROZEN_TOP_LAYER)
				.addTreeFeature(TerrestriaFeatures.HEMLOCK_TREE, 1)
				.addTreeFeature(TerrestriaFeatures.TINY_HEMLOCK_TREE, 1)
				.addTreeFeature(TerrestriaFeatures.FALLEN_HEMLOCK_LOG, 2)
				.addGrassFeature(Blocks.GRASS.getDefaultState(), 4)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 8, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.FOX, 8, 2, 4))
				.build());
	}
}
