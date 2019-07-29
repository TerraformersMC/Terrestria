package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.block.TerraformPressurePlateBlock;
import com.terraformersmc.terraform.block.TerraformStoneButtonBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PressurePlateBlock;

public class StoneBlocks {
	public StoneVariantBlocks plain;
	public StoneVariantBlocks smooth;
	public StoneVariantBlocks cobblestone;
	public StoneVariantBlocks mossyCobblestone;
	public StoneVariantBlocks bricks;
	public StoneVariantBlocks mossyBricks;

	public TerraformStoneButtonBlock button;
	public TerraformPressurePlateBlock pressurePlate;
	public Block chiseledBricks;
	public Block crackedBricks;

	private StoneBlocks() {}

	public static StoneBlocks register(String name, MaterialColor color) {
		StoneBlocks blocks = new StoneBlocks();

		blocks.plain = StoneVariantBlocks.register(name, color);
		blocks.smooth = StoneVariantBlocks.register("smooth_" + name, color);
		blocks.cobblestone = StoneVariantBlocks.register(name + "_cobblestone", color);
		blocks.mossyCobblestone = StoneVariantBlocks.register("mossy_" + name + "_cobblestone", color);
		blocks.bricks = StoneVariantBlocks.register(name + "_bricks", name + "_brick", color);
		blocks.mossyBricks = StoneVariantBlocks.register("mossy_" + name + "_bricks", "mossy_" + name + "_brick", color);

		blocks.button = TerrestriaRegistry.register(name + "_button", new TerraformStoneButtonBlock(FabricBlockSettings.copy(Blocks.STONE).materialColor(color).build()));
		blocks.pressurePlate = TerrestriaRegistry.register(name + "_pressure_plate", new TerraformPressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copy(Blocks.STONE).materialColor(color).build()));
		blocks.chiseledBricks = TerrestriaRegistry.register("chiseled_" + name + "_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE).materialColor(color).build()));
		blocks.crackedBricks = TerrestriaRegistry.register("cracked_" + name + "_bricks", new Block(FabricBlockSettings.copy(Blocks.STONE).materialColor(color).build()));

		return blocks;
	}
}
