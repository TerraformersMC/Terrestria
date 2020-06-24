package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.DefaultFeature;
import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatureConfigs;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class CalderaBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.waterColor(0x54d3c0)
				.waterFogColor(0x24a0b0)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DISKS, DEFAULT_FLOWERS,
						DEFAULT_GRASS, DEFAULT_MUSHROOMS, DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER)
				.addStructureFeature(DefaultBiomeFeatures.STRONGHOLD)
				.addStructureFeature(DefaultBiomeFeatures.NORMAL_MINESHAFT)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SQUID, 3, 1, 4))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.SALMON, 15, 3, 6))
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.COD, 15, 1, 5))
		);

		TerrestriaBiomes.CALDERA = TerrestriaBiomes.register("caldera", template.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
				.depth(1.5F)
				.scale(0.05F)
				.temperature(0.7F)
				.downfall(0.7F)
				.waterColor(0x54d3c0)
				.waterFogColor(0x24a0b0)
				.build());

		TerrestriaBiomes.CALDERA_BEACH = TerrestriaBiomes.register("caldera_beach", template.builder()
				.configureSurfaceBuilder(TerrestriaSurfaces.CALDERA, SurfaceBuilder.GRASS_SAND_UNDERWATER_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.BEACH)
				.depth(2.25F)
				.scale(0F)
				.temperature(0.7F)
				.downfall(0.7F)
				.build());

		TerrestriaBiomes.CALDERA_FOOTHILLS = TerrestriaBiomes.register("caldera_foothills", template.builder()
				.configureSurfaceBuilder(SurfaceBuilder.MOUNTAIN, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.SNOW).category(Biome.Category.EXTREME_HILLS)
				.depth(1.7F)
				.scale(0.4F)
				.temperature(0.0F)
				.downfall(0.1F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addRareTreeFeature(TerrestriaFeatures.REDWOOD_TREE.configure(TerrestriaFeatureConfigs.REDWOOD), 2)
				.addRareTreeFeature(TerrestriaFeatures.HEMLOCK_TREE.configure(TerrestriaFeatureConfigs.HEMLOCK), 2)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.TINY_REDWOOD), 1)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.TINY_HEMLOCK), 1)
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
				.build());

		TerrestriaBiomes.CALDERA_RIDGE = TerrestriaBiomes.register("caldera_ridge", template.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.EXTREME_HILLS)
				.depth(4F)
				.scale(0F)
				.temperature(0F)
				.downfall(1F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.TINY_REDWOOD), 1)
				.addTreeFeature(Feature.TREE.configure(TerrestriaFeatureConfigs.TINY_HEMLOCK), 1)
				.build());
	}
}
