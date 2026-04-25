package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;

import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class MegaTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<MegaTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
		trunkPlacerParts(instance).apply(instance, MegaTrunkPlacer::new)
	);

	public MegaTrunkPlacer(int height, int randomHeight, int extraRandomHeight) {
		super(height, randomHeight, extraRandomHeight);
	}

	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.MEGA;
	}

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {
		// Set the blocks below the trunk to dirt
		BlockPos down = pos.below();
		setDirtAt(world, replacer, random, down, treeFeatureConfig);
		setDirtAt(world, replacer, random, down.east(), treeFeatureConfig);
		setDirtAt(world, replacer, random, down.south(), treeFeatureConfig);
		setDirtAt(world, replacer, random, down.south().east(), treeFeatureConfig);

		// Place the trunk
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, i, 0);
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, i, 0);
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, i, 1);
			setLog(world, mutable, replacer, getState(random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, i, 1);
		}

		BlockStateProvider rootsProvider = treeFeatureConfig.trunkProvider;

		if (treeFeatureConfig instanceof QuarteredMegaTreeConfig) {
			rootsProvider = ((QuarteredMegaTreeConfig) treeFeatureConfig).rootsProvider;
		}

		growRoots(replacer, world, pos.mutable(), random, rootsProvider);

		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(trunkHeight), 0, true));
	}

	static BlockState getState(RandomSource random, BlockPos pos, TreeConfiguration config, QuarterLogBlock.BarkSide side) {
		if (config instanceof QuarteredMegaTreeConfig && Terrestria.getConfigManager().getGeneralConfig().areQuarterLogsEnabled()) {
			return ((QuarteredMegaTreeConfig) config).quarteredTrunkProvider.getState(random, pos).setValue(QuarterLogBlock.BARK_SIDE, side);
		} else {
			return config.trunkProvider.getState(random, pos);
		}
	}

	private static void setLog(LevelSimulatedReader testableWorld, BlockPos.MutableBlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state, BlockPos blockPos, int i, int j, int k) {
		mutable.setWithOffset(blockPos, i, j, k);

		setLog(testableWorld, mutable, replacer, state);
	}

	protected static void setLog(LevelSimulatedReader testableWorld, BlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state) {
		if (TreeFeature.validTreePos(testableWorld, mutable)) {
			replacer.accept(mutable.immutable(), state);
		}
	}

	public void growRoots(BiConsumer<BlockPos, BlockState> replacer, LevelSimulatedReader world, BlockPos.MutableBlockPos pos, RandomSource random, BlockStateProvider wood) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(replacer, world, pos.set(x - 1, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, world, pos.set(x + 2, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, world, pos.set(x + random.nextInt(2), y, z - 1), random, wood);
		tryGrowRoot(replacer, world, pos.set(x + random.nextInt(2), y, z + 2), random, wood);
	}

	public void tryGrowRoot(BiConsumer<BlockPos, BlockState> replacer, LevelSimulatedReader world, BlockPos.MutableBlockPos bottom, RandomSource random, BlockStateProvider wood) {
		// Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		// Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		// Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.validTreePos(world, bottom) || TreeFeature.validTreePos(world, bottom) || world.isStateAtPosition(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				replacer.accept(bottom.immutable(), wood.getState(random, bottom));
			}

			bottom.move(Direction.UP);
		}
	}
}
