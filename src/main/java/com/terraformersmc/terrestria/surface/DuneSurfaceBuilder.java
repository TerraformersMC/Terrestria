package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.Perlin;
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

	private static final Perlin PERLIN = new Perlin(32, 3445);

	public DuneSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function) {
		super(function);
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, double noise, BlockState stone, BlockState water, int seaLevel, long seed, TernarySurfaceConfig config) {
		vHeight = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG).get(x & 15, z & 15);

		BlockPos.Mutable pos = new BlockPos.Mutable(x, vHeight, z);
		chunk.setBlockState(pos, config.getTopMaterial(), false);
		//Place the sand dunes
		for (int h = 0; h < (PERLIN.getNoiseLevelAtPosition(x, (int) (z*1.5)) * (seaLevel - vHeight)) * 5; h++) {
			chunk.setBlockState(pos, config.getTopMaterial(), false);
			pos.setOffset(Direction.UP);
		}
	}
}
