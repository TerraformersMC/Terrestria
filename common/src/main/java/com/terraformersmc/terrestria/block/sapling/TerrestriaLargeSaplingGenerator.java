package com.terraformersmc.terrestria.block.sapling;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.function.Supplier;

public class TerrestriaLargeSaplingGenerator extends LargeTreeSaplingGenerator {
	public final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree;
	public final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> largeTree;

	public TerrestriaLargeSaplingGenerator(Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree, Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> largeTree) {
		this.tree = tree;
		this.largeTree = largeTree;
	}

	@Override
	protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeFeature(Random random, boolean bees) {
		return this.tree.get();
	}

	@Override
	protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getLargeTreeFeature(Random random) {
		return this.largeTree.get();
	}
}
