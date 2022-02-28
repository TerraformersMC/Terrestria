package com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers;

import net.minecraft.util.math.Direction;

import java.util.Random;

public class DirectionHelper {

	/**
	 * A utility method for getting a direction not the same as the one specified in the horizontal direction
	 * Often used in tree generation for creating unique branches
	 *
	 * @param rand the java.util.Random instance to be used
	 * @param direction the direction you want a general direction away from
	 * @return a random direction away from the specified direction. weighted so the opposite direction is more likely.
	 */
	public static Direction randomHorizontalDirectionAwayFrom(Random rand, Direction direction) {
		Direction out = Direction.Type.HORIZONTAL.random(rand);
		return out == direction ? direction.getOpposite() : out;
	}
}
