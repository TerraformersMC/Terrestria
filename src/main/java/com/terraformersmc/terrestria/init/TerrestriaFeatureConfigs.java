package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.MegaTreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleStateProvider;

import java.util.Collections;
import java.util.List;

public class TerrestriaFeatureConfigs {
	public static final BranchedTreeFeatureConfig REDWOOD = basic(TerrestriaBlocks.REDWOOD);
	public static final MegaTreeFeatureConfig MEGA_REDWOOD = basicMega(TerrestriaBlocks.REDWOOD);
	public static final BranchedTreeFeatureConfig HEMLOCK = basic(TerrestriaBlocks.HEMLOCK);
	public static final MegaTreeFeatureConfig MEGA_HEMLOCK = basicMega(TerrestriaBlocks.HEMLOCK);
	public static final BranchedTreeFeatureConfig RUBBER = basic(TerrestriaBlocks.RUBBER);
	public static final BranchedTreeFeatureConfig CYPRESS = basic(TerrestriaBlocks.CYPRESS);
	public static final MegaTreeFeatureConfig MEGA_CYPRESS = basicMega(TerrestriaBlocks.CYPRESS);
	public static final BranchedTreeFeatureConfig WILLOW = basic(TerrestriaBlocks.WILLOW);
	public static final BranchedTreeFeatureConfig JAPANESE_MAPLE = basic(TerrestriaBlocks.JAPANESE_MAPLE);

	public static final BranchedTreeFeatureConfig JAPANESE_MAPLE_SHRUB = basic (
			TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
			TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES.getDefaultState()
	);

	public static final BranchedTreeFeatureConfig DARK_JAPANESE_MAPLE = basic (
			TerrestriaBlocks.JAPANESE_MAPLE.log.getDefaultState(),
			TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES.getDefaultState()
	);

	public static final MegaTreeFeatureConfig MEGA_RAINBOW_EUCALYPTUS = basicMega(TerrestriaBlocks.RAINBOW_EUCALYPTUS);
	public static final BranchedTreeFeatureConfig SAKURA = sakura(
			TerrestriaBlocks.SAKURA,
			ImmutableList.of(
					new SakuraLeafPileDecorator(4),
					new FixSmallLogsDecorator()
			)
	);

	public static final BranchedTreeFeatureConfig JUNGLE_PALM = basic(Blocks.JUNGLE_LOG.getDefaultState(), TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState());

	public static final BranchedTreeFeatureConfig TINY_REDWOOD = spruce(TerrestriaBlocks.REDWOOD);
	public static final BranchedTreeFeatureConfig TINY_HEMLOCK = spruce(TerrestriaBlocks.HEMLOCK);

	public static final FallenLogFeatureConfig FALLEN_HEMLOCK_LOG = fallenLog(TerrestriaBlocks.HEMLOCK);
	public static final FallenLogFeatureConfig FALLEN_REDWOOD_LOG = fallenLog(TerrestriaBlocks.REDWOOD);

	public static final BranchedTreeFeatureConfig RAINBOW_EUCALYPTUS = basicJungle(TerrestriaBlocks.RAINBOW_EUCALYPTUS);

	private static BranchedTreeFeatureConfig spruce(WoodBlocks blocks) {
		return spruce(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static BranchedTreeFeatureConfig spruce(BlockState log, BlockState leaves) {
		BranchedTreeFeatureConfig.Builder builder = new BranchedTreeFeatureConfig.Builder(
				new SimpleStateProvider(log),
				new SimpleStateProvider(leaves),
				new SpruceFoliagePlacer(2, 1));

		return builder.baseHeight(6).heightRandA(3).trunkHeight(1).trunkHeightRandom(1).trunkTopOffsetRandom(2).noVines().build();
	}

	private static FallenLogFeatureConfig fallenLog(WoodBlocks blocks) {
		return fallenLog(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static FallenLogFeatureConfig fallenLog(BlockState log, BlockState leaves) {
		SimpleStateProvider logProvider = new SimpleStateProvider(log);
		SimpleStateProvider leavesProvider = new SimpleStateProvider(leaves);

		return new FallenLogFeatureConfig.Builder(logProvider, leavesProvider).baseLength(5).lengthRandom(8).build();
	}

	private static BranchedTreeFeatureConfig basic(WoodBlocks blocks) {
		return basic(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static BranchedTreeFeatureConfig basic(BlockState log, BlockState leaves) {
		SimpleStateProvider logProvider = new SimpleStateProvider(log);
		SimpleStateProvider leavesProvider = new SimpleStateProvider(leaves);

		return new BranchedTreeFeatureConfig.Builder(logProvider, leavesProvider, null).build();
	}

	private static MegaTreeFeatureConfig basicMega(WoodBlocks blocks) {
		return basicMega(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static MegaTreeFeatureConfig basicMega(BlockState log, BlockState leaves) {
		SimpleStateProvider logProvider = new SimpleStateProvider(log);
		SimpleStateProvider leavesProvider = new SimpleStateProvider(leaves);

		return new MegaTreeFeatureConfig.Builder(logProvider, leavesProvider).build();
	}

	private static BranchedTreeFeatureConfig sakura(WoodBlocks blocks, List<TreeDecorator> decorators) {
		return sakura(blocks.log.getDefaultState(), blocks.leaves.getDefaultState(), decorators);
	}

	private static BranchedTreeFeatureConfig sakura(BlockState log, BlockState leaves, List<TreeDecorator> decorators) {
		SimpleStateProvider logProvider = new SimpleStateProvider(log);
		SimpleStateProvider leavesProvider = new SimpleStateProvider(leaves);

		return new BranchedTreeFeatureConfig.Builder(logProvider, leavesProvider, null).treeDecorators(decorators).build();
	}

	private static BranchedTreeFeatureConfig basicJungle(WoodBlocks blocks) {
		return basicJungle(blocks.log.getDefaultState(), blocks.leaves.getDefaultState());
	}

	private static BranchedTreeFeatureConfig basicJungle(BlockState log, BlockState leaves) {
		SimpleStateProvider logProvider = new SimpleStateProvider(log);
		SimpleStateProvider leavesProvider = new SimpleStateProvider(leaves);

		return new BranchedTreeFeatureConfig.Builder(logProvider, leavesProvider, new BlobFoliagePlacer(2, 0))
			.baseHeight(4)
			.heightRandA(8)
			.foliageHeight(3)
			.treeDecorators(ImmutableList.of(
				new CocoaBeansTreeDecorator(0.2F),
				new TrunkVineTreeDecorator(),
				new LeaveVineTreeDecorator())
			)
			.noVines()
			.build();
	}
}
