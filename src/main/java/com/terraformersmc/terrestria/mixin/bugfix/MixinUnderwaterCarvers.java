package com.terraformersmc.terrestria.mixin.bugfix;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.carver.UnderwaterCaveCarver;
import net.minecraft.world.gen.carver.UnderwaterRavineCarver;

/**
 * Same as {@link MixinCarver} but for underwater carvers.
 */
@Mixin({UnderwaterCaveCarver.class, UnderwaterRavineCarver.class})
public class MixinUnderwaterCarvers {
	@SuppressWarnings("rawtypes")
	@Inject(method = "<init>", at = @At("RETURN"))
	private void terrestria$carveRedSandAndSmoothSandstone(Codec configCodec, CallbackInfo ci) {
		Set<Block> alwaysCarvableBlocks = ((CarverAccessor) this).getAlwaysCarvableBlocks();

		alwaysCarvableBlocks = terrestria$addToImmutableSet(alwaysCarvableBlocks, Blocks.SMOOTH_SANDSTONE);
		alwaysCarvableBlocks = terrestria$addToImmutableSet(alwaysCarvableBlocks, Blocks.RED_SAND);

		((CarverAccessor) this).setAlwaysCarvableBlocks(alwaysCarvableBlocks);
	}

	private static <E> ImmutableSet<E> terrestria$addToImmutableSet(Set<E> list, E item) {
		ImmutableSet.Builder<E> newList = ImmutableSet.builder();

		newList.addAll(list);
		newList.add(item);

		return newList.build();
	}
}
