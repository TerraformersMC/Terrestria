package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
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

	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeFeature tree) {
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		// For now, we use only one trunk and one root block variant, so for performance we cache them here:
		BlockState trunkState = tree.trunkProvider().value().getState(level, random, pos);
		BlockState rootsState = trunkState;
		WoodBlocks woodBlocks = WoodBlocks.getFromLog(trunkState.getBlock()).orElse(null);
		if (Terrestria.getConfigManager().getGeneralConfig().areQuarterLogsEnabled() && woodBlocks != null && woodBlocks.hasQuarterLog()) {
			assert woodBlocks.quarterLog != null;
			trunkState = woodBlocks.quarterLog.defaultBlockState();
		}
		if (woodBlocks != null && woodBlocks.hasWood()) {
			assert woodBlocks.wood != null;
			rootsState = woodBlocks.wood.defaultBlockState();
		}

		// Set the blocks below the trunk to dirt
		mutable.setWithOffset(pos, Direction.DOWN);
		placeBelowTrunkBlock(level, replacer, random, mutable, tree);
		placeBelowTrunkBlock(level, replacer, random, mutable.move(Direction.EAST), tree);
		placeBelowTrunkBlock(level, replacer, random, mutable.move(Direction.SOUTH), tree);
		placeBelowTrunkBlock(level, replacer, random, mutable.move(Direction.WEST), tree);

		// Place the trunk
		for (int yOffset = 0; yOffset < trunkHeight; ++yOffset) {
			setLog(level, mutable, replacer, trunkState.trySetValue(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHWEST), pos, 0, yOffset, 0);
			setLog(level, mutable, replacer, trunkState.trySetValue(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHEAST), pos, 1, yOffset, 0);
			setLog(level, mutable, replacer, trunkState.trySetValue(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHEAST), pos, 1, yOffset, 1);
			setLog(level, mutable, replacer, trunkState.trySetValue(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHWEST), pos, 0, yOffset, 1);
		}

		// Place the roots (trunk buttresses)
		growRoots(replacer, level, mutable.set(pos), random, rootsState);

		// Return the selected foliage attachments
		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(trunkHeight), 0, true));
	}

	private static void setLog(LevelSimulatedReader level, BlockPos.MutableBlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state, BlockPos blockPos, int xOffset, int yOffset, int zOffset) {
		mutable.setWithOffset(blockPos, xOffset, yOffset, zOffset);

		setLog(level, mutable, replacer, state);
	}

	private static void setLog(LevelSimulatedReader level, BlockPos mutable, BiConsumer<BlockPos, BlockState> replacer, BlockState state) {
		if (TreeFeature.validTreePos(level, mutable)) {
			replacer.accept(mutable.immutable(), state);
		}
	}

	public void growRoots(BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel level, BlockPos.MutableBlockPos pos, RandomSource random, BlockState wood) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(replacer, level, pos.set(x - 1, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + 2, y, z + random.nextInt(2)), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + random.nextInt(2), y, z - 1), random, wood);
		tryGrowRoot(replacer, level, pos.set(x + random.nextInt(2), y, z + 2), random, wood);
	}

	public void tryGrowRoot(BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel level, BlockPos.MutableBlockPos bottom, RandomSource random, BlockState wood) {
		// Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		// Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		// Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.validTreePos(level, bottom) || level.isStateAtPosition(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				replacer.accept(bottom.immutable(), wood);
			}

			bottom.move(Direction.UP);
		}
	}
}
