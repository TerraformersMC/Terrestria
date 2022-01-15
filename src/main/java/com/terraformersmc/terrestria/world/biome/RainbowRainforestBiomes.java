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
// import net.minecraft.world.gen.feature.*;
// import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

// import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

// public class RainbowRainforestBiomes {
// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
// 				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.JUNGLE)
// 				.temperature(0.95F)
// 				.downfall(0.9F)
// 				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 					.waterColor(0x3f76e4)
// 					.waterFogColor(0x50533)
// 				)
// 				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
// 						JUNGLE_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, JUNGLE_VEGETATION, FROZEN_TOP_LAYER)
// 				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 				.addStructureFeature(ConfiguredStructureFeatures.JUNGLE_PYRAMID)
// 				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 				.addStructureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_JUNGLE)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSER_RAINBOW_EUCALYPTUS_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSER_FANCY_OAK_TREES)
// 				.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, TerrestriaDecoratedFeatures.DENSE_RUBBER_TREES)
// 				.addDefaultSpawnEntries()
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.PARROT, 40, 1, 2))
// 				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.OCELOT, 2, 1, 1))
// 		);

// 		TerrestriaBiomes.RAINBOW_RAINFOREST = TerrestriaBiomes.register("rainbow_rainforest", template.builder()
// 				.depth(0.4F)
// 				.scale(0.4F)
// 				.playerSpawnFriendly()
// 				.build());

// 		TerrestriaBiomes.RAINBOW_RAINFOREST_MOUNTAINS = TerrestriaBiomes.register("rainbow_rainforest_mountains", template.builder()
// 				.depth(1.0F)
// 				.scale(0.5F)
// 				.build());

// 		TerrestriaBiomes.RAINBOW_RAINFOREST_LAKE = TerrestriaBiomes.register("rainbow_rainforest_lake", template.builder()
// 				.depth(-0.2F)
// 				.scale(0.0F)
// 				.build());
// 	}
// }
