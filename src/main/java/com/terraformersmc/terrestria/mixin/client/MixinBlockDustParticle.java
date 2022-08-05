package com.terraformersmc.terrestria.mixin.client;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockDustParticle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Mixin(BlockDustParticle.class)
@Environment(EnvType.CLIENT)
public class MixinBlockDustParticle {
	@Redirect(method = "<init>(Lnet/minecraft/client/world/ClientWorld;DDDDDDLnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)V",
	        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private boolean terrestria$fixParticleColors(BlockState blockState, Block requiredBlock) {
		Block block = blockState.getBlock();

		// Minecraft hardcodes it so that break particles from GRASS_BLOCK are not tinted with the color returned from
		// BlockColors. This prevents the particles (which are in actuality, the same as dirt particles) from being colored
		// with the grass color.
		//
		// The below code does the same thing for our own blocks, since they too have particles that shouldn't be tinted.
		if (block == TerrestriaBlocks.ANDISOL.getGrassBlock() || block == TerrestriaBlocks.SMALL_OAK_LOG
				|| block == TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG) {
			return true;
		}

		return blockState.isOf(requiredBlock);
	}
}
