package com.terraformersmc.terrestria.init;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.helpers.SurfaceLevelFilterPlacementModifier;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.ArrayList;
import java.util.List;

public class TerrestriaPlacedFeatures {
	private static final BlockPredicate ON_DIRT = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT);
	private static final BlockPredicate ON_SAND = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.SAND);
	private static final BlockPredicate ON_DIRT_OR_SAND = BlockPredicate.eitherOf(ON_DIRT, ON_SAND);

	// Terrestria Decorated Features

	public static final RegistryEntry<PlacedFeature> CATTAILS_WARM = createPlacedFeature("cattails_warm", TerrestriaConfiguredFeatures.CATTAIL,
			CountPlacementModifier.of(80),
			SquarePlacementModifier.of(),
			PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));

	public static final RegistryEntry<PlacedFeature> SPARSE_OAK_SHRUBS = createPlacedFeatureWithoutBiomeFilter("oak_shrubs", TerrestriaConfiguredFeatures.OAK_SHRUB,
			PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));


	public static final RegistryEntry<PlacedFeature> PATCH_LUSH_FERNS = createPlacedFeature("patch_lush_ferns", VegetationConfiguredFeatures.PATCH_TAIGA_GRASS,
			CountPlacementModifier.of(16),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));

	private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_VOLCANIC_ISLAND_GRASS_CONFIGURED = TerrestriaConfiguredFeatures.register("patch_volcanic_island_grass", Feature.RANDOM_PATCH,
			new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
					new WeightedBlockStateProvider(createStatePoolBuilder()
							.add(Blocks.GRASS.getDefaultState(), 1)
							.add(Blocks.FERN.getDefaultState(), 1)
							.add(TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState(), 1)
							.add(TerrestriaBlocks.MONSTERAS.getDefaultState(), 4)
							.build())),
					BlockPredicate.IS_AIR)));
	public static final RegistryEntry<PlacedFeature> PATCH_VOLCANIC_ISLAND_GRASS = createPlacedFeature("patch_volcanic_island_grass", PATCH_VOLCANIC_ISLAND_GRASS_CONFIGURED,
			CountPlacementModifier.of(12),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));

	private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DEAD_GRASS_CONFIGURED = TerrestriaConfiguredFeatures.register("patch_dead_grass", Feature.RANDOM_PATCH,
			new RandomPatchFeatureConfig(4, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
					BlockStateProvider.of(TerrestriaBlocks.DEAD_GRASS.getDefaultState())),
					BlockPredicate.IS_AIR)));
	public static final RegistryEntry<PlacedFeature> PATCH_DEAD_GRASS = createPlacedFeature("patch_dead_grass", PATCH_DEAD_GRASS_CONFIGURED,
			CountPlacementModifier.of(12),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));

	private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_OUTBACK_BUSHLAND_GRASS_CONFIGURED = TerrestriaConfiguredFeatures.register("patch_outback_bushland_grass", Feature.RANDOM_PATCH,
			new RandomPatchFeatureConfig(4, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
							new WeightedBlockStateProvider(createStatePoolBuilder()
									.add(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 3)
									.add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
									.build())),
					BlockPredicate.IS_AIR)));
	public static final RegistryEntry<PlacedFeature> PATCH_OUTBACK_BUSHLAND_GRASS = createPlacedFeature("patch_outback_bushland_grass", PATCH_OUTBACK_BUSHLAND_GRASS_CONFIGURED,
			CountPlacementModifier.of(12),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));

	private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_OASIS_VEGETATION_CONFIGURED = TerrestriaConfiguredFeatures.register("patch_oasis_vegetation", Feature.RANDOM_PATCH,
			new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
							new WeightedBlockStateProvider(createStatePoolBuilder()
									.add(Blocks.FERN.getDefaultState(), 1)
									.add(Blocks.GRASS.getDefaultState(), 2)
									.add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
									.add(TerrestriaBlocks.AGAVE.getDefaultState(), 1)
									.add(TerrestriaBlocks.ALOE_VERA.getDefaultState(), 1)
									.build())),
					BlockPredicate.IS_AIR)));
	public static final RegistryEntry<PlacedFeature> PATCH_OASIS_VEGETATION = createPlacedFeature("patch_oasis_vegetation", PATCH_OASIS_VEGETATION_CONFIGURED,
			CountPlacementModifier.of(6),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));

	private static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_LUSH_DESERT_VEGETATION_CONFIGURED = TerrestriaConfiguredFeatures.register("patch_lush_desert_vegetation", Feature.RANDOM_PATCH,
			new RandomPatchFeatureConfig(32, 15, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
							new WeightedBlockStateProvider(createStatePoolBuilder()
									.add(TerrestriaBlocks.DEAD_GRASS.getDefaultState(), 2)
									.add(Blocks.DEAD_BUSH.getDefaultState(), 1)
									.add(TerrestriaBlocks.TINY_CACTUS.getDefaultState(), 1)
									.build())),
					BlockPredicate.IS_AIR)));
	public static final RegistryEntry<PlacedFeature> PATCH_LUSH_DESERT_VEGETATION = createPlacedFeature("patch_lush_desert_vegetation", PATCH_LUSH_DESERT_VEGETATION_CONFIGURED,
			CountPlacementModifier.of(4),
			SquarePlacementModifier.of(),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));


	public static final RegistryEntry<PlacedFeature> SPARSE_FALLEN_HEMLOCK_LOGS = createPlacedTreeFeature("sparse_fallen_hemlock_logs", 1, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);
	public static final RegistryEntry<PlacedFeature> SPARSE_FALLEN_REDWOOD_LOGS = createPlacedTreeFeature("sparse_fallen_redwood_logs", 1, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);
	public static final RegistryEntry<PlacedFeature> FALLEN_HEMLOCK_LOGS = createPlacedTreeFeature("fallen_hemlock_logs", 2, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);
	public static final RegistryEntry<PlacedFeature> FALLEN_REDWOOD_LOGS = createPlacedTreeFeature("fallen_redwood_logs", 2, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);
	public static final RegistryEntry<PlacedFeature> DENSE_FALLEN_HEMLOCK_LOGS = createPlacedTreeFeature("dense_fallen_hemlock_logs", 4, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);
	public static final RegistryEntry<PlacedFeature> DENSE_FALLEN_REDWOOD_LOGS = createPlacedTreeFeature("dense_fallen_redwood_logs", 4, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);
	public static final RegistryEntry<PlacedFeature> SPARSE_FALLEN_OAK_LOGS = createPlacedTreeFeature("sparse_fallen_oak_logs", 1, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_OAK_LOG);
	public static final RegistryEntry<PlacedFeature> DENSE_FALLEN_OAK_LOGS = createPlacedTreeFeature("dense_fallen_oak_logs", 3, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_OAK_LOG);

	public static final RegistryEntry<PlacedFeature> SPARSE_SMALL_HEMLOCK_TREES = createPlacedTreeFeature("sparse_small_hemlock_trees", 1, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> SPARSE_SMALL_REDWOOD_TREES = createPlacedTreeFeature("sparse_small_redwood_trees", 1, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);
	public static final RegistryEntry<PlacedFeature> CALDERA_SMALL_HEMLOCK_TREES = createPlacedFeature("caldera_small_hemlock_trees",
			TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE,
			PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 80, 320),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));
	public static final RegistryEntry<PlacedFeature> CALDERA_SMALL_REDWOOD_TREES = createPlacedFeature("caldera_small_redwood_trees",
			TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE,
			PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 80, 320),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));

	public static final RegistryEntry<PlacedFeature> SMALL_HEMLOCK_TREES = createPlacedTreeFeature("small_hemlock_trees", 2, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> SMALL_REDWOOD_TREES = createPlacedTreeFeature("small_redwood_trees", 2, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);

	public static final RegistryEntry<PlacedFeature> SPARSE_HEMLOCK_TREES = createPlacedTreeFeature("sparse_hemlock_trees", 1, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> SPARSE_REDWOOD_TREES = createPlacedTreeFeature("sparse_redwood_trees", 1, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);
	public static final RegistryEntry<PlacedFeature> CALDERA_HEMLOCK_TREES = createPlacedFeature("caldera_hemlock_trees",
			TerrestriaConfiguredFeatures.HEMLOCK_TREE,
			PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 64, 100),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));
	public static final RegistryEntry<PlacedFeature> CALDERA_REDWOOD_TREES = createPlacedFeature("caldera_redwood_trees",
			TerrestriaConfiguredFeatures.REDWOOD_TREE,
			PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 64, 100),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT));

	public static final RegistryEntry<PlacedFeature> HEMLOCK_TREES = createPlacedTreeFeature("hemlock_trees", 2, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> REDWOOD_TREES = createPlacedTreeFeature("redwood_trees", 2, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);
	public static final RegistryEntry<PlacedFeature> DENSE_HEMLOCK_TREES = createPlacedTreeFeature("dense_hemlock_trees", 4, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> DENSE_REDWOOD_TREES = createPlacedTreeFeature("dense_redwood_trees", 3, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);
	public static final RegistryEntry<PlacedFeature> DENSEST_HEMLOCK_TREES = createPlacedTreeFeature("densest_hemlock_trees", 8, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

	public static final RegistryEntry<PlacedFeature> SPARSE_MEGA_HEMLOCK_TREES = createPlacedTreeFeature("sparse_mega_hemlock_trees", 1, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> MEGA_REDWOOD_TREES = createPlacedTreeFeature("mega_redwood_trees", 4, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);
	public static final RegistryEntry<PlacedFeature> MEGA_HEMLOCK_TREES = createPlacedTreeFeature("mega_hemlock_trees", 4, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);
	public static final RegistryEntry<PlacedFeature> DENSEST_MEGA_REDWOOD_TREES = createPlacedTreeFeature("densest_mega_redwood_trees", 7, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);
	public static final RegistryEntry<PlacedFeature> DENSEST_MEGA_HEMLOCK_TREES = createPlacedTreeFeature("densest_mega_hemlock_trees", 8, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

	public static final RegistryEntry<PlacedFeature> DENSER_FANCY_OAK_TREES = createPlacedTreeFeature("denser_fancy_oak_trees", 5, ON_DIRT, TreeConfiguredFeatures.FANCY_OAK);

	public static final RegistryEntry<PlacedFeature> SPARSE_DENSE_WOODLANDS_TREES = createPlacedTreeFeature("sparse_dense_woodlands_trees", 1, ON_DIRT, TerrestriaConfiguredFeatures.DENSE_WOODLAND_TREE);
	public static final RegistryEntry<PlacedFeature> DENSE_DENSE_WOODLANDS_TREES = createPlacedTreeFeature("dense_dense_woodlands_trees", 3, ON_DIRT, TerrestriaConfiguredFeatures.DENSE_WOODLAND_TREE);
	public static final RegistryEntry<PlacedFeature> DENSEST_DENSE_WOODLANDS_TREES = createPlacedTreeFeature("densest_dense_woodlands_trees", 7, ON_DIRT, TerrestriaConfiguredFeatures.DENSE_WOODLAND_TREE);

	public static final RegistryEntry<PlacedFeature> JUNGLE_PALM_TREES = createPlacedFeature("jungle_palm_trees",
			TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE,
			PlacedFeatures.createCountExtraModifier(2, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 62, 71),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));
	public static final RegistryEntry<PlacedFeature> DENSER_JUNGLE_PALM_TREES = createPlacedFeature("denser_jungle_palm_trees",
			TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE,
			PlacedFeatures.createCountExtraModifier(5, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 72, 320),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));
	public static final RegistryEntry<PlacedFeature> RARE_DUM_DUM_HEADS = createPlacedFeature("rare_dum_dum_heads",
			TerrestriaConfiguredFeatures.DUM_DUM_HEAD,
			PlacedFeatures.createCountExtraModifier(0, 0.1f, 1),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 62, 64),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));

	public static final RegistryEntry<PlacedFeature> DENSE_JAPANESE_MAPLE_TREES = createPlacedTreeFeature("dense_japanese_maple_trees", 3, ON_DIRT, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_TREE);
	public static final RegistryEntry<PlacedFeature> DENSE_DARK_JAPANESE_MAPLE_TREES = createPlacedTreeFeature("dense_dark_japanese_maple_trees", 3, ON_DIRT, TerrestriaConfiguredFeatures.DARK_JAPANESE_MAPLE_TREE);
	public static final RegistryEntry<PlacedFeature> DENSE_JAPANESE_MAPLE_SHRUBS = createPlacedTreeFeature("dense_japanese_maple_shrubs", 3, ON_DIRT, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_SHRUB);
	public static final RegistryEntry<PlacedFeature> DENSER_SAKURA_TREES = createPlacedTreeFeature("denser_sakura_trees", 6, ON_DIRT, TerrestriaConfiguredFeatures.SAKURA_TREE);

	public static final RegistryEntry<PlacedFeature> DENSEST_CYPRESS_TREES = createPlacedTreeFeature("densest_cypress_trees", 9, ON_DIRT, TerrestriaConfiguredFeatures.CYPRESS_TREE);

	public static final RegistryEntry<PlacedFeature> DENSER_RAINBOW_EUCALYPTUS_TREES = createPlacedTreeFeature("denser_rainbow_eucalyptus_trees", 5, 3, ON_DIRT, TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE);
	public static final RegistryEntry<PlacedFeature> DENSE_RUBBER_TREES = createPlacedTreeFeature("dense_rubber_trees", 3, ON_DIRT, TerrestriaConfiguredFeatures.RUBBER_TREE);

	public static final RegistryEntry<PlacedFeature> MEGA_CYPRESS_TREES = createPlacedTreeFeature("mega_cypress_trees", 2, 6, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE);
	public static final RegistryEntry<PlacedFeature> SPARSE_WILLOW_TREES = createPlacedTreeFeature("sparse_willow_trees", 1, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.WILLOW_TREE);

	private static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> OUTBACK_BUSHLAND_TREES_CONFIGURED = TerrestriaConfiguredFeatures.register("outback_bushland_trees", Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(ImmutableList.of(
					new RandomFeatureEntry(createPlacedFeature("acacia", TreeConfiguredFeatures.ACACIA), 0.95F),
					new RandomFeatureEntry(createPlacedFeature("yucca_palm_tree", TerrestriaConfiguredFeatures.YUCCA_PALM_TREE), 0.75F)
			), createPlacedFeature("fancy_oak", TreeConfiguredFeatures.FANCY_OAK)));
	public static final RegistryEntry<PlacedFeature> OUTBACK_BUSHLAND_TREES = createPlacedTreeFeature("outback_bushland_trees", 2, ON_DIRT_OR_SAND, OUTBACK_BUSHLAND_TREES_CONFIGURED);

	public static final RegistryEntry<PlacedFeature> RARE_YUCCA_PALM_TREES = createPlacedTreeFeature("yucca_palm_trees", 0, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.YUCCA_PALM_TREE);
	public static final RegistryEntry<PlacedFeature> ACACIA_DOT_SHRUBS = createPlacedTreeFeature("acacia_dot_shrubs", 2, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.ACACIA_DOT_SHRUB);
	public static final RegistryEntry<PlacedFeature> OAK_DOT_SHRUBS = createPlacedTreeFeature("oak_dot_shrubs", 2, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.OAK_DOT_SHRUB);

	public static final RegistryEntry<PlacedFeature> SMALL_OAK_SPRUCE_TREES = createPlacedTreeFeature("small_oak_spruce_trees", 2, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_OAK_SPRUCE);

	public static final RegistryEntry<PlacedFeature> SAGUARO_CACTUSES = createPlacedTreeFeature("saguaro_cactuses", 2, ON_SAND, TerrestriaConfiguredFeatures.SAGUARO_CACTUS_FEATURE);

	// Rare bryce trees is a placement used for the Canyon biome and
	// we modify the placement so the trees don't place below y == 80.
	public static final RegistryEntry<PlacedFeature> RARE_BRYCE_TREES = createPlacedFeature("rare_bryce_trees", TerrestriaConfiguredFeatures.BRYCE_TREE,
			RarityFilterPlacementModifier.of(2),
			SquarePlacementModifier.of(),
			SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 80, 320),
			PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
			BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND));


	public static void init() {
	}


	private static DataPool.Builder<BlockState> createStatePoolBuilder() {
		return DataPool.builder();
	}

	public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedTreeFeature(String id, int count, BlockPredicate predicate, RegistryEntry<ConfiguredFeature<FC, ?>> feature) {
		return createPlacedFeature(id, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(predicate));
	}

	public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedTreeFeature(String id, int count, int maxWaterDepth, BlockPredicate predicate, RegistryEntry<ConfiguredFeature<FC, ?>> feature) {
		return createPlacedFeature(id, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
				SurfaceWaterDepthFilterPlacementModifier.of(maxWaterDepth),
				BlockFilterPlacementModifier.of(predicate));
	}

	public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeature(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
		List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
		list.add(BiomePlacementModifier.of());
		return createPlacedFeature(id, feature, list);
	}

	public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeatureWithoutBiomeFilter(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, PlacementModifier... placementModifiers) {
		List<PlacementModifier> list = new ArrayList<>(List.of(placementModifiers));
		return createPlacedFeature(id, feature, list);
	}

	public static <FC extends FeatureConfig> RegistryEntry<PlacedFeature> createPlacedFeature(String id, RegistryEntry<ConfiguredFeature<FC, ?>> feature, List<PlacementModifier> placementModifiers) {
		Identifier realID = new Identifier(Terrestria.MOD_ID, id);
		if (BuiltinRegistries.PLACED_FEATURE.getIds().contains(realID))
			throw new IllegalStateException("Placed Feature ID: \"" + realID.toString() + "\" already exists in the Placed Features registry!");

		return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, realID, new PlacedFeature(RegistryEntry.upcast(feature), List.copyOf(placementModifiers)));
	}
}
