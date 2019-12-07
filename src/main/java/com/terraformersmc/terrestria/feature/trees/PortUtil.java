package com.terraformersmc.terrestria.feature.trees;

import com.terraformersmc.terraform.block.ExtendedLeavesBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;

public class PortUtil {
	public static boolean setBlockState(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos pos, BlockState state, BlockBox box) {
		if (!AbstractTreeFeature.isAirOrLeaves(world, pos) && !AbstractTreeFeature.isReplaceablePlant(world, pos) && !AbstractTreeFeature.isWater(world, pos)) {
			return false;
		} else {
			blocks.add(pos.toImmutable());
			world.setBlockState(pos, state, 19);
			box.encompass(new BlockBox(pos, pos));

			return true;
		}
	}

	public static boolean setLeavesWithDistance(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> blocks, BlockBox box, TreeFeatureConfig config, int distance) {
		BlockState state = config.leavesProvider.getBlockState(random, pos);

		if(state.contains(ExtendedLeavesBlock.DISTANCE)) {
			state = state.with(ExtendedLeavesBlock.DISTANCE, Math.min(distance, 14));
		} else if(state.contains(Properties.DISTANCE_1_7)) {
			state = state.with(Properties.DISTANCE_1_7, Math.min(distance, 7));
		}

		return setBlockState(blocks, world, pos, state, box);
	}
}
