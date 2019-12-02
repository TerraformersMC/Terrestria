package com.terraformersmc.terrestria.feature.trees;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import java.util.Set;

public class PortUtil {
	public static boolean setBlockState(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos pos, BlockState state, BlockBox box) {
		if (!AbstractTreeFeature.isAirOrLeaves(world, pos) && !AbstractTreeFeature.isReplaceablePlant(world, pos) && !AbstractTreeFeature.isWater(world, pos)) {
			return false;
		} else {
			logs.add(pos.toImmutable());
			world.setBlockState(pos, state, 19);
			box.encompass(new BlockBox(pos, pos));

			return true;
		}
	}
}
