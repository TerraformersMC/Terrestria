package com.terraformersmc.terrestria.mixin.bugfix;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.gen.carver.Carver;

/**
 * A partial fix for https://bugs.mojang.com/browse/MC-16132: Cave generator won't cut through snow blocks or red sand
 *
 * <p>This mixin allows cave carvers to cut through red sand just as they would with normal sand.</p>
 *
 * <p>Additionally, it allows caves to cut through smooth sandstone, to fix issues with caves in the Canyon biome. It
 * doesn't appear like vanilla uses smooth sandstone anywhere in cave generation, so this shouldn't cause any issues.
 * </p>
 */
@Mixin(Carver.class)
public class MixinCarver {
	@Shadow
	protected Set<Block> alwaysCarvableBlocks;

	@SuppressWarnings("rawtypes")
	@Inject(method = "<init>", at = @At("RETURN"))
	private void terrestria$carveSmoothSandstone(Codec configCodec, int heightLimit, CallbackInfo ci) {
		// Part 1: Allow smooth sandstone
		alwaysCarvableBlocks = terrestria$addToImmutableSet(alwaysCarvableBlocks, Blocks.SMOOTH_SANDSTONE);
	}

	@Inject(method = "canCarveBlock(Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
	private void terrestria$carveRedSand(BlockState state, BlockState stateAbove, CallbackInfoReturnable<Boolean> ci) {
		// Part 2: Allow red sand conditionally just like vanilla does for normal sand
		if (state.isOf(Blocks.RED_SAND) && !stateAbove.getFluidState().isIn(FluidTags.WATER)) {
			ci.setReturnValue(true);
		}
	}

	private static <E> ImmutableSet<E> terrestria$addToImmutableSet(Set<E> list, E item) {
		ImmutableSet.Builder<E> newList = ImmutableSet.builder();

		newList.addAll(list);
		newList.add(item);

		return newList.build();
	}
}
