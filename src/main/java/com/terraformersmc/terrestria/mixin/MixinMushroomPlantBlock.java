package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MushroomPlantBlock.class)
public class MixinMushroomPlantBlock {
	@Inject(method = "canPlaceAt", at = @At("RETURN"), cancellable = true)
	private void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (!info.getReturnValue()) {
			BlockPos downPos = pos.down();
			BlockState downState = world.getBlockState(downPos);
			Block block = downState.getBlock();

			if (block == TerrestriaBlocks.ANDISOL_PODZOL) {
				info.setReturnValue(true);
			}
		}
	}
}
