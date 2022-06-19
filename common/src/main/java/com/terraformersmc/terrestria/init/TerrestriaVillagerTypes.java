package com.terraformersmc.terrestria.init;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.VillagerType;
import net.minecraft.world.biome.Biome;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerTypeHelper;

import static com.terraformersmc.terrestria.init.TerrestriaBiomes.*;

public class TerrestriaVillagerTypes {
	public static void init() {
		//register(VillagerType.TAIGA, CALDERA, CALDERA_BEACH, CALDERA_FOOTHILLS, CALDERA_RIDGE);
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
		//register(VillagerType.DESERT, VOLCANIC_ISLAND, VOLCANIC_ISLAND_BEACH, VOLCANIC_ISLAND_SHORE);
		//register(VillagerType.DESERT, CANYON_ARCHES, CANYON_CLIFFS, CANYON_EDGE);
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
