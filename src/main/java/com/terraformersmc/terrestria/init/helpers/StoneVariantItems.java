package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.init.TerrestriaBlockItemIds;
import net.minecraft.world.item.BlockItem;

public class StoneVariantItems {
	public BlockItem full;
	public BlockItem slab;
	public BlockItem stairs;
	public BlockItem wall;

	private StoneVariantItems() {}

	public static StoneVariantItems register(TerrestriaBlockItemIds.StoneVariantBlockItemIds itemIds, StoneVariantBlocks blocks) {
		StoneVariantItems items = new StoneVariantItems();

		items.full = TerrestriaRegistry.registerBlockItem(itemIds.full(), blocks.full);
		items.slab = TerrestriaRegistry.registerBlockItem(itemIds.slab(), blocks.slab);
		items.stairs = TerrestriaRegistry.registerBlockItem(itemIds.stairs(), blocks.stairs);
		items.wall = TerrestriaRegistry.registerBlockItem(itemIds.wall(), blocks.wall);

		return items;
	}
}
