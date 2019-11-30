package com.terraformersmc.terrestria.feature.trees.components;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;

import java.util.Random;
import java.util.Set;

public interface GroundClutter {
	void placeGroundCover(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, BlockBox boundingBox);
}
