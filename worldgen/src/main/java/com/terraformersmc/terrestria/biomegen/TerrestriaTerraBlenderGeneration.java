package com.terraformersmc.terrestria.biomegen;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.config.TerrestriaBiomeConfig;
import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilders;
import com.terraformersmc.terrestria.surfacerules.TerrestriaSurfaceRules;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaTerraBlenderGeneration extends Region implements Runnable, TerraBlenderApi {
	TerrestriaBiomeConfig BIOME_CONFIG;

	public TerrestriaTerraBlenderGeneration() {
		super(new Identifier(Terrestria.MOD_ID, "overworld"), RegionType.OVERWORLD, 13);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
		if (BIOME_CONFIG.isBiomeEnabled(CALDERA))                  { this.addBiomeSimilar(mapper, BiomeKeys.MEADOW, CALDERA); }
		if (BIOME_CONFIG.isBiomeEnabled(CANYON))                   { this.addBiomeSimilar(mapper, BiomeKeys.DESERT, CANYON); }
		if (BIOME_CONFIG.isBiomeEnabled(CYPRESS_FOREST))           { this.addBiomeSimilar(mapper, BiomeKeys.FOREST, CYPRESS_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(CYPRESS_SWAMP))            { this.addBiomeSimilar(mapper, BiomeKeys.SWAMP, CYPRESS_SWAMP); }
		if (BIOME_CONFIG.isBiomeEnabled(DENSE_WOODLANDS))          { this.addBiomeSimilar(mapper, BiomeKeys.BIRCH_FOREST, DENSE_WOODLANDS); }
		if (BIOME_CONFIG.isBiomeEnabled(HEMLOCK_RAINFOREST))       { this.addBiomeSimilar(mapper, BiomeKeys.TAIGA, HEMLOCK_RAINFOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(JAPANESE_MAPLE_FOREST))    { this.addBiomeSimilar(mapper, BiomeKeys.FOREST, JAPANESE_MAPLE_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(LUSH_REDWOOD_FOREST))      { this.addBiomeSimilar(mapper, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, LUSH_REDWOOD_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(LUSH_DESERT))              { this.addBiomeSimilar(mapper, BiomeKeys.DESERT, LUSH_DESERT); }
		if (BIOME_CONFIG.isBiomeEnabled(OUTBACK))                  { this.addBiomeSimilar(mapper, BiomeKeys.SAVANNA, OUTBACK); }
		if (BIOME_CONFIG.isBiomeEnabled(REDWOOD_FOREST))           { this.addBiomeSimilar(mapper, BiomeKeys.FOREST, REDWOOD_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(SAKURA_FOREST))            { this.addBiomeSimilar(mapper, BiomeKeys.CHERRY_GROVE, SAKURA_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_FOREST))     { this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_TAIGA, SNOWY_HEMLOCK_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_FOREST))     { this.addBiomeSimilar(mapper, BiomeKeys.GROVE, SNOWY_HEMLOCK_FOREST); }
		if (BIOME_CONFIG.isBiomeEnabled(SNOWY_HEMLOCK_TREELINE))   { this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_SLOPES, SNOWY_HEMLOCK_TREELINE); }
		if (BIOME_CONFIG.isBiomeEnabled(WINDSWEPT_REDWOOD_FOREST)) { this.addBiomeSimilar(mapper, BiomeKeys.WINDSWEPT_FOREST, WINDSWEPT_REDWOOD_FOREST); }

		// Balancing low-utilization areas with vanilla biomes.
		this.addBiomeSimilar(mapper, BiomeKeys.RIVER, BiomeKeys.RIVER);
		this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_RIVER, BiomeKeys.FROZEN_RIVER);
		this.addBiomeSimilar(mapper, BiomeKeys.BEACH, BiomeKeys.BEACH);
		this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_BEACH);
		this.addBiomeSimilar(mapper, BiomeKeys.STONY_SHORE, BiomeKeys.STONY_SHORE);
		this.addBiomeSimilar(mapper, BiomeKeys.WARM_OCEAN, BiomeKeys.WARM_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.OCEAN, BiomeKeys.OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.COLD_OCEAN, BiomeKeys.COLD_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE);
		this.addBiomeSimilar(mapper, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.MANGROVE_SWAMP);
		this.addBiomeSimilar(mapper, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BAMBOO_JUNGLE);
		this.addBiomeSimilar(mapper, BiomeKeys.SPARSE_JUNGLE, BiomeKeys.SPARSE_JUNGLE);
		this.addBiomeSimilar(mapper, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA);
		this.addBiomeSimilar(mapper, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.SAVANNA_PLATEAU);
		this.addBiomeSimilar(mapper, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.WINDSWEPT_SAVANNA);
		this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_PEAKS, BiomeKeys.FROZEN_PEAKS);
		this.addBiomeSimilar(mapper, BiomeKeys.STONY_PEAKS, BiomeKeys.STONY_PEAKS);
		this.addBiomeSimilar(mapper, BiomeKeys.PLAINS, BiomeKeys.PLAINS);
		this.addBiomeSimilar(mapper, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.SUNFLOWER_PLAINS);
		this.addBiomeSimilar(mapper, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS);
		this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS);
		this.addBiomeSimilar(mapper, BiomeKeys.ICE_SPIKES, BiomeKeys.ICE_SPIKES);
		this.addBiomeSimilar(mapper, BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.MUSHROOM_FIELDS);
	}

	@Override
	public void onTerraBlenderInitialized() {
		// We can't do registration stuff until both Terrestria and TerraBlender are ready.
		// The run() method below will be called when Terrestria is done initializing.
		Terrestria.callbackWhenInitialized(TerrestriaSurfaceBuilders::init);
		Terrestria.callbackWhenInitialized(this);
	}

	// Initialize TerraBlender as our biome placement provider.
	@Override
	public void run() {
		// Register the Terrestria surface rules; this must happen before we call addSurfaceRules().
		TerrestriaSurfaceRules.init();

		// Add the Terrestria Overworld surface rules via TerraBlender.
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Terrestria.MOD_ID, TerrestriaSurfaceRules.createRules());

		// Register the Terrestria surface builders.
		TerrestriaSurfaceBuilders.getBuilders().forEach(SurfaceGeneration::addSurfaceBuilder);

		// Add the biomes to Overworld generation via TerraBlender.
		BIOME_CONFIG = Terrestria.getConfigManager().getBiomeConfig();
		Regions.register(this);
		Regions.register(new TerrestriaTerraBlenderRare());
	}
}
