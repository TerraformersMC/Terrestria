package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.leaves.api.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;

import java.util.Optional;

public class TerrestriaOptiLeavesBlock extends ExtendedLeavesBlock {
	public TerrestriaOptiLeavesBlock(Properties settings) {
		super(0.01f, Optional.empty(), true, true, settings);
	}

	@Override
	public boolean skipRendering(BlockState state, BlockState neighborState, Direction offset) {
		return Terrestria.getConfigManager().getClientConfig().isOptiLeavesEnabled() && super.skipRendering(state, neighborState, offset);
	}
}
