package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.canyoncliffs.Perlin;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class CanyonSurfaceBuilder extends SurfaceBuilder<CanyonSurfaceConfig> {

	private int seaLevel;
	private SurfaceBuilder<TernarySurfaceConfig> parent;
	private static final Perlin PERLIN = new Perlin(14, 346987);
	private double pNoise;

	public CanyonSurfaceBuilder(Function<Dynamic<?>, ? extends CanyonSurfaceConfig> function, int seaLevel, SurfaceBuilder<TernarySurfaceConfig> parent) {
		super(function);

		this.seaLevel = seaLevel;
		this.parent = parent;
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, double noise, BlockState stone, BlockState water, int var11, long seed, CanyonSurfaceConfig config) {

		//This gives the canyons valleys.
		if (noise > 0.1D && vHeight > seaLevel) {

			//User perlin noise for height after we test for generation
			pNoise = PERLIN.getNoiseLevelAtPosition(x, z) * 30.0;

			int height = 1;

			//Generates canyon steps
			if (pNoise > 3) {
				height += 2;
			}
			if (pNoise > 5) {
				height += 4;
			}
			if (pNoise > 9) {
				height += 6;
			}
			if (pNoise > 14) {
				height += 8;
			}
			if (pNoise > 20) {
				height += 12;
			}
			if (pNoise > 27) {
				height += 14;
			}

			BlockPos.Mutable pos = new BlockPos.Mutable(x, seaLevel - 1, z);

			//Determine the number of cliff blocks to use
			int cliffLayers = 3;

			if (height > 10) {
				cliffLayers += 1;
			}
			if (height > 20) {
				cliffLayers += pNoise > 10 ?  3 : 1;
			}
			if (height > 30) {
				cliffLayers += pNoise > 20 ?  4 : 2;
			}

			//Start placing the cliff material
			for (int i = 0; i < height; i++) {
				chunk.setBlockState(pos, config.getCliffMaterial(), false);
				pos.setOffset(Direction.UP);
			}

			//Start placing the under material
			for (int i = 0; i < cliffLayers; i++) {
				chunk.setBlockState(pos, config.getUnderMaterial(), false);
				pos.setOffset(Direction.UP);
			}

			//Place the top Material
			chunk.setBlockState(pos, config.getTopMaterial(), false);
		} else {
			parent.generate(rand, chunk, biome, x, z, vHeight, noise, stone, water, var11, seed, config);
		}
	}
}
