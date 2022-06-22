package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.item.BlockItem;

public class StoneItems {
	public StoneVariantItems plain;
	public StoneVariantItems smooth;
	public StoneVariantItems cobblestone;
	public StoneVariantItems mossyCobblestone;
	public StoneVariantItems bricks;
	public StoneVariantItems mossyBricks;

	public BlockItem button;
	public BlockItem pressurePlate;
	public BlockItem chiseledBricks;
	public BlockItem crackedBricks;

	private StoneItems() {}

	public static StoneItems register(String name, StoneBlocks blocks) {
		StoneItems items = new StoneItems();

		items.plain = StoneVariantItems.register(name, blocks.plain);
		items.smooth = StoneVariantItems.register("smooth_" + name, blocks.smooth);
		items.cobblestone = StoneVariantItems.register(name + "_cobblestone", blocks.cobblestone);
		items.mossyCobblestone = StoneVariantItems.register("mossy_" + name + "_cobblestone", blocks.mossyCobblestone);
		items.bricks = StoneVariantItems.register(name + "_bricks", name + "_brick", blocks.bricks);
		items.mossyBricks = StoneVariantItems.register("mossy_" + name + "_bricks", "mossy_" + name + "_brick", blocks.mossyBricks);

		items.button = TerrestriaRegistry.registerRedstoneBlockItem(name + "_button", blocks.button);
		items.pressurePlate = TerrestriaRegistry.registerRedstoneBlockItem(name + "_pressure_plate", blocks.pressurePlate);
		items.chiseledBricks = TerrestriaRegistry.registerBuildingBlockItem("chiseled_" + name + "_bricks", blocks.chiseledBricks);
		items.crackedBricks = TerrestriaRegistry.registerBuildingBlockItem("cracked_" + name + "_bricks", blocks.crackedBricks);

		return items;
	}
}
