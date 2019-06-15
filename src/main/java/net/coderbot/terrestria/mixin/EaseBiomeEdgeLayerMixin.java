package net.coderbot.terrestria.mixin;

import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
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
	private static final int VOLCANIC_ISLAND_ID = Registry.BIOME.getRawId(TerrestriaBiomes.VOLCANIC_ISLAND);
	private static final int VOLCANIC_ISLAND_SHORE_ID = Registry.BIOME.getRawId(TerrestriaBiomes.VOLCANIC_ISLAND_SHORE);

	@Inject(method = "sample", at = @At("HEAD"), cancellable = true)
	public void onSample(LayerRandomnessSource rand, int neighbor1, int neighbor2, int neighbor3, int neighbor4, int center, CallbackInfoReturnable<Integer> info) {
		if(center == CALDERA_RIDGE_ID) {
			if(neighbor1 == CALDERA_RIDGE_ID && neighbor2 == CALDERA_RIDGE_ID && neighbor3 == CALDERA_RIDGE_ID && neighbor4 == CALDERA_RIDGE_ID) {
				info.setReturnValue(CALDERA_ID);
			}
		} else if(hasNeighbor(neighbor1, neighbor2, neighbor3, neighbor4, CALDERA_RIDGE_ID)) {
			info.setReturnValue(MOUNTAINS_ID);
		}

		if(center == VOLCANIC_ISLAND_ID) {
			// TODO
		} else if(hasNeighbor(neighbor1, neighbor2, neighbor3, neighbor4, VOLCANIC_ISLAND_ID)) {
			info.setReturnValue(VOLCANIC_ISLAND_SHORE_ID);
		}
	}

	private static boolean hasNeighbor(int neighbor1, int neighbor2, int neighbor3, int neighbor4, int biome) {
		return neighbor1 == biome || neighbor2 == biome || neighbor3 == biome || neighbor4 == biome;
	}
}
