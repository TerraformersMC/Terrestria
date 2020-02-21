package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biome.builder.TerraformBiome;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaFeatureConfigs;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biome.builder.DefaultFeature.*;

public class JapaneseMapleForestBiomes {
	public static void register() {
		TerraformBiome.Template template = new TerraformBiome.Template(TerraformBiome.builder()
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST)
				.temperature(0.8F)
				.downfall(0.5F)
				.waterColor(4159204)
				.waterFogColor(329011)
				.grassColor(0x7aab1a)
				.foliageColor(0x7aab1a)
				.addDefaultFeatures(LAND_CARVERS, STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, DEFAULT_MUSHROOMS,
						DEFAULT_VEGETATION, SPRINGS, FROZEN_TOP_LAYER, FOREST_GRASS)
				.addStructureFeature(Feature.STRONGHOLD)
				.addStructureFeature(Feature.MINESHAFT, new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL))
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_TREE.configure(TerrestriaFeatureConfigs.JAPANESE_MAPLE), 3)
				.addTreeFeature(Feature.JUNGLE_GROUND_BUSH.configure(TerrestriaFeatureConfigs.JAPANESE_MAPLE_SHRUB), 3)
				.addTreeFeature(TerrestriaFeatures.JAPANESE_MAPLE_TREE.configure(TerrestriaFeatureConfigs.DARK_JAPANESE_MAPLE), 3)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new Biome.SpawnEntry(EntityType.WOLF, 5, 4, 4))
		);

		TerrestriaBiomes.JAPANESE_MAPLE_FOREST = TerrestriaBiomes.register("japanese_maple_forest", template.builder()
				.depth(0.05F)
				.scale(0.2F)
				.build());

		TerrestriaBiomes.WOODED_JAPANESE_MAPLE_HILLS = TerrestriaBiomes.register("wooded_japanese_maple_hills", template.builder()
				.depth(0.45F)
				.scale(0.5F)
				.build());
	}
}
