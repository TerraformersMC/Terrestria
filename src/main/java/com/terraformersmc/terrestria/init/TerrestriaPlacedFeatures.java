package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.helpers.SurfaceLevelFilterPlacementModifier;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

public class TerrestriaPlacedFeatures {
	public static final ResourceKey<PlacedFeature> CATTAILS_WARM = resourceKey("cattails_warm");
	public static final ResourceKey<PlacedFeature> SPARSE_OAK_SHRUBS = resourceKey("sparse_oak_shrubs");
	public static final ResourceKey<PlacedFeature> PATCH_LUSH_FERNS = resourceKey("patch_lush_ferns");
	public static final ResourceKey<PlacedFeature> PATCH_VOLCANIC_ISLAND_GRASS = resourceKey("patch_volcanic_island_grass");
	public static final ResourceKey<PlacedFeature> PATCH_DEAD_GRASS = resourceKey("patch_dead_grass");
	public static final ResourceKey<PlacedFeature> PATCH_OUTBACK_BUSHLAND_GRASS = resourceKey("patch_outback_bushland_grass");
	public static final ResourceKey<PlacedFeature> PATCH_OASIS_VEGETATION = resourceKey("patch_oasis_vegetation");
	public static final ResourceKey<PlacedFeature> PATCH_LUSH_DESERT_VEGETATION = resourceKey("patch_lush_desert_vegetation");
	public static final ResourceKey<PlacedFeature> SPARSE_FALLEN_HEMLOCK_LOGS = resourceKey("sparse_fallen_hemlock_logs");
	public static final ResourceKey<PlacedFeature> SPARSE_FALLEN_REDWOOD_LOGS = resourceKey("sparse_fallen_redwood_logs");
	public static final ResourceKey<PlacedFeature> FALLEN_HEMLOCK_LOGS = resourceKey("fallen_hemlock_logs");
	public static final ResourceKey<PlacedFeature> FALLEN_REDWOOD_LOGS = resourceKey("fallen_redwood_logs");
	public static final ResourceKey<PlacedFeature> DENSE_FALLEN_HEMLOCK_LOGS = resourceKey("dense_fallen_hemlock_logs");
	public static final ResourceKey<PlacedFeature> DENSE_FALLEN_REDWOOD_LOGS = resourceKey("dense_fallen_redwood_logs");
	public static final ResourceKey<PlacedFeature> SPARSE_SMALL_HEMLOCK_TREES = resourceKey("sparse_small_hemlock_trees");
	public static final ResourceKey<PlacedFeature> SPARSE_SMALL_REDWOOD_TREES = resourceKey("sparse_small_redwood_trees");
	public static final ResourceKey<PlacedFeature> CALDERA_SMALL_HEMLOCK_TREES = resourceKey("caldera_small_hemlock_trees");
	public static final ResourceKey<PlacedFeature> CALDERA_SMALL_REDWOOD_TREES = resourceKey("caldera_small_redwood_trees");
	public static final ResourceKey<PlacedFeature> SMALL_HEMLOCK_TREES = resourceKey("small_hemlock_trees");
	public static final ResourceKey<PlacedFeature> SMALL_REDWOOD_TREES = resourceKey("small_redwood_trees");
	public static final ResourceKey<PlacedFeature> SPARSE_HEMLOCK_TREES = resourceKey("sparse_hemlock_trees");
	public static final ResourceKey<PlacedFeature> SPARSE_REDWOOD_TREES = resourceKey("sparse_redwood_trees");
	public static final ResourceKey<PlacedFeature> CALDERA_HEMLOCK_TREES = resourceKey("caldera_hemlock_trees");
	public static final ResourceKey<PlacedFeature> CALDERA_REDWOOD_TREES = resourceKey("caldera_redwood_trees");
	public static final ResourceKey<PlacedFeature> HEMLOCK_TREES = resourceKey("hemlock_trees");
	public static final ResourceKey<PlacedFeature> REDWOOD_TREES = resourceKey("redwood_trees");
	public static final ResourceKey<PlacedFeature> DENSE_HEMLOCK_TREES = resourceKey("dense_hemlock_trees");
	public static final ResourceKey<PlacedFeature> DENSE_REDWOOD_TREES = resourceKey("dense_redwood_trees");
	public static final ResourceKey<PlacedFeature> DENSEST_HEMLOCK_TREES = resourceKey("densest_hemlock_trees");
	public static final ResourceKey<PlacedFeature> DENSEST_REDWOOD_TREES = resourceKey("densest_redwood_trees");
	public static final ResourceKey<PlacedFeature> SPARSE_MEGA_HEMLOCK_TREES = resourceKey("sparse_mega_hemlock_trees");
	public static final ResourceKey<PlacedFeature> SPARSE_MEGA_REDWOOD_TREES = resourceKey("sparse_mega_redwood_trees");
	public static final ResourceKey<PlacedFeature> MEGA_HEMLOCK_TREES = resourceKey("mega_hemlock_trees");
	public static final ResourceKey<PlacedFeature> MEGA_REDWOOD_TREES = resourceKey("mega_redwood_trees");
	public static final ResourceKey<PlacedFeature> DENSEST_MEGA_HEMLOCK_TREES = resourceKey("densest_mega_hemlock_trees");
	public static final ResourceKey<PlacedFeature> DENSEST_MEGA_REDWOOD_TREES = resourceKey("densest_mega_redwood_trees");
	public static final ResourceKey<PlacedFeature> DENSE_FANCY_OAK_TREES = resourceKey("dense_fancy_oak_trees");
	public static final ResourceKey<PlacedFeature> DENSER_FANCY_OAK_TREES = resourceKey("denser_fancy_oak_trees");
	public static final ResourceKey<PlacedFeature> DENSEST_FANCY_OAK_TREES = resourceKey("densest_fancy_oak_trees");
	public static final ResourceKey<PlacedFeature> JUNGLE_PALM_TREES = resourceKey("jungle_palm_trees");
	public static final ResourceKey<PlacedFeature> DENSER_JUNGLE_PALM_TREES = resourceKey("denser_jungle_palm_trees");
	public static final ResourceKey<PlacedFeature> RARE_DUM_DUM_HEADS = resourceKey("rare_dum_dum_heads");
	public static final ResourceKey<PlacedFeature> DENSE_JAPANESE_MAPLE_TREES = resourceKey("dense_japanese_maple_trees");
	public static final ResourceKey<PlacedFeature> DENSE_DARK_JAPANESE_MAPLE_TREES = resourceKey("dense_dark_japanese_maple_trees");
	public static final ResourceKey<PlacedFeature> DENSE_JAPANESE_MAPLE_SHRUBS = resourceKey("dense_japanese_maple_shrubs");
	public static final ResourceKey<PlacedFeature> DENSER_SAKURA_TREES = resourceKey("denser_sakura_trees");
	public static final ResourceKey<PlacedFeature> DENSEST_CYPRESS_TREES = resourceKey("densest_cypress_trees");
	public static final ResourceKey<PlacedFeature> DENSER_RAINBOW_EUCALYPTUS_TREES = resourceKey("denser_rainbow_eucalyptus_trees");
	public static final ResourceKey<PlacedFeature> DENSE_RUBBER_TREES = resourceKey("dense_rubber_trees");
	public static final ResourceKey<PlacedFeature> MEGA_CYPRESS_TREES = resourceKey("mega_cypress_trees");
	public static final ResourceKey<PlacedFeature> SPARSE_WILLOW_TREES = resourceKey("sparse_willow_trees");
	public static final ResourceKey<PlacedFeature> OUTBACK_YUCCA_PALM = resourceKey("outback_yucca_palm");
	public static final ResourceKey<PlacedFeature> OUTBACK_BUSHLAND_TREES = resourceKey("outback_bushland_trees");
	public static final ResourceKey<PlacedFeature> RARE_YUCCA_PALM_TREES = resourceKey("yucca_palm_trees");
	public static final ResourceKey<PlacedFeature> ACACIA_DOT_SHRUBS = resourceKey("acacia_dot_shrubs");
	public static final ResourceKey<PlacedFeature> OAK_DOT_SHRUBS = resourceKey("oak_dot_shrubs");
	public static final ResourceKey<PlacedFeature> SAGUARO_CACTUSES = resourceKey("saguaro_cactuses");
	public static final ResourceKey<PlacedFeature> RARE_BRYCE_TREES = resourceKey("rare_bryce_trees");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		// For things without saplings:
		final BlockPredicate ON_FERTILE_SOIL = BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(), BlockTags.SUPPORTS_VEGETATION);
		final BlockPredicate ON_INFERTILE_SOIL = BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(), BlockTags.SUPPORTS_DRY_VEGETATION);
		final BlockPredicate ON_ANY_SOIL = BlockPredicate.anyOf(ON_FERTILE_SOIL, ON_INFERTILE_SOIL);

		TerrestriaRegistry.register(context, CATTAILS_WARM, TerrestriaConfiguredFeatures.CATTAIL,
				CountPlacement.of(80),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
				BlockPredicateFilter.forPredicate(ON_ANY_SOIL),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, SPARSE_OAK_SHRUBS, TerrestriaConfiguredFeatures.OAK_SHRUB,
				PlacementUtils.countExtra(1, 0.1f, 1),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, PATCH_LUSH_FERNS, VegetationFeatures.TAIGA_GRASS,
				CountPlacement.of(16),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, ON_FERTILE_SOIL)),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, PATCH_VOLCANIC_ISLAND_GRASS, TerrestriaConfiguredFeatures.PATCH_VOLCANIC_ISLAND_GRASS,
				Util.copyAndAdd(
					VegetationPlacements.worldSurfaceSquaredWithCount(12),
					CountPlacement.of(32),
					RandomOffsetPlacement.ofTriangle(15, 3),
					BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE)
				));

		TerrestriaRegistry.register(context, PATCH_DEAD_GRASS, TerrestriaConfiguredFeatures.PATCH_DEAD_GRASS,
				Util.copyAndAdd(
					VegetationPlacements.worldSurfaceSquaredWithCount(12),
					CountPlacement.of(4),
					RandomOffsetPlacement.ofTriangle(15, 3),
					BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, ON_INFERTILE_SOIL))
				));

		TerrestriaRegistry.register(context, PATCH_OUTBACK_BUSHLAND_GRASS, TerrestriaConfiguredFeatures.PATCH_OUTBACK_BUSHLAND_GRASS,
				Util.copyAndAdd(
					VegetationPlacements.worldSurfaceSquaredWithCount(12),
					CountPlacement.of(4),
					RandomOffsetPlacement.ofTriangle(15, 3),
					BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, ON_ANY_SOIL))
				));

		TerrestriaRegistry.register(context, PATCH_OASIS_VEGETATION, TerrestriaConfiguredFeatures.PATCH_OASIS_VEGETATION,
				Util.copyAndAdd(
					VegetationPlacements.worldSurfaceSquaredWithCount(6),
					CountPlacement.of(32),
					RandomOffsetPlacement.ofTriangle(15, 3),
					BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, ON_FERTILE_SOIL))
				));

		TerrestriaRegistry.register(context, PATCH_LUSH_DESERT_VEGETATION, TerrestriaConfiguredFeatures.PATCH_LUSH_DESERT_VEGETATION,
				Util.copyAndAdd(
					VegetationPlacements.worldSurfaceSquaredWithCount(4),
					CountPlacement.of(32),
					RandomOffsetPlacement.ofTriangle(15, 3),
					BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, ON_ANY_SOIL))
				));

		registerTreeFeature(context, SPARSE_FALLEN_HEMLOCK_LOGS, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);

		registerTreeFeature(context, SPARSE_FALLEN_REDWOOD_LOGS, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		registerTreeFeature(context, FALLEN_HEMLOCK_LOGS, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);

		registerTreeFeature(context, FALLEN_REDWOOD_LOGS, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		registerTreeFeature(context, DENSE_FALLEN_HEMLOCK_LOGS, 4, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);

		registerTreeFeature(context, DENSE_FALLEN_REDWOOD_LOGS, 4, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		registerTreeFeature(context, SPARSE_SMALL_HEMLOCK_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);

		registerTreeFeature(context, SPARSE_SMALL_REDWOOD_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);

		TerrestriaRegistry.register(context, CALDERA_SMALL_HEMLOCK_TREES, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE,
				PlacementUtils.countExtra(1, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 80, 320),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, CALDERA_SMALL_REDWOOD_TREES, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE,
				PlacementUtils.countExtra(1, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 80, 320),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING),
				BiomeFilter.biome());

		registerTreeFeature(context, SMALL_HEMLOCK_TREES, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);

		registerTreeFeature(context, SMALL_REDWOOD_TREES, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);

		registerTreeFeature(context, SPARSE_HEMLOCK_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(context, SPARSE_REDWOOD_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.REDWOOD_TREE);

		TerrestriaRegistry.register(context, CALDERA_HEMLOCK_TREES, TerrestriaConfiguredFeatures.HEMLOCK_TREE,
				PlacementUtils.countExtra(1, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 64, 100),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, CALDERA_REDWOOD_TREES, TerrestriaConfiguredFeatures.REDWOOD_TREE,
				PlacementUtils.countExtra(1, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 64, 100),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING),
				BiomeFilter.biome());

		registerTreeFeature(context, HEMLOCK_TREES, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(context, REDWOOD_TREES, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.REDWOOD_TREE);

		registerTreeFeature(context, DENSE_HEMLOCK_TREES, 4, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(context, DENSE_REDWOOD_TREES, 3, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.REDWOOD_TREE);

		registerTreeFeature(context, DENSEST_HEMLOCK_TREES, 8, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(context, DENSEST_REDWOOD_TREES, 6, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.REDWOOD_TREE);

		registerTreeFeature(context, SPARSE_MEGA_HEMLOCK_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		registerTreeFeature(context, SPARSE_MEGA_REDWOOD_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);

		registerTreeFeature(context, MEGA_HEMLOCK_TREES, 4, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		registerTreeFeature(context, MEGA_REDWOOD_TREES, 4, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);

		registerTreeFeature(context, DENSEST_MEGA_HEMLOCK_TREES, 8, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.HEMLOCK_SAPLING), TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		registerTreeFeature(context, DENSEST_MEGA_REDWOOD_TREES, 7, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.REDWOOD_SAPLING), TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);

		registerTreeFeature(context, DENSE_FANCY_OAK_TREES, 3, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), TreeFeatures.FANCY_OAK);

		registerTreeFeature(context, DENSER_FANCY_OAK_TREES, 5, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), TreeFeatures.FANCY_OAK);

		registerTreeFeature(context, DENSEST_FANCY_OAK_TREES, 7, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), TreeFeatures.FANCY_OAK_LEAF_LITTER);

		TerrestriaRegistry.register(context, JUNGLE_PALM_TREES, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE,
				PlacementUtils.countExtra(2, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 62, 71),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.JUNGLE_PALM_SAPLING),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, DENSER_JUNGLE_PALM_TREES, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE,
				PlacementUtils.countExtra(5, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 72, 320),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.JUNGLE_PALM_SAPLING),
				BiomeFilter.biome());

		TerrestriaRegistry.register(context, RARE_DUM_DUM_HEADS, TerrestriaConfiguredFeatures.DUM_DUM_HEAD,
				PlacementUtils.countExtra(0, 0.1f, 1),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 62, 64),
				PlacementUtils.HEIGHTMAP,
				BlockPredicateFilter.forPredicate(ON_ANY_SOIL),
				BiomeFilter.biome());

		registerTreeFeature(context, DENSE_JAPANESE_MAPLE_TREES, 3, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING), TerrestriaConfiguredFeatures.JAPANESE_MAPLE_TREE);

		registerTreeFeature(context, DENSE_DARK_JAPANESE_MAPLE_TREES, 3, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING), TerrestriaConfiguredFeatures.DARK_JAPANESE_MAPLE_TREE);

		registerTreeFeature(context, DENSE_JAPANESE_MAPLE_SHRUBS, 3, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING), TerrestriaConfiguredFeatures.JAPANESE_MAPLE_SHRUB);

		registerTreeFeature(context, DENSER_SAKURA_TREES, 6, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.SAKURA_SAPLING), TerrestriaConfiguredFeatures.SAKURA_TREE);

		registerTreeFeature(context, DENSEST_CYPRESS_TREES, 9, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.CYPRESS_SAPLING), TerrestriaConfiguredFeatures.CYPRESS_TREE);

		registerTreeFeature(context, DENSER_RAINBOW_EUCALYPTUS_TREES, 5, 3, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING), TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE);

		registerTreeFeature(context, DENSE_RUBBER_TREES, 3, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.RUBBER_SAPLING), TerrestriaConfiguredFeatures.RUBBER_TREE);

		registerTreeFeature(context, MEGA_CYPRESS_TREES, 2, 6, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.CYPRESS_SAPLING), TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE);

		registerTreeFeature(context, SPARSE_WILLOW_TREES, 1, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.WILLOW_SAPLING), TerrestriaConfiguredFeatures.WILLOW_TREE);

		TerrestriaRegistry.register(context, OUTBACK_YUCCA_PALM, TerrestriaConfiguredFeatures.YUCCA_PALM_TREE, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.YUCCA_PALM_SAPLING));

		registerTreeFeature(context, OUTBACK_BUSHLAND_TREES, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.YUCCA_PALM_SAPLING), TerrestriaConfiguredFeatures.OUTBACK_BUSHLAND_TREES);

		registerTreeFeature(context, RARE_YUCCA_PALM_TREES, 0, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.YUCCA_PALM_SAPLING), TerrestriaConfiguredFeatures.YUCCA_PALM_TREE);

		registerTreeFeature(context, ACACIA_DOT_SHRUBS, 2, BlockPredicateFilter.forPredicate(ON_ANY_SOIL), TerrestriaConfiguredFeatures.ACACIA_DOT_SHRUB);

		registerTreeFeature(context, OAK_DOT_SHRUBS, 2, BlockPredicateFilter.forPredicate(ON_ANY_SOIL), TerrestriaConfiguredFeatures.OAK_DOT_SHRUB);

		registerTreeFeature(context, SAGUARO_CACTUSES, 2, PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING), TerrestriaConfiguredFeatures.SAGUARO_CACTUS);

		TerrestriaRegistry.register(context, RARE_BRYCE_TREES, TerrestriaConfiguredFeatures.BRYCE_TREE,
				RarityFilter.onAverageOnceEvery(2),
				InSquarePlacement.spread(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Types.WORLD_SURFACE_WG, 80, 320),
				PlacementUtils.HEIGHTMAP,
				PlacementUtils.filteredByBlockSurvival(TerrestriaBlocks.BRYCE_SAPLING),
				BiomeFilter.biome());
	}

	private static void registerTreeFeature(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, int count, BlockPredicateFilter blockPredicateFilter, ResourceKey<ConfiguredFeature<?, ?>> feature) {
		TerrestriaRegistry.register(registerable, key, feature,
				PlacementUtils.countExtra(count, 0.1f, 1),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				blockPredicateFilter,
				BiomeFilter.biome());
	}

	private static void registerTreeFeature(BootstrapContext<PlacedFeature> registerable, ResourceKey<PlacedFeature> key, int count, int maxWaterDepth, BlockPredicateFilter blockPredicateFilter, ResourceKey<ConfiguredFeature<?, ?>> feature) {
		TerrestriaRegistry.register(registerable, key, feature,
				PlacementUtils.countExtra(count, 0.1f, 1),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
				SurfaceWaterDepthFilter.forMaxDepth(maxWaterDepth),
				blockPredicateFilter,
				BiomeFilter.biome());
	}

	private static ResourceKey<PlacedFeature> resourceKey(String path) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}
}
