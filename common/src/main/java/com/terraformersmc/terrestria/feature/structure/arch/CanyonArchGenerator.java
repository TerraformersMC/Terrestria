package com.terraformersmc.terrestria.feature.structure.arch;

import com.terraformersmc.terraform.noise.api.OpenSimplexNoise;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CanyonArchGenerator extends StructurePiece {
	private final OpenSimplexNoise noise;

	private final float a;
	private final float b;
	private final int maxHeight;
	private final int radius;
	private final int yStart;

	private final int centerX;
	private final int centerZ;

	CanyonArchGenerator(RandomSource random, int centerX, int centerZ) {
		//noinspection ConstantConditions
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

		this.boundingBox = new BoundingBox(this.centerX - radiusBound, yStart, this.centerZ - radiusBound, this.centerX + radiusBound, yStart + maxHeight, this.centerZ + radiusBound);
	}

	public CanyonArchGenerator(StructurePieceSerializationContext context, CompoundTag tag) {
		super(TerrestriaStructures.CANYON_ARCH_PIECE, tag);

		noise = new OpenSimplexNoise(tag.getLongOr("NoiseSeed", 0));

		a = tag.getFloatOr("a", 0);
		b = tag.getFloatOr("b", 0);
		maxHeight = tag.getIntOr("MaxHeight", 0);
		radius = tag.getIntOr("Radius", 0);
		yStart = tag.getIntOr("YStart", 0);

		centerX = tag.getIntOr("CenterX", 0);
		centerZ = tag.getIntOr("CenterZ", 0);
	}

	@Override
	protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
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
	public void postProcess(WorldGenLevel world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos blockPos) {
		if (box.minY() > this.boundingBox.minY() || box.maxY() < this.boundingBox.maxY()) {
			throw new IllegalArgumentException("Unexpected bounding box Y range in " + box + ", the Y range is smaller than the one we expected");
		}

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		for (int z = box.minZ(); z <= box.maxZ(); z++) {
			for (int x = box.minX(); x <= box.maxX(); x++) {

				double noiseValue = noise.sample(x * 0.05, z * 0.05);
				double height = maxHeight - Math.abs(noiseValue) * 8;

				for (int h = 0; h < height; h++) {
					if (shapeArch(h, x, z)) {
						pos.set(x, yStart + h, z);
						world.setBlock(pos, getStateAtY(h, x, z), 2);
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
			return Blocks.SMOOTH_SANDSTONE.defaultBlockState();
		} else if (noiseValue * 4 > height % 3) {
			return Blocks.TERRACOTTA.defaultBlockState();
		} else {
			return Blocks.SMOOTH_SANDSTONE.defaultBlockState();
		}
	}
}
