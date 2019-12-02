package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terraform.feature.FallenLogFeatureConfig;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.decorator.CocoaBeansTreeDecorator;
import net.minecraft.world.gen.decorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.decorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleStateProvider;

public class TerrestriaFeatureConfigs {
	public static final BranchedTreeFeatureConfig REDWOOD = basic(TerrestriaBlocks.REDWOOD);
	public static final BranchedTreeFeatureConfig HEMLOCK = basic(TerrestriaBlocks.HEMLOCK);
	public static final BranchedTreeFeatureConfig RUBBER = basic(TerrestriaBlocks.RUBBER);
	public static final BranchedTreeFeatureConfig CYPRESS = basic(TerrestriaBlocks.CYPRESS);
	public static final BranchedTreeFeatureConfig WILLOW = basic(TerrestriaBlocks.WILLOW);
	public static final BranchedTreeFeatureConfig JAPANESE_MAPLE = basic(TerrestriaBlocks.JAPANESE_MAPLE);
	public static final BranchedTreeFeatureConfig RAINBOW_EUCALYPTUS = basic(TerrestriaBlocks.RAINBOW_EUCALYPTUS);
	public static final BranchedTreeFeatureConfig SAKURA = basic(TerrestriaBlocks.SAKURA);
	public static final BranchedTreeFeatureConfig JUNGLE_PALM = basic(Blocks.JUNGLE_LOG.getDefaultState(), TerrestriaBlocks.JUNGLE_PALM_LEAVES.getDefaultState());

	public static final FallenLogFeatureConfig FALLEN_HEMLOCK_LOG = fallenLog(TerrestriaBlocks.HEMLOCK);
	public static final FallenLogFeatureConfig FALLEN_REDWOOD_LOG = fallenLog(TerrestriaBlocks.REDWOOD);

	public static final BranchedTreeFeatureConfig RAINBOW_EUCALYPTUS_SMALL = basicJungle(TerrestriaBlocks.RAINBOW_EUCALYPTUS);

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
