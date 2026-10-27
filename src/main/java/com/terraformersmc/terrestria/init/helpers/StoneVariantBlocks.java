package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terrestria.init.TerrestriaBlockIds;
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

	public static StoneVariantBlocks register(TerrestriaBlockIds.StoneVariantBlockIds stoneVariantBlockIds, MapColor color, Block family) {
		StoneVariantBlocks blocks = new StoneVariantBlocks();

		blocks.full = TerrestriaRegistry.register(stoneVariantBlockIds.full(), Block::new, BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));
		blocks.slab = TerrestriaRegistry.register(stoneVariantBlockIds.slab(), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));
		blocks.stairs = TerrestriaRegistry.register(stoneVariantBlockIds.stairs(), settings -> new StairBlock(blocks.full.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));
		blocks.wall = TerrestriaRegistry.register(stoneVariantBlockIds.wall(), WallBlock::new, BlockBehaviour.Properties.ofFullCopy(family).mapColor(color));

		return blocks;
	}
}
