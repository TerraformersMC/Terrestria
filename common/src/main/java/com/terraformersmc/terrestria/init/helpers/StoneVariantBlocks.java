package com.terraformersmc.terrestria.init.helpers;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;

public class StoneVariantBlocks {
	public Block full;
	public SlabBlock slab;
	public StairsBlock stairs;
	public WallBlock wall;

	private StoneVariantBlocks() {}

	public static StoneVariantBlocks register(String name, MapColor color) {
		return register(name, name, color);
	}

	public static StoneVariantBlocks register(String name, String shapedName, MapColor color) {
		StoneVariantBlocks blocks = new StoneVariantBlocks();

		blocks.full = TerrestriaRegistry.register(name, new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(color)));
		blocks.slab = TerrestriaRegistry.register(shapedName + "_slab", new SlabBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_STAIRS).mapColor(color)));
		blocks.stairs = TerrestriaRegistry.register(shapedName + "_stairs", new StairsBlock(blocks.full.getDefaultState(), FabricBlockSettings.copyOf(Blocks.COBBLESTONE_STAIRS).mapColor(color)));
		blocks.wall = TerrestriaRegistry.register(shapedName + "_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_WALL).mapColor(color)));

		return blocks;
	}
}
