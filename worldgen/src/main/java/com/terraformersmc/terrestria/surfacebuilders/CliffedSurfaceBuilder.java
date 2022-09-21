package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import com.terraformersmc.terrestria.biomeperimeters.BiomePerimeters;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;

public class CliffedSurfaceBuilder extends TerrestriaSurfaceBuilder {
	private static final OpenSimplexNoise CLIFF_NOISE = new OpenSimplexNoise(346987);

	private static final int BUFFER = 16;

	private final BlockState topMaterial;
	private final BlockState midMaterial;
	private final BlockState lowMaterial;
	private final BlockState beachMaterial;
	private final BlockState underwaterMaterial;
	private final int cliffHeight;

	public CliffedSurfaceBuilder(BlockState topMaterial, BlockState midMaterial, BlockState lowMaterial, BlockState beachMaterial, BlockState underwaterMaterial, int cliffHeight) {
		this.topMaterial = topMaterial;
		this.midMaterial = midMaterial;
		this.lowMaterial = lowMaterial;
		this.beachMaterial = beachMaterial;
		this.underwaterMaterial = underwaterMaterial;
		this.cliffHeight = cliffHeight;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		int delta = cliffHeight;

		// We are going to accept the ocean surface noise and just raise it so we need the ocean surface.
		vHeight = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x & 0xf, z & 0xf);

		// Delta is how much we are raising the surface.  Reduce it as we approach the edge of the island biome.
		int borderAdjustment = BiomePerimeters.getOrCreateInstance(biome, 40)
				.getPerimeterDistance(biomeAccess, new BlockPos(x, 62, z));

		borderAdjustment -= BUFFER;
		int soilDepth = 0;
		// Plateau
		if (borderAdjustment > 12) {
			soilDepth = 6;
		} else if (borderAdjustment > 9) {
			soilDepth = borderAdjustment - 6;
		// Top of the cliff
		} else if (borderAdjustment == 9) {
			--delta;
			soilDepth = 3;
		} else if (borderAdjustment > 5) {
			delta -= 9 - borderAdjustment;
			soilDepth = borderAdjustment - 6;
		} else if (borderAdjustment == 5) {
			delta -= 5;
		// Foot of the cliff
		} else if (borderAdjustment == 4) {
			delta = 5;
	    } else {
			delta = Math.max(borderAdjustment, 0);
		}

		// Final solid surface level
		int top = vHeight + delta;

		// Fill in starting where the original stone left off.
		for (int y = vHeight - 1; y <= top; y++) {
			if (top >= seaLevel + 3) {
				// On the cliffs, soil establishes as we head inland.
				if (y == top && soilDepth > 0) {
					column.setState(y, topMaterial);
				} else if (soilDepth - (top - y) > 0) {
					column.setState(y, midMaterial);
				} else {
					column.setState(y, lowMaterial);
				}
			} else if (top >= seaLevel) {
				// Beach level can have some beach material.
				column.setState(y, y >= seaLevel ? beachMaterial : lowMaterial);
			} else {
				// Underwater just gets a skin of whatever the underwater material is, over the low material.
				column.setState(y, y == top ? underwaterMaterial : lowMaterial);
			}
		}
	}
}
