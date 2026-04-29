package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.biolith.api.biomeperimeters.BiomePerimeters;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terraform.noise.api.OpenSimplexNoise;
import com.terraformersmc.terrestria.TerrestriaWorldgen;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class OceanIslandSurfaceBuilder extends BiolithSurfaceBuilder {
	private static final OpenSimplexNoise ISLAND_NOISE = new OpenSimplexNoise(346987);
	private static final int ISLAND_HEIGHT = 12;
	private static final double NOISE_SCALE = 9.5d;

	private final BlockState topMaterial;
	private final BlockState midMaterial;
	private final BlockState lowMaterial;
	private final BlockState beachMaterial;
	private final BlockState underwaterMaterial;

	public OceanIslandSurfaceBuilder(BlockState topMaterial, BlockState midMaterial, BlockState lowMaterial, BlockState beachMaterial, BlockState underwaterMaterial) {
		this.topMaterial = topMaterial;
		this.midMaterial = midMaterial;
		this.lowMaterial = lowMaterial;
		this.beachMaterial = beachMaterial;
		this.underwaterMaterial = underwaterMaterial;
	}

	@Override
	public void generate(BiomeManager biomeAccess, BlockColumn column, RandomSource rand, ChunkAccess chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		ServerLevel overworld = TerrestriaWorldgen.getOverworld();
		if (overworld == null) {
			throw new IllegalStateException("Overworld does not exist during Overworld surface generation...");
		}
		NormalNoise surface = overworld.getChunkSource().chunkMap.randomState
				.getOrCreateNoise(Noises.SURFACE);

		// Find the original top Y value, the desired top Y value, and the delta between them.
		// We can't trust the provided vHeight because we need the ocean floor instead of surface.
		vHeight = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x & 0xf, z & 0xf);
		int top = seaLevel + ISLAND_HEIGHT + (int) (NOISE_SCALE * surface.getValue(x, seaLevel, z));
		int delta = Mth.clamp(top - vHeight, 0, 128);

		// Work around noise-adapting structures (like villages)...
		if (vHeight >= seaLevel - 2) {
			vHeight = seaLevel - delta;
		}

		// Delta is how much we are raising the surface.  Reduce it as we approach the edge of the island biome.
		int borderAdjustment = BiomePerimeters.getOrCreateInstance(biome, 40)
				.getPerimeterDistance(biomeAccess, new BlockPos(x, seaLevel, z));
		if (borderAdjustment < 32) {
			delta = delta * borderAdjustment / 32;
		}

		top = vHeight + delta;
		for (int y = 0; y <= top; y++) {
			BlockState originalState = column.getBlock(y);
			if (originalState.is(Blocks.STONE) || originalState.is(Blocks.WATER) || originalState.isAir()) {
				if (top > seaLevel + 9) {
					// In this case we are generating island "inland".
					if (y < top - 3) {
						column.setBlock(y, lowMaterial);
					} else if (y < top) {
						column.setBlock(y, midMaterial);
					} else {
						column.setBlock(y, topMaterial);
					}
				} else {
					// The noise below distributes the beach sand colors.
					boolean surfaceBias = Math.pow(1.1D, top - seaLevel) * ISLAND_NOISE.sample(x * 0.05D, z * 0.05D) > 0.15D;

					if (top >= seaLevel) {
						// In this case we are generating island beach.
						if (y < seaLevel - 3) {
							column.setBlock(y, lowMaterial);
						} else if (ISLAND_NOISE.sample(x * 0.04D, z * 0.04D) > 0.3D) {
							// The noise above creates "breakthrough" areas where there is no cliff.
							column.setBlock(y, y == top ? topMaterial : midMaterial);
						} else if (y < seaLevel) {
							// Place the main beach surface.
							column.setBlock(y, surfaceBias ? beachMaterial : underwaterMaterial);
						} else if (y < seaLevel - 1 + (top - seaLevel) / 4.5D && top > seaLevel + 4) {
							// Raise the beach level in some interior locations.
							column.setBlock(y, surfaceBias ? rand.nextFloat() > 0.78D ? lowMaterial : beachMaterial : underwaterMaterial);
						} else {
							column.setBlock(y, Blocks.AIR.defaultBlockState());
						}
					} else {
						// In this case we are generating island off-shore (ocean).
						if (y < top - 3) {
							column.setBlock(y, lowMaterial);
						} else {
							column.setBlock(y, surfaceBias ? beachMaterial : underwaterMaterial);
						}
					}
				}
			}
		}
	}
}
