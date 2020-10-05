package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchStructureFeature;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoStructureFeature;
import net.earthcomputer.libstructure.LibStructure;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
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
		VOLCANO = registerDefaultStructure("volcano_structure", new VolcanoStructureFeature(DefaultFeatureConfig.CODEC), 10, 5);

	}

	public static void addToVanillaBiomes() {
		if (TerrestriaConfigManager.getGeneralConfig().areOceanVolcanoesEnabled()) {
			addOceanVolcanoesToBiome(BiomeKeys.DEEP_WARM_OCEAN);
			addOceanVolcanoesToBiome(BiomeKeys.DEEP_LUKEWARM_OCEAN);
			addOceanVolcanoesToBiome(BiomeKeys.DEEP_OCEAN);
			addOceanVolcanoesToBiome(BiomeKeys.DEEP_COLD_OCEAN);
			addOceanVolcanoesToBiome(BiomeKeys.DEEP_FROZEN_OCEAN);
		}
	}

	private static void addOceanVolcanoesToBiome(RegistryKey<Biome> biome) {
		BuiltinRegistries.BIOME.get(biome).getGenerationSettings().getStructureFeatures().add(() -> OCEAN_VOLCANO);
	}

	private static StructurePieceType registerStructurePiece(String id, StructurePieceType piece) {
		return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, id), piece);
	}

	private static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> registerDefaultStructure(String id, StructureFeature<DefaultFeatureConfig> feature, int spacing, int separation) {
		ConfiguredStructureFeature<DefaultFeatureConfig, ?> configured = feature.configure(DefaultFeatureConfig.INSTANCE);
		Identifier identifier = new Identifier(Terrestria.MOD_ID, id);

		LibStructure.registerStructure(identifier,
				feature,
				GenerationStep.Feature.SURFACE_STRUCTURES,
				new StructureConfig(spacing, separation, 21345),
				configured);

		return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, identifier, configured);
	}
}
