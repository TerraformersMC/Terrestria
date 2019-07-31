package com.terraformersmc.terrestria.carver;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CanyonValleyCarver extends Carver<ProbabilityConfig> {

	private static final int DEPTH = 10;
	//absolute max this value can be is 16 (to make math easier)
	private static final int MAX_BALL_RADIUS = 8;
	private static final int LAYERS = 5;
	private static int[] depths = new int[LAYERS];
	private static int[] xOffsets = new int[LAYERS];
	private static int[] zOffsets = new int[LAYERS];

	public CanyonValleyCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int heightLimit) {
		super(config, heightLimit);
	}

	@Override
	public boolean carve(Chunk chunk, Random random, int seaLevel, int startChunkX, int startChunkZ, int carvedChunkX, int carvedChunkZ, BitSet bitSet, ProbabilityConfig probabilityConfig) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 100; y++) {
					if (isNearPoint(carvedChunkX, carvedChunkZ, carvedChunkX * 16 + x, seaLevel - y, carvedChunkZ * 16 + z, seaLevel)) {
						chunk.setBlockState(new BlockPos.Mutable(carvedChunkX * 16 + x, seaLevel - y, carvedChunkZ * 16 + z), Blocks.CAVE_AIR.getDefaultState(), false);
					}
				}
			}
		}
		return true;
	}

	//returns a boolean array with values responding to directions boolean[nw, n, ne, e, se, s, sw, w]
	private boolean[] getChunksWithinRadius(int x, int z) {
		return new boolean[] {
			(x % 16 - MAX_BALL_RADIUS) < 1 && (z % 16 - MAX_BALL_RADIUS) < 1,
			(z % 16 - MAX_BALL_RADIUS) < 1,
			(x % 16 + MAX_BALL_RADIUS) > 16 && (z % 16 - MAX_BALL_RADIUS) < 1,
			(x % 16 + MAX_BALL_RADIUS) > 16,
			(x % 16 + MAX_BALL_RADIUS) < 0 && (z % 16 + MAX_BALL_RADIUS) > 16,
			(z % 16 + MAX_BALL_RADIUS) > 16,
			(x % 16 - MAX_BALL_RADIUS) < 1 && (z % 16 + MAX_BALL_RADIUS) > 16,
			(x % 16 - MAX_BALL_RADIUS) < 1
		};
	}

	private BlockPos findNearestPoint(int chunkX, int chunkZ, int x, int y, int z, boolean[] chunks, int seaLevel) {
		BlockPos nearestPoint = null;
		int xOrigin = (chunkX * 16);
		int zOrigin = (chunkZ * 16);
		int xOffset;
		int zOffset;
		int tY = seaLevel - DEPTH;
		int depthDist = 255;
		//Look in the current chunk first
		generateOffsetsForChunk(chunkX, chunkZ);

		//Find the depth closest to the point
		for (int l = 0; l < LAYERS; l++) {
			tY = tY + depths[l];
			if (Math.abs(y - tY) < depthDist) {
				depthDist = Math.abs(y - tY);
			} else {
				//tY is now the y coordinate of the closest point
				tY = tY - depths[l];

				xOffset = xOffsets[l];
				zOffset = zOffsets[l];
				nearestPoint = new BlockPos.Mutable(xOrigin + xOffset, tY, zOrigin + zOffset);
			}
		}

		//Look in chunks withing radius
		int cPosOffsetX = 0;
		int cPosOffsetZ = 0;
		for (int i = 0; i < 8; i++) {
			if (chunks[i]) {

				if (i == 0 || i == 6 || i == 7) {
					cPosOffsetX = -1;
				}
				if (i == 2 || i == 3 || i == 4) {
					cPosOffsetX = 1;
				}
				if (i == 4 || i == 5 || i == 6) {
					cPosOffsetZ = 1;
				}
				if (i == 0 || i == 1 || i == 2) {
					cPosOffsetZ = -1;
				}

				generateOffsetsForChunk(chunkX + cPosOffsetX, chunkZ + cPosOffsetZ);
				for (int l = 0; l < LAYERS; l++) {
					tY = tY + depths[l];
					if (Math.abs(y - tY) < depthDist) {
						depthDist = Math.abs(y - tY);
					} else {
						//tY is now the y coordinate of the closest point
						tY = tY - depths[l];

						xOffset = xOffsets[l];
						zOffset = zOffsets[l];
						nearestPoint = new BlockPos.Mutable(xOrigin + xOffset, tY, zOrigin + zOffset);
					}
				}
			}
		}

		if (nearestPoint == null) {
			return new BlockPos.Mutable(99 - x, 0, 0); //Make sure it's out of range, but not null still
		}
		return nearestPoint;
	}

	private boolean isNearPoint(int chunkX, int chunkZ, int x, int y, int z, int seaLevel) {
		return findNearestPoint(chunkX, chunkZ, x, y, z, getChunksWithinRadius(x, z), seaLevel).isWithinDistance(new Vec3i(x, y, z), MAX_BALL_RADIUS);
	}

	private static void generateOffsetsForChunk(int chunkX, int chunkZ) {
		Random random = new Random();
		for (int i = 0; i < LAYERS; i++) {
			random.setSeed(Integer.parseInt(Math.abs(chunkX) + "" + Math.abs(chunkZ) + "" + i));
			depths[i] = random.nextInt((int) (MAX_BALL_RADIUS * 1.8));
			xOffsets[i] = random.nextInt(16);
			zOffsets[i] = random.nextInt(16);
		}
	}

	@Override
	public boolean shouldCarve(Random random, int i, int i1, ProbabilityConfig probabilityConfig) {
		return true;
	}

	@Override
	protected boolean isPositionExcluded(double v, double v1, double v2, int i) {
		return false;
	}
}
