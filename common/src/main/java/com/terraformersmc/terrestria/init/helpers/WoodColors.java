package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.block.MapColor;

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
		REDWOOD.planks = MapColor.OAK_TAN;

		HEMLOCK = new WoodColors();
		HEMLOCK.bark = MapColor.BROWN;
		HEMLOCK.planks = MapColor.OAK_TAN;

		RUBBER = new WoodColors();
		RUBBER.bark = MapColor.TERRACOTTA_WHITE;
		RUBBER.planks = MapColor.PALE_YELLOW;

		CYPRESS = new WoodColors();
		CYPRESS.bark = MapColor.TERRACOTTA_WHITE;
		CYPRESS.planks = MapColor.LIGHT_GRAY;

		WILLOW = new WoodColors();
		WILLOW.bark = MapColor.TERRACOTTA_WHITE;
		WILLOW.planks = MapColor.GRAY;
		WILLOW.leaves = MapColor.LIGHT_GRAY;

		JAPANESE_MAPLE = new WoodColors();
		JAPANESE_MAPLE.bark = MapColor.BROWN;
		JAPANESE_MAPLE.planks = MapColor.TERRACOTTA_MAGENTA;
		JAPANESE_MAPLE.leaves = MapColor.RED;

		RAINBOW_EUCALYPTUS = new WoodColors();
		RAINBOW_EUCALYPTUS.bark = MapColor.BLUE;
		RAINBOW_EUCALYPTUS.planks = MapColor.LAPIS_BLUE;

		SAKURA = new WoodColors();
		SAKURA.bark = MapColor.SPRUCE_BROWN;
		SAKURA.planks = MapColor.BROWN;
		SAKURA.leaves = MapColor.PINK;

		YUCCA_PALM = new WoodColors();
		YUCCA_PALM.bark = MapColor.GRAY;
		YUCCA_PALM.planks = MapColor.TERRACOTTA_YELLOW;
		YUCCA_PALM.leaves = MapColor.LIGHT_GRAY;
	}

	public MapColor bark;
	public MapColor planks;
	public MapColor leaves = MapColor.DARK_GREEN;
}
