package com.terraformersmc.terrestria.biomegen;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.surfacerules.TerrestriaSurfaceRules;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.*;

import java.util.function.Consumer;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaTerraBlenderGeneration extends Region implements Runnable, TerraBlenderApi {

	public TerrestriaTerraBlenderGeneration() {
		super(new Identifier(Terrestria.MOD_ID, "overworld"), RegionType.OVERWORLD, 10);
	}

	@Override
	public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
		//this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_PEAKS, CALDERA_RIDGE);
		this.addBiomeSimilar(mapper, BiomeKeys.FOREST, CYPRESS_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.SWAMP, CYPRESS_SWAMP);
		this.addBiomeSimilar(mapper, BiomeKeys.TAIGA, HEMLOCK_RAINFOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.BIRCH_FOREST, DENSE_WOODLANDS);
		this.addBiomeSimilar(mapper, BiomeKeys.FOREST, JAPANESE_MAPLE_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, LUSH_REDWOOD_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.DESERT, LUSH_DESERT);
		this.addBiomeSimilar(mapper, BiomeKeys.FOREST, REDWOOD_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.JUNGLE, RAINBOW_RAINFOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.BIRCH_FOREST, SAKURA_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_TAIGA, SNOWY_HEMLOCK_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.GROVE, SNOWY_HEMLOCK_FOREST);
		this.addBiomeSimilar(mapper, BiomeKeys.MEADOW, SNOWY_HEMLOCK_TREELINE);
		//this.addBiomeSimilar(mapper, BiomeKeys.DEEP_OCEAN, VOLCANIC_ISLAND_SHORE);
		//this.addBiomeSimilar(mapper, BiomeKeys.DESERT, CANYON_CLIFFS);
		this.addBiomeSimilar(mapper, BiomeKeys.DESERT, DUNES);
		this.addBiomeSimilar(mapper, BiomeKeys.SAVANNA, OUTBACK);

		// Balancing low-utilization areas with vanilla biomes.
		this.addBiomeSimilar(mapper, BiomeKeys.RIVER, BiomeKeys.RIVER);
		this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_RIVER, BiomeKeys.FROZEN_RIVER);
		this.addBiomeSimilar(mapper, BiomeKeys.BEACH, BiomeKeys.BEACH);
		this.addBiomeSimilar(mapper, BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_BEACH);
		this.addBiomeSimilar(mapper, BiomeKeys.STONY_SHORE, BiomeKeys.STONY_SHORE);
		this.addBiomeSimilar(mapper, BiomeKeys.OCEAN, BiomeKeys.OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.COLD_OCEAN, BiomeKeys.COLD_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN);
		this.addBiomeSimilar(mapper, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE);
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
	}

	@Override
	public void onTerraBlenderInitialized() {
		// We can't do registration stuff until both Terrestria and TerraBlender are ready.
		// The run() method below will be called when Terrestria is done initializing.
		Terrestria.callbackWhenInitialized(this);
	}

	// Initialize TerraBlender as our biome placement provider.
	public void run() {
		// Register the Terrestria surface rules; this must happen before we call addSurfaceRules().
		TerrestriaSurfaceRules.register();

		// Add the Terrestria Overworld surface rules via TerraBlender.
		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Terrestria.MOD_ID, TerrestriaSurfaceRules.createRules());

		// Add the biomes to Overworld generation via TerraBlender.
		Regions.register(this);
	}
}
