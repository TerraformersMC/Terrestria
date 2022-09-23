package com.terraformersmc.terrestria.init;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.DenseWoodlandTreeConfig;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import com.terraformersmc.terrestria.init.helpers.QuarteredWoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class TerrestriaConfiguredFeatures {

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SMALL_RAINBOW_EUCALYPTUS_SAPLING_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> BRYCE_TREE;

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JUNGLE_PALM_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> WILLOW_TREE;

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SMALL_HEMLOCK_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SMALL_REDWOOD_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> HEMLOCK_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> REDWOOD_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_HEMLOCK_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_REDWOOD_TREE;

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MEGA_CYPRESS_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CYPRESS_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RAINBOW_EUCALYPTUS_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SAKURA_TREE;

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DENSE_WOODLAND_TREE;

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FALLEN_HEMLOCK_LOG;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FALLEN_REDWOOD_LOG;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> FALLEN_OAK_LOG;

	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JAPANESE_MAPLE_SHRUB;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JAPANESE_MAPLE_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> DARK_JAPANESE_MAPLE_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> YUCCA_PALM_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> OAK_DOT_SHRUB;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> ACACIA_DOT_SHRUB;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> RUBBER_TREE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SAGUARO_CACTUS_FEATURE;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> OAK_SHRUB;
	public static RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SMALL_OAK_SPRUCE;

	public static RegistryEntry<ConfiguredFeature<ProbabilityConfig, ?>> CATTAIL;
	public static RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> DUM_DUM_HEAD;

	public static void init() {
		BRYCE_TREE = register("bryce_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.SMALL_OAK_LOG),
				new SpindlyTrunkPlacer(10, 0, 0),
				SimpleBlockStateProvider.of(Blocks.OAK_LEAVES),
				new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 0))
				.build());

		JUNGLE_PALM_TREE = register("jungle_palm_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(Blocks.JUNGLE_WOOD),
				new BentTrunkPlacer(15, 15, 15),
				SimpleBlockStateProvider.of(TerrestriaBlocks.JUNGLE_PALM_LEAVES),
				new PalmFanFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 2))
				.ignoreVines()
				.build());

		SMALL_HEMLOCK_TREE = register("small_hemlock_tree", Feature.TREE, spruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState()));
		SMALL_REDWOOD_TREE = register("small_redwood_tree", Feature.TREE, spruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState()));

		HEMLOCK_TREE = register("hemlock_tree", Feature.TREE, tallSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), 24, 4, 3, 2, 5, 1, 11));
		REDWOOD_TREE = register("redwood_tree", Feature.TREE, tallSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), 24, 4, 3, 5, 7, 12, 19));

		MEGA_HEMLOCK_TREE = register("mega_hemlock_tree", TerrestriaFeatures.QUARTERED_MEGA_TREE, giantSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), 32, 8, 7, 2, 5, 1, 11));
		MEGA_REDWOOD_TREE = register("mega_redwood_tree", TerrestriaFeatures.QUARTERED_MEGA_TREE, giantSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), 32, 8, 7, 2, 5, 12, 19));
		RUBBER_TREE = register("rubber_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.RUBBER.log),
				new RubberTreeTrunkPlacer(6, 2, 2),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RUBBER.leaves),
				new SphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
				).build());

		CYPRESS_TREE = register("cypress_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
				new StraightTrunkPlacer(7, 3, 0),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
				new CypressFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		FALLEN_HEMLOCK_LOG = register("fallen_hemlock_log", Feature.TREE, fallenLogOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(5, 3, 1)));
		FALLEN_REDWOOD_LOG = register("fallen_redwood_log", Feature.TREE, fallenLogOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(7, 2, 1)));
		FALLEN_OAK_LOG = register("fallen_oak_log", Feature.TREE, fallenLogOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(4, 1, 1)));

		JAPANESE_MAPLE_SHRUB = register("japanese_maple_shrub", Feature.TREE, shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState(),  TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING.getDefaultState()));
		OAK_SHRUB = register("oak_shrub", Feature.TREE, shrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState()));

		RAINBOW_EUCALYPTUS_TREE = register("rainbow_eucalyptus_tree", TerrestriaFeatures.QUARTERED_MEGA_TREE, new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood)));

		SMALL_RAINBOW_EUCALYPTUS_SAPLING_TREE = register("small_rainbow_eucalyptus_tree", Feature.TREE, (new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new StraightTrunkPlacer(4, 8, 0),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
				new TwoLayersFeatureSize(1, 0, 1)))
				.ignoreVines()
				.build());

//		SAGUARO_CACTUS_FEATURE = register("saguaro_cactus", TerraformTreeFeatures.SANDY_TREE, new TreeFeatureConfig.Builder(
		SAGUARO_CACTUS_FEATURE = register("saguaro_cactus", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
				new SaguaroCactusTrunkPlacer(0,0,0),
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		SAKURA_TREE = register("sakura_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAKURA.log),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAKURA.leaves),
				new SmallCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.decorators(ImmutableList.of(new SakuraTreeDecorator()))
				.build());

		JAPANESE_MAPLE_TREE = register("japanese_maple_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.leaves),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
		).build());

		DARK_JAPANESE_MAPLE_TREE = register("dark_japanese_maple_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
		).build());

		MEGA_CYPRESS_TREE = register("mega_cypress_tree", TerrestriaFeatures.QUARTERED_MEGA_TREE, new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.quarterLog),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.wood)));

		DENSE_WOODLAND_TREE = register("dense_woodland_tree", TerrestriaFeatures.DENSE_WOODLAND_TREE, new DenseWoodlandTreeConfig(new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
				new DenseWoodlandTrunkPlacer(7, 4, 0),
				SimpleBlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleBlockStateProvider.of(Blocks.OAK_LOG),
				SimpleBlockStateProvider.of(Blocks.OAK_WOOD)));

		SMALL_OAK_SPRUCE = register("small_oak_spruce", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()),
				new StraightTrunkPlacer(4, 1, 0),
				SimpleBlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
				new PyramidFoliagePlacer(ConstantIntProvider.create(2), UniformIntProvider.create(2, 4)),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		WILLOW_TREE = register("willow_tree", Feature.TREE, canopyOf(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING.getDefaultState(), new CanopyTree4BranchTrunkPlacer(4, 1, 1), ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.getDefaultState()))));
//		YUCCA_PALM_TREE = register("yucca_palm_tree", TerraformTreeFeatures.SANDY_TREE, new TreeFeatureConfig.Builder(
		YUCCA_PALM_TREE = register("yucca_palm_tree", Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.log),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.leaves),
				new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)).build());

		OAK_DOT_SHRUB = register("oak_dot_shrub", Feature.TREE, dotShrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState()));
		ACACIA_DOT_SHRUB = register("acacia_dot_shrub", Feature.TREE, dotShrubOf(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState(), Blocks.ACACIA_SAPLING.getDefaultState()));

		CATTAIL = register("cattail", TerrestriaFeatures.CATTAIL, new ProbabilityConfig(0.3F));
		DUM_DUM_HEAD = register("dum_dum_head", TerrestriaFeatures.DUM_DUM_HEAD, DefaultFeatureConfig.INSTANCE);
	}

	static  <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
		return register(id, new ConfiguredFeature<>(feature, config));
	}

	private static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, ConfiguredFeature<FC, F> cf) {
		Identifier realId = new Identifier(Terrestria.MOD_ID, id);
		Preconditions.checkState(!BuiltinRegistries.CONFIGURED_FEATURE.getIds().contains(realId), "Duplicate ID: %s", id);
		return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, realId.toString(), cf);
	}

	static TreeFeatureConfig canopyOf(WoodBlocks woodBlocks, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling, trunkPlacer, decorators);
	}

	static TreeFeatureConfig canopyOf(BlockState log, BlockState leaves, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				trunkPlacer,
				SimpleBlockStateProvider.of(leaves),
				new CanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(3, 0 , 1))
				.decorators(decorators)
				.build();
	}

	static TreeFeatureConfig fallenLogOf(WoodBlocks woodBlocks, BlockState sapling, FallenStraightTrunkPlacer trunk) {
		return fallenLogOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling, trunk);
	}

	static TreeFeatureConfig fallenLogOf(BlockState log, BlockState leaves, BlockState sapling, FallenStraightTrunkPlacer trunk) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				trunk,
				SimpleBlockStateProvider.of(leaves),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(0, 0, 0))

				.build();
	}

	static TreeFeatureConfig shrubOf(WoodBlocks woodBlocks, BlockState sapling) {
		return shrubOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling);
	}

	static TreeFeatureConfig shrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 0, 0),
				SimpleBlockStateProvider.of(leaves),
				new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig dotShrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 1, 0),
				SimpleBlockStateProvider.of(leaves),
				new DotShrubPlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig spruceOf(WoodBlocks woodBlocks, BlockState sapling) {
		return spruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling);
	}

	static TreeFeatureConfig spruceOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				new StraightTrunkPlacer(5, 2, 1),
				SimpleBlockStateProvider.of(leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 1)),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	static TreeFeatureConfig tallSpruceOf(WoodBlocks woodBlocks, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return tallSpruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling, minHeight, extraRandomHeight1, extraRandomHeight2, minLeavesRadius, maxLeavesRadius, minBareHeight, maxBareHeight);
	}

	static TreeFeatureConfig tallSpruceOf(BlockState log, BlockState leaves, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(log),
				new StraightTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				SimpleBlockStateProvider.of(leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(minLeavesRadius, maxLeavesRadius), UniformIntProvider.create(0, 2), UniformIntProvider.create(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	static QuarteredMegaTreeConfig giantSpruceOf(QuarteredWoodBlocks woodBlocks, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(woodBlocks.log),
				new MegaTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				SimpleBlockStateProvider.of(woodBlocks.leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(minLeavesRadius, maxLeavesRadius), UniformIntProvider.create(0, 2), UniformIntProvider.create(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 1, 2))
				.ignoreVines()
				.build(),
				SimpleBlockStateProvider.of(woodBlocks.quarterLog),
				SimpleBlockStateProvider.of(woodBlocks.wood));
	}
}
