package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VolcanoStructureFeature extends StructureFeature<VolcanoFeatureConfig> {

	public VolcanoStructureFeature(Codec<VolcanoFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory<VolcanoFeatureConfig> getStructureStartFactory() {
		return VolcanoStructureStart::new;
	}

	public static class VolcanoStructureStart extends StructureStart<VolcanoFeatureConfig> {
		VolcanoStructureStart(StructureFeature<VolcanoFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long baseSeed) {
			super(feature, chunkX, chunkZ, box, references, baseSeed);
		}

		@Override
		public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int chunkX, int chunkZ, Biome biome, VolcanoFeatureConfig config) {
			VolcanoGenerator volcano = new VolcanoGenerator(this.random, chunkX * 16, chunkZ * 16, config);

			this.children.add(volcano);
			this.setBoundingBoxFromChildren();
		}
	}
}
