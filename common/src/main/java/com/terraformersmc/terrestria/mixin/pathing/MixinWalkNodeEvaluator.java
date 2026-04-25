package com.terraformersmc.terrestria.mixin.pathing;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;

/**
 * Makes sure that the pathfinder knows that Saguaro and Tiny cactus blocks are cactuses.
 *
 * This is especially important because mobs really like to stand in tiny cactuses and die without this.
 */
@Mixin(WalkNodeEvaluator.class)
public class MixinWalkNodeEvaluator {
	@Inject(method = "getPathTypeFromState",
			at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;CACTUS:Lnet/minecraft/world/level/block/Block;"),
			cancellable = true)
	private static void terrestria$preventPathingIntoCustomCactuses(BlockGetter blockView, BlockPos blockPos, CallbackInfoReturnable<PathType> cir) {
		BlockState state = blockView.getBlockState(blockPos);

		if (state.is(TerrestriaBlocks.SAGUARO_CACTUS) || state.is(TerrestriaBlocks.TINY_CACTUS)) {
			cir.setReturnValue(PathType.DAMAGE_OTHER);
		}
	}
}
