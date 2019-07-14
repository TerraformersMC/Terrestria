package net.coderbot.terrestria.biome.biomes;

import net.coderbot.terrestria.biome.TerrestriaBiome;
import net.coderbot.terrestria.init.TerrestriaFeatures;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
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
				.addTerrestriaFeature(
						GenerationStep.Feature.VEGETAL_DECORATION,
						Biome.configureFeature(
								Feature.RANDOM_SELECTOR,
								new RandomFeatureConfig(
										new Feature[]{TerrestriaFeatures.CYPRESS_TREE, TerrestriaFeatures.TALLER_BIRCH_TREE },
										new FeatureConfig[]{ FeatureConfig.DEFAULT, FeatureConfig.DEFAULT },
										new float[]{ 0.3F, 0.3F },

										TerrestriaFeatures.CYPRESS_TREE,
										FeatureConfig.DEFAULT
								),
								Decorator.COUNT_EXTRA_HEIGHTMAP,
								new CountExtraChanceDecoratorConfig(13, 0.1F, 1)
						))
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

	/*
	public CypressForestBiome(Biome.Settings settings, int trees, Feature<DefaultFeatureConfig> tree1, Feature<DefaultFeatureConfig> tree2, Feature<DefaultFeatureConfig> tree3) {
		super(settings);

		this.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL));
		this.addStructureFeature(Feature.STRONGHOLD, FeatureConfig.DEFAULT);
		this.addStructureFeature(Feature.STRONGHOLD, FeatureConfig.DEFAULT);

		DefaultBiomeFeatures.addLandCarvers(this);
		DefaultBiomeFeatures.addDefaultStructures(this);
		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDungeons(this);
		DefaultBiomeFeatures.addForestFlowers(this);
		DefaultBiomeFeatures.addMineables(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		DefaultBiomeFeatures.addDefaultDisks(this);

		this.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(
						Feature.RANDOM_SELECTOR,
						new RandomFeatureConfig(
								new Feature[]{ tree2, tree3 },
								new FeatureConfig[]{ FeatureConfig.DEFAULT, FeatureConfig.DEFAULT },
								new float[]{ 0.3F, 0.3F },

								tree1,
								FeatureConfig.DEFAULT
						),
						Decorator.COUNT_EXTRA_HEIGHTMAP,
						new CountExtraChanceDecoratorConfig(trees, 0.1F, 1)
				)
		);

		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addForestGrass(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addFrozenTopLayer(this);

		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityCategory.CREATURE, new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4));
		this.addSpawn(EntityCategory.AMBIENT, new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}

	 */
}
