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

	public CanyonSurfaceBuilder(Function<Dynamic<?>, ? extends CanyonSurfaceConfig> function, int seaLevel, SurfaceBuilder<TernarySurfaceConfig> parent) {
		super(function);

		this.seaLevel = seaLevel;
		this.parent = parent;
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, double noise, BlockState stone, BlockState water, int var11, long seed, CanyonSurfaceConfig config) {
		x &= 15;
		z &= 15;

		vHeight -= 1;

		//This gives the canyons valleys.
		if (noise > 0.24D && vHeight > seaLevel + 1 && vHeight < seaLevel + 55) {

			//User perlin noise for height after we test for generation
			double pNoise = (int) new Perlin(6).getNoiseLevelAtPosition(x, z) * 30;

			int height = vHeight - 1;

			//Generates canyon steps
			if (pNoise > seaLevel + 3) {
				height = seaLevel + 3;
			}
			if (pNoise > seaLevel + 5) {
				height = seaLevel + 5;
			}
			if (pNoise > seaLevel + 9) {
				height = seaLevel + 9;
			}
			if (pNoise > seaLevel + 14) {
				height = seaLevel + 14;
			}
			if (pNoise > seaLevel + 20) {
				height = seaLevel + 20;
			}
			if (pNoise > seaLevel + 27) {
				height = seaLevel + 27;
			}

			BlockPos.Mutable pos = new BlockPos.Mutable(x, seaLevel - 1, z);

			//Determine the number of cliff blocks to use
			int cliffLayers = 3;

			if (pNoise > 1.0D) {
				cliffLayers += rand.nextInt(2);
			}
			if (pNoise > 1.4D) {
				cliffLayers += height > seaLevel + 10 ?  3 : 1;
			}
			if (pNoise > 1.6D) {
				cliffLayers += height > seaLevel + 20 ?  4 : 2;
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
