package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.Map;

public final class TerrestriaRegistryAliases {
	@SuppressWarnings("UnnecessaryReturnStatement")
	private TerrestriaRegistryAliases() {
		return;
	}

	// Use Fabric registry aliases to repair identifier changes
	public static void init() {
		registerStatic();
	}

	private static void registerStatic() {
		// Blocks with identically ID'd items
		Map<Identifier, Identifier> BLOCKS_ITEMS = Map.ofEntries(
				entry("basalt_dirt",                     "andisol"),
				entry("basalt_grass_path",               "andisol_dirt_path"),
				entry("basalt_grass_block",              "andisol_grass_block"),
				entry("basalt_podzol",                   "andisol_podzol"),
				entry("chiseled_basalt_bricks",          "chiseled_volcanic_rock_bricks"),
				entry("cracked_basalt_bricks",           "cracked_volcanic_rock_bricks"),
				entry("mossy_basalt_brick_slab",         "mossy_volcanic_rock_brick_slab"),
				entry("mossy_basalt_brick_stairs",       "mossy_volcanic_rock_brick_stairs"),
				entry("mossy_basalt_brick_wall",         "mossy_volcanic_rock_brick_wall"),
				entry("mossy_basalt_bricks",             "mossy_volcanic_rock_bricks"),
				entry("mossy_basalt_cobblestone",        "mossy_volcanic_rock_cobblestone"),
				entry("mossy_basalt_cobblestone_slab",   "mossy_volcanic_rock_cobblestone_slab"),
				entry("mossy_basalt_cobblestone_stairs", "mossy_volcanic_rock_cobblestone_stairs"),
				entry("mossy_basalt_cobblestone_wall",   "mossy_volcanic_rock_cobblestone_wall"),
				entry("smooth_basalt",                   "smooth_volcanic_rock"),
				entry("smooth_basalt_slab",              "smooth_volcanic_rock_slab"),
				entry("smooth_basalt_stairs",            "smooth_volcanic_rock_stairs"),
				entry("smooth_basalt_wall",              "smooth_volcanic_rock_wall"),
				entry("basalt",                          "volcanic_rock"),
				entry("basalt_brick_slab",               "volcanic_rock_brick_slab"),
				entry("basalt_brick_stairs",             "volcanic_rock_brick_stairs"),
				entry("basalt_brick_wall",               "volcanic_rock_brick_wall"),
				entry("basalt_bricks",                   "volcanic_rock_bricks"),
				entry("basalt_button",                   "volcanic_rock_button"),
				entry("basalt_cobblestone",              "volcanic_rock_cobblestone"),
				entry("basalt_cobblestone_slab",         "volcanic_rock_cobblestone_slab"),
				entry("basalt_cobblestone_stairs",       "volcanic_rock_cobblestone_stairs"),
				entry("basalt_cobblestone_wall",         "volcanic_rock_cobblestone_wall"),
				entry("basalt_pressure_plate",           "volcanic_rock_pressure_plate"),
				entry("basalt_slab",                     "volcanic_rock_slab"),
				entry("basalt_stairs",                   "volcanic_rock_stairs"),
				entry("basalt_wall",                     "volcanic_rock_wall"),
				entry("basalt_sand",                     "volcanic_sand")
		);
		BLOCKS_ITEMS.forEach(BuiltInRegistries.BLOCK::addAlias);
		BLOCKS_ITEMS.forEach(BuiltInRegistries.ITEM::addAlias);
	}

	private static Map.Entry<Identifier, Identifier> entry(String oldName, String newName) {
		return Map.entry(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, oldName), Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, newName));
	}
}
