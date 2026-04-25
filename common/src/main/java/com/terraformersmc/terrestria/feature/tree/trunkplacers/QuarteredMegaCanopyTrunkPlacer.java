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
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class QuarteredMegaCanopyTrunkPlacer extends MegaTrunkPlacer {
	public static final MapCodec<QuarteredMegaCanopyTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(quarteredMegaCanopyTrunkPlacerInstance ->
			trunkPlacerParts(quarteredMegaCanopyTrunkPlacerInstance).apply(quarteredMegaCanopyTrunkPlacerInstance, QuarteredMegaCanopyTrunkPlacer::new));

	public QuarteredMegaCanopyTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.QUARTERED_MEGA_CANOPY;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {
		// Determine the number of branched branchLayers to have
		int branchLayers = 5 + random.nextInt(3);

		// Generate the trunk from the MegaTrunkPlacer, but ignore its chosen foliage location
		// This also grows the roots
		super.placeTrunk(world, replacer, random, trunkHeight + branchLayers, pos, treeFeatureConfig);

		// Create our list of foliage nodes
		ArrayList<FoliagePlacer.FoliageAttachment> foliageNodes = new ArrayList<>();

		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.UP, trunkHeight - 3);

		// Place branches on each branch layer
		for (int i = 0; i < branchLayers + 3; i++) {
			// Place a branch in a random direction
			Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

			// Place the branch logs
			BlockPos branch = placeBranch(world, random, currentPosition.immutable(), replacer, treeFeatureConfig, direction);

			// Add the end of the branch to the foliage locations
			foliageNodes.add(new FoliagePlacer.FoliageAttachment(branch, random.nextInt(2) + 4, false));

			// Move the position up
			currentPosition.move(Direction.UP);
		}

		// Make sure the top of the tree has leaf locations
		foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(world, random, currentPosition.immutable(), replacer, treeFeatureConfig, Direction.NORTH), random.nextInt(2) + 4, false));
		foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(world, random, currentPosition.immutable(), replacer, treeFeatureConfig, Direction.SOUTH), random.nextInt(2) + 4, false));
		foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(world, random, currentPosition.immutable(), replacer, treeFeatureConfig, Direction.EAST), random.nextInt(2) + 4, false));
		foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(world, random, currentPosition.immutable(), replacer, treeFeatureConfig, Direction.WEST), random.nextInt(2) + 4, false));

		// Return an immutable version of the foliage node list
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(LevelSimulatedReader world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> replacer, TreeConfiguration treeFeatureConfig, Direction direction) {
		// Create the Mutable version of our block position so that we can procedurally create the branch
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.DOWN);

		// Pick weather this branch should go diagonal or straight
		Direction diagonalDirection = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());

		// Determine the length of the branch
		int length = 4 + random.nextInt(3);

		if (diagonalDirection == direction && length >= 3) {
			// Some branches are a bit long...
			length /= 2;
		}

		// Place a branch with length in the diagonalDirection, with an upwards angle
		for (int i = 0; i < length; i++) {
			if (random.nextBoolean()) {
				placeLog(world, replacer, random, currentPosition.move(direction), treeFeatureConfig);
				placeLog(world, replacer, random, currentPosition.move(diagonalDirection), treeFeatureConfig);
			}
			placeLog(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		// Return the end of the branch as a valid foliage placement location
		return currentPosition.immutable();
	}
}
