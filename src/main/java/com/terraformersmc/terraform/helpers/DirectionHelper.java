package com.terraformersmc.terraform.helpers;

import net.minecraft.util.math.Direction;

import java.util.Random;

public class DirectionHelper {

	public static Direction randomHorizontalDirectionAwayFrom(Random rand, Direction direction) {
		Direction out = Direction.Type.HORIZONTAL.random(rand);
		return out == direction ? direction.getOpposite() : out;
	}
}
