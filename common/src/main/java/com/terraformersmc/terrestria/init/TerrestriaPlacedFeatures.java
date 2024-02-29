package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.helpers.SurfaceLevelFilterPlacementModifier;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

public class TerrestriaPlacedFeatures {
	public static final RegistryKey<PlacedFeature> CATTAILS_WARM = createRegistryKey("cattails_warm");
	public static final RegistryKey<PlacedFeature> SPARSE_OAK_SHRUBS = createRegistryKey("sparse_oak_shrubs");
	public static final RegistryKey<PlacedFeature> PATCH_LUSH_FERNS = createRegistryKey("patch_lush_ferns");
	public static final RegistryKey<PlacedFeature> PATCH_VOLCANIC_ISLAND_GRASS = createRegistryKey("patch_volcanic_island_grass");
	public static final RegistryKey<PlacedFeature> PATCH_DEAD_GRASS = createRegistryKey("patch_dead_grass");
	public static final RegistryKey<PlacedFeature> PATCH_OUTBACK_BUSHLAND_GRASS = createRegistryKey("patch_outback_bushland_grass");
	public static final RegistryKey<PlacedFeature> PATCH_OASIS_VEGETATION = createRegistryKey("patch_oasis_vegetation");
	public static final RegistryKey<PlacedFeature> PATCH_LUSH_DESERT_VEGETATION = createRegistryKey("patch_lush_desert_vegetation");
	public static final RegistryKey<PlacedFeature> SPARSE_FALLEN_HEMLOCK_LOGS = createRegistryKey("sparse_fallen_hemlock_logs");
	public static final RegistryKey<PlacedFeature> SPARSE_FALLEN_REDWOOD_LOGS = createRegistryKey("sparse_fallen_redwood_logs");
	public static final RegistryKey<PlacedFeature> FALLEN_HEMLOCK_LOGS = createRegistryKey("fallen_hemlock_logs");
	public static final RegistryKey<PlacedFeature> FALLEN_REDWOOD_LOGS = createRegistryKey("fallen_redwood_logs");
	public static final RegistryKey<PlacedFeature> DENSE_FALLEN_HEMLOCK_LOGS = createRegistryKey("dense_fallen_hemlock_logs");
	public static final RegistryKey<PlacedFeature> DENSE_FALLEN_REDWOOD_LOGS = createRegistryKey("dense_fallen_redwood_logs");
	public static final RegistryKey<PlacedFeature> SPARSE_SMALL_HEMLOCK_TREES = createRegistryKey("sparse_small_hemlock_trees");
	public static final RegistryKey<PlacedFeature> SPARSE_SMALL_REDWOOD_TREES = createRegistryKey("sparse_small_redwood_trees");
	public static final RegistryKey<PlacedFeature> CALDERA_SMALL_HEMLOCK_TREES = createRegistryKey("caldera_small_hemlock_trees");
	public static final RegistryKey<PlacedFeature> CALDERA_SMALL_REDWOOD_TREES = createRegistryKey("caldera_small_redwood_trees");
	public static final RegistryKey<PlacedFeature> SMALL_HEMLOCK_TREES = createRegistryKey("small_hemlock_trees");
	public static final RegistryKey<PlacedFeature> SMALL_REDWOOD_TREES = createRegistryKey("small_redwood_trees");
	public static final RegistryKey<PlacedFeature> SPARSE_HEMLOCK_TREES = createRegistryKey("sparse_hemlock_trees");
	public static final RegistryKey<PlacedFeature> SPARSE_REDWOOD_TREES = createRegistryKey("sparse_redwood_trees");
	public static final RegistryKey<PlacedFeature> CALDERA_HEMLOCK_TREES = createRegistryKey("caldera_hemlock_trees");
	public static final RegistryKey<PlacedFeature> CALDERA_REDWOOD_TREES = createRegistryKey("caldera_redwood_trees");
	public static final RegistryKey<PlacedFeature> HEMLOCK_TREES = createRegistryKey("hemlock_trees");
	public static final RegistryKey<PlacedFeature> REDWOOD_TREES = createRegistryKey("redwood_trees");
	public static final RegistryKey<PlacedFeature> DENSE_HEMLOCK_TREES = createRegistryKey("dense_hemlock_trees");
	public static final RegistryKey<PlacedFeature> DENSE_REDWOOD_TREES = createRegistryKey("dense_redwood_trees");
	public static final RegistryKey<PlacedFeature> DENSEST_HEMLOCK_TREES = createRegistryKey("densest_hemlock_trees");
	public static final RegistryKey<PlacedFeature> DENSEST_REDWOOD_TREES = createRegistryKey("densest_redwood_trees");
	public static final RegistryKey<PlacedFeature> SPARSE_MEGA_HEMLOCK_TREES = createRegistryKey("sparse_mega_hemlock_trees");
	public static final RegistryKey<PlacedFeature> SPARSE_MEGA_REDWOOD_TREES = createRegistryKey("sparse_mega_redwood_trees");
	public static final RegistryKey<PlacedFeature> MEGA_HEMLOCK_TREES = createRegistryKey("mega_hemlock_trees");
	public static final RegistryKey<PlacedFeature> MEGA_REDWOOD_TREES = createRegistryKey("mega_redwood_trees");
	public static final RegistryKey<PlacedFeature> DENSEST_MEGA_HEMLOCK_TREES = createRegistryKey("densest_mega_hemlock_trees");
	public static final RegistryKey<PlacedFeature> DENSEST_MEGA_REDWOOD_TREES = createRegistryKey("densest_mega_redwood_trees");
	public static final RegistryKey<PlacedFeature> DENSE_FANCY_OAK_TREES = createRegistryKey("dense_fancy_oak_trees");
	public static final RegistryKey<PlacedFeature> DENSER_FANCY_OAK_TREES = createRegistryKey("denser_fancy_oak_trees");
	public static final RegistryKey<PlacedFeature> DENSEST_FANCY_OAK_TREES = createRegistryKey("densest_fancy_oak_trees");
	public static final RegistryKey<PlacedFeature> JUNGLE_PALM_TREES = createRegistryKey("jungle_palm_trees");
	public static final RegistryKey<PlacedFeature> DENSER_JUNGLE_PALM_TREES = createRegistryKey("denser_jungle_palm_trees");
	public static final RegistryKey<PlacedFeature> RARE_DUM_DUM_HEADS = createRegistryKey("rare_dum_dum_heads");
	public static final RegistryKey<PlacedFeature> DENSE_JAPANESE_MAPLE_TREES = createRegistryKey("dense_japanese_maple_trees");
	public static final RegistryKey<PlacedFeature> DENSE_DARK_JAPANESE_MAPLE_TREES = createRegistryKey("dense_dark_japanese_maple_trees");
	public static final RegistryKey<PlacedFeature> DENSE_JAPANESE_MAPLE_SHRUBS = createRegistryKey("dense_japanese_maple_shrubs");
	public static final RegistryKey<PlacedFeature> DENSER_SAKURA_TREES = createRegistryKey("denser_sakura_trees");
	public static final RegistryKey<PlacedFeature> DENSEST_CYPRESS_TREES = createRegistryKey("densest_cypress_trees");
	public static final RegistryKey<PlacedFeature> DENSER_RAINBOW_EUCALYPTUS_TREES = createRegistryKey("denser_rainbow_eucalyptus_trees");
	public static final RegistryKey<PlacedFeature> DENSE_RUBBER_TREES = createRegistryKey("dense_rubber_trees");
	public static final RegistryKey<PlacedFeature> MEGA_CYPRESS_TREES = createRegistryKey("mega_cypress_trees");
	public static final RegistryKey<PlacedFeature> SPARSE_WILLOW_TREES = createRegistryKey("sparse_willow_trees");
	public static final RegistryKey<PlacedFeature> OUTBACK_YUCCA_PALM = createRegistryKey("outback_yucca_palm");
	public static final RegistryKey<PlacedFeature> OUTBACK_BUSHLAND_TREES = createRegistryKey("outback_bushland_trees");
	public static final RegistryKey<PlacedFeature> RARE_YUCCA_PALM_TREES = createRegistryKey("yucca_palm_trees");
	public static final RegistryKey<PlacedFeature> ACACIA_DOT_SHRUBS = createRegistryKey("acacia_dot_shrubs");
	public static final RegistryKey<PlacedFeature> OAK_DOT_SHRUBS = createRegistryKey("oak_dot_shrubs");
	public static final RegistryKey<PlacedFeature> SAGUARO_CACTUSES = createRegistryKey("saguaro_cactuses");
	public static final RegistryKey<PlacedFeature> RARE_BRYCE_TREES = createRegistryKey("rare_bryce_trees");

	public static void bootstrap(Registerable<PlacedFeature> registerable) {
		RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatures = registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

		final BlockPredicate ON_DIRT = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.DIRT);
		final BlockPredicate ON_SAND = BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), BlockTags.SAND);
		final BlockPredicate ON_DIRT_OR_SAND = BlockPredicate.eitherOf(ON_DIRT, ON_SAND);

		TerrestriaRegistry.register(registerable, CATTAILS_WARM, TerrestriaConfiguredFeatures.CATTAIL,
				CountPlacementModifier.of(80),
				SquarePlacementModifier.of(),
				PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, SPARSE_OAK_SHRUBS, TerrestriaConfiguredFeatures.OAK_SHRUB,
				PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, PATCH_LUSH_FERNS, VegetationConfiguredFeatures.PATCH_TAIGA_GRASS,
				CountPlacementModifier.of(16),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, PATCH_VOLCANIC_ISLAND_GRASS, TerrestriaConfiguredFeatures.PATCH_VOLCANIC_ISLAND_GRASS,
				CountPlacementModifier.of(12),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, PATCH_DEAD_GRASS, TerrestriaConfiguredFeatures.PATCH_DEAD_GRASS,
				CountPlacementModifier.of(12),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, PATCH_OUTBACK_BUSHLAND_GRASS, TerrestriaConfiguredFeatures.PATCH_OUTBACK_BUSHLAND_GRASS,
				CountPlacementModifier.of(12),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, PATCH_OASIS_VEGETATION, TerrestriaConfiguredFeatures.PATCH_OASIS_VEGETATION,
				CountPlacementModifier.of(6),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, PATCH_LUSH_DESERT_VEGETATION, TerrestriaConfiguredFeatures.PATCH_LUSH_DESERT_VEGETATION,
				CountPlacementModifier.of(4),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		registerTreeFeature(registerable, SPARSE_FALLEN_HEMLOCK_LOGS, 1, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);

		registerTreeFeature(registerable, SPARSE_FALLEN_REDWOOD_LOGS, 1, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		registerTreeFeature(registerable, FALLEN_HEMLOCK_LOGS, 2, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);

		registerTreeFeature(registerable, FALLEN_REDWOOD_LOGS, 2, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		registerTreeFeature(registerable, DENSE_FALLEN_HEMLOCK_LOGS, 4, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_HEMLOCK_LOG);

		registerTreeFeature(registerable, DENSE_FALLEN_REDWOOD_LOGS, 4, ON_DIRT, TerrestriaConfiguredFeatures.FALLEN_REDWOOD_LOG);

		registerTreeFeature(registerable, SPARSE_SMALL_HEMLOCK_TREES, 1, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);

		registerTreeFeature(registerable, SPARSE_SMALL_REDWOOD_TREES, 1, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);

		TerrestriaRegistry.register(registerable, CALDERA_SMALL_HEMLOCK_TREES, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE,
				PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 80, 320),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, CALDERA_SMALL_REDWOOD_TREES, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE,
				PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 80, 320),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		registerTreeFeature(registerable, SMALL_HEMLOCK_TREES, 2, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_HEMLOCK_TREE);

		registerTreeFeature(registerable, SMALL_REDWOOD_TREES, 2, ON_DIRT, TerrestriaConfiguredFeatures.SMALL_REDWOOD_TREE);

		registerTreeFeature(registerable, SPARSE_HEMLOCK_TREES, 1, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(registerable, SPARSE_REDWOOD_TREES, 1, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);

		TerrestriaRegistry.register(registerable, CALDERA_HEMLOCK_TREES, TerrestriaConfiguredFeatures.HEMLOCK_TREE,
				PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 64, 100),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, CALDERA_REDWOOD_TREES, TerrestriaConfiguredFeatures.REDWOOD_TREE,
				PlacedFeatures.createCountExtraModifier(1, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 64, 100),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT),
				BiomePlacementModifier.of());

		registerTreeFeature(registerable, HEMLOCK_TREES, 2, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(registerable, REDWOOD_TREES, 2, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);

		registerTreeFeature(registerable, DENSE_HEMLOCK_TREES, 4, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(registerable, DENSE_REDWOOD_TREES, 3, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);

		registerTreeFeature(registerable, DENSEST_HEMLOCK_TREES, 8, ON_DIRT, TerrestriaConfiguredFeatures.HEMLOCK_TREE);

		registerTreeFeature(registerable, DENSEST_REDWOOD_TREES, 6, ON_DIRT, TerrestriaConfiguredFeatures.REDWOOD_TREE);

		registerTreeFeature(registerable, SPARSE_MEGA_HEMLOCK_TREES, 1, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		registerTreeFeature(registerable, SPARSE_MEGA_REDWOOD_TREES, 1, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);

		registerTreeFeature(registerable, MEGA_HEMLOCK_TREES, 4, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		registerTreeFeature(registerable, MEGA_REDWOOD_TREES, 4, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);

		registerTreeFeature(registerable, DENSEST_MEGA_HEMLOCK_TREES, 8, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_HEMLOCK_TREE);

		registerTreeFeature(registerable, DENSEST_MEGA_REDWOOD_TREES, 7, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_REDWOOD_TREE);

		registerTreeFeature(registerable, DENSE_FANCY_OAK_TREES, 3, ON_DIRT, TreeConfiguredFeatures.FANCY_OAK);

		registerTreeFeature(registerable, DENSER_FANCY_OAK_TREES, 5, ON_DIRT, TreeConfiguredFeatures.FANCY_OAK);

		registerTreeFeature(registerable, DENSEST_FANCY_OAK_TREES, 7, ON_DIRT, TreeConfiguredFeatures.FANCY_OAK);

		TerrestriaRegistry.register(registerable, JUNGLE_PALM_TREES, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE,
				PlacedFeatures.createCountExtraModifier(2, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 62, 71),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, DENSER_JUNGLE_PALM_TREES, TerrestriaConfiguredFeatures.JUNGLE_PALM_TREE,
				PlacedFeatures.createCountExtraModifier(5, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 72, 320),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		TerrestriaRegistry.register(registerable, RARE_DUM_DUM_HEADS, TerrestriaConfiguredFeatures.DUM_DUM_HEAD,
				PlacedFeatures.createCountExtraModifier(0, 0.1f, 1),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 62, 64),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());

		registerTreeFeature(registerable, DENSE_JAPANESE_MAPLE_TREES, 3, ON_DIRT, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_TREE);

		registerTreeFeature(registerable, DENSE_DARK_JAPANESE_MAPLE_TREES, 3, ON_DIRT, TerrestriaConfiguredFeatures.DARK_JAPANESE_MAPLE_TREE);

		registerTreeFeature(registerable, DENSE_JAPANESE_MAPLE_SHRUBS, 3, ON_DIRT, TerrestriaConfiguredFeatures.JAPANESE_MAPLE_SHRUB);

		registerTreeFeature(registerable, DENSER_SAKURA_TREES, 6, ON_DIRT, TerrestriaConfiguredFeatures.SAKURA_TREE);

		registerTreeFeature(registerable, DENSEST_CYPRESS_TREES, 9, ON_DIRT, TerrestriaConfiguredFeatures.CYPRESS_TREE);

		registerTreeFeature(registerable, DENSER_RAINBOW_EUCALYPTUS_TREES, 5, 3, ON_DIRT, TerrestriaConfiguredFeatures.RAINBOW_EUCALYPTUS_TREE);

		registerTreeFeature(registerable, DENSE_RUBBER_TREES, 3, ON_DIRT, TerrestriaConfiguredFeatures.RUBBER_TREE);

		registerTreeFeature(registerable, MEGA_CYPRESS_TREES, 2, 6, ON_DIRT, TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE);

		registerTreeFeature(registerable, SPARSE_WILLOW_TREES, 1, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.WILLOW_TREE);

		TerrestriaRegistry.register(registerable, OUTBACK_YUCCA_PALM, TerrestriaConfiguredFeatures.YUCCA_PALM_TREE, PlacedFeatures.wouldSurvive(TerrestriaBlocks.YUCCA_PALM_SAPLING));

		registerTreeFeature(registerable, OUTBACK_BUSHLAND_TREES, 2, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.OUTBACK_BUSHLAND_TREES);

		registerTreeFeature(registerable, RARE_YUCCA_PALM_TREES, 0, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.YUCCA_PALM_TREE);

		registerTreeFeature(registerable, ACACIA_DOT_SHRUBS, 2, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.ACACIA_DOT_SHRUB);

		registerTreeFeature(registerable, OAK_DOT_SHRUBS, 2, ON_DIRT_OR_SAND, TerrestriaConfiguredFeatures.OAK_DOT_SHRUB);

		registerTreeFeature(registerable, SAGUARO_CACTUSES, 2, ON_SAND, TerrestriaConfiguredFeatures.SAGUARO_CACTUS);

		TerrestriaRegistry.register(registerable, RARE_BRYCE_TREES, TerrestriaConfiguredFeatures.BRYCE_TREE,
				RarityFilterPlacementModifier.of(2),
				SquarePlacementModifier.of(),
				SurfaceLevelFilterPlacementModifier.of(Heightmap.Type.WORLD_SURFACE_WG, 80, 320),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(ON_DIRT_OR_SAND),
				BiomePlacementModifier.of());
	}

	private static RegistryKey<PlacedFeature> createRegistryKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Terrestria.MOD_ID, name));
	}

	private static void registerTreeFeature(Registerable<PlacedFeature> registerable, RegistryKey<PlacedFeature> key, int count, BlockPredicate predicate, RegistryKey<ConfiguredFeature<?, ?>> feature) {
		TerrestriaRegistry.register(registerable, key, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
				BlockFilterPlacementModifier.of(predicate),
				BiomePlacementModifier.of());
	}

	private static void registerTreeFeature(Registerable<PlacedFeature> registerable, RegistryKey<PlacedFeature> key, int count, int maxWaterDepth, BlockPredicate predicate, RegistryKey<ConfiguredFeature<?, ?>> feature) {
		TerrestriaRegistry.register(registerable, key, feature,
				PlacedFeatures.createCountExtraModifier(count, 0.1f, 1),
				SquarePlacementModifier.of(),
				PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
				SurfaceWaterDepthFilterPlacementModifier.of(maxWaterDepth),
				BlockFilterPlacementModifier.of(predicate),
				BiomePlacementModifier.of());
	}
}
