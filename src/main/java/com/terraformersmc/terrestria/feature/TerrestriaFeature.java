package com.terraformersmc.terrestria.feature;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class TerrestriaFeature {

	private GenerationStep.Feature step;
	private ConfiguredFeature feature;

	public TerrestriaFeature(GenerationStep.Feature step, ConfiguredFeature feature) {
		this.step = step;
		this.feature = feature;
	}

	public GenerationStep.Feature getStep() {
		return this.step;
	}

	public ConfiguredFeature getFeature() {
		return this.feature;
	}
}
