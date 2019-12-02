package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.MegaPineTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MegaPineTreeFeature.class)
public class MixinMegaPineTreeFeature {
	// TODO

	/*@Inject(method = "prepareGroundColumn", at = @At("HEAD"))
	private void prepareGroundColumn(ModifiableTestableWorld world, BlockPos pos, CallbackInfo info) {
		for(int i = 2; i >= -3; --i) {
			BlockPos posUp = pos.up(i);

			if (world.testBlockState(posUp, (blockState_1) -> (blockState_1.getBlock() == TerrestriaBlocks.BASALT_DIRT) || (blockState_1.getBlock() == TerrestriaBlocks.BASALT_GRASS_BLOCK))) {
				world.setBlockState(posUp, TerrestriaBlocks.BASALT_PODZOL.getDefaultState(), 18);
				break;
			}

			if (!world.testBlockState(posUp, BlockState::isAir) && i < 0) {
				break;
			}
		}
	}*/
}
