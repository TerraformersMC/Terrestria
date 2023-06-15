package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriaRegistry {
	@SuppressWarnings("UnnecessaryReturnStatement")
	public TerrestriaRegistry() {
		return;
	}

	public static BlockItem registerBuildingBlockItem(String name, Block block) {
		return registerBlockItem(name, block, ItemGroup.BUILDING_BLOCKS);
	}

	public static BlockItem registerDecorationBlockItem(String name, Block block) {
		return registerBlockItem(name, block, ItemGroup.DECORATIONS);
	}

	public static BlockItem registerRedstoneBlockItem(String name, Block block) {
		return registerBlockItem(name, block, ItemGroup.REDSTONE);
	}

	public static BlockItem registerBlockItem(String name, Block block, ItemGroup itemGroup) {
		BlockItem item = new BlockItem(block, new Item.Settings().group(itemGroup));
		item.appendBlocks(Item.BLOCK_ITEMS, item);

		return Registry.register(Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), item);
	}

	public static SignItem registerSignItem(String name, Block standing, Block wall) {
		return Registry.register(Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), new SignItem(new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(16), standing, wall));
	}

	public static <I extends Item> I registerItem(String name, I item) {
		return Registry.register(Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), item);
	}

	public static <B extends Block> B register(String name, B block) {
		return Registry.register(Registry.BLOCK, new Identifier(Terrestria.MOD_ID, name), block);
	}
}
