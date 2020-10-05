package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.biome.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;

public class TerrestriaBiomes {
	public static Biome CALDERA;
	public static Biome CALDERA_BEACH;
	public static Biome CALDERA_FOOTHILLS;
	public static Biome CALDERA_RIDGE;
	public static Biome CANYON_ARCHES;
	public static Biome CANYON_CLIFFS;
	public static Biome CANYON_EDGE;
	public static Biome CYPRESS_FOREST;
	public static Biome CYPRESS_SWAMP;
	public static Biome DENSE_WOODLANDS;
	public static Biome DENSE_WOODLANDS_EDGE;
	public static Biome DUNES;
	public static Biome DUNES_EDGE;
	public static Biome HEMLOCK_CLEARING;
	public static Biome HEMLOCK_RAINFOREST;
	public static Biome JAPANESE_MAPLE_FOREST;
	public static Biome LUSH_REDWOOD_CLEARING;
	public static Biome LUSH_REDWOOD_FOREST;
	public static Biome LUSH_REDWOOD_FOREST_EDGE;
	public static Biome LUSH_DESERT;
	public static Biome OASIS;
	public static Biome OUTBACK;
	public static Biome OUTBACK_BUSHLAND;
	public static Biome OUTBACK_ULURU;
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

	public static <T extends Biome> T register(String name, T biome) {
		return BuiltinRegistries.add(BuiltinRegistries.BIOME, new Identifier(Terrestria.MOD_ID, name), biome);
	}
}
