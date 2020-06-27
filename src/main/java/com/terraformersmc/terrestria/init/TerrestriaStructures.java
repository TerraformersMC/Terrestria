package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchStructureFeature;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoStructureFeature;
import net.earthcomputer.libstructure.LibStructure;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class TerrestriaStructures {

	public static StructurePieceType VOLCANO_PIECE;
	public static StructurePieceType CANYON_ARCH_PIECE;

	public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> CANYON_ARCH;
	public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> OCEAN_VOLCANO;
	public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> VOLCANO;

	public static void init() {

		VOLCANO_PIECE = registerStructurePiece("volcano", VolcanoGenerator::new);
		CANYON_ARCH_PIECE = registerStructurePiece("canyon_arch", CanyonArchGenerator::new);

		CANYON_ARCH = registerDefaultStructure("canyon_arch_structure", new CanyonArchStructureFeature(DefaultFeatureConfig.CODEC), 5, 3);
		OCEAN_VOLCANO = registerDefaultStructure("ocean_volcano_structure", new VolcanoStructureFeature(DefaultFeatureConfig.CODEC), 24, 8);
		VOLCANO = registerDefaultStructure("volcano_structure", new VolcanoStructureFeature(DefaultFeatureConfig.CODEC), 6, 3);

	}

	public static void addToVanillaBiomes() {
		Biomes.DEEP_WARM_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_LUKEWARM_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_COLD_OCEAN.addStructureFeature(OCEAN_VOLCANO);
		Biomes.DEEP_FROZEN_OCEAN.addStructureFeature(OCEAN_VOLCANO);
	}

	private static StructurePieceType registerStructurePiece(String id, StructurePieceType piece) {
		return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, id), piece);
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
