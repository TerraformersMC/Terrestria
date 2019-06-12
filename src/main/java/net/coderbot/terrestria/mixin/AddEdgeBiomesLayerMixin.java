package net.coderbot.terrestria.mixin;

import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.AddEdgeBiomesLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO: Awaiting Fabric biomes API
@Mixin(AddEdgeBiomesLayer.class)
public class AddEdgeBiomesLayerMixin {
	private static int CALDERA_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA);
	private static int CALDERA_BEACH_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA_BEACH);

	@Inject(method = "sample", at = @At("HEAD"), cancellable = true)
	public void onSample(LayerRandomnessSource rand, int neighbor1, int neighbor2, int neighbor3, int neighbor4, int center, CallbackInfoReturnable<Integer> info) {
		if(center == CALDERA_ID) {
			if(neighbor1 != CALDERA_ID || neighbor2 != CALDERA_ID || neighbor3 != CALDERA_ID || neighbor4 != CALDERA_ID) {
				info.setReturnValue(CALDERA_BEACH_ID);
			}
		}
	}
}
