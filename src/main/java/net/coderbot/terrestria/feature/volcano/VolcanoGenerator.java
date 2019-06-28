package net.coderbot.terrestria.feature.volcano;

import net.coderbot.terrestria.init.TerrestriaBiomes;
import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.*;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.Random;

public class VolcanoGenerator extends StructurePiece {
	private SimpleRadiusNoise radiusNoise;
	private SimpleRadiusNoise vegetationNoise;
	private int height;
	private int radius;
	private int lavaHeight;
	private int lavaTubeLength;
	private int baseY;
	private boolean underwater;

	private int centerX;
	private int centerZ;

	public VolcanoGenerator(Random random, int centerX, int centerZ, Biome biome) {
		super(TerrestriaBiomes.VOLCANO_PIECE, 0);
		this.setOrientation(null);

		this.centerX = centerX;
		this.centerZ = centerZ;

		radiusNoise = new SimpleRadiusNoise(16, random.nextLong(), 0.75, 0.5);
		vegetationNoise = new SimpleRadiusNoise(16, random.nextLong(), 0.25, 0.5);


		if(biome == Biomes.DEEP_OCEAN || biome == Biomes.DEEP_COLD_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN || biome == Biomes.DEEP_FROZEN_OCEAN || biome == Biomes.DEEP_WARM_OCEAN) {

			if(random.nextInt(5) == 0) {
				height = 50 + random.nextInt(20);
			} else {
				height = 20 + random.nextInt(20);
			}

			baseY = 30;
		} else if(biome.getCategory() == Biome.Category.OCEAN) {
			height = 32 + random.nextInt(32);
			baseY = 45;
		} else {
			height = 32 + random.nextInt(64);
			baseY = 60;
		}

		if(height < 48) {
			radius = random.nextInt(height / 2) + height * 2;
		} else if(underwater) {
			radius = random.nextInt(height / 2) + height / 2;
		} else {
			radius = random.nextInt(height) + height / 2;
		}

		lavaHeight = (int)(height * shape(0.2));
		lavaTubeLength = 22;

		underwater = baseY + lavaHeight < 64;

		int radiusBound = MathHelper.ceil(radius * 1.5);

		this.boundingBox = new MutableIntBoundingBox(centerX - radiusBound, 1, centerZ - radiusBound, centerX + radiusBound, 62 + height, centerZ + radiusBound);
	}

	public VolcanoGenerator(StructureManager manager, CompoundTag tag) {
		super(TerrestriaBiomes.VOLCANO_PIECE, tag);

		radiusNoise = new SimpleRadiusNoise(16, tag.getLong("VR"), 0.75, 0.5);
		vegetationNoise = new SimpleRadiusNoise(16, tag.getLong("VV"), 0.25, 0.5);

		height = tag.getInt("VH");
		radius = tag.getInt("VR");
		lavaHeight = tag.getInt("VL");
		lavaTubeLength = tag.getInt("VLT");
		baseY = tag.getInt("Y");
		underwater = tag.getBoolean("VU");

		centerX = tag.getInt("CX");
		centerZ = tag.getInt("CZ");
	}

	@Override
	protected void toNbt(CompoundTag tag) {
		tag.putLong("VR", radiusNoise.getSeed());
		tag.putLong("VV", vegetationNoise.getSeed());
		tag.putInt("VH", height);
		tag.putInt("VR", radius);
		tag.putInt("VL", lavaHeight);
		tag.putInt("VLT", lavaTubeLength);
		tag.putInt("Y", baseY);
		tag.putBoolean("VU", underwater);

		tag.putInt("CX", centerX);
		tag.putInt("CZ", centerZ);
	}

	// TODO: Flowing spouts

	@Override
	public boolean generate(IWorld world, Random random, MutableIntBoundingBox boundingBox, ChunkPos chunkPos) {
		if(boundingBox.maxY < this.boundingBox.maxY || boundingBox.minY > this.boundingBox.minY) {
			throw new IllegalArgumentException("Unexpected bounding box Y range in "+boundingBox+", the Y range is smaller than the one we expected");
		}

		BlockPos.Mutable pos = new BlockPos.Mutable();

		for(int z = boundingBox.minZ; z <= boundingBox.maxZ; z++) {
			for(int x = boundingBox.minX; x <= boundingBox.maxX; x++) {
				int dX = x - centerX;
				int dZ = z - centerZ;

				if(dZ == 0 && (dX >= -1 && dX <= 1) || dX ==0 && (dZ >= -1 && dZ <= 1)) {
					for(int dY = -lavaTubeLength; dY < lavaHeight; dY++) {
						pos.set(x, baseY + dY, z);

						if(underwater && dY == lavaHeight - 1) {
							BlockState state = random.nextInt(4) == 0 ? Blocks.MAGMA_BLOCK.getDefaultState() : Blocks.OBSIDIAN.getDefaultState();

							world.setBlockState(pos, state, 2);
						} else {
							world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 2);
							world.getFluidTickScheduler().schedule(pos, world.getFluidState(pos).getFluid(), 0);
						}
					}

					continue;
				}

				double dist = Math.sqrt(dZ*dZ + dX*dX);
				double angle = positionToAngle(dist, dX, dZ);

				double noise = radiusNoise.sample(angle);
				double vegetation = vegetationNoise.sample(angle) + random.nextDouble() * 0.15;

				double scaled = (dist / radius) * noise;
				int columnHeight = (int)(shape(scaled) * height);
				BlockState top = TerrestriaBlocks.BASALT.getDefaultState();

				if(columnHeight <= 0) {
					continue;
				}

				if(scaled > 0.2 && scaled < 0.35) {
					columnHeight += random.nextInt(2);
				} else if(scaled >= 0.35 && scaled <= 0.8 && random.nextInt(4) == 0) {
					columnHeight += 1;
				}

				if(scaled > 0.3) {
					double scaledHeight = (double)(columnHeight) / (double)(lavaHeight);
					if(scaledHeight < vegetation) {
						if(columnHeight < 4) {
							top = TerrestriaBlocks.BASALT_SAND.getDefaultState();
						} else {
							top = TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState();
						}
					}
				}

				for(int dY = -16; dY < columnHeight - 1; dY++) {
					pos.set(x, baseY + dY, z);

					if(dY > -4 || world.getBlockState(pos).isAir()) {
						world.setBlockState(pos, TerrestriaBlocks.BASALT.getDefaultState(), 2);
					}
				}

				if(columnHeight > 0) {
					pos.set(x, baseY + columnHeight, z);

					if(!world.getBlockState(pos).isAir()) {
						top = TerrestriaBlocks.BASALT.getDefaultState();
					}

					pos.setOffset(Direction.DOWN);
					world.setBlockState(pos, top, 2);
				}

				if(scaled <= 0.3) {
					for(int dY = columnHeight; dY < lavaHeight; dY++) {
						pos.set(x, baseY + dY, z);

						if(underwater && dY == lavaHeight - 1) {
							BlockState state = random.nextInt(4) == 0 ? Blocks.MAGMA_BLOCK.getDefaultState() : Blocks.OBSIDIAN.getDefaultState();

							world.setBlockState(pos, state, 2);
						} else {
							world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 2);
						}
					}
				}
			}
		}

		return true;
	}

	private static double positionToAngle(double dist, double dX, double dZ) {
		// 0.0 to 0.5
		double angle = 0.5 * Math.asin(dX / dist) / Math.PI + 0.25;

		if(dZ < 0) {
			angle = 1.0 - angle;
		}

		return angle;
	}

	// Models the caldera shape of the volcano.
	private static double shape(double scaled) {
		scaled = MathHelper.clamp(scaled, 0.0, 1.0);
		double curve = curve(1.0 - scaled);

		if(scaled <= 0.3) {
			curve -= (0.3 - scaled) * 2;
		}

		return curve;
	}

	// Models an S-curve.
	private static double curve(double progress) {
		progress = MathHelper.clamp(progress, 0.0, 1.0);

		if(progress <= 0.5) {
			return 2 * progress * progress;
		} else {
			double progressUntil = 1.0 - progress;

			return 1 - 2 * progressUntil * progressUntil;
		}
	}
}
