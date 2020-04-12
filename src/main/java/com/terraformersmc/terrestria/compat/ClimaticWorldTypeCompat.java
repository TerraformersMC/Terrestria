package com.terraformersmc.terrestria.compat;

import java.util.Set;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;

import tk.valoeghese.climatic.api.Climate;
import tk.valoeghese.climatic.api.ClimateBiomes;
import tk.valoeghese.climatic.api.OceanClimate;
import tk.valoeghese.climatic.impl.ClimateBiomesImpl;

public final class ClimaticWorldTypeCompat {
	private ClimaticWorldTypeCompat() {
	}

	public static void init(Set<String> enabledBiomes) {
		// Caldera
		if (enabledBiomes.contains("caldera")) {
			ClimateBiomes.addBiome(Climate.HUMID_SUBTROPICAL, TerrestriaBiomes.CALDERA_RIDGE, 5);
			ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.CALDERA_RIDGE, 5);
			ClimateBiomes.addSmallEdgeCorrection(TerrestriaBiomes.CALDERA_BEACH, TerrestriaBiomes.CALDERA, TerrestriaBiomes.CALDERA_BEACH, TerrestriaBiomes.CALDERA);

			ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.CALDERA);
			ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.CALDERA_FOOTHILLS);
		}

		// Cypress Forest
		if (enabledBiomes.contains("cypress_forest")) {
			ClimateBiomes.addBiome(Climate.MEDITERRANEAN, TerrestriaBiomes.CYPRESS_FOREST, 10);
		}

		// Cypress Swamp
		if (enabledBiomes.contains("cypress_swamp")) {
			ClimateBiomes.addBiome(Climate.HUMID_SUBTROPICAL, TerrestriaBiomes.CYPRESS_SWAMP, 10);
		}

		// Dense Woodlands
		if (enabledBiomes.contains("dense_woodlands")) {
			ClimateBiomes.addBiome(Climate.TROPICAL_STEPPE, TerrestriaBiomes.DENSE_WOODLANDS, 3);
		}

		// Hemlock Rainforest
		if (enabledBiomes.contains("hemlock_rainforest")) {
			ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.HEMLOCK_RAINFOREST, 10);
		}

		// Japanese Maple Forest
		if (enabledBiomes.contains("japanese_maple_forest")) {
			ClimateBiomes.addBiome(Climate.WARM_TEMPERATE, TerrestriaBiomes.JAPANESE_MAPLE_FOREST, 10);
		}

		// Lush Desert
		if (enabledBiomes.contains("lush_desert")) {
			ClimateBiomes.addBiome(Climate.TROPICAL_DESERT, TerrestriaBiomes.LUSH_DESERT, 3);
		}

		// Lush Redwood Forest
		if (enabledBiomes.contains("lush_redwood_forest")) {
			if (enabledBiomes.contains("redwood_forest")) {
				ClimateBiomes.addNeighboursForBiome(TerrestriaBiomes.REDWOOD_FOREST, TerrestriaBiomes.LUSH_REDWOOD_FOREST, 5);
			} else {
				ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.LUSH_REDWOOD_FOREST, 5);
			}
		}

		// Rainbow Eucalyptus Rainforest
		if (enabledBiomes.contains("rainbow_rainforest")) {
			ClimateBiomes.addBiome(Climate.TROPICAL_RAINFOREST, TerrestriaBiomes.RAINBOW_RAINFOREST, 5);
			ClimateBiomes.addNeighboursForBiome(TerrestriaBiomes.RAINBOW_RAINFOREST, TerrestriaBiomes.RAINBOW_RAINFOREST_MOUNTAINS, 5);
		}

		// Redwood Forest
		if (enabledBiomes.contains("redwood_forest")) {
			ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.REDWOOD_FOREST, 10);
		}

		// Sakura Forest
		if (enabledBiomes.contains("sakura_forest")) {
			ClimateBiomes.addBiome(Climate.MARITIME, TerrestriaBiomes.SAKURA_FOREST, 5);
			ClimateBiomes.addBiome(Climate.COOL_TEMPERATE, TerrestriaBiomes.SAKURA_FOREST, 10);
		}

		// Snowy Hemlock Rainforest
		if (enabledBiomes.contains("snowy_hemlock_forest")) {
			ClimateBiomes.addBiome(Climate.SNOWY, TerrestriaBiomes.SNOWY_HEMLOCK_FOREST, 10);
		}

		// Volcanic Island
		if (enabledBiomes.contains("volcanic_island")) {
			ClimateBiomes.addIslandBiome(OceanClimate.WARM, TerrestriaBiomes.VOLCANIC_ISLAND_SHORE, 14);
			ClimateBiomes.addIslandBiome(OceanClimate.LUKEWARM, TerrestriaBiomes.VOLCANIC_ISLAND_SHORE, 5);

			ClimateBiomesImpl.trackBiomeIfAbsent(TerrestriaBiomes.VOLCANIC_ISLAND);
		}
	}
}
