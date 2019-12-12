package com.terraformersmc.terrestria.compat;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;

import net.minecraft.world.biome.Biome;
import tk.valoeghese.climatic.api.Climate;
import tk.valoeghese.climatic.api.ClimateBiomes;
import tk.valoeghese.climatic.api.OceanClimate;
import tk.valoeghese.climatic.impl.ClimateBiomesImpl;

public final class ClimaticWorldTypeCompat {
	private ClimaticWorldTypeCompat() {
	}

	public static void init(BiomeEnabledConfigCache config) {
		// Caldera
		
		ClimateBiomes.addBiome(Climate.HUMID_SUBTROPICAL, TerrestriaBiomes.CALDERA_RIDGE, 5);
		ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.CALDERA_RIDGE, 5);
		ClimateBiomes.addSmallEdgeCorrection(TerrestriaBiomes.CALDERA_BEACH, TerrestriaBiomes.CALDERA_RIDGE, TerrestriaBiomes.CALDERA);

		ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.CALDERA);
		ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.CALDERA_FOOTHILLS);

		// Cypress Forest
		ClimateBiomes.addBiome(Climate.MEDITERRANEAN, TerrestriaBiomes.CYPRESS_FOREST, 10);

		// Cypress Swamp
		ClimateBiomes.addBiome(Climate.HUMID_SUBTROPICAL, TerrestriaBiomes.CYPRESS_SWAMP, 10);

		// Dense Woodlands
		ClimateBiomes.addBiome(Climate.TROPICAL_STEPPE, TerrestriaBiomes.DENSE_WOODLANDS, 3);

		// Hemlock Rainforest
		ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.HEMLOCK_RAINFOREST, 10);

		// Japanese Maple Forest
		ClimateBiomes.addBiome(Climate.WARM_TEMPERATE, TerrestriaBiomes.JAPANESE_MAPLE_FOREST, 10);

		// Redwood Forest and Lush Redwood Forest
		ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.REDWOOD_FOREST, 10);
		ClimateBiomes.addNeighboursForBiome(TerrestriaBiomes.REDWOOD_FOREST, TerrestriaBiomes.LUSH_REDWOOD_FOREST, 5);

		// Rainbow Eucalyptus Rainforest
		ClimateBiomes.addBiome(Climate.TROPICAL_RAINFOREST, TerrestriaBiomes.RAINBOW_RAINFOREST, 5);
		ClimateBiomes.addNeighboursForBiome(TerrestriaBiomes.RAINBOW_RAINFOREST, TerrestriaBiomes.RAINBOW_RAINFOREST_MOUNTAINS, 5);

		// Sakura Forest
		ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.SAKURA_FOREST, 5);
		ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.SAKURA_FOREST, 10);

		// Snowy Hemlock Rainforest
		ClimateBiomes.addBiome(Climate.SNOWY, TerrestriaBiomes.SNOWY_HEMLOCK_FOREST, 10);

		// Volcanic Island
		ClimateBiomes.addIslandBiome(OceanClimate.WARM, TerrestriaBiomes.VOLCANIC_ISLAND_SHORE, 14);
		ClimateBiomes.addIslandBiome(OceanClimate.LUKEWARM, TerrestriaBiomes.VOLCANIC_ISLAND_SHORE, 5);

		ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.VOLCANIC_ISLAND);
	}
	
	private static void addBiome(Biome main, int weight, String name, BiomeEnabledConfigCache config, ClimaticCompatCallback ifEnabled) {
		if (config.isEnabled(name)) {
			ifEnabled.exec(main, weight);
		}
	}
	
	private static interface ClimaticCompatCallback {
		void exec(Biome main, int weight);
	}
}
