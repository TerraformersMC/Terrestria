package com.terraformersmc.terrestria.surfacerules;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.MaterialRule;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

public class TerrestriaSurfaceRules {

	private static MaterialRule block(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}

	public static MaterialRule createRules() {
		MaterialRule defaultGrass = VanillaSurfaceRules.createDefaultRule(true, false, true);

		MaterialRule sandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule deepSandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule redSandAndSandstone = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		MaterialRule blackSandAndBasalt = sequence(condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, block(TerrestriaBlocks.BLACK_SAND)), block(TerrestriaBlocks.VOLCANIC_ROCK.plain.full));
		MaterialRule deepBlackGrassAndBasalt = sequence(condition(STONE_DEPTH_FLOOR, condition(aboveY(YOffset.fixed(62), 0), condition(water(0, 0), block(TerrestriaBlocks.ANDISOL.getGrassBlock())))), condition(STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, block(TerrestriaBlocks.ANDISOL.getDirt())), block(TerrestriaBlocks.VOLCANIC_ROCK.plain.full));

		// Biome-level rules
		MaterialRule canyon = condition(MaterialRules.biome(TerrestriaBiomes.CANYON), sandAndSandstone);
		MaterialRule cypressSwamp = condition(MaterialRules.biome(TerrestriaBiomes.CYPRESS_SWAMP),
				condition(MaterialRules.STONE_DEPTH_FLOOR,
						condition(MaterialRules.aboveY(YOffset.fixed(62), 0),
								condition(MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(63), 0)),
										condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0D), block(Blocks.WATER))))));
		MaterialRule dunes = condition(MaterialRules.biome(TerrestriaBiomes.DUNES), deepSandAndSandstone);
		MaterialRule lushDesert = condition(MaterialRules.biome(TerrestriaBiomes.LUSH_DESERT),
				new SandWithPatchesSurfaceRule(-0.75D, NoiseParametersKeys.SURFACE, sandAndSandstone, defaultGrass));
		MaterialRule outback = condition(MaterialRules.biome(TerrestriaBiomes.OUTBACK),
				new SandWithPatchesSurfaceRule(-0.12D, NoiseParametersKeys.BADLANDS_SURFACE, redSandAndSandstone, defaultGrass));
		MaterialRule volcanicIsland = condition(MaterialRules.biome(TerrestriaBiomes.VOLCANIC_ISLAND),
				new VolcanicIslandSurfaceRule(0.05D, NoiseParametersKeys.CONTINENTALNESS, deepBlackGrassAndBasalt, blackSandAndBasalt));

		// Volcanic rock all the way down to the deepslate.
		MaterialRule volcanicIslandSubSurface = condition(biome(TerrestriaBiomes.VOLCANIC_ISLAND), sequence(MaterialRules.condition(MaterialRules.verticalGradient("deepslate", YOffset.fixed(0), YOffset.fixed(8)), block(Blocks.DEEPSLATE)), block(TerrestriaBlocks.VOLCANIC_ROCK.plain.full)));

		return sequence(condition(MaterialRules.surface(), sequence(canyon, cypressSwamp, dunes, lushDesert, outback, volcanicIsland, defaultGrass)), volcanicIslandSubSurface, defaultGrass);
	}

	public static void register() {
		Registry.register(Registry.MATERIAL_RULE, new Identifier(Terrestria.MOD_ID,"sand_with_patches_rule"), SandWithPatchesSurfaceRule.CONDITION_CODEC);
		Registry.register(Registry.MATERIAL_RULE, new Identifier(Terrestria.MOD_ID,"volcanic_island_rule"), VolcanicIslandSurfaceRule.CONDITION_CODEC);
	}
}
