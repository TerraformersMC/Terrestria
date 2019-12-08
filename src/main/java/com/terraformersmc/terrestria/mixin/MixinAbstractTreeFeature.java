package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractTreeFeature.class)
public abstract class MixinAbstractTreeFeature extends Feature {
	// Bypasses the "no default constructor" error
	private MixinAbstractTreeFeature() {
		super(null);

		throw new UnsupportedOperationException();
	}

	@Inject(method = "isNaturalDirt", at = @At("HEAD"), cancellable = true)
	private static void hookIsNaturalDirt(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (world.testBlockState(pos, state -> {
			Block block = state.getBlock();
			return block == TerrestriaBlocks.BASALT_GRASS_BLOCK;
		})) {
			callback.setReturnValue(false);
		}
	}

	// TODO: Basalt farmland when added
	/*@Inject(method = "isDirtOrGrass", at = @At("HEAD"), cancellable = true)
	private static void hookIsDirtOrGrass(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (world.testBlockState(pos, state -> {
			Block block = state.getBlock();
			return block == TerrestriaBlocks.BASALT_FARMLAND;
		})) {
			callback.setReturnValue(true);
		}
	}*/

	@Inject(method = "setToDirt", at = @At("HEAD"), cancellable = true)
	private void hookSetToDirt(ModifiableTestableWorld world, BlockPos pos, CallbackInfo callback) {
		if (world.testBlockState(pos, state -> {
			Block block = state.getBlock();
			return block == TerrestriaBlocks.BASALT_GRASS_BLOCK || block == TerrestriaBlocks.BASALT_DIRT;
		})) {
			super.setBlockState(world, pos, TerrestriaBlocks.BASALT_DIRT.getDefaultState());

			callback.cancel();
		}
	}

}
