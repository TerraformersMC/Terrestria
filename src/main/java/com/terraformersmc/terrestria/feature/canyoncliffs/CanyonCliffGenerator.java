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
	private static final int TOP_NOISE_ZOOM = 6;
	private static final int VERTICAL_NOISE_ZOOM = 18;
	private static final int MATERIAL_NOISE_ZOOM = 7;

	private int seed;
	private int shapeType;

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
		shapeType = random.nextInt(2);
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
		shapeType = tag.getInt("CS");
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
		tag.putInt("CS", shapeType);
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

				if (fitsShape(x, z, dist)) {
					for (int h = 0; h < maxHeight; h++) {
						if (radiusBloob(h) > dist) {
							pos.set(x, yStart + h, z);
							world.setBlockState(pos, getStateAtY(h, x, z), 2);
						}
					}
				}
			}
		}
		return true;
	}

	private double shape(int h) {
		switch (shapeType) {
			case 1:
				return radiusPerlin(h);
			case 2:
				return radiusUniform(h);
			default:
				return radiusBloob(h);
		}
	}

	private double radiusPerlin(int h) {
		return radius - (verticalNoise.getNoiseLevelAtPosition(h,1) * 3) + (radius / 1.25);
	}

	private double radiusUniform(int h) {
		return radius - (verticalNoise.getNoiseLevelAtPosition(h,1) * 4);
	}

	//Bloob shape (idk what to call it lol)
	private double radiusBloob(int h) {
		h = h / maxHeight;
		return radius * ((2 * (h*h)) - (h*h*h) - h + 0.95);
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
