package net.coderbot.terrestria.biome.biomes;

import net.coderbot.terrestria.biome.TerrestriaBiome;
import net.coderbot.terrestria.init.TerrestriaFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static net.coderbot.terrestria.biome.TerrestriaBiome.DefaultFeature.*;

public class CypressForestBiome extends Biome {

	protected CypressForestBiome(Settings biome$Settings_1) {
		super(biome$Settings_1);
	}

	public static Biome get() {
		return TerrestriaBiome.builder()
				.configuredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.depth(0.1F)
				.scale(0.2F)
				.temperature(0.7F)
				.downfall(0.8F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, FOREST_FLOWERS, MINEABLES, ORES, DISKS,
						DEFAULT_FLOWERS, DEFAULT_MUSHROOMS, FOREST_GRASS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
                .addTreeFeature(TerrestriaFeatures.CYPRESS_TREE, 9)
                .addTreeFeature(TerrestriaFeatures.TALLER_BIRCH_TREE, 4)
				.addStructureFeature(Feature.STRONGHOLD)
                .addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.COW, 8, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1))
				.build();
	}
}
