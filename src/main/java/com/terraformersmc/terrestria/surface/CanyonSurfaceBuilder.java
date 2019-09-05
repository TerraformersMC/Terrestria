package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import com.terraformersmc.terrestria.feature.arch.Perlin;
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

	private static final OpenSimplexNoise CLIFF_NOISE = new OpenSimplexNoise(346987);

	public CanyonSurfaceBuilder(Function<Dynamic<?>, ? extends CanyonSurfaceConfig> function, int seaLevel, SurfaceBuilder<TernarySurfaceConfig> parent) {
		super(function);

		this.seaLevel = seaLevel;
		this.parent = parent;
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, double noise, BlockState stone, BlockState water, int var11, long seed, CanyonSurfaceConfig config) {
		if(/*noise < 0.1D || */vHeight < seaLevel + 5) {
			// In the future make this dig down instead
			// This will break some stuff like water flowing down so it may need an edge biome first

			parent.generate(rand, chunk, biome, x, z, vHeight, noise, stone, water, var11, seed, config);

			return;
		}

		// Uses perlin noise for height after we test for generation
		// double sNoise = PERLIN.getNoiseLevelAtPosition(x, z) * 30.0;
		double tNoise = PERLIN.getNoiseLevelAtPosition(x + 242, z + 138) * 3;

		double sNoise = Math.abs(CLIFF_NOISE.sample(x * 0.015, z * 0.015) * 60.0);

		int height = 1;

		// Generates canyon steps

		if (sNoise > 3) {
			height += 2;
		}

		if (sNoise > 5) {
			height += 3;
		}

		if (sNoise > 9) {
			height += 4;
		}

		if (sNoise > 14) {
			height += 4;
		}

		if (sNoise > 20) {
			height += 5;
		}

		if (sNoise > 27) {
			height += 6;
		}

		if (sNoise > 39) {
			height += 7;
		}

		if (sNoise > 55) {
			height += 8;
		}

		BlockPos.Mutable pos = new BlockPos.Mutable(x, seaLevel - 1, z);

		// Determine the number of cliff blocks to use
		int cliffLayers = (int) (tNoise + 0.5);

		if (height > 5) {
			cliffLayers += 1;
		}
		if (height > 10) {
			cliffLayers += sNoise > 10 ?  3 : 1;
		}
		if (height > 15) {
			cliffLayers += sNoise > 20 ?  4 : 2;
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
		for (int i = 0; i < PERLIN.getNoiseLevelAtPosition(x + 678, z + 567) * 2.5; i++) {
			chunk.setBlockState(pos, config.getTopMaterial(), false);
			pos.setOffset(Direction.UP);
		}

		if(pos.getY() <= vHeight) {
			// Prevent exposed stone.

			parent.generate(rand, chunk, biome, x, z, vHeight, noise, stone, water, var11, seed, config);
		}
	}
}
