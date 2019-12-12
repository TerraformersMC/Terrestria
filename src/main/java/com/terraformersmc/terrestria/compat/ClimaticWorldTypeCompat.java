package com.terraformersmc.terrestria.compat;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;

import tk.valoeghese.climatic.api.Climate;
import tk.valoeghese.climatic.api.ClimateBiomes;
import tk.valoeghese.climatic.api.OceanClimate;
import tk.valoeghese.climatic.impl.ClimateBiomesImpl;

public final class ClimaticWorldTypeCompat {
	private ClimaticWorldTypeCompat() {
	}

	public static void init(BiomeEnabledConfigCache configCache) {
		// Caldera
		computeIfEnabled("caldera", configCache, () -> {
			ClimateBiomes.addBiome(Climate.HUMID_SUBTROPICAL, TerrestriaBiomes.CALDERA_RIDGE, 5);
			ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.CALDERA_RIDGE, 5);
			ClimateBiomes.addSmallEdgeCorrection(TerrestriaBiomes.CALDERA_BEACH, TerrestriaBiomes.CALDERA_RIDGE, TerrestriaBiomes.CALDERA);

			ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.CALDERA);
			ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.CALDERA_FOOTHILLS);
		});

		// Cypress Forest
		computeIfEnabled("cypress_forest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.MEDITERRANEAN, TerrestriaBiomes.CYPRESS_FOREST, 10);
		});

		// Cypress Swamp
		computeIfEnabled("cypress_swamp", configCache, () -> {
			ClimateBiomes.addBiome(Climate.HUMID_SUBTROPICAL, TerrestriaBiomes.CYPRESS_SWAMP, 10);
		});

		// Dense Woodlands
		computeIfEnabled("dense_woodlands", configCache, () -> {
			ClimateBiomes.addBiome(Climate.TROPICAL_STEPPE, TerrestriaBiomes.DENSE_WOODLANDS, 3);
		});

		// Hemlock Rainforest
		computeIfEnabled("hemlock_rainforest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.HEMLOCK_RAINFOREST, 10);
		});

		// Japanese Maple Forest
		computeIfEnabled("japanese_maple_forest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.WARM_TEMPERATE, TerrestriaBiomes.JAPANESE_MAPLE_FOREST, 10);
		});

		// Lush Redwood Forest
		computeIfEnabled("lush_redwood_forest", configCache, () -> {
			if (configCache.isEnabled("redwood_forest")) {
				ClimateBiomes.addNeighboursForBiome(TerrestriaBiomes.REDWOOD_FOREST, TerrestriaBiomes.LUSH_REDWOOD_FOREST, 5);
			} else {
				ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.LUSH_REDWOOD_FOREST, 5);
			}
		});

		// Rainbow Eucalyptus Rainforest
		computeIfEnabled("rainbow_rainforest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.TROPICAL_RAINFOREST, TerrestriaBiomes.RAINBOW_RAINFOREST, 5);
			ClimateBiomes.addNeighboursForBiome(TerrestriaBiomes.RAINBOW_RAINFOREST, TerrestriaBiomes.RAINBOW_RAINFOREST_MOUNTAINS, 5);
		});

		// Redwood Forest
		computeIfEnabled("redwood_forest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.REDWOOD_FOREST, 10);
		});

		// Sakura Forest
		computeIfEnabled("sakura_forest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.SAKURA_FOREST, 5);
			ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.SAKURA_FOREST, 10);
		});

		// Snowy Hemlock Rainforest
		computeIfEnabled("snowy_hemlock_forest", configCache, () -> {
			ClimateBiomes.addBiome(Climate.SNOWY, TerrestriaBiomes.SNOWY_HEMLOCK_FOREST, 10);
		});

		// Volcanic Island
		computeIfEnabled("volcanic_island", configCache, () -> {
			ClimateBiomes.addIslandBiome(OceanClimate.WARM, TerrestriaBiomes.VOLCANIC_ISLAND_SHORE, 14);
			ClimateBiomes.addIslandBiome(OceanClimate.LUKEWARM, TerrestriaBiomes.VOLCANIC_ISLAND_SHORE, 5);

			ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.VOLCANIC_ISLAND);
		});
	}

	private static void computeIfEnabled(String name, BiomeEnabledConfigCache configCache, ClimaticCompatCallback ifEnabled) {
		if (configCache.isEnabled(name)) {
			ifEnabled.exec();
		}
	}

	private static interface ClimaticCompatCallback {
		void exec();
	}
}
