package net.coderbot.funwoods.init;

import net.coderbot.funwoods.Funwoods;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class FunwoodsItems {
	public static Item RUBBER_SAPLING;
	public static Item CYPRESS_SAPLING;

	public static void init() {
		RUBBER_SAPLING = register("funwoods:rubber_sapling", FunwoodsBlocks.RUBBER_SAPLING);
		CYPRESS_SAPLING = register("funwoods:cypress_sapling", FunwoodsBlocks.CYPRESS_SAPLING);
	}

	private static Item register(String name, Block block) {
		return Registry.register(Registry.ITEM, name, new BlockItem(block, new Item.Settings().itemGroup(Funwoods.ITEM_GROUP)));
	}
}
