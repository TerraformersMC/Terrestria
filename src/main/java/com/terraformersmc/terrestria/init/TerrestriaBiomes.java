package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.biome.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeKeys;

public class TerrestriaBiomes {
	public static RegistryKey<Biome> CALDERA;
	public static RegistryKey<Biome> CALDERA_BEACH;
	public static RegistryKey<Biome> CALDERA_FOOTHILLS;
	public static RegistryKey<Biome> CALDERA_RIDGE;
	public static RegistryKey<Biome> CANYON_ARCHES;
	public static RegistryKey<Biome> CANYON_CLIFFS;
	public static RegistryKey<Biome> CANYON_EDGE;
	public static RegistryKey<Biome> CYPRESS_FOREST;
	public static RegistryKey<Biome> CYPRESS_SWAMP;
	public static RegistryKey<Biome> DENSE_WOODLANDS;
	public static RegistryKey<Biome> DENSE_WOODLANDS_EDGE;
	public static RegistryKey<Biome> DUNES;
	public static RegistryKey<Biome> DUNES_EDGE;
	public static RegistryKey<Biome> HEMLOCK_CLEARING;
	public static RegistryKey<Biome> HEMLOCK_RAINFOREST;
	public static RegistryKey<Biome> JAPANESE_MAPLE_FOREST;
	public static RegistryKey<Biome> LUSH_REDWOOD_CLEARING;
	public static RegistryKey<Biome> LUSH_REDWOOD_FOREST;
	public static RegistryKey<Biome> LUSH_REDWOOD_FOREST_EDGE;
	public static RegistryKey<Biome> LUSH_DESERT;
	public static RegistryKey<Biome> OASIS;
	public static RegistryKey<Biome> OUTBACK;
	public static RegistryKey<Biome> OUTBACK_BUSHLAND;
	public static RegistryKey<Biome> OUTBACK_ULURU;
	public static RegistryKey<Biome> RAINBOW_RAINFOREST;
	public static RegistryKey<Biome> RAINBOW_RAINFOREST_LAKE;
	public static RegistryKey<Biome> RAINBOW_RAINFOREST_MOUNTAINS;
	public static RegistryKey<Biome> REDWOOD_CLEARING;
	public static RegistryKey<Biome> REDWOOD_FOREST;
	public static RegistryKey<Biome> REDWOOD_FOREST_EDGE;
	public static RegistryKey<Biome> SAKURA_FOREST;
	public static RegistryKey<Biome> SNOWY_HEMLOCK_CLEARING;
	public static RegistryKey<Biome> SNOWY_HEMLOCK_FOREST;
	public static RegistryKey<Biome> VOLCANIC_ISLAND;
	public static RegistryKey<Biome> VOLCANIC_ISLAND_BEACH;
	public static RegistryKey<Biome> VOLCANIC_ISLAND_SHORE;
	public static RegistryKey<Biome> WOODED_CYPRESS_HILLS;
	public static RegistryKey<Biome> WOODED_JAPANESE_MAPLE_HILLS;
	public static RegistryKey<Biome> WOODED_SAKURA_HILLS;

	// Copied from Traverse
	public static BiomeEffects.Builder createDefaultBiomeEffects() {
		return new BiomeEffects.Builder()
				.waterColor(0x3F76E4)
				.waterFogColor(0x50533)
				.skyColor(getSkyColor(0.2F))
				.fogColor(0xC0D8FF);
	}

	// Copied from Minecraft
	private static int getSkyColor(float temperature) {
		float f = temperature / 3.0F;
		f = MathHelper.clamp(f, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
	}

	public static void init() {
		CalderaBiomes.register();
		CanyonBiomes.register();
		CypressForestBiomes.register();
		CypressSwampBiomes.register();
		DenseWoodlandsBiomes.register();
		DunesBiomes.register();
		HemlockRainforestBiomes.register();
		LushRedwoodForestBiomes.register();
		LushDesertBiomes.register();
		RainbowRainforestBiomes.register();
		RedwoodForestBiomes.register();
		SakuraForestBiomes.register();
		SnowyHemlockRainforestBiomes.register();
		JapaneseMapleForestBiomes.register();
		VolcanicIslandBiomes.register();
		OutbackBiomes.register();
	}

	public static RegistryKey<Biome> register(String name, Biome biome) {
		Identifier identifier = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.BIOME, identifier, biome);

		return RegistryKey.of(Registry.BIOME_KEY, identifier);
	}
}
