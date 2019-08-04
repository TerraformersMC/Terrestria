package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class DuneSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {

	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(3445);

	public DuneSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function) {
		super(function);
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, double noise, BlockState stone, BlockState water, int seaLevel, long seed, TernarySurfaceConfig config) {
		vHeight = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG).get(x & 15, z & 15) - 8;
		seaLevel -= 8;
		BlockPos.Mutable pos = new BlockPos.Mutable(x, vHeight, z);
		chunk.setBlockState(pos, config.getTopMaterial(), false);

		//Place the sand dunes
		double height = (NOISE.sample(x * 0.04 , z * 0.06) * 20) * ((double) vHeight / seaLevel);
		if (height < 0) {
			height = 0 - height - 4;
		} else {
			height += 4;
		}
		height += seaLevel;
		for (int i = 0; i < 8; i++) {
			chunk.setBlockState(pos, config.getTopMaterial(), false);
			pos.setOffset(Direction.UP);
		}
		for (double h = seaLevel; h < height && !isRemoved(x, (int) h, z); h++) {
			chunk.setBlockState(pos, config.getTopMaterial(), false);
			pos.setOffset(Direction.UP);
		}
	}

	//To get the removed sand look
	private boolean isRemoved(int x, int y, int z) {
		return y > (NOISE.sample(x * 0.03 + 5 , z * 0.05 + 5) * 20) + 63;
	}
}
