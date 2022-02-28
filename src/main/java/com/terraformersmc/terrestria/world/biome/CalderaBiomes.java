package com.terraformersmc.terrestria.world.biome;

// TODO: Update to 1.18
// import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
// import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
// import net.minecraft.entity.EntityType;
// import net.minecraft.world.biome.Biome;
// import net.minecraft.world.biome.SpawnSettings;
// import net.minecraft.world.gen.GenerationStep;
// import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
// import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import com.terraformersmc.terrestria.world.gen.feature.TerrestriaTreeDecoratedFeatures;

// import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

// public class CalderaBiomes {

// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x54d3c0)
// 					.waterFogColor(0x24a0b0)
// 				)
// 				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
// 						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
// 				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 				.addDefaultSpawnEntries()
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SQUID, 3, 1, 4))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.SALMON, 15, 3, 6))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.COD, 15, 1, 5))
// 		);

// 		TerrestriaBiomes.CALDERA = TerrestriaBiomes.register("caldera", template.builder()
// 				.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
// 				.depth(1.5F)
// 				.scale(0.05F)
// 				.temperature(0.7F)
// 				.downfall(0.7F)
// 				.build());

// 		TerrestriaBiomes.CALDERA_BEACH = TerrestriaBiomes.register("caldera_beach", template.builder()
// 				.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
// 				.depth(2.25F)
// 				.scale(0F)
// 				.temperature(0.7F)
// 				.downfall(0.7F)
// 				.build());

// 		TerrestriaBiomes.CALDERA_FOOTHILLS = TerrestriaBiomes.register("caldera_foothills", template.builder()
// 				.configureSurfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.EXTREME_HILLS)
// 				.depth(1.7F)
// 				.scale(0.4F)
// 				.temperature(0.0F)
// 				.downfall(0.1F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 				)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_CONIFER_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_HEMLOCK_TREES)
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4))
// 				.build());

// 		TerrestriaBiomes.CALDERA_RIDGE = TerrestriaBiomes.register("caldera_ridge", template.builder()
// 				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS)
// 				.depth(4F)
// 				.scale(0F)
// 				.temperature(0F)
// 				.downfall(1F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 				)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_REDWOOD_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.SPARSE_SMALL_HEMLOCK_TREES)
// 				.build());
// 	}
// }
