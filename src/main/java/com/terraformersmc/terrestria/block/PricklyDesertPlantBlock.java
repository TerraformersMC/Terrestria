package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.block.TerraformDesertPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
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
		entity.damage(DamageSource.CACTUS, 1.0F);
	}
}
