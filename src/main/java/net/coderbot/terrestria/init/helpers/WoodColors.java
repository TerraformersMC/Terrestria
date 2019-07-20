package net.coderbot.terrestria.init.helpers;

import net.minecraft.block.MaterialColor;

public class WoodColors {
	public static final WoodColors REDWOOD;
	public static final WoodColors HEMLOCK;
	public static final WoodColors RUBBER;
	public static final WoodColors CYPRESS;
	public static final WoodColors WILLOW;
	public static final WoodColors JAPANESE_MAPLE;
	public static final WoodColors RAINBOW_EUCALYPTUS;
	public static final WoodColors SAKURA;

	static {
		REDWOOD = new WoodColors();
		REDWOOD.bark = MaterialColor.RED_TERRACOTTA;
		REDWOOD.planks = MaterialColor.WOOD;

		HEMLOCK = new WoodColors();
		HEMLOCK.bark = MaterialColor.BROWN;
		HEMLOCK.planks = MaterialColor.WOOD;

		RUBBER = new WoodColors();
		RUBBER.bark = MaterialColor.WHITE_TERRACOTTA;
		RUBBER.planks = MaterialColor.SAND;

		CYPRESS = new WoodColors();
		CYPRESS.bark = MaterialColor.WHITE_TERRACOTTA;
		CYPRESS.planks = MaterialColor.LIGHT_GRAY;

		WILLOW = new WoodColors();
		WILLOW.bark = MaterialColor.WHITE_TERRACOTTA;
		WILLOW.planks = MaterialColor.GRAY;
		WILLOW.leaves = MaterialColor.LIGHT_GRAY;

		JAPANESE_MAPLE = new WoodColors();
		JAPANESE_MAPLE.bark = MaterialColor.BROWN;
		JAPANESE_MAPLE.planks = MaterialColor.MAGENTA_TERRACOTTA;
		JAPANESE_MAPLE.leaves = MaterialColor.RED;

		RAINBOW_EUCALYPTUS = new WoodColors();
		RAINBOW_EUCALYPTUS.bark = MaterialColor.BLUE;
		RAINBOW_EUCALYPTUS.planks = MaterialColor.LAPIS;

		SAKURA = new WoodColors();
		SAKURA.bark = MaterialColor.SPRUCE;
		SAKURA.planks = MaterialColor.BROWN;
		SAKURA.leaves = MaterialColor.PINK;
	}

	public MaterialColor bark;
	public MaterialColor planks;
	public MaterialColor leaves = MaterialColor.FOLIAGE;
}
