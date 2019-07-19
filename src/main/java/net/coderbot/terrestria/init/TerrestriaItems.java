package net.coderbot.terrestria.init;

import net.coderbot.terrestria.init.helpers.TerrestriaRegistry;
import net.coderbot.terrestria.init.helpers.WoodItems;
import net.minecraft.item.BlockItem;

// This class exports public item constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaItems {
	public static WoodItems REDWOOD;
	public static WoodItems HEMLOCK;
	public static WoodItems RUBBER;
	public static WoodItems CYPRESS;
	public static WoodItems WILLOW;
	public static WoodItems JAPANESE_MAPLE;
	public static WoodItems RAINBOW_EUCALYPTUS;
	public static WoodItems SAKURA;

	public static BlockItem JAPANESE_MAPLE_SHRUB_LEAVES;
	public static BlockItem DARK_JAPANESE_MAPLE_LEAVES;
	public static BlockItem SAKURA_LEAF_PILE;

	public static BlockItem CATTAIL;

	public static BlockItem REDWOOD_QUARTER_LOG;
	public static BlockItem HEMLOCK_QUARTER_LOG;
	public static BlockItem CYPRESS_QUARTER_LOG;
	public static BlockItem RAINBOW_EUCALYPTUS_QUARTER_LOG;
	public static BlockItem STRIPPED_REDWOOD_QUARTER_LOG;
	public static BlockItem STRIPPED_HEMLOCK_QUARTER_LOG;
	public static BlockItem STRIPPED_CYPRESS_QUARTER_LOG;
	public static BlockItem STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG;

	public static BlockItem REDWOOD_SAPLING;
	public static BlockItem HEMLOCK_SAPLING;
	public static BlockItem RUBBER_SAPLING;
	public static BlockItem CYPRESS_SAPLING;
	public static BlockItem WILLOW_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SHRUB_SAPLING;
	public static BlockItem DARK_JAPANESE_MAPLE_SAPLING;
	public static BlockItem RAINBOW_EUCALYPTUS_SAPLING;
	public static BlockItem SAKURA_SAPLING;
	public static BlockItem PALM_SAPLING;

	public static BlockItem BASALT;
	public static BlockItem BASALT_SAND;
	public static BlockItem BASALT_DIRT;
	public static BlockItem BASALT_GRASS_BLOCK;
	public static BlockItem INDIAN_PAINTBRUSH;
	public static BlockItem MONSTERAS;

	public static void init() {
		REDWOOD = WoodItems.register("redwood", TerrestriaBlocks.REDWOOD);
		HEMLOCK = WoodItems.register("hemlock", TerrestriaBlocks.HEMLOCK);
		RUBBER = WoodItems.register("rubber", TerrestriaBlocks.RUBBER);
		CYPRESS = WoodItems.register("cypress", TerrestriaBlocks.CYPRESS);
		WILLOW = WoodItems.register("willow", TerrestriaBlocks.WILLOW);
		JAPANESE_MAPLE = WoodItems.register("japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE);
		RAINBOW_EUCALYPTUS = WoodItems.register("rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS);
		SAKURA = WoodItems.register("sakura", TerrestriaBlocks.SAKURA);
		SAKURA.wood = SAKURA.log;

		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.registerBlockItem("japanese_maple_shrub_leaves", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES);
		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.registerBlockItem("dark_japanese_maple_leaves", TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES);
		SAKURA_LEAF_PILE = TerrestriaRegistry.registerBlockItem("sakura_leaf_pile", TerrestriaBlocks.SAKURA_LEAF_PILE);

		CATTAIL = TerrestriaRegistry.registerBlockItem("cattail", TerrestriaBlocks.CATTAIL);

		REDWOOD_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("redwood_quarter_log", TerrestriaBlocks.REDWOOD_QUARTER_LOG);
		HEMLOCK_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("hemlock_quarter_log", TerrestriaBlocks.HEMLOCK_QUARTER_LOG);
		CYPRESS_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("cypress_quarter_log", TerrestriaBlocks.CYPRESS_QUARTER_LOG);
		RAINBOW_EUCALYPTUS_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("rainbow_eucalyptus_quarter_log", TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG);

		STRIPPED_REDWOOD_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("stripped_redwood_quarter_log", TerrestriaBlocks.STRIPPED_REDWOOD_QUARTER_LOG);
		STRIPPED_HEMLOCK_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("stripped_hemlock_quarter_log", TerrestriaBlocks.STRIPPED_HEMLOCK_QUARTER_LOG);
		STRIPPED_CYPRESS_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("stripped_cypress_quarter_log", TerrestriaBlocks.STRIPPED_CYPRESS_QUARTER_LOG);
		STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG = TerrestriaRegistry.registerBlockItem("stripped_rainbow_eucalyptus_quarter_log", TerrestriaBlocks.STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG);

		REDWOOD_SAPLING = TerrestriaRegistry.registerBlockItem("redwood_sapling", TerrestriaBlocks.REDWOOD_SAPLING);
		HEMLOCK_SAPLING = TerrestriaRegistry.registerBlockItem("hemlock_sapling", TerrestriaBlocks.HEMLOCK_SAPLING);
		RUBBER_SAPLING = TerrestriaRegistry.registerBlockItem("rubber_sapling", TerrestriaBlocks.RUBBER_SAPLING);
		CYPRESS_SAPLING = TerrestriaRegistry.registerBlockItem("cypress_sapling", TerrestriaBlocks.CYPRESS_SAPLING);
		WILLOW_SAPLING = TerrestriaRegistry.registerBlockItem("willow_sapling", TerrestriaBlocks.WILLOW_SAPLING);
		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlockItem("japanese_maple_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.registerBlockItem("japanese_maple_shrub_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlockItem("dark_japanese_maple_sapling", TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING);
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.registerBlockItem("rainbow_eucalyptus_sapling", TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		SAKURA_SAPLING = TerrestriaRegistry.registerBlockItem("sakura_sapling", TerrestriaBlocks.SAKURA_SAPLING);
		PALM_SAPLING = TerrestriaRegistry.registerBlockItem("palm_sapling", TerrestriaBlocks.PALM_SAPLING);

		BASALT = TerrestriaRegistry.registerBlockItem("basalt", TerrestriaBlocks.BASALT);
		BASALT_SAND = TerrestriaRegistry.registerBlockItem("basalt_sand", TerrestriaBlocks.BASALT_SAND);
		BASALT_DIRT = TerrestriaRegistry.registerBlockItem("basalt_dirt", TerrestriaBlocks.BASALT_DIRT);
		BASALT_GRASS_BLOCK = TerrestriaRegistry.registerBlockItem("basalt_grass_block", TerrestriaBlocks.BASALT_GRASS_BLOCK);
		INDIAN_PAINTBRUSH = TerrestriaRegistry.registerBlockItem("indian_paintbrush", TerrestriaBlocks.INDIAN_PAINTBRUSH);
		MONSTERAS = TerrestriaRegistry.registerBlockItem("monsteras", TerrestriaBlocks.MONSTERAS);
	}
}
