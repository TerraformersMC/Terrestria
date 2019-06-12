package net.coderbot.terrestria.mixin;

import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.AddRiversLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import net.minecraft.world.biome.layer.LayerSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO: Awaiting Fabric biomes API
@Mixin(AddRiversLayer.class)
public class AddRiversLayerMixin {
	private static final int CALDERA_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA);
	private static final int CALDERA_RIDGE_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA_RIDGE);
	private static final int CALDERA_BEACH_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA_BEACH);
	private static final int RIVER_ID = Registry.BIOME.getRawId(Biomes.RIVER);

	@Inject(method = "sample", at = @At("HEAD"), cancellable = true)
	public void onSample(LayerRandomnessSource rand, LayerSampler biomes, LayerSampler rivers, int x, int z, CallbackInfoReturnable<Integer> info) {
		int biome = biomes.sample(x, z);
		int river = rivers.sample(x, z);

		if(river == RIVER_ID) {
			if(biome == CALDERA_ID || biome == CALDERA_RIDGE_ID || biome == CALDERA_BEACH_ID) {
				info.setReturnValue(biome);
			}
		}
	}
}
