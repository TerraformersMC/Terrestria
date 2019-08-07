package com.terraformersmc.terrestria.surface;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class RandomSurfaceBuilder extends SurfaceBuilder<RandomSurfaceConfig> {

	public RandomSurfaceBuilder() {
		super(RandomSurfaceConfig::deserialize);
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState deprecated, BlockState deprecated_2, int seaLevel, long seed, RandomSurfaceConfig config) {
		int localX = x & 15;
		int localZ = z & 15;
		
		int run = -1;
		BlockPos.Mutable chunkPos = new BlockPos.Mutable(localX, 0, localZ);
		
		for (int y = height; y >= 0; --y) {
			chunkPos.setY(y);
			
			Block currentBlock = chunk.getBlockState(chunkPos).getBlock();
			++run;
			if (currentBlock == Blocks.WATER) {
				run = -2;
				chunk.setBlockState(chunkPos, Blocks.WATER.getDefaultState(), false);
			} else if (currentBlock == Blocks.AIR) {
				run = -1;
				chunk.setBlockState(chunkPos, Blocks.AIR.getDefaultState(), false);
			} else if (currentBlock == Blocks.STONE) {
				BlockState stateToSet;
				if (run == 0) {
					stateToSet = config.getTopMaterial(rand, noise);
				} else if (run == -1) {
					run = 1;
					stateToSet = config.getUnderwaterMaterial(rand, noise);
				} else if (run < 5) {
					stateToSet = config.getUnderMaterial(rand, noise);
				} else {
					stateToSet = STONE;
				}
				chunk.setBlockState(chunkPos, stateToSet, false);
			}
		}
	}

}
