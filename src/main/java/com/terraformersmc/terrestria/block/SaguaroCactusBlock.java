package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.block.BareSmallLogBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class SaguaroCactusBlock extends BareSmallLogBlock {

	public SaguaroCactusBlock(Supplier<Block> stripped, Settings settings) {
		super(stripped, settings);
	}

	public void onEntityCollision(BlockState blockState, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0F);
	}
}
