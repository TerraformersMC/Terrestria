package com.terraformersmc.terrestria.biomegen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.config.TerrestriaBiomeConfig;
import com.terraformersmc.terrestria.surface.builders.TerrestriaSurfaceBuilders;
import com.terraformersmc.terrestria.surface.rules.TerrestriaSurfaceRules;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biomes;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaBiolithGeneration {
	private static final TerrestriaBiomeConfig BIOME_CONFIG = Terrestria.getConfigManager().getBiomeConfig();

	public static void init() {
		// Register the Terrestria surface rules.
		SurfaceGeneration.addOverworldSurfaceRules(
			Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "surface_rules"),
			TerrestriaSurfaceRules::bootstrap);

		// Register the Terrestria surface builders.
		TerrestriaSurfaceBuilders.getBuilders().forEach(SurfaceGeneration::addSurfaceBuilder);

		// Register the Terrestria biomes.
		if (BIOME_CONFIG.isBiomeEnabled(CALDERA))                  { BiomePlacement.replaceOverworld(Biomes.MEADOW, CALDERA, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(CANYON))                   { BiomePlacement.replaceOverworld(Biomes.DESERT, CANYON, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(CYPRESS_FOREST))           { BiomePlacement.replaceOverworld(Biomes.FOREST, CYPRESS_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(CYPRESS_SWAMP))            { BiomePlacement.replaceOverworld(Biomes.SWAMP, CYPRESS_SWAMP, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(DENSE_WOODLANDS))          { BiomePlacement.replaceOverworld(Biomes.BIRCH_FOREST, DENSE_WOODLANDS, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(HEMLOCK_RAINFOREST))       { BiomePlacement.replaceOverworld(Biomes.TAIGA, HEMLOCK_RAINFOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(JAPANESE_MAPLE_FOREST))    { BiomePlacement.replaceOverworld(Biomes.FOREST, JAPANESE_MAPLE_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(LUSH_REDWOOD_FOREST))      { BiomePlacement.replaceOverworld(Biomes.OLD_GROWTH_BIRCH_FOREST, LUSH_REDWOOD_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(LUSH_DESERT))              { BiomePlacement.replaceOverworld(Biomes.DESERT, LUSH_DESERT, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(OUTBACK))                  { BiomePlacement.replaceOverworld(Biomes.SAVANNA, OUTBACK, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(REDWOOD_FOREST))           { BiomePlacement.replaceOverworld(Biomes.FOREST, REDWOOD_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SAKURA_FOREST))            { BiomePlacement.replaceOverworld(Biomes.CHERRY_GROVE, SAKURA_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_FOREST))     { BiomePlacement.replaceOverworld(Biomes.SNOWY_TAIGA, SNOWY_HEMLOCK_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_FOREST))     { BiomePlacement.replaceOverworld(Biomes.GROVE, SNOWY_HEMLOCK_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_TREELINE))   { BiomePlacement.replaceOverworld(Biomes.SNOWY_SLOPES, SNOWY_HEMLOCK_TREELINE, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(WINDSWEPT_REDWOOD_FOREST)) { BiomePlacement.replaceOverworld(Biomes.WINDSWEPT_FOREST, WINDSWEPT_REDWOOD_FOREST, 0.325D); }

		if (BIOME_CONFIG.isBiomeEnabled(DUNES))                    { BiomePlacement.replaceOverworld(Biomes.DESERT, DUNES, 0.175D); }
		if (BIOME_CONFIG.isBiomeEnabled(HEMLOCK_TREELINE))         { BiomePlacement.replaceOverworld(Biomes.MEADOW, HEMLOCK_TREELINE, 0.175D); }
		if (BIOME_CONFIG.isBiomeEnabled(RAINBOW_RAINFOREST))       { BiomePlacement.replaceOverworld(Biomes.JUNGLE, RAINBOW_RAINFOREST, 0.175D); }
		if (BIOME_CONFIG.isBiomeEnabled(VOLCANIC_ISLAND))          { BiomePlacement.replaceOverworld(Biomes.DEEP_LUKEWARM_OCEAN, VOLCANIC_ISLAND, 0.175D); }
    }
}
