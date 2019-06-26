package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class VolcanoFeature extends Feature<DefaultFeatureConfig> {
	public VolcanoFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify) {
		super(function, notify);
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		if(pos.getY() < 62) {
			// TODO: Ocean volcanoes
			return false;
		}

		pos = new BlockPos(pos.getX(), 62, pos.getZ());
		BlockPos.Mutable offset = new BlockPos.Mutable();

		Volcano volcano = new Volcano(random.nextLong());

		for(int dY = 0; dY < volcano.lavaHeight; dY++) {
			this.setBlockState(world, offset.set(pos).setOffset(0, dY, 0), Blocks.LAVA.getDefaultState());
			this.setBlockState(world, offset.set(pos).setOffset(1, dY, 0), Blocks.LAVA.getDefaultState());
			this.setBlockState(world, offset.set(pos).setOffset(0, dY, 1), Blocks.LAVA.getDefaultState());
			this.setBlockState(world, offset.set(pos).setOffset(-1, dY, 0), Blocks.LAVA.getDefaultState());
			this.setBlockState(world, offset.set(pos).setOffset(0, dY, -1), Blocks.LAVA.getDefaultState());
		}

		for(int dZ = -volcano.radiusBound; dZ <= volcano.radiusBound; dZ++) {
			for(int dX = -volcano.radiusBound; dX <= volcano.radiusBound; dX++) {
				if(dZ == 0 && (dX >= -1 && dX <= 1) || dX ==0 && (dZ >= -1 && dZ <= 1)) {
					continue;
				}

				Random atRandom = volcano.configureRandomAt(dX, dZ);

				double dist = Math.sqrt(dZ*dZ + dX*dX);
				double angle = positionToAngle(dist, dX, dZ);

				double noise = volcano.radiusNoise.sample(angle);
				double vegetation = volcano.vegetationNoise.sample(angle) + atRandom.nextDouble() * 0.15;

				double scaled = (dist / volcano.radius) * noise;
				int columnHeight = (int)(shape(scaled) * volcano.height);
				BlockState top = TerrestriaBlocks.BASALT.getDefaultState();

				if(columnHeight <= 0) {
					continue;
				}

				if(scaled > 0.2 && scaled < 0.35) {
					columnHeight += atRandom.nextInt(2);
				} else if(scaled >= 0.35 && scaled <= 0.8 && atRandom.nextInt(4) == 0) {
					columnHeight += 1;
				}

				if(scaled > 0.3) {
					double scaledHeight = (double)(columnHeight) / (double)(volcano.lavaHeight);
					if(scaledHeight < vegetation) {
						if(columnHeight < 4) {
							top = TerrestriaBlocks.BASALT_SAND.getDefaultState();
						} else {
							top = TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState();
						}
					}
				}

				for(int dY = -16; dY < columnHeight - 1; dY++) {
					offset.set(pos).setOffset(dX, dY, dZ);

					if(dY > -4 || world.getBlockState(pos).isAir()) {
						this.setBlockState(world, offset, TerrestriaBlocks.BASALT.getDefaultState());
					}
				}

				if(columnHeight > 0) {
					offset.set(pos).setOffset(dX, columnHeight - 1, dZ);
					this.setBlockState(world, offset, top);
				}

				if(scaled <= 0.3) {
					for(int dY = columnHeight; dY < volcano.lavaHeight; dY++) {
						offset.set(pos).setOffset(dX, dY, dZ);

						this.setBlockState(world, offset, Blocks.LAVA.getDefaultState());
					}
				}
			}
		}

		return true;
	}

	private static double positionToAngle(double dist, double dX, double dZ) {
		// 0.0 to 0.5
		double angle = 0.5 * Math.asin(dX / dist) / Math.PI + 0.25;

		if(dZ < 0) {
			angle = 1.0 - angle;
		}

		return angle;
	}

	private static double fade(double f) {
		double f3 = f * f * f;
		double f4 = f3 * f;
		double f5 = f4 * f;

		return 6 * f5 - 15 * f4 + 10 * f3;
	}

	// Models the caldera shape of the volcano.
	private static double shape(double scaled) {
		scaled = MathHelper.clamp(scaled, 0.0, 1.0);
		double curve = curve(1.0 - scaled);

		if(scaled <= 0.3) {
			curve -= (0.3 - scaled) * 2;
		}

		return curve;
	}

	// Models an S-curve.
	private static double curve(double progress) {
		progress = MathHelper.clamp(progress, 0.0, 1.0);

		if(progress <= 0.5) {
			return 2 * progress * progress;
		} else {
			double progressUntil = 1.0 - progress;

			return 1 - 2 * progressUntil * progressUntil;
		}
	}

	private static class Volcano {
		SimpleRadiusNoise radiusNoise;
		SimpleRadiusNoise vegetationNoise;
		int height;
		int radius;
		int radiusBound;
		int lavaHeight;
		long seed;
		Random temp;

		public Volcano(long seed) {
			this.seed = seed;
			this.temp = new Random(seed);

			Random random = new Random(seed);

			radiusNoise = new SimpleRadiusNoise(16, random, 0.75, 0.5);
			vegetationNoise = new SimpleRadiusNoise(16, random, 0.25, 0.5);

			height = 32 + random.nextInt(64);
			radius = random.nextInt(height) + height / 2;
			radiusBound = MathHelper.ceil(radius/*TODO * 1.5*/);
			lavaHeight = (int)(height * shape(0.2));
		}

		public Random configureRandomAt(int dX, int dZ) {
			long baseSeed = ((long)(dX) << 32) | (long)(dZ);
			temp.setSeed(baseSeed ^ seed);

			return temp;
		}
	}

	private static class SimpleRadiusNoise {
		private double[] noise;
		private int size;

		public SimpleRadiusNoise(int size, Random random, double min, double variance) {
			this.noise = new double[size];
			this.size = size;

			for(int i = 0; i<16; i++) {
				noise[i] = random.nextDouble() * variance + min;
			}
		}

		public double sample(double angle) {
			angle = angle * size;

			int lower = MathHelper.floor(angle);
			int upper = MathHelper.ceil(angle);
			double frac = MathHelper.fractionalPart(angle);

			double from = noise[lower < size ? lower : size - 1];
			double to = noise[upper < size ? upper : 0];

			return MathHelper.lerp(fade(frac), from, to);
		}
	}
}
