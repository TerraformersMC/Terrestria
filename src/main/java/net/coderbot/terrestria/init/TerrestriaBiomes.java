package net.coderbot.terrestria.init;

import io.github.terraformersmc.terraform.feature.CattailFeature;
import net.coderbot.terrestria.biome.*;
import io.github.terraformersmc.terraform.block.SmallLogBlock;
import net.coderbot.terrestria.biome.extensions.OverworldBiomesExt;
import net.coderbot.terrestria.feature.*;
import net.coderbot.terrestria.feature.volcano.VolcanoGenerator;
import net.coderbot.terrestria.feature.volcano.VolcanoStructureFeature;
import net.coderbot.terrestria.surface.BeachySurfaceBuilder;
import net.coderbot.terrestria.surface.CliffySurfaceBuilder;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class TerrestriaBiomes {
	public static CypressTreeFeature CYPRESS_TREE;
	public static CypressTreeFeature SMALL_BALD_CYPRESS_TREE;
	public static OakTreeFeature TALLER_BIRCH_TREE;
	public static JapaneseTreeFeature SAKURA_TREE;
	public static JapaneseTreeFeature JAPANESE_MAPLE_TREE;
	public static ShrubFeature JAPANESE_MAPLE_SHRUB;
	public static MegaCanopyTreeFeature RAINBOW_EUCALYPTUS_TREE;
	public static MegaCanopyTreeFeature BALD_CYPRESS_TREE;
	public static CattailFeature CATTAIL;
	public static PalmTreeFeature PALM_TREE;


	public static VolcanoStructureFeature VOLCANO_STRUCTURE;
	public static StructurePieceType VOLCANO_PIECE;

	public static BeachySurfaceBuilder CALDERA_SURFACE;
	public static BeachySurfaceBuilder BASALT_SURFACE;
	public static CliffySurfaceBuilder CLIFF_SURFACE;
	public static TernarySurfaceConfig BASALT_CONFIG;

	public static CypressForestBiome CYPRESS_FOREST;
	// public static CypressForestBiome CYPRESS_HILLS; // TODO
	public static JapaneseForestBiome SAKURA_FOREST;
	public static JapaneseForestBiome JAPANESE_MAPLE_FOREST;
	public static RainforestBiome RAINFOREST;
	public static CypressSwampBiome CYPRESS_SWAMP;
	public static CalderaBiome CALDERA;
	public static CalderaBiome CALDERA_RIDGE;
	public static CalderaBiome CALDERA_BEACH;
	public static VolcanicIslandBiome VOLCANIC_ISLAND;
	public static VolcanicIslandBiome VOLCANIC_ISLAND_BEACH;
	public static VolcanicIslandBiome VOLCANIC_ISLAND_SHORE;

	public static void init() {
		CYPRESS_TREE = Registry.register(Registry.FEATURE, "terrestria:cypress_tree",
				new CypressTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.CYPRESS.getBasicDefinition())
		);

		SMALL_BALD_CYPRESS_TREE = Registry.register(Registry.FEATURE, "terrestria:small_bald_cypress_tree",
				new CypressTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.BALD_CYPRESS.getBasicDefinition())
		);

		TALLER_BIRCH_TREE = Registry.register(Registry.FEATURE, "terrestria:taller_birch_tree",
				new OakTreeFeature(DefaultFeatureConfig::deserialize, false, 8, Blocks.BIRCH_LOG.getDefaultState(), Blocks.BIRCH_LEAVES.getDefaultState(), false)
		);

		SAKURA_TREE = Registry.register(Registry.FEATURE, "terrestria:sakura_tree",
				new SakuraTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.SAKURA.getBasicDefinition().toSakura (
						TerrestriaBlocks.SAKURA.log.getDefaultState().with(SmallLogBlock.HAS_LEAVES, true),
						TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState()
				))
		);

		JAPANESE_MAPLE_TREE = Registry.register(Registry.FEATURE, "terrestria:japanese_maple_tree",
				new JapaneseMapleTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.JAPANESE_MAPLE.getBasicDefinition())
		);

		TreeDefinition.Basic shrubDefinition = new TreeDefinition.Basic (
				TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()
		);

		JAPANESE_MAPLE_SHRUB = Registry.register(Registry.FEATURE, "terrestria:japanese_maple_shrub",
				new ShrubFeature(DefaultFeatureConfig::deserialize, false, shrubDefinition)
		);

		RAINBOW_EUCALYPTUS_TREE = Registry.register(Registry.FEATURE, "terrestria:rainbow_eucalyptus_tree",
				new MegaCanopyTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.RAINBOW_EUCALYPTUS.getBasicDefinition().toMega (
						TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG.getDefaultState(),
						TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState()
				))
		);

		BALD_CYPRESS_TREE = Registry.register(Registry.FEATURE, "terrestria:bald_cypress_tree",
				new MegaCanopyTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.BALD_CYPRESS.getBasicDefinition().toMega (
						TerrestriaBlocks.BALD_CYPRESS_QUARTER_LOG.getDefaultState(),
						TerrestriaBlocks.BALD_CYPRESS.wood.getDefaultState()
				))
		);

		CATTAIL = Registry.register(Registry.FEATURE, "terrestria:cattail",
				new CattailFeature(SeagrassFeatureConfig::deserialize, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL)
		);

		// TODO: WithBark wood
		TreeDefinition.Basic palmDefinition = new TreeDefinition.Basic (
				Blocks.JUNGLE_LOG.getDefaultState(),
				Blocks.JUNGLE_LEAVES.getDefaultState()
		);

		PALM_TREE = Registry.register(Registry.FEATURE, "terrestria:palm_tree",
				new PalmTreeFeature(DefaultFeatureConfig::deserialize, false, palmDefinition.withBark(Blocks.JUNGLE_WOOD.getDefaultState()))
		);

		VOLCANO_STRUCTURE = Registry.register(Registry.STRUCTURE_FEATURE, "terrestria:volcano",
				new VolcanoStructureFeature(DefaultFeatureConfig::deserialize)
		);

		Feature.STRUCTURES.put("volcano", VOLCANO_STRUCTURE);

		VOLCANO_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "terrestria:volcano", VolcanoGenerator::new);

		CALDERA_SURFACE = Registry.register(Registry.SURFACE_BUILDER, "terrestria:caldera", new BeachySurfaceBuilder(TernarySurfaceConfig::deserialize, 100, v -> Blocks.SAND.getDefaultState()));

		BASALT_SURFACE = Registry.register(Registry.SURFACE_BUILDER, "terrestria:basalt", new BeachySurfaceBuilder(
				TernarySurfaceConfig::deserialize,
				63,
				v -> v > 1.0 ? TerrestriaBlocks.BASALT_SAND.getDefaultState() : Blocks.SAND.getDefaultState()
		));

		CLIFF_SURFACE = Registry.register(Registry.SURFACE_BUILDER, "terrestria:cliff", new CliffySurfaceBuilder(TernarySurfaceConfig::deserialize, 63));

		BASALT_CONFIG = new TernarySurfaceConfig (
				TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState(),
				TerrestriaBlocks.BASALT_DIRT.getDefaultState(),
				TerrestriaBlocks.BASALT_DIRT.getDefaultState()
		);

		CYPRESS_FOREST = Registry.register(Registry.BIOME, "terrestria:cypress_forest", new CypressForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
						.depth(0.1F)
						.scale(0.2F)
						.temperature(0.7F)
						.downfall(0.8F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				13,
				CYPRESS_TREE,
				SMALL_BALD_CYPRESS_TREE,
				TALLER_BIRCH_TREE
		));

		SAKURA_FOREST = Registry.register(Registry.BIOME, "terrestria:sakura_forest", new JapaneseForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
						.depth(0.05F)
						.scale(0.1F)
						.temperature(0.8F)
						.downfall(1.0F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				SAKURA_TREE,
				JAPANESE_MAPLE_SHRUB
		));

		JAPANESE_MAPLE_FOREST = Registry.register(Registry.BIOME, "terrestria:japanese_maple_forest", new JapaneseForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
						.depth(0.05F)
						.scale(0.2F)
						.temperature(0.8F)
						.downfall(0.5F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				JAPANESE_MAPLE_TREE,
				JAPANESE_MAPLE_SHRUB
		));

		DefaultBiomeFeatures.addForestGrass(JAPANESE_MAPLE_FOREST);

		RAINFOREST = Registry.register(Registry.BIOME, "terrestria:rainforest", new RainforestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.JUNGLE)
						.depth(0.1F)
						.scale(0.2F)
						.temperature(0.95F)
						.downfall(0.9F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				RAINBOW_EUCALYPTUS_TREE,
				Feature.FANCY_TREE
		));

		CYPRESS_SWAMP = Registry.register(Registry.BIOME, "terrestria:cypress_swamp", new CypressSwampBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.SWAMP)
						.depth(-0.3F)
						.scale(0F)
						.temperature(0.7F)
						.downfall(0.7F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				7,
				BALD_CYPRESS_TREE,
				Feature.SWAMP_TREE
		));

		CALDERA = Registry.register(Registry.BIOME, "terrestria:caldera", new CalderaBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(CALDERA_SURFACE, SurfaceBuilder.SAND_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
						.depth(1.5F)
						.scale(0.05F)
						.temperature(0.7F)
						.downfall(0.7F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null)
		));

		CALDERA_RIDGE = Registry.register(Registry.BIOME, "terrestria:caldera_ridge", new CalderaBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS)
						.depth(4F)
						.scale(0F)
						.temperature(0F)
						.downfall(1F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null)
		));

		CALDERA_BEACH = Registry.register(Registry.BIOME, "terrestria:caldera_beach", new CalderaBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(CALDERA_SURFACE, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
						.depth(2.25F)
						.scale(0F)
						.temperature(0.7F)
						.downfall(0.7F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null)
		));

		VOLCANIC_ISLAND = Registry.register(Registry.BIOME, "terrestria:volcanic_island", new VolcanicIslandBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(CLIFF_SURFACE, BASALT_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS)
						.depth(0.1F)
						.scale(0.2F)
						.temperature(0.9F)
						.downfall(0.9F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null),
				5,
				PALM_TREE,
				PALM_TREE,
				0.01F
		));

		VOLCANIC_ISLAND_SHORE = Registry.register(Registry.BIOME, "terrestria:volcanic_island_shore", new VolcanicIslandBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(CLIFF_SURFACE, BASALT_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
						.depth(0.05F)
						.scale(0.05F)
						.temperature(0.9F)
						.downfall(0.9F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null),
				2,
				PALM_TREE,
				PALM_TREE,
				0.005F
		));

		VOLCANIC_ISLAND_BEACH = Registry.register(Registry.BIOME, "terrestria:volcanic_island_beach", new VolcanicIslandBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(BASALT_SURFACE, BASALT_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
						.depth(0F)
						.scale(0.05F)
						.temperature(0.9F)
						.downfall(0.9F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null),
				2,
				PALM_TREE,
				PALM_TREE,
				0
		));

		addVolcanoStarts(VOLCANIC_ISLAND, Biomes.DEEP_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_WARM_OCEAN);

		// 33% of Jungles will be replaced by Rainforest biomes
		// 33% of Mountains will be replaced with Caldera Ridges
		// 33% of Deep Oceans will be replaced with Volcanic Islands
		OverworldBiomes.addBiomeVariant(Biomes.JUNGLE, RAINFOREST, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.MOUNTAINS, CALDERA_RIDGE, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.DEEP_OCEAN, VOLCANIC_ISLAND,0.33);

		OverworldBiomes.addBaseBiome(CYPRESS_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addBaseBiome(SAKURA_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addBaseBiome(JAPANESE_MAPLE_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addBaseBiome(CYPRESS_SWAMP, OverworldClimate.TEMPERATE, 1.0);

		OverworldBiomes.addEdgeBiome(CALDERA, CALDERA_BEACH, 1);

		OverworldBiomesExt.addBorderBiome(CALDERA_RIDGE, Biomes.MOUNTAINS);
		OverworldBiomesExt.addBorderBiome(VOLCANIC_ISLAND, VOLCANIC_ISLAND_SHORE);
		OverworldBiomesExt.addCenterBiome(CALDERA_RIDGE, CALDERA);

		OverworldBiomes.setRiverBiome(CALDERA, null);
		OverworldBiomes.setRiverBiome(CALDERA_RIDGE, null);
		OverworldBiomes.setRiverBiome(CALDERA_BEACH, null);

		OverworldBiomes.addShoreBiome(VOLCANIC_ISLAND, VOLCANIC_ISLAND_BEACH, 1);
		OverworldBiomes.addShoreBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_BEACH, 1);
		OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND, null);
		OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND_SHORE, null);
		OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND_BEACH, null);

		FabricBiomes.addSpawnBiome(CYPRESS_FOREST);
		FabricBiomes.addSpawnBiome(RAINFOREST);
	}

	public static void addVolcanoStarts(Biome... biomes) {
		for(Biome biome: biomes) {
			biome.addStructureFeature(TerrestriaBiomes.VOLCANO_STRUCTURE, new DefaultFeatureConfig());
		}
	}

	public static void addVolcanoStructure(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, Biome.configureFeature(TerrestriaBiomes.VOLCANO_STRUCTURE, FeatureConfig.DEFAULT, Decorator.NOPE, DecoratorConfig.DEFAULT));
	}
}
