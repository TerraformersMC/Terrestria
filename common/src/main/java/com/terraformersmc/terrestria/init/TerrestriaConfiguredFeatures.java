package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class TerrestriaConfiguredFeatures {
	public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_VOLCANIC_ISLAND_GRASS = createRegistryKey("patch_volcanic_island_grass");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_DEAD_GRASS = createRegistryKey("patch_dead_grass");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_OUTBACK_BUSHLAND_GRASS = createRegistryKey("patch_outback_bushland_grass");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_OASIS_VEGETATION = createRegistryKey("patch_oasis_vegetation");
	public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_LUSH_DESERT_VEGETATION = createRegistryKey("patch_lush_desert_vegetation");

	public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_RAINBOW_EUCALYPTUS_TREE = createRegistryKey("small_rainbow_eucalyptus_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> BRYCE_TREE = createRegistryKey("bryce_tree");

	public static final RegistryKey<ConfiguredFeature<?, ?>> JUNGLE_PALM_TREE = createRegistryKey("jungle_palm_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> WILLOW_TREE = createRegistryKey("willow_tree");

	public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_HEMLOCK_TREE = createRegistryKey("small_hemlock_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_REDWOOD_TREE = createRegistryKey("small_redwood_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> HEMLOCK_TREE = createRegistryKey("hemlock_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = createRegistryKey("redwood_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_HEMLOCK_TREE = createRegistryKey("mega_hemlock_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_REDWOOD_TREE = createRegistryKey("mega_redwood_tree");

	public static final RegistryKey<ConfiguredFeature<?, ?>> MEGA_CYPRESS_TREE = createRegistryKey("mega_cypress_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> CYPRESS_TREE = createRegistryKey("cypress_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_TREE = createRegistryKey("rainbow_eucalyptus_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> SAKURA_TREE = createRegistryKey("sakura_tree");

	public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_HEMLOCK_LOG = createRegistryKey("fallen_hemlock_log");
	public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_REDWOOD_LOG = createRegistryKey("fallen_redwood_log");

	public static final RegistryKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_SHRUB = createRegistryKey("japanese_maple_shrub");
	public static final RegistryKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_TREE = createRegistryKey("japanese_maple_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> DARK_JAPANESE_MAPLE_TREE = createRegistryKey("dark_japanese_maple_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> YUCCA_PALM_TREE = createRegistryKey("yucca_palm_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> OUTBACK_BUSHLAND_TREES = createRegistryKey("outback_bushland_trees");
	public static final RegistryKey<ConfiguredFeature<?, ?>> OAK_DOT_SHRUB = createRegistryKey("oak_dot_shrub");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ACACIA_DOT_SHRUB = createRegistryKey("acacia_dot_shrub");
	public static final RegistryKey<ConfiguredFeature<?, ?>> RUBBER_TREE = createRegistryKey("rubber_tree");
	public static final RegistryKey<ConfiguredFeature<?, ?>> SAGUARO_CACTUS = createRegistryKey("saguaro_cactus");
	public static final RegistryKey<ConfiguredFeature<?, ?>> OAK_SHRUB = createRegistryKey("oak_shrub");

	public static final RegistryKey<ConfiguredFeature<?, ?>> CATTAIL = createRegistryKey("cattail");
	public static final RegistryKey<ConfiguredFeature<?, ?>> DUM_DUM_HEAD = createRegistryKey("dum_dum_head");

	public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> registerable) {
		RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatures = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
		RegistryEntryLookup<PlacedFeature> placedFeatures = registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

		register(registerable, PATCH_VOLCANIC_ISLAND_GRASS, Feature.RANDOM_PATCH,
				new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
								new WeightedBlockStateProvider(createStatePoolBuilder()
										.add(Blocks.SHORT_GRASS.getDefaultState(), 1)
										.add(Blocks.FERN.getDefaultState(), 1)
										.add(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
										.add(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
										.build())),
						BlockPredicate.IS_AIR)));

		register(registerable, PATCH_DEAD_GRASS, Feature.RANDOM_PATCH,
				new RandomPatchFeatureConfig(4, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
								BlockStateProvider.of(TerrestriaBlocks.DEAD_GRASS.getDefaultState())),
						BlockPredicate.IS_AIR)));

		register(registerable, PATCH_OUTBACK_BUSHLAND_GRASS, Feature.RANDOM_PATCH,
				new RandomPatchFeatureConfig(4, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
								new WeightedBlockStateProvider(createStatePoolBuilder()
										.add(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
										.add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
										.build())),
						BlockPredicate.IS_AIR)));

		register(registerable, PATCH_OASIS_VEGETATION, Feature.RANDOM_PATCH,
				new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
								new WeightedBlockStateProvider(createStatePoolBuilder()
										.add(Blocks.FERN.getDefaultState(), 1)
										.add(Blocks.SHORT_GRASS.getDefaultState(), 2)
										.add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
										.add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
										.add(TerrestriaBlocks.ALOE_VERA.getDefaultState(), 1)
										.build())),
						BlockPredicate.IS_AIR)));

		register(registerable, PATCH_LUSH_DESERT_VEGETATION, Feature.RANDOM_PATCH,
				new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
								new WeightedBlockStateProvider(createStatePoolBuilder()
										.add(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 2)
										.add(Blocks.DEAD_BUSH.getDefaultState(), 1)
										.add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
										.build())),
						BlockPredicate.IS_AIR)));

		register(registerable, BRYCE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.SMALL_OAK_LOG),
				new SpindlyTrunkPlacer(10, 0, 0),
				SimpleBlockStateProvider.of(Blocks.OAK_LEAVES),
				new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 0))
				.build());

		register(registerable, JUNGLE_PALM_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(Blocks.JUNGLE_WOOD),
				new BentTrunkPlacer(15, 15, 15),
				SimpleBlockStateProvider.of(TerrestriaBlocks.JUNGLE_PALM_LEAVES),
				new PalmFanFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 2))
				.ignoreVines()
				.build());

		register(registerable, SMALL_HEMLOCK_TREE, Feature.TREE, spruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState()));
		register(registerable, SMALL_REDWOOD_TREE, Feature.TREE, spruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState()));

		register(registerable, HEMLOCK_TREE, Feature.TREE, tallSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), 24, 4, 3, 2, 5, 1, 11));
		register(registerable, REDWOOD_TREE, Feature.TREE, tallSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), 24, 4, 3, 5, 7, 12, 19));

		register(registerable, MEGA_HEMLOCK_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, giantSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), 32, 8, 7, 2, 5, 1, 11));
		register(registerable, MEGA_REDWOOD_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, giantSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), 32, 8, 7, 2, 5, 12, 19));

		register(registerable, RUBBER_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.RUBBER.log),
				new RubberTreeTrunkPlacer(6, 2, 2),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RUBBER.leaves),
				new SphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)
				).build());

		register(registerable, CYPRESS_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
				new StraightTrunkPlacer(7, 3, 0),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
				new CypressFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		register(registerable, FALLEN_HEMLOCK_LOG, Feature.TREE, fallenLogOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(5, 3, 1)));
		register(registerable, FALLEN_REDWOOD_LOG, Feature.TREE, fallenLogOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.getDefaultState(), new FallenStraightTrunkPlacer(7, 2, 1)));

		register(registerable, JAPANESE_MAPLE_SHRUB, Feature.TREE, shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING.getDefaultState()));
		register(registerable, OAK_SHRUB, Feature.TREE, shrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState()));

		register(registerable, RAINBOW_EUCALYPTUS_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood)));

		register(registerable, SMALL_RAINBOW_EUCALYPTUS_TREE, Feature.TREE, (new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new StraightTrunkPlacer(4, 8, 0),
				SimpleBlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
				new TwoLayersFeatureSize(1, 0, 1)))
				.ignoreVines()
				.build());

		register(registerable, SAGUARO_CACTUS, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
				new SaguaroCactusTrunkPlacer(0,0,0),
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		register(registerable, SAKURA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAKURA.log),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.SAKURA.leaves),
				new SmallCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.decorators(ImmutableList.of(new SakuraTreeDecorator()))
				.build());

		register(registerable, JAPANESE_MAPLE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.leaves),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		register(registerable, DARK_JAPANESE_MAPLE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES),
				new JapaneseCanopyFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		register(registerable, MEGA_CYPRESS_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
				new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.quarterLog),
				SimpleBlockStateProvider.of(TerrestriaBlocks.CYPRESS.wood)));

		register(registerable, WILLOW_TREE, Feature.TREE, canopyOf(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING.getDefaultState(), new CanopyTree4BranchTrunkPlacer(4, 1, 1), ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.getDefaultState()))));

		register(registerable, YUCCA_PALM_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
				SimpleBlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.log),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				SimpleBlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.leaves),
				new SmallLogSphereFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
				new TwoLayersFeatureSize(1, 0, 1)).build());

		register(registerable, OUTBACK_BUSHLAND_TREES, Feature.RANDOM_SELECTOR,
				new RandomFeatureConfig(
						ImmutableList.of(
								new RandomFeatureEntry(placedFeatures.getOrThrow(TreePlacedFeatures.ACACIA_CHECKED), 0.95F),
								new RandomFeatureEntry(placedFeatures.getOrThrow(TerrestriaPlacedFeatures.OUTBACK_YUCCA_PALM), 0.75F)
						),
						placedFeatures.getOrThrow(TreePlacedFeatures.FANCY_OAK_CHECKED)));

		register(registerable, OAK_DOT_SHRUB, Feature.TREE, dotShrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState(), Blocks.OAK_SAPLING.getDefaultState()));
		register(registerable, ACACIA_DOT_SHRUB, Feature.TREE, dotShrubOf(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState(), Blocks.ACACIA_SAPLING.getDefaultState()));

		register(registerable, CATTAIL, TerrestriaFeatures.CATTAIL, new ProbabilityConfig(0.3F));
		register(registerable, DUM_DUM_HEAD, TerrestriaFeatures.DUM_DUM_HEAD, DefaultFeatureConfig.INSTANCE);
	}

	private static DataPool.Builder<BlockState> createStatePoolBuilder() {
		return DataPool.builder();
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

	static QuarteredMegaTreeConfig giantSpruceOf(WoodBlocks woodBlocks, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		if (!woodBlocks.hasQuarterLog()) {
			throw new IllegalArgumentException("giantSpruceOf() requires WoodBlocks with defined Quarter Logs: " + woodBlocks.getName());
		}
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

	public static RegistryKey<ConfiguredFeature<?, ?>> createRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Terrestria.MOD_ID, name));
	}

	public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		ConfiguredFeatures.register(registerable, key, feature, config);
	}
}
