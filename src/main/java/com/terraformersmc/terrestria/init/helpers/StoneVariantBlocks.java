package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.block.TerraformStairsBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;

public class StoneVariantBlocks {
	public Block full;
	public SlabBlock slab;
	public TerraformStairsBlock stairs;
	public WallBlock wall;

	private StoneVariantBlocks() {}

	public static StoneVariantBlocks register(String name, MaterialColor color) {
		return register(name, name, color);
	}

	public static StoneVariantBlocks register(String name, String shapedName, MaterialColor color) {
		StoneVariantBlocks blocks = new StoneVariantBlocks();

		blocks.full = TerrestriaRegistry.register(name, new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).materialColor(color)));
		blocks.slab = TerrestriaRegistry.register(shapedName + "_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_STAIRS).materialColor(color)));
		blocks.stairs = TerrestriaRegistry.register(shapedName + "_stairs", new TerraformStairsBlock(blocks.full, FabricBlockSettings.copyOf(Blocks.COBBLESTONE_STAIRS).materialColor(color)));
		blocks.wall = TerrestriaRegistry.register(shapedName + "_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_WALL).materialColor(color)));

		return blocks;
	}
}
