package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeature.class)
public abstract class MixinTreeFeature<FC extends FeatureConfig> extends Feature<FC> {
	// Bypasses the "no default constructor" error, will never actually get used
	private MixinTreeFeature() {
		super(null);

		throw new UnsupportedOperationException();
	}

	@Inject(method = "isDirtOrGrass", at = @At("HEAD"), cancellable = true)
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
}
