package com.terraformersmc.terrestria.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class TerrestriaTreeDecoratedFeatures {
	public static ConfiguredFeature<RandomFeatureConfig, ?> CATTAILS_WARM;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_OAK_SHRUBS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> PATCH_LUSH_FERNS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> PATCH_VOLCANIC_ISLAND_GRASS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> PATCH_DEAD_GRASS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> PATCH_OUTBACK_BUSHLAND_GRASS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> PATCH_OASIS_VEGETATION;
	public static ConfiguredFeature<RandomFeatureConfig, ?> PATCH_LUSH_DESERT_VEGETATION;

	// Terminology: Sparse = 1 per chunk, normal = 2 per chunk, dense = 3-4 per chunk, denser = 5-6 per chunk, densest = 7-9 per chunk.

	// Fallen logs.
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_FALLEN_HEMLOCK_LOGS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_FALLEN_REDWOOD_LOGS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> FALLEN_HEMLOCK_LOGS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> FALLEN_REDWOOD_LOGS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_FALLEN_HEMLOCK_LOGS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_FALLEN_REDWOOD_LOGS;

	// Small spruce-shaped trees.
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_SMALL_HEMLOCK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_SMALL_REDWOOD_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SMALL_HEMLOCK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SMALL_REDWOOD_TREES;

	// Taller and wider spruce-like trees.
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_HEMLOCK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_REDWOOD_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_CONIFER_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> HEMLOCK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> REDWOOD_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSEST_HEMLOCK_TREES;

	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_REDWOOD_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_HEMLOCK_TREES;

	// Gigantic 2x2 spruce-like trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_MEGA_HEMLOCK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> MEGA_REDWOOD_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> MEGA_HEMLOCK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSEST_MEGA_REDWOOD_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSEST_MEGA_HEMLOCK_TREES;

	// Fancy / large oak trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_FANCY_OAK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSER_FANCY_OAK_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSEST_FANCY_OAK_TREES;

	// Volcanic island Trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> JUNGLE_PALM_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSER_JUNGLE_PALM_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> RARE_DUM_DUM_HEADS;

	// Japanese Maple and Sakura trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_JAPANESE_MAPLE_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_DARK_JAPANESE_MAPLE_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_JAPANESE_MAPLE_SHRUBS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSER_SAKURA_TREES;

	// Small cypress trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSEST_CYPRESS_TREES;

	// Rainbow Rainforest trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSER_RAINBOW_EUCALYPTUS_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> DENSE_RUBBER_TREES;

	// Cypress Swamp trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> MEGA_CYPRESS_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> SPARSE_WILLOW_TREES;

	// Outback trees and shrubs
	public static ConfiguredFeature<RandomFeatureConfig, ?> OUTBACK_BUSHLAND_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> RARE_YUCCA_PALM_TREES;
	public static ConfiguredFeature<RandomFeatureConfig, ?> ACACIA_DOT_SHRUBS;
	public static ConfiguredFeature<RandomFeatureConfig, ?> OAK_DOT_SHRUBS;

	// Lush desert trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> SAGUARO_CACTUSES;

	// Bryce trees
	public static ConfiguredFeature<RandomFeatureConfig, ?> RARE_BRYCE_TREES;

	public static void init() {
		CATTAILS_WARM = register("cattails_warm", TerrestriaFeature.CATTAIL.configure(new ProbabilityConfig(0.3F)).repeat(80).decorate(ConfiguredFeatures.Decorators.SQUARE_TOP_SOLID_HEIGHTMAP));

		SPARSE_OAK_SHRUBS = decorateTree("oak_shrubs", 1, TerrestriaTreeConfiguredFeatures.OAK_SHRUB);

		PATCH_LUSH_FERNS = decoratePatch("patch_lush_ferns", 16, ConfiguredFeatures.Configs.TAIGA_GRASS_CONFIG);
		PATCH_VOLCANIC_ISLAND_GRASS = decoratePatch("patch_volcanic_island_grass", 12, new RandomPatchFeatureConfig.Builder(
				new WeightedBlockStateProvider(createStatePoolBuilder()
						.add(Blocks.GRASS.getDefaultState(), 1)
						.add(Blocks.FERN.getDefaultState(), 1)
						.add(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
						.add(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
						.build()), SimpleBlockPlacer.INSTANCE).tries(32).build());

		PATCH_DEAD_GRASS = decoratePatch("patch_dead_grass", 12, new RandomPatchFeatureConfig.Builder(
				BlockStateProvider.of(TerrestriaBlocks.DEAD_GRASS.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(4).build());

		PATCH_OUTBACK_BUSHLAND_GRASS = decoratePatch("patch_outback_bushland_grass", 12, new RandomPatchFeatureConfig.Builder(
				new WeightedBlockStateProvider(createStatePoolBuilder()
						.add(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
						.add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
						.build()), SimpleBlockPlacer.INSTANCE).tries(4).build());

		PATCH_OASIS_VEGETATION = decoratePatch("patch_oasis_vegetation", 6, new RandomPatchFeatureConfig.Builder(
				new WeightedBlockStateProvider(createStatePoolBuilder()
						.add(Blocks.FERN.getDefaultState(), 1)
						.add(Blocks.GRASS.getDefaultState(), 2)
						.add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
						.add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
						.add(TerrestriaBlocks.ALOE_VERA.getDefaultState(), 1)), SimpleBlockPlacer.INSTANCE).tries(32).build());

		PATCH_LUSH_DESERT_VEGETATION = decoratePatch("patch_lush_desert_vegetation", 4, new RandomPatchFeatureConfig.Builder(
				new WeightedBlockStateProvider(createStatePoolBuilder()
						.add(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 2)
						.add(Blocks.DEAD_BUSH.getDefaultState(), 1)
						.add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)), SimpleBlockPlacer.INSTANCE).tries(32).build());

		SPARSE_FALLEN_HEMLOCK_LOGS = decorateTree("sparse_fallen_hemlock_logs", 1, TerrestriaTreeConfiguredFeatures.FALLEN_HEMLOCK_LOG);
		SPARSE_FALLEN_REDWOOD_LOGS = decorateTree("sparse_fallen_redwood_logs", 1, TerrestriaTreeConfiguredFeatures.FALLEN_REDWOOD_LOG);
		FALLEN_HEMLOCK_LOGS = decorateTree("fallen_hemlock_logs", 2, TerrestriaTreeConfiguredFeatures.FALLEN_HEMLOCK_LOG);
		FALLEN_REDWOOD_LOGS = decorateTree("fallen_redwood_logs", 2, TerrestriaTreeConfiguredFeatures.FALLEN_REDWOOD_LOG);
		DENSE_FALLEN_HEMLOCK_LOGS = decorateTree("dense_fallen_hemlock_logs", 4, TerrestriaTreeConfiguredFeatures.FALLEN_HEMLOCK_LOG);
		DENSE_FALLEN_REDWOOD_LOGS = decorateTree("dense_fallen_redwood_logs", 4, TerrestriaTreeConfiguredFeatures.FALLEN_REDWOOD_LOG);

		SPARSE_SMALL_HEMLOCK_TREES = decorateTree("sparse_small_hemlock_trees", 1, TerrestriaTreeConfiguredFeatures.SMALL_HEMLOCK_TREE);
		SPARSE_SMALL_REDWOOD_TREES = decorateTree("sparse_small_redwood_trees", 1, TerrestriaTreeConfiguredFeatures.SMALL_REDWOOD_TREE);
		SMALL_HEMLOCK_TREES = decorateTree("small_hemlock_trees", 2, TerrestriaTreeConfiguredFeatures.SMALL_HEMLOCK_TREE);
		SMALL_REDWOOD_TREES = decorateTree("small_redwood_trees", 2, TerrestriaTreeConfiguredFeatures.SMALL_REDWOOD_TREE);

		SPARSE_HEMLOCK_TREES = decorateTree("sparse_hemlock_trees", 1, TerrestriaTreeConfiguredFeatures.HEMLOCK_TREE);
		SPARSE_REDWOOD_TREES = decorateTree("sparse_redwood_trees", 1, TerrestriaTreeConfiguredFeatures.REDWOOD_TREE);
		SPARSE_CONIFER_TREES = decorateTree("sparse_conifer_trees", 1, Feature.RANDOM_SELECTOR.configure(
				new RandomFeatureConfig(ImmutableList.of(TerrestriaTreeConfiguredFeatures.HEMLOCK_TREE.withChance(0.50F)), TerrestriaTreeConfiguredFeatures.REDWOOD_TREE)));
		HEMLOCK_TREES = decorateTree("hemlock_trees", 2, TerrestriaTreeConfiguredFeatures.HEMLOCK_TREE);
		REDWOOD_TREES = decorateTree("redwood_trees", 2, TerrestriaTreeConfiguredFeatures.REDWOOD_TREE);
		DENSE_HEMLOCK_TREES = decorateTree("dense_hemlock_trees", 4, TerrestriaTreeConfiguredFeatures.HEMLOCK_TREE);
		DENSE_REDWOOD_TREES = decorateTree("dense_redwood_trees", 3, TerrestriaTreeConfiguredFeatures.REDWOOD_TREE);
		DENSEST_HEMLOCK_TREES = decorateTree("densest_hemlock_trees", 8, TerrestriaTreeConfiguredFeatures.HEMLOCK_TREE);

		SPARSE_MEGA_HEMLOCK_TREES = decorateTree("sparse_mega_hemlock_trees", 1, TerrestriaTreeConfiguredFeatures.MEGA_HEMLOCK_TREE);
		MEGA_REDWOOD_TREES = decorateTree("mega_redwood_trees", 4, TerrestriaTreeConfiguredFeatures.MEGA_REDWOOD_TREE);
		MEGA_HEMLOCK_TREES = decorateTree("mega_hemlock_trees", 4, TerrestriaTreeConfiguredFeatures.MEGA_HEMLOCK_TREE);
		DENSEST_MEGA_REDWOOD_TREES = decorateTree("densest_mega_redwood_trees", 7, TerrestriaTreeConfiguredFeatures.MEGA_REDWOOD_TREE);
		DENSEST_MEGA_HEMLOCK_TREES = decorateTree("densest_mega_hemlock_trees", 8, TerrestriaTreeConfiguredFeatures.MEGA_HEMLOCK_TREE);

		DENSE_FANCY_OAK_TREES = decorateTree("dense_fancy_oak_trees", 3, ConfiguredFeatures.FANCY_OAK);
		DENSER_FANCY_OAK_TREES = decorateTree("denser_fancy_oak_trees", 5, ConfiguredFeatures.FANCY_OAK);
		DENSEST_FANCY_OAK_TREES = decorateTree("densest_fancy_oak_trees", 7, ConfiguredFeatures.FANCY_OAK);

		JUNGLE_PALM_TREES = decorateTree("jungle_palm_trees", 2, TerrestriaTreeConfiguredFeatures.JUNGLE_PALM_TREE);
		DENSER_JUNGLE_PALM_TREES = decorateTree("denser_jungle_palm_trees", 5, TerrestriaTreeConfiguredFeatures.JUNGLE_PALM_TREE);
		RARE_DUM_DUM_HEADS = decorateTree("rare_dum_dum_heads", 0, TerrestriaTreeConfiguredFeatures.DUM_DUM_HEAD);

		DENSE_JAPANESE_MAPLE_TREES = decorateTree("dense_japanese_maple_trees", 3, TerrestriaTreeConfiguredFeatures.JAPANESE_MAPLE_TREE);
		DENSE_DARK_JAPANESE_MAPLE_TREES = decorateTree("dense_dark_japanese_maple_trees", 3, TerrestriaTreeConfiguredFeatures.DARK_JAPANESE_MAPLE_TREE);
		DENSE_JAPANESE_MAPLE_SHRUBS = decorateTree("dense_japanese_maple_shrubs", 3, TerrestriaTreeConfiguredFeatures.JAPANESE_MAPLE_SHRUB);
		DENSER_SAKURA_TREES = decorateTree("denser_sakura_trees", 6, TerrestriaTreeConfiguredFeatures.SAKURA_TREE);

		DENSEST_CYPRESS_TREES = decorateTree("densest_cypress_trees", 9, TerrestriaTreeConfiguredFeatures.CYPRESS_TREE);

		DENSER_RAINBOW_EUCALYPTUS_TREES = decorateTree("denser_rainbow_eucalyptus_trees", 5, 3, TerrestriaTreeConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE);
		DENSE_RUBBER_TREES = decorateTree("dense_rubber_trees", 3, TerrestriaTreeConfiguredFeatures.RUBBER_TREE);

		MEGA_CYPRESS_TREES = decorateTree("mega_cypress_trees", 2, 6, TerrestriaTreeConfiguredFeatures.MEGA_CYPRESS_TREE);
		SPARSE_WILLOW_TREES = decorateTree("sparse_willow_trees", 1, TerrestriaTreeConfiguredFeatures.WILLOW_TREE);

		OUTBACK_BUSHLAND_TREES = decorateTree("outback_bushland_trees", 2, Feature.RANDOM_SELECTOR.configure(
				new RandomFeatureConfig(ImmutableList.of(ConfiguredFeatures.ACACIA.withChance(0.95F), TerrestriaTreeConfiguredFeatures.YUCCA_PALM_TREE.withChance(0.75F)), ConfiguredFeatures.FANCY_OAK)));

		RARE_YUCCA_PALM_TREES = decorateTree("yucca_palm_trees", 0, TerrestriaTreeConfiguredFeatures.YUCCA_PALM_TREE);
		ACACIA_DOT_SHRUBS = decorateTree("acacia_dot_shrubs", 2, TerrestriaTreeConfiguredFeatures.ACACIA_DOT_SHRUB);
		OAK_DOT_SHRUBS = decorateTree("oak_dot_shrubs", 2, TerrestriaTreeConfiguredFeatures.OAK_DOT_SHRUB);

		SAGUARO_CACTUSES = decorateTree("saguaro_cactuses", 2, TerrestriaTreeConfiguredFeatures.SAGUARO_CACTUS);

		RARE_BRYCE_TREES = register("rare_bryce_trees", TerrestriaTreeConfiguredFeatures.BRYCE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(2))));
	}

	private static DataPool.Builder<BlockState> createStatePoolBuilder() {
		return DataPool.builder();
	}

	private static ConfiguredFeature<RandomFeatureConfig, ?> decoratePatch(String name, int count, RandomPatchFeatureConfig config) {
		return register(name, Feature.RANDOM_PATCH.configure(config)
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE)
				.repeat(count));
	}

	private static ConfiguredFeature<RandomFeatureConfig, ?> decorateTree(String name, int count, ConfiguredFeature<RandomFeatureConfig, ?> base) {
		return register(name, base
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
				.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1))));
	}

	private static ConfiguredFeature<RandomFeatureConfig, ?> decorateTree(String name, int count, int maxWaterDepth, ConfiguredFeature<RandomFeatureConfig, ?> base) {
		// Why did I add this? It's been a month since then, and I can't remember what it was supposed to do :/
		// return register(name, Feature.RANDOM_SELECTOR.configure(
		// 		new RandomBooleanFeatureConfig(List.of(new RandomFeatureEntry(base))))
		// 		.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
		// 		.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1))
		// 		.decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(maxWaterDepth)))));
		return register(name, base
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
				.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1))
				.decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(maxWaterDepth)))));
	}

	private static ConfiguredFeature<RandomFeatureConfig, ?> register(String name, ConfiguredFeature<RandomFeatureConfig, ?> decorated) {
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, decorated);

		return decorated;
	}
}
