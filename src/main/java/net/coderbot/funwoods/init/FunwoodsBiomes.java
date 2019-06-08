package net.coderbot.funwoods.init;

import net.coderbot.funwoods.biome.CypressForestBiome;
import net.coderbot.funwoods.feature.CypressTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class FunwoodsBiomes {
	public static CypressTreeFeature CYPRESS_TREE;

	public static CypressForestBiome CYPRESS_FOREST;

	public static void init() {
		CYPRESS_TREE = Registry.register(Registry.FEATURE, "funwoods:cypress_tree",
				new CypressTreeFeature(DefaultFeatureConfig::deserialize, false, FunwoodsBlocks.CYPRESS.getBasicDefinition())
		);

		// TODO: Numeric IDs
		CYPRESS_FOREST = Registry.register(Registry.BIOME, 51, "funwoods:cypress_forest", new CypressForestBiome (
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(0.1F)
						.scale(0.2F)
						.temperature(0.7F)
						.downfall(0.8F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				CYPRESS_TREE,
				CYPRESS_TREE
		));
	}
}
