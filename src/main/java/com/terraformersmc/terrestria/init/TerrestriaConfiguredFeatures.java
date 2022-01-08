package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terraform.tree.feature.TerraformTreeFeatures;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
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
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class TerrestriaConfiguredFeatures {

	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_RAINBOW_EUCALYPTUS_SAPLING_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> BRYCE_TREE;

	public static ConfiguredFeature<TreeFeatureConfig, ?> JUNGLE_PALM_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> WILLOW_TREE;

	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_HEMLOCK_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_REDWOOD_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> HEMLOCK_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> REDWOOD_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> MEGA_HEMLOCK_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> MEGA_REDWOOD_TREE;

	public static ConfiguredFeature<TreeFeatureConfig, ?> MEGA_CYPRESS_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> CYPRESS_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> RAINBOW_EUCALYPTUS_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SAKURA_TREE;

	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_HEMLOCK_LOG;
	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_REDWOOD_LOG;

	public static ConfiguredFeature<TreeFeatureConfig, ?> JAPANESE_MAPLE_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> JAPANESE_MAPLE_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> DARK_JAPANESE_MAPLE_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> YUCCA_PALM_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> OAK_DOT_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> ACACIA_DOT_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> RUBBER_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SAGUARO_CACTUS_FEATURE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> OAK_SHRUB;

	public static ConfiguredFeature<DefaultFeatureConfig, ?> DUM_DUM_HEAD;

	public static void init() {
		BRYCE_TREE = registerSandyTree("bryce_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SMALL_OAK_LOG.getDefaultState()),
				new SpindlyTrunkPlacer(10, 0, 0),
				new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.BRYCE_SAPLING.getDefaultState()),
				new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 0))
				.build());

		JUNGLE_PALM_TREE = registerSandyTree("jungle_palm_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.JUNGLE_WOOD.getDefaultState()),
				new BentTrunkPlacer(15, 15, 15),
				new SimpleBlockStateProvider(TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JUNGLE_PALM_SAPLING.getDefaultState()),
				new PalmFanFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 2))
				.ignoreVines()
				.build());

		SMALL_HEMLOCK_TREE = registerTree("small_hemlock_tree", spruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState()));
		SMALL_REDWOOD_TREE = registerTree("small_redwood_tree", spruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState()));

		// Hemlock and Redwood trees are supposed to be different, don't change their stats just to bring them more in line!
		HEMLOCK_TREE = registerTree("hemlock_tree", tallSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), 24, 4, 3, 2, 5, 1, 11));
		REDWOOD_TREE = registerTree("redwood_tree", tallSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), 24, 4, 3, 5, 2, 12, 7));

		MEGA_HEMLOCK_TREE = registerQuarteredMegaTree("mega_hemlock_tree", giantSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), 32, 8, 7, 2, 5, 1, 11));
		MEGA_REDWOOD_TREE = registerQuarteredMegaTree("mega_redwood_tree", giantSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), 32, 8, 7, 2, 5, 12, 7));
		RUBBER_TREE = registerTree("rubber_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RUBBER.log.getDefaultState()),
				new RubberTreeTrunkPlacer(6, 2, 2),
				new SimpleBlockStateProvider(TerrestriaBlocks.RUBBER.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RUBBER_SAPLING.getDefaultState()),
				new SphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
				).build());

		CYPRESS_TREE = registerTree("cypress_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.log.getDefaultState()),
				new StraightTrunkPlacer(7, 3, 0),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS_SAPLING.getDefaultState()),
				new CypressFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		FALLEN_HEMLOCK_LOG = registerTree("fallen_hemlock_log", fallenLogOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(5, 3, 1)));
		FALLEN_REDWOOD_LOG = registerTree("fallen_redwood_log", fallenLogOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(7, 2, 1)));

		JAPANESE_MAPLE_SHRUB = registerTree("japanese_maple_shrub", shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState(),  TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING.getDefaultState()));
		OAK_SHRUB = registerTree("oak_shrub", shrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState()));

		RAINBOW_EUCALYPTUS_TREE = registerQuarteredMegaTree("rainbow_eucalyptus_tree", new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState()),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING.getDefaultState()),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState())));

		SMALL_RAINBOW_EUCALYPTUS_SAPLING_TREE = registerTree("small_rainbow_eucalyptus_tree", (new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState()),
				new StraightTrunkPlacer(4, 8, 0),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING.getDefaultState()),
				new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
				new TwoLayersFeatureSize(1, 0, 1)))
				.ignoreVines()
				.build());

		SAGUARO_CACTUS_FEATURE = registerSandyTree("saguaro_cactus", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SAGUARO_CACTUS.getDefaultState()),
				new SaguaroCactusTrunkPlacer(0,0,0),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAGUARO_CACTUS.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING.getDefaultState()),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		SAKURA_TREE = registerTree("sakura_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA.log.getDefaultState()),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA_SAPLING.getDefaultState()),
				new SmallCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.decorators(ImmutableList.of(new SakuraTreeDecorator()))
				.build());

		JAPANESE_MAPLE_TREE = registerTree("japanese_maple_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState()),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING.getDefaultState()),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
		).build());

		DARK_JAPANESE_MAPLE_TREE = registerTree("dark_japanese_maple_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState()),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				new SimpleBlockStateProvider(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING.getDefaultState()),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
		).build());

		MEGA_CYPRESS_TREE = registerQuarteredMegaTree("mega_cypress_tree", new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.log.getDefaultState()),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS_SAPLING.getDefaultState()),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.quarterLog.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.wood.getDefaultState())));

		WILLOW_TREE = registerTree("willow_tree", canopyOf(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING.getDefaultState(), new CanopyTree4BranchTrunkPlacer(4, 1, 1), ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.getDefaultState()))));
		YUCCA_PALM_TREE = registerSandyTree("yucca_palm_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM.log.getDefaultState()),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM.leaves.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM_SAPLING.getDefaultState()),
				new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)).build());

		OAK_DOT_SHRUB = registerTree("oak_dot_shrub", dotShrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState()));
		ACACIA_DOT_SHRUB = registerTree("acacia_dot_shrub", dotShrubOf(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState(), Blocks.ACACIA_SAPLING.getDefaultState()));

		DUM_DUM_HEAD = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Terrestria.MOD_ID, "dum_dum_head"), TerrestriaFeatures.DUM_DUM_HEAD.configure(DefaultFeatureConfig.INSTANCE));
	}

	private static ConfiguredFeature<TreeFeatureConfig, ?> registerTree(String name, TreeFeatureConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = Feature.TREE.configure(config);
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, configured);

		return configured;
	}

	private static ConfiguredFeature<TreeFeatureConfig, ?> registerQuarteredMegaTree(String name, QuarteredMegaTreeConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = TerrestriaFeatures.QUARTERED_MEGA_TREE.configure(config);
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, configured);

		return configured;
	}

	private static ConfiguredFeature<TreeFeatureConfig, ?> registerSandyTree(String name, TreeFeatureConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = TerraformTreeFeatures.SANDY_TREE.configure(config);
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, configured);

		return configured;
	}

	static TreeFeatureConfig canopyOf(WoodBlocks woodBlocks, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling, trunkPlacer, decorators);
	}

	static TreeFeatureConfig canopyOf(BlockState log, BlockState leaves, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				trunkPlacer,
				new SimpleBlockStateProvider(leaves),
				new SimpleBlockStateProvider(sapling),
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
				new SimpleBlockStateProvider(log),
				trunk,
				new SimpleBlockStateProvider(leaves),
				new SimpleBlockStateProvider(sapling),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(0, 0, 0))

				.build();
	}

	static TreeFeatureConfig shrubOf(WoodBlocks woodBlocks, BlockState sapling) {
		return shrubOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling);
	}

	static TreeFeatureConfig shrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new StraightTrunkPlacer(1, 0, 0),
				new SimpleBlockStateProvider(leaves),
				new SimpleBlockStateProvider(sapling),
				new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig dotShrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new StraightTrunkPlacer(1, 1, 0),
				new SimpleBlockStateProvider(leaves),
				new SimpleBlockStateProvider(sapling),
				new DotShrubPlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig spruceOf(WoodBlocks woodBlocks, BlockState sapling) {
		return spruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling);
	}

	static TreeFeatureConfig spruceOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new StraightTrunkPlacer(5, 2, 1),
				new SimpleBlockStateProvider(leaves),
				new SimpleBlockStateProvider(sapling),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 1)),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	static TreeFeatureConfig tallSpruceOf(WoodBlocks woodBlocks, BlockState sapling, int height, int randomHeight, int extraRandomHeight, int baseRadius, int randomRadius, int baseBareHeight, int randomBareHeight) {
		return tallSpruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), sapling, height, randomHeight, extraRandomHeight, baseRadius, randomRadius, baseBareHeight, randomBareHeight);
	}

	static TreeFeatureConfig tallSpruceOf(BlockState log, BlockState leaves, BlockState sapling, int height, int randomHeight, int extraRandomHeight, int baseRadius, int randomRadius, int baseBareHeight, int randomBareHeight) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new StraightTrunkPlacer(height, randomHeight, extraRandomHeight),
				new SimpleBlockStateProvider(leaves),
				new SimpleBlockStateProvider(sapling),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(baseRadius, randomRadius), UniformIntProvider.create(0, 2), UniformIntProvider.create(baseBareHeight, randomBareHeight)),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	static QuarteredMegaTreeConfig giantSpruceOf(QuarteredWoodBlocks woodBlocks, BlockState sapling, int height, int randomHeight, int extraRandomHeight, int baseRadius, int randomRadius, int baseBareHeight, int randomBareHeight) {
		return new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(woodBlocks.log.getDefaultState()),
				new MegaTrunkPlacer(height, randomHeight, extraRandomHeight),
				new SimpleBlockStateProvider(woodBlocks.leaves.getDefaultState()),
				new SimpleBlockStateProvider(sapling),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(baseRadius, randomRadius), UniformIntProvider.create(0, 2), UniformIntProvider.create(baseBareHeight, randomBareHeight)),
				new TwoLayersFeatureSize(2, 1, 2))
				.ignoreVines()
				.build(),
				new SimpleBlockStateProvider(woodBlocks.quarterLog.getDefaultState()),
				new SimpleBlockStateProvider(woodBlocks.wood.getDefaultState()));
	}
}
