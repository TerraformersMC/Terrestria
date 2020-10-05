package com.terraformersmc.terrestria.feature.structure.arch;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class CanyonArchStructureFeature extends StructureFeature<DefaultFeatureConfig> {

	public CanyonArchStructureFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
		return CanyonArchStructureStart::new;
	}

	public int getRadius() {
		return 12;
	}

	public static class CanyonArchStructureStart extends StructureStart<DefaultFeatureConfig> {
		CanyonArchStructureStart(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long baseSeed) {
			super(feature, chunkX, chunkZ, box, references, baseSeed);
		}

		@Override
		public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig config) {
			CanyonArchGenerator canyonArch = new CanyonArchGenerator(this.random, chunkX * 16, chunkZ * 16);

			this.children.add(canyonArch);
			this.setBoundingBoxFromChildren();
		}
	}
}
