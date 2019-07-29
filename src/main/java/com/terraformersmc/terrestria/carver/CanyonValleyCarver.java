package com.terraformersmc.terrestria.carver;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.canyon.Perlin;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CanyonValleyCarver extends Carver<ProbabilityConfig> {

	private static final int DEPTH = -40;
	//absolute max this value can be is 16 (to make math easier)
	private static final int MAX_BALL_RADIUS = 8;
	private static final int LAYERS = 5;
	private static ArrayList<Perlin> depths = new ArrayList<>();
	private static ArrayList<Perlin> xOffsets = new ArrayList<>();
	private static ArrayList<Perlin> zOffsets = new ArrayList<>();

	public CanyonValleyCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int heightLimit) {
		super(config, heightLimit);
		Random random = new Random();
		//Generate a perlin noise map with a new seed for each layer of blobs to find their properties (depth and radius)
		for (int i = 0; i < LAYERS; i++) {
			depths.add(new Perlin(random.nextInt(1234)));
			xOffsets.add(new Perlin(random.nextInt(2455)));
			zOffsets.add(new Perlin(random.nextInt(3466)));
		}
	}

	@Override
	public boolean carve(Chunk chunk, Random random, int seaLevel, int startCunkX, int startChunkZ, int carvedChunkX, int carvedChunkZ, BitSet bitSet, ProbabilityConfig probabilityConfig) {
		for (int x = carvedChunkX * 16; x < carvedChunkX * 16 + 16; x++) {
			for (int z = carvedChunkZ * 16; z < carvedChunkZ * 16 + 16; z++) {
				for (int y = seaLevel + DEPTH; y < seaLevel + 32; y++) {
					if (isNearPoint(carvedChunkX, carvedChunkZ, x, y, z)) {
						chunk.setBlockState(new BlockPos.Mutable(x, y, z), Blocks.CAVE_AIR.getDefaultState(), false);
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

	private BlockPos findNearestPoint(int chunkX, int chunkZ, int x, int y, int z, boolean[] chunks) {
		BlockPos nearestPoint = null;
		int distance = 255;
		int tempDist;
		//Look in the current chunk first
		for (int l = 0; l < LAYERS; l++) {
			for (int cx = chunkX * 16; cx < chunkX * 16 + 16; cx++) {
				for (int cz = chunkX * 16; cz < chunkZ * 16 + 16; cz++) {
					for (int ch = 0; ch < 100; ch++) {
						if (cx == (int) xOffsets.get(l).getNoiseLevelAtPosition(chunkX, chunkZ) * 16.5 &&
							cz == (int) zOffsets.get(l).getNoiseLevelAtPosition(chunkX, chunkZ) * 16.5 &&
							ch == (int) depths.get(l).getNoiseLevelAtPosition(chunkX, chunkZ) * 16.5) {
							tempDist = (int) Math.sqrt(((x - cx)* (x - cx)) + ((z - cz)* (z - cz)) + ((y - ch)* (y - ch)));
							if (tempDist < distance) {
								distance = tempDist;
								nearestPoint = new BlockPos.Mutable(cx, ch, cz);
							}
						}
					}
				}
			}
		}

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

				for (int l = 0; l < LAYERS; l++) {
					for (int cx = chunkX * 16; cx < chunkX * 16 + 16; cx++) {
						for (int cz = chunkX * 16; cz < chunkZ * 16 + 16; cz++) {
							for (int ch = 0; ch < 100; ch++) {
								if (cx == (int) xOffsets.get(l).getNoiseLevelAtPosition(chunkX + cPosOffsetX, chunkZ + cPosOffsetZ) * 16.5 &&
									cz == (int) zOffsets.get(l).getNoiseLevelAtPosition(chunkX + cPosOffsetX, chunkZ + cPosOffsetZ) * 16.5 &&
									ch == (int) depths.get(l).getNoiseLevelAtPosition(chunkX + cPosOffsetX, chunkZ + cPosOffsetZ) * 16.5) {
									tempDist = (int) Math.sqrt(((x - cx)* (x - cx)) + ((z - cz)* (z - cz)) + ((y - ch)* (y - ch)));
									if (tempDist < distance) {
										distance = tempDist;
										nearestPoint = new BlockPos.Mutable(cx, ch, cz);
									}
								}
							}
						}
					}
				}
			}
		}

		if (nearestPoint == null) {
			return new BlockPos.Mutable(x + 99, 0, 0); //Make sure it's out of range, but not null still
		}
		return nearestPoint;
	}

	private boolean isNearPoint(int chunkX, int chunkZ, int x, int y, int z) {
		return findNearestPoint(chunkX, chunkZ, x, y, z, getChunksWithinRadius(x, z)).isWithinDistance(new Vec3i(x, y, z), MAX_BALL_RADIUS);
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
