package com.terraformersmc.terrestria.biome;

import com.terraformersmc.terraform.biomebuilder.TerraformBiomeBuilder;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaConfiguredFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static com.terraformersmc.terraform.biomebuilder.DefaultFeature.*;

public class CypressSwampBiomes {
	public static void register() {
		TerrestriaBiomes.CYPRESS_SWAMP = TerrestriaBiomes.register("cypress_swamp", TerraformBiomeBuilder.create()
				.configureSurfaceBuilder(SurfaceBuilder.SWAMP, SurfaceBuilder.GRASS_CONFIG)
				.precipitation(Biome.Precipitation.RAIN).category(Biome.Category.SWAMP)
				.depth(-0.25F)
				.scale(0.05F)
				.temperature(0.7F)
				.downfall(0.7F)
				.effects(TerrestriaBiomes.createDefaultBiomeEffects()
					.waterColor(0x2c7f32)
					.waterFogColor(0x053305)
					.grassColor(0x699e3c)
					.foliageColor(0x619137)
				)
				.addDefaultFeatures(LAND_CARVERS, DEFAULT_UNDERGROUND_STRUCTURES, LAKES, DUNGEONS, MINEABLES, ORES, CLAY, DEFAULT_GRASS,
						DEFAULT_MUSHROOMS, SPRINGS, /*TODO: SEAGRASS, MORE_SEAGRASS,*/ FOSSILS, FROZEN_TOP_LAYER, SWAMP_VEGETATION,
						DESERT_VEGETATION)
				.addTreeFeature(TerrestriaConfiguredFeatures.MEGA_CYPRESS_TREE, 2)
				.addTreeFeature(TerrestriaConfiguredFeatures.RUBBER_TREE, 3)
				.addTreeFeature(TerrestriaConfiguredFeatures.WILLOW_TREE, 1)
				/*TODO:.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
						TerrestriaFeatures.CATTAIL
							.configure(new ProbabilityConfig(80, 0.3D))
							.createDecoratedFeature(Decorator.TOP_SOLID_HEIGHTMAP.configure(DecoratorConfig.DEFAULT)))
				.addCustomFeature(GenerationStep.Feature.VEGETAL_DECORATION,
						ConfiguredFeatures.PATCH_WATERLILLY.decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_SPREAD_DOUBLE, Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(4))))*/
				.addGrassFeature(Blocks.GRASS.getDefaultState(), 2)
				.addGrassFeature(Blocks.BROWN_MUSHROOM.getDefaultState(), 1)
				.addDoubleGrassFeature(Blocks.TALL_GRASS.getDefaultState(), 1)
				.addStructureFeature(ConfiguredStructureFeatures.STRONGHOLD)
				.addStructureFeature(ConfiguredStructureFeatures.MINESHAFT)
				.addDefaultSpawnEntries()
				.addSpawnEntry(new SpawnSettings.SpawnEntry(EntityType.COD, 8, 2, 4))
				.build());
	}
}
