package com.terraformersmc.terrestria.surfacerules;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialRule;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class TerrestriaSurfaceRules {
	public static MaterialRule createRules() {
		// Sandy surface rules
		MaterialRule sandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
			block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule redSandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
			block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		// Special dirt surfaces
		MaterialRule oldGrowthSurface = condition(STONE_DEPTH_FLOOR, sequence(
			condition(surfaceNoiseThreshold(1.75), block(Blocks.COARSE_DIRT)),
				condition(surfaceNoiseThreshold(-0.95), block(Blocks.PODZOL))));

		// Biome-level rules
		MaterialRule canyon = condition(biome(TerrestriaBiomes.CANYON), sandAndSandstone);
		MaterialRule cypressSwamp = condition(biome(TerrestriaBiomes.CYPRESS_SWAMP),
			condition(STONE_DEPTH_FLOOR,
				condition(aboveY(YOffset.fixed(62), 0),
					condition(not(aboveY(YOffset.fixed(63), 0)),
						condition(noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0D),
							block(Blocks.WATER))))));
		MaterialRule dunes = condition(biome(TerrestriaBiomes.DUNES), sandAndSandstone);
		MaterialRule lushDesert = condition(biome(TerrestriaBiomes.LUSH_DESERT),
			condition(noiseThreshold(NoiseParametersKeys.SURFACE, -0.75D), sandAndSandstone));
		MaterialRule outback = condition(biome(TerrestriaBiomes.OUTBACK),
			condition(noiseThreshold(NoiseParametersKeys.BADLANDS_SURFACE, -0.12D), redSandAndSandstone));
		MaterialRule redwoodForest = condition(biome(TerrestriaBiomes.REDWOOD_FOREST), oldGrowthSurface);

		// Return a surface-only sequence of our surface rules
		return condition(surface(),
				sequence(canyon, cypressSwamp, dunes, lushDesert, outback, redwoodForest));
	}

	private static MaterialRule block(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}

	private static MaterialRules.MaterialCondition surfaceNoiseThreshold(double min) {
		return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE);
	}

	public static void init() {
	}
}
