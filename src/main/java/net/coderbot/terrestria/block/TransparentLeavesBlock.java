package net.coderbot.terrestria.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class TransparentLeavesBlock extends LeavesBlock {
	public TransparentLeavesBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public int getLightSubtracted(BlockState state, BlockView view, BlockPos pos) {
		return 0;
	}
}
