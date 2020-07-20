package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VolcanoStructureFeature extends StructureFeature<DefaultFeatureConfig> {

	public VolcanoStructureFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory getStructureStartFactory() {
		return VolcanoStructureStart::new;
	}

	public static class VolcanoStructureStart extends StructureStart<DefaultFeatureConfig> {
		VolcanoStructureStart(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long baseSeed) {
			super(feature, chunkX, chunkZ, box, references, baseSeed);
		}

		@Override
		public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, DefaultFeatureConfig featureConfig) {
			VolcanoGenerator volcano = new VolcanoGenerator(this.random, x * 16, z * 16, biome);

			this.children.add(volcano);
			this.setBoundingBoxFromChildren();
		}
	}
}
