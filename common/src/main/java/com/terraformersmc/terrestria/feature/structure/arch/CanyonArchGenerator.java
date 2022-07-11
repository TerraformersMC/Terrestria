package com.terraformersmc.terrestria.feature.structure.arch;

import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class CanyonArchGenerator extends StructurePiece {

	private final OpenSimplexNoise noise;

	private final float a;
	private final float b;
	private final int maxHeight;
	private final int radius;
	private final int yStart;

	private final int centerX;
	private final int centerZ;

	CanyonArchGenerator(Random random, int centerX, int centerZ) {
		super(TerrestriaStructures.CANYON_ARCH_PIECE, 0, null);
		this.setOrientation(null);

		this.centerX = centerX;
		this.centerZ = centerZ;

		int seed = random.nextInt(10000);

		noise = new OpenSimplexNoise(seed);

		a = random.nextFloat() * 2 - 1;
		b = random.nextFloat() * 2 - 1;

		maxHeight = 55 + random.nextInt(50);
		yStart = 30;
		radius = 15 + random.nextInt(40);

		// Just to be sure.
		int radiusBound = radius + 5;

		this.boundingBox = new BlockBox(centerX - radiusBound, yStart, centerZ - radiusBound, centerX + radiusBound, yStart + maxHeight, centerZ + radiusBound);
	}

	public CanyonArchGenerator(StructureContext context, NbtCompound tag) {
		super(TerrestriaStructures.CANYON_ARCH_PIECE, tag);

		noise = new OpenSimplexNoise(tag.getLong("NoiseSeed"));

		a = tag.getFloat("a");
		b = tag.getFloat("b");
		maxHeight = tag.getInt("MaxHeight");
		radius = tag.getInt("Radius");
		yStart = tag.getInt("YStart");

		centerX = tag.getInt("CenterX");
		centerZ = tag.getInt("CenterZ");
	}

	@Override
	protected void writeNbt(StructureContext context, NbtCompound tag) {
		tag.putLong("NoiseSeed", noise.getSeed());

		tag.putFloat("a", a);
		tag.putFloat("b", b);
		tag.putInt("MaxHeight", maxHeight);
		tag.putInt("Radius", radius);
		tag.putInt("YStart", yStart);

		tag.putInt("CenterX", centerX);
		tag.putInt("CenterZ", centerZ);
	}

	@Override
	public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
		if (box.getMinY() > this.boundingBox.getMinY() || box.getMaxY() < this.boundingBox.getMaxY()) {
			throw new IllegalArgumentException("Unexpected bounding box Y range in " + box + ", the Y range is smaller than the one we expected");
		}

		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int z = box.getMinZ(); z <= box.getMaxZ(); z++) {
			for (int x = box.getMinX(); x <= box.getMaxX(); x++) {

				double noiseValue = noise.sample(x * 0.05, z * 0.05);
				double height = maxHeight - Math.abs(noiseValue) * 8;

				for (int h = 0; h < height; h++) {
					if (shapeArch(h, x, z)) {
						pos.set(x, yStart + h, z);
						world.setBlockState(pos, getStateAtY(h, x, z), 2);
					}
				}
			}
		}
	}

	// Circle distance and line distance to create an arch shape

	private boolean shapeArch(double h, int x, int z) {
		// Test the distance of the point from the center first

		double offsetX = x - centerX;
		double offsetY = h - yStart;
		double offsetZ = z - centerZ;

		// Simple distance formula, testing against a larger and smaller circle.

		double vertexDistanceSquared = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;
		double minDistanceSq = (radius - 5) * (radius - 5);
		double maxDistanceSq = radius * radius;

		if (vertexDistanceSquared <= minDistanceSq || vertexDistanceSquared >= maxDistanceSq) {
			return false;
		}

		// Test the distance of the point from the line
		// Finds the perpendicular distance from the current 2d coordinate from a 2d line with a random slope

		// Formula: |ax + by + c| / sqrt(a^2 + b^2)
		// a = a, b = b, c = 0

		// Top expression, squared
		double numeratorSq = (a * offsetX) + (b * offsetZ);
		//Square numerator so we don't have to call sqrt in denominator
		numeratorSq *= numeratorSq;

		// Bottom expression, squared
		double denominatorSq = (a * a) + (b * b);

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
		double noiseValue = Math.abs(noise.sample(x * 0.05, z * 0.05));

		if (noiseValue * 3 > height % 6) {
			return Blocks.SMOOTH_SANDSTONE.getDefaultState();
		} else if (noiseValue * 4 > height % 3) {
			return Blocks.TERRACOTTA.getDefaultState();
		} else {
			return Blocks.SMOOTH_SANDSTONE.getDefaultState();
		}
	}
}
