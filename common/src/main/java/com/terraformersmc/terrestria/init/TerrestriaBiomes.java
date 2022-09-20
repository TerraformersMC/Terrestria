package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.biome.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import java.util.HashMap;
import java.util.Map;

public class TerrestriaBiomes {
	public static final Map<String, RegistryKey<Biome>> biomes = new HashMap<>();

	public static RegistryKey<Biome> CALDERA;
	public static RegistryKey<Biome> CANYON;
	public static RegistryKey<Biome> CYPRESS_FOREST;
	public static RegistryKey<Biome> CYPRESS_SWAMP;
	public static RegistryKey<Biome> DENSE_WOODLANDS;
	public static RegistryKey<Biome> DUNES;
	public static RegistryKey<Biome> HEMLOCK_RAINFOREST;
	public static RegistryKey<Biome> HEMLOCK_TREELINE;
	public static RegistryKey<Biome> JAPANESE_MAPLE_FOREST;
	public static RegistryKey<Biome> LUSH_REDWOOD_FOREST;
	public static RegistryKey<Biome> LUSH_DESERT;
	public static RegistryKey<Biome> OASIS;
	public static RegistryKey<Biome> OUTBACK;
	public static RegistryKey<Biome> RAINBOW_RAINFOREST;
	public static RegistryKey<Biome> REDWOOD_FOREST;
	public static RegistryKey<Biome> SAKURA_FOREST;
	public static RegistryKey<Biome> SNOWY_HEMLOCK_FOREST;
	public static RegistryKey<Biome> SNOWY_HEMLOCK_TREELINE;
	public static RegistryKey<Biome> VOLCANIC_ISLAND;
	public static RegistryKey<Biome> WINDSWEPT_REDWOOD_FOREST;
	public static RegistryKey<Biome> WINDSWEPT_WOODLANDS;


	public static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
		DefaultBiomeFeatures.addLandCarvers(generationSettings);
		DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
		DefaultBiomeFeatures.addDungeons(generationSettings);
		DefaultBiomeFeatures.addMineables(generationSettings);
		DefaultBiomeFeatures.addSprings(generationSettings);
		DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
	}

	public static SpawnSettings.Builder createDefaultSpawnSettings() {
		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		addDefaultCreatureSpawnEntries(spawnSettings);
		addDefaultAmbientSpawnEntries(spawnSettings);
		addDefaultMonsterSpawnEntries(spawnSettings);
		return spawnSettings;
	}

	public static void addDefaultCreatureSpawnEntries(SpawnSettings.Builder builder) {
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 10, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 10, 4, 4));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 8, 4, 4));
	}

	public static void addDefaultAmbientSpawnEntries(SpawnSettings.Builder builder) {
		builder.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
	}

	public static void addDefaultMonsterSpawnEntries(SpawnSettings.Builder builder) {
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 4, 4));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
	}

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
		DunesBiomes.register();
		HemlockRainforestBiomes.register();
		JapaneseMapleForestBiomes.register();
		LushDesertBiomes.register();
		LushRedwoodForestBiomes.register();
		OutbackBiomes.register();
		RainbowRainforestBiomes.register();
		RedwoodForestBiomes.register();
		SakuraForestBiomes.register();
		SnowyHemlockRainforestBiomes.register();
		VolcanicIslandBiomes.register();
		WoodlandsBiomes.register();
	}

	public static RegistryKey<Biome> register(String name, Biome biome) {
		Identifier identifier = new Identifier(Terrestria.MOD_ID, name);
		RegistryKey<Biome> biomeKey = RegistryKey.of(Registry.BIOME_KEY, identifier);

		BuiltinRegistries.add(BuiltinRegistries.BIOME, identifier, biome);
		biomes.put(name, biomeKey);

		return biomeKey;
	}
}
