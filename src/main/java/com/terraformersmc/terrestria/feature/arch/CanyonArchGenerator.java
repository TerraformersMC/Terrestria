package com.terraformersmc.terrestria.feature.arch;

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

	private static final int TOP_NOISE_ZOOM = 15;
	private static final int MATERIAL_NOISE_ZOOM = 7;

	private Perlin topNoise;
	private Perlin materialNoise;

	private int lineSlope;
	private int maxHeight;
	private int radius;
	private int yStart;

	private int centerX;
	private int centerZ;

	CanyonArchGenerator(Random random, int centerX, int centerZ, Biome biome) {
		super(TerrestriaFeatures.CANYON_ARCH_PIECE, 0);
		this.setOrientation(null);
		this.centerX = centerX;
		this.centerZ = centerZ;

		int seed = random.nextInt(10000);

		topNoise = new Perlin(TOP_NOISE_ZOOM, seed);
		materialNoise = new Perlin(MATERIAL_NOISE_ZOOM, seed);

		lineSlope = seed % 10;

		// 50% inversion chance
		if(seed > 5000) {
			lineSlope = -lineSlope;
		}

		maxHeight = 55 + random.nextInt(50);
		yStart = 30;
		radius = 15 + random.nextInt(40);

		int radiusBound = MathHelper.ceil(radius * 1.5);
		this.boundingBox = new MutableIntBoundingBox(centerX - radiusBound, 30, centerZ - radiusBound, centerX + radiusBound, 30 + maxHeight, centerZ + radiusBound);
	}

	public CanyonArchGenerator(StructureManager manager, CompoundTag tag) {
		super(TerrestriaFeatures.CANYON_ARCH_PIECE, tag);

		topNoise = new Perlin(TOP_NOISE_ZOOM, tag.getInt("TopNoise"));
		materialNoise = new Perlin(MATERIAL_NOISE_ZOOM, tag.getInt("MaterialNoise"));

		lineSlope = tag.getInt("LineSlope");
		maxHeight = tag.getInt("MaxHeight");
		radius = tag.getInt("Radius");
		yStart = tag.getInt("YStart");

		centerX = tag.getInt("CenterX");
		centerZ = tag.getInt("CenterZ");
	}

	@Override
	protected void toNbt(CompoundTag tag) {
		tag.putInt("TopNoise", topNoise.getSeed());
		tag.putInt("MaterialNoise", materialNoise.getSeed());

		tag.putInt("LineSlope", lineSlope);
		tag.putInt("MaxHeight", maxHeight);
		tag.putInt("Radius", radius);
		tag.putInt("YStart", yStart);

		tag.putInt("CenterX", centerX);
		tag.putInt("CenterZ", centerZ);
	}

	@Override
	public boolean generate(IWorld world, Random random, MutableIntBoundingBox boundingBox, ChunkPos chunkPos) {
		if (boundingBox.maxY < this.boundingBox.maxY || boundingBox.minY > this.boundingBox.minY) {
			throw new IllegalArgumentException("Unexpected bounding box Y range in " + boundingBox + ", the Y range is smaller than the one we expected");
		}

		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int z = boundingBox.minZ; z <= boundingBox.maxZ; z++) {
			for (int x = boundingBox.minX; x <= boundingBox.maxX; x++) {
				double height = maxHeight - (topNoise.getNoiseLevelAtPosition(x, z) * 8);

				for (int h = 0; h < height; h++) {
					if (shapeArch(h, x, z)) {
						pos.set(x, yStart + h, z);
						world.setBlockState(pos, getStateAtY(h, x, z), 2);
					}
				}
			}
		}
		return true;
	}

	// Circle distance and line distance to create an arch shape

	private boolean shapeArch(double h, int x, int z) {
		// Test the distance of the point from the center first

		double offsetX = x - centerX;
		double offsetY = h - 30.0;
		double offsetZ = z - centerZ;

		// Simple distance formula, testing against a larger and smaller circle.

		double vertexDistanceSquared = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;
		double minDistanceSq = (radius - 5) * (radius - 5);
		double maxDistanceSq = radius * radius;

		if(vertexDistanceSquared <= minDistanceSq || vertexDistanceSquared >= maxDistanceSq) {
			return false;
		}

		// Test the distance of the point from the line
		// Finds the perpendicular distance from the current 2d coordinate from a 2d line with a random slope

		// Formula: |ax + by + c| / sqrt(a^2 + b^2)
		// a = lineSlope, b = 1, c = 0

		// Top part, squared
		double numeratorSq = lineSlope * offsetX + offsetZ;
		numeratorSq *= numeratorSq;

		// Bottom part, squared (avoids sqrt call)
		double denominatorSq = lineSlope * lineSlope + 1;

		// Divide the two together, resulting in the squared distance
		double lineDistanceSquared = numeratorSq / denominatorSq;

		// Calculate the max distance squared, this decreases as the height increases
		// Achieves the narrowing effect at the top.

		double maxLineDistanceSquared = 2 + ((maxHeight - h) / 6);
		maxLineDistanceSquared *= maxLineDistanceSquared;

		return lineDistanceSquared < maxLineDistanceSquared;
	}

	// Generates the stone layers

	private BlockState getStateAtY(int height, int x, int z) {
		if (materialNoise.getNoiseLevelAtPosition(x, z) * 3 > height % 6) {
			return Blocks.SMOOTH_SANDSTONE.getDefaultState();
		} else {
			if (materialNoise.getNoiseLevelAtPosition(x, z) * 4 > height % 3) {
				return Blocks.TERRACOTTA.getDefaultState();
			} else {
				return Blocks.SMOOTH_SANDSTONE.getDefaultState();
			}
		}
	}
}
