package com.terraformersmc.terrestria.surfacerules;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialRule;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class TerrestriaSurfaceRules {

	private static MaterialRule block(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}

	public static MaterialRule createRules() {

		// Sandy surface rules
		MaterialRule sandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
			block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule redSandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
			block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		// Biome-level rules
		MaterialRule canyon = condition(biome(TerrestriaBiomes.CANYON), sandAndSandstone);
		MaterialRule cypressSwamp = condition(biome(TerrestriaBiomes.CYPRESS_SWAMP),
			condition(STONE_DEPTH_FLOOR,
				condition(aboveY(YOffset.fixed(62), 0),
					condition(not(aboveY(YOffset.fixed(63), 0)),
						condition(noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0D),
							block(Blocks.WATER))))));
		MaterialRule denseWoodlands = condition(biome(TerrestriaBiomes.DENSE_WOODLANDS),
			condition(STONE_DEPTH_FLOOR,
				condition(aboveY(YOffset.fixed(62), 0), sequence(
					condition(noiseThreshold(NoiseParametersKeys.SURFACE, -0.67D),
						condition(noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.2D), block(Blocks.PODZOL))),
					block(Blocks.GRASS_BLOCK)))));
		MaterialRule dunes = condition(biome(TerrestriaBiomes.DUNES), sandAndSandstone);
		MaterialRule lushDesert = condition(biome(TerrestriaBiomes.LUSH_DESERT),
			condition(noiseThreshold(NoiseParametersKeys.SURFACE, -0.75D), sandAndSandstone));
		MaterialRule outback = condition(biome(TerrestriaBiomes.OUTBACK),
			condition(noiseThreshold(NoiseParametersKeys.BADLANDS_SURFACE, -0.12D), redSandAndSandstone));
		MaterialRule windsweptWoodlands = condition(biome(TerrestriaBiomes.WINDSWEPT_WOODLANDS),
			condition(aboveY(YOffset.fixed(62), 0), sequence(
				condition(STONE_DEPTH_FLOOR,
					condition(noiseThreshold(NoiseParametersKeys.SURFACE, -0.67D),
						condition(noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.2D),
							block(Blocks.GRASS_BLOCK)))),
				condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, block(Blocks.COARSE_DIRT)))));

		// Return a surface-only sequence of our surface rules
		return condition(surface(),
				sequence(canyon, cypressSwamp, denseWoodlands, dunes, lushDesert, outback, windsweptWoodlands));
	}

	public static void init() {
	}

	public static <T extends MaterialRule> Codec<T> register(String id, Codec<T> ruleCodec) {
		return Registry.register(Registry.MATERIAL_RULE, new Identifier(Terrestria.MOD_ID, id), ruleCodec);
	}
}
