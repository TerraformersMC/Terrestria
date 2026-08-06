package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.biome.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.List;

public class TerrestriaBiomes {
	public static final ResourceKey<Biome> CALDERA = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "caldera"));
	public static final ResourceKey<Biome> CANYON = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "canyon"));
	public static final ResourceKey<Biome> CYPRESS_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "cypress_forest"));
	public static final ResourceKey<Biome> CYPRESS_SWAMP = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "cypress_swamp"));
	public static final ResourceKey<Biome> DENSE_WOODLANDS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "dense_woodlands"));
	public static final ResourceKey<Biome> DUNES = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "dunes"));
	public static final ResourceKey<Biome> HEMLOCK_RAINFOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "hemlock_rainforest"));
	public static final ResourceKey<Biome> HEMLOCK_TREELINE = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "hemlock_treeline"));
	public static final ResourceKey<Biome> JAPANESE_MAPLE_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "japanese_maple_forest"));
	public static final ResourceKey<Biome> LUSH_DESERT = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "lush_desert"));
	public static final ResourceKey<Biome> LUSH_REDWOOD_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "lush_redwood_forest"));
	public static final ResourceKey<Biome> OASIS = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "oasis"));
	public static final ResourceKey<Biome> OUTBACK = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "outback"));
	public static final ResourceKey<Biome> RAINBOW_RAINFOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "rainbow_rainforest"));
	public static final ResourceKey<Biome> REDWOOD_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "redwood_forest"));
	public static final ResourceKey<Biome> SAKURA_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "sakura_forest"));
	public static final ResourceKey<Biome> SNOWY_HEMLOCK_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "snowy_hemlock_forest"));
	public static final ResourceKey<Biome> SNOWY_HEMLOCK_TREELINE = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "snowy_hemlock_treeline"));
	public static final ResourceKey<Biome> VOLCANIC_ISLAND = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "volcanic_island"));
	public static final ResourceKey<Biome> WINDSWEPT_REDWOOD_FOREST = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "windswept_redwood_forest"));

	public static final List<ResourceKey<Biome>> BIOMES = List.of(
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

	public static void bootstrap(BootstrapContext<Biome> context) {
		context.register(CALDERA, CalderaBiomes.create(context));
		context.register(CANYON, CanyonBiomes.create(context));
		context.register(CYPRESS_FOREST, CypressForestBiomes.create(context));
		context.register(CYPRESS_SWAMP, CypressSwampBiomes.create(context));
		context.register(DENSE_WOODLANDS, DenseWoodlandsBiomes.create(context));
		context.register(DUNES, DunesBiomes.create(context));
		context.register(HEMLOCK_RAINFOREST, HemlockRainforestBiomes.create(context, false));
		context.register(HEMLOCK_TREELINE, HemlockRainforestBiomes.create(context, true));
		context.register(JAPANESE_MAPLE_FOREST, JapaneseMapleForestBiomes.create(context));
		context.register(LUSH_DESERT, LushDesertBiomes.create(context, false));
		context.register(LUSH_REDWOOD_FOREST, LushRedwoodForestBiomes.create(context));
		context.register(OASIS, LushDesertBiomes.create(context, true));
		context.register(OUTBACK, OutbackBiomes.create(context));
		context.register(RAINBOW_RAINFOREST, RainbowRainforestBiomes.create(context));
		context.register(REDWOOD_FOREST, RedwoodForestBiomes.create(context, false));
		context.register(SAKURA_FOREST, SakuraForestBiomes.create(context));
		context.register(SNOWY_HEMLOCK_FOREST, SnowyHemlockRainforestBiomes.create(context, false));
		context.register(SNOWY_HEMLOCK_TREELINE, SnowyHemlockRainforestBiomes.create(context, true));
		context.register(VOLCANIC_ISLAND, VolcanicIslandBiomes.create(context));
		context.register(WINDSWEPT_REDWOOD_FOREST, RedwoodForestBiomes.create(context, true));
	}

	public static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings, boolean lavaSprings) {
		BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
		BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
		BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
		if (lavaSprings) {
			BiomeDefaultFeatures.addDefaultSprings(generationSettings);
		} else {
			generationSettings.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, MiscOverworldPlacements.SPRING_WATER);
		}
		BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
	}

	public static MobSpawnSettings.Builder createDefaultSpawnSettings() {
		MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
		addDefaultCreatureSpawnEntries(spawnSettings);
		addDefaultCaveSpawnEntries(spawnSettings);
		addDefaultMonsterSpawnEntries(spawnSettings);
		return spawnSettings;
	}

	public static void addDefaultCreatureSpawnEntries(MobSpawnSettings.Builder builder) {
		builder.addSpawn(MobCategory.CREATURE, 12, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 4, 4));
		builder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 4, 4));
		builder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 4, 4));
		builder.addSpawn(MobCategory.CREATURE,  8, new MobSpawnSettings.SpawnerData(EntityType.COW, 4, 4));
	}

	public static void addDefaultCaveSpawnEntries(MobSpawnSettings.Builder builder) {
		builder.addSpawn(MobCategory.AMBIENT, 10, new MobSpawnSettings.SpawnerData(EntityType.BAT, 8, 8));
		builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID, 4, 6));
	}

	public static void addDefaultMonsterSpawnEntries(MobSpawnSettings.Builder builder) {
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 4, 4));
		builder.addSpawn(MobCategory.MONSTER,  95, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 4, 4));
		builder.addSpawn(MobCategory.MONSTER,   5, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 1, 1));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 4, 4));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 4, 4));
		builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 4, 4));
		builder.addSpawn(MobCategory.MONSTER,  10, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4));
		builder.addSpawn(MobCategory.MONSTER,   5, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 1, 1));
	}

	// Copied from Traverse
	public static BiomeSpecialEffects.Builder createDefaultBiomeEffects() {
		return new BiomeSpecialEffects.Builder()
			.waterColor(0x3F76E4);
	}

	public static EnvironmentAttributeMap.Builder createDefaultEnvironmentAttributes() {
		return EnvironmentAttributeMap.builder()
			.set(EnvironmentAttributes.WATER_FOG_COLOR, 0x50533)
			.set(EnvironmentAttributes.SKY_COLOR, getSkyColor(0.2F))
			.set(EnvironmentAttributes.FOG_COLOR, 0xC0D8FF);
	}

	// Copied from Minecraft
	private static int getSkyColor(float temperature) {
		float f = temperature / 3.0F;
		f = Mth.clamp(f, -1.0F, 1.0F);
		return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
	}
}
