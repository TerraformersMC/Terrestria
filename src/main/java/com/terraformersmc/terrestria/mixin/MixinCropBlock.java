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
	private static void getAvailableMoisture(Block block_1, BlockView blockView_1, BlockPos blockPos_1, CallbackInfoReturnable<Float> info) {
		float float_1 = 1.0F;
		boolean shouldCancel = false;
		BlockPos blockPos_2 = blockPos_1.down();

		for(int int_1 = -1; int_1 <= 1; ++int_1) {
			for(int int_2 = -1; int_2 <= 1; ++int_2) {
				float float_2 = 0.0F;
				BlockState blockState_1 = blockView_1.getBlockState(blockPos_2.add(int_1, 0, int_2));
				if (blockState_1.getBlock() == TerrestriaBlocks.BASALT_FARMLAND) {
					float_2 = 1.5F;
					shouldCancel = true;
					if (blockState_1.get(TerraformFarmlandBlock.MOISTURE) > 0) {
						float_2 = 4.5F;
					}
				}

				if (int_1 != 0 || int_2 != 0) {
					float_2 /= 4.0F;
				}

				float_1 += float_2;
			}
		}

		BlockPos blockPos_3 = blockPos_1.north();
		BlockPos blockPos_4 = blockPos_1.south();
		BlockPos blockPos_5 = blockPos_1.west();
		BlockPos blockPos_6 = blockPos_1.east();
		boolean boolean_1 = block_1 == blockView_1.getBlockState(blockPos_5).getBlock() || block_1 == blockView_1.getBlockState(blockPos_6).getBlock();
		boolean boolean_2 = block_1 == blockView_1.getBlockState(blockPos_3).getBlock() || block_1 == blockView_1.getBlockState(blockPos_4).getBlock();
		if (boolean_1 && boolean_2) {
			float_1 /= 2.0F;
		} else {
			boolean boolean_3 = block_1 == blockView_1.getBlockState(blockPos_5.north()).getBlock() || block_1 == blockView_1.getBlockState(blockPos_6.north()).getBlock() || block_1 == blockView_1.getBlockState(blockPos_6.south()).getBlock() || block_1 == blockView_1.getBlockState(blockPos_5.south()).getBlock();
			if (boolean_3) {
				float_1 /= 2.0F;
			}
		}
		if (shouldCancel) {
			info.setReturnValue(float_1);
			info.cancel();
		}
	}
}
