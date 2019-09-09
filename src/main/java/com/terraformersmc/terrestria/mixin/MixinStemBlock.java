package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.*;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StemBlock.class)
public class MixinStemBlock {
	@Shadow
	@Final
	private GourdBlock gourdBlock;

	@Shadow
	@Final
	public static IntProperty AGE;

	@Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
	private void canPlantOnTop(BlockState state, BlockView view, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
		if (state.getBlock() == TerrestriaBlocks.BASALT_FARMLAND) {
			info.setReturnValue(true);
			info.cancel();
		}
	}

	@Inject(method = "onScheduledTick", at = @At("HEAD"), cancellable = true)
	private void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1, CallbackInfo info) {
		if (world_1.getLightLevel(blockPos_1, 0) >= 9) {
			float float_1 = this.getAvailableMoisture(blockState_1.getBlock(), world_1, blockPos_1);
			if (random_1.nextInt((int)(25.0F / float_1) + 1) == 0) {
				int int_1 = (Integer)blockState_1.get(AGE);
				if (int_1 < 7) {
					blockState_1 = (BlockState)blockState_1.with(AGE, int_1 + 1);
					world_1.setBlockState(blockPos_1, blockState_1, 2);
				} else {
					Direction direction_1 = Direction.Type.HORIZONTAL.random(random_1);
					BlockPos blockPos_2 = blockPos_1.offset(direction_1);
					Block block_1 = world_1.getBlockState(blockPos_2.down()).getBlock();
					if (world_1.getBlockState(blockPos_2).isAir() && (block_1 == TerrestriaBlocks.BASALT_FARMLAND || block_1 == TerrestriaBlocks.BASALT_GRASS_BLOCK || block_1 == TerrestriaBlocks.BASALT_DIRT)) {
						world_1.setBlockState(blockPos_2, this.gourdBlock.getDefaultState());
						world_1.setBlockState(blockPos_1, (BlockState)this.gourdBlock.getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction_1));
						info.cancel();
					}
				}
			}

		}
	}

	private static float getAvailableMoisture(Block block_1, BlockView blockView_1, BlockPos blockPos_1) {
		float float_1 = 1.0F;
		BlockPos blockPos_2 = blockPos_1.down();

		for(int int_1 = -1; int_1 <= 1; ++int_1) {
			for(int int_2 = -1; int_2 <= 1; ++int_2) {
				float float_2 = 0.0F;
				BlockState blockState_1 = blockView_1.getBlockState(blockPos_2.add(int_1, 0, int_2));
				if (blockState_1.getBlock() == Blocks.FARMLAND) {
					float_2 = 1.0F;
					if ((Integer)blockState_1.get(FarmlandBlock.MOISTURE) > 0) {
						float_2 = 3.0F;
					}
				}
				if (blockState_1.getBlock() == TerrestriaBlocks.BASALT_FARMLAND) {
					float_2 = 1.5F;
					if ((Integer)blockState_1.get(FarmlandBlock.MOISTURE) > 0) {
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

		return float_1;
	}
}
