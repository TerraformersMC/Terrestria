package com.terraformersmc.terrestria.world.gen.feature;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.QuarteredWoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.CanopyFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.CypressFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.DotShrubPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.JapaneseCanopyFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.NoneFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.PalmFanFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.PredictiveSpruceFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.SmallCanopyFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.SmallLogSphereFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.SphereFoliagePlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.world.gen.feature.tree.treedecorators.SakuraTreeDecorator;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.BentTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.CanopyTree4BranchTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.FallenStraightTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.MegaTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.QuarteredMegaCanopyTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.RubberTreeTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.SaguaroCactusTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.SmallBranchingTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.SmallCanopyTree4BranchTrunkPlacer;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.SpindlyTrunkPlacer;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class TerrestriaTreeConfiguredFeatures {

	// ---------- SANDY TREES ----------
	// Bryce
	public static ConfiguredFeature<TreeFeatureConfig, ?> BRYCE_TREE;

	// Jungle Palm
	public static ConfiguredFeature<TreeFeatureConfig, ?> JUNGLE_PALM_TREE;

	// Yucca Palm
	public static ConfiguredFeature<TreeFeatureConfig, ?> YUCCA_PALM_TREE;

	// Saguaro Cactus
	public static ConfiguredFeature<TreeFeatureConfig, ?> SAGUARO_CACTUS;


	// ---------- NORMAL & MEGA TREES ----------
	// Cypress
	public static ConfiguredFeature<TreeFeatureConfig, ?> CYPRESS_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> MEGA_CYPRESS_TREE;

	// Rubber
	public static ConfiguredFeature<TreeFeatureConfig, ?> RUBBER_TREE;

	// Willow
	public static ConfiguredFeature<TreeFeatureConfig, ?> WILLOW_TREE;

	// Japanese Maple
	public static ConfiguredFeature<TreeFeatureConfig, ?> JAPANESE_MAPLE_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> JAPANESE_MAPLE_SHRUB;

	// Dark Japanese Maple
	public static ConfiguredFeature<TreeFeatureConfig, ?> DARK_JAPANESE_MAPLE_TREE;

	// Sakura
	public static ConfiguredFeature<TreeFeatureConfig, ?> SAKURA_TREE;

	// Rainbow Eucalyptus
	public static ConfiguredFeature<TreeFeatureConfig, ?> RAINBOW_EUCALYPTUS_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_RAINBOW_EUCALYPTUS_TREE;

	// Hemlock
	public static ConfiguredFeature<TreeFeatureConfig, ?> HEMLOCK_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_HEMLOCK_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> MEGA_HEMLOCK_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_HEMLOCK_LOG;

	// Redwood
	public static ConfiguredFeature<TreeFeatureConfig, ?> REDWOOD_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_REDWOOD_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> MEGA_REDWOOD_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_REDWOOD_LOG;

	// Additional features for vanilla trees
	public static ConfiguredFeature<TreeFeatureConfig, ?> OAK_DOT_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> ACACIA_DOT_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> OAK_SHRUB;



	public static void init() {

		// ---------- SANDY TREES ----------
		// Bryce
		BRYCE_TREE = TerrestriaConfiguredFeatures.registerSandyTree(
				"bryce_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.SMALL_OAK_LOG),
						new SpindlyTrunkPlacer(10, 0, 0),
						BlockStateProvider.of(Blocks.OAK_LEAVES),
						new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 0))
						.build());

		// Jungle Palm
		JUNGLE_PALM_TREE = TerrestriaConfiguredFeatures.registerSandyTree(
				"jungle_palm_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(Blocks.JUNGLE_WOOD),
						new BentTrunkPlacer(15, 15, 15),
						BlockStateProvider.of(TerrestriaBlocks.JUNGLE_PALM_LEAVES),
						new PalmFanFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 2))
						.ignoreVines()
						.build());

		// Yucca Palm
		YUCCA_PALM_TREE = TerrestriaConfiguredFeatures.registerSandyTree(
				"yucca_palm_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.log),
						new SmallBranchingTrunkPlacer(6, 2, 1),
						BlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.leaves),
						new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 1)).build());

		// Saguaro Cactus
		SAGUARO_CACTUS = TerrestriaConfiguredFeatures.registerSandyTree(
				"saguaro_cactus",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
						new SaguaroCactusTrunkPlacer(0,0,0),
						BlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
						new NoneFoliagePlacer(),
						new TwoLayersFeatureSize(1, 0, 1))
						.build());



		// ---------- NORMAL & MEGA TREES ----------
		// Cypress
		CYPRESS_TREE = TerrestriaConfiguredFeatures.registerTree(
				"cypress_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
						new StraightTrunkPlacer(7, 3, 0),
						BlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
						new CypressFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		MEGA_CYPRESS_TREE = TerrestriaConfiguredFeatures.registerQuarteredMegaTree(
				"mega_cypress_tree",
				new QuarteredMegaTreeConfig(
						new TreeFeatureConfig.Builder(
								BlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
								new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
								BlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
								new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),
								new TwoLayersFeatureSize(1, 1, 1))
						.ignoreVines()
						.build(),
						BlockStateProvider.of(TerrestriaBlocks.CYPRESS.quarterLog),
						BlockStateProvider.of(TerrestriaBlocks.CYPRESS.wood)));

		// Rubber
		RUBBER_TREE = TerrestriaConfiguredFeatures.registerTree(
				"rubber_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.RUBBER.log),
						new RubberTreeTrunkPlacer(6, 2, 2),
						BlockStateProvider.of(TerrestriaBlocks.RUBBER.leaves),
						new SphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 1))
				.build());

		// Willow
		WILLOW_TREE = TerrestriaConfiguredFeatures.registerTree(
				"willow_tree",
				canopyOf(
						TerrestriaBlocks.WILLOW,
						new CanopyTree4BranchTrunkPlacer(4, 1, 1),
						ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.getDefaultState()))));

		// Japanese Maple
		JAPANESE_MAPLE_TREE = TerrestriaConfiguredFeatures.registerTree(
				"japanese_maple_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
						new CanopyTree4BranchTrunkPlacer(4, 1, 1),
						BlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.leaves),
						new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 1))
				.build());

		JAPANESE_MAPLE_SHRUB = TerrestriaConfiguredFeatures.registerTree("japanese_maple_shrub", shrubOf(
				TerrestriaBlocks.JAPANESE_MAPLE.log,
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES));

		// Dark Japanese Maple
		DARK_JAPANESE_MAPLE_TREE = TerrestriaConfiguredFeatures.registerTree(
				"dark_japanese_maple_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
						new CanopyTree4BranchTrunkPlacer(4, 1, 1),
						BlockStateProvider.of(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES),
						new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
						new TwoLayersFeatureSize(1, 0, 1))
				.build());

		// Sakura
		SAKURA_TREE = TerrestriaConfiguredFeatures.registerTree(
			"sakura_tree",
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(TerrestriaBlocks.SAKURA.log),
					new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
					BlockStateProvider.of(TerrestriaBlocks.SAKURA.leaves),
					new SmallCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
					new TwoLayersFeatureSize(1, 0, 1))
			.decorators(ImmutableList.of(new SakuraTreeDecorator()))
			.build());

		// Rainbow Eucalyptus
		RAINBOW_EUCALYPTUS_TREE = TerrestriaConfiguredFeatures.registerQuarteredMegaTree(
				"rainbow_eucalyptus_tree",
				new QuarteredMegaTreeConfig(
						new TreeFeatureConfig.Builder(
								BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
								new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
								BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
								new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
								new TwoLayersFeatureSize(1, 1, 1))
						.ignoreVines()
						.build(),
						BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog),
						BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood)));

		SMALL_RAINBOW_EUCALYPTUS_TREE = TerrestriaConfiguredFeatures.registerTree(
				"small_rainbow_eucalyptus_tree",
				new TreeFeatureConfig.Builder(
						BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
						new StraightTrunkPlacer(4, 8, 0),
						BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
						new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
						new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		// Hemlock
		HEMLOCK_TREE = TerrestriaConfiguredFeatures.registerTree(
				"hemlock_tree",
				tallSpruceOf(
						TerrestriaBlocks.HEMLOCK,
						24, 4, 3, 2, 5, 1, 11));

		SMALL_HEMLOCK_TREE = TerrestriaConfiguredFeatures.registerTree(
				"small_hemlock_tree",
				spruceOf(
						TerrestriaBlocks.HEMLOCK));

		MEGA_HEMLOCK_TREE = TerrestriaConfiguredFeatures.registerQuarteredMegaTree(
				"mega_hemlock_tree",
				giantSpruceOf(
						TerrestriaBlocks.HEMLOCK,
						32, 8, 7, 2, 5, 1, 11));

		FALLEN_HEMLOCK_LOG = TerrestriaConfiguredFeatures.registerTree(
				"fallen_hemlock_log",
				fallenLogOf(
						TerrestriaBlocks.HEMLOCK,
						new FallenStraightTrunkPlacer(5, 3, 1)));

		// Redwood
		REDWOOD_TREE = TerrestriaConfiguredFeatures.registerTree(
				"redwood_tree",
				tallSpruceOf(
						TerrestriaBlocks.REDWOOD,
						24, 4, 3, 5, 7, 12, 19));

		SMALL_REDWOOD_TREE = TerrestriaConfiguredFeatures.registerTree(
				"small_redwood_tree",
				spruceOf(
						TerrestriaBlocks.REDWOOD));

		MEGA_REDWOOD_TREE = TerrestriaConfiguredFeatures.registerQuarteredMegaTree(
				"mega_redwood_tree",
				giantSpruceOf(
						TerrestriaBlocks.REDWOOD,
						32, 8, 7, 2, 5, 12, 19));

		FALLEN_REDWOOD_LOG = TerrestriaConfiguredFeatures.registerTree(
				"fallen_redwood_log",
				fallenLogOf(
						TerrestriaBlocks.REDWOOD,
						new FallenStraightTrunkPlacer(7, 2, 1)));

		// Additional features for vanilla trees
		OAK_SHRUB = TerrestriaConfiguredFeatures.registerTree(
				"oak_shrub",
				shrubOf(
						Blocks.OAK_LOG,
						Blocks.OAK_LEAVES));

		OAK_DOT_SHRUB = TerrestriaConfiguredFeatures.registerTree(
				"oak_dot_shrub",
				dotShrubOf(
						Blocks.OAK_LOG,
						Blocks.OAK_LEAVES));

		ACACIA_DOT_SHRUB = TerrestriaConfiguredFeatures.registerTree(
				"acacia_dot_shrub",
				dotShrubOf(
						Blocks.ACACIA_LOG,
						Blocks.ACACIA_LEAVES));
	}

	static TreeFeatureConfig canopyOf(WoodBlocks woodBlocks, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log, woodBlocks.leaves, trunkPlacer, decorators);
	}

	static TreeFeatureConfig canopyOf(Block log, Block leaves, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(log),
				trunkPlacer,
				BlockStateProvider.of(leaves),
				new CanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(3, 0 , 1))
				.decorators(decorators)
				.build();
	}

	static TreeFeatureConfig fallenLogOf(WoodBlocks woodBlocks, FallenStraightTrunkPlacer trunk) {
		return fallenLogOf(woodBlocks.log, woodBlocks.leaves, trunk);
	}

	static TreeFeatureConfig fallenLogOf(Block log, Block leaves, FallenStraightTrunkPlacer trunk) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(log),
				trunk,
				BlockStateProvider.of(leaves),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig shrubOf(WoodBlocks woodBlocks) {
		return shrubOf(woodBlocks.log, woodBlocks.leaves);
	}

	static TreeFeatureConfig shrubOf(Block log, Block leaves) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 0, 0),
				BlockStateProvider.of(leaves),
				new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig dotShrubOf(Block log, Block leaves) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 1, 0),
				BlockStateProvider.of(leaves),
				new DotShrubPlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig spruceOf(WoodBlocks woodBlocks) {
		return spruceOf(woodBlocks.log, woodBlocks.leaves);
	}

	static TreeFeatureConfig spruceOf(Block log, Block leaves) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(5, 2, 1),
				BlockStateProvider.of(leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(1, 2), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 1)),
				new TwoLayersFeatureSize(2, 0, 2))
				.ignoreVines()
				.build();
	}

	static TreeFeatureConfig tallSpruceOf(WoodBlocks woodBlocks, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return tallSpruceOf(woodBlocks.log, woodBlocks.leaves, minHeight, extraRandomHeight1, extraRandomHeight2, minLeavesRadius, maxLeavesRadius, minBareHeight, maxBareHeight);
	}

	static TreeFeatureConfig tallSpruceOf(Block log, Block leaves, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new TreeFeatureConfig.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				BlockStateProvider.of(leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(minLeavesRadius, maxLeavesRadius), UniformIntProvider.create(0, 2), UniformIntProvider.create(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 0, 2))
				.ignoreVines()
				.build();
	}

	static QuarteredMegaTreeConfig giantSpruceOf(QuarteredWoodBlocks woodBlocks, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				BlockStateProvider.of(woodBlocks.log),
				new MegaTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				BlockStateProvider.of(woodBlocks.leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntProvider.create(minLeavesRadius, maxLeavesRadius), UniformIntProvider.create(0, 2), UniformIntProvider.create(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 1, 2))
				.ignoreVines()
				.build(),
				BlockStateProvider.of(woodBlocks.quarterLog),
				BlockStateProvider.of(woodBlocks.wood));
	}
}
