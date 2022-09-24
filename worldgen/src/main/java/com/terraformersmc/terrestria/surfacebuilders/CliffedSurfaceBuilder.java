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
	private static final OpenSimplexNoise CLIFF_NOISE = new OpenSimplexNoise(841717094);

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
		int underseaDelta = Math.max(seaLevel - vHeight - 1, 0);

		// Delta is how much we are raising the surface.  We reduce it as we approach the edge of the island biome.
		int borderAdjustment = BiomePerimeters.getOrCreateInstance(biome, 40)
				.getPerimeterDistance(biomeAccess, new BlockPos(x, 62, z));

		int soilDepth = 0;
		// Plateau
		if (borderAdjustment >= 28) {
			soilDepth = 6;
		// Top of the cliff
		}  else if (borderAdjustment >= 20) {
			double noise = (CLIFF_NOISE.sample(x * 0.03D, z * 0.03D) + 1.0D) * 4.0D;
			delta = (int) (underseaDelta +
					(delta - underseaDelta)
					* Math.log(borderAdjustment - 18.0D + noise)
					/ Math.log(9.0D + noise));
			soilDepth = Math.max(borderAdjustment - 22, 0);
		// Foot of the cliff
	    } else if (borderAdjustment >= 16) {
			delta = underseaDelta + borderAdjustment - 16;
		// Underwater (if in a sea)
		} else {
			delta = (int) (underseaDelta * Math.pow(1.26D, borderAdjustment) / 32.0D);
		}

		// Final solid surface level
		int top = vHeight + delta;

		// Fill in starting where the original stone left off.
		for (int y = vHeight - 1; y <= top; y++) {
			if (top >= seaLevel + 5) {
				// On the cliffs, soil establishes as we head inland.
				if (y == top && soilDepth > 0) {
					column.setState(y, topMaterial);
				} else if (soilDepth - (top - y) > 0) {
					column.setState(y, midMaterial);
				} else {
					column.setState(y, lowMaterial);
				}
			} else if (top >= seaLevel - 1) {
				// Beach level can have some beach material.
				column.setState(y, y == top ? beachMaterial : lowMaterial);
			} else if (top > vHeight + 5) {
				// Steep underwater is all low material since sand and gravel slide off.
				column.setState(y, lowMaterial);
			} else {
				// Deep underwater gets a skin of whatever the underwater material is, over the low material.
				column.setState(y, y == top ? underwaterMaterial : lowMaterial);
			}
		}
	}
}
