package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public class VolcanoFeatureConfig implements FeatureConfig {
	public static Codec<VolcanoFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->
		instance.group(
				IntProvider.VALUE_CODEC.fieldOf("height").forGetter(config -> config.height),
				Codec.INT.fieldOf("baseY").forGetter(config -> config.baseY),
				Codec.BOOL.fieldOf("thinIfTall").forGetter(config -> config.thinIfTall)
		).apply(instance, VolcanoFeatureConfig::new)
	);

	private final IntProvider height;
	private final int baseY;
	private final boolean thinIfTall;

	public VolcanoFeatureConfig(IntProvider height, int baseY, boolean thinIfTall) {
		this.height = height;
		this.baseY = baseY;
		this.thinIfTall = thinIfTall;
	}

	public IntProvider getHeight() {
		return height;
	}

	public int getBaseY() {
		return baseY;
	}

	public boolean isThinIfTall() {
		return thinIfTall;
	}
}
