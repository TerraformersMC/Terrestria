package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.structure.*;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VolcanoStructureFeature extends StructureFeature<VolcanoFeatureConfig> {

	public VolcanoStructureFeature(Codec<VolcanoFeatureConfig> codec) {
		super(codec, StructureGeneratorFactory.simple(VolcanoStructureFeature::canGenerate, VolcanoStructureFeature::addPieces));
	}

	// StructureGeneratorFactory.Context.isBiomeValid() except we specifically check for Volcanic Island.
	// If areOceanVolcanoesEnabled() returns false, volcanoes only generate within Volcanic Islands.
	private static <C extends FeatureConfig> boolean canGenerate(StructureGeneratorFactory.Context<C> context) {
		int x = context.chunkPos().getCenterX();
		int z = context.chunkPos().getCenterZ();
		int y = context.chunkGenerator().getHeightInGround(x, z, Heightmap.Type.OCEAN_FLOOR_WG, context.world());
		RegistryEntry<Biome> biome = context.chunkGenerator().getBiomeForNoiseGen(BiomeCoords.fromBlock(x),
				BiomeCoords.fromBlock(y), BiomeCoords.fromBlock(z));

		if (!biome.matchesKey(TerrestriaBiomes.VOLCANIC_ISLAND) &&
				!Terrestria.getConfigManager().getGeneralConfig().areOceanVolcanoesEnabled()) {
			return false;
		}

		return context.validBiome().test(biome);
	}

	private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<VolcanoFeatureConfig> context) {
		collector.addPiece(new VolcanoGenerator(context.random(), context.chunkPos().getStartX(), context.chunkPos().getStartZ(), context.config()));
	}

	@Override
	public GenerationStep.Feature getGenerationStep() {
		return GenerationStep.Feature.SURFACE_STRUCTURES;
	}
}
