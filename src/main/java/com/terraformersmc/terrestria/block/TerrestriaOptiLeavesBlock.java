package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.leaves.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class TerrestriaOptiLeavesBlock extends ExtendedLeavesBlock {
	public TerrestriaOptiLeavesBlock(Settings settings) {
		super(settings);
	}

	@Override
	public boolean isSideInvisible(BlockState state, BlockState neighborState, Direction offset) {
		return Terrestria.getConfigManager().getClientConfig().isOptiLeavesEnabled() && super.isSideInvisible(state, neighborState, offset);
	}
}
