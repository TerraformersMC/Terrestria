package com.terraformersmc.terrestria.mixin.client;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.util.math.BlockPos;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Mixin(BlockDustParticle.class)
@Environment(EnvType.CLIENT)
public class MixinBlockDustParticle {
	@Shadow
	@Final
	private BlockState blockState;

	@Inject(method = "setBlockPos(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/client/particle/BlockDustParticle;",
	        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"),
	        cancellable = true)
	private void terrestria$fixParticleColors(BlockPos pos, CallbackInfoReturnable<BlockDustParticle> cir) {
		Block block = blockState.getBlock();

		// Minecraft hardcodes it so that break particles from GRASS_BLOCK are not tinted with the color returned from
		// BlockColors. This prevents the particles (which are in actuality, the same as dirt particles) from being colored
		// with the grass color.
		//
		// The below code does the same thing for our own blocks, since they too have particles that shouldn't be tinted.
		if (block == TerrestriaBlocks.ANDISOL.getGrassBlock() || block == TerrestriaBlocks.SMALL_OAK_LOG
				|| block == TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG) {
			cir.setReturnValue((BlockDustParticle) (Object) this);
		}
	}
}
