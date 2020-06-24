package com.terraformersmc.terrestria.feature.trees.components;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;

import java.util.Random;
import java.util.Set;

public interface Roots {
	void growRoots(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, BlockBox box);
	void tryGrowRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable bottom, int baseTrunkHeight, Random rand, BlockBox box);
}
