package com.terraformersmc.terrestria.feature.canyon;

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

public class CanyonArchGenerator extends StructurePiece {

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

	CanyonArchGenerator(Random random, int centerX, int centerZ, Biome biome) {
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

	public CanyonArchGenerator(StructureManager manager, CompoundTag tag) {
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

		for (int z = boundingBox.minZ; z <= boundingBox.maxZ; z++) {
			for (int x = boundingBox.minX; x <= boundingBox.maxX; x++) {
				for (int h = 0; h < maxHeight - (topNoise.getNoiseLevelAtPosition(x, z) * 8); h++) {
					if (shapeArch((double) h, x, z)) {
						pos.set(x, yStart + h, z);
						world.setBlockState(pos, getStateAtY(h, x, z), 2);
					}
				}
			}
		}
		return true;
	}

	public int getNthDigit(int number, int n) {
		return (int) ((number / Math.pow(10, n - 1)) % 10);
	}

	/*
	private boolean shapeArch(double h, int x, int z) {
		//Find the distance from the vertex
		double distVertex = Math.sqrt(((x - centerX)* (x - centerX)) + ((z - centerZ)* (z - centerZ)) + ((h - 30.0)* (h - 30.0)));
		//Find the perpendicular distance from the current 2d coordinate from a 2d line generated from the first and second indexes of the seed
		double a = centerX < 1 ? -getNthDigit(seed, 2) : getNthDigit(seed, 2); // allows for a negative number, just use one of the center cords for ease
		double distLine = Math.abs((a * (x - centerX)) + z - centerZ) / Math.sqrt((a * a) + 1);
		return distVertex > radius - 5 && distVertex < radius && distLine < 2 + ((maxHeight - h) / 6);
	}
	 */

	private boolean shapeArch(double h, int x, int z) {
		return (Math.sqrt(((x - centerX)* (x - centerX)) + ((z - centerZ)* (z - centerZ)) + ((h - 30.0)* (h - 30.0)))) > radius - 5 &&
				(Math.sqrt(((x - centerX)* (x - centerX)) + ((z - centerZ)* (z - centerZ)) + ((h - 30.0)* (h - 30.0)))) < radius &&
				(Math.abs(((centerX < 1 ? -getNthDigit(seed, 2) : getNthDigit(seed, 2)) * (x - centerX)) + z - centerZ) / Math.sqrt((getNthDigit(seed, 2) * getNthDigit(seed, 2)) + 1))
						< 2 + ((maxHeight - h) / 6);
	}

	//Generates the stone layers
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
