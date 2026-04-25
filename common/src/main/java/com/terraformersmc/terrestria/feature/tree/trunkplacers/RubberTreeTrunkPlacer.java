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

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class RubberTreeTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<RubberTreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(rubberTreeTrunkPlacerInstance ->
			trunkPlacerParts(rubberTreeTrunkPlacerInstance).apply(rubberTreeTrunkPlacerInstance, RubberTreeTrunkPlacer::new));

	public RubberTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.RUBBER_TREE;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {
		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.DOWN);

		// Create the placer storage
		ArrayList<FoliagePlacer.FoliageAttachment> foliageNodes = new ArrayList<>();

		// Place the trunk
		for (int i = 0; i < trunkHeight; i++) {
			placeLog(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		// Instance a direction before the loop so it no be slow mkay
		Direction branchDirection;

		// Place the rest of the trunk and branches
		for (int j = 0; j < trunkHeight + 3; j++) {
			placeLog(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
			branchDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
			foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(world, random, currentPosition.immutable(), branchDirection, replacer, treeFeatureConfig), 1, false));
			foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(world, random, currentPosition.immutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, branchDirection), replacer, treeFeatureConfig), 1, false));
		}

		// Make sure the top gets some love
		foliageNodes.add(new FoliagePlacer.FoliageAttachment(currentPosition, 1, false));

		// Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(LevelSimulatedReader world, RandomSource random, BlockPos pos, Direction direction, BiConsumer<BlockPos, BlockState> replacer, TreeConfiguration treeFeatureConfig) {
		BlockPos.MutableBlockPos currentPosition = pos.mutable();
		// Place a block in the branch direction
		placeLog(world, replacer, random, currentPosition.move(direction), treeFeatureConfig);
		// 50% of the time place another block in the same general direction
		if (random.nextBoolean()) {
			// 50% of the time make the branch move upwards
			if (random.nextBoolean()) {
				currentPosition.move(Direction.UP);
			}
			placeLog(world, replacer, random, currentPosition.move(DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite())), treeFeatureConfig);
		}
		return currentPosition;
	}
}
