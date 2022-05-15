package com.terraformersmc.terrestria.surfacerules;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
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
//		MaterialRule sandAndSandstone = sequence(condition(MaterialRules.STONE_DEPTH_CEILING, block(Blocks.SANDSTONE)), block(Blocks.SAND));
		MaterialRule sandAndSandstone = sequence(condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule deepSandAndSandstone = sequence(condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule redSandAndSandstone = sequence(condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		MaterialRule cypressSwamp = condition(MaterialRules.biome(TerrestriaBiomes.CYPRESS_SWAMP),
				condition(MaterialRules.STONE_DEPTH_FLOOR,
						condition(MaterialRules.aboveY(YOffset.fixed(62), 0),
								condition(MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(63), 0)),
										condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), block(Blocks.WATER))))));
		MaterialRule dunes = condition(MaterialRules.biome(TerrestriaBiomes.DUNES),
				new SandWithPatchesSurfaceRule(1.5D, NoiseParametersKeys.SURFACE, defaultGrass, deepSandAndSandstone));
		MaterialRule lushDesert = condition(MaterialRules.biome(TerrestriaBiomes.LUSH_DESERT),
				new SandWithPatchesSurfaceRule(0.9D, NoiseParametersKeys.SURFACE, defaultGrass, sandAndSandstone));
		MaterialRule outback = condition(MaterialRules.biome(TerrestriaBiomes.OUTBACK),
				new SandWithPatchesSurfaceRule(0.5D, NoiseParametersKeys.BADLANDS_SURFACE, defaultGrass, redSandAndSandstone));

		return sequence(condition(MaterialRules.surface(), sequence(cypressSwamp, dunes, lushDesert, outback)), defaultGrass);
	}

	public static void register() {
		Registry.register(Registry.MATERIAL_RULE, new Identifier(Terrestria.MOD_ID,"sand_with_patches_rule"), SandWithPatchesSurfaceRule.CONDITION_CODEC);
	}

	record SandWithPatchesSurfaceRule(double threshold, RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, MaterialRule thenRun, MaterialRule elseRun) implements MaterialRule {

		static final Codec<SandWithPatchesSurfaceRule> CONDITION_CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.DOUBLE.fieldOf("threshold").forGetter(SandWithPatchesSurfaceRule::threshold),
				RegistryKey.createCodec(Registry.NOISE_WORLDGEN).fieldOf("noise").forGetter(SandWithPatchesSurfaceRule::noise),
				MaterialRule.CODEC.fieldOf("then_run").forGetter(SandWithPatchesSurfaceRule::thenRun),
				MaterialRule.CODEC.fieldOf("else_run").forGetter(SandWithPatchesSurfaceRule::elseRun)).apply(instance, SandWithPatchesSurfaceRule::new));

		@Override
		public Codec<? extends MaterialRule> codec() {
			return CONDITION_CODEC;
		}

		@Override
		public MaterialRules.BlockStateRule apply(MaterialRules.MaterialRuleContext context) {
			MaterialRules.BlockStateRule followup1 = thenRun.apply(context);
			MaterialRules.BlockStateRule followup2 = elseRun.apply(context);
			final DoublePerlinNoiseSampler doublePerlinNoiseSampler = context.surfaceBuilder.getNoiseSampler(this.noise);
			return (x, y, z) -> {
				double noise = doublePerlinNoiseSampler.sample(x, 0.0, y);
				if (noise > threshold){
					return followup1.tryApply(x, y, z);
				} else {
					return followup2.tryApply(x, y, z);
				}
			};
		}
	}

}
