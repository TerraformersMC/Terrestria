package com.terraformersmc.terrestria.biome.builder;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

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
	PLAINS_TALL_GRASS("plains_tall_grass"),
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

	public void add(Biome biome) {
		switch (this) {
			case LAND_CARVERS:
				DefaultBiomeFeatures.addLandCarvers(biome);
				break;
			case OCEAN_CARVERS:
				DefaultBiomeFeatures.addOceanCarvers(biome);
				break;
			case STRUCTURES:
				DefaultBiomeFeatures.addDefaultStructures(biome);
				break;
			case LAKES:
				DefaultBiomeFeatures.addDefaultLakes(biome);
				break;
			case DESERT_LAKES:
				DefaultBiomeFeatures.addDesertLakes(biome);
				break;
			case DUNGEONS:
				DefaultBiomeFeatures.addDungeons(biome);
				break;
			case MINEABLES:
				DefaultBiomeFeatures.addMineables(biome);
				break;
			case ORES:
				DefaultBiomeFeatures.addDefaultOres(biome);
				break;
			case EXTRA_GOLD:
				DefaultBiomeFeatures.addExtraGoldOre(biome);
				break;
			case EMERALD_ORE:
				DefaultBiomeFeatures.addEmeraldOre(biome);
				break;
			case INFECTED_STONE:
				DefaultBiomeFeatures.addInfestedStone(biome);
				break;
			case DISKS:
				DefaultBiomeFeatures.addDefaultDisks(biome);
				break;
			case CLAY:
				DefaultBiomeFeatures.addClay(biome);
				break;
			case MOSSY_ROCKS:
				DefaultBiomeFeatures.addMossyRocks(biome);
				break;
			case LARGE_FERNS:
				DefaultBiomeFeatures.addLargeFerns(biome);
				break;
			case SWEET_BERRY_BUSHES:
				DefaultBiomeFeatures.addSweetBerryBushes(biome);
				break;
			case SWEET_BERRY_BUSHES_SNOWY:
				DefaultBiomeFeatures.addSweetBerryBushesSnowy(biome);
				break;
			case BAMBOO:
				DefaultBiomeFeatures.addBamboo(biome);
				break;
			case BAMBOO_JUNGLE_TREES:
				DefaultBiomeFeatures.addBambooJungleTrees(biome);
				break;
			case TAIGA_TREES:
				DefaultBiomeFeatures.addTaigaTrees(biome);
				break;
			case WATER_BIOME_OAK_TREES:
				DefaultBiomeFeatures.addWaterBiomeOakTrees(biome);
				break;
			case BIRCH_TREES:
				DefaultBiomeFeatures.addBirchTrees(biome);
				break;
			case FOREST_TREES:
				DefaultBiomeFeatures.addForestTrees(biome);
				break;
			case TALL_BIRCH_TREES:
				DefaultBiomeFeatures.addTallBirchTrees(biome);
				break;
			case SAVANNAH_TREES:
				DefaultBiomeFeatures.addSavannaTrees(biome);
				break;
			case EXTRA_SAVANNA_TREES:
				DefaultBiomeFeatures.addExtraSavannaTrees(biome);
				break;
			case MOUNTAIN_TREES:
				DefaultBiomeFeatures.addMountainTrees(biome);
				break;
			case EXTRA_MOUNTAIN_TREES:
				DefaultBiomeFeatures.addExtraMountainTrees(biome);
				break;
			case JUNGLE_TREES:
				DefaultBiomeFeatures.addJungleTrees(biome);
				break;
			case JUNGLE_EDGE_TREES:
				DefaultBiomeFeatures.addJungleEdgeTrees(biome);
				break;
			case BADLANDS_PLATEAU_TREES:
				DefaultBiomeFeatures.addBadlandsPlateauTrees(biome);
				break;
			case SNOWY_SPRUCE_TREES:
				DefaultBiomeFeatures.addSnowySpruceTrees(biome);
				break;
			case GIANT_SPRUCE_TAIGA_TREES:
				DefaultBiomeFeatures.addGiantSpruceTaigaTrees(biome);
				break;
			case MEGA_SPRUCE_TAIGA_TREES:
				DefaultBiomeFeatures.addGiantTreeTaigaTrees(biome);
				break;
			case JUNGLE_GRASS:
				DefaultBiomeFeatures.addJungleGrass(biome);
				break;
			case SAVANNA_TALL_GRASS:
				DefaultBiomeFeatures.addSavannaTallGrass(biome);
				break;
			case SHATTERED_SAVANNAH_TALL_GRASS:
				DefaultBiomeFeatures.addShatteredSavannaGrass(biome);
				break;
			case SAVANNAH_GRASS:
				DefaultBiomeFeatures.addSavannaGrass(biome);
				break;
			case BADLANDS_GRASS:
				DefaultBiomeFeatures.addBadlandsGrass(biome);
				break;
			case FOREST_FLOWERS:
				DefaultBiomeFeatures.addForestFlowers(biome);
				break;
			case FOREST_GRASS:
				DefaultBiomeFeatures.addForestGrass(biome);
				break;
			case SWAMP_FEATURES:
				DefaultBiomeFeatures.addSwampFeatures(biome);
				break;
			case MUSHROOM_FIELDS_FEATURES:
				DefaultBiomeFeatures.addMushroomFieldsFeatures(biome);
				break;
			case PLAINS_FEATURES:
				DefaultBiomeFeatures.addPlainsFeatures(biome);
				break;
			case DESERT_DEAD_BUSHES:
				DefaultBiomeFeatures.addDesertDeadBushes(biome);
				break;
			case GIANT_TAIGA_GRASS:
				DefaultBiomeFeatures.addGiantTaigaGrass(biome);
				break;
			case DEFAULT_FLOWERS:
				DefaultBiomeFeatures.addDefaultFlowers(biome);
				break;
			case EXTRA_DEFAULT_FLOWERS:
				DefaultBiomeFeatures.addExtraDefaultFlowers(biome);
				break;
			case DEFAULT_GRASS:
				DefaultBiomeFeatures.addDefaultGrass(biome);
				break;
			case TAIGA_GRASS:
				DefaultBiomeFeatures.addTaigaGrass(biome);
				break;
			case PLAINS_TALL_GRASS:
				DefaultBiomeFeatures.addPlainsTallGrass(biome);
				break;
			case DEFAULT_MUSHROOMS:
				DefaultBiomeFeatures.addDefaultMushrooms(biome);
				break;
			case DEFAULT_VEGETATION:
				DefaultBiomeFeatures.addDefaultVegetation(biome);
				break;
			case BADLANDS_VEGETATION:
				DefaultBiomeFeatures.addBadlandsVegetation(biome);
				break;
			case JUNGLE_VEGETATION:
				DefaultBiomeFeatures.addJungleVegetation(biome);
				break;
			case DESERT_VEGETATION:
				DefaultBiomeFeatures.addDesertVegetation(biome);
				break;
			case SWAMP_VEGETATION:
				DefaultBiomeFeatures.addSwampVegetation(biome);
				break;
			case DESSERT_FEATURES:
				DefaultBiomeFeatures.addDesertFeatures(biome);
				break;
			case FOSSILS:
				DefaultBiomeFeatures.addFossils(biome);
				break;
			case KELP:
				DefaultBiomeFeatures.addKelp(biome);
				break;
			case SEAGRASS_ON_STONE:
				DefaultBiomeFeatures.addSeagrassOnStone(biome);
				break;
			case SEAGRASS:
				DefaultBiomeFeatures.addSeagrass(biome);
				break;
			case MORE_SEAGRASS:
				DefaultBiomeFeatures.addMoreSeagrass(biome);
				break;
			case LESS_KELP:
				DefaultBiomeFeatures.addLessKelp(biome);
				break;
			case SPRINGS:
				DefaultBiomeFeatures.addSprings(biome);
				break;
			case ICEBERGS:
				DefaultBiomeFeatures.addIcebergs(biome);
				break;
			case BLUE_ICE:
				DefaultBiomeFeatures.addBlueIce(biome);
				break;
			case FROZEN_TOP_LAYER:
				DefaultBiomeFeatures.addFrozenTopLayer(biome);
				break;
		}
	}
}
