package com.terraformersmc.terrestria.surface;
/*
import java.util.Random;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class PatchyGrassSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {

	public PatchyGrassSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
		super(codec);
	}

	@Override
	public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState stone, BlockState water, int seaLevel, int minSurfaceLevel, long seed, TernarySurfaceConfig config) {
		// The random coeffecient is from [1, 2], used for randomizing the values a bit more.
		// This isn't strictly needed but it increases the randomization overall.
		double coefficient = random.nextDouble() + 1;

		// Multiply a random double by the coeffecient, then add that to the given noise for the gradient noise.
		double gradientNoise = noise + ((random.nextDouble() * coefficient));

		// If the noise + random combo is above the threshold, then generate grass.
		if (gradientNoise > 1.0D) {
			SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, stone, water, seaLevel, minSurfaceLevel, seed, SurfaceBuilder.GRASS_CONFIG);
		} else {
			SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, stone, water, seaLevel, minSurfaceLevel, seed, config);
		}
	}
}
*/
