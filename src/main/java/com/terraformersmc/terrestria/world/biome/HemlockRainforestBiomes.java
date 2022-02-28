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
// import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
// import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

// import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

// public class HemlockRainforestBiomes {
// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.TAIGA)
// 				.temperature(0.6F)
// 				.downfall(0.9F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 					.grassColor(0x60b05a)
// 				)
// 				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, LARGE_FERNS, MINEABLES, ORES, DISKS,
// 						TAIGA_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, SWEET_BERRY_BUSHES_SNOWY, FROZEN_TOP_LAYER)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.PATCH_LUSH_FERNS)
// 				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS)
// 				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL)
// 				.addDefaultSpawnEntries()
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 8, 4, 4))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.FOX, 8, 2, 4))
// 		);

// 		TerrestriaBiomes.HEMLOCK_CLEARING = TerrestriaBiomes.register("hemlock_clearing", template.builder()
// 				.depth(0.95F)
// 				.scale(0.2F)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_MEGA_HEMLOCK_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_HEMLOCK_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.FALLEN_HEMLOCK_LOGS)
// 				.build());

// 		TerrestriaBiomes.HEMLOCK_RAINFOREST = TerrestriaBiomes.register("hemlock_rainforest", template.builder()
// 				.depth(0.95F)
// 				.scale(0.55F)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSEST_MEGA_HEMLOCK_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSEST_HEMLOCK_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_FALLEN_HEMLOCK_LOGS)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_HEMLOCK_TREES)
// 				.playerSpawnFriendly()
// 				.build());
// 	}
// }
