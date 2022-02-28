package com.terraformersmc.terrestria.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;

public class DuneSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {

	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(3445);

	public DuneSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
		super(codec);
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, double noise, BlockState stone, BlockState water, int seaLevel, int minSurfaceLevel, long seed, TernarySurfaceConfig config) {
		vHeight = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG).get(x & 15, z & 15);
		BlockPos.Mutable pos = new BlockPos.Mutable(x, vHeight - 8, z);

		// TODO: Blending doesn't work some of the time
		double blend = MathHelper.clamp((vHeight - seaLevel) * 0.125, 0, 1);

		double height = (NOISE.sample(x * 0.01 , z * 0.015) * 30) * blend;

		height = Math.abs(height);

		for (int i = 0; i < 8; i++) {
			chunk.setBlockState(pos, config.getTopMaterial(), false);
			pos.move(Direction.UP);
		}

		// Cap the height based on noise

		height = Math.min(height, (NOISE.sample(x * 0.03 + 5 , z * 0.05 + 5) * 30 + 6));

		for (int h = 0; h < height; h++) {
			chunk.setBlockState(pos, config.getTopMaterial(), false);
			pos.move(Direction.UP);
		}
	}
}
