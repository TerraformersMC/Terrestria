package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.leaves.api.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class TerrestriaOptiLeavesBlock extends ExtendedLeavesBlock {
	public TerrestriaOptiLeavesBlock(Settings settings) {
		super(0.01f, Optional.empty(), true, true, settings);
	}

	@Override
	public boolean isSideInvisible(BlockState state, BlockState neighborState, Direction offset) {
		return Terrestria.getConfigManager().getClientConfig().isOptiLeavesEnabled() && super.isSideInvisible(state, neighborState, offset);
	}
}
