package net.coderbot.terrestria.init;

import net.coderbot.terrestria.Terrestria;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriaItems {
	public static WoodItems RUBBER;
	public static WoodItems CYPRESS;
	public static WoodItems BALD_CYPRESS;
	public static WoodItems JAPANESE_MAPLE;
	public static WoodItems RAINBOW_EUCALYPTUS;
	public static WoodItems SAKURA;

	public static BlockItem JAPANESE_MAPLE_SHRUB_LEAVES;
	public static BlockItem SAKURA_LEAF_PILE;

	public static BlockItem RUBBER_SAPLING;
	public static BlockItem CYPRESS_SAPLING;
	public static BlockItem BALD_CYPRESS_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SHRUB_SAPLING;
	public static BlockItem RAINBOW_EUCALYPTUS_SAPLING;
	public static BlockItem SAKURA_SAPLING;

	public static void init() {
		RUBBER = WoodItems.register("rubber", TerrestriaBlocks.RUBBER);
		CYPRESS = WoodItems.register("cypress", TerrestriaBlocks.CYPRESS);
		BALD_CYPRESS = WoodItems.register("bald_cypress", TerrestriaBlocks.BALD_CYPRESS);
		JAPANESE_MAPLE = WoodItems.register("japanese_maple", TerrestriaBlocks.JAPANESE_MAPLE);
		RAINBOW_EUCALYPTUS = WoodItems.register("rainbow_eucalyptus", TerrestriaBlocks.RAINBOW_EUCALYPTUS);
		SAKURA = WoodItems.register("sakura", TerrestriaBlocks.SAKURA);

		JAPANESE_MAPLE_SHRUB_LEAVES = register("japanese_maple_shrub_leaves", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES);
		SAKURA_LEAF_PILE = register("sakura_leaf_pile", TerrestriaBlocks.SAKURA_LEAF_PILE);

		RUBBER_SAPLING = register("rubber_sapling", TerrestriaBlocks.RUBBER_SAPLING);
		CYPRESS_SAPLING = register("cypress_sapling", TerrestriaBlocks.CYPRESS_SAPLING);
		BALD_CYPRESS_SAPLING = register("bald_cypress_sapling", TerrestriaBlocks.BALD_CYPRESS_SAPLING);
		JAPANESE_MAPLE_SAPLING = register("japanese_maple_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		JAPANESE_MAPLE_SHRUB_SAPLING = register("japanese_maple_shrub_sapling", TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		RAINBOW_EUCALYPTUS_SAPLING = register("rainbow_eucalyptus_sapling", TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		SAKURA_SAPLING = register("sakura_sapling", TerrestriaBlocks.SAKURA_SAPLING);
	}

	private static BlockItem register(String name, Block block) {
		return Registry.register(Registry.ITEM, new Identifier("terrestria", name), new BlockItem(block, new Item.Settings().itemGroup(Terrestria.ITEM_GROUP)));
	}

	public static class WoodItems {
		public BlockItem log;
		public BlockItem leaves;
		public BlockItem planks;
		public BlockItem slab;
		public BlockItem stairs;
		public BlockItem fence;
		public BlockItem fenceGate;
		public BlockItem door;

		private WoodItems() {}

		public static WoodItems register(String name, TerrestriaBlocks.WoodBlocks blocks) {
			WoodItems items = new WoodItems();

			items.log = TerrestriaItems.register(name + "_log", blocks.log);
			items.leaves = TerrestriaItems.register(name + "_leaves", blocks.leaves);
			items.planks = TerrestriaItems.register(name + "_planks", blocks.planks);
			items.slab = TerrestriaItems.register(name + "_slab", blocks.slab);
			items.stairs = TerrestriaItems.register(name + "_stairs", blocks.stairs);
			items.fence = TerrestriaItems.register(name + "_fence", blocks.fence);
			items.fenceGate = TerrestriaItems.register(name + "_fence_gate", blocks.fenceGate);
			items.door = TerrestriaItems.register(name + "_door", blocks.door);


			return items;
		}
	}
}
