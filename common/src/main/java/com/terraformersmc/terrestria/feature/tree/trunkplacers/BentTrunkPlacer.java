package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class BentTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<BentTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((bentTrunkPlacerInstance) ->
			trunkPlacerParts(bentTrunkPlacerInstance).apply(bentTrunkPlacerInstance, BentTrunkPlacer::new));

	public BentTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.BENT;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {
		// Decide the direction at which the tree will bend
		Direction bendDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);

		// Check and set the block below to dirt
		setDirtAt(world, replacer, random, pos.below(), treeFeatureConfig);

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.DOWN);

		// Place the first few blocks
		for (int i = 0; i < 4 + random.nextInt(3); i++) {
			placeLog(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		//Offset in the lean direction and also move it down to keep the trunk solid
		currentPosition.move(bendDirection).move(Direction.DOWN);

		// Place a few more blocks
		for (int i = 0; i < 4 + random.nextInt(1); i++) {
			placeLog(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		// 50% of the time make a 3rd set of blocks the same as the step before
		if (random.nextBoolean()) {
			currentPosition.move(bendDirection).move(Direction.DOWN);

			for (int i = 0; i < 3; i++) {
				placeLog(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
			}
		}

		// Return the top as the valid foliage placer location
		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(currentPosition.immutable(), 0, false));
	}
}
