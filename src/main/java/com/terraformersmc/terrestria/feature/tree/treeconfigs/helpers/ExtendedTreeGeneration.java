package com.terraformersmc.terrestria.feature.tree.treeconfigs.helpers;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;

/**
 * Interface to allow us to extend the generation options aside from what is given by vanilla
 *
 */
public interface ExtendedTreeGeneration {
	boolean canGenerateOn(ModifiableTestableWorld world, BlockPos pos);
}
