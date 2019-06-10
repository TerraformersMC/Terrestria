package net.coderbot.funwoods.mixin;

import net.coderbot.funwoods.init.FunwoodsBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.SetBaseBiomesLayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

// TODO: Awaiting Fabric biomes API
@Mixin(SetBaseBiomesLayer.class)
public class MixinSetBaseBiomesLayer {
	@Shadow
	@Final
	@Mutable
	private static int[] TEMPERATE_BIOMES;

	@Shadow
	@Final
	@Mutable
	private static int[] SNOWY_BIOMES;

	@Shadow
	@Final
	@Mutable
	private static int[] DRY_BIOMES;

	@Shadow
	@Final
	@Mutable
	private static int[] COOL_BIOMES;

	static {
		int CYPRESS_FOREST_ID = Registry.BIOME.getRawId(FunwoodsBiomes.CYPRESS_FOREST);
		int SAKURA_FOREST_ID = Registry.BIOME.getRawId(FunwoodsBiomes.SAKURA_FOREST);

		// TODO: rip every other biome
		TEMPERATE_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID };
		SNOWY_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID };
		DRY_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID };
		COOL_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID };
	}
}
