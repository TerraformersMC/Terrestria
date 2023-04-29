package com.terraformersmc.terrestria.init.helpers;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;

public class StoneBlocks {
	public StoneVariantBlocks plain;
	public StoneVariantBlocks smooth;
	public StoneVariantBlocks cobblestone;
	public StoneVariantBlocks mossyCobblestone;
	public StoneVariantBlocks bricks;
	public StoneVariantBlocks mossyBricks;

	public ButtonBlock button;
	public PressurePlateBlock pressurePlate;
	public Block chiseledBricks;
	public Block crackedBricks;

	private StoneBlocks() {}

	public static StoneBlocks register(String name, MapColor color) {
		StoneBlocks blocks = new StoneBlocks();

		blocks.plain = StoneVariantBlocks.register(name, color, Blocks.STONE);
		blocks.smooth = StoneVariantBlocks.register("smooth_" + name, color, Blocks.SMOOTH_STONE);
		blocks.cobblestone = StoneVariantBlocks.register(name + "_cobblestone", color, Blocks.COBBLESTONE);
		blocks.mossyCobblestone = StoneVariantBlocks.register("mossy_" + name + "_cobblestone", color, Blocks.MOSSY_COBBLESTONE);
		blocks.bricks = StoneVariantBlocks.register(name + "_bricks", name + "_brick", color, Blocks.STONE_BRICKS);
		blocks.mossyBricks = StoneVariantBlocks.register("mossy_" + name + "_bricks", "mossy_" + name + "_brick", color, Blocks.MOSSY_STONE_BRICKS);

		blocks.button = TerrestriaRegistry.register(name + "_button", new ButtonBlock(FabricBlockSettings.copyOf(Blocks.STONE_BUTTON).mapColor(color), BlockSetType.STONE, 20, false));
		blocks.pressurePlate = TerrestriaRegistry.register(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(Blocks.STONE_PRESSURE_PLATE).mapColor(color), BlockSetType.STONE));
		blocks.chiseledBricks = TerrestriaRegistry.register("chiseled_" + name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.CRACKED_STONE_BRICKS).mapColor(color)));
		blocks.crackedBricks = TerrestriaRegistry.register("cracked_" + name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.CHISELED_STONE_BRICKS).mapColor(color)));

		return blocks;
	}
}
