package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class FallenStraightTrunkPlacer extends StraightTrunkPlacer {
	public static final Codec<FallenStraightTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
		method_28904(instance).apply(instance, FallenStraightTrunkPlacer::new));

	public FallenStraightTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.FALLEN_STRAIGHT;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

		// Select a Direction for the log to be placed in
		Direction direction = random.nextBoolean() ? Direction.NORTH : Direction.EAST;

		//Create the Mutable version of our block position so that we can procedurally create the log
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(direction, trunkHeight / 2 + 1);

		//Determine the Percentage of the ground that the log will lay flat on
		for (int i = 0; i < trunkHeight; i++) {
			BlockPos localPos = currentPosition.move(direction.getOpposite());

			// If the pos is blocked, then return
			if (!world.testBlockState(localPos, state -> state.getMaterial().isReplaceable())) {
				return ImmutableList.of();
			}

			// If there is air underneath, then return
			if (world.testBlockState(localPos.down(), BlockState::isAir)) {
				return ImmutableList.of();
			}
		}

		//Place the blocks
		for (int i = 0; i < trunkHeight; ++i) {
			checkAndPlaceSpecificBlockState(world, random, currentPosition.move(direction), set, blockBox, treeFeatureConfig.trunkProvider.getBlockState(random, currentPosition).with(PillarBlock.AXIS, direction.getAxis()));
		}

		//No foliage placer locations needed
		return ImmutableList.of();
	}

	private static void checkAndPlaceSpecificBlockState(ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos, Set<BlockPos> set, BlockBox blockBox, BlockState blockState) {
		if (TreeFeature.canReplace(modifiableTestableWorld, blockPos)) {
			method_27404(modifiableTestableWorld, blockPos, blockState, blockBox);
			set.add(blockPos.toImmutable());
		}
	}
}
