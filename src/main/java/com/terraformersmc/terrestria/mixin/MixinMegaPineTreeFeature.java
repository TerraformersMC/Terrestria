package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.MegaPineTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MegaPineTreeFeature.class)
public class MixinMegaPineTreeFeature {
	@Inject(method = "prepareGroundColumn", at = @At("HEAD"), cancellable = true)
	private void prepareGroundColumn(ModifiableTestableWorld world, BlockPos pos, CallbackInfo info) {
		boolean didPlaceBasaltPodzol = false;
		for(int int_1 = 2; int_1 >= -3; --int_1) {
			BlockPos posUp = pos.up(int_1);
			if (world.testBlockState(posUp, (blockState_1) -> (blockState_1.getBlock() == TerrestriaBlocks.BASALT_DIRT) || (blockState_1.getBlock() == TerrestriaBlocks.BASALT_GRASS_BLOCK))) {
				world.setBlockState(posUp, TerrestriaBlocks.BASALT_PODZOL.getDefaultState(), 18);
				didPlaceBasaltPodzol = true;
				break;
			}

			if (!(world.testBlockState(posUp, BlockState::isAir)) && int_1 < 0) {
				break;
			}
		}
		if (didPlaceBasaltPodzol) {
			info.cancel();
			return;
		}
	}
}
