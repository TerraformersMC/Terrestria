package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record VolcanoFeatureConfig(IntProvider height, int baseY, boolean thinIfTall) implements FeatureConfiguration {
	public static Codec<VolcanoFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
					IntProviders.CODEC.fieldOf("height").forGetter(config -> config.height),
					Codec.INT.fieldOf("baseY").forGetter(config -> config.baseY),
					Codec.BOOL.fieldOf("thinIfTall").forGetter(config -> config.thinIfTall)
			).apply(instance, VolcanoFeatureConfig::new)
	);
}
