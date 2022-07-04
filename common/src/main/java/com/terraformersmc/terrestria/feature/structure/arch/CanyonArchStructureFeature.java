package com.terraformersmc.terrestria.feature.structure.arch;

import com.mojang.serialization.Codec;
import net.minecraft.structure.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class CanyonArchStructureFeature extends StructureFeature<DefaultFeatureConfig> {

	public CanyonArchStructureFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec, StructureGeneratorFactory.simple(CanyonArchStructureFeature::canGenerate, CanyonArchStructureFeature::addPieces));
	}

	// TODO: Should this be more restrictive?
	private static <C extends FeatureConfig> boolean canGenerate(StructureGeneratorFactory.Context<C> context) {
		return context.isBiomeValid(Heightmap.Type.WORLD_SURFACE_WG);
	}

	private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
		collector.addPiece(new CanyonArchGenerator(context.random(), context.chunkPos().getStartX(), context.chunkPos().getStartZ()));
	}

	@Override
	public GenerationStep.Feature getGenerationStep() {
		return GenerationStep.Feature.SURFACE_STRUCTURES;
	}

	public int getRadius() {
		return 12;
	}
}
