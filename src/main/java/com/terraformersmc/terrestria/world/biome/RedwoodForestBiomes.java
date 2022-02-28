package com.terraformersmc.terrestria.world.biome;

// TODO: Update to 1.18
// import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
// import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.world.gen.feature.TerrestriaTreeDecoratedFeatures;

// import net.minecraft.entity.EntityType;
// import net.minecraft.world.biome.Biome;
// import net.minecraft.world.biome.SpawnSettings;
// import net.minecraft.world.gen.GenerationStep;
// import net.minecraft.world.gen.feature.ConfiguredFeatures;
// import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
// import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

// import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

// public class RedwoodForestBiomes {
// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 				.configureSurfaceBuilder(SurfaceBuilder.GIANT_TREE_TAIGA, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
// 				.temperature(0.9F)
// 				.downfall(0.9F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 				)
// 				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
// 						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY,
// 						FROZEN_TOP_LAYER)
// 				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_MOUNTAIN)
// 				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS)
// 				.addDefaultSpawnEntries()
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4))
// 		);

// 		TerrestriaBiomes.REDWOOD_FOREST = TerrestriaBiomes.register("redwood_forest", template.builder()
// 				.depth(1.2F)
// 				.scale(0.3F)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSEST_MEGA_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_FALLEN_REDWOOD_LOGS)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_GRASS_TAIGA)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_LARGE_FERN)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_TALL_GRASS)
// 				.playerSpawnFriendly()
// 				.build());

// 		TerrestriaBiomes.REDWOOD_FOREST_EDGE = TerrestriaBiomes.register("redwood_forest_edge", template.builder()
// 				.depth(0.5F)
// 				.scale(0.3F)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_LARGE_FERN)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_TALL_GRASS)
// 				.build());

// 		TerrestriaBiomes.REDWOOD_CLEARING = TerrestriaBiomes.register("redwood_clearing", template.builder()
// 				.depth(1.2F)
// 				.scale(0.3F)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.FALLEN_REDWOOD_LOGS)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SMALL_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_LARGE_FERN)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_TALL_GRASS)
// 				.build());
// 	}
// }
