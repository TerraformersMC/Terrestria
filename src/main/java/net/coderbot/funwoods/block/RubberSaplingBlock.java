package net.coderbot.funwoods.block;

import net.minecraft.block.SaplingBlock;

public class RubberSaplingBlock extends SaplingBlock {
	public RubberSaplingBlock(RubberSaplingGenerator.TreeDefinition definition, Settings block) {
		super(new RubberSaplingGenerator(definition), block);
	}
}
