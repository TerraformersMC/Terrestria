package net.coderbot.terrestria.feature.volcano;

import net.minecraft.util.math.MathHelper;

import java.util.Random;

// This class exports public utilities, these methods should be public
@SuppressWarnings("WeakerAccess")
public class SimpleRadiusNoise {
	private final long seed;
	private final double[] noise;

	public SimpleRadiusNoise(int size, long seed, double min, double variance) {
		this.noise = new double[size];
		this.seed = seed;
		Random random = new Random(seed);

		for(int i = 0; i < size; i++) {
			noise[i] = random.nextDouble() * variance + min;
		}
	}

	public long getSeed() {
		return seed;
	}

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