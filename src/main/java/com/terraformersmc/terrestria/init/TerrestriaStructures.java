package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.volcano.VolcanoStructureFeature;
import net.earthcomputer.libstructure.LibStructure;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.*;

public class TerrestriaStructures {

	public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> VOLCANO;
	public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> OCEAN_VOLCANO;

	public static void init() {

		VOLCANO = registerDefaultStructure("volcano_structure", new VolcanoStructureFeature(DefaultFeatureConfig.CODEC), 6, 3);
		OCEAN_VOLCANO = registerDefaultStructure("ocean_volcano_structure", new VolcanoStructureFeature(DefaultFeatureConfig.CODEC), 24, 8);

	}

	public static void addToVanillaBiomes() {
		Biomes.DEEP_WARM_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_LUKEWARM_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_COLD_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_FROZEN_OCEAN.addStructureFeature(OCEAN_VOLCANO);
	}

	private static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> registerDefaultStructure(String id, StructureFeature<DefaultFeatureConfig> feature, int spacing, int separation) {
		LibStructure.registerStructure(new Identifier(Terrestria.MOD_ID, id),
				feature,
				GenerationStep.Feature.SURFACE_STRUCTURES,
				new StructureConfig(spacing, separation, 21345),
				feature.configure(new DefaultFeatureConfig()));
		return feature.configure(new DefaultFeatureConfig());
	}
}
