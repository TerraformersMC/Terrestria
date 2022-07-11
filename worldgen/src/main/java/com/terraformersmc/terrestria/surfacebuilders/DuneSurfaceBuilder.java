package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.random.AbstractRandom;

public class DuneSurfaceBuilder extends TerrestriaSurfaceBuilder {

	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(3445);

	private final BlockState topMaterial;

	public DuneSurfaceBuilder(BlockState topMaterial) {
		this.topMaterial = topMaterial;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, AbstractRandom rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		vHeight = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG).get(x & 15, z & 15);
		int y = vHeight - 8;

		// TODO: Blending doesn't work some of the time
		double blend = MathHelper.clamp((vHeight - seaLevel) * 0.125, 0, 1);

		double height = (NOISE.sample(x * 0.01 , z * 0.015) * 30) * blend;

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
