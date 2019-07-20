package net.coderbot.terrestria.init;

import io.github.terraformersmc.terraform.biomeapi.OverworldBiomesExt;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.world.biome.Biomes;
import static net.coderbot.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaGeneration {
	public static void init() {
		// 33% of Jungles will be replaced by Rainforest biomes
		// 33% of Mountains will be replaced with Caldera Ridges
		// 10% of Deep Oceans will be replaced with Volcanic Islands
		OverworldBiomes.addBiomeVariant(Biomes.JUNGLE, RAINBOW_RAINFOREST, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.MOUNTAINS, CALDERA_RIDGE, 0.33);
		OverworldBiomes.addBiomeVariant(Biomes.DEEP_OCEAN, VOLCANIC_ISLAND_SHORE, 0.10);

		OverworldBiomes.addContinentalBiome(CYPRESS_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(SAKURA_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(JAPANESE_MAPLE_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(CYPRESS_SWAMP, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(REDWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(LUSH_REDWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0);
		OverworldBiomes.addContinentalBiome(HEMLOCK_RAINFOREST, OverworldClimate.COOL, 1.0);
		OverworldBiomes.addContinentalBiome(SNOWY_HEMLOCK_FOREST, OverworldClimate.SNOWY, 2.0);
		OverworldBiomes.addContinentalBiome(ALPS, OverworldClimate.SNOWY, 1.0);
		OverworldBiomes.addContinentalBiome(DENSE_WOODLANDS, OverworldClimate.DRY, 1.0);

		// Sub biome registration

		OverworldBiomes.addEdgeBiome(CALDERA, CALDERA_BEACH, 1);
		OverworldBiomes.addEdgeBiome(REDWOOD_FOREST, REDWOOD_FOREST_EDGE, 1);
		OverworldBiomes.addEdgeBiome(LUSH_REDWOOD_FOREST, LUSH_REDWOOD_FOREST_EDGE, 1);
		OverworldBiomes.addEdgeBiome(DENSE_WOODLANDS, DENSE_WOODLANDS_EDGE, 1);

		OverworldBiomesExt.addBorderBiome(CALDERA_RIDGE, ALPINE);
		OverworldBiomesExt.addCenterBiome(CALDERA_RIDGE, CALDERA);

		OverworldBiomesExt.addBorderBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_SHORE);
		OverworldBiomesExt.addCenterBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND);

		OverworldBiomes.addHillsBiome(REDWOOD_FOREST, REDWOOD_CLEARING, 1.0);
		OverworldBiomes.addHillsBiome(LUSH_REDWOOD_FOREST, LUSH_REDWOOD_CLEARING, 1.0);
		OverworldBiomes.addHillsBiome(CYPRESS_FOREST, WOODED_CYPRESS_HILLS, 1.0);
		OverworldBiomes.addHillsBiome(HEMLOCK_RAINFOREST, HEMLOCK_CLEARING, 1.0);
		OverworldBiomes.addHillsBiome(SNOWY_HEMLOCK_FOREST, SNOWY_HEMLOCK_CLEARING, 1.0);
		OverworldBiomes.addHillsBiome(SAKURA_FOREST, WOODED_SAKURA_HILLS, 1.0);
		OverworldBiomes.addHillsBiome(JAPANESE_MAPLE_FOREST, WOODED_JAPANESE_MAPLE_HILLS, 1.0);
		OverworldBiomes.addHillsBiome(RAINBOW_RAINFOREST, RAINBOW_RAINFOREST_MOUNTAINS, 1.0);
		OverworldBiomes.addHillsBiome(RAINBOW_RAINFOREST, RAINBOW_RAINFOREST_LAKE, 0.6);
		OverworldBiomes.addHillsBiome(ALPS, ALPINE, 1);

		OverworldBiomes.setRiverBiome(ALPS, null);
		OverworldBiomes.setRiverBiome(ALPINE, null);
		OverworldBiomes.setRiverBiome(CALDERA, null);
		OverworldBiomes.setRiverBiome(CALDERA_RIDGE, null);
		OverworldBiomes.setRiverBiome(CALDERA_BEACH, null);
		OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND, null);
		OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND_SHORE, null);
		OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND_BEACH, null);

		OverworldBiomes.addShoreBiome(VOLCANIC_ISLAND, VOLCANIC_ISLAND_BEACH, 1);
		OverworldBiomes.addShoreBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_BEACH, 1);

		FabricBiomes.addSpawnBiome(CYPRESS_FOREST);
		FabricBiomes.addSpawnBiome(RAINBOW_RAINFOREST);
		FabricBiomes.addSpawnBiome(REDWOOD_FOREST);
		FabricBiomes.addSpawnBiome(LUSH_REDWOOD_FOREST);
		FabricBiomes.addSpawnBiome(HEMLOCK_RAINFOREST);
	}
}
