package net.coderbot.terrestria.mixin;

import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.AddEdgeBiomesLayer;
import net.minecraft.world.biome.layer.EaseBiomeEdgeLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EaseBiomeEdgeLayer.class)
public class EaseBiomeEdgeLayerMixin {
	private static final int CALDERA_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA);
	private static final int CALDERA_RIDGE_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA_RIDGE);
	private static final int MOUNTAINS_ID = Registry.BIOME.getRawId(Biomes.MOUNTAINS);

	@Inject(method = "sample", at = @At("HEAD"), cancellable = true)
	public void onSample(LayerRandomnessSource rand, int neighbor1, int neighbor2, int neighbor3, int neighbor4, int center, CallbackInfoReturnable<Integer> info) {
		if(center == CALDERA_RIDGE_ID) {
			if(neighbor1 == CALDERA_RIDGE_ID && neighbor2 == CALDERA_RIDGE_ID && neighbor3 == CALDERA_RIDGE_ID && neighbor4 == CALDERA_RIDGE_ID) {
				info.setReturnValue(CALDERA_ID);
			}
		} else if(neighbor1 == CALDERA_RIDGE_ID || neighbor2 == CALDERA_RIDGE_ID || neighbor3 == CALDERA_RIDGE_ID || neighbor4 == CALDERA_RIDGE_ID) {
			info.setReturnValue(MOUNTAINS_ID);
		}
	}
}
