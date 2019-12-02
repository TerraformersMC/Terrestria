package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terraform.block.ExtendedLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public class MixinRenderLayers {
	@Inject(method = "getBlockLayer", at = @At("HEAD"), cancellable = true)
	private static void patchExtendedLeaves(BlockState state, CallbackInfoReturnable<RenderLayer> callback) {
		if(state.getBlock() instanceof ExtendedLeavesBlock) {
			callback.setReturnValue(RenderLayers.getBlockLayer(Blocks.OAK_LEAVES.getDefaultState()));
		}
	}
}
