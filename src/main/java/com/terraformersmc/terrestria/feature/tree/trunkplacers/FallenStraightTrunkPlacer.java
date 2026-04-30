package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.BiConsumer;

@NullMarked
public class FallenStraightTrunkPlacer extends StraightTrunkPlacer {
	public static final MapCodec<FallenStraightTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
		trunkPlacerParts(instance).apply(instance, FallenStraightTrunkPlacer::new));

	public FallenStraightTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.FALLEN_STRAIGHT;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {

		// Select a Direction for the log to be placed in
		Direction direction = random.nextBoolean() ? Direction.NORTH : Direction.EAST;

		// Create the Mutable version of our block position so that we can procedurally create the log
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(direction, trunkHeight / 2 + 1);

		// Determine the Percentage of the ground that the log will lay flat on
		for (int i = 0; i < trunkHeight; i++) {
			BlockPos localPos = currentPosition.move(direction.getOpposite());

			// If the pos is blocked, then return
			if (!level.isStateAtPosition(localPos, BlockBehaviour.BlockStateBase::canBeReplaced)) {
				return ImmutableList.of();
			}

			// If there is air underneath, then return
			if (level.isStateAtPosition(localPos.below(), BlockState::isAir)) {
				return ImmutableList.of();
			}
		}

		// Place the blocks
		for (int i = 0; i < trunkHeight; ++i) {
			checkAndPlaceSpecificBlockState(level, random, currentPosition.move(direction), replacer, treeFeatureConfig.trunkProvider.getState(level, random, currentPosition).setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
		}

		// No foliage placer locations needed
		return ImmutableList.of();
	}

	private static void checkAndPlaceSpecificBlockState(LevelSimulatedReader level, RandomSource random, BlockPos blockPos, BiConsumer<BlockPos, BlockState> replacer, BlockState blockState) {
		if (TreeFeature.validTreePos(level, blockPos)) {
			replacer.accept(blockPos.immutable(), blockState);
		}
	}
}
