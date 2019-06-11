package net.coderbot.terrestria.init;

import net.coderbot.terrestria.biome.CypressForestBiome;
import net.coderbot.terrestria.biome.CypressSwampBiome;
import net.coderbot.terrestria.biome.JapaneseForestBiome;
import net.coderbot.terrestria.biome.RainforestBiome;
import net.coderbot.terrestria.block.SakuraLogBlock;
import net.coderbot.terrestria.feature.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.JungleGroundBushFeature;
import net.minecraft.world.gen.feature.SeagrassFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class TerrestriaBiomes {
	public static CypressTreeFeature CYPRESS_TREE;
	public static JapaneseTreeFeature SAKURA_TREE;
	public static JapaneseTreeFeature JAPANESE_MAPLE_TREE;
	public static JungleGroundBushFeature JAPANESE_MAPLE_SHRUB;
	public static MegaCanopyTreeFeature RAINBOW_EUCALYPTUS_TREE;
	public static MegaCanopyTreeFeature BALD_CYPRESS_TREE;

	public static CattailFeature CATTAIL;

	public static CypressForestBiome CYPRESS_FOREST;
	public static JapaneseForestBiome SAKURA_FOREST;
	public static JapaneseForestBiome JAPANESE_MAPLE_FOREST;
	public static RainforestBiome RAINFOREST;
	public static CypressSwampBiome CYPRESS_SWAMP;

	public static void init() {
		CYPRESS_TREE = Registry.register(Registry.FEATURE, "terrestria:cypress_tree",
				new CypressTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.CYPRESS.getBasicDefinition())
		);

		SAKURA_TREE = Registry.register(Registry.FEATURE, "terrestria:sakura_tree",
				new SakuraTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.SAKURA.getBasicDefinition().toSakura (
						TerrestriaBlocks.SAKURA.log.getDefaultState().with(SakuraLogBlock.HAS_LEAVES, true),
						TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState()
				))
		);

		JAPANESE_MAPLE_TREE = Registry.register(Registry.FEATURE, "terrestria:japanese_maple_tree",
				new JapaneseMapleTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.JAPANESE_MAPLE.getBasicDefinition())
		);

		// TODO: Modify shrub to push Sakura leaf piles above it
		JAPANESE_MAPLE_SHRUB = Registry.register(Registry.FEATURE, "terrestria:japanese_maple_shrub",
				new JungleGroundBushFeature(DefaultFeatureConfig::deserialize, TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState())
		);

		RAINBOW_EUCALYPTUS_TREE = Registry.register(Registry.FEATURE, "terrestria:rainbow_eucalyptus_tree",
				new MegaCanopyTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.RAINBOW_EUCALYPTUS.getBasicDefinition().toMega(TerrestriaBlocks.RAINBOW_EUCALYPTUS_QUARTER_LOG.getDefaultState()))
		);

		BALD_CYPRESS_TREE = Registry.register(Registry.FEATURE, "terrestria:bald_cypress_tree",
				new MegaCanopyTreeFeature(DefaultFeatureConfig::deserialize, false, TerrestriaBlocks.BALD_CYPRESS.getBasicDefinition().toMega(TerrestriaBlocks.BALD_CYPRESS_QUARTER_LOG.getDefaultState()))
		);

		CATTAIL = Registry.register(Registry.FEATURE, "terrestria:cattail",
				new CattailFeature(SeagrassFeatureConfig::deserialize)
		);

		CYPRESS_FOREST = Registry.register(Registry.BIOME, "terrestria:cypress_forest", new CypressForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
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

		SAKURA_FOREST = Registry.register(Registry.BIOME, "terrestria:sakura_forest", new JapaneseForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
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

		JAPANESE_MAPLE_FOREST = Registry.register(Registry.BIOME, "terrestria:japanese_maple_forest", new JapaneseForestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
						.depth(0.05F)
						.scale(0.2F)
						.temperature(0.8F)
						.downfall(0.5F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				JAPANESE_MAPLE_TREE,
				JAPANESE_MAPLE_SHRUB
		));

		DefaultBiomeFeatures.addForestGrass(JAPANESE_MAPLE_FOREST);

		RAINFOREST = Registry.register(Registry.BIOME, "terrestria:rainforest", new RainforestBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.JUNGLE)
						.depth(0.1F)
						.scale(0.2F)
						.temperature(0.95F)
						.downfall(0.9F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				10,
				RAINBOW_EUCALYPTUS_TREE,
				Feature.FANCY_TREE
		));

		CYPRESS_SWAMP = Registry.register(Registry.BIOME, "terrestria:cypress_swamp", new CypressSwampBiome(
				new Biome.Settings()
						.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
						.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.SWAMP)
						.depth(-0.3F)
						.scale(0F)
						.temperature(0.7F)
						.downfall(0.7F)
						.waterColor(4159204)
						.waterFogColor(329011)
						.parent(null),
				7,
				BALD_CYPRESS_TREE,
				Feature.SWAMP_TREE
		));
	}
}
