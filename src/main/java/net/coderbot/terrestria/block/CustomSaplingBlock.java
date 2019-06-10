package net.coderbot.terrestria.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class CustomSaplingBlock extends SaplingBlock {
	public CustomSaplingBlock(SaplingGenerator generator) {
		super(generator, Block.Settings.copy(Blocks.OAK_SAPLING));
	}
}
