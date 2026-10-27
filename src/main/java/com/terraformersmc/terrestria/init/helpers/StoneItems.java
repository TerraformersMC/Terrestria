package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.init.TerrestriaBlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;

public class StoneItems {
	private final TerrestriaBlockItemIds.StoneBlockItemIds itemIds;

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

	private StoneItems(TerrestriaBlockItemIds.StoneBlockItemIds itemIds, StoneBlocks blocks) {
		this.itemIds = itemIds;

		plain = StoneVariantItems.register(itemIds.plain(), blocks.plain);
		smooth = StoneVariantItems.register(itemIds.smooth(), blocks.smooth);
		cobblestone = StoneVariantItems.register(itemIds.cobblestone(), blocks.cobblestone);
		mossyCobblestone = StoneVariantItems.register(itemIds.mossyCobblestone(), blocks.mossyCobblestone);
		bricks = StoneVariantItems.register(itemIds.bricks(), blocks.bricks);
		mossyBricks = StoneVariantItems.register(itemIds.mossyBricks(), blocks.mossyBricks);

		button = TerrestriaRegistry.registerBlockItem(itemIds.button(), blocks.button);
		pressurePlate = TerrestriaRegistry.registerBlockItem(itemIds.pressurePlate(), blocks.pressurePlate);
		chiseledBricks = TerrestriaRegistry.registerBlockItem(itemIds.chiseledBricks(), blocks.chiseledBricks);
		crackedBricks = TerrestriaRegistry.registerBlockItem(itemIds.crackedBricks(), blocks.crackedBricks);
	}

	public static StoneItems register(TerrestriaBlockItemIds.StoneBlockItemIds itemIds, StoneBlocks blocks) {
		return new StoneItems(itemIds, blocks);
	}

	public String getName() {
		return itemIds.id().getPath();
	}

	public Identifier getId() {
		return itemIds.id();
	}
}
