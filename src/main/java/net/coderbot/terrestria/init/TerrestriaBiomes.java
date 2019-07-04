package net.coderbot.terrestria.init;

import io.github.terraformersmc.terraform.biomeapi.OverworldBiomesExt;
import net.coderbot.terrestria.Terrestria;
import net.coderbot.terrestria.biome.*;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import java.util.function.Consumer;

// This class exports public biome constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaBiomes {
	public static ConiferousBiome REDWOOD_FOREST;
	public static ConiferousBiome LUSH_REDWOOD_FOREST;
	public static ConiferousBiome TEMPERATE_RAINFOREST;
	public static ConiferousBiome SNOWY_RAINFOREST;
	public static AlpineBiome ALPINE;
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
		REDWOOD_FOREST = register("redwood_forest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(1.2F)
						.scale(0.3F)
						.temperature(1.1F)
						.downfall(1.4F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				7,
				TerrestriaFeatures.MEGA_REDWOOD_TREE,
				TerrestriaFeatures.MEGA_REDWOOD_TREE
		));

		LUSH_REDWOOD_FOREST = register("lush_redwood_forest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(1.2F)
						.scale(0.3F)
						.temperature(1.1F)
						.downfall(1.4F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				8,
				TerrestriaFeatures.MEGA_REDWOOD_TREE,
				TerrestriaFeatures.HEMLOCK_TREE
		));

		DefaultBiomeFeatures.addDefaultFlowers(LUSH_REDWOOD_FOREST);

		TEMPERATE_RAINFOREST = register("temperate_rainforest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(0.95F)
						.scale(0.55F)
						.temperature(0.6F)
						.downfall(0.9F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				17,
				TerrestriaFeatures.MEGA_HEMLOCK_TREE,
				TerrestriaFeatures.HEMLOCK_TREE
		));

		TEMPERATE_RAINFOREST.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(Feature.GRASS,
						new GrassFeatureConfig(Blocks.FERN.getDefaultState()),
						Decorator.COUNT_HEIGHTMAP_DOUBLE,
						new CountDecoratorConfig(12)
				)
		);

		TEMPERATE_RAINFOREST.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(Feature.GRASS,
						new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
						Decorator.COUNT_HEIGHTMAP_DOUBLE,
						new CountDecoratorConfig(4)
				)
		);

		SNOWY_RAINFOREST = register("snowy_rainforest", new ConiferousBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.TAIGA)
						.depth(0.95F)
						.scale(0.55F)
						.temperature(-0.5F)
						.downfall(1.3F)
						.waterColor(4020182)
						.waterFogColor(329011)
						.parent(null),
				17,
				TerrestriaFeatures.MEGA_HEMLOCK_TREE,
				TerrestriaFeatures.HEMLOCK_TREE
		));

		SNOWY_RAINFOREST.addFeature(
				GenerationStep.Feature.VEGETAL_DECORATION,
				Biome.configureFeature(Feature.GRASS,
						new GrassFeatureConfig(Blocks.GRASS.getDefaultState()),
						Decorator.COUNT_HEIGHTMAP_DOUBLE,
						new CountDecoratorConfig(4)
				)
		);

		ALPINE = register("alpine", new AlpineBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.EXTREME_HILLS)
						.depth(1.7F)
						.scale(0.4F)
						.temperature(0.0F)
						.downfall(0.1F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				1,
				TerrestriaFeatures.REDWOOD_TREE,
				TerrestriaFeatures.HEMLOCK_TREE
		));

		CYPRESS_FOREST = register("cypress_forest", new CypressForestBiome(
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
				TerrestriaFeatures.CYPRESS_TREE,
				TerrestriaFeatures.TALLER_BIRCH_TREE
		));

		SAKURA_FOREST = register("sakura_forest", new JapaneseForestBiome(
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
				TerrestriaFeatures.SAKURA_TREE,
				TerrestriaFeatures.JAPANESE_MAPLE_SHRUB
		));

		JAPANESE_MAPLE_FOREST = register("japanese_maple_forest", new JapaneseForestBiome(
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
				TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE,
				TerrestriaFeatures.JAPANESE_MAPLE_SHRUB
		));

		DefaultBiomeFeatures.addForestGrass(JAPANESE_MAPLE_FOREST);

		RAINFOREST = register("rainforest", new RainforestBiome(
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

		CYPRESS_SWAMP = register("cypress_swamp", new CypressSwampBiome(
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
				TerrestriaFeatures.MEGA_CYPRESS_TREE,
				Feature.SWAMP_TREE
		));

		CALDERA = register("caldera", new CalderaBiome(
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

		CALDERA_RIDGE = register("caldera_ridge", new CalderaBiome(
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

		CALDERA_BEACH = register("caldera_beach", new CalderaBiome(
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

		VOLCANIC_ISLAND = register("volcanic_island", new VolcanicIslandBiome(
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
				TerrestriaFeatures.PALM_TREE
		));

		VOLCANIC_ISLAND_SHORE = register("volcanic_island_shore", new VolcanicIslandBiome(
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
				TerrestriaFeatures.PALM_TREE
		));

		VOLCANIC_ISLAND_BEACH = register("volcanic_island_beach", new VolcanicIslandBiome(
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
				TerrestriaFeatures.PALM_TREE
		));

		TerrestriaFeatures.addVolcanoStarts (
				VOLCANIC_ISLAND,
				VOLCANIC_ISLAND_SHORE,
				Biomes.DEEP_OCEAN,
				Biomes.DEEP_COLD_OCEAN,
				Biomes.DEEP_LUKEWARM_OCEAN,
				Biomes.DEEP_WARM_OCEAN
		);

		forEveryBiome(TerrestriaFeatures::addVolcanoStructure);

		// 33% of Jungles will be replaced by Rainforest biomes
		// 33% of Mountains will be replaced with Caldera Ridges
		// 10% of Deep Oceans will be replaced with Volcanic Islands
		OverworldBiomes.addBiomeVariant(Biomes.JUNGLE, RAINFOREST, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.MOUNTAINS, CALDERA_RIDGE, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.DEEP_OCEAN, VOLCANIC_ISLAND_SHORE,0.10);

		OverworldBiomes.addContinentalBiome(CYPRESS_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(SAKURA_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(JAPANESE_MAPLE_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(CYPRESS_SWAMP, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(REDWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(LUSH_REDWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(TEMPERATE_RAINFOREST, OverworldClimate.COOL, 1.0);
		OverworldBiomes.addContinentalBiome(SNOWY_RAINFOREST, OverworldClimate.SNOWY, 2.0);
		OverworldBiomes.addContinentalBiome(ALPINE, OverworldClimate.SNOWY, 1.0);

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
		FabricBiomes.addSpawnBiome(REDWOOD_FOREST);
		FabricBiomes.addSpawnBiome(LUSH_REDWOOD_FOREST);
		FabricBiomes.addSpawnBiome(TEMPERATE_RAINFOREST);
	}

	public static <T extends Biome> T register(String name, T biome) {
		return Registry.register(Registry.BIOME, new Identifier(Terrestria.MOD_ID, name), biome);
	}

	private static void forEveryBiome(Consumer<Biome> biomes) {
		Registry.BIOME.forEach(biomes);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((rawId, id, biome) -> biomes.accept(biome));
	}
}
