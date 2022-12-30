package com.terraformersmc.terrestria.biomegen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.config.TerrestriaBiomeConfig;
import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilders;
import com.terraformersmc.terrestria.surfacerules.TerrestriaSurfaceRules;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaBiolithGeneration implements Runnable {
	TerrestriaBiomeConfig BIOME_CONFIG;

    public void addBiomes() {
		if (BIOME_CONFIG.isBiomeEnabled(CALDERA))                  { BiomePlacement.replaceOverworld(BiomeKeys.MEADOW, CALDERA, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(CANYON))                   { BiomePlacement.replaceOverworld(BiomeKeys.DESERT, CANYON, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(CYPRESS_FOREST))           { BiomePlacement.replaceOverworld(BiomeKeys.FOREST, CYPRESS_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(CYPRESS_SWAMP))            { BiomePlacement.replaceOverworld(BiomeKeys.SWAMP, CYPRESS_SWAMP, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(DENSE_WOODLANDS))          { BiomePlacement.replaceOverworld(BiomeKeys.BIRCH_FOREST, DENSE_WOODLANDS, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(HEMLOCK_RAINFOREST))       { BiomePlacement.replaceOverworld(BiomeKeys.TAIGA, HEMLOCK_RAINFOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(JAPANESE_MAPLE_FOREST))    { BiomePlacement.replaceOverworld(BiomeKeys.FOREST, JAPANESE_MAPLE_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(LUSH_REDWOOD_FOREST))      { BiomePlacement.replaceOverworld(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, LUSH_REDWOOD_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(LUSH_DESERT))              { BiomePlacement.replaceOverworld(BiomeKeys.DESERT, LUSH_DESERT, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(OUTBACK))                  { BiomePlacement.replaceOverworld(BiomeKeys.SAVANNA, OUTBACK, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(REDWOOD_FOREST))           { BiomePlacement.replaceOverworld(BiomeKeys.FOREST, REDWOOD_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SAKURA_FOREST))            { BiomePlacement.replaceOverworld(BiomeKeys.BIRCH_FOREST, SAKURA_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_FOREST))     { BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_TAIGA, SNOWY_HEMLOCK_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_FOREST))     { BiomePlacement.replaceOverworld(BiomeKeys.GROVE, SNOWY_HEMLOCK_FOREST, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_TREELINE))   { BiomePlacement.replaceOverworld(BiomeKeys.SNOWY_SLOPES, SNOWY_HEMLOCK_TREELINE, 0.325D); }
		if (BIOME_CONFIG.isBiomeEnabled(WINDSWEPT_REDWOOD_FOREST)) { BiomePlacement.replaceOverworld(BiomeKeys.WINDSWEPT_FOREST, WINDSWEPT_REDWOOD_FOREST, 0.325D); }

		if (BIOME_CONFIG.isBiomeEnabled(DUNES))                    { BiomePlacement.replaceOverworld(BiomeKeys.DESERT, DUNES, 0.175D); }
		if (BIOME_CONFIG.isBiomeEnabled(HEMLOCK_TREELINE))         { BiomePlacement.replaceOverworld(BiomeKeys.MEADOW, HEMLOCK_TREELINE, 0.175D); }
		if (BIOME_CONFIG.isBiomeEnabled(RAINBOW_RAINFOREST))       { BiomePlacement.replaceOverworld(BiomeKeys.JUNGLE, RAINBOW_RAINFOREST, 0.175D); }
		if (BIOME_CONFIG.isBiomeEnabled(VOLCANIC_ISLAND))          { BiomePlacement.replaceOverworld(BiomeKeys.DEEP_LUKEWARM_OCEAN, VOLCANIC_ISLAND, 0.175D); }
    }

	// Use Biolith to register our Biome placements.
	// We can't do registration stuff until Terrestria's common module is ready.
	// This method will be called when Terrestria is done initializing.
    @Override
    public void run() {
        // Register the Terrestria surface rules.
		SurfaceGeneration.addOverworldSurfaceRules(
				Identifier.of(Terrestria.MOD_ID, "surface_rules"),
				TerrestriaSurfaceRules.createRules());

		// Register the Terrestria surface builders.
		TerrestriaSurfaceBuilders.getBuilders().forEach(SurfaceGeneration::addSurfaceBuilder);

		// Add the biomes to Overworld generation via Biolith.
		BIOME_CONFIG = Terrestria.getConfigManager().getBiomeConfig();
		this.addBiomes();
    }
}
