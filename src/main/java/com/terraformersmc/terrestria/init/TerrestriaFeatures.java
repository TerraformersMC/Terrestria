package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.misc.CattailFeature;
import com.terraformersmc.terrestria.feature.misc.DumDumHeadFeature;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.treedecorators.SakuraTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class TerrestriaFeatures {
	public static final ResourceKey<Feature> PATCH_VOLCANIC_ISLAND_GRASS = resourceKey("patch_volcanic_island_grass");
	public static final ResourceKey<Feature> PATCH_DEAD_GRASS = resourceKey("patch_dead_grass");
	public static final ResourceKey<Feature> PATCH_OUTBACK_BUSHLAND_GRASS = resourceKey("patch_outback_bushland_grass");
	public static final ResourceKey<Feature> PATCH_OASIS_VEGETATION = resourceKey("patch_oasis_vegetation");
	public static final ResourceKey<Feature> PATCH_LUSH_DESERT_VEGETATION = resourceKey("patch_lush_desert_vegetation");

	public static final ResourceKey<Feature> SMALL_RAINBOW_EUCALYPTUS_TREE = resourceKey("small_rainbow_eucalyptus_tree");
	public static final ResourceKey<Feature> BRYCE_TREE = resourceKey("bryce_tree");

	public static final ResourceKey<Feature> JUNGLE_PALM_TREE = resourceKey("jungle_palm_tree");
	public static final ResourceKey<Feature> WILLOW_TREE = resourceKey("willow_tree");

	public static final ResourceKey<Feature> SMALL_HEMLOCK_TREE = resourceKey("small_hemlock_tree");
	public static final ResourceKey<Feature> SMALL_REDWOOD_TREE = resourceKey("small_redwood_tree");
	public static final ResourceKey<Feature> HEMLOCK_TREE = resourceKey("hemlock_tree");
	public static final ResourceKey<Feature> REDWOOD_TREE = resourceKey("redwood_tree");
	public static final ResourceKey<Feature> MEGA_HEMLOCK_TREE = resourceKey("mega_hemlock_tree");
	public static final ResourceKey<Feature> MEGA_REDWOOD_TREE = resourceKey("mega_redwood_tree");

	public static final ResourceKey<Feature> MEGA_CYPRESS_TREE = resourceKey("mega_cypress_tree");
	public static final ResourceKey<Feature> CYPRESS_TREE = resourceKey("cypress_tree");
	public static final ResourceKey<Feature> RAINBOW_EUCALYPTUS_TREE = resourceKey("rainbow_eucalyptus_tree");
	public static final ResourceKey<Feature> SAKURA_TREE = resourceKey("sakura_tree");

	public static final ResourceKey<Feature> FALLEN_HEMLOCK_LOG = resourceKey("fallen_hemlock_log");
	public static final ResourceKey<Feature> FALLEN_REDWOOD_LOG = resourceKey("fallen_redwood_log");

	public static final ResourceKey<Feature> JAPANESE_MAPLE_SHRUB = resourceKey("japanese_maple_shrub");
	public static final ResourceKey<Feature> JAPANESE_MAPLE_TREE = resourceKey("japanese_maple_tree");
	public static final ResourceKey<Feature> DARK_JAPANESE_MAPLE_TREE = resourceKey("dark_japanese_maple_tree");
	public static final ResourceKey<Feature> YUCCA_PALM_TREE = resourceKey("yucca_palm_tree");
	public static final ResourceKey<Feature> OUTBACK_BUSHLAND_TREES = resourceKey("outback_bushland_trees");
	public static final ResourceKey<Feature> OAK_DOT_SHRUB = resourceKey("oak_dot_shrub");
	public static final ResourceKey<Feature> ACACIA_DOT_SHRUB = resourceKey("acacia_dot_shrub");
	public static final ResourceKey<Feature> RUBBER_TREE = resourceKey("rubber_tree");
	public static final ResourceKey<Feature> SAGUARO_CACTUS = resourceKey("saguaro_cactus");
	public static final ResourceKey<Feature> OAK_SHRUB = resourceKey("oak_shrub");

	public static final ResourceKey<Feature> CATTAIL = resourceKey("cattail");
	public static final ResourceKey<Feature> DUM_DUM_HEAD = resourceKey("dum_dum_head");

	public static void bootstrap(BootstrapContext<Feature> context) {
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		HolderGetter<BlockStateProvider> blockStateProviders = context.lookup(Registries.BLOCK_STATE_PROVIDER);

		Holder<BlockStateProvider> belowTrunkProvider = blockStateProviders.getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);

		TerrestriaRegistry.register(context, PATCH_VOLCANIC_ISLAND_GRASS, new SimpleBlockFeature(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(Blocks.SHORT_GRASS.defaultBlockState(), 1)
					.add(Blocks.FERN.defaultBlockState(), 1)
					.add(TerrestriaBlocks.INDIAN_PAINTBRUSH.defaultBlockState(), 1)
					.add(TerrestriaBlocks.MONSTERAS.defaultBlockState(), 4)
				)
		));

		TerrestriaRegistry.register(context, PATCH_DEAD_GRASS,
				new SimpleBlockFeature(BlockStateProvider.of(TerrestriaBlocks.DEAD_GRASS)));

		TerrestriaRegistry.register(context, PATCH_OUTBACK_BUSHLAND_GRASS, new SimpleBlockFeature(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(TerrestriaBlocks.DEAD_GRASS.defaultBlockState(), 3)
					.add(TerrestriaBlocks.AGAVE.defaultBlockState(), 1)
				)
		));

		TerrestriaRegistry.register(context, PATCH_OASIS_VEGETATION, new SimpleBlockFeature(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(Blocks.FERN.defaultBlockState(), 1)
					.add(Blocks.SHORT_GRASS.defaultBlockState(), 2)
					.add(TerrestriaBlocks.TINY_CACTUS.defaultBlockState(), 1)
					.add(TerrestriaBlocks.AGAVE.defaultBlockState(), 1)
					.add(TerrestriaBlocks.ALOE_VERA.defaultBlockState(), 1)
				)
		));

		TerrestriaRegistry.register(context, PATCH_LUSH_DESERT_VEGETATION, new SimpleBlockFeature(
				new WeightedStateProvider(new WeightedList.Builder<BlockState>()
					.add(TerrestriaBlocks.DEAD_GRASS.defaultBlockState(), 2)
					.add(Blocks.DEAD_BUSH.defaultBlockState(), 1)
					.add(TerrestriaBlocks.TINY_CACTUS.defaultBlockState(), 1)
				)
		));

		TerrestriaRegistry.register(context, BRYCE_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.SMALL_OAK_LOG),
				new SpindlyTrunkPlacer(10, 0, 0),
				BlockStateProvider.of(Blocks.OAK_LEAVES),
				new SmallLogSphereFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 0),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, JUNGLE_PALM_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(Blocks.JUNGLE_WOOD),
				new BentTrunkPlacer(15, 15, 15),
				BlockStateProvider.of(TerrestriaBlocks.JUNGLE_PALM_LEAVES),
				new PalmFanFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 2),
				belowTrunkProvider
		).ignoreVines().build());

		TerrestriaRegistry.register(context, SMALL_HEMLOCK_TREE, spruceOf(
				TerrestriaBlocks.HEMLOCK,
				TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, SMALL_REDWOOD_TREE, spruceOf(
				TerrestriaBlocks.REDWOOD,
				TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, HEMLOCK_TREE, tallSpruceOf(
				TerrestriaBlocks.HEMLOCK,
				TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(),
				belowTrunkProvider,
				24, 4, 3,
				2, 5,
				1, 11
		).build());

		TerrestriaRegistry.register(context, REDWOOD_TREE, tallSpruceOf(
				TerrestriaBlocks.REDWOOD,
				TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(),
				belowTrunkProvider,
				24, 4, 3,
				5, 7,
				12, 19
		).build());

		TerrestriaRegistry.register(context, MEGA_HEMLOCK_TREE, giantSpruceOf(
				TerrestriaBlocks.HEMLOCK,
				TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(),
				belowTrunkProvider,
				32, 8, 7,
				2, 5,
				1, 11
		).build());

		TerrestriaRegistry.register(context, MEGA_REDWOOD_TREE, giantSpruceOf(
				TerrestriaBlocks.REDWOOD,
				TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(),
				belowTrunkProvider,
				32, 8, 7,
				2, 5,
				12, 19
		).build());

		TerrestriaRegistry.register(context, RUBBER_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.RUBBER.log),
				new RubberTreeTrunkPlacer(6, 2, 2),
				BlockStateProvider.of(TerrestriaBlocks.RUBBER.leaves),
				new SphereFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, CYPRESS_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
				new StraightTrunkPlacer(7, 3, 0),
				BlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
				new CypressFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
			).ignoreVines().build());

		TerrestriaRegistry.register(context, FALLEN_HEMLOCK_LOG, fallenLogOf(
				TerrestriaBlocks.HEMLOCK,
				TerrestriaBlocks.HEMLOCK_SAPLING.defaultBlockState(),
				new FallenStraightTrunkPlacer(5, 3, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, FALLEN_REDWOOD_LOG, fallenLogOf(
				TerrestriaBlocks.REDWOOD,
				TerrestriaBlocks.REDWOOD_SAPLING.defaultBlockState(),
				new FallenStraightTrunkPlacer(7, 2, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, JAPANESE_MAPLE_SHRUB, shrubOf(
				TerrestriaBlocks.JAPANESE_MAPLE.log.defaultBlockState(),
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.defaultBlockState(),
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING.defaultBlockState(),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, OAK_SHRUB, shrubOf(
				Blocks.OAK_LOG.defaultBlockState(),
				Blocks.OAK_LEAVES.defaultBlockState(),
				Blocks.OAK_SAPLING.defaultBlockState(),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, RAINBOW_EUCALYPTUS_TREE, new TreeFeature.Builder(
			    BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
				Optional.empty(),
				new TwoLayersFeatureSize(1, 1, 1),
				belowTrunkProvider
		).ignoreVines().build());

		TerrestriaRegistry.register(context, SMALL_RAINBOW_EUCALYPTUS_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log),
				new StraightTrunkPlacer(4, 8, 0),
				BlockStateProvider.of(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves),
				new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).ignoreVines().build());

		TerrestriaRegistry.register(context, SAGUARO_CACTUS, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
				new SaguaroCactusTrunkPlacer(0,0,0),
				BlockStateProvider.of(TerrestriaBlocks.SAGUARO_CACTUS),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, SAKURA_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.SAKURA.log),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				BlockStateProvider.of(TerrestriaBlocks.SAKURA.leaves),
				new SmallCanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).decorators(ImmutableList.of(new SakuraTreeDecorator())).build());

		TerrestriaRegistry.register(context, JAPANESE_MAPLE_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				BlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.leaves),
				new JapaneseCanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, DARK_JAPANESE_MAPLE_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.JAPANESE_MAPLE.log),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				BlockStateProvider.of(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES),
				new JapaneseCanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, MEGA_CYPRESS_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.CYPRESS.log),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				BlockStateProvider.of(TerrestriaBlocks.CYPRESS.leaves),
				new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
				Optional.empty(),
				new TwoLayersFeatureSize(1, 1, 1),
				belowTrunkProvider
		).ignoreVines().build());

		TerrestriaRegistry.register(context, WILLOW_TREE, canopyOf(
				TerrestriaBlocks.WILLOW,
				TerrestriaBlocks.WILLOW_SAPLING.defaultBlockState(),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.defaultBlockState())),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, YUCCA_PALM_TREE, new TreeFeature.Builder(
				BlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.log),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				BlockStateProvider.of(TerrestriaBlocks.YUCCA_PALM.leaves),
				new SmallLogSphereFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, OUTBACK_BUSHLAND_TREES,
				new RandomSelectorFeature(
						ImmutableList.of(
								new WeightedPlacedFeature(placedFeatures.getOrThrow(TreePlacements.ACACIA_CHECKED), 0.95F),
								new WeightedPlacedFeature(placedFeatures.getOrThrow(TerrestriaPlacedFeatures.OUTBACK_YUCCA_PALM), 0.75F)
						),
						placedFeatures.getOrThrow(TreePlacements.FANCY_OAK_CHECKED)));

		TerrestriaRegistry.register(context, OAK_DOT_SHRUB, dotShrubOf(
				Blocks.OAK_LOG.defaultBlockState(),
				Blocks.OAK_LEAVES.defaultBlockState(),
				Blocks.OAK_SAPLING.defaultBlockState(),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, ACACIA_DOT_SHRUB, dotShrubOf(
				Blocks.ACACIA_LOG.defaultBlockState(),
				Blocks.ACACIA_LEAVES.defaultBlockState(),
				Blocks.ACACIA_SAPLING.defaultBlockState(),
				belowTrunkProvider
		).build());

		TerrestriaRegistry.register(context, CATTAIL, new CattailFeature(0.3F, TerrestriaBlocks.CATTAIL.defaultBlockState(), TerrestriaBlocks.TALL_CATTAIL.defaultBlockState()));
		TerrestriaRegistry.register(context, DUM_DUM_HEAD, new DumDumHeadFeature());
	}


	private static TreeFeature.Builder canopyOf(WoodBlocks woodBlocks, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators, Holder<BlockStateProvider> belowTrunkProvider) {
		return canopyOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, trunkPlacer, decorators, belowTrunkProvider);
	}

	private static TreeFeature.Builder canopyOf(BlockState log, BlockState leaves, BlockState sapling, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(log),
				trunkPlacer,
				BlockStateProvider.of(leaves),
				new CanopyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(3, 0 , 1),
				belowTrunkProvider

		).decorators(decorators);
	}

	private static TreeFeature.Builder fallenLogOf(WoodBlocks woodBlocks, BlockState sapling, FallenStraightTrunkPlacer trunk, Holder<BlockStateProvider> belowTrunkProvider) {
		return fallenLogOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, trunk, belowTrunkProvider);
	}

	private static TreeFeature.Builder fallenLogOf(BlockState log, BlockState leaves, BlockState sapling, FallenStraightTrunkPlacer trunk, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(log),
				trunk,
				BlockStateProvider.of(leaves),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(0, 0, 0),
				belowTrunkProvider
		);
	}

	private static TreeFeature.Builder shrubOf(WoodBlocks woodBlocks, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider) {
		return shrubOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, belowTrunkProvider);
	}

	private static TreeFeature.Builder shrubOf(BlockState log, BlockState leaves, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 0, 0),
				BlockStateProvider.of(leaves),
				new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
				new TwoLayersFeatureSize(0, 0, 0),
				belowTrunkProvider
		);
	}

	private static TreeFeature.Builder dotShrubOf(BlockState log, BlockState leaves, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(1, 1, 0),
				BlockStateProvider.of(leaves),
				new DotShrubPlacer(ConstantInt.of(0), ConstantInt.of(0)),
				new TwoLayersFeatureSize(0, 0, 0),
				belowTrunkProvider
		);
	}

	private static TreeFeature.Builder spruceOf(WoodBlocks woodBlocks, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider) {
		return spruceOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, belowTrunkProvider);
	}

	private static TreeFeature.Builder spruceOf(BlockState log, BlockState leaves, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(5, 2, 1),
				BlockStateProvider.of(leaves),
				new PredictiveSpruceFoliagePlacer(UniformInt.of(1, 2), UniformInt.of(0, 2), UniformInt.of(1, 1)),
				new TwoLayersFeatureSize(2, 0, 2),
				belowTrunkProvider
		).ignoreVines();
	}

	private static TreeFeature.Builder tallSpruceOf(WoodBlocks woodBlocks, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return tallSpruceOf(woodBlocks.log.defaultBlockState(), woodBlocks.leaves.defaultBlockState(), sapling, belowTrunkProvider, minHeight, extraRandomHeight1, extraRandomHeight2, minLeavesRadius, maxLeavesRadius, minBareHeight, maxBareHeight);
	}

	private static TreeFeature.Builder tallSpruceOf(BlockState log, BlockState leaves, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(log),
				new StraightTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				BlockStateProvider.of(leaves),
				new PredictiveSpruceFoliagePlacer(UniformInt.of(minLeavesRadius, maxLeavesRadius), UniformInt.of(0, 2), UniformInt.of(minBareHeight, maxBareHeight)),
				new TwoLayersFeatureSize(2, 0, 2),
				belowTrunkProvider
		).ignoreVines();
	}

	private static TreeFeature.Builder giantSpruceOf(WoodBlocks woodBlocks, BlockState sapling, Holder<BlockStateProvider> belowTrunkProvider, int minHeight, int extraRandomHeight1, int extraRandomHeight2, int minLeavesRadius, int maxLeavesRadius, int minBareHeight, int maxBareHeight) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(woodBlocks.log),
				new MegaTrunkPlacer(minHeight, extraRandomHeight1, extraRandomHeight2),
				BlockStateProvider.of(woodBlocks.leaves),
				new PredictiveSpruceFoliagePlacer(UniformInt.of(minLeavesRadius, maxLeavesRadius), UniformInt.of(0, 2), UniformInt.of(minBareHeight, maxBareHeight)),
				Optional.empty(),
				new TwoLayersFeatureSize(2, 1, 2),
				belowTrunkProvider
		).ignoreVines();
	}

	private static ResourceKey<Feature> resourceKey(String path) {
		return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}
}
