package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class CanopyTree4BranchTrunkPlacer extends TrunkPlacer {
	public static final MapCodec<CanopyTree4BranchTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((straightTrunkWith4BranchesPlacerInstance ->
			fillTrunkPlacerFields(straightTrunkWith4BranchesPlacerInstance).apply(straightTrunkWith4BranchesPlacerInstance, CanopyTree4BranchTrunkPlacer::new)));

	public CanopyTree4BranchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.CANOPY_4_BRANCHES;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int trunkHeight, BlockPos pos, TreeFeatureConfig treeFeatureConfig) {

		// Check and set the block below to dirt
		setToDirt(world, replacer, random, pos.down(), treeFeatureConfig);

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//We vary the base trunk height, but not the top of the tree for simplicity sake so this height does not reflect the actual height of this type of tree
		for (int i = 0; i < getHeight(random); i++) {
			getAndSetState(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		// Remember the current location for branch placement
		BlockPos origin = currentPosition.toImmutable();

		//Determine the branch length and note it as the radius for the foliage placer later and the branch placement now
		int radius = random.nextInt(1) + 2;

		// Place the branches
		currentPosition.move(Direction.NORTH, radius + 1);
		for (int i = 0; i < (radius * 2) + 1; i++) {
			checkAndPlaceSpecificBlockState(world, random, currentPosition.move(Direction.SOUTH), replacer, treeFeatureConfig.trunkProvider.get(random, currentPosition).with(PillarBlock.AXIS, Direction.NORTH.getAxis()));
		}
		currentPosition = origin.mutableCopy();
		currentPosition.move(Direction.EAST, radius + 1);
		for (int i = 0; i < (radius * 2) + 1; i++) {
			checkAndPlaceSpecificBlockState(world, random, currentPosition.move(Direction.WEST), replacer, treeFeatureConfig.trunkProvider.get(random, currentPosition).with(PillarBlock.AXIS, Direction.EAST.getAxis()));
		}

		// Go back to the middle of the tree
		currentPosition = origin.mutableCopy();

		// Place 2 more blocks to cap off the tree
		getAndSetState(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		getAndSetState(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);

		// Return the crossing of the branches as the foliage placer's center
		return ImmutableList.of(new FoliagePlacer.TreeNode(origin, radius, false));
	}

	private static void checkAndPlaceSpecificBlockState(TestableWorld testableWorld, Random random, BlockPos blockPos, BiConsumer<BlockPos, BlockState> replacer, BlockState blockState) {
		if (TreeFeature.canReplace(testableWorld, blockPos)) {
			replacer.accept(blockPos.toImmutable(), blockState);
		}
	}
}
