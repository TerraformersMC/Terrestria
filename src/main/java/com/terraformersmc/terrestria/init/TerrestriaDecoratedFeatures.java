package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

public class TerrestriaDecoratedFeatures {
	public static ConfiguredFeature<?, ?> CATTAILS_WARM;
	public static ConfiguredFeature<?, ?> SPARSE_OAK_SHRUBS;
	public static ConfiguredFeature<?, ?> PATCH_LUSH_FERNS;
	public static ConfiguredFeature<?, ?> PATCH_VOLCANIC_ISLAND_GRASS;
	public static ConfiguredFeature<?, ?> PATCH_DEAD_GRASS;
	public static ConfiguredFeature<?, ?> PATCH_OUTBACK_BUSHLAND_GRASS;
	public static ConfiguredFeature<?, ?> PATCH_OASIS_VEGETATION;
	public static ConfiguredFeature<?, ?> PATCH_LUSH_DESERT_VEGETATION;

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
	public static ConfiguredFeature<?, ?> SPARSE_CONIFER_TREES;
	public static ConfiguredFeature<?, ?> HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> DENSEST_HEMLOCK_TREES;

	public static ConfiguredFeature<?, ?> DENSE_REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> DENSE_HEMLOCK_TREES;

	// Gigantic 2x2 spruce-like trees
	public static ConfiguredFeature<?, ?> SPARSE_MEGA_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> MEGA_REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> MEGA_HEMLOCK_TREES;
	public static ConfiguredFeature<?, ?> DENSEST_MEGA_REDWOOD_TREES;
	public static ConfiguredFeature<?, ?> DENSEST_MEGA_HEMLOCK_TREES;

	// Fancy / large oak trees
	public static ConfiguredFeature<?, ?> DENSE_FANCY_OAK_TREES;
	public static ConfiguredFeature<?, ?> DENSER_FANCY_OAK_TREES;
	public static ConfiguredFeature<?, ?> DENSEST_FANCY_OAK_TREES;

	// Volcanic island Trees
	public static ConfiguredFeature<?, ?> JUNGLE_PALM_TREES;
	public static ConfiguredFeature<?, ?> DENSER_JUNGLE_PALM_TREES;
	public static ConfiguredFeature<?, ?> RARE_DUM_DUM_HEADS;

	// Japanese Maple and Sakura trees
	public static ConfiguredFeature<?, ?> DENSE_JAPANESE_MAPLE_TREES;
	public static ConfiguredFeature<?, ?> DENSE_DARK_JAPANESE_MAPLE_TREES;
	public static ConfiguredFeature<?, ?> DENSE_JAPANESE_MAPLE_SHRUBS;
	public static ConfiguredFeature<?, ?> DENSER_SAKURA_TREES;

	// Small cypress trees
	public static ConfiguredFeature<?, ?> DENSEST_CYPRESS_TREES;

	// Rainbow Rainforest trees
	public static ConfiguredFeature<?, ?> DENSER_RAINBOW_EUCALYPTUS_TREES;
	public static ConfiguredFeature<?, ?> DENSE_RUBBER_TREES;

	// Cypress Swamp trees
	public static ConfiguredFeature<?, ?> MEGA_CYPRESS_TREES;
	public static ConfiguredFeature<?, ?> SPARSE_WILLOW_TREES;

	// Outback trees and shrubs
	public static ConfiguredFeature<?, ?> OUTBACK_BUSHLAND_TREES;
	public static ConfiguredFeature<?, ?> RARE_YUCCA_PALM_TREES;
	public static ConfiguredFeature<?, ?> ACACIA_DOT_SHRUBS;
	public static ConfiguredFeature<?, ?> OAK_DOT_SHRUBS;

	// Lush desert trees
	public static ConfiguredFeature<?, ?> SAGUARO_CACTUSES;

	// Bryce trees
	public static ConfiguredFeature<?, ?> RARE_BRYCE_TREES;

	public static void init() {
		CATTAILS_WARM = register("cattails_warm", TerrestriaFeatures.CATTAIL.configure(new ProbabilityConfig(0.3F)).repeat(80).decorate(ConfiguredFeatures.Decorators.SQUARE_TOP_SOLID_HEIGHTMAP));

		SPARSE_OAK_SHRUBS = decorateTree("oak_shrubs", 1, TerrestriaConfiguredFeatures.OAK_SHRUB);

		PATCH_LUSH_FERNS = decoratePatch("patch_lush_ferns", 16, ConfiguredFeatures.Configs.TAIGA_GRASS_CONFIG);
		PATCH_VOLCANIC_ISLAND_GRASS = decoratePatch("patch_volcanic_island_grass", 12, new RandomPatchFeatureConfig.Builder(
				new WeightedBlockStateProvider(createStatePoolBuilder()
						.add(Blocks.GRASS.getDefaultState(), 1)
						.add(Blocks.FERN.getDefaultState(), 1)
						.add(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
						.add(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
						.build()), SimpleBlockPlacer.INSTANCE).tries(32).build());

		PATCH_DEAD_GRASS = decoratePatch("patch_dead_grass", 12, new RandomPatchFeatureConfig.Builder(
				new SimpleBlockStateProvider(TerrestriaBlocks.DEAD_GRASS.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(4).build());

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
		SPARSE_CONIFER_TREES = decorateTree("sparse_conifer_trees", 1, Feature.RANDOM_SELECTOR.configure(
				new RandomFeatureConfig(ImmutableList.of(TerrestriaConfiguredFeatures.HEMLOCK_TREE.withChance(0.50F)), TerrestriaConfiguredFeatures.REDWOOD_TREE)));
		HEMLOCK_TREES = decorateTree("hemlock_trees", 2, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
		REDWOOD_TREES = decorateTree("redwood_trees", 2, TerrestriaConfiguredFeatures.REDWOOD_TREE);
		DENSE_HEMLOCK_TREES = decorateTree("dense_hemlock_trees", 4, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
		DENSE_REDWOOD_TREES = decorateTree("dense_redwood_trees", 3, TerrestriaConfiguredFeatures.REDWOOD_TREE);
		DENSEST_HEMLOCK_TREES = decorateTree("densest_hemlock_trees", 8, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		SPARSE_MEGA_HEMLOCK_TREES = decorateTree("sparse_mega_hemlock_trees", 1, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);
		MEGA_REDWOOD_TREES = decorateTree("mega_redwood_trees", 4, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);
		MEGA_HEMLOCK_TREES = decorateTree("mega_hemlock_trees", 4, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);
		DENSEST_MEGA_REDWOOD_TREES = decorateTree("densest_mega_redwood_trees", 7, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);
		DENSEST_MEGA_HEMLOCK_TREES = decorateTree("densest_mega_hemlock_trees", 8, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		DENSE_FANCY_OAK_TREES = decorateTree("dense_fancy_oak_trees", 3, ConfiguredFeatures.FANCY_OAK);
		DENSER_FANCY_OAK_TREES = decorateTree("denser_fancy_oak_trees", 5, ConfiguredFeatures.FANCY_OAK);
		DENSEST_FANCY_OAK_TREES = decorateTree("densest_fancy_oak_trees", 7, ConfiguredFeatures.FANCY_OAK);

		JUNGLE_PALM_TREES = decorateTree("jungle_palm_trees", 2, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE);
		DENSER_JUNGLE_PALM_TREES = decorateTree("denser_jungle_palm_trees", 5, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE);
		RARE_DUM_DUM_HEADS = decorateTree("rare_dum_dum_heads", 0, TerrestriaConfiguredFeatures.DUM_DUM_HEAD);

		DENSE_JAPANESE_MAPLE_TREES = decorateTree("dense_japanese_maple_trees", 3, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_TREE);
		DENSE_DARK_JAPANESE_MAPLE_TREES = decorateTree("dense_dark_japanese_maple_trees", 3, TerrestriaConfiguredFeatures.DARK_JAPANESE_MAPLE_TREE);
		DENSE_JAPANESE_MAPLE_SHRUBS = decorateTree("dense_japanese_maple_shrubs", 3, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_SHRUB);
		DENSER_SAKURA_TREES = decorateTree("denser_sakura_trees", 6, TerrestriaConfiguredFeatures.SAKURA_TREE);

		DENSEST_CYPRESS_TREES = decorateTree("densest_cypress_trees", 9, TerrestriaConfiguredFeatures.CYPRESS_TREE);

		DENSER_RAINBOW_EUCALYPTUS_TREES = decorateTree("denser_rainbow_eucalyptus_trees", 5, 3, TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE);
		DENSE_RUBBER_TREES = decorateTree("dense_rubber_trees", 3, TerrestriaConfiguredFeatures.RUBBER_TREE);

		MEGA_CYPRESS_TREES = decorateTree("mega_cypress_trees", 2, 6, TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE);
		SPARSE_WILLOW_TREES = decorateTree("sparse_willow_trees", 1, TerrestriaConfiguredFeatures.WILLOW_TREE);

		OUTBACK_BUSHLAND_TREES = decorateTree("outback_bushland_trees", 2, Feature.RANDOM_SELECTOR.configure(
				new RandomFeatureConfig(ImmutableList.of(ConfiguredFeatures.ACACIA.withChance(0.95F), TerrestriaConfiguredFeatures.YUCCA_PALM_TREE.withChance(0.75F)), ConfiguredFeatures.FANCY_OAK)));

		RARE_YUCCA_PALM_TREES = decorateTree("yucca_palm_trees", 0, TerrestriaConfiguredFeatures.YUCCA_PALM_TREE);
		ACACIA_DOT_SHRUBS = decorateTree("acacia_dot_shrubs", 2, TerrestriaConfiguredFeatures.ACACIA_DOT_SHRUB);
		OAK_DOT_SHRUBS = decorateTree("oak_dot_shrubs", 2, TerrestriaConfiguredFeatures.OAK_DOT_SHRUB);

		SAGUARO_CACTUSES = decorateTree("saguaro_cactuses", 2, TerrestriaConfiguredFeatures.SAGUARO_CACTUS_FEATURE);

		RARE_BRYCE_TREES = register("rare_bryce_trees", TerrestriaConfiguredFeatures.BRYCE_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(2))));
	}

	private static DataPool.Builder<BlockState> createStatePoolBuilder() {
		return DataPool.builder();
	}

	private static ConfiguredFeature<?, ?> decoratePatch(String name, int count, RandomPatchFeatureConfig config) {
		return register(name, Feature.RANDOM_PATCH.configure(config).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_SPREAD_DOUBLE).repeat(count));
	}

	private static ConfiguredFeature<?, ?> decorateTree(String name, int count, ConfiguredFeature<?, ?> base) {
		return register(name, base.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1))));
	}

	private static ConfiguredFeature<?, ?> decorateTree(String name, int count, int maxWaterDepth, ConfiguredFeature<?, ?> base) {
		return register(name, base
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
				.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(count, 0.1F, 1))
				.decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new SurfaceWaterDepthFilterPlacementModifier(maxWaterDepth)))));
	}

	private static ConfiguredFeature<?, ?> register(String name, ConfiguredFeature<?, ?> decorated) {
		Identifier id = new Identifier(Terrestria.MOD_ID, name);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, id, decorated);

		return decorated;
	}
}
