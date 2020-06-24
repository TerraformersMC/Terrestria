package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.biome.*;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.function.Consumer;

public class TerrestriaBiomes {
	public static Biome CALDERA;
	public static Biome CALDERA_BEACH;
	public static Biome CALDERA_FOOTHILLS;
	public static Biome CALDERA_RIDGE;
	public static Biome CYPRESS_FOREST;
	public static Biome CYPRESS_SWAMP;
	public static Biome DENSE_WOODLANDS;
	public static Biome DENSE_WOODLANDS_EDGE;
	public static Biome HEMLOCK_CLEARING;
	public static Biome HEMLOCK_RAINFOREST;
	public static Biome JAPANESE_MAPLE_FOREST;
	public static Biome LUSH_REDWOOD_CLEARING;
	public static Biome LUSH_REDWOOD_FOREST;
	public static Biome LUSH_REDWOOD_FOREST_EDGE;
	public static Biome RAINBOW_RAINFOREST;
	public static Biome RAINBOW_RAINFOREST_LAKE;
	public static Biome RAINBOW_RAINFOREST_MOUNTAINS;
	public static Biome REDWOOD_CLEARING;
	public static Biome REDWOOD_FOREST;
	public static Biome REDWOOD_FOREST_EDGE;
	public static Biome SAKURA_FOREST;
	public static Biome SNOWY_HEMLOCK_CLEARING;
	public static Biome SNOWY_HEMLOCK_FOREST;
	public static Biome VOLCANIC_ISLAND;
	public static Biome VOLCANIC_ISLAND_BEACH;
	public static Biome VOLCANIC_ISLAND_SHORE;
	public static Biome WOODED_CYPRESS_HILLS;
	public static Biome WOODED_JAPANESE_MAPLE_HILLS;
	public static Biome WOODED_SAKURA_HILLS;

	public static void init() {

		CalderaBiomes.register();
		CypressForestBiomes.register();
		CypressSwampBiomes.register();
		DenseWoodlandsBiomes.register();
		HemlockRainforestBiomes.register();
		LushRedwoodForestBiomes.register();
		RainbowRainforestBiomes.register();
		RedwoodForestBiomes.register();
		SakuraForestBiomes.register();
		SnowyHemlockRainforestBiomes.register();
		JapaneseMapleForestBiomes.register();
		VolcanicIslandBiomes.register();

		TerrestriaFeatures.addVolcanoStarts(
				VOLCANIC_ISLAND,
				VOLCANIC_ISLAND_SHORE,
				Biomes.DEEP_OCEAN,
				Biomes.DEEP_COLD_OCEAN,
				Biomes.DEEP_LUKEWARM_OCEAN,
				Biomes.DEEP_WARM_OCEAN
		);
	}

	public static <T extends Biome> T register(String name, T biome) {
		return Registry.register(Registry.BIOME, new Identifier(Terrestria.MOD_ID, name), biome);
	}

	private static void forEveryBiome(Consumer<Biome> biomes) {
		Registry.BIOME.forEach(biomes);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((rawId, id, biome) -> biomes.accept(biome));
	}
}
