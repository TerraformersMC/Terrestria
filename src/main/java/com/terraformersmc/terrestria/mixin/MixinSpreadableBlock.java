package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terraform.block.TerraformGrassBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(SpreadableBlock.class)
public abstract class MixinSpreadableBlock {
	@Shadow
	private static boolean canSpread(BlockState state, WorldView worldView, BlockPos pos) {
		return false;
	}

	@Inject(method = "scheduledTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;"), locals = LocalCapture.CAPTURE_FAILHARD)
	private void onDo(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info, BlockState defaultState, int i, BlockPos spreadingPos) {
		BlockState defaultBasaltGrassState = TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState();
		if (world.getBlockState(spreadingPos).getBlock() == TerrestriaBlocks.BASALT_DIRT && TerraformGrassBlock.canSpread(defaultBasaltGrassState, world, spreadingPos)) {
			world.setBlockState(spreadingPos, defaultBasaltGrassState.with(SpreadableBlock.SNOWY, world.getBlockState(spreadingPos.up()).getBlock() == Blocks.SNOW));
		}
	}
}
