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

	public StoneButtonBlock button;
	public PressurePlateBlock pressurePlate;
	public Block chiseledBricks;
	public Block crackedBricks;

	private StoneBlocks() {}

	public static StoneBlocks register(String name, MapColor color) {
		StoneBlocks blocks = new StoneBlocks();

		// TODO: Need to differentiate stone and cobblestone, because cobblestone breaks a bit slower!
		blocks.plain = StoneVariantBlocks.register(name, color);
		blocks.smooth = StoneVariantBlocks.register("smooth_" + name, color);
		blocks.cobblestone = StoneVariantBlocks.register(name + "_cobblestone", color);
		blocks.mossyCobblestone = StoneVariantBlocks.register("mossy_" + name + "_cobblestone", color);
		blocks.bricks = StoneVariantBlocks.register(name + "_bricks", name + "_brick", color);
		blocks.mossyBricks = StoneVariantBlocks.register("mossy_" + name + "_bricks", "mossy_" + name + "_brick", color);

		blocks.button = TerrestriaRegistry.register(name + "_button", new StoneButtonBlock(FabricBlockSettings.copyOf(Blocks.STONE).mapColor(color)));
		blocks.pressurePlate = TerrestriaRegistry.register(name + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(Blocks.STONE).mapColor(color)));
		blocks.chiseledBricks = TerrestriaRegistry.register("chiseled_" + name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.STONE).mapColor(color)));
		blocks.crackedBricks = TerrestriaRegistry.register("cracked_" + name + "_bricks", new Block(FabricBlockSettings.copyOf(Blocks.STONE).mapColor(color)));

		return blocks;
	}
}
