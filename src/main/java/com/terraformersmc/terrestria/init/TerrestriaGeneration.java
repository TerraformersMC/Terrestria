package com.terraformersmc.terrestria.init;

import java.util.Set;
import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.terraform.config.BiomeConfig;
import com.terraformersmc.terraform.config.BiomeConfigNode;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaGeneration extends Region {
	public TerrestriaGeneration(Identifier name, int weight) {
		super(name, RegionType.OVERWORLD, weight);
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
		this.addBiomeSimilar(mapper, BiomeKeys.MEADOW, SNOWY_HEMLOCK_CLEARING);
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

	public static void init(BiomeConfig config, Set<String> enabledBiomes) {
		/*
		// 33% of Jungles will be replaced by Rainforest biomes
		// 33% of Mountains will be replaced with Caldera Ridges
		// 10% of Deep Oceans will be replaced with Volcanic Islands

		addBiomeVariant(BiomeKeys.MOUNTAINS, CALDERA_RIDGE, 0.33, "caldera", config, enabledBiomes);
		addContinentalBiome(CYPRESS_FOREST, OverworldClimate.TEMPERATE, 1.0, "cypress_forest", config, enabledBiomes);
		addContinentalBiome(CYPRESS_SWAMP, OverworldClimate.TEMPERATE, 1.0, "cypress_swamp", config, enabledBiomes);
		addContinentalBiome(HEMLOCK_RAINFOREST, OverworldClimate.COOL, 1.0, "hemlock_rainforest", config, enabledBiomes);
		addContinentalBiome(DENSE_WOODLANDS, OverworldClimate.DRY, 1.0, "dense_woodlands", config, enabledBiomes);
		addContinentalBiome(JAPANESE_MAPLE_FOREST, OverworldClimate.TEMPERATE, 1.0, "japanese_maple_forest", config, enabledBiomes);
		addContinentalBiome(LUSH_REDWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0, "lush_redwood_forest", config, enabledBiomes);
		addContinentalBiome(LUSH_DESERT, OverworldClimate.DRY, 1.0, "lush_desert", config, enabledBiomes);
		addContinentalBiome(REDWOOD_FOREST, OverworldClimate.TEMPERATE, 1.0, "redwood_forest", config, enabledBiomes);
		addBiomeVariant(BiomeKeys.JUNGLE, RAINBOW_RAINFOREST, 0.33, "rainbow_rainforest", config, enabledBiomes);
		addContinentalBiome(SAKURA_FOREST, OverworldClimate.TEMPERATE, 1.0, "sakura_forest", config, enabledBiomes);
		addContinentalBiome(SNOWY_HEMLOCK_FOREST, OverworldClimate.SNOWY, 2.0, "snowy_hemlock_forest", config, enabledBiomes);
		addBiomeVariant(BiomeKeys.DEEP_OCEAN, VOLCANIC_ISLAND_SHORE, 0.10, "volcanic_island", config, enabledBiomes);
		addContinentalBiome(CANYON_CLIFFS, OverworldClimate.DRY, 1.0, "canyon_cliffs", config, enabledBiomes);
		addContinentalBiome(DUNES, OverworldClimate.DRY, 0.2, "dunes", config, enabledBiomes);
		addContinentalBiome(OUTBACK, OverworldClimate.DRY, 1.0, "outback", config, enabledBiomes);

		if(CALDERA_RIDGE != null) {
			OverworldBiomes.addEdgeBiome(CALDERA, CALDERA_BEACH, 1);
			OverworldBiomesExt.addBorderBiome(CALDERA_RIDGE, CALDERA_FOOTHILLS);
			OverworldBiomesExt.addCenterBiome(CALDERA_RIDGE, CALDERA);

			OverworldBiomes.setRiverBiome(CALDERA_FOOTHILLS, CALDERA_FOOTHILLS);
			OverworldBiomes.setRiverBiome(CALDERA, CALDERA);
			OverworldBiomes.setRiverBiome(CALDERA_BEACH, CALDERA_BEACH);
			OverworldBiomes.setRiverBiome(CALDERA_RIDGE, CALDERA_RIDGE);
		}

		if (CANYON_CLIFFS != null) {
			OverworldBiomes.addHillsBiome(CANYON_CLIFFS, CANYON_ARCHES, 1);
			OverworldBiomes.addEdgeBiome(CANYON_CLIFFS, CANYON_EDGE, 1);
		}

		if(CYPRESS_FOREST != null) {
			OverworldBiomes.addHillsBiome(CYPRESS_FOREST, WOODED_CYPRESS_HILLS, 1.0);
		}

		if(DENSE_WOODLANDS != null) {
			OverworldBiomes.addEdgeBiome(DENSE_WOODLANDS, DENSE_WOODLANDS_EDGE, 1.0);
		}

		if (DUNES != null) {
			OverworldBiomes.setRiverBiome(DUNES, DUNES);
			OverworldBiomes.addEdgeBiome(DUNES, DUNES_EDGE, 1);
		}

		if(HEMLOCK_RAINFOREST != null) {
			OverworldBiomes.addHillsBiome(HEMLOCK_RAINFOREST, HEMLOCK_CLEARING, 1.0);
		}

		if(JAPANESE_MAPLE_FOREST != null) {
			OverworldBiomes.addHillsBiome(JAPANESE_MAPLE_FOREST, WOODED_JAPANESE_MAPLE_HILLS, 1.0);
		}

		if(LUSH_DESERT != null) {
			OverworldBiomesExt.addCenterBiome(LUSH_DESERT, OASIS);
		}

		if(LUSH_REDWOOD_FOREST != null) {
			OverworldBiomes.addHillsBiome(LUSH_REDWOOD_FOREST, LUSH_REDWOOD_CLEARING, 1.0);
			OverworldBiomes.addEdgeBiome(LUSH_REDWOOD_FOREST, LUSH_REDWOOD_FOREST_EDGE, 1);
		}

		if(RAINBOW_RAINFOREST != null) {
			OverworldBiomes.addHillsBiome(RAINBOW_RAINFOREST, RAINBOW_RAINFOREST_LAKE, 0.6);
			OverworldBiomes.addHillsBiome(RAINBOW_RAINFOREST, RAINBOW_RAINFOREST_MOUNTAINS, 1.0);
		}

		if(REDWOOD_FOREST != null) {
			OverworldBiomes.addHillsBiome(REDWOOD_FOREST, REDWOOD_CLEARING, 1.0);
			OverworldBiomes.addEdgeBiome(REDWOOD_FOREST, REDWOOD_FOREST_EDGE, 1.0);
		}

		if(SAKURA_FOREST != null) {
			OverworldBiomes.addHillsBiome(SAKURA_FOREST, WOODED_SAKURA_HILLS, 1.0);
		}

		if(SNOWY_HEMLOCK_FOREST != null) {
			OverworldBiomes.addHillsBiome(SNOWY_HEMLOCK_FOREST, SNOWY_HEMLOCK_CLEARING, 1.0);
		}

		if(VOLCANIC_ISLAND_SHORE != null) {
			OverworldBiomesExt.addCenterBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND);
			OverworldBiomesExt.addBorderBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_SHORE);
			OverworldBiomes.addShoreBiome(VOLCANIC_ISLAND, VOLCANIC_ISLAND_BEACH, 1);
			OverworldBiomes.addShoreBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_BEACH, 1);

			OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND, VOLCANIC_ISLAND);
			OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND_BEACH, VOLCANIC_ISLAND_BEACH);
			OverworldBiomes.setRiverBiome(VOLCANIC_ISLAND_SHORE, VOLCANIC_ISLAND_SHORE);
		}

		if (OUTBACK != null) {
			OverworldBiomes.addHillsBiome(OUTBACK, OUTBACK_ULURU, 1);
			OverworldBiomesExt.addPredicatedBorderBiome(OUTBACK, OUTBACK_BUSHLAND, b -> {
				Biome.Category category = BuiltinRegistries.BIOME.get(b).getCategory();
				return category != Biome.Category.DESERT && category != Biome.Category.SAVANNA && category != Biome.Category.PLAINS && category != Biome.Category.MESA;
			});
		}
	}

	private static void addBiomeVariant(RegistryKey<Biome> parent, RegistryKey<Biome> biome, double chance, String name, BiomeConfig config, Set<String> enabledBiomes) {
		boolean enable = !config.isFrozen();

		BiomeConfigNode.Variant variant = config.variant(name, new BiomeConfigNode.Variant(enable, chance));
		enable = variant.isEnabled();

		if (enable) {
			enabledBiomes.add(name);
		}

		if(biome != null && enable && variant.getVariantChance() > 0.0) {
			OverworldBiomes.addBiomeVariant(parent, biome, variant.getVariantChance());
		}
	}

	private static void addContinentalBiome(RegistryKey<Biome> biome, OverworldClimate climate, double weight, String name, BiomeConfig config, Set<String> enabledBiomes) {
		boolean enable = !config.isFrozen();

		BiomeConfigNode.Continental continental = config.continental(name, new BiomeConfigNode.Continental(enable, weight));
		enable = continental.isEnabled();

		if (enable) {
			enabledBiomes.add(name);
		}

		if(biome != null && enable && continental.getWeight() > 0.0) {
			OverworldBiomes.addContinentalBiome(biome, climate, continental.getWeight());
		}
	 */
	}
}
