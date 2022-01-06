package com.terraformersmc.terrestria.block.sapling;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class TerrestriaSaplingGenerator extends SaplingGenerator {
	public final Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> tree;

	public TerrestriaSaplingGenerator(Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> tree) {
		this.tree = tree;
	}

	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bl) {
		return this.tree.get();
	}
}
