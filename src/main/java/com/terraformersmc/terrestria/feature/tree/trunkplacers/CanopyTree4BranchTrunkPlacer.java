package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class CanopyTree4BranchTrunkPlacer extends TrunkPlacer {

	public static final Codec<CanopyTree4BranchTrunkPlacer> CODEC = RecordCodecBuilder.create((straightTrunkWith4BranchesPlacerInstance ->
			method_28904(straightTrunkWith4BranchesPlacerInstance).apply(straightTrunkWith4BranchesPlacerInstance, CanopyTree4BranchTrunkPlacer::new)));

	public CanopyTree4BranchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.CANOPY_4_BRANCHES;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

		//Check and set the block below to dirt
		method_27400(world, pos.down());

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//We vary the base trunk height, but not the top of the tree for simplicity sake so this height does not reflect the actual height of this type of tree
		for (int i = 0; i < getHeight(random); i++) {
			method_27402(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
		}

		//Remember the current location for branch placement
		BlockPos origin = currentPosition.toImmutable();

		//Determine the branch length and note it as the radius for the foliage placer later and the branch placement now
		int radius = random.nextInt(1) + 2;

		//Place the branches
		currentPosition.move(Direction.NORTH, radius + 1);
		for (int i = 0; i < (radius * 2) + 1; i++) {
			checkAndPlaceSpecificBlockState(world, random, currentPosition.move(Direction.SOUTH), set, blockBox, treeFeatureConfig.trunkProvider.getBlockState(random, currentPosition).with(PillarBlock.AXIS, Direction.NORTH.getAxis()));
		}
		currentPosition = origin.mutableCopy();
		currentPosition.move(Direction.EAST, radius + 1);
		for (int i = 0; i < (radius * 2) + 1; i++) {
			checkAndPlaceSpecificBlockState(world, random, currentPosition.move(Direction.WEST), set, blockBox, treeFeatureConfig.trunkProvider.getBlockState(random, currentPosition).with(PillarBlock.AXIS, Direction.EAST.getAxis()));
		}

		//Go back to the middle of the tree
		currentPosition = origin.mutableCopy();

		//Place 2 more blocks to cap off the tree
		method_27402(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
		method_27402(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);

		//Return the crossing of the branches as the foliage placer's center
		return ImmutableList.of(new FoliagePlacer.TreeNode(origin, radius, false));
	}

	private static void checkAndPlaceSpecificBlockState(ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos, Set<BlockPos> set, BlockBox blockBox, BlockState blockState) {
		if (TreeFeature.canReplace(modifiableTestableWorld, blockPos)) {
			method_27404(modifiableTestableWorld, blockPos, blockState, blockBox);
			set.add(blockPos.toImmutable());
		}
	}
}
