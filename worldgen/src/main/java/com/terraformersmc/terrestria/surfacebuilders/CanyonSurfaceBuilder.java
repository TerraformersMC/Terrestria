package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;

public class CanyonSurfaceBuilder extends BiolithSurfaceBuilder {

	private static final OpenSimplexNoise CLIFF_NOISE = new OpenSimplexNoise(346987);

	private final BlockState cliffMaterial;
	private final BlockState topMaterial;
	private final BlockState underMaterial;

	public CanyonSurfaceBuilder(BlockState cliffMaterial, BlockState topMaterial, BlockState underMaterial) {
		this.cliffMaterial = cliffMaterial;
		this.topMaterial = topMaterial;
		this.underMaterial = underMaterial;
	}

	private static int underNoiseToLayers(double noise, int cliffLayers) {
		int underLayers = (int) (noise * 3 + 0.5);

		if (cliffLayers > 5) {
			underLayers += 1;
		}

		if (cliffLayers > 10) {
			underLayers += 3;
		}

		if (cliffLayers > 15) {
			underLayers += 4;
		}

		return underLayers;
	}

	/**
	 * "terraces" the input noise (from 0.0 to 1.0) returning an integer from 1 to 40, inclusive
	 * Domain: [-1.0, 1.0]
	 * Range: [1, 40]
	 */

	private static int cliffNoiseToLayers(double noise) {
		// Domain transformation:
		// [0.0, 1.0] -> [0, 60]

		noise = MathHelper.clamp(noise, 0, 1);
		noise *= 60;

		int height = 1;

		if (noise > 3) {
			height += 2;
		}

		if (noise > 5) {
			height += 3;
		}

		if (noise > 9) {
			height += 4;
		}

		if (noise > 14) {
			height += 4;
		}

		if (noise > 20) {
			height += 5;
		}

		if (noise > 27) {
			height += 6;
		}

		if (noise > 39) {
			height += 7;
		}

		if (noise > 55) {
			height += 8;
		}

		return height;
	}

	@Override
	public void generate(BiomeAccess biomeAccess, BlockColumn column, Random rand, Chunk chunk, Biome biome, int x, int z, int vHeight, int seaLevel) {
		if (vHeight < seaLevel + 5) {
			// In the future make this dig down instead
			// This will break some stuff like water flowing down so it may need an edge biome first

			return;
		}

		int y = seaLevel - 1;

		// Generate noise values

		double cliffNoise = Math.abs(CLIFF_NOISE.sample(x * 0.015, z * 0.015));
		double underNoise = Math.abs(CLIFF_NOISE.sample(x * 0.015 - 1024, z * 0.015 - 1024));
		double topNoise = Math.abs(CLIFF_NOISE.sample(x * 0.015 + 1024, z * 0.015 + 1024));

		// Prevent huge cliffs near borders, make them slightly smaller cliffs

		if (vHeight < seaLevel + 8 && cliffNoise > 0.3) {
			// seaLevel+5 -> 1/4, seaLevel+6 -> 2/4, seaLevel+7 -> 3/4

			cliffNoise *= (vHeight - seaLevel - 3) * 0.25;
		}

		// Convert noise values to layer counts

		int cliffLayers = cliffNoiseToLayers(cliffNoise);
		int underLayers = underNoiseToLayers(underNoise, cliffLayers);
		int topLayers = (int) (topNoise * 2.5) + 1;

		// Place cliff material

		for (int i = 0; i < cliffLayers; i++) {
			column.setState(y, cliffMaterial);
			++y;
		}

		// Place under material

		for (int i = 0; i < underLayers; i++) {
			column.setState(y, underMaterial);
			++y;
		}

		// Place top material

		for (int i = 0; i < topLayers; i++) {
			column.setState(y, topMaterial);
			++y;
		}
	}
}
