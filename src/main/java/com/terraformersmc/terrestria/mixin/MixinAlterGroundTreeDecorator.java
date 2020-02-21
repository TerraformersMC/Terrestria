package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.decorator.AlterGroundTreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.Predicate;

@Mixin(AlterGroundTreeDecorator.class)
public class MixinAlterGroundTreeDecorator {
	// prepareGroundColumn
	@Inject(method = "method_23463", at = @At("HEAD"), cancellable = true)
	private void prepareGroundColumn(ModifiableTestableWorld world, Random random, BlockPos pos, CallbackInfo callback) {
		for(int i = 2; i >= -3; --i) {
			BlockPos posUp = pos.up(i);

			Predicate<BlockState> isBasaltDirt =
					state -> state.getBlock() == TerrestriaBlocks.BASALT_DIRT ||
							state.getBlock() == TerrestriaBlocks.BASALT_GRASS_BLOCK ||
							state.getBlock() == TerrestriaBlocks.BASALT_PODZOL;

			if (world.testBlockState(posUp, isBasaltDirt)) {
				world.setBlockState(posUp, TerrestriaBlocks.BASALT_PODZOL.getDefaultState(), 18);
				callback.cancel();
				break;
			}

			if (!world.testBlockState(posUp, BlockState::isAir) && i < 0) {
				break;
			}
		}
	}
}
