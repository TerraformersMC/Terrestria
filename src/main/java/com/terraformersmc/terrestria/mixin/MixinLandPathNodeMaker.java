package com.terraformersmc.terrestria.mixin;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
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
		at = @At(value = "FIELD", target = "net/minecraft/block/Blocks.CACTUS:Lnet/minecraft/block/Block;"), cancellable = true)
	private static void terrestria$preventPathingIntoCustomCactuses(BlockView blockView, BlockPos blockPos, CallbackInfoReturnable<PathNodeType> callback) {
		Block block = blockView.getBlockState(blockPos).getBlock();

		if (block == TerrestriaBlocks.SAGUARO_CACTUS || block == TerrestriaBlocks.TINY_CACTUS) {
			callback.setReturnValue(PathNodeType.DAMAGE_CACTUS);
		}
	}
}
