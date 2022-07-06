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

	private static <C extends FeatureConfig> boolean canGenerate(StructureGeneratorFactory.Context<C> context) {
		if (!context.isBiomeValid(Heightmap.Type.WORLD_SURFACE_WG)) {
			return false;
		}

		// Try to ensure the arch feet are not in water.  96 doesn't guarantee it, but makes it likely.
		// Increasing the bounds checked here makes arches significantly less common.
		return getMinCenteredCornerHeight(context, 96, 96) >= context.chunkGenerator().getSeaLevel();
	}

	private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
		collector.addPiece(new CanyonArchGenerator(context.random(), context.chunkPos().getStartX(), context.chunkPos().getStartZ()));
	}

	@Override
	public GenerationStep.Feature getGenerationStep() {
		return GenerationStep.Feature.SURFACE_STRUCTURES;
	}

	// Centered version of StructureGeneratorFactory.Context.getMinCornerHeight(), considers ocean depth.
	public static <C extends FeatureConfig> int getMinCenteredCornerHeight(StructureGeneratorFactory.Context<C> context, int width, int length) {
		int x = context.chunkPos().getCenterX() - width / 2;
		int z = context.chunkPos().getCenterZ() - length / 2;

		return Math.min(
			Math.min(
				context.chunkGenerator().getHeightInGround(x, z, Heightmap.Type.OCEAN_FLOOR_WG, context.world()),
				context.chunkGenerator().getHeightInGround(x, z + length, Heightmap.Type.OCEAN_FLOOR_WG, context.world())
			),
			Math.min(
				context.chunkGenerator().getHeightInGround(x + width, z, Heightmap.Type.OCEAN_FLOOR_WG, context.world()),
				context.chunkGenerator().getHeightInGround(x + width, z + length, Heightmap.Type.OCEAN_FLOOR_WG, context.world())
			)
		);
	}

	public int getRadius() {
		return 12;
	}
}
