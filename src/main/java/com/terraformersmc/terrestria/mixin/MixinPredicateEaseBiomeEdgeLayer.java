package com.terraformersmc.terrestria.mixin;

import net.minecraft.world.biome.layer.util.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.terraformersmc.terrestria.util.TerrestriaOverworldBiomes;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.EaseBiomeEdgeLayer;

@Mixin(value = EaseBiomeEdgeLayer.class, priority = 1500)
public class MixinPredicateEaseBiomeEdgeLayer {
	@Inject(method = "sample", at = @At("HEAD"), cancellable = true)
	private void predicateSample(LayerRandomnessSource rand, int neighbor1, int neighbor2, int neighbor3, int neighbor4, int center, CallbackInfoReturnable<Integer> info) {
		Biome biome = Registry.BIOME.get(center);

		for (TerrestriaOverworldBiomes.PredicatedBiomeEntry entry : TerrestriaOverworldBiomes.getPredicatedBorders(biome)) {
			if (entry.predicate.test(Registry.BIOME.get(neighbor1)) || entry.predicate.test(Registry.BIOME.get(neighbor2)) || entry.predicate.test(Registry.BIOME.get(neighbor3)) || entry.predicate.test(Registry.BIOME.get(neighbor4))) {
				info.setReturnValue(Registry.BIOME.getRawId(entry.biome));
			}
		}
	}
}
