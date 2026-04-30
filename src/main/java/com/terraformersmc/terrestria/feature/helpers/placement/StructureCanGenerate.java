package com.terraformersmc.terrestria.feature.helpers.placement;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.Structure;

public class StructureCanGenerate {

	// Centered version of StructureGeneratorFactory.Context.getMinCornerHeight(), considers ocean depth.
	public static <C extends FeatureConfiguration> int getMinCenteredCornerHeight(Structure.GenerationContext context, int width, int length) {
		int x = context.chunkPos().getMiddleBlockX() - width / 2;
		int z = context.chunkPos().getMiddleBlockZ() - length / 2;

		return Math.min(
				Math.min(
						context.chunkGenerator().getFirstOccupiedHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()),
						context.chunkGenerator().getFirstOccupiedHeight(x, z + length, Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState())
				),
				Math.min(
						context.chunkGenerator().getFirstOccupiedHeight(x + width, z, Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState()),
						context.chunkGenerator().getFirstOccupiedHeight(x + width, z + length, Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState())
				)
		);
	}
}
