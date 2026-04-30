package com.terraformersmc.terrestria.surface.builders;

import com.terraformersmc.biolith.api.biomeperimeters.BiomePerimeters;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terraform.noise.api.OpenSimplexNoise;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BlockColumn;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;

public class DuneSurfaceBuilder extends BiolithSurfaceBuilder {
	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(3445);

	private final BlockState topMaterial;

	public DuneSurfaceBuilder(BlockState topMaterial) {
		this.topMaterial = topMaterial;
	}

	@Override
	public void generate(BiomeManager biomeAccess, BlockColumn column, RandomSource rand, ChunkAccess chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		vHeight = chunk.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x & 0xf, z & 0xf);
		int y = vHeight - 8;

		double height = (NOISE.sample(x * 0.01 , z * 0.015) * 30);

		// Height is how much we are raising the surface.  Reduce it as we approach the edge of the Dunes biome.
		int borderAdjustment = BiomePerimeters.getOrCreateInstance(biome, 20)
				.getPerimeterDistance(biomeAccess, new BlockPos(x, 62, z));
		if (borderAdjustment < 16) {
			height *= borderAdjustment / 16.0D;
		}

		height = Math.abs(height);

		for (int i = 0; i < 8; i++) {
			column.setBlock(y, topMaterial);
			++y;
		}

		// Cap the height based on noise

		height = Math.min(height, (NOISE.sample(x * 0.03 + 5 , z * 0.05 + 5) * 30 + 6));

		for (int h = 0; h < height; h++) {
			column.setBlock(y, topMaterial);
			++y;
		}
	}
}
