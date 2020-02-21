package com.terraformersmc.terrestria.feature.trees.components;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Set;

public interface Trunk {
	void growTrunk(Set<BlockPos> logs, Set<BlockPos> leaves, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, BlockBox box, TreeFeatureConfig config);
}
