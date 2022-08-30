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

public class OceanIslandSurfaceBuilder extends TerrestriaSurfaceBuilder {
	private static final OpenSimplexNoise ISLAND_NOISE = new OpenSimplexNoise(346987);
	private static final int DEEP_DEPTH = 32;
	private static final int SHALLOW_DEPTH = 16;
	private static final double HEIGHT_FACTOR = 1.1;

	private final BlockState topMaterial;
	private final BlockState midMaterial;
	private final BlockState lowMaterial;
	private final BlockState beachMaterial;
	private final BlockState underwaterMaterial;
	private final boolean deepOcean;

	public OceanIslandSurfaceBuilder(BlockState topMaterial, BlockState midMaterial, BlockState lowMaterial, BlockState beachMaterial, BlockState underwaterMaterial, boolean deepOcean) {
		this.topMaterial = topMaterial;
		this.midMaterial = midMaterial;
		this.lowMaterial = lowMaterial;
		this.beachMaterial = beachMaterial;
		this.underwaterMaterial = underwaterMaterial;
		this.deepOcean = deepOcean;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		int delta = (int)((deepOcean ? DEEP_DEPTH : SHALLOW_DEPTH) * HEIGHT_FACTOR);

		// We are going to accept the ocean surface noise and just raise it so we need the ocean surface.
		vHeight = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x & 0xf, z & 0xf);

		// Work around noise-adapting structures (like villages)...
		if (vHeight >= 60) {
			vHeight = seaLevel - delta;
		}

		// Delta is how much we are raising the surface.  Reduce it as we approach the edge of the island biome.
		int borderAdjustment = BiomePerimeters.getOrCreateInstance(biome, 40)
				.getPerimeterDistance(biomeAccess, new BlockPos(x, 62, z));
		if (borderAdjustment < 32) {
			delta = delta * borderAdjustment / 32;
		}

		int top = vHeight + delta;
		for (int y = 0; y <= top; y++) {
			BlockState originalState = column.getState(y);
			if (originalState.isOf(Blocks.STONE) || originalState.isOf(Blocks.WATER) || originalState.isAir()) {
				if (top > seaLevel + 9) {
					// In this case we are generating island "inland".
					if (y < top - 3) {
						column.setState(y, lowMaterial);
					} else if (y < top) {
						column.setState(y, midMaterial);
					} else {
						column.setState(y, topMaterial);
					}
				} else {
					// The noise below distributes the beach sand colors.
					boolean surfaceBias = Math.pow(1.1D, top - seaLevel) * ISLAND_NOISE.sample(x * 0.05D, z * 0.05D) > 0.15D;

					if (top >= seaLevel) {
						// In this case we are generating island beach.
						if (y < seaLevel - 3) {
							column.setState(y, lowMaterial);
						} else if (ISLAND_NOISE.sample(x * 0.04D, z * 0.04D) > 0.3D) {
							// The noise above creates "breakthrough" areas where there is no cliff.
							column.setState(y, y == top ? topMaterial : midMaterial);
						} else if (y < seaLevel) {
							// Place the main beach surface.
							column.setState(y, surfaceBias ? beachMaterial : underwaterMaterial);
						} else if (y < seaLevel - 1 + (top - seaLevel) / 4.5D && top > seaLevel + 4) {
							// Raise the beach level in some interior locations.
							column.setState(y, surfaceBias ? rand.nextFloat() > 0.78D ? lowMaterial : beachMaterial : underwaterMaterial);
						} else {
							column.setState(y, Blocks.AIR.getDefaultState());
						}
					} else {
						// In this case we are generating island off-shore (ocean).
						if (y < top - 3) {
							column.setState(y, lowMaterial);
						} else {
							column.setState(y, surfaceBias ? beachMaterial : underwaterMaterial);
						}
					}
				}
			}
		}
	}
}
