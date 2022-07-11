package com.terraformersmc.terrestria.feature.helpers.placement;

import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeatureConfig;

public class StructureCanGenerate {

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
}
