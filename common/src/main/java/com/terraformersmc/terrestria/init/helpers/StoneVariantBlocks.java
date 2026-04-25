package com.terraformersmc.terrestria.init.helpers;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class StoneVariantBlocks {
	public Block full;
	public SlabBlock slab;
	public StairBlock stairs;
	public WallBlock wall;

	private StoneVariantBlocks() {}

	public static StoneVariantBlocks register(String name, MapColor color, Block family) {
		return register(name, name, color, family);
	}

	public static StoneVariantBlocks register(String name, String shapedName, MapColor color, Block family) {
		StoneVariantBlocks blocks = new StoneVariantBlocks();

		blocks.full = TerrestriaRegistry.register(name, Block::new, BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));
		blocks.slab = TerrestriaRegistry.register(shapedName + "_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));
		blocks.stairs = TerrestriaRegistry.register(shapedName + "_stairs", settings -> new StairBlock(blocks.full.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));
		blocks.wall = TerrestriaRegistry.register(shapedName + "_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));

		return blocks;
	}
}
