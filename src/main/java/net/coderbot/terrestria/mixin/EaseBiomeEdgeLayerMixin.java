package net.coderbot.terrestria.mixin;

import net.coderbot.terrestria.biome.extensions.OverworldBiomesExt;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.layer.EaseBiomeEdgeLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.IntConsumer;

@Mixin(EaseBiomeEdgeLayer.class)
@SuppressWarnings("unused") // it's a mixin
public class EaseBiomeEdgeLayerMixin {
	@Inject(method = "sample", at = @At("HEAD"), cancellable = true)
	public void onSample(LayerRandomnessSource rand, int neighbor1, int neighbor2, int neighbor3, int neighbor4, int center, CallbackInfoReturnable<Integer> info) {
		boolean replaced =
				tryReplace(center, neighbor1, info::setReturnValue) ||
				tryReplace(center, neighbor2, info::setReturnValue) ||
				tryReplace(center, neighbor3, info::setReturnValue) ||
				tryReplace(center, neighbor4, info::setReturnValue);

		if(replaced) {
			return;
		}

		if(surrounded(neighbor1, neighbor2, neighbor3, neighbor4, center)) {
			Optional<Biome> target = OverworldBiomesExt.getCenter(Registry.BIOME.get(center));

			target.ifPresent(value -> info.setReturnValue(Registry.BIOME.getRawId(value)));
		}
	}

	private static boolean tryReplace(int center, int neighbor, IntConsumer consumer) {
		if(center == neighbor) {
			return false;
		}

		Optional<Biome> border = OverworldBiomesExt.getBorder(Registry.BIOME.get(neighbor));
		if(border.isPresent()) {
			consumer.accept(Registry.BIOME.getRawId(border.get()));

			return true;
		}

		return false;
	}

	private static boolean surrounded(int neighbor1, int neighbor2, int neighbor3, int neighbor4, int biome) {
		return neighbor1 == biome && neighbor2 == biome && neighbor3 == biome && neighbor4 == biome;
	}
}
