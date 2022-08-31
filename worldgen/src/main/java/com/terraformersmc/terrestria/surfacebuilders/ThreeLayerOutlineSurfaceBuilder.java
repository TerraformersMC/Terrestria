package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;

public class ThreeLayerOutlineSurfaceBuilder extends TerrestriaSurfaceBuilder {
	private static final OpenSimplexNoise TLO_NOISE = new OpenSimplexNoise(8675309);

	public static final BlockState STONE = Blocks.STONE.getDefaultState();

	private final BlockState topMaterial;
	private final BlockState topMiddle;
	private final float middleNoisePoint;
	private final BlockState topOuter;
	private final float outerNoisePoint;
	private final BlockState underMaterial;
	private final BlockState underwaterMaterial;

	public ThreeLayerOutlineSurfaceBuilder(BlockState topMaterial, BlockState topMiddle, float middleNoisePoint, BlockState topOuter, float outerNoisePoint, BlockState underMaterial, BlockState underwaterMaterial) {
		this.topMaterial = topMaterial;
		this.topMiddle = topMiddle;
		this.middleNoisePoint = middleNoisePoint;
		this.topOuter = topOuter;
		this.outerNoisePoint = outerNoisePoint;
		this.underMaterial = underMaterial;
		this.underwaterMaterial = underwaterMaterial;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		double noise = TLO_NOISE.sample(x, z);
		int run = -1;

		for (int y = vHeight; y >= 0; --y) {
			Block currentBlock = column.getState(y).getBlock();
			++run;

			if (currentBlock == Blocks.WATER) {
				run = -2;
				column.setState(y, Blocks.WATER.getDefaultState());
			} else if (currentBlock == Blocks.AIR) {
				run = -1;
				column.setState(y, Blocks.AIR.getDefaultState());
			} else if (currentBlock == Blocks.STONE) {
				BlockState stateToSet;
				if (run == 0) {
					stateToSet = topMaterial;
					if (noise > middleNoisePoint) {
						stateToSet = topMiddle;
					}
					if (noise > outerNoisePoint) {
						stateToSet = topOuter;
					}
				} else if (run == -1) {
					run = 1;
					stateToSet = underwaterMaterial;
				} else if (run < 5) {
					stateToSet = underMaterial;
				} else {
					stateToSet = STONE;
				}
				column.setState(y, stateToSet);
			}
		}
	}
}
