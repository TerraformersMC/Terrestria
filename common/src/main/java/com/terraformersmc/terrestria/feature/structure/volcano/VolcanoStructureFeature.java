package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;

import net.minecraft.structure.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class VolcanoStructureFeature extends StructureFeature<VolcanoFeatureConfig> {

	public VolcanoStructureFeature(Codec<VolcanoFeatureConfig> codec) {
		super(codec, StructureGeneratorFactory.simple(VolcanoStructureFeature::canGenerate, VolcanoStructureFeature::addPieces));
	}

	// TODO: Should this be more restrictive?
	private static <C extends FeatureConfig> boolean canGenerate(StructureGeneratorFactory.Context<C> context) {
		return context.isBiomeValid(Heightmap.Type.WORLD_SURFACE_WG);
	}

	private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<VolcanoFeatureConfig> context) {
		collector.addPiece(new VolcanoGenerator(context.random(), context.chunkPos().getStartX(), context.chunkPos().getStartZ(), context.config()));
	}

	@Override
	public GenerationStep.Feature getGenerationStep() {
		return GenerationStep.Feature.SURFACE_STRUCTURES;
	}
}
