package com.terraformersmc.terrestria.block.sapling;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public class TerrestriaLargeSaplingGenerator extends LargeTreeSaplingGenerator {
	public final Supplier<RegistryKey<ConfiguredFeature<?, ?>>> tree;
	public final Supplier<RegistryKey<ConfiguredFeature<?, ?>>> largeTree;

	public TerrestriaLargeSaplingGenerator(Supplier<RegistryKey<ConfiguredFeature<?, ?>>> tree, Supplier<RegistryKey<ConfiguredFeature<?, ?>>> largeTree) {
		this.tree = tree;
		this.largeTree = largeTree;
	}

	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return this.tree.get();
	}

	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
		return this.largeTree.get();
	}
}
