package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {
	@Inject(method = "isNaturalDirt", at = @At("HEAD"), cancellable = true)
	private static void hookIsNaturalDirt(Block block, CallbackInfoReturnable<Boolean> callback) {
		if(block == TerrestriaBlocks.BASALT_DIRT) {
			callback.setReturnValue(true);
		}
	}
}
