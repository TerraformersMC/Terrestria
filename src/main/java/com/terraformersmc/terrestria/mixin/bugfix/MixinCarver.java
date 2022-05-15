package com.terraformersmc.terrestria.mixin.bugfix;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.Carver;

/**
 * See also: https://bugs.mojang.com/browse/MC-16132
 * Cave generators couldn't cut through snow blocks or red sand, but both of those blocks have since been fixed.
 *
 * <p>This mixin allows caves to cut through smooth sandstone, to fix issues with caves in the Canyon biome. It
 * doesn't appear vanilla uses smooth sandstone anywhere in cave generation, so this shouldn't cause any issues.
 * </p>
 */
@Mixin(Carver.class)
public class MixinCarver {
	@Shadow
	protected Set<Block> alwaysCarvableBlocks;

	@SuppressWarnings("rawtypes")
	@Inject(method = "<init>", at = @At("RETURN"))
	private void terrestria$carveSmoothSandstone(Codec configCodec, CallbackInfo ci) {
		// Part 1: Allow smooth sandstone
		alwaysCarvableBlocks = terrestria$addToImmutableSet(alwaysCarvableBlocks, Blocks.SMOOTH_SANDSTONE);
	}

	private static <E> ImmutableSet<E> terrestria$addToImmutableSet(Set<E> list, E item) {
		ImmutableSet.Builder<E> newList = ImmutableSet.builder();

		newList.addAll(list);
		newList.add(item);

		return newList.build();
	}
}
