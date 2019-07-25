package com.terraformersmc.terrestria.feature.canyoncliffs;

import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class CanyonCliffGenerator extends StructurePiece {

	private static final int SHAPE_NOISE_ZOOM = 10;
	private static final int TOP_NOISE_ZOOM = 15;
	private static final int VERTICAL_NOISE_ZOOM = 8;
	private static final int MATERIAL_NOISE_ZOOM = 7;

	private int seed;

	private Perlin shapeNoise;
	private Perlin topNoise;
	private Perlin verticalNoise;
	private Perlin materialNoise;

	private int maxHeight;
	private int radius;
	private int yStart;

	private int centerX;
	private int centerZ;

	CanyonCliffGenerator(Random random, int centerX, int centerZ, Biome biome) {
		super(TerrestriaFeatures.CANYON_CLIFF_PIECE, 0);
		this.setOrientation(null);
		this.centerX = centerX;
		this.centerZ = centerZ;
		seed = random.nextInt(10000);
		shapeNoise = new Perlin(SHAPE_NOISE_ZOOM, seed);
		topNoise = new Perlin(TOP_NOISE_ZOOM, seed);
		verticalNoise = new Perlin(VERTICAL_NOISE_ZOOM, seed);
		materialNoise = new Perlin(MATERIAL_NOISE_ZOOM, seed);
		maxHeight = 55 + random.nextInt(50);
		yStart = 30;
		radius = 15 + random.nextInt(40);

		int radiusBound = MathHelper.ceil(radius * 1.5);
		this.boundingBox = new MutableIntBoundingBox(centerX - radiusBound, 30, centerZ - radiusBound, centerX + radiusBound, 30 + maxHeight, centerZ + radiusBound);
	}

	public CanyonCliffGenerator(StructureManager manager, CompoundTag tag) {
		super(TerrestriaFeatures.CANYON_CLIFF_PIECE, tag);
		seed = tag.getInt("S");
		shapeNoise = new Perlin(SHAPE_NOISE_ZOOM, tag.getInt("SN"));
		topNoise = new Perlin(TOP_NOISE_ZOOM, tag.getInt("TN"));
		verticalNoise = new Perlin(VERTICAL_NOISE_ZOOM, tag.getInt("VN"));
		materialNoise = new Perlin(MATERIAL_NOISE_ZOOM, tag.getInt("MN"));
		maxHeight = tag.getInt("MH");
		radius = tag.getInt("R");
		yStart = tag.getInt("S");
		centerX = tag.getInt("CX");
		centerZ = tag.getInt("CZ");
	}

	@Override
	protected void toNbt(CompoundTag tag) {
		tag.putInt("S", seed);
		tag.putInt("SN", shapeNoise.getSeed());
		tag.putInt("TN", topNoise.getSeed());
		tag.putInt("VN", verticalNoise.getSeed());
		tag.putInt("MN", materialNoise.getSeed());
		tag.putInt("MH", maxHeight);
		tag.putInt("R", radius);
		tag.putInt("S", yStart);
		tag.putInt("CX", centerX);
		tag.putInt("CZ", centerZ);
	}

	@Override
	public boolean generate(IWorld world, Random random, MutableIntBoundingBox boundingBox, ChunkPos chunkPos) {
		if (boundingBox.maxY < this.boundingBox.maxY || boundingBox.minY > this.boundingBox.minY) {
			throw new IllegalArgumentException("Unexpected bounding box Y range in " + boundingBox + ", the Y range is smaller than the one we expected");
		}

		BlockPos.Mutable pos = new BlockPos.Mutable();
		int dX;
		int dZ;
		double dist;

		for (int z = boundingBox.minZ; z <= boundingBox.maxZ; z++) {
			for (int x = boundingBox.minX; x <= boundingBox.maxX; x++) {

				dX = x - centerX;
				dZ = z - centerZ;
				dist = Math.sqrt((dZ * dZ) + (dX * dX));

				for (int h = 0; h < maxHeight - (topNoise.getNoiseLevelAtPosition(x, z) * 8); h++) {
					if (shapeArch((double) h, dist, x, z)) {
						pos.set(x, yStart + h, z);
						world.setBlockState(pos, getStateAtY(h, x, z), 2);
					}
				}
			}
		}
		return true;
	}

	private boolean shape(double height, double distance, int x, int z) {
		if (radius < 13) {
			if (height > 45) {
				return radiusUniform(height, distance);
			} else {
				return detailDome(height, distance);
			}
		} else if (radius > 24) {
			if (height < 67) {
				return radiusBloob(height, distance);
			} else {
				return shapeArch(height, distance, x, z);
			}
		}
		return radiusPerlin(height, distance, x, z);
	}

	public int getNthDigit(int number, int n) {
		return (int) ((number / Math.pow(10, n - 1)) % 10);
	}

	//Generates a dome
	private boolean shapeArch(double h, double distance, int x, int z) {
		//Find the distance from the vertex of the dome
		double distVertex = Math.sqrt(((x - centerX)* (x - centerX)) + ((z - centerZ)* (z - centerZ)) + ((h - 30.0)* (h - 30.0)));
		//Find the perpendicular distance from the current 2d coordinate from a 2d line generated from the first and second indexes of the seed
		double a = centerX < 1 ? -getNthDigit(seed, 2) : getNthDigit(seed, 2); // allows for a negative number, just use one of the center cords for ease
		double distLine = Math.abs((a * (x - centerX)) + z - centerZ) / Math.sqrt((a * a) + 1);
		return distVertex > radius - 5 && distVertex < radius && distLine < 2 + ((maxHeight - h) / 6);
	}

	private boolean detailDome(double h, double distance) {
		return -((maxHeight * 4.7) / (radius * radius)) * h + 10 > distance;
	}

	private boolean radiusPerlin(double h, double distance, int x, int z) {
		return maxHeight - weightedHeight(shapeNoise.getNoiseLevelAtPosition(x, z), distance) * (maxHeight / 12.0) < h;
	}

	private boolean radiusUniform(double h, double distance) {
		return radius - (verticalNoise.getNoiseLevelAtPosition((int)h + seed * 1440,seed) * 6) > distance;
	}

	private boolean radiusBloob(double h, double distance) {
		h = h / maxHeight;
		return (radius * (3.9 * (h * h) - (1.5 * (h * h * h)) - (2.6 * h) + 0.95)) - (verticalNoise.getNoiseLevelAtPosition((int) h, seed) * 8) > distance;
	}

	//Decides the 2d shape bounds of the feature
	private boolean fitsShape(int x, int z, double distance) {
		return weightedHeight(shapeNoise.getNoiseLevelAtPosition(x, z), distance) > (maxHeight * 0.2);
	}

	private double weightedHeight(double noiseHeight, double distance) {
		return noiseHeight * (maxHeight * Math.cos((3.14 * distance) / (radius * 2)));
	}

	private BlockState getStateAtY(int height, int x, int z) {
		if (materialNoise.getNoiseLevelAtPosition(x + (height / 6 * seed), z - (height / 6 * seed)) * 3 > height % 6) {
			return Blocks.SMOOTH_SANDSTONE.getDefaultState();
		} else {
			if (materialNoise.getNoiseLevelAtPosition(x - (height / 6 * seed), z + (height / 6 * seed)) * 4 > height % 3) {
				return Blocks.TERRACOTTA.getDefaultState();
			} else {
				return Blocks.SMOOTH_SANDSTONE.getDefaultState();
			}
		}
	}
}
