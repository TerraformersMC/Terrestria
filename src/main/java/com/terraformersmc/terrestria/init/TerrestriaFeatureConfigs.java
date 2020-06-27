package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.*;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.SandyTreeConfig;
import com.terraformersmc.terrestria.feature.tree.treedecorators.DanglingLeavesTreeDecorator;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.*;
import com.terraformersmc.terrestria.init.helpers.QuarteredWoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class TerrestriaFeatureConfigs {

	private static TreeFeatureConfig RAINBOW_EUCALYPTS_SAPLING_TREE;
	public static TreeFeatureConfig BRYCE_TREE;

	public static TreeFeatureConfig JUNGLE_PALM_TREE;
	public static TreeFeatureConfig WILLOW_TREE;

	public static TreeFeatureConfig SMALL_HEMLOCK_TREE;
	public static TreeFeatureConfig SMALL_REDWOOD_TREE;
	public static TreeFeatureConfig HEMLOCK_TREE;
	public static TreeFeatureConfig REDWOOD_TREE;
	public static TreeFeatureConfig MEGA_CYPRESS_TREE;
	public static TreeFeatureConfig MEGA_HEMLOCK_TREE;
	public static TreeFeatureConfig MEGA_REDWOOD_TREE;
	public static TreeFeatureConfig CYPRESS_TREE;
	public static TreeFeatureConfig RAINBOW_EUCALYPTUS_TREE;
	public static TreeFeatureConfig SAKURA_TREE;

	public static TreeFeatureConfig FALLEN_HEMLOCK_LOG;
	public static TreeFeatureConfig FALLEN_REDWOOD_LOG;

	public static TreeFeatureConfig JAPANESE_MAPLE_SHRUB;
	public static TreeFeatureConfig JAPANESE_MAPLE_TREE;
	public static TreeFeatureConfig DARK_JAPANESE_MAPLE_TREE;
	public static TreeFeatureConfig YUCCA_PALM_TREE;
	public static TreeFeatureConfig OAK_DOT_SHRUB;
	public static TreeFeatureConfig ACATIA_DOT_SHRUB;

	public static void init() {
		BRYCE_TREE = new SandyTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SMALL_OAK_LOG.getDefaultState()),
				new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
				new SmallLogSphereFoliagePlacer(1, 0, 0, 0),
				new SpindlyTrunkPlacer(10, 0, 0),
				new TwoLayersFeatureSize(1, 0, 0))
				.build());
		JUNGLE_PALM_TREE = new SandyTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(Blocks.JUNGLE_WOOD.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState()),
				new PalmFanFoliagePlacer(3, 0, 0, 0),
				new BentTrunkPlacer(15, 15, 15),
				new TwoLayersFeatureSize(1, 0, 2))
				.ignoreVines()
				.build());

		SMALL_HEMLOCK_TREE = spruceOf(TerrestriaBlocks.HEMLOCK);
		SMALL_REDWOOD_TREE = spruceOf(TerrestriaBlocks.REDWOOD);

		HEMLOCK_TREE = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.HEMLOCK.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.HEMLOCK.leaves.getDefaultState()),
				new PyramidFoliagePlacer(0,0,0,0),
				new IncrementedStraightTrunkPlacer(8, 3, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build();

		REDWOOD_TREE = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.REDWOOD.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.REDWOOD.leaves.getDefaultState()),
				new PyramidFoliagePlacer(0,0,0,0),
				new IncrementedStraightTrunkPlacer(12, 4, 2),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.build();

		CYPRESS_TREE = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.leaves.getDefaultState()),
				new CypressFoliagePlacer(0,0,0,0),
				new StraightTrunkPlacer(7, 3, 0),
				new TwoLayersFeatureSize(1, 0, 1))
				.ignoreVines()
				.build();

		MEGA_HEMLOCK_TREE = megaConiferOf(TerrestriaBlocks.HEMLOCK, new QuarteredMegaIncrementedStraightTrunkPlacer(12, 4, 2), new PyramidFoliagePlacer(0, 0, 0, 0));
		MEGA_REDWOOD_TREE = megaConiferOf(TerrestriaBlocks.REDWOOD, new QuarteredMegaIncrementedStraightTrunkPlacer(22, 6, 4), new PyramidFoliagePlacer(0, 0, 0, 0));
		FALLEN_HEMLOCK_LOG = fallenLogOf(TerrestriaBlocks.HEMLOCK, new FallenStraightTrunkPlacer(5, 3, 1));
		FALLEN_REDWOOD_LOG = fallenLogOf(TerrestriaBlocks.REDWOOD, new FallenStraightTrunkPlacer(7, 2, 1));
		JAPANESE_MAPLE_SHRUB = shrubOf(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(), TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState());
		RAINBOW_EUCALYPTUS_TREE = new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState()),
				new LargeOakFoliagePlacer(2, 0, 1, 0, 2),
				new QuarteredMegaCanopyTrunkPlacer(4, 2, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.maxWaterDepth(3)
				.build(),
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.quarterLog.getDefaultState(),
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.wood.getDefaultState());
		RAINBOW_EUCALYPTS_SAPLING_TREE = (new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState()),
				new BlobFoliagePlacer(2, 0, 0, 0, 3),
				new StraightTrunkPlacer(4, 8, 0),
				new TwoLayersFeatureSize(1, 0, 1)))
				.ignoreVines()
				.build();
		SAKURA_TREE = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.SAKURA.leaves.getDefaultState()),
				new SmallCanopyFoliagePlacer(0, 0, 0, 0),
				new SmallCanopyTree4BranchTrunkPlacer(4, 1, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.build();
		JAPANESE_MAPLE_TREE = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.leaves.getDefaultState()),
				new JapaneseCanopyFoliagePlacer(0, 0, 0, 0),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				new TwoLayersFeatureSize(1, 1, 1)
		).build();
		DARK_JAPANESE_MAPLE_TREE = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()),
				new JapaneseCanopyFoliagePlacer(0, 0, 0, 0),
				new CanopyTree4BranchTrunkPlacer(4, 1, 1),
				new TwoLayersFeatureSize(1, 1, 1)
		).build();
		MEGA_CYPRESS_TREE = new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.wood.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.CYPRESS.leaves.getDefaultState()),
				new LargeOakFoliagePlacer(3, 0, 2, 0, 2),
				new QuarteredMegaCanopyTrunkPlacer(5, 2, 1),
				new TwoLayersFeatureSize(1, 1, 1))
				.ignoreVines()
				.maxWaterDepth(3)
				.build(),
				TerrestriaBlocks.CYPRESS.quarterLog.getDefaultState(),
				TerrestriaBlocks.CYPRESS.wood.getDefaultState());
		WILLOW_TREE = canopyOf(TerrestriaBlocks.WILLOW, new CanopyTree4BranchTrunkPlacer(4, 1, 1), ImmutableList.of(new DanglingLeavesTreeDecorator(TerrestriaBlocks.WILLOW.leaves.getDefaultState())));
		YUCCA_PALM_TREE = new SandyTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM.log.getDefaultState()),
				new SimpleBlockStateProvider(TerrestriaBlocks.YUCCA_PALM.leaves.getDefaultState()),
				new SmallLogSphereFoliagePlacer(1, 0, 0, 0),
				new SmallBranchingTrunkPlacer(6, 2, 1),
				new TwoLayersFeatureSize(1, 1, 1)).build());
		OAK_DOT_SHRUB = dotShrubOf(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState());
		ACATIA_DOT_SHRUB = dotShrubOf(Blocks.ACACIA_LOG.getDefaultState(), Blocks.ACACIA_LEAVES.getDefaultState());
	}

	static TreeFeatureConfig canopyOf(WoodBlocks woodBlocks, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return canopyOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), trunkPlacer, decorators);
	}

	static TreeFeatureConfig canopyOf(BlockState log, BlockState leaves, CanopyTree4BranchTrunkPlacer trunkPlacer, List<TreeDecorator> decorators) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new CanopyFoliagePlacer(0, 0, 0, 0),
				trunkPlacer,
				new TwoLayersFeatureSize(1, 0 , 1))
				.decorators(decorators)
				.build();
	}

	static QuarteredMegaTreeConfig megaConiferOf(QuarteredWoodBlocks woodBlocks, QuarteredMegaIncrementedStraightTrunkPlacer logPlacer, PyramidFoliagePlacer foliagePlacer) {
		return megaConiferOf(woodBlocks.log.getDefaultState(), woodBlocks.leaves.getDefaultState(), woodBlocks.quarterLog.getDefaultState(), woodBlocks.wood.getDefaultState(), logPlacer, foliagePlacer);
	}

	static QuarteredMegaTreeConfig megaConiferOf(BlockState log, BlockState leaves, BlockState quarterLog, BlockState wood, QuarteredMegaIncrementedStraightTrunkPlacer logPlacer, PyramidFoliagePlacer foliagePlacer) {
		return new QuarteredMegaTreeConfig(new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				foliagePlacer,
				logPlacer,
				new TwoLayersFeatureSize(1, 0, 1))
				.build(),
				quarterLog,
				wood);
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
				new BushFoliagePlacer(2, 0, 1, 0, 2),
				new StraightTrunkPlacer(1, 0, 0),
				new TwoLayersFeatureSize(0, 0, 0))

				.heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
				.build();
	}

	static TreeFeatureConfig dotShrubOf(BlockState log, BlockState leaves) {
		return new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new DotShrubPlacer(0, 0, 0, 0),
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
				new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1),
				new StraightTrunkPlacer(5, 2, 1),
				new TwoLayersFeatureSize(2, 0, 2))

				.ignoreVines()
				.build();
	}
}
