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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.BiConsumer;

@NullMarked
public class CanopyTree4BranchTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<CanopyTree4BranchTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((straightTrunkWith4BranchesPlacerInstance ->
			trunkPlacerParts(straightTrunkWith4BranchesPlacerInstance).apply(straightTrunkWith4BranchesPlacerInstance, CanopyTree4BranchTrunkPlacer::new)));

	public CanopyTree4BranchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.CANOPY_4_BRANCHES;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {

		// Check and set the block below to dirt
		placeBelowTrunkBlock(level, replacer, random, pos.below(), treeFeatureConfig);

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.DOWN);

		//We vary the base trunk height, but not the top of the tree for simplicity sake so this height does not reflect the actual height of this type of tree
		for (int i = 0; i < getTreeHeight(random); i++) {
			placeLog(level, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		// Remember the current location for branch placement
		BlockPos origin = currentPosition.immutable();

		//Determine the branch length and note it as the radius for the foliage placer later and the branch placement now
		int radius = random.nextInt(1) + 2;

		// Place the branches
		currentPosition.move(Direction.NORTH, radius + 1);
		for (int i = 0; i < (radius * 2) + 1; i++) {
			checkAndPlaceSpecificBlockState(level, random, currentPosition.move(Direction.SOUTH), replacer, treeFeatureConfig.trunkProvider.getState(level, random, currentPosition).setValue(RotatedPillarBlock.AXIS, Direction.NORTH.getAxis()));
		}
		currentPosition = origin.mutable();
		currentPosition.move(Direction.EAST, radius + 1);
		for (int i = 0; i < (radius * 2) + 1; i++) {
			checkAndPlaceSpecificBlockState(level, random, currentPosition.move(Direction.WEST), replacer, treeFeatureConfig.trunkProvider.getState(level, random, currentPosition).setValue(RotatedPillarBlock.AXIS, Direction.EAST.getAxis()));
		}

		// Go back to the middle of the tree
		currentPosition = origin.mutable();

		// Place 2 more blocks to cap off the tree
		placeLog(level, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		placeLog(level, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);

		// Return the crossing of the branches as the foliage placer's center
		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(origin, radius, false));
	}

	private static void checkAndPlaceSpecificBlockState(LevelSimulatedReader level, RandomSource random, BlockPos blockPos, BiConsumer<BlockPos, BlockState> replacer, BlockState blockState) {
		if (TreeFeature.validTreePos(level, blockPos)) {
			replacer.accept(blockPos.immutable(), blockState);
		}
	}
}
