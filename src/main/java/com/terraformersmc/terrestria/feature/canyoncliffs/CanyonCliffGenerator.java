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

	private static final int HORIZONTAL_NOISE_ZOOM = 8;
	private static final int TOP_NOISE_ZOOM = 6;
	private static final int VERTICAL_NOISE_ZOOM = 18;

	private int seed;
	private int shapeType;

	private Perlin shapeNoise;
	private Perlin topNoise;
	private Perlin verticalNoise;

	private int maxHeight;
	private int radius;
	private int cuttoffHeight;
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
		shapeNoise = new Perlin(HORIZONTAL_NOISE_ZOOM, seed);
		topNoise = new Perlin(TOP_NOISE_ZOOM, seed);
		verticalNoise = new Perlin(VERTICAL_NOISE_ZOOM, seed);
		maxHeight = 80 + random.nextInt(80);
		yStart = 30;
		radius = 10 + random.nextInt(10);
		cuttoffHeight = maxHeight - random.nextInt(maxHeight/2);

		int radiusBound = MathHelper.ceil(radius * 1.5);
		this.boundingBox = new MutableIntBoundingBox(centerX - radiusBound, 30, centerZ - radiusBound, centerX + radiusBound, 30 + maxHeight, centerZ + radiusBound);
	}

	public CanyonCliffGenerator(StructureManager manager, CompoundTag tag) {
		super(TerrestriaFeatures.CANYON_CLIFF_PIECE, tag);
		seed = tag.getInt("S");
		shapeType = tag.getInt("CS");
		shapeNoise = new Perlin(HORIZONTAL_NOISE_ZOOM, tag.getInt("SN"));
		topNoise = new Perlin(TOP_NOISE_ZOOM, tag.getInt("TN"));
		verticalNoise = new Perlin(VERTICAL_NOISE_ZOOM, tag.getInt("VN"));
		maxHeight = tag.getInt("MH");
		radius = tag.getInt("R");
		cuttoffHeight = tag.getInt("CO");
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
		tag.putInt("MH", maxHeight);
		tag.putInt("R", radius);
		tag.putInt("CO", cuttoffHeight);
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
		BlockState state;
		int dX;
		int dZ;
		double dist;

		for (int z = boundingBox.minZ; z <= boundingBox.maxZ; z++) {
			for (int x = boundingBox.minX; x <= boundingBox.maxX; x++) {

				dX = x - centerX;
				dZ = z - centerZ;
				dist = Math.sqrt(dZ * dZ + dX * dX);

				//Start placing vertical blocks
				for (int h = 0; h < maxHeight; h++) {
					//If the x and z coordinates fit within the feature's shape bounds
					if (!canGenerate(x, z, radius(h), dist)) {
						continue;
					}
					//If it is within it's vertical bounds
					if (h + 30 < cuttoffHeight + (topNoise.getNoiseLevelAtPosition(x, z) * 3)) {
						if (h % 6 >= 3) {
							state = Blocks.SMOOTH_SANDSTONE.getDefaultState();
						} else {
							state = Blocks.TERRACOTTA.getDefaultState();
						}
						pos.set(x, yStart + h, z);
						world.setBlockState(pos, state, 2);
					}
				}
			}
		}
		return true;
	}

	private double radius(int h) {
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
		return (radius/2.0) * verticalNoise.getNoiseLevelAtPosition(h,1) + (radius / 2.0);
	}

	private double radiusUniform(int h) {
		return radius - verticalNoise.getNoiseLevelAtPosition(h,1) * 3;
	}

	//Bloob shape (idk what to call it lol)
	private double radiusBloob(int h) {
		return radius * (2 * (h*h)) - (h*h*h) - h + 1;
	}

	//Decides the 2d shape bounds of the feature
	private boolean canGenerate(int x, int z, double radius, double distance) {
		double noiseVal = shapeNoise.getNoiseLevelAtPosition(x, z);
		if ((noiseVal * (radius / distance)) > 0.5) {
			return true;
		}
		return false;
	}
}
