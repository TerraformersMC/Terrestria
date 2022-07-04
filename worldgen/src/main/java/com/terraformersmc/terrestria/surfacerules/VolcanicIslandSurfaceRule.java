package com.terraformersmc.terrestria.surfacerules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

record VolcanicIslandSurfaceRule(double threshold, RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, MaterialRule thenRun, MaterialRule elseRun) implements MaterialRule {
	static final Codec<VolcanicIslandSurfaceRule> CONDITION_CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.DOUBLE.fieldOf("threshold").forGetter(VolcanicIslandSurfaceRule::threshold),
			RegistryKey.createCodec(Registry.NOISE_WORLDGEN).fieldOf("noise").forGetter(VolcanicIslandSurfaceRule::noise),
			MaterialRule.CODEC.fieldOf("then_run").forGetter(VolcanicIslandSurfaceRule::thenRun),
			MaterialRule.CODEC.fieldOf("else_run").forGetter(VolcanicIslandSurfaceRule::elseRun)).apply(instance, VolcanicIslandSurfaceRule::new));

	@Override
	public Codec<? extends MaterialRule> codec() {
		return CONDITION_CODEC;
	}

	@Override
	public BlockStateRule apply(MaterialRuleContext context) {
		final BlockStateRule followup1 = thenRun.apply(context);
		final BlockStateRule followup2 = elseRun.apply(context);
		final DoublePerlinNoiseSampler doublePerlinNoiseSampler = context.surfaceBuilder.getNoiseSampler(this.noise);

		return (x, y, z) -> {
			double noise = doublePerlinNoiseSampler.sample(x, 0.0, z);
			if (noise > threshold) {
				return followup1.tryApply(x, y, z);
			} else {
				if ((y > 64) || (y > 63 && noise < 0.8D * threshold) || (y > 62 && noise < 0.1D * threshold)) {
					return Blocks.AIR.getDefaultState();
				} else if (y > 56) {
					return TerrestriaBlocks.BLACK_SAND.getDefaultState();
				}
				return followup2.tryApply(x, y, z);
			}
		};
	}
}
