package net.coderbot.terrestria.block;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class CustomSaplingGenerator extends SaplingGenerator {
	private Function<Random, AbstractTreeFeature<DefaultFeatureConfig>> provider;

	public CustomSaplingGenerator(Function<Random, AbstractTreeFeature<DefaultFeatureConfig>> provider) {
		super();

		this.provider = provider;
	}

	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random random) {
		return provider.apply(random);
	}
}
