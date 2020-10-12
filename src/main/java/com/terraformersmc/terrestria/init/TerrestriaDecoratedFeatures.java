package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class TerrestriaDecoratedFeatures {
	public static ConfiguredFeature<?, ?> FALLEN_REDWOOD_LOGS;
	public static ConfiguredFeature<?, ?> MEGA_REDWOOD_TREES;

	public static void init() {
		FALLEN_REDWOOD_LOGS = decorateTree("fallen_redwood_logs", 2, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);
		MEGA_REDWOOD_TREES = decorateTree("mega_redwood_trees", 4, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);
	}

	private static ConfiguredFeature<?, ?> decorateTree(String name, int count, ConfiguredFeature<?, ?> base) {
		ConfiguredFeature<?, ?> decorated = base.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1)));
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, decorated);

		return decorated;
	}
}
