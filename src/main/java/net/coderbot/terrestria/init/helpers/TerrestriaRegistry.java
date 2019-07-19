package net.coderbot.terrestria.init.helpers;

import net.coderbot.terrestria.Terrestria;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriaRegistry {

	public static BlockItem registerBlockItem(String name, Block block) {
		return net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), new BlockItem(block, new Item.Settings().group(Terrestria.ITEM_GROUP)));
	}

	public static SignItem registerSign(String name, Block standing, Block wall) {
		return net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), new SignItem(new Item.Settings().group(Terrestria.ITEM_GROUP), standing, wall));
	}

	public static <T extends Block> T registerBlock(String name, T block) {
		return Registry.register(Registry.BLOCK, new Identifier(Terrestria.MOD_ID, name), block);
	}
}
