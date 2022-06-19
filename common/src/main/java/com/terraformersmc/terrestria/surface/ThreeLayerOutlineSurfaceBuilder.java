package com.terraformersmc.terrestria.surface;
/*
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import java.util.Random;

public class ThreeLayerOutlineSurfaceBuilder extends SurfaceBuilder<ThreeLayerOutlinedSurfaceConfig> {
	public static final BlockState STONE = Blocks.STONE.getDefaultState();

	public ThreeLayerOutlineSurfaceBuilder(Codec<ThreeLayerOutlinedSurfaceConfig> codec) {
		super(codec);
	}

	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState deprecated, BlockState deprecated_2, int seaLevel, int minSurfaceLevel, long seed, ThreeLayerOutlinedSurfaceConfig config) {
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
					stateToSet = config.getTopMaterial();
					if (noise > config.getMiddleNoisePoint()) {
						stateToSet = config.getMiddleMaterial();
					}
					if (noise > config.getOuterNoisePoint()) {
						stateToSet = config.getOuterMaterial();
					}
				} else if (run == -1) {
					run = 1;
					stateToSet = config.getUnderwaterMaterial();
				} else if (run < 5) {
					stateToSet = config.getUnderMaterial();
				} else {
					stateToSet = STONE;
				}
				chunk.setBlockState(chunkPos, stateToSet, false);
			}
		}
	}

}
*/
