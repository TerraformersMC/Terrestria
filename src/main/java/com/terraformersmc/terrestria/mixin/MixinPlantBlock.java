package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class MixinPlantBlock {
	@Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
	protected void hookPlantOnTop(BlockState state, BlockView view, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		Block block = state.getBlock();

		if(block == TerrestriaBlocks.BASALT_GRASS_BLOCK || block == TerrestriaBlocks.BASALT_DIRT || block == TerrestriaBlocks.BASALT_PODZOL) {
			callback.setReturnValue(true);
		}
	}
}
