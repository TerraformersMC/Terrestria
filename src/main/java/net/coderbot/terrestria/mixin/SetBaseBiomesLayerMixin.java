package net.coderbot.terrestria.mixin;

import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.SetBaseBiomesLayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

// TODO: Awaiting Fabric biomes API
@Mixin(SetBaseBiomesLayer.class)
public class SetBaseBiomesLayerMixin {
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
		int CYPRESS_FOREST_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CYPRESS_FOREST);
		int SAKURA_FOREST_ID = Registry.BIOME.getRawId(TerrestriaBiomes.SAKURA_FOREST);
		int JAPANESE_MAPLE_FOREST_ID = Registry.BIOME.getRawId(TerrestriaBiomes.JAPANESE_MAPLE_FOREST);
		int RAINFOREST_ID = Registry.BIOME.getRawId(TerrestriaBiomes.RAINFOREST);
		int CYPRESS_SWAMP_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CYPRESS_SWAMP);
		int CALDERA_RIDGE_ID = Registry.BIOME.getRawId(TerrestriaBiomes.CALDERA_RIDGE);

		// TODO: rip every other biome
		TEMPERATE_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID, JAPANESE_MAPLE_FOREST_ID, RAINFOREST_ID, CYPRESS_SWAMP_ID, CALDERA_RIDGE_ID };
		SNOWY_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID, JAPANESE_MAPLE_FOREST_ID, RAINFOREST_ID, CYPRESS_SWAMP_ID, CALDERA_RIDGE_ID };
		DRY_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID, JAPANESE_MAPLE_FOREST_ID, RAINFOREST_ID, CYPRESS_SWAMP_ID, CALDERA_RIDGE_ID };
		COOL_BIOMES = new int[] { CYPRESS_FOREST_ID, SAKURA_FOREST_ID, JAPANESE_MAPLE_FOREST_ID, RAINFOREST_ID, CYPRESS_SWAMP_ID, CALDERA_RIDGE_ID };

		/*TEMPERATE_BIOMES = new int[] { CYPRESS_FOREST_ID, CALDERA_RIDGE_ID };
		SNOWY_BIOMES = new int[] { CYPRESS_FOREST_ID, CALDERA_RIDGE_ID };
		DRY_BIOMES = new int[] { CYPRESS_FOREST_ID, CALDERA_RIDGE_ID };
		COOL_BIOMES = new int[] { CYPRESS_FOREST_ID, CALDERA_RIDGE_ID };*/
	}
}
