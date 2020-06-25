package com.terraformersmc.terrestria.feature.treeconfigs.helpers;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;

/**
 * Todo: javadoc lol
 */
public interface ExtendedTreeGeneration {
	boolean canGenerateOn(ModifiableTestableWorld world, BlockPos pos);
}
