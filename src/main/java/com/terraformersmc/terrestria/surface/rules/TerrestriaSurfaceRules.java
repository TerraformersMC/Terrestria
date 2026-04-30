package com.terraformersmc.terrestria.surface.rules;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import static net.minecraft.world.level.levelgen.SurfaceRules.*;

public class TerrestriaSurfaceRules {
	public static RuleSource createRules() {
		// Sandy surface rules
		RuleSource sandAndSandstone = sequence(ifTrue(UNDER_FLOOR,
			block(Blocks.SAND)), block(Blocks.SANDSTONE));
		RuleSource redSandAndSandstone = sequence(ifTrue(UNDER_FLOOR,
			block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		// Special dirt surfaces
		RuleSource oldGrowthSurface = ifTrue(ON_FLOOR, sequence(
			ifTrue(surfaceNoiseThreshold(1.75), block(Blocks.COARSE_DIRT)),
				ifTrue(surfaceNoiseThreshold(-0.95), block(Blocks.PODZOL))));

		// Biome-level rules
		RuleSource canyon = ifTrue(isBiome(TerrestriaBiomes.CANYON), sandAndSandstone);
		RuleSource cypressSwamp = ifTrue(isBiome(TerrestriaBiomes.CYPRESS_SWAMP),
			ifTrue(ON_FLOOR,
				ifTrue(yBlockCheck(VerticalAnchor.absolute(62), 0),
					ifTrue(not(yBlockCheck(VerticalAnchor.absolute(63), 0)),
						ifTrue(noiseCondition(Noises.SWAMP, 0.0D),
							block(Blocks.WATER))))));
		RuleSource dunes = ifTrue(isBiome(TerrestriaBiomes.DUNES), sandAndSandstone);
		RuleSource lushDesert = ifTrue(isBiome(TerrestriaBiomes.LUSH_DESERT),
			ifTrue(noiseCondition(Noises.SURFACE, -0.75D), sandAndSandstone));
		RuleSource outback = ifTrue(isBiome(TerrestriaBiomes.OUTBACK),
			ifTrue(noiseCondition(Noises.BADLANDS_SURFACE, -0.12D), redSandAndSandstone));
		RuleSource redwoodForest = ifTrue(isBiome(TerrestriaBiomes.REDWOOD_FOREST), oldGrowthSurface);

		// Return a surface-only sequence of our surface rules
		return ifTrue(abovePreliminarySurface(),
				sequence(canyon, cypressSwamp, dunes, lushDesert, outback, redwoodForest));
	}

	private static RuleSource block(Block block) {
		return SurfaceRules.state(block.defaultBlockState());
	}

	private static SurfaceRules.ConditionSource surfaceNoiseThreshold(double min) {
		return SurfaceRules.noiseCondition(Noises.SURFACE, min / 8.25, Double.MAX_VALUE);
	}

	public static void init() {
	}
}
