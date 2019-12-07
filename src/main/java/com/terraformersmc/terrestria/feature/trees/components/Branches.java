package com.terraformersmc.terrestria.feature.trees.components;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;

public interface Branches {
	void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, int length);
}
