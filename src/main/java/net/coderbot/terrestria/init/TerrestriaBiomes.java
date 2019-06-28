package net.coderbot.terrestria.init;

import net.coderbot.terrestria.biome.*;
import net.coderbot.terrestria.biome.extensions.OverworldBiomesExt;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class TerrestriaBiomes {
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
				TerrestriaFeatures.CYPRESS_TREE,
				TerrestriaFeatures.SMALL_BALD_CYPRESS_TREE,
				TerrestriaFeatures.TALLER_BIRCH_TREE
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
				TerrestriaFeatures.SAKURA_TREE,
				TerrestriaFeatures.JAPANESE_MAPLE_SHRUB
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
				TerrestriaFeatures.JAPANESE_MAPLE_TREE,
				TerrestriaFeatures.JAPANESE_MAPLE_SHRUB
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
				TerrestriaFeatures.RAINBOW_EUCALYPTUS_TREE,
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
				TerrestriaFeatures.BALD_CYPRESS_TREE,
				Feature.SWAMP_TREE
		));

		CALDERA = Registry.register(Registry.BIOME, "terrestria:caldera", new CalderaBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA_SURFACE, SurfaceBuilder.SAND_CONFIG)
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
						.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA_SURFACE, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
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
						.configureSurfaceBuilder(TerrestriaSurfaces.CLIFF_SURFACE, TerrestriaSurfaces.BASALT_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS)
						.depth(0.1F)
						.scale(0.2F)
						.temperature(0.9F)
						.downfall(0.9F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null),
				5,
				TerrestriaFeatures.PALM_TREE,
				TerrestriaFeatures.PALM_TREE,
				0.01F
		));

		VOLCANIC_ISLAND_SHORE = Registry.register(Registry.BIOME, "terrestria:volcanic_island_shore", new VolcanicIslandBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(TerrestriaSurfaces.CLIFF_SURFACE, TerrestriaSurfaces.BASALT_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
						.depth(0.05F)
						.scale(0.05F)
						.temperature(0.9F)
						.downfall(0.9F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null),
				2,
				TerrestriaFeatures.PALM_TREE,
				TerrestriaFeatures.PALM_TREE,
				0.005F
		));

		VOLCANIC_ISLAND_BEACH = Registry.register(Registry.BIOME, "terrestria:volcanic_island_beach", new VolcanicIslandBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(TerrestriaSurfaces.BASALT_SURFACE, TerrestriaSurfaces.BASALT_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
						.depth(0F)
						.scale(0.05F)
						.temperature(0.9F)
						.downfall(0.9F)
						.waterColor(0x54d3c0)
						.waterFogColor(0x24a0b0)
						.parent(null),
				2,
				TerrestriaFeatures.PALM_TREE,
				TerrestriaFeatures.PALM_TREE,
				0
		));

		addVolcanoStarts(VOLCANIC_ISLAND, VOLCANIC_ISLAND_SHORE, Biomes.DEEP_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_WARM_OCEAN);

		// 33% of Jungles will be replaced by Rainforest biomes
		// 33% of Mountains will be replaced with Caldera Ridges
		// 10% of Deep Oceans will be replaced with Volcanic Islands
		OverworldBiomes.addBiomeVariant(Biomes.JUNGLE, RAINFOREST, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.MOUNTAINS, CALDERA_RIDGE, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.DEEP_OCEAN, VOLCANIC_ISLAND_SHORE,0.10);

		OverworldBiomes.addBaseBiome(CYPRESS_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addBaseBiome(SAKURA_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addBaseBiome(JAPANESE_MAPLE_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addBaseBiome(CYPRESS_SWAMP, OverworldClimate.TEMPERATE, 1.0);

		OverworldBiomes.addEdgeBiome(CALDERA, CALDERA_BEACH, 1);

		OverworldBiomesExt.addBorderBiome(CALDERA_RIDGE, Biomes.MOUNTAINS);
		OverworldBiomesExt.addCenterBiome(CALDERA_RIDGE, CALDERA);

		OverworldBiomesExt.addBorderBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_SHORE);
		OverworldBiomesExt.addCenterBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND);

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
			biome.addStructureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE, new DefaultFeatureConfig());
		}
	}

	public static void addVolcanoStructure(Biome biome) {
		biome.addFeature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, Biome.configureFeature(TerrestriaFeatures.VOLCANO_STRUCTURE, FeatureConfig.DEFAULT, Decorator.NOPE, DecoratorConfig.DEFAULT));
	}
}
