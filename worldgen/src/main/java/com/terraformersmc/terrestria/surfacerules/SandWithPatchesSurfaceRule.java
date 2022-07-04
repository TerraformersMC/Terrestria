package com.terraformersmc.terrestria.surfacerules;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.*;

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
	public BlockStateRule apply(MaterialRuleContext context) {
		final BlockStateRule followup1 = thenRun.apply(context);
		final BlockStateRule followup2 = elseRun.apply(context);
		final DoublePerlinNoiseSampler doublePerlinNoiseSampler = context.surfaceBuilder.getNoiseSampler(this.noise);

		return (x, y, z) -> {
			double noise = doublePerlinNoiseSampler.sample(x, 0.0, z);
			if (noise > threshold) {
				return followup1.tryApply(x, y, z);
			} else {
				return followup2.tryApply(x, y, z);
			}
		};
	}
}
