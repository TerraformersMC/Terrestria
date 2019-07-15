package net.coderbot.terrestria.biome;

import net.coderbot.terrestria.feature.TerrestriaFeature;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TerrestriaBiome extends Biome {

	private static Biome biome;

	private TerrestriaBiome(Biome.Settings biomeSettings, ArrayList<SpawnEntry> spawns) {
		super(biomeSettings);
		for (SpawnEntry entry : spawns) {
			this.addSpawn(entry.type.getCategory(), entry);
		}
	}

	public static TerrestriaBiome.Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Biome.Settings biomeSettings;
		private ArrayList<DefaultFeature> defaultFeatures = new ArrayList<>();
		private ArrayList<TerrestriaFeature> features = new ArrayList<>();
		private Map<StructureFeature, FeatureConfig> structureFeatures = new HashMap<>();
		private Map<Feature<DefaultFeatureConfig>, Integer> treeFeatures = new HashMap<>();
		private Map<BlockState, Integer> plantFeatures = new HashMap<>();
		private ConfiguredSurfaceBuilder surfaceBuilder;
		private Biome.Precipitation precipitation;
		private Biome.Category category;
		private float depth;
		private float scale;
		private float temperature;
		private float downfall;
		private int waterColor;
		private int waterFogColor;
		private ArrayList<Biome.SpawnEntry> spawnEntries = new ArrayList<>();

		public Biome build() {
			// Create the biome settings from the builder options
			biomeSettings = new Biome.Settings().parent(null).category(category).depth(depth).scale(scale)
					.precipitation(precipitation).temperature(temperature).downfall(downfall).waterColor(waterColor)
					.waterFogColor(waterFogColor)
					.configureSurfaceBuilder(this.surfaceBuilder.surfaceBuilder, this.surfaceBuilder.config); // more

			// Add SpawnEntries
			TerrestriaBiome.biome = new TerrestriaBiome(this.biomeSettings, this.spawnEntries);

			// Add structures
			for (Map.Entry<StructureFeature, FeatureConfig> structure : structureFeatures.entrySet()) {
				TerrestriaBiome.biome.addStructureFeature(structure.getKey(), structure.getValue());
			}

			// Tree Feature stuff
			if (treeFeatures.size() > 0) {

				// Determine the total tree count

				int totalTreesPerChunk = 0;
				for(Integer count: treeFeatures.values()) {
					totalTreesPerChunk += count;
				}

				// Add each tree

				for (Map.Entry<Feature<DefaultFeatureConfig>, Integer> tree : treeFeatures.entrySet()) {
					Feature<DefaultFeatureConfig> feature = tree.getKey();
					int count = tree.getValue();

					float weight = (float)count / totalTreesPerChunk;

					TerrestriaBiome.biome.addFeature(
							GenerationStep.Feature.VEGETAL_DECORATION,
							Biome.configureFeature(
									feature,
									FeatureConfig.DEFAULT,
									Decorator.COUNT_EXTRA_HEIGHTMAP,
									new CountExtraChanceDecoratorConfig(count, 0.1F * weight, 1)
							)
					);
				}
			}

			// Add any minecraft (default) features
			for (DefaultFeature defaultFeature : defaultFeatures) {
				buildDefaultFeature(defaultFeature);
			}

			// Add custom features that don't fit in the templates
			for (TerrestriaFeature feature : features) {
				TerrestriaBiome.biome.addFeature(feature.getStep(), feature.getFeature());
			}

			// Add Plant decoration features
			for (Map.Entry<BlockState, Integer> plant : plantFeatures.entrySet()) {
				TerrestriaBiome.biome.addFeature(
						GenerationStep.Feature.VEGETAL_DECORATION,
						Biome.configureFeature(Feature.GRASS, new GrassFeatureConfig(plant.getKey()), Decorator.COUNT_HEIGHTMAP_DOUBLE, new CountDecoratorConfig(plant.getValue())));
			}


			return TerrestriaBiome.biome;
		}

		public TerrestriaBiome.Builder settings(Biome.Settings settings) {
			this.biomeSettings = settings;
			return this;
		}

		public <SC extends SurfaceConfig> TerrestriaBiome.Builder configuredSurfaceBuilder(SurfaceBuilder<SC> surfaceBuilder, SC surfaceConfig) {
			this.surfaceBuilder = new ConfiguredSurfaceBuilder<>(surfaceBuilder, surfaceConfig);
			return this;
		}

		public TerrestriaBiome.Builder addTreeFeature(Feature<DefaultFeatureConfig> feature, int numPerChunk) {
			this.treeFeatures.put(feature, numPerChunk);
			return this;
		}

		public TerrestriaBiome.Builder addGrassFeature(BlockState blockState, int count) {
			this.plantFeatures.put(blockState, count);
			return this;
		}

		public TerrestriaBiome.Builder addCustomFeature(GenerationStep.Feature step, ConfiguredFeature feature) {
			this.features.add(new TerrestriaFeature(step, feature));
			return this;
		}

		public TerrestriaBiome.Builder precipitation(Biome.Precipitation precipitation) {
			this.precipitation = precipitation;
			return this;
		}

		public TerrestriaBiome.Builder category(Biome.Category category) {
			this.category = category;
			return this;
		}

		public TerrestriaBiome.Builder depth(float depth) {
			this.depth = depth;
			return this;
		}

		public TerrestriaBiome.Builder scale(float scale) {
			this.scale = scale;
			return this;
		}

		public TerrestriaBiome.Builder temperature(float temperature) {
			this.temperature = temperature;
			return this;
		}

		public TerrestriaBiome.Builder downfall(float downfall) {
			this.downfall = downfall;
			return this;
		}

		public TerrestriaBiome.Builder waterColor(int waterColor) {
			this.waterColor = waterColor;
			return this;
		}

		public TerrestriaBiome.Builder waterFogColor(int waterFogColor) {
			this.waterFogColor = waterFogColor;
			return this;
		}

		public TerrestriaBiome.Builder addSpawnEntry(SpawnEntry entry) {
			this.spawnEntries.add(entry);
			return this;
		}

		public TerrestriaBiome.Builder addStructureFeature(StructureFeature feature) {
			this.addStructureFeature(feature, FeatureConfig.DEFAULT);
			return this;
		}

		public TerrestriaBiome.Builder addStructureFeature(StructureFeature feature, FeatureConfig config) {
			this.structureFeatures.put(feature, config);
			return this;
		}

		public TerrestriaBiome.Builder addStructureFeatures(StructureFeature... defaultStructureFeatures) {
			for (StructureFeature feature : defaultStructureFeatures) {
				this.structureFeatures.put(feature, FeatureConfig.DEFAULT);
			}
			return this;
		}

		public TerrestriaBiome.Builder addDefaultFeature(DefaultFeature feature) {
			defaultFeatures.add(feature);
			return this;
		}

		public TerrestriaBiome.Builder addDefaultFeatures(DefaultFeature... features) {
			defaultFeatures.addAll(Arrays.asList(features));
			return this;
		}

		public TerrestriaBiome.Builder addDefaultSpawnEntries() {
			this.addSpawnEntry(new Biome.SpawnEntry(EntityType.SHEEP, 12, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.PIG, 10, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.CHICKEN, 10, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.COW, 8, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.BAT, 10, 8, 8))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.SPIDER, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.SKELETON, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.CREEPER, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.SLIME, 100, 4, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4))
					.addSpawnEntry(new Biome.SpawnEntry(EntityType.WITCH, 5, 1, 1));
			return this;
		}

		void buildDefaultFeature(DefaultFeature feature) {
			switch (feature) {
				case LAND_CARVERS:
					DefaultBiomeFeatures.addLandCarvers(TerrestriaBiome.biome);
					break;
				case OCEAN_CARVERS:
					DefaultBiomeFeatures.addOceanCarvers(TerrestriaBiome.biome);
					break;
				case STRUCTURES:
					DefaultBiomeFeatures.addDefaultStructures(TerrestriaBiome.biome);
					break;
				case LAKES:
					DefaultBiomeFeatures.addDefaultLakes(TerrestriaBiome.biome);
					break;
				case DESERT_LAKES:
					DefaultBiomeFeatures.addDesertLakes(TerrestriaBiome.biome);
					break;
				case DUNGEONS:
					DefaultBiomeFeatures.addDungeons(TerrestriaBiome.biome);
					break;
				case MINEABLES:
					DefaultBiomeFeatures.addMineables(TerrestriaBiome.biome);
					break;
				case ORES:
					DefaultBiomeFeatures.addDefaultOres(TerrestriaBiome.biome);
					break;
				case EXTRA_GOLD:
					DefaultBiomeFeatures.addExtraGoldOre(TerrestriaBiome.biome);
					break;
				case EMERALD_ORE:
					DefaultBiomeFeatures.addEmeraldOre(TerrestriaBiome.biome);
					break;
				case INFECTED_STONE:
					DefaultBiomeFeatures.addInfestedStone(TerrestriaBiome.biome);
					break;
				case DISKS:
					DefaultBiomeFeatures.addDefaultDisks(TerrestriaBiome.biome);
					break;
				case CLAY:
					DefaultBiomeFeatures.addClay(TerrestriaBiome.biome);
					break;
				case MOSSY_ROCKS:
					DefaultBiomeFeatures.addMossyRocks(TerrestriaBiome.biome);
					break;
				case LARGE_FERNS:
					DefaultBiomeFeatures.addLargeFerns(TerrestriaBiome.biome);
					break;
				case SWEET_BERRY_BUSHES:
					DefaultBiomeFeatures.addSweetBerryBushes(TerrestriaBiome.biome);
					break;
				case SWEET_BERRY_BUSHES_SNOWY:
					DefaultBiomeFeatures.addSweetBerryBushesSnowy(TerrestriaBiome.biome);
					break;
				case BAMBOO:
					DefaultBiomeFeatures.addBamboo(TerrestriaBiome.biome);
					break;
				case BAMBOO_JUNGLE_TREES:
					DefaultBiomeFeatures.addBambooJungleTrees(TerrestriaBiome.biome);
					break;
				case TAIGA_TREES:
					DefaultBiomeFeatures.addTaigaTrees(TerrestriaBiome.biome);
					break;
				case WATER_BIOME_OAK_TREES:
					DefaultBiomeFeatures.addWaterBiomeOakTrees(TerrestriaBiome.biome);
					break;
				case BIRCH_TREES:
					DefaultBiomeFeatures.addBirchTrees(TerrestriaBiome.biome);
					break;
				case FOREST_TREES:
					DefaultBiomeFeatures.addForestTrees(TerrestriaBiome.biome);
					break;
				case TALL_BIRCH_TREES:
					DefaultBiomeFeatures.addTallBirchTrees(TerrestriaBiome.biome);
					break;
				case SAVANNAH_TREES:
					DefaultBiomeFeatures.addSavannaTrees(TerrestriaBiome.biome);
					break;
				case EXTRA_SAVANNA_TREES:
					DefaultBiomeFeatures.addExtraSavannaTrees(TerrestriaBiome.biome);
					break;
				case MOUNTAIN_TREES:
					DefaultBiomeFeatures.addMountainTrees(TerrestriaBiome.biome);
					break;
				case EXTRA_MOUNTAIN_TREES:
					DefaultBiomeFeatures.addExtraMountainTrees(TerrestriaBiome.biome);
					break;
				case JUNGLE_TREES:
					DefaultBiomeFeatures.addJungleTrees(TerrestriaBiome.biome);
					break;
				case JUNGLE_EDGE_TREES:
					DefaultBiomeFeatures.addJungleEdgeTrees(TerrestriaBiome.biome);
					break;
				case BADLANDS_PLATEAU_TREES:
					DefaultBiomeFeatures.addBadlandsPlateauTrees(TerrestriaBiome.biome);
					break;
				case SNOWY_SPRUCE_TREES:
					DefaultBiomeFeatures.addSnowySpruceTrees(TerrestriaBiome.biome);
					break;
				case GIANT_SPRUCE_TAIGA_TREES:
					DefaultBiomeFeatures.addGiantSpruceTaigaTrees(TerrestriaBiome.biome);
					break;
				case MEGA_SPRUCE_TAIGA_TREES:
					DefaultBiomeFeatures.addGiantTreeTaigaTrees(TerrestriaBiome.biome);
					break;
				case JUNGLE_GRASS:
					DefaultBiomeFeatures.addJungleGrass(TerrestriaBiome.biome);
					break;
				case SAVANNA_TALL_GRASS:
					DefaultBiomeFeatures.addSavannaTallGrass(TerrestriaBiome.biome);
					break;
				case SHATTERED_SAVANNAH_TALL_GRASS:
					DefaultBiomeFeatures.addShatteredSavannaGrass(TerrestriaBiome.biome);
					break;
				case SAVANNAH_GRASS:
					DefaultBiomeFeatures.addSavannaGrass(TerrestriaBiome.biome);
					break;
				case BADLANDS_GRASS:
					DefaultBiomeFeatures.addBadlandsGrass(TerrestriaBiome.biome);
					break;
				case FOREST_FLOWERS:
					DefaultBiomeFeatures.addForestFlowers(TerrestriaBiome.biome);
					break;
				case FOREST_GRASS:
					DefaultBiomeFeatures.addForestGrass(TerrestriaBiome.biome);
					break;
				case SWAMP_FEATURES:
					DefaultBiomeFeatures.addSwampFeatures(TerrestriaBiome.biome);
					break;
				case MUSHROOM_FIELDS_FEATURES:
					DefaultBiomeFeatures.addMushroomFieldsFeatures(TerrestriaBiome.biome);
					break;
				case PLAINS_FEATURES:
					DefaultBiomeFeatures.addPlainsFeatures(TerrestriaBiome.biome);
					break;
				case DESERT_DEAD_BUSHES:
					DefaultBiomeFeatures.addDesertDeadBushes(TerrestriaBiome.biome);
					break;
				case GIANT_TAIGA_GRASS:
					DefaultBiomeFeatures.addGiantTaigaGrass(TerrestriaBiome.biome);
					break;
				case DEFAULT_FLOWERS:
					DefaultBiomeFeatures.addDefaultFlowers(TerrestriaBiome.biome);
					break;
				case EXTRA_DEFAULT_FLOWERS:
					DefaultBiomeFeatures.addExtraDefaultFlowers(TerrestriaBiome.biome);
					break;
				case DEFAULT_GRASS:
					DefaultBiomeFeatures.addDefaultGrass(TerrestriaBiome.biome);
					break;
				case TAIGA_GRASS:
					DefaultBiomeFeatures.addTaigaGrass(TerrestriaBiome.biome);
					break;
				case PLANS_TALL_GRASS:
					DefaultBiomeFeatures.addPlainsTallGrass(TerrestriaBiome.biome);
					break;
				case DEFAULT_MUSHROOMS:
					DefaultBiomeFeatures.addDefaultMushrooms(TerrestriaBiome.biome);
					break;
				case DEFAULT_VEGETATION:
					DefaultBiomeFeatures.addDefaultVegetation(TerrestriaBiome.biome);
					break;
				case BADLANDS_VEGETATION:
					DefaultBiomeFeatures.addBadlandsVegetation(TerrestriaBiome.biome);
					break;
				case JUNGLE_VEGETATION:
					DefaultBiomeFeatures.addJungleVegetation(TerrestriaBiome.biome);
					break;
				case DESERT_VEGETATION:
					DefaultBiomeFeatures.addDesertVegetation(TerrestriaBiome.biome);
					break;
				case SWAMP_VEGETATION:
					DefaultBiomeFeatures.addSwampVegetation(TerrestriaBiome.biome);
					break;
				case DESSERT_FEATURES:
					DefaultBiomeFeatures.addDesertFeatures(TerrestriaBiome.biome);
					break;
				case FOSSILS:
					DefaultBiomeFeatures.addFossils(TerrestriaBiome.biome);
					break;
				case KELP:
					DefaultBiomeFeatures.addKelp(TerrestriaBiome.biome);
					break;
				case SEAGRASS_ON_STONE:
					DefaultBiomeFeatures.addSeagrassOnStone(TerrestriaBiome.biome);
					break;
				case SEAGRASS:
					DefaultBiomeFeatures.addSeagrass(TerrestriaBiome.biome);
					break;
				case MORE_SEAGRASS:
					DefaultBiomeFeatures.addMoreSeagrass(TerrestriaBiome.biome);
					break;
				case LESS_KELP:
					DefaultBiomeFeatures.addLessKelp(TerrestriaBiome.biome);
					break;
				case SPRINGS:
					DefaultBiomeFeatures.addSprings(TerrestriaBiome.biome);
					break;
				case ICEBERGS:
					DefaultBiomeFeatures.addIcebergs(TerrestriaBiome.biome);
					break;
				case BLUE_ICE:
					DefaultBiomeFeatures.addBlueIce(TerrestriaBiome.biome);
					break;
				case FROZEN_TOP_LAYER:
					DefaultBiomeFeatures.addFrozenTopLayer(TerrestriaBiome.biome);
					break;
			}
		}
	}

	public enum DefaultFeature {
		LAND_CARVERS("land_carvers"),
		OCEAN_CARVERS("ocean_carvers"),
		STRUCTURES("structures"),
		LAKES("lakes"),
		DESERT_LAKES("desert_lakes"),
		DUNGEONS("dungeons"),
		MINEABLES("mineables"),
		ORES("ores"),
		EXTRA_GOLD("extra_gold"),
		EMERALD_ORE("emerald_ore"),
		INFECTED_STONE("infected_stone"),
		DISKS("disks"),
		CLAY("clay"),
		MOSSY_ROCKS("mossy_rocks"),
		LARGE_FERNS("large_ferns"),
		SWEET_BERRY_BUSHES("sweet_berry_bushes"),
		SWEET_BERRY_BUSHES_SNOWY("sweet_berry_bushes_snowy"),
		BAMBOO("bamboo"),
		BAMBOO_JUNGLE_TREES("bamboo jungle trees"),
		TAIGA_TREES("taiga_trees"),
		WATER_BIOME_OAK_TREES("water_biome_oak_trees"),
		BIRCH_TREES("birch_trees"),
		FOREST_TREES("forest_trees"),
		TALL_BIRCH_TREES("tall_birch_trees"),
		SAVANNAH_TREES("savannah_trees"),
		EXTRA_SAVANNA_TREES("extra_savannah_trees"),
		MOUNTAIN_TREES("mountain_trees"),
		EXTRA_MOUNTAIN_TREES("extra_mountain_trees"),
		JUNGLE_TREES("jungle_trees"),
		JUNGLE_EDGE_TREES("jungle_edge_trees"),
		BADLANDS_PLATEAU_TREES("badlands_plateau_trees"),
		SNOWY_SPRUCE_TREES("snowy_spruce_trees"),
		GIANT_SPRUCE_TAIGA_TREES("giant_spruce_taiga_trees"),
		MEGA_SPRUCE_TAIGA_TREES("mega_spruce_taiga_trees"),
		JUNGLE_GRASS("jungle_grass"),
		SAVANNA_TALL_GRASS("savanna_tall_grass"),
		SHATTERED_SAVANNAH_TALL_GRASS("shattered_savannah_tall_grass"),
		SAVANNAH_GRASS("savannah_grass"),
		BADLANDS_GRASS("badlands_grass"),
		FOREST_FLOWERS("forrest_flowers"),
		FOREST_GRASS("forrest_grass"),
		SWAMP_FEATURES("swamp_features"),
		MUSHROOM_FIELDS_FEATURES("mushroom_fields_features"),
		PLAINS_FEATURES("plains_features"),
		DESERT_DEAD_BUSHES("desert_dead_bushes"),
		GIANT_TAIGA_GRASS("giant_taiga_grass"),
		DEFAULT_FLOWERS("default_flowers"),
		EXTRA_DEFAULT_FLOWERS("extra_default_flowers"),
		DEFAULT_GRASS("default_grass"),
		TAIGA_GRASS("taiga_grass"),
		PLANS_TALL_GRASS("plains_tall_grass"),
		DEFAULT_MUSHROOMS("default_mushrooms"),
		DEFAULT_VEGETATION("default_vegetation"),
		BADLANDS_VEGETATION("badlands_vegetation"),
		JUNGLE_VEGETATION("jungle_vegetation"),
		DESERT_VEGETATION("desert_vegetation"),
		SWAMP_VEGETATION("swamp_vegetation"),
		DESSERT_FEATURES("desert_features"),
		FOSSILS("fossils"),
		KELP("kelp"),
		SEAGRASS_ON_STONE("seagrass_on_stone"),
		SEAGRASS("seagrass"),
		MORE_SEAGRASS("more_seagrass"),
		LESS_KELP("less_kelp"),
		SPRINGS("springs"),
		ICEBERGS("icebergs"),
		BLUE_ICE("blue_ice"),
		FROZEN_TOP_LAYER("frozen_top_layer");

		private final String name;

		DefaultFeature(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
