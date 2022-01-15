package com.terraformersmc.terrestria.world.biome;

// TODO: Update to 1.18
// import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
// import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaDecoratedFeatures;
// import net.minecraft.entity.EntityType;
// import net.minecraft.world.biome.Biome;
// import net.minecraft.world.biome.SpawnSettings;
// import net.minecraft.world.gen.GenerationStep;
// import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
// import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

// import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

// public class JapaneseMapleForestBiomes {
// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
// 				.temperature(0.8F)
// 				.downfall(0.5F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 					.grassColor(0x7aab1a)
// 					.foliageColor(0x7aab1a)
// 				)
// 				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DEFAULT_MUSHROOMS,
// 						DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER, FOREST_GRASS)
// 				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL)
// 				.addStructureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_JAPANESE_MAPLE_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_DARK_JAPANESE_MAPLE_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_JAPANESE_MAPLE_SHRUBS)
// 				.addDefaultSpawnEntries()
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4))
// 		);

// 		TerrestriaBiomes.JAPANESE_MAPLE_FOREST = TerrestriaBiomes.register("japanese_maple_forest", template.builder()
// 				.depth(0.05F)
// 				.scale(0.2F)
// 				.build());

// 		TerrestriaBiomes.WOODED_JAPANESE_MAPLE_HILLS = TerrestriaBiomes.register("wooded_japanese_maple_hills", template.builder()
// 				.depth(0.45F)
// 				.scale(0.5F)
// 				.build());
// 	}
// }
