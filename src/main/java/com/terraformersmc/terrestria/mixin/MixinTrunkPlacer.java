package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TrunkPlacer.class)
public class MixinTrunkPlacer {
	@Inject(method = "method_27400", at = @At("HEAD"), cancellable = true)
	private static void notAlwaysDirt(ModifiableTestableWorld world, BlockPos pos, CallbackInfo ci) {
		if (world.testBlockState(pos, (blockState) -> {
			Block block = blockState.getBlock();
			return block == Blocks.SAND || block == Blocks.RED_SAND || block == TerrestriaBlocks.BLACK_SAND;
		})) {
			ci.cancel();
		} else if (world.testBlockState(pos, (blockState) -> {
			Block block = blockState.getBlock();
			return block == TerrestriaBlocks.ANDISOL || block == TerrestriaBlocks.ANDISOL_GRASS_BLOCK || block == TerrestriaBlocks.ANDISOL_PODZOL;
		})) {
			TreeFeature.setBlockStateWithoutUpdatingNeighbors(world, pos, TerrestriaBlocks.ANDISOL.getDefaultState());
			ci.cancel();
		}
	}
}
