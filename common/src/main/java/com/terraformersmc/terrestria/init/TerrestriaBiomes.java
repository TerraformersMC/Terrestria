package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.biome.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class TerrestriaBiomes {
	public static final RegistryKey<Biome> CALDERA = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "caldera"));
	public static final RegistryKey<Biome> CANYON = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "canyon"));
	public static final RegistryKey<Biome> CYPRESS_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "cypress_forest"));
	public static final RegistryKey<Biome> CYPRESS_SWAMP = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "cypress_swamp"));
	public static final RegistryKey<Biome> DENSE_WOODLANDS = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "dense_woodlands"));
	public static final RegistryKey<Biome> DUNES = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "dunes"));
	public static final RegistryKey<Biome> HEMLOCK_RAINFOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "hemlock_rainforest"));
	public static final RegistryKey<Biome> HEMLOCK_TREELINE = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "hemlock_treeline"));
	public static final RegistryKey<Biome> JAPANESE_MAPLE_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "japanese_maple_forest"));
	public static final RegistryKey<Biome> LUSH_DESERT = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "lush_desert"));
	public static final RegistryKey<Biome> LUSH_REDWOOD_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "lush_redwood_forest"));
	public static final RegistryKey<Biome> OASIS = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "oasis"));
	public static final RegistryKey<Biome> OUTBACK = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "outback"));
	public static final RegistryKey<Biome> RAINBOW_RAINFOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "rainbow_rainforest"));
	public static final RegistryKey<Biome> REDWOOD_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "redwood_forest"));
	public static final RegistryKey<Biome> SAKURA_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "sakura_forest"));
	public static final RegistryKey<Biome> SNOWY_HEMLOCK_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "snowy_hemlock_forest"));
	public static final RegistryKey<Biome> SNOWY_HEMLOCK_TREELINE = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "snowy_hemlock_treeline"));
	public static final RegistryKey<Biome> VOLCANIC_ISLAND = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "volcanic_island"));
	public static final RegistryKey<Biome> WINDSWEPT_REDWOOD_FOREST = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Terrestria.MOD_ID, "windswept_redwood_forest"));

	public static final List<RegistryKey<Biome>> BIOMES = List.of(
			CALDERA,
			CANYON,
			CYPRESS_FOREST,
			CYPRESS_SWAMP,
			DENSE_WOODLANDS,
			DUNES,
			HEMLOCK_RAINFOREST,
			HEMLOCK_TREELINE,
			JAPANESE_MAPLE_FOREST,
			LUSH_DESERT,
			LUSH_REDWOOD_FOREST,
			OASIS,
			OUTBACK,
			RAINBOW_RAINFOREST,
			REDWOOD_FOREST,
			SAKURA_FOREST,
			SNOWY_HEMLOCK_FOREST,
			SNOWY_HEMLOCK_TREELINE,
			VOLCANIC_ISLAND,
			WINDSWEPT_REDWOOD_FOREST
	);

	public static void populate(FabricDynamicRegistryProvider.Entries entries) {
		entries.add(CALDERA, CalderaBiomes.create(entries));
		entries.add(CANYON, CanyonBiomes.create(entries));
		entries.add(CYPRESS_FOREST, CypressForestBiomes.create(entries));
		entries.add(CYPRESS_SWAMP, CypressSwampBiomes.create(entries));
		entries.add(DENSE_WOODLANDS, DenseWoodlandsBiomes.create(entries));
		entries.add(DUNES, DunesBiomes.create(entries));
		entries.add(HEMLOCK_RAINFOREST, HemlockRainforestBiomes.create(entries, false));
		entries.add(HEMLOCK_TREELINE, HemlockRainforestBiomes.create(entries, true));
		entries.add(JAPANESE_MAPLE_FOREST, JapaneseMapleForestBiomes.create(entries));
		entries.add(LUSH_DESERT, LushDesertBiomes.create(entries, false));
		entries.add(LUSH_REDWOOD_FOREST, LushRedwoodForestBiomes.create(entries));
		entries.add(OASIS, LushDesertBiomes.create(entries, true));
		entries.add(OUTBACK, OutbackBiomes.create(entries));
		entries.add(RAINBOW_RAINFOREST, RainbowRainforestBiomes.create(entries));
		entries.add(REDWOOD_FOREST, RedwoodForestBiomes.create(entries, false));
		entries.add(SAKURA_FOREST, SakuraForestBiomes.create(entries));
		entries.add(SNOWY_HEMLOCK_FOREST, HemlockRainforestBiomes.create(entries, false));
		entries.add(SNOWY_HEMLOCK_TREELINE, HemlockRainforestBiomes.create(entries, true));
		entries.add(VOLCANIC_ISLAND, VolcanicIslandBiomes.create(entries));
		entries.add(WINDSWEPT_REDWOOD_FOREST, RedwoodForestBiomes.create(entries, true));
	}

	public static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
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
		// This just creates the registry keys.  Biome objects are requested and consumed by datagen now.
	}
}
