package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class TerrestriaConfiguredFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_VOLCANIC_ISLAND_GRASS = resourceKey("patch_volcanic_island_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DEAD_GRASS = resourceKey("patch_dead_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_OUTBACK_BUSHLAND_GRASS = resourceKey("patch_outback_bushland_grass");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_OASIS_VEGETATION = resourceKey("patch_oasis_vegetation");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LUSH_DESERT_VEGETATION = resourceKey("patch_lush_desert_vegetation");

	public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_RAINBOW_EUCALYPTUS_TREE = resourceKey("small_rainbow_eucalyptus_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BRYCE_TREE = resourceKey("bryce_tree");

	public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_PALM_TREE = resourceKey("jungle_palm_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE = resourceKey("willow_tree");

	public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_HEMLOCK_TREE = resourceKey("small_hemlock_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_REDWOOD_TREE = resourceKey("small_redwood_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> HEMLOCK_TREE = resourceKey("hemlock_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = resourceKey("redwood_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_HEMLOCK_TREE = resourceKey("mega_hemlock_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_REDWOOD_TREE = resourceKey("mega_redwood_tree");

	public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_CYPRESS_TREE = resourceKey("mega_cypress_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE = resourceKey("cypress_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_EUCALYPTUS_TREE = resourceKey("rainbow_eucalyptus_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_TREE = resourceKey("sakura_tree");

	public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_HEMLOCK_LOG = resourceKey("fallen_hemlock_log");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_REDWOOD_LOG = resourceKey("fallen_redwood_log");

	public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_SHRUB = resourceKey("japanese_maple_shrub");
	public static final ResourceKey<ConfiguredFeature<?, ?>> JAPANESE_MAPLE_TREE = resourceKey("japanese_maple_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_JAPANESE_MAPLE_TREE = resourceKey("dark_japanese_maple_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> YUCCA_PALM_TREE = resourceKey("yucca_palm_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OUTBACK_BUSHLAND_TREES = resourceKey("outback_bushland_trees");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_DOT_SHRUB = resourceKey("oak_dot_shrub");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA_DOT_SHRUB = resourceKey("acacia_dot_shrub");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RUBBER_TREE = resourceKey("rubber_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAGUARO_CACTUS = resourceKey("saguaro_cactus");
	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_SHRUB = resourceKey("oak_shrub");

	public static final ResourceKey<ConfiguredFeature<?, ?>> CATTAIL = resourceKey("cattail");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DUM_DUM_HEAD = resourceKey("dum_dum_head");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

		TerrestriaRegistry.register(context, PATCH_VOLCANIC_ISLAND_GRASS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(Blocks.SHORT_GRASS.defaultBlockState(), 1)
					.add(Blocks.FERN.defaultBlockState(), 1)
					.add(TerrestriaBlocks.INDIAN_PAINTBRUSH.defaultBlockState(), 1)
					.add(TerrestriaBlocks.MONSTERAS.defaultBlockState(), 4)
				)
		));

		TerrestriaRegistry.register(context, PATCH_DEAD_GRASS, Feature.SIMPLE_BLOCK,
				new SimpleBlockConfiguration(BlockStateProvider.simple(TerrestriaBlocks.DEAD_GRASS)));

		TerrestriaRegistry.register(context, PATCH_OUTBACK_BUSHLAND_GRASS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(TerrestriaBlocks.DEAD_GRASS.defaultBlockState(), 3)
					.add(TerrestriaBlocks.AGAVE.defaultBlockState(), 1)
				)
		));

		TerrestriaRegistry.register(context, PATCH_OASIS_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(Blocks.FERN.defaultBlockState(), 1)
					.add(Blocks.SHORT_GRASS.defaultBlockState(), 2)
					.add(TerrestriaBlocks.TINY_CACTUS.defaultBlockState(), 1)
					.add(TerrestriaBlocks.AGAVE.defaultBlockState(), 1)
					.add(TerrestriaBlocks.ALOE_VERA.defaultBlockState(), 1)
				)
		));

		TerrestriaRegistry.register(context, PATCH_LUSH_DESERT_VEGETATION, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(TerrestriaBlocks.DEAD_GRASS.defaultBlockState(), 2)
					.add(Blocks.DEAD_BUSH.defaultBlockState(), 1)
					.add(TerrestriaBlocks.TINY_CACTUS.defaultBlockState(), 1)
				)
		));

		TerrestriaRegistry.register(context, BRYCE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.SMALL_OAK_LOG),
				new SpindlyTrunkPlacer(10, 0, 0),
				SimpleStateProvider.simple(Blocks.OAK_LEAVES),
				new SmallLogSphereFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 0))
				.build());

		TerrestriaRegistry.register(context, JUNGLE_PALM_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(Blocks.JUNGLE_WOOD),
				new BentTrunkPlacer(15, 15, 15),
				SimpleStateProvider.simple(TerrestriaBlocks.JUNGLE_PALM_LEAVES),
				new PalmFanFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 2))
				.ignoreVines()
				.build());

		TerrestriaRegistry.register(context, SMALL_HEMLOCK_TREE, Feature.TREE, spruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState()));
		TerrestriaRegistry.register(context, SMALL_REDWOOD_TREE, Feature.TREE, spruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState()));

		TerrestriaRegistry.register(context, HEMLOCK_TREE, Feature.TREE, tallSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(), 24, 4, 3, 2, 5, 1, 11));
		TerrestriaRegistry.register(context, REDWOOD_TREE, Feature.TREE, tallSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(), 24, 4, 3, 5, 7, 12, 19));

		TerrestriaRegistry.register(context, MEGA_HEMLOCK_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, giantSpruceOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(), 32, 8, 7, 2, 5, 1, 11));
		TerrestriaRegistry.register(context, MEGA_REDWOOD_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, giantSpruceOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(), 32, 8, 7, 2, 5, 12, 19));

		TerrestriaRegistry.register(context, RUBBER_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.RUBBER.log),
				new RubberTreeTrunkPlacer(6, 2, 2),
				SimpleStateProvider.simple(TerrestriaBlocks.RUBBER.leaves),
				new SphereFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1)
				).build());

		TerrestriaRegistry.register(context, CYPRESS_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.CYPRESS.log),
				new StraightTrunkPlacer(7, 3, 0),
				SimpleStateProvider.simple(TerrestriaBlocks.CYPRESS.leaves),
				new CypressFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build());

		TerrestriaRegistry.register(context, FALLEN_HEMLOCK_LOG, Feature.TREE, fallenLogOf(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(), new FallenStraightTrunkPlacer(5, 3, 1)));
		TerrestriaRegistry.register(context, FALLEN_REDWOOD_LOG, Feature.TREE, fallenLogOf(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(), new FallenStraightTrunkPlacer(7, 2, 1)));

		TerrestriaRegistry.register(context, JAPANESE_MAPLE_SHRUB, Feature.TREE, shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.defaultBlockState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.defaultBlockState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING.defaultBlockState()));
		TerrestriaRegistry.register(context, OAK_SHRUB, Feature.TREE, shrubOf(Blocks.OAK_LOG.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState(), Blocks.OAK_SAPLING.defaultBlockState()));

		TerrestriaRegistry.register(context, RAINBOW_EUCALYPTUS_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, new QuarteredMegaTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				SimpleStateProvider.simple(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleStateProvider.simple(TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog),
				SimpleStateProvider.simple(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood)));

		TerrestriaRegistry.register(context, SMALL_RAINBOW_EUCALYPTUS_TREE, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new StraightTrunkPlacer(4, 8, 0),
				SimpleStateProvider.simple(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1)))
				.ignoreVines()
				.build());

		TerrestriaRegistry.register(context, SAGUARO_CACTUS, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.SAGUARO_CACTUS),
				new SaguaroCactusTrunkPlacer(0,0,0),
				SimpleStateProvider.simple(TerrestriaBlocks.SAGUARO_CACTUS),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		TerrestriaRegistry.register(context, SAKURA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.SAKURA.log),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleStateProvider.simple(TerrestriaBlocks.SAKURA.leaves),
				new SmallCanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.decorators(ImmutableList.of(new SakuraTreeDecorator()))
				.build());

		TerrestriaRegistry.register(context, JAPANESE_MAPLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleStateProvider.simple(TerrestriaBlocks.JAPANESE_MAPLE.leaves),
				new JapaneseCanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		TerrestriaRegistry.register(context, DARK_JAPANESE_MAPLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				SimpleStateProvider.simple(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES),
				new JapaneseCanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1))
				.build());

		TerrestriaRegistry.register(context, MEGA_CYPRESS_TREE, TerrestriaFeatures.QUARTERED_MEGA_TREE, new QuarteredMegaTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.CYPRESS.log),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				SimpleStateProvider.simple(TerrestriaBlocks.CYPRESS.leaves),
				new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build(),
				SimpleStateProvider.simple(TerrestriaBlocks.CYPRESS.quarterLog),
				SimpleStateProvider.simple(TerrestriaBlocks.CYPRESS.wood)));

		TerrestriaRegistry.register(context, WILLOW_TREE, Feature.TREE, canopyOf(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING.defaultBlockState(), new CanopyTree4BranchTrunkPlacer(4, 1, 1), ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.defaultBlockState()))));

		TerrestriaRegistry.register(context, YUCCA_PALM_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(TerrestriaBlocks.YUCCA_PALM.log),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				SimpleStateProvider.simple(TerrestriaBlocks.YUCCA_PALM.leaves),
				new SmallLogSphereFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1)).build());

		TerrestriaRegistry.register(context, OUTBACK_BUSHLAND_TREES, Feature.RANDOM_SELECTOR,
				new RandomFeatureConfiguration(
						ImmutableList.of(
								new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.ACACIA_CHECKED), 0.95F),
								new WeightedPlacedFeature(placedFeatures.getOrThrow(TerrestriaPlacedFeatures.OUTBACK_YUCCA_PALM), 0.75F)
						),
						placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_CHECKED)));

		TerrestriaRegistry.register(context, OAK_DOT_SHRUB, Feature.TREE, dotShrubOf(Blocks.OAK_LOG.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState(), Blocks.OAK_SAPLING.defaultBlockState()));
		TerrestriaRegistry.register(context, ACACIA_DOT_SHRUB, Feature.TREE, dotShrubOf(Blocks.ACACIA_LOG.defaultBlockState(), Blocks.ACACIA_LEAVES.defaultBlockState(), Blocks.ACACIA_SAPLING.defaultBlockState()));

		TerrestriaRegistry.register(context, CATTAIL, TerrestriaFeatures.CATTAIL, new ProbabilityFeatureConfiguration(0.3F));
		TerrestriaRegistry.register(context, DUM_DUM_HEAD, TerrestriaFeatures.DUM_DUM_HEAD, NoneFeatureConfiguration.INSTANCE);
	}

	private static TreeConfiguration canopyOf(WoodBlocks woodBlocks, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, trunkPlacer, decorators);
	}

	private static TreeConfiguration canopyOf(BlockState log, BlockState leaves, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(log),
				trunkPlacer,
				SimpleStateProvider.simple(leaves),
				new CanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(3, 0 , 1))
				.decorators(decorators)
				.build();
	}

	private static TreeConfiguration fallenLogOf(WoodBlocks woodBlocks, BlockState sapling, FallenStraightTrunkPlacer trunk) {
		return fallenLogOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, trunk);
	}

	private static TreeConfiguration fallenLogOf(BlockState log, BlockState leaves, BlockState sapling, FallenStraightTrunkPlacer trunk) {
		return new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(log),
				trunk,
				SimpleStateProvider.simple(leaves),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(0, 0, 0))

				.build();
	}

	private static TreeConfiguration shrubOf(WoodBlocks woodBlocks, BlockState sapling) {
		return shrubOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling);
	}

	private static TreeConfiguration shrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(log),
				new StraightTrunkPlacer(1, 0, 0),
				SimpleStateProvider.simple(leaves),
				new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	private static TreeConfiguration dotShrubOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(log),
				new StraightTrunkPlacer(1, 1, 0),
				SimpleStateProvider.simple(leaves),
				new DotShrubPlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(0, 0, 0))
				.build();
	}

	private static TreeConfiguration spruceOf(WoodBlocks woodBlocks, BlockState sapling) {
		return spruceOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling);
	}

	private static TreeConfiguration spruceOf(BlockState log, BlockState leaves, BlockState sapling) {
		return new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(log),
				new StraightTrunkPlacer(5, 2, 1),
				SimpleStateProvider.simple(leaves),
				new PredictiveSpruceFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2), UniformInt.of(1, 1)),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	private static TreeConfiguration tallSpruceOf(WoodBlocks woodBlocks, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return tallSpruceOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, minHeight, extraRandomHeight1, extraRandomHeight2, minLeavesRadius, maxLeavesRadius, minBareHeight, maxBareHeight);
	}

	private static TreeConfiguration tallSpruceOf(BlockState log, BlockState leaves, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(log),
				new StraightTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				SimpleStateProvider.simple(leaves),
				new PredictiveSpruceFoliagePlacer(UniformInt.of(minLeavesRadius, maxLeavesRadius), UniformInt.of(0, 2), UniformInt.of(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}

	private static QuarteredMegaTreeConfig giantSpruceOf(WoodBlocks woodBlocks, BlockState sapling, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		if (!woodBlocks.hasQuarterLog()) {
			throw new IllegalArgumentException("giantSpruceOf() requires WoodBlocks with defined Quarter Logs: " + woodBlocks.getName());
		}
		return new QuarteredMegaTreeConfig(new TreeConfiguration.TreeConfigurationBuilder(
				SimpleStateProvider.simple(woodBlocks.log),
				new MegaTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				SimpleStateProvider.simple(woodBlocks.leaves),
				new PredictiveSpruceFoliagePlacer(UniformInt.of(minLeavesRadius, maxLeavesRadius), UniformInt.of(0, 2), UniformInt.of(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 1, 2))
				.ignoreVines()
				.build(),
				SimpleStateProvider.simple(woodBlocks.quarterLog),
				SimpleStateProvider.simple(woodBlocks.wood));
	}

	private static ResourceKey<ConfiguredFeature<?, ?>> resourceKey(String path) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}
}
