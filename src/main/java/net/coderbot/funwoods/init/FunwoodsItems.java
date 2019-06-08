package net.coderbot.funwoods.init;

import net.coderbot.funwoods.Funwoods;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FunwoodsItems {
	public static WoodItems RUBBER;
	public static WoodItems CYPRESS;
	public static WoodItems BALD_CYPRESS;
	public static WoodItems JAPANESE_MAPLE;
	public static WoodItems RAINBOW_EUCALYPTUS;
	public static WoodItems SAKURA;

	public static BlockItem RUBBER_SAPLING;
	public static BlockItem CYPRESS_SAPLING;
	public static BlockItem BALD_CYPRESS_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SAPLING;
	public static BlockItem RAINBOW_EUCALYPTUS_SAPLING;
	public static BlockItem SAKURA_SAPLING;

	public static void init() {
		RUBBER = WoodItems.register("rubber", FunwoodsBlocks.RUBBER);
		CYPRESS = WoodItems.register("cypress", FunwoodsBlocks.CYPRESS);
		BALD_CYPRESS = WoodItems.register("bald_cypress", FunwoodsBlocks.BALD_CYPRESS);
		JAPANESE_MAPLE = WoodItems.register("japanese_maple", FunwoodsBlocks.JAPANESE_MAPLE);
		RAINBOW_EUCALYPTUS = WoodItems.register("rainbow_eucalyptus", FunwoodsBlocks.RAINBOW_EUCALYPTUS);
		SAKURA = WoodItems.register("sakura", FunwoodsBlocks.SAKURA);

		RUBBER_SAPLING = register("rubber_sapling", FunwoodsBlocks.RUBBER_SAPLING);
		CYPRESS_SAPLING = register("cypress_sapling", FunwoodsBlocks.CYPRESS_SAPLING);
		BALD_CYPRESS_SAPLING = register("bald_cypress_sapling", FunwoodsBlocks.BALD_CYPRESS_SAPLING);
		JAPANESE_MAPLE_SAPLING = register("japanese_maple_sapling", FunwoodsBlocks.JAPANESE_MAPLE_SAPLING);
		RAINBOW_EUCALYPTUS_SAPLING = register("rainbow_eucalyptus_sapling", FunwoodsBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		SAKURA_SAPLING = register("sakura_sapling", FunwoodsBlocks.SAKURA_SAPLING);
	}

	private static BlockItem register(String name, Block block) {
		return Registry.register(Registry.ITEM, new Identifier("funwoods", name), new BlockItem(block, new Item.Settings().itemGroup(Funwoods.ITEM_GROUP)));
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

		public static WoodItems register(String name, FunwoodsBlocks.WoodBlocks blocks) {
			WoodItems items = new WoodItems();

			items.log = FunwoodsItems.register(name + "_log", blocks.log);
			items.leaves = FunwoodsItems.register(name + "_leaves", blocks.leaves);
			items.planks = FunwoodsItems.register(name + "_planks", blocks.planks);
			items.slab = FunwoodsItems.register(name + "_slab", blocks.slab);
			items.stairs = FunwoodsItems.register(name + "_stairs", blocks.stairs);
			items.fence = FunwoodsItems.register(name + "_fence", blocks.fence);
			items.fenceGate = FunwoodsItems.register(name + "_fence_gate", blocks.fenceGate);
			items.door = FunwoodsItems.register(name + "_door", blocks.door);


			return items;
		}
	}
}
