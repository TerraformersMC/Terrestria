package com.terraformersmc.terrestria.mixin.pathing;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

/**
 * Makes sure that the pathfinder knows that Saguaro and Tiny cactus blocks are cactuses.
 *
 * This is especially important because mobs really like to stand in tiny cactuses and die without this.
 */
@Mixin(LandPathNodeMaker.class)
public class MixinLandPathNodeMaker {
	@Inject(method = "getCommonNodeType(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/entity/ai/pathing/PathNodeType;",
			at = @At(value = "FIELD", target = "net/minecraft/block/Blocks.CACTUS:Lnet/minecraft/block/Block;"),
			cancellable = true)
	private static void terrestria$preventPathingIntoCustomCactuses(BlockView blockView, BlockPos blockPos, CallbackInfoReturnable<PathNodeType> callback) {
		BlockState state = blockView.getBlockState(blockPos);

		if (state.isOf(TerrestriaBlocks.SAGUARO_CACTUS) || state.isOf(TerrestriaBlocks.TINY_CACTUS)) {
			callback.setReturnValue(PathNodeType.DAMAGE_CACTUS);
		}
	}

	@Inject(method = "getNodeTypeFromNeighbors(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos$Mutable;Lnet/minecraft/entity/ai/pathing/PathNodeType;)Lnet/minecraft/entity/ai/pathing/PathNodeType;",
			at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/world/BlockView.getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"),
			cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
	private static void terrestria$preventPathingNearCustomCactuses(BlockView blockView, BlockPos.Mutable mutable,
																	PathNodeType pathNodeType, CallbackInfoReturnable<PathNodeType> callback,
																	int i, int j, int k, int l, int m, int n, BlockState state) {
		if (state.isOf(TerrestriaBlocks.SAGUARO_CACTUS) || state.isOf(TerrestriaBlocks.TINY_CACTUS)) {
			callback.setReturnValue(PathNodeType.DAMAGE_CACTUS);
		}
	}
}
