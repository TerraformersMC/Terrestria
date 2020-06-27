package com.terraformersmc.terrestria.feature.structure.arch;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class CanyonArchStructureFeature extends StructureFeature<DefaultFeatureConfig> {

	public CanyonArchStructureFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory getStructureStartFactory() {
		return CanyonArchStructureStart::new;
	}

	public int getRadius() {
		return 12;
	}

	public static class CanyonArchStructureStart extends StructureStart {
		CanyonArchStructureStart(StructureFeature<?> feature, int chunkX, int chunkZ, BlockBox box, int references, long baseSeed) {
			super(feature, chunkX, chunkZ, box, references, baseSeed);
		}

		@Override
		public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, FeatureConfig featureConfig) {
			CanyonArchGenerator canyonArch = new CanyonArchGenerator(this.random, x * 16, z * 16, biome);

			this.children.add(canyonArch);
			this.setBoundingBoxFromChildren();
		}
	}
}
