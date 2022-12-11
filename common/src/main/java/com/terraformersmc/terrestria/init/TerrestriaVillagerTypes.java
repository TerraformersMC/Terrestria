package com.terraformersmc.terrestria.init;

import net.minecraft.registry.RegistryKey;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaVillagerTypes {
	public static void init() {
		register(VillagerType.TAIGA, CALDERA);
		// Plains: Cypress Forest
		register(VillagerType.SWAMP, CYPRESS_SWAMP);
		// Plains: Dense Woodlands
		// Plains: Hemlock Rainforest
		// Plains: Japanese Maple Forest
		// Plains: Lush Redwood Forest
		register(VillagerType.JUNGLE, RAINBOW_RAINFOREST);
		// Plains: Redwood Forest
		// Plains: Sakura Forest
		register(VillagerType.TAIGA, SNOWY_HEMLOCK_FOREST);
		register(VillagerType.JUNGLE, VOLCANIC_ISLAND);
		register(VillagerType.DESERT, CANYON);
		register(VillagerType.DESERT, DUNES);
		register(VillagerType.DESERT, LUSH_DESERT);
		register(VillagerType.DESERT, OASIS);
		register(VillagerType.DESERT, OUTBACK);
	}

	@SafeVarargs
	private static void register(VillagerType type, RegistryKey<Biome>... biomes) {
		for (RegistryKey<Biome> biome : biomes) {
			VillagerTypeHelper.addVillagerTypeToBiome(biome, type);
		}
	}
}
