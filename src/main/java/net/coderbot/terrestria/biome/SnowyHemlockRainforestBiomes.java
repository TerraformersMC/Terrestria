package net.coderbot.terrestria.biome;

import net.coderbot.terrestria.biome.builder.TerrestriaBiome;
import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.coderbot.terrestria.init.TerrestriaFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static net.coderbot.terrestria.biome.builder.TerrestriaBiome.DefaultFeature.*;

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
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY, FROZEN_TOP_LAYER)
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
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY, FROZEN_TOP_LAYER)
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
