package com.terraformersmc.terrestria.biome;

// TODO: Update to 1.18
// import com.terraformersmc.terraform.biomebuilder.BiomeTemplate;
// import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
// import com.terraformersmc.terrestria.init.TerrestriaBiomes;
// import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
// import net.minecraft.world.biome.Biome;
// import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;

// import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

// public class DunesBiomes {
// 	public static void register() {
// 		BiomeTemplate template = new BiomeTemplate(TerraformBiomeBuilder.create()
// 			.configureSurfaceBuilder(TerrestriaSurfaces.DUNES, TerrestriaSurfaces.DUNES_CONFIG)
// 			.precipitation(Biome.Precipitation.NONE).category(Biome.Category.DESERT)
// 			.temperature(0.9F)
// 			.downfall(0.1F)
// 			.effects(TerrestriaBiomes.createDefaultBiomeEffects()
// 				.waterColor(0x4da5e3)
// 				.waterFogColor(0x24a0b0)
// 			)
// 			.addDefaultFeatures(DEFAULT_UNDERGROUND_STRUCTURES, DUNGEONS, MINEABLES, ORES, DISKS, FROZEN_TOP_LAYER)
// 			.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
// 			.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
// 			.addDefaultSpawnEntries()
// 		);

// 		TerrestriaBiomes.DUNES = TerrestriaBiomes.register("dunes", template.builder()
// 			.depth(0.3F)
// 			.scale(0.0F)
// 			.build()
// 		);

// 		TerrestriaBiomes.DUNES_EDGE = TerrestriaBiomes.register("dunes_edge", template.builder()
// 			.depth(-1F)
// 			.scale(0.0F)
// 			.build()
// 		);
// 	}
// }
