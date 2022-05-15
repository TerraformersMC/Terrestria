package com.terraformersmc.terrestria.block.sapling;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class TerrestriaSaplingGenerator extends SaplingGenerator {
	public final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree;

	public TerrestriaSaplingGenerator(Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree) {
		this.tree = tree;
	}

	@Override
	protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeFeature(Random random, boolean bees) {
		return this.tree.get();
	}
}
