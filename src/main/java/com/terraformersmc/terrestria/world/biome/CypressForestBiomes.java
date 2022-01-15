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

// public class CypressForestBiomes {
// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
// 				.temperature(0.7F)
// 				.downfall(0.8F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 					.grassColor(0x7ecc41)
// 				)
// 				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, FOREST_FLOWERS, MINEABLES, ORES, DISKS,
// 						DEFAULT_FLOWERS, DEFAULT_MUSHROOMS, FOREST_GRASS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER, TALL_BIRCH_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSEST_CYPRESS_TREES)
// 				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 				.addDefaultSpawnEntries()
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4))
// 		);

// 		TerrestriaBiomes.CYPRESS_FOREST = TerrestriaBiomes.register("cypress_forest", template.builder()
// 				.depth(0.1F)
// 				.scale(0.2F)
// 				.playerSpawnFriendly()
// 				.build());

// 		TerrestriaBiomes.WOODED_CYPRESS_HILLS = TerrestriaBiomes.register("wooded_cypress_hills", template.builder()
// 				.depth(0.45F)
// 				.scale(0.3F)
// 				.build());
// 	}
// }
