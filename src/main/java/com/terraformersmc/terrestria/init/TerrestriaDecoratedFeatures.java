package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class TerrestriaDecoratedFeatures {
	public static ConfiguredFeature<?, ?> PATCH_LUSH_FERNS;
	public static ConfiguredFeature<?, ?> PATCH_VOLCANIC_ISLAND_GRASS;

	// Terminology: Sparse = 1 per chunk, normal = 2 per chunk, dense = 3-4 per chunk, denser = 5-6 per chunk, densest = 7-9 per chunk.

	// Fallen logs.
	public static ConfiguredFeature<?, ?> SPARSE_FALLEN_HEMLOCK_LOGS;
	public static ConfiguredFeature<?, ?> SPARSE_FALLEN_REDWOOD_LOGS;
	public static ConfiguredFeature<?, ?> FALLEN_HEMLOCK_LOGS;
	public static ConfiguredFeature<?, ?> FALLEN_REDWOOD_LOGS;
	public static ConfiguredFeature<?, ?> DENSE_FALLEN_HEMLOCK_LOGS;
	public static ConfiguredFeature<?, ?> DENSE_FALLEN_REDWOOD_LOGS;

	// Small spruce-shaped trees.
	public static ConfiguredFeature<?, ?> SPARSE_SMALL_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> SPARSE_SMALL_REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> SMALL_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> SMALL_REDWOOD_TREES;

	// Taller and wider spruce-like trees.
	public static ConfiguredFeature<?, ?> SPARSE_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> SPARSE_REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> DENSEST_HEMLOCK_TREES;

	public static ConfiguredFeature<?, ?> DENSE_HEMLOCK_TREES;

	// Gigantic 2x2 spruce-like trees
	public static ConfiguredFeature<?, ?> SPARSE_MEGA_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> MEGA_REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> MEGA_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> DENSEST_MEGA_HEMLOCK_TREES;

	// Volcanic island Trees
	public static ConfiguredFeature<?, ?> JUNGLE_PALM_TREES;
	public static ConfiguredFeature<?, ?> DENSER_JUNGLE_PALM_TREES;

	// Japanese Maple and Sakura trees
	public static ConfiguredFeature<?, ?> DENSE_JAPANESE_MAPLE_TREES;
	public static ConfiguredFeature<?, ?> DENSE_DARK_JAPANESE_MAPLE_TREES;
	public static ConfiguredFeature<?, ?> DENSE_JAPANESE_MAPLE_SHRUBS;

	// Small cypress trees
	public static ConfiguredFeature<?, ?> DENSEST_CYPRESS_TREES;

	// Rainbow Rainforest trees
	public static ConfiguredFeature<?, ?> DENSER_RAINBOW_EUCALYPTUS_TREES;
	public static ConfiguredFeature<?, ?> DENSER_FANCY_OAK_TREES;
	public static ConfiguredFeature<?, ?> DENSE_RUBBER_TREES;

	public static void init() {
		PATCH_LUSH_FERNS = decoratePatch("patch_lush_ferns", 16, ConfiguredFeatures.Configs.TAIGA_GRASS_CONFIG);
		PATCH_VOLCANIC_ISLAND_GRASS = decoratePatch("patch_volcanic_island_grass", 12, new RandomPatchFeatureConfig.Builder(
				new WeightedBlockStateProvider()
						.addState(Blocks.GRASS.getDefaultState(), 1)
						.addState(Blocks.FERN.getDefaultState(), 1)
						.addState(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
						.addState(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4), SimpleBlockPlacer.INSTANCE).tries(32).build());

		SPARSE_FALLEN_HEMLOCK_LOGS = decorateTree("sparse_fallen_hemlock_logs", 1, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);
		SPARSE_FALLEN_REDWOOD_LOGS = decorateTree("sparse_fallen_redwood_logs", 1, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);
		FALLEN_HEMLOCK_LOGS = decorateTree("fallen_hemlock_logs", 2, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);
		FALLEN_REDWOOD_LOGS = decorateTree("fallen_redwood_logs", 2, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);
		DENSE_FALLEN_HEMLOCK_LOGS = decorateTree("dense_fallen_hemlock_logs", 4, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);
		DENSE_FALLEN_REDWOOD_LOGS = decorateTree("dense_fallen_redwood_logs", 4, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		SPARSE_SMALL_HEMLOCK_TREES = decorateTree("sparse_small_hemlock_trees", 1, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);
		SPARSE_SMALL_REDWOOD_TREES = decorateTree("sparse_small_redwood_trees", 1, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);
		SMALL_HEMLOCK_TREES = decorateTree("small_hemlock_trees", 2, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);
		SMALL_REDWOOD_TREES = decorateTree("small_redwood_trees", 2, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);

		SPARSE_HEMLOCK_TREES = decorateTree("sparse_hemlock_trees", 1, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
		SPARSE_REDWOOD_TREES = decorateTree("sparse_redwood_trees", 1, TerrestriaConfiguredFeatures.REDWOOD_TREE);
		HEMLOCK_TREES = decorateTree("hemlock_trees", 2, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
		REDWOOD_TREES = decorateTree("redwood_trees", 2, TerrestriaConfiguredFeatures.REDWOOD_TREE);
		DENSE_HEMLOCK_TREES = decorateTree("four_hemlock_trees", 4, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
		DENSEST_HEMLOCK_TREES = decorateTree("densest_hemlock_trees", 8, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		SPARSE_MEGA_HEMLOCK_TREES = decorateTree("sparse_mega_hemlock_trees", 1, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);
		MEGA_REDWOOD_TREES = decorateTree("mega_redwood_trees", 4, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);
		MEGA_HEMLOCK_TREES = decorateTree("mega_hemlock_trees", 4, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);
		DENSEST_MEGA_HEMLOCK_TREES = decorateTree("densest_mega_hemlock_trees", 8, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		JUNGLE_PALM_TREES = decorateTree("jungle_palm_trees", 2, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE);
		DENSER_JUNGLE_PALM_TREES = decorateTree("denser_jungle_palm_trees", 5, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE);

		DENSE_JAPANESE_MAPLE_TREES = decorateTree("dense_japanese_maple_trees", 3, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_TREE);
		DENSE_DARK_JAPANESE_MAPLE_TREES = decorateTree("dense_dark_japanese_maple_trees", 3, TerrestriaConfiguredFeatures.DARK_JAPANESE_MAPLE_TREE);
		DENSE_JAPANESE_MAPLE_SHRUBS = decorateTree("dense_japanese_maple_shrubs", 3, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_SHRUB);

		DENSEST_CYPRESS_TREES = decorateTree("densest_cypress_trees", 9, TerrestriaConfiguredFeatures.CYPRESS_TREE);

		DENSER_RAINBOW_EUCALYPTUS_TREES = decorateTree("denser_rainbow_eucalyptus_trees", 5, TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE);
		DENSER_FANCY_OAK_TREES = decorateTree("denser_fancy_oak_trees", 5, ConfiguredFeatures.FANCY_OAK);
		DENSE_RUBBER_TREES = decorateTree("dense_rubber_trees", 3, TerrestriaConfiguredFeatures.RUBBER_TREE);
	}

	private static ConfiguredFeature<?, ?> decoratePatch(String name, int count, RandomPatchFeatureConfig config) {
		return register(name, Feature.RANDOM_PATCH.configure(config).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(count));
	}

	private static ConfiguredFeature<?, ?> decorateTree(String name, int count, ConfiguredFeature<?, ?> base) {
		return register(name, base.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1))));
	}

	private static ConfiguredFeature<?, ?> register(String name, ConfiguredFeature<?, ?> decorated) {
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, decorated);

		return decorated;
	}
}
