package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.BiConsumer;

@NullMarked
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

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {
		// Set the blocks below the trunk to dirt
		BlockPos down = pos.below();
		placeBelowTrunkBlock(level, replacer, random, down, treeFeatureConfig);
		placeBelowTrunkBlock(level, replacer, random, down.east(), treeFeatureConfig);
		placeBelowTrunkBlock(level, replacer, random, down.south(), treeFeatureConfig);
		placeBelowTrunkBlock(level, replacer, random, down.south().east(), treeFeatureConfig);

		// Place the trunk
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for(int i = 0; i < trunkHeight; ++i) {
			setLog(level, mutable, replacer, getState(level, random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, i, 0);
			setLog(level, mutable, replacer, getState(level, random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, i, 0);
			setLog(level, mutable, replacer, getState(level, random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, i, 1);
			setLog(level, mutable, replacer, getState(level, random, mutable, treeFeatureConfig, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, i, 1);
		}

		BlockStateProvider rootsProvider = treeFeatureConfig.trunkProvider;

		if (treeFeatureConfig instanceof QuarteredMegaTreeConfig) {
			rootsProvider = ((QuarteredMegaTreeConfig) treeFeatureConfig).rootsProvider;
		}

		growRoots(replacer, level, pos.mutable(), random, rootsProvider);

		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(trunkHeight), 0, true));
	}

	private static BlockState getState(WorldGenLevel level, RandomSource random, BlockPos pos, TreeConfiguration config, QuarterLogBlock.BarkSide side) {
		if (config instanceof QuarteredMegaTreeConfig && Terrestria.getConfigManager().getGeneralConfig().areQuarterLogsEnabled()) {
			return ((QuarteredMegaTreeConfig) config).quarteredTrunkProvider.getState(level, random, pos).setValue(QuarterLogBlock.BARK_SIDE, side);
		} else {
			return config.trunkProvider.getState(level, random, pos);
		}
	}

	private static void setLog(LevelSimulatedReader level, BlockPos.MutableBlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state, BlockPos blockPos, int i, int j, int k) {
		mutable.setWithOffset(blockPos, i, j, k);

		setLog(level, mutable, replacer, state);
	}

	private static void setLog(LevelSimulatedReader level, BlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state) {
		if (TreeFeature.validTreePos(level, mutable)) {
			replacer.accept(mutable.immutable(), state);
		}
	}

	public void growRoots(BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel level, BlockPos.MutableBlockPos pos, RandomSource random, BlockStateProvider wood) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(replacer, level, pos.set(x - 1, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + 2, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + random.nextInt(2), y, z - 1), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + random.nextInt(2), y, z + 2), random, wood);
	}

	public void tryGrowRoot(BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel level, BlockPos.MutableBlockPos bottom, RandomSource random, BlockStateProvider wood) {
		// Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		// Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		// Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.validTreePos(level, bottom) || TreeFeature.validTreePos(level, bottom) || level.isStateAtPosition(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				replacer.accept(bottom.immutable(), wood.getState(level, random, bottom));
			}

			bottom.move(Direction.UP);
		}
	}
}
