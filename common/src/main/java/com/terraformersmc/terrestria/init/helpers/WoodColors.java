package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.world.level.material.MapColor;

public class WoodColors {
	public static final WoodColors REDWOOD;
	public static final WoodColors HEMLOCK;
	public static final WoodColors RUBBER;
	public static final WoodColors CYPRESS;
	public static final WoodColors WILLOW;
	public static final WoodColors JAPANESE_MAPLE;
	public static final WoodColors RAINBOW_EUCALYPTUS;
	public static final WoodColors SAKURA;
	public static final WoodColors YUCCA_PALM;

	static {
		REDWOOD = new WoodColors();
		REDWOOD.bark = MapColor.TERRACOTTA_RED;
		REDWOOD.planks = MapColor.WOOD;

		HEMLOCK = new WoodColors();
		HEMLOCK.bark = MapColor.COLOR_BROWN;
		HEMLOCK.planks = MapColor.WOOD;

		RUBBER = new WoodColors();
		RUBBER.bark = MapColor.TERRACOTTA_WHITE;
		RUBBER.planks = MapColor.SAND;

		CYPRESS = new WoodColors();
		CYPRESS.bark = MapColor.TERRACOTTA_WHITE;
		CYPRESS.planks = MapColor.COLOR_LIGHT_GRAY;

		WILLOW = new WoodColors();
		WILLOW.bark = MapColor.TERRACOTTA_WHITE;
		WILLOW.planks = MapColor.COLOR_GRAY;
		WILLOW.leaves = MapColor.COLOR_LIGHT_GRAY;

		JAPANESE_MAPLE = new WoodColors();
		JAPANESE_MAPLE.bark = MapColor.COLOR_BROWN;
		JAPANESE_MAPLE.planks = MapColor.TERRACOTTA_MAGENTA;
		JAPANESE_MAPLE.leaves = MapColor.COLOR_RED;

		RAINBOW_EUCALYPTUS = new WoodColors();
		RAINBOW_EUCALYPTUS.bark = MapColor.COLOR_BLUE;
		RAINBOW_EUCALYPTUS.planks = MapColor.LAPIS;

		SAKURA = new WoodColors();
		SAKURA.bark = MapColor.PODZOL;
		SAKURA.planks = MapColor.COLOR_BROWN;
		SAKURA.leaves = MapColor.COLOR_PINK;

		YUCCA_PALM = new WoodColors();
		YUCCA_PALM.bark = MapColor.COLOR_GRAY;
		YUCCA_PALM.planks = MapColor.TERRACOTTA_YELLOW;
		YUCCA_PALM.leaves = MapColor.COLOR_LIGHT_GRAY;
	}

	public MapColor bark;
	public MapColor planks;
	public MapColor leaves = MapColor.PLANT;
}
