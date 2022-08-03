package com.terraformersmc.terrestria.feature.structure.volcano;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terraform.noise.OpenSimplexNoise;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.tick.OrderedTick;

import java.util.Random;

public class VolcanoGenerator extends StructurePiece {
	private final SimpleRadialNoise radiusNoise;
	private final SimpleRadialNoise vegetationNoise;
	private final SimpleRadialNoise chamberNoise;
	private final OpenSimplexNoise chamberOreNoise;

	private final int height;
	private final int radius;
	private final int lavaHeight;
	private final int lavaTubeLength;
	private final int baseY;
	private final int chamberHeight;
	private final boolean underwater;
	private final long chamberOreSeed;

	private final int centerX;
	private final int centerZ;

	VolcanoGenerator(Random random, int centerX, int centerZ, VolcanoFeatureConfig config) {
		super(TerrestriaStructures.VOLCANO_PIECE, 0, null);
		this.setOrientation(null);

		this.centerX = centerX;
		this.centerZ = centerZ;

		radiusNoise = new SimpleRadialNoise(16, random.nextLong(), 0.75, 0.5);
		vegetationNoise = new SimpleRadialNoise(16, random.nextLong(), 0.25, 0.5);
		chamberNoise = new SimpleRadialNoise(16, random.nextLong(), 0.75, 0.5);
		chamberOreSeed = random.nextLong();
		chamberOreNoise = new OpenSimplexNoise(chamberOreSeed);

		this.height = config.height().get(random);
		this.baseY = config.baseY();

		if (height < 48) {
			radius = random.nextInt(height / 2) + height * 2;
		} else if (config.thinIfTall()) {
			radius = random.nextInt(height / 3) + height / 4;
		} else {
			radius = random.nextInt(height * 3 / 4) + height / 2;
		}

		lavaHeight = (int) (height * shape(0.2));
		lavaTubeLength = Math.min(22, baseY - 20);

		// Make sure that the chamber doesn't go too deep
		int potentialChamberHeight = random.nextInt(Math.max(baseY - lavaTubeLength - 10, 0) + 1);
		chamberHeight = Math.max(potentialChamberHeight, 10);

		underwater = baseY + lavaHeight < 64;

		int radiusBound = MathHelper.ceil(radius * 1.5);

		this.boundingBox = new BlockBox(centerX - radiusBound, 1, centerZ - radiusBound, centerX + radiusBound, 62 + height, centerZ + radiusBound);
	}

	public VolcanoGenerator(StructureContext context, NbtCompound tag) {
		super(TerrestriaStructures.VOLCANO_PIECE, tag);

		radiusNoise = new SimpleRadialNoise(16, tag.getLong("VRN"), 0.75, 0.5);
		vegetationNoise = new SimpleRadialNoise(16, tag.getLong("VVN"), 0.25, 0.5);
		chamberNoise = new SimpleRadialNoise(16, tag.getLong("VCN"), 0.75, 0.5);
		chamberOreSeed = tag.getLong("VCON");
		chamberOreNoise = new OpenSimplexNoise(chamberOreSeed);

		height = tag.getInt("VH");
		radius = tag.getInt("VR");
		lavaHeight = tag.getInt("VL");
		lavaTubeLength = tag.getInt("VLT");
		baseY = tag.getInt("Y");
		chamberHeight = tag.getInt("VCH");
		underwater = tag.getBoolean("VU");

		centerX = tag.getInt("CX");
		centerZ = tag.getInt("CZ");
	}

	private static double positionToAngle(double dist, double dX, double dZ) {
		// 0.0 to 0.5
		double angle = 0.5 * Math.asin(dX / dist) / Math.PI + 0.25;

		if (dZ < 0) {
			angle = 1.0 - angle;
		}

		return angle;
	}

	// Models the caldera shape of the volcano.
	private static double shape(double scaled) {
		// must be at least 0
		scaled = Math.max(scaled, 0.0);

		double curve = curve(1.0 - scaled);

		if (scaled <= 0.3) {
			curve -= (0.3 - scaled) * 2;
		}

		return curve;
	}

	// Models an S-curve.
	private static double curve(double progress) {
		// can't be greater than 1
		progress = Math.min(progress, 1.0);

		if (progress < 0.1) {
			return 2 * (progress - 0.1);
		} else if (progress <= 0.5) {
			return 2 * progress * progress;
		} else {
			double progressUntil = 1.0 - progress;

			return 1 - 2 * progressUntil * progressUntil;
		}
	}

	@Override
	protected void writeNbt(StructureContext context, NbtCompound tag) {
		tag.putLong("VRN", radiusNoise.getSeed());
		tag.putLong("VVN", vegetationNoise.getSeed());
		tag.putLong("VCN", chamberNoise.getSeed());
		tag.putLong("VCON", chamberOreSeed);
		tag.putInt("VH", height);
		tag.putInt("VR", radius);
		tag.putInt("VL", lavaHeight);
		tag.putInt("VLT", lavaTubeLength);
		tag.putInt("Y", baseY);
		tag.putInt("VCH", chamberHeight);
		tag.putBoolean("VU", underwater);

		tag.putInt("CX", centerX);
		tag.putInt("CZ", centerZ);
	}

	@Override
	public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, BlockPos blockPos) {
		if (box.getMinY() > this.boundingBox.getMinY() || box.getMaxY() < this.boundingBox.getMaxY()) {
			throw new IllegalArgumentException("Unexpected bounding box Y range in " + box + ", the Y range is smaller than the one we expected");
		}

		int chamberMiddle = baseY - lavaTubeLength - chamberHeight / 2;

		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int z = box.getMinZ(); z <= box.getMaxZ(); z++) {
			for (int x = box.getMinX(); x <= box.getMaxX(); x++) {
				int dX = x - centerX;
				int dZ = z - centerZ;

				double dist = Math.sqrt(dZ * dZ + dX * dX);
				double angle = positionToAngle(dist, dX, dZ);

				// Fill in the lava chamber part!
				double chamberNoise = radiusNoise.sample(angle);
				double chamberScaled = (dist / radius) * chamberNoise;

				// chamberScaled = chamberScaled ^ 4
				chamberScaled *= chamberScaled;
				chamberScaled *= chamberScaled;

				double chamberShape = 1.0 - 16.0 * chamberScaled;
				int chamberDY = (int) (chamberHeight * 0.5 * chamberShape);

				if (chamberShape > 0.0) {
					for (int dY = -chamberDY; dY <= chamberDY; dY++) {
						pos.set(x, chamberMiddle + dY, z);
						world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 2);
						world.getFluidTickScheduler().scheduleTick(OrderedTick.create(world.getFluidState(pos).getFluid(), pos));
					}
				} else if (chamberShape > -0.1) {
					pos.set(x, chamberMiddle, z);
					world.setBlockState(pos, pickRandomChamberBlock(true, dX, dZ), 2);
				}

				// The center of the volcano is a lava tube, arranged in a plus sign shape.

				if (dZ == 0 && (dX >= -1 && dX <= 1) || dX == 0 && (dZ >= -1 && dZ <= 1)) {
					for (int dY = -(lavaTubeLength + chamberHeight); dY < lavaHeight; dY++) {
						pos.set(x, baseY + dY, z);

						if (underwater && dY == lavaHeight - 1) {
							BlockState state = random.nextInt(4) == 0 ? Blocks.MAGMA_BLOCK.getDefaultState() : Blocks.OBSIDIAN.getDefaultState();

							world.setBlockState(pos, state, 2);
						} else {
							world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 2);
							world.getFluidTickScheduler().scheduleTick(OrderedTick.create(world.getFluidState(pos).getFluid(), pos));;
						}
					}

					continue;
				} else if (chamberShape > 0.0) {
					world.setBlockState(pos.set(x, chamberMiddle + chamberDY + 1, z), pickRandomChamberBlock(true, dX, dZ), 2);
					world.setBlockState(pos.set(x, chamberMiddle - chamberDY - 1, z), pickRandomChamberBlock(false, dX, dZ), 2);
				}

				// Otherwise, proceed with normal generation. Sample the necessary values.

				double noise = radiusNoise.sample(angle);
				double vegetation = vegetationNoise.sample(angle) + random.nextDouble() * 0.15;

				double scaled = (dist / radius) * noise;
				int columnHeight = (int) (shape(scaled) * height);
				BlockState top = TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState();

				// Below bedrock, skip.

				if (columnHeight + baseY <= 0) {
					continue;
				}

				// Add column height variance, for some random noise in the volcano shape.

				if (scaled > 0.2 && scaled < 0.35) {
					columnHeight += random.nextInt(2);
				} else if (scaled >= 0.35 && scaled <= 0.8 && random.nextInt(4) == 0) {
					columnHeight += 1;
				}

				// Set the vegetation / surface block to be used if the conditions are right.

				if (scaled > 0.3) {
					double scaledHeight = (double) (columnHeight) / (double) (lavaHeight);
					if (scaledHeight < vegetation) {
						if (columnHeight < 4) {
							top = TerrestriaBlocks.BLACK_SAND.getDefaultState();
						} else {
							top = TerrestriaBlocks.ANDISOL.getGrassBlock().getDefaultState();
						}
					}
				}

				// Place the basalt column to the specified height.

				int startY = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, new BlockPos(x, 0, z)).getY() - baseY;

				for (int dY = startY; dY < columnHeight - 1; dY++) {
					pos.set(x, baseY + dY, z);

					if (world.getBlockState(pos).isAir() || world.getFluidState(pos).getFluid() == Fluids.WATER) {
						world.setBlockState(pos, TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState(), 2);
					}
				}

				// Surround the lava tube with basalt

				if ((Math.abs(dX) == 1 && Math.abs(dZ) == 1) || (Math.abs(dX) == 2 && dZ == 0) || (dX == 0 && Math.abs(dZ) == 2)) {
					startY = chamberMiddle + chamberDY + 1;
					int endY = baseY + columnHeight - 1;

					for (int y = startY; y < endY; y++) {
						pos.set(x, y, z);

						world.setBlockState(pos, TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState(), 2);
					}

					// The bowl fill logic can miss placing lava directly above the center columns.
					pos.move(Direction.UP);
					world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 2);
				}

				// Some complex top block logic:
				// If the volcano is within the ocean, revert the top block to basalt. However, underwater volcanoes
				// have some magma on the sides, this is the one override.
				// Otherwise, if around the top areas of the volcano, place a few lava blocks to flow down the mountain.

				pos.set(x, baseY + columnHeight, z);
				boolean lava = false;

				if (baseY < 60 || !world.getBlockState(pos).isAir()) {
					if (underwater && random.nextInt(80) == 0) {
						top = Blocks.MAGMA_BLOCK.getDefaultState();
					} else {
						top = TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState();
					}
				} else if (scaled > 0.25 && scaled < 0.35) {
					if (!underwater && random.nextInt(320) == 0) {
						top = Blocks.LAVA.getDefaultState();
						lava = true;
					}
				}

				// Finally, actually set the top block. If lava, it needs a fluid tick update to get going.

				pos.move(Direction.DOWN);

				if ((world.getBlockState(pos).isAir() || world.getFluidState(pos).getFluid() == Fluids.WATER) && startY < columnHeight) {
					world.setBlockState(pos, top, 2);

					if (lava) {
						world.getFluidTickScheduler().scheduleTick(OrderedTick.create(world.getFluidState(pos).getFluid(), pos));
					}
				}

				// If within the top caldera, set lava / magma / obsidian based on whether this is an underwater volcano or not.
				// Effectively, fill the caldera with lava up to the lavaHeight height.

				if (scaled <= 0.3) {
					for (int dY = columnHeight; dY < lavaHeight; dY++) {
						pos.set(x, baseY + dY, z);

						if (underwater && dY == lavaHeight - 1) {
							BlockState state = random.nextInt(6) == 0 ? Blocks.MAGMA_BLOCK.getDefaultState() : Blocks.OBSIDIAN.getDefaultState();

							world.setBlockState(pos, state, 2);
						} else {
							world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 2);
						}
					}
				}
			}
		}
	}

	private BlockState pickRandomChamberBlock(boolean top, int dX, int dZ) {
		if (!top) {
			dX = -dX;
		}

		double goldNoise = chamberOreNoise.sample(dX * 0.05, dZ * 0.05);
		double diamondNoise = chamberOreNoise.sample(-dX * 0.2, dZ * 0.2);
		double obsidianNoise = chamberOreNoise.sample(dX * 0.05, -dZ * 0.05);

		if (diamondNoise > 0.70) {
			return Blocks.DIAMOND_ORE.getDefaultState();
		} else if (goldNoise < -0.75) {
			return Blocks.GOLD_ORE.getDefaultState();
		} else {
			return obsidianNoise > 0.25 ? Blocks.OBSIDIAN.getDefaultState() : TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState();
		}
	}
}

