package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terraform.block.TerraformFarmlandBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CropBlock.class)
public class MixinCropBlock {
	@Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
	private void canPlantOnTop(BlockState state, BlockView view, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (state.getBlock() == TerrestriaBlocks.BASALT_FARMLAND) {
			info.setReturnValue(true);
			info.cancel();
		}
	}

	@Inject(method = "getAvailableMoisture", at = @At("HEAD"), cancellable = true)
	private static void getAvailableMoisture(Block block, BlockView view, BlockPos pos, CallbackInfoReturnable<Float> info) {
		float moistureAmt = 1.0F;
		boolean shouldCancel = false;
		BlockPos downPos = pos.down();

		for(int i = -1; i <= 1; ++i) {
			for(int j = -1; j <= 1; ++j) {
				float moistureFromFarmlnd = 0.0F;
				BlockState state = view.getBlockState(downPos.add(i, 0, j));
				if (state.getBlock() == TerrestriaBlocks.BASALT_FARMLAND) {
					moistureFromFarmlnd = 1.5F;
					shouldCancel = true;
					if (state.get(TerraformFarmlandBlock.MOISTURE) > 0) {
						moistureFromFarmlnd = 4.5F;
					}
				}

				if (i != 0 || j != 0) {
					moistureFromFarmlnd /= 4.0F;
				}

				moistureAmt += moistureFromFarmlnd;
			}
		}

		BlockPos north = pos.north();
		BlockPos south = pos.south();
		BlockPos west = pos.west();
		BlockPos east = pos.east();
		boolean eastwest = block == view.getBlockState(west).getBlock() || block == view.getBlockState(east).getBlock();
		boolean northsouth = block == view.getBlockState(north).getBlock() || block == view.getBlockState(south).getBlock();
		if (eastwest && northsouth) {
			moistureAmt /= 2.0F;
		} else {
			boolean temp = block == view.getBlockState(west.north()).getBlock() || block == view.getBlockState(east.north()).getBlock() || block == view.getBlockState(east.south()).getBlock() || block == view.getBlockState(west.south()).getBlock();
			if (temp) {
				moistureAmt /= 2.0F;
			}
		}
		if (shouldCancel) {
			info.setReturnValue(moistureAmt);
			info.cancel();
		}
	}
}
