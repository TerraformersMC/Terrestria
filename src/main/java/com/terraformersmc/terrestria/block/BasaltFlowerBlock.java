package com.terraformersmc.terrestria.block;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class BasaltFlowerBlock extends FlowerBlock {

	public BasaltFlowerBlock(StatusEffect stewEffect, int effectSeconds, Settings settings) {
		super(stewEffect, effectSeconds, settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		return Blocks.GRASS.getDefaultState().getOutlineShape(view, pos, context);
	}

	@Override
	protected boolean canPlantOnTop(BlockState state, BlockView world, BlockPos pos) {
		Block block = state.getBlock();

		return block == TerrestriaBlocks.BASALT_GRASS_BLOCK || block == TerrestriaBlocks.BASALT_DIRT || super.canPlantOnTop(state, world, pos);
	}
}
