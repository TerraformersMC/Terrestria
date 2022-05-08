package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructureFeature;

public class VolcanoStructureFeature extends StructureFeature<VolcanoFeatureConfig> {

	public VolcanoStructureFeature(Codec<VolcanoFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory<VolcanoFeatureConfig> getStructureStartFactory() {
		return VolcanoStructureStart::new;
	}

	public static class VolcanoStructureStart extends StructureStart<VolcanoFeatureConfig> {
		VolcanoStructureStart(StructureFeature<VolcanoFeatureConfig> feature, ChunkPos pos, int references, long baseSeed) {
			super(feature, pos, references, baseSeed);
		}

		@Override
		public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, ChunkPos pos, Biome biome, VolcanoFeatureConfig config, HeightLimitView heightLimitView) {
			VolcanoGenerator volcano = new VolcanoGenerator(this.random, pos.getStartX() * 16, pos.getStartZ() * 16, config);

			this.children.add(volcano);
			this.getBoundingBox();
		}
	}
}
