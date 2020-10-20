package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
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
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

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

	public static ConfiguredFeature<TreeFeatureConfig, ?> DENSE_WOODLAND_TREE;

	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_HEMLOCK_LOG;
	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_REDWOOD_LOG;
	public static ConfiguredFeature<TreeFeatureConfig, ?> FALLEN_OAK_LOG;

	public static ConfiguredFeature<TreeFeatureConfig, ?> JAPANESE_MAPLE_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> JAPANESE_MAPLE_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> DARK_JAPANESE_MAPLE_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> YUCCA_PALM_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> OAK_DOT_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> ACACIA_DOT_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> RUBBER_TREE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SAGUARO_CACTUS_FEATURE;
	public static ConfiguredFeature<TreeFeatureConfig, ?> OAK_SHRUB;
	public static ConfiguredFeature<TreeFeatureConfig, ?> SMALL_OAK_SPRUCE;

	public static ConfiguredFeature<DefaultFeatureConfig, ?> DUM_DUM_HEAD;

	public static void init() {
		BRYCE_TREE = registerSandyTree("bryce_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SMALL_OAK_LOG.getDefaultState()),
				new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
				new SmallLogSphereFoliagePlacer(UniformIntDistribution.of(1), UniformIntDistribution.of(0)),
				new SpindlyTrunkPlacer(10, 0, 0),
				new TwoLayersFeatureSize(1, 0, 0))
				.build());

		JUNGLE_PALM_TREE = registerSandyTree("jungle_palm_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.JUNGLE_WOOD.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState()),
				new PalmFanFoliagePlacer(UniformIntDistribution.of(3), UniformIntDistribution.of(0)),
				new BentTrunkPlacer(15, 15, 15),
				new TwoLayersFeatureSize(1, 0, 2))
				.ignoreVines()
				.build());

		SMALL_HEMLOCK_TREE = registerTree("small_hemlock_tree", spruceOf(TerrestriaBlocks.HEMLOCK));
		SMALL_REDWOOD_TREE = registerTree("small_redwood_tree", spruceOf(TerrestriaBlocks.REDWOOD));

		HEMLOCK_TREE = registerTree("hemlock_tree", tallSpruceOf(TerrestriaBlocks.HEMLOCK, 24, 4, 3, 2, 5, 1, 11));
		REDWOOD_TREE = registerTree("redwood_tree", tallSpruceOf(TerrestriaBlocks.REDWOOD, 24, 4, 3, 5, 2, 12, 7));

		MEGA_HEMLOCK_TREE = registerTree("mega_hemlock_tree", giantSpruceOf(TerrestriaBlocks.HEMLOCK, 32, 8, 7, 2, 5, 1, 11));
		MEGA_REDWOOD_TREE = registerTree("mega_redwood_tree", giantSpruceOf(TerrestriaBlocks.REDWOOD, 32, 8, 7, 2, 5, 12, 7));
		RUBBER_TREE = registerTree("rubber_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RUBBER.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RUBBER.leaves.getDefaultState()),
				new SphereFoliagePlacer(UniformIntDistribution.of(1), UniformIntDistribution.of(0)),
				new RubberTreeTrunkPlacer(6, 2, 2),
				new TwoLayersFeatureSize(1, 1, 1)
				).build());

		CYPRESS_TREE = registerTree("cypress_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.leaves.getDefaultState()),
				new CypressFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
				new StraightTrunkPlacer(7, 3, 0),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		FALLEN_HEMLOCK_LOG = registerTree("fallen_hemlock_log", fallenLogOf(TerrestriaBlocks.HEMLOCK, new FallenStraightTrunkPlacer(5, 3, 1)));
		FALLEN_REDWOOD_LOG = registerTree("fallen_redwood_log", fallenLogOf(TerrestriaBlocks.REDWOOD, new FallenStraightTrunkPlacer(7, 2, 1)));
		FALLEN_OAK_LOG = registerTree("fallen_oak_log", fallenLogOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), new FallenStraightTrunkPlacer(4, 1, 1)));

		JAPANESE_MAPLE_SHRUB = registerTree("japanese_maple_shrub", shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()));
		OAK_SHRUB = registerTree("oak_shrub", shrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()));

		RAINBOW_EUCALYPTUS_TREE = registerTree("rainbow_eucalyptus_tree", new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState()),
				new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.maxWaterDepth(3)
				.build(),
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog.getDefaultState(),
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState(),
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState()));

		SMALL_RAINBOW_EUCALYPTUS_SAPLING_TREE = registerTree("small_rainbow_eucalyptus_tree", (new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState()),
				new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
				new StraightTrunkPlacer(4, 8, 0),
				new TwoLayersFeatureSize(1, 0, 1)))
				.ignoreVines()
				.build());

		SAGUARO_CACTUS_FEATURE = registerSandyTree("saguaro_cactus", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SAGUARO_CACTUS.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAGUARO_CACTUS.getDefaultState()),
				new NoneFoliagePlacer(),
				new SaguaroCactusTrunkPlacer(0,0,0),
				new TwoLayersFeatureSize(1, 1, 1))
				.build());

		SAKURA_TREE = registerTree("sakura_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA.leaves.getDefaultState()),
				new SmallCanopyFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.decorators(ImmutableList.of(new SakuraTreeDecorator()))
				.build());

		JAPANESE_MAPLE_TREE = registerTree("japanese_maple_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.leaves.getDefaultState()),
				new JapaneseCanopyFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				new TwoLayersFeatureSize(1, 1, 1)
		).build());

		DARK_JAPANESE_MAPLE_TREE = registerTree("dark_japanese_maple_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()),
				new JapaneseCanopyFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				new TwoLayersFeatureSize(1, 1, 1)
		).build());

		MEGA_CYPRESS_TREE = registerTree("mega_cypress_tree", new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.leaves.getDefaultState()),
				new LargeOakFoliagePlacer(UniformIntDistribution.of(3), UniformIntDistribution.of(2), 2),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.maxWaterDepth(6)
				.build(),
				TerrestriaBlocks.CYPRESS.quarterLog.getDefaultState(),
				TerrestriaBlocks.CYPRESS.log.getDefaultState(),
				TerrestriaBlocks.CYPRESS.wood.getDefaultState()));

		DENSE_WOODLAND_TREE = registerTree("dense_woodland_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
				new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
				new LargeOakFoliagePlacer(UniformIntDistribution.of(3), UniformIntDistribution.of(2), 2),
				new DenseWoodlandTrunkPlacer(7, 4, 0),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.heightmap(Heightmap.Type.MOTION_BLOCKING).build());

		SMALL_OAK_SPRUCE = registerTree("small_oak_spruce", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
				new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
				new SpruceFoliagePlacer(UniformIntDistribution.of(2, 0), UniformIntDistribution.of(0, 1), UniformIntDistribution.of(1, 1)),
				new StraightTrunkPlacer(4, 1, 0),
				new TwoLayersFeatureSize(2, 0, 2))
				.ignoreVines()
				.build());

		WILLOW_TREE = registerTree("willow_tree", canopyOf(TerrestriaBlocks.WILLOW, new CanopyTree4BranchTrunkPlacer(4, 1, 1), ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.getDefaultState()))));
		YUCCA_PALM_TREE = registerSandyTree("yucca_palm_tree", new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM.leaves.getDefaultState()),
				new SmallLogSphereFoliagePlacer(UniformIntDistribution.of(1), UniformIntDistribution.of(0)),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				new TwoLayersFeatureSize(1, 1, 1)).build());

		OAK_DOT_SHRUB = registerTree("oak_dot_shrub", dotShrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()));
		ACACIA_DOT_SHRUB = registerTree("acacia_dot_shrub", dotShrubOf(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState()));

		DUM_DUM_HEAD = BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Terrestria.MOD_ID, "dum_dum_head"), TerrestriaFeatures.DUM_DUM_HEAD.configure(DefaultFeatureConfig.INSTANCE));
	}

	private static ConfiguredFeature<TreeFeatureConfig, ?> registerTree(String name, TreeFeatureConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = Feature.TREE.configure(config);
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, configured);

		return configured;
	}

	private static ConfiguredFeature<TreeFeatureConfig, ?> registerSandyTree(String name, TreeFeatureConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = TerrestriaFeatures.SANDY_TREE.configure(config);
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, configured);

		return configured;
	}

	static TreeFeatureConfig canopyOf(WoodBlocks woodBlocks, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), trunkPlacer, decorators);
	}

	static TreeFeatureConfig canopyOf(BlockState log, BlockState leaves, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new CanopyFoliagePlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
				trunkPlacer,
				new TwoLayersFeatureSize(1, 0 , 1))
				.decorators(decorators)
				.build();
	}

	static TreeFeatureConfig fallenLogOf(WoodBlocks woodBlocks, FallenStraightTrunkPlacer trunk) {
		return fallenLogOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), trunk);
	}

	static TreeFeatureConfig fallenLogOf(BlockState log, BlockState leaves, FallenStraightTrunkPlacer trunk) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new NoneFoliagePlacer(),
				trunk,
				new TwoLayersFeatureSize(0, 0, 0))

				.build();
	}

	static TreeFeatureConfig shrubOf(WoodBlocks woodBlocks) {
		return shrubOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState());
	}

	static TreeFeatureConfig shrubOf(BlockState log, BlockState leaves) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
				new StraightTrunkPlacer(1, 0, 0),
				new TwoLayersFeatureSize(0, 0, 0))

				.heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
				.build();
	}

	static TreeFeatureConfig dotShrubOf(BlockState log, BlockState leaves) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new DotShrubPlacer(UniformIntDistribution.of(0), UniformIntDistribution.of(0)),
				new StraightTrunkPlacer(1, 1, 0),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	static TreeFeatureConfig spruceOf(WoodBlocks woodBlocks) {
		return spruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState());
	}

	static TreeFeatureConfig spruceOf(BlockState log, BlockState leaves) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntDistribution.of(2, 1), UniformIntDistribution.of(0, 2), UniformIntDistribution.of(1, 1)),
				new StraightTrunkPlacer(5, 2, 1),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	static TreeFeatureConfig tallSpruceOf(WoodBlocks woodBlocks, int height, int randomHeight, int extraRandomHeight, int baseRadius, int randomRadius, int baseBareHeight, int randomBareHeight) {
		return tallSpruceOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), height, randomHeight, extraRandomHeight, baseRadius, randomRadius, baseBareHeight, randomBareHeight);
	}

	static TreeFeatureConfig tallSpruceOf(BlockState log, BlockState leaves, int height, int randomHeight, int extraRandomHeight, int baseRadius, int randomRadius, int baseBareHeight, int randomBareHeight) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new PredictiveSpruceFoliagePlacer(UniformIntDistribution.of(baseRadius, randomRadius), UniformIntDistribution.of(0, 2), UniformIntDistribution.of(baseBareHeight, randomBareHeight)),
				new StraightTrunkPlacer(height, randomHeight, extraRandomHeight),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	static TreeFeatureConfig giantSpruceOf(QuarteredWoodBlocks woodBlocks, int height, int randomHeight, int extraRandomHeight, int baseRadius, int randomRadius, int baseBareHeight, int randomBareHeight) {
		return new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(woodBlocks.log.getDefaultState()),
				new SimpleBlockStateProvider(woodBlocks.leaves.getDefaultState()),
				new PredictiveSpruceFoliagePlacer(UniformIntDistribution.of(baseRadius, randomRadius), UniformIntDistribution.of(0, 2), UniformIntDistribution.of(baseBareHeight, randomBareHeight)),
				new MegaTrunkPlacer(height, randomHeight, extraRandomHeight),
				new TwoLayersFeatureSize(2, 1, 2))

				.ignoreVines()
				.build(),
				woodBlocks.quarterLog.getDefaultState(),
				woodBlocks.log.getDefaultState(),
				woodBlocks.wood.getDefaultState());
	}
}
