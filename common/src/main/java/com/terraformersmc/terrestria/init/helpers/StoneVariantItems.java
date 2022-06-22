package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.item.BlockItem;

public class StoneVariantItems {
	public BlockItem full;
	public BlockItem slab;
	public BlockItem stairs;
	public BlockItem wall;

	private StoneVariantItems() {}

	public static StoneVariantItems register(String name, StoneVariantBlocks blocks) {
		return register(name, name, blocks);
	}

	public static StoneVariantItems register(String name, String shapedName, StoneVariantBlocks blocks) {
		StoneVariantItems items = new StoneVariantItems();

		items.full = TerrestriaRegistry.registerBuildingBlockItem(name, blocks.full);
		items.slab = TerrestriaRegistry.registerBuildingBlockItem(shapedName + "_slab", blocks.slab);
		items.stairs = TerrestriaRegistry.registerBuildingBlockItem(shapedName + "_stairs", blocks.stairs);
		items.wall = TerrestriaRegistry.registerDecorationBlockItem(shapedName + "_wall", blocks.wall);

		return items;
	}
}
