package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import com.terraformersmc.terrestria.biomeperimeters.BiomePerimeters;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;

public class CalderaSurfaceBuilder extends TerrestriaSurfaceBuilder {
	private static final OpenSimplexNoise CALDERA_NOISE = new OpenSimplexNoise(27438);

	private static final int RIM_HEIGHT = 128;
	private static final int LAKE_LEVEL = RIM_HEIGHT - 28;

	private final BlockState topMaterial;
	private final BlockState midMaterial;
	private final BlockState lowMaterial;
	private final BlockState beachMaterial;

	public CalderaSurfaceBuilder(BlockState topMaterial, BlockState midMaterial, BlockState lowMaterial, BlockState beachMaterial) {
		this.topMaterial = topMaterial;
		this.midMaterial = midMaterial;
		this.lowMaterial = lowMaterial;
		this.beachMaterial = beachMaterial;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		int surfaceNoise = (int) (4.6D * CALDERA_NOISE.sample(x * 0.05D, z * 0.05D));
		int inBiomeDistance = BiomePerimeters.getOrCreateInstance(biome, 80).getPerimeterDistance(biomeAccess, new BlockPos.Mutable(x, RIM_HEIGHT, z));
		int top;

		// Re-sample vHeight so we match the surface even when we're in or abutting watery biomes.
		vHeight = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x & 0xf, z & 0xf);

		if (inBiomeDistance <= 16) {
			// Slope (up?) to rim
			top = vHeight + (RIM_HEIGHT + surfaceNoise - vHeight) * inBiomeDistance / 16;
		} else if (inBiomeDistance < 32) {
			// Across the rim
			top = RIM_HEIGHT + surfaceNoise;
		} else if (inBiomeDistance < 48) {
			// Slope down to caldera lake
			top = RIM_HEIGHT + surfaceNoise - 24 * (inBiomeDistance - 32) / 16;
		} else if (inBiomeDistance < 72) {
			// Lake shore down to lake bottom
			if (inBiomeDistance < 49) {
				top = RIM_HEIGHT - 24;
			} else if (inBiomeDistance < 53) {
				top = RIM_HEIGHT - 25;
			} else if (inBiomeDistance < 56) {
				top = RIM_HEIGHT - 26;
			} else if (inBiomeDistance < 58) {
				top = RIM_HEIGHT - 27;
			} else if (inBiomeDistance < 68) {
				top = RIM_HEIGHT - 38 + (68 - inBiomeDistance);
			} else {
				top = RIM_HEIGHT - 39 + surfaceNoise;
			}
		} else {
			// Lake bottom
			top = RIM_HEIGHT - 40 + surfaceNoise;
		}

		// Update the column with our overrides.
		for (int y = 0; y <= Math.max(Math.max(top, vHeight), LAKE_LEVEL); y++) {
			// Don't mess with the deepslate transition.
			if (column.getState(y).isOf(Blocks.DEEPSLATE)) {
				continue;
			}

			if (y < top - Math.abs(surfaceNoise)) {
				column.setState(y, lowMaterial);
			} else if (inBiomeDistance < 56) {
				// terrain
				if (y < top) {
					column.setState(y, midMaterial);
				} else if (y == top) {
					if (y < seaLevel - 1) {
						column.setState(y, midMaterial);
					} else {
						column.setState(y, topMaterial);
					}
				} else {
					if (y < seaLevel) {
						column.setState(y, Blocks.WATER.getDefaultState());
					} else {
						column.setState(y, Blocks.AIR.getDefaultState());
					}
				}
			} else {
				// lake sand
				if (y <= top) {
					column.setState(y, beachMaterial);
				} else if (y < LAKE_LEVEL) {
					column.setState(y, Blocks.WATER.getDefaultState());
				} else {
					column.setState(y, Blocks.AIR.getDefaultState());
				}
			}
		}
	}
}
