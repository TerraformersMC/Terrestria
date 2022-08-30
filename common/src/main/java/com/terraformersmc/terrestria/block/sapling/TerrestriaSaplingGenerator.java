package com.terraformersmc.terrestria.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class TerrestriaSaplingGenerator extends SaplingGenerator {
	public final Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree;

	public TerrestriaSaplingGenerator(Supplier<RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> tree) {
		this.tree = tree;
	}

	@Nullable
	@Override
	protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return this.tree.get();
	}
}
