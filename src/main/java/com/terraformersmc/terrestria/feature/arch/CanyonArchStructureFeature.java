package com.terraformersmc.terrestria.feature.arch;

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

public class CanyonArchStructureFeature extends StructureFeature<DefaultFeatureConfig> {

	private static final int ARCH_SPACING = 5;

	// How many chunks should be in between each canyon arch at least
	private static final int ARCH_SEPARATION = 3;
	private static final int SEED_MODIFIER = 0x0401C480;

	public CanyonArchStructureFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function) {
		super(function);
	}

	protected ChunkPos getStart(ChunkGenerator<?> chunkGenerator_1, Random random_1, int chunkX, int chunkZ, int scaleX, int scaleZ) {
		chunkX += ARCH_SPACING * scaleX;
		chunkZ += ARCH_SPACING * scaleZ;

		// Adjust to grid position
		chunkX = chunkX < 0 ? chunkX - ARCH_SPACING + 1 : chunkX;
		chunkZ = chunkZ < 0 ? chunkZ - ARCH_SPACING + 1 : chunkZ;

		int finalChunkX = chunkX / ARCH_SPACING;
		int finalChunkZ = chunkZ / ARCH_SPACING;

		((ChunkRandom) random_1).setStructureSeed(chunkGenerator_1.getSeed(), finalChunkX, finalChunkZ, SEED_MODIFIER);

		// Get random position within grid area
		finalChunkX *= ARCH_SPACING;
		finalChunkZ *= ARCH_SPACING;
		finalChunkX += random_1.nextInt(ARCH_SPACING - ARCH_SEPARATION);
		finalChunkZ += random_1.nextInt(ARCH_SPACING - ARCH_SEPARATION);

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
		return CanyonArchStructureFeature.CanyonArchStructureStart::new;
	}

	public String getName() {
		return "canyon_arch";
	}

	public int getRadius() {
		return 12;
	}

	public static class CanyonArchStructureStart extends StructureStart {
		CanyonArchStructureStart(StructureFeature<?> feature, int chunkX, int chunkZ, Biome biome, MutableIntBoundingBox boundingBox, int references, long baseSeed) {
			super(feature, chunkX, chunkZ, biome, boundingBox, references, baseSeed);
		}

		public void initialize(ChunkGenerator<?> generator, StructureManager manager, int chunkX, int chunkZ, Biome biome) {
			CanyonArchGenerator canyonArch = new CanyonArchGenerator(this.random, chunkX * 16, chunkZ * 16, biome);

			this.children.add(canyonArch);
			this.setBoundingBoxFromChildren();
		}
	}
}
