package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Feature.class)
public class MixinFeature {
	@Inject(method = "isDirt", at = @At("HEAD"), cancellable = true)
	private static void includeAndisol(Block block, CallbackInfoReturnable<Boolean> callback) {
		if(block == TerrestriaBlocks.ANDISOL || block == TerrestriaBlocks.ANDISOL_GRASS_BLOCK || block == TerrestriaBlocks.ANDISOL_PODZOL) {
			callback.setReturnValue(true);
		}
	}
}
