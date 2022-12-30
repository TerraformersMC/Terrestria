package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.biolith.api.biomeperimeters.BiomePerimeters;
import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;

public class DuneSurfaceBuilder extends BiolithSurfaceBuilder {

	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(3445);

	private final BlockState topMaterial;

	public DuneSurfaceBuilder(BlockState topMaterial) {
		this.topMaterial = topMaterial;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		vHeight = chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x & 0xf, z & 0xf);
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
			column.setState(y, topMaterial);
			++y;
		}

		// Cap the height based on noise

		height = Math.min(height, (NOISE.sample(x * 0.03 + 5 , z * 0.05 + 5) * 30 + 6));

		for (int h = 0; h < height; h++) {
			column.setState(y, topMaterial);
			++y;
		}
	}
}
