package com.terraformersmc.terrestria.world.gen.feature;

import com.terraformersmc.terraform.tree.feature.TerraformTreeFeatures;
import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class TerrestriaConfiguredFeatures {

	/**
	 * Registers a normal Minecraft tree
	 */
	public static ConfiguredFeature<TreeFeatureConfig, ?> registerTree(String id, TreeFeatureConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = Feature.TREE.configure(config);
		Identifier identifier = new Identifier(Terrestria.MOD_ID, id);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configured);

		return configured;
	}


	/**
	 * Registers a tree with a 2x2 stem block base, e.g. Hemlock or Redwood
	 */
	public static ConfiguredFeature<TreeFeatureConfig, ?> registerQuarteredMegaTree(String name, QuarteredMegaTreeConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = TerrestriaFeature.QUARTERED_MEGA_TREE.configure(config);
		Identifier identifier = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configured);

		return configured;
	}


	/**
	 * Registers a tree which can be also placed on sand, e.g. the palms or the Saguaro Cactus.
	 */
	public static ConfiguredFeature<TreeFeatureConfig, ?> registerSandyTree(String id, TreeFeatureConfig config) {
		ConfiguredFeature<TreeFeatureConfig, ?> configured = TerraformTreeFeatures.SANDY_TREE.configure(config);
		Identifier identifier = new Identifier(Terrestria.MOD_ID, id);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configured);

		return configured;
	}
}
