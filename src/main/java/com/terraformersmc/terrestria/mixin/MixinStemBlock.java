package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terraform.block.TerraformFarmlandBlock;
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
	private void onScheduledTick(BlockState state, World world, BlockPos pos, Random rand, CallbackInfo info) {
		if (world.getLightLevel(pos, 0) >= 9) {
			float moisture = this.getAvailableMoisture(state.getBlock(), world, pos);
			if (rand.nextInt((int)(25.0F / moisture) + 1) == 0) {
				int age = state.get(AGE);
				if (age < 7) {
					state = state.with(AGE, age + 1);
					world.setBlockState(pos, state, 2);
				} else {
					Direction direction = Direction.Type.HORIZONTAL.random(rand);
					BlockPos randPos = pos.offset(direction);
					Block block = world.getBlockState(randPos.down()).getBlock();
					if (world.getBlockState(randPos).isAir() && (block == TerrestriaBlocks.BASALT_FARMLAND || block == TerrestriaBlocks.BASALT_GRASS_BLOCK || block == TerrestriaBlocks.BASALT_DIRT)) {
						world.setBlockState(randPos, this.gourdBlock.getDefaultState());
						world.setBlockState(pos, this.gourdBlock.getAttachedStem().getDefaultState().with(HorizontalFacingBlock.FACING, direction));
						info.cancel();
					}
				}
			}

		}
	}

	private static float getAvailableMoisture(Block block, BlockView view, BlockPos pos) {
		float moistureAmt = 1.0F;
		BlockPos downPos = pos.down();

		for(int i = -1; i <= 1; ++i) {
			for(int j = -1; j <= 1; ++j) {
				float moistureFromFarmlnd = 0.0F;
				BlockState state = view.getBlockState(downPos.add(i, 0, j));
				if (state.getBlock() == TerrestriaBlocks.BASALT_FARMLAND) {
					moistureFromFarmlnd = 1.5F;
					if (state.get(TerraformFarmlandBlock.MOISTURE) > 0) {
						moistureFromFarmlnd = 4.5F;
					}
				}
				if (state.getBlock() == Blocks.FARMLAND) {
					moistureFromFarmlnd = 1.0F;
					if (state.get(TerraformFarmlandBlock.MOISTURE) > 0) {
						moistureFromFarmlnd = 3.0F;
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
		return moistureAmt;
	}
}
