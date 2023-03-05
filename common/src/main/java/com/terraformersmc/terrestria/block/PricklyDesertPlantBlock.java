package com.terraformersmc.terrestria.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PricklyDesertPlantBlock extends TerraformDesertPlantBlock {

	public PricklyDesertPlantBlock(Settings settings) {
		super(settings, false);
	}

	public PricklyDesertPlantBlock(Settings settings, boolean onlySand) {
		super(settings, onlySand);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(world.getDamageSources().cactus(), 1.0f);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}
}
