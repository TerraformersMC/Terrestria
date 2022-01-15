package com.terraformersmc.terrestria.world.gen.feature.structure.volcano;

import java.util.Random;

import net.minecraft.util.math.MathHelper;

/**
 * Simple noise function that proceeds in a circular fashion.
 */
public class SimpleRadialNoise {
	private final long seed;
	private final double[] noise;

	/**
	 * Creates SimpleRadialNoise instance from the supplied parameters.
	 * @param size How many random values to interpolate between
	 * @param seed The seed for the randomness
	 * @param min The minimum value returned from sample()
	 * @param variance The maximum value that can be added to minimum in sample()
	 */
	public SimpleRadialNoise(int size, long seed, double min, double variance) {
		this.noise = new double[size];
		this.seed = seed;
		Random random = new Random(seed);

		for(int i = 0; i < size; i++) {
			noise[i] = random.nextDouble() * variance + min;
		}
	}

	/**
	 * Gets the seed used to create this noise generator.
	 * @return The seed
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * Samples as the specified angle.
	 * @param angle The angle, from 0.0 to 1.0 (inclusive).
	 * @return A random value within the range [min, min + variance] - ie, value >= min && value <= (min + variance)
	 */
	public double sample(double angle) {
		angle = angle * noise.length;

		int lower = MathHelper.floor(angle);
		int upper = MathHelper.ceil(angle);
		double frac = MathHelper.fractionalPart(angle);

		double from = noise[lower < noise.length ? lower : noise.length - 1];
		double to = noise[upper < noise.length ? upper : 0];

		return MathHelper.lerp(fade(frac), from, to);
	}

	private static double fade(double f) {
		double f3 = f * f * f;
		double f4 = f3 * f;
		double f5 = f4 * f;

		return 6 * f5 - 15 * f4 + 10 * f3;
	}
}
