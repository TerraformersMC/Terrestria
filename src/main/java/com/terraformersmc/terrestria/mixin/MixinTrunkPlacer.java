package com.terraformersmc.terrestria.mixin;

import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TrunkPlacer.class)
public class MixinTrunkPlacer {
	@Inject(method = "method_27400", at = @At("HEAD"), cancellable = true)
	private static void notAlwaysDirt(ModifiableTestableWorld world, BlockPos pos, CallbackInfo ci) {
		if (world.testBlockState(pos, state -> state.isIn(BlockTags.SAND))) {
			ci.cancel();
		}
	}
}
