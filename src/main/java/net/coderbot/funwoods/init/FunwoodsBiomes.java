package net.coderbot.funwoods.init;

import net.coderbot.funwoods.biome.CypressForestBiome;
import net.coderbot.funwoods.biome.SakuraForestBiome;
import net.coderbot.funwoods.block.SakuraLogBlock;
import net.coderbot.funwoods.feature.CypressTreeFeature;
import net.coderbot.funwoods.feature.SakuraTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.JungleGroundBushFeature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class FunwoodsBiomes {
	public static CypressTreeFeature CYPRESS_TREE;
	public static SakuraTreeFeature SAKURA_TREE;
	public static JungleGroundBushFeature JAPANESE_MAPLE_SHRUB;

	public static CypressForestBiome CYPRESS_FOREST;
	public static SakuraForestBiome SAKURA_FOREST;

	public static void init() {
		CYPRESS_TREE = Registry.register(Registry.FEATURE, "funwoods:cypress_tree",
				new CypressTreeFeature(DefaultFeatureConfig::deserialize, false, FunwoodsBlocks.CYPRESS.getBasicDefinition())
		);

		SAKURA_TREE = Registry.register(Registry.FEATURE, "funwoods:sakura_tree",
				new SakuraTreeFeature(DefaultFeatureConfig::deserialize, false, FunwoodsBlocks.SAKURA.getBasicDefinition().toSakura (
						FunwoodsBlocks.SAKURA.log.getDefaultState().with(SakuraLogBlock.HAS_LEAVES, true),
						FunwoodsBlocks.SAKURA_LEAF_PILE.getDefaultState()
				))
		);

		JAPANESE_MAPLE_SHRUB = Registry.register(Registry.FEATURE, "funwoods:japanese_maple_shrub", new JungleGroundBushFeature(DefaultFeatureConfig::deserialize, FunwoodsBlocks.JAPANESE_MAPLE.log.getDefaultState(), FunwoodsBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()));

		// TODO: Numeric IDs
		CYPRESS_FOREST = Registry.register(Registry.BIOME, 51, "funwoods:cypress_forest", new CypressForestBiome(
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

		SAKURA_FOREST = Registry.register(Registry.BIOME, 52, "funwoods:sakura_forest", new SakuraForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
						.depth(0.05F)
						.scale(0.1F)
						.temperature(0.8F)
						.downfall(1.0F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				SAKURA_TREE,
				JAPANESE_MAPLE_SHRUB
		));
	}
}
