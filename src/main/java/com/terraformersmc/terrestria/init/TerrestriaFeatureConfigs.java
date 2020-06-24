package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terraform.feature.FallenLogFeatureConfig;
import com.terraformersmc.terrestria.feature.trees.decorator.FixSmallLogsDecorator;
import com.terraformersmc.terrestria.feature.trees.decorator.SakuraLeafPileDecorator;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.decorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.decorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class TerrestriaFeatureConfigs {
	public static final TreeFeatureConfig REDWOOD = basic(TerrestriaBlocks.REDWOOD);
	public static final TreeFeatureConfig MEGA_REDWOOD = basicMega(TerrestriaBlocks.REDWOOD);
	public static final TreeFeatureConfig HEMLOCK = basic(TerrestriaBlocks.HEMLOCK);
	public static final TreeFeatureConfig MEGA_HEMLOCK = basicMega(TerrestriaBlocks.HEMLOCK);
	public static final TreeFeatureConfig RUBBER = basic(TerrestriaBlocks.RUBBER);
	public static final TreeFeatureConfig CYPRESS = basic(TerrestriaBlocks.CYPRESS);
	public static final TreeFeatureConfig MEGA_CYPRESS = basicMega(TerrestriaBlocks.CYPRESS);
	public static final TreeFeatureConfig WILLOW = basic(TerrestriaBlocks.WILLOW);
	public static final TreeFeatureConfig JAPANESE_MAPLE = basic(TerrestriaBlocks.JAPANESE_MAPLE);

	public static final TreeFeatureConfig JAPANESE_MAPLE_SHRUB = basic(
			TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
			TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()
	);

	public static final TreeFeatureConfig DARK_JAPANESE_MAPLE = basic(
			TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
			TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()
	);

	public static final TreeFeatureConfig MEGA_RAINBOW_EUCALYPTUS = basicMega(TerrestriaBlocks.RAINBOW_EUCALYPTUS);
	public static final TreeFeatureConfig SAKURA = sakura(
			TerrestriaBlocks.SAKURA,
			ImmutableList.of(
					new SakuraLeafPileDecorator(4),
					new FixSmallLogsDecorator()
			)
	);

	public static final TreeFeatureConfig JUNGLE_PALM = basic(Blocks.JUNGLE_LOG.getDefaultState(), TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState());

	public static final TreeFeatureConfig TINY_REDWOOD = spruce(TerrestriaBlocks.REDWOOD);
	public static final TreeFeatureConfig TINY_HEMLOCK = spruce(TerrestriaBlocks.HEMLOCK);

	public static final FallenLogFeatureConfig FALLEN_HEMLOCK_LOG = fallenLog(TerrestriaBlocks.HEMLOCK);
	public static final FallenLogFeatureConfig FALLEN_REDWOOD_LOG = fallenLog(TerrestriaBlocks.REDWOOD);

	public static final TreeFeatureConfig RAINBOW_EUCALYPTUS = basicJungle(TerrestriaBlocks.RAINBOW_EUCALYPTUS);

	private static TreeFeatureConfig spruce(WoodBlocks blocks) {
		return spruce(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static TreeFeatureConfig spruce(BlockState log, BlockState leaves) {
		TreeFeatureConfig.Builder builder = new TreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(log),
				new SimpleBlockStateProvider(leaves),
				new SpruceFoliagePlacer(2, 1, 0, 2, 1, 1),
				new StraightTrunkPlacer(6, 3, 2),
				new TwoLayersFeatureSize(2, 0, 2));
		return builder.ignoreVines().build();
	}

	private static FallenLogFeatureConfig fallenLog(WoodBlocks blocks) {
		return fallenLog(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static FallenLogFeatureConfig fallenLog(BlockState log, BlockState leaves) {
		SimpleBlockStateProvider logProvider = new SimpleBlockStateProvider(log);
		SimpleBlockStateProvider leavesProvider = new SimpleBlockStateProvider(leaves);

		return new FallenLogFeatureConfig.Builder(logProvider, leavesProvider).baseLength(5).lengthRandom(8).build();
	}

	private static TreeFeatureConfig basic(WoodBlocks blocks) {
		return basic(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static TreeFeatureConfig todoFix(BlockState log, BlockState leaves) {
		SimpleBlockStateProvider logProvider = new SimpleBlockStateProvider(log);
		SimpleBlockStateProvider leavesProvider = new SimpleBlockStateProvider(leaves);

		return new TreeFeatureConfig.Builder(logProvider, leavesProvider, new BushFoliagePlacer(2, 0, 1, 0, 2), new StraightTrunkPlacer(1, 0, 0), new TwoLayersFeatureSize(0, 0, 0)).build();
	}

	private static TreeFeatureConfig basicMega(WoodBlocks blocks) {
		return basicMega(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static TreeFeatureConfig basicMega(BlockState log, BlockState leaves) {
		SimpleBlockStateProvider logProvider = new SimpleBlockStateProvider(log);
		SimpleBlockStateProvider leavesProvider = new SimpleBlockStateProvider(leaves);

		return new TreeFeatureConfig.Builder(logProvider, leavesProvider).build();
	}

	private static TreeFeatureConfig sakura(WoodBlocks blocks, List<TreeDecorator> decorators) {
		return sakura(blocks.log.getDefaultState(), blocks.leaves.getDefaultState(), decorators);
	}

	private static TreeFeatureConfig sakura(BlockState log, BlockState leaves, List<TreeDecorator> decorators) {
		SimpleBlockStateProvider logProvider = new SimpleBlockStateProvider(log);
		SimpleBlockStateProvider leavesProvider = new SimpleBlockStateProvider(leaves);

		return new TreeFeatureConfig.Builder(logProvider, leavesProvider, null, new StraightTrunkPlacer(0, 0, 0)).treeDecorators(decorators).build();
	}

	private static TreeFeatureConfig basicJungle(WoodBlocks blocks) {
		return basicJungle(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static TreeFeatureConfig basicJungle(BlockState log, BlockState leaves) {
		SimpleBlockStateProvider logProvider = new SimpleBlockStateProvider(log);
		SimpleBlockStateProvider leavesProvider = new SimpleBlockStateProvider(leaves);

		return new TreeFeatureConfig.Builder(logProvider, leavesProvider, new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(4, 8, 0))
				.treeDecorators(ImmutableList.of(
						new CocoaBeansTreeDecorator(0.2F),
						new TrunkVineTreeDecorator(),
						new LeaveVineTreeDecorator())
				)
				.noVines()
				.build();
	}
}
