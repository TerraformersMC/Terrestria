package com.terraformersmc.terrestria.feature.canyoncliffs;

import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;
import java.util.function.Function;

public class CanyonCliffStructureFeature extends StructureFeature<DefaultFeatureConfig> {

	private static final int CANYON_CLIFF_SPACING = 2;

	// How many chunks should be in between each canyonCliff at least
	private static final int CANYON_CLIFF_SEPARATION = 1;
	private static final int SEED_MODIFIER = 0x0401C480;

	public CanyonCliffStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator_1, Random random_1, int chunkX, int chunkZ, int scaleX, int scaleZ) {
		chunkX += CANYON_CLIFF_SPACING * scaleX;
		chunkZ += CANYON_CLIFF_SPACING * scaleZ;

		// Adjust to grid position
		chunkX = chunkX < 0 ? chunkX - CANYON_CLIFF_SPACING + 1 : chunkX;
		chunkZ = chunkZ < 0 ? chunkZ - CANYON_CLIFF_SPACING + 1 : chunkZ;

		int finalChunkX = chunkX / CANYON_CLIFF_SPACING;
		int finalChunkZ = chunkZ / CANYON_CLIFF_SPACING;

		((ChunkRandom) random_1).setStructureSeed(chunkGenerator_1.getSeed(), finalChunkX, finalChunkZ, SEED_MODIFIER);

		// Get random position within grid area
		finalChunkX *= CANYON_CLIFF_SPACING;
		finalChunkZ *= CANYON_CLIFF_SPACING;
		finalChunkX += random_1.nextInt(CANYON_CLIFF_SPACING - CANYON_CLIFF_SEPARATION);
		finalChunkZ += random_1.nextInt(CANYON_CLIFF_SPACING - CANYON_CLIFF_SEPARATION);

		return new ChunkPos(finalChunkX, finalChunkZ);
	}

	public boolean shouldStartAt(ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ) {
		ChunkPos start = this.getStart(generator, random, chunkX, chunkZ, 0, 0);

		if (chunkX == start.x && chunkZ == start.z) {
			Biome biome = generator.getBiomeSource().getBiome(new BlockPos(chunkX * 16 + 9, 0, chunkZ * 16 + 9));

			if (biome.getCategory() == Biome.Category.OCEAN) {
				return false;
			}

			return generator.hasStructure(biome, this);
		}

		return false;
	}

	public StructureFeature.StructureStartFactory getStructureStartFactory() {
		return CanyonCliffStructureFeature.CanyonCliffStructureStart::new;
	}

	public String getName() {
		return "canyon_cliff";
	}

	public int getRadius() {
		return 12;
	}

	public static class CanyonCliffStructureStart extends StructureStart {
		CanyonCliffStructureStart(StructureFeature<?> feature, int chunkX, int chunkZ, Biome biome, MutableIntBoundingBox boundingBox, int references, long baseSeed) {
			super(feature, chunkX, chunkZ, biome, boundingBox, references, baseSeed);
		}

		public void initialize(ChunkGenerator<?> generator, StructureManager manager, int chunkX, int chunkZ, Biome biome) {
			CanyonCliffGenerator canyonCliff = new CanyonCliffGenerator(this.random, chunkX * 16, chunkZ * 16, biome);

			this.children.add(canyonCliff);
			this.setBoundingBoxFromChildren();
		}
	}
}
