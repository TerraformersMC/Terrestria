package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ViewableWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(SugarCaneBlock.class)
public class MixinSugarCaneBlock {
	@Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
	private void canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		Block block = world.getBlockState(pos.down()).getBlock();
		if (block == state.getBlock()) {
			info.setReturnValue(true);
		} else {
			if (block == TerrestriaBlocks.BASALT_GRASS_BLOCK || block == TerrestriaBlocks.BASALT_DIRT || block == TerrestriaBlocks.BASALT_SAND || block == TerrestriaBlocks.BASALT_PODZOL) {
				BlockPos downPos = pos.down();
				Iterator iterator = Direction.Type.HORIZONTAL.iterator();

				while(iterator.hasNext()) {
					Direction direction = (Direction)iterator.next();
					BlockState downState = world.getBlockState(downPos.offset(direction));
					FluidState fluidState = world.getFluidState(downPos.offset(direction));
					if (fluidState.matches(FluidTags.WATER) || downState.getBlock() == Blocks.FROSTED_ICE) {
						info.setReturnValue(true);
					}
				}
			}
		}
	}
}
