package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchStructureFeature;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoFeatureConfig;
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
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class TerrestriaStructures {

	public static StructurePieceType VOLCANO_PIECE;
	public static StructurePieceType CANYON_ARCH_PIECE;

	private static final VolcanoFeatureConfig OCEAN_VOLCANO_CONFIG = new VolcanoFeatureConfig(UniformIntDistribution.of(20, 19), 30, false);
	private static final VolcanoFeatureConfig SHORE_VOLCANO_CONFIG = new VolcanoFeatureConfig(UniformIntDistribution.of(48, 31), 45, true);
	private static final VolcanoFeatureConfig VOLCANO_CONFIG = new VolcanoFeatureConfig(UniformIntDistribution.of(32, 63), 60, false);

	public static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> CANYON_ARCH;
	public static ConfiguredStructureFeature<VolcanoFeatureConfig, ? extends StructureFeature<VolcanoFeatureConfig>> OCEAN_VOLCANO;
	public static ConfiguredStructureFeature<VolcanoFeatureConfig, ? extends StructureFeature<VolcanoFeatureConfig>> VOLCANO;
	public static ConfiguredStructureFeature<VolcanoFeatureConfig, ? extends StructureFeature<VolcanoFeatureConfig>> SHORE_VOLCANO;

	public static void init() {

		VOLCANO_PIECE = registerStructurePiece("volcano", VolcanoGenerator::new);
		CANYON_ARCH_PIECE = registerStructurePiece("canyon_arch", CanyonArchGenerator::new);

		CANYON_ARCH = registerStructure("canyon_arch_structure", new CanyonArchStructureFeature(DefaultFeatureConfig.CODEC).configure(FeatureConfig.DEFAULT), 5, 3);

		VOLCANO = registerStructure("volcano_structure", new VolcanoStructureFeature(VolcanoFeatureConfig.CODEC).configure(VOLCANO_CONFIG), 10, 5);
		SHORE_VOLCANO = VOLCANO.feature.configure(SHORE_VOLCANO_CONFIG);
		registerConfiguredStructure("shore_volcano", SHORE_VOLCANO);
	}

	static {
		// Need this to be ready as early as possible so that it can be injected into existing biomes.
		OCEAN_VOLCANO = registerStructure("ocean_volcano_structure", new VolcanoStructureFeature(VolcanoFeatureConfig.CODEC).configure(OCEAN_VOLCANO_CONFIG), 24, 8);
	}

	public static void addOceanVolcanoesToBiome(GenerationSettings.Builder builder) {
		if (TerrestriaConfigManager.getGeneralConfig().areOceanVolcanoesEnabled()) {
			builder.structureFeature(TerrestriaStructures.OCEAN_VOLCANO);
		}
	}

	private static StructurePieceType registerStructurePiece(String id, StructurePieceType piece) {
		return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, id), piece);
	}

	private static <FC extends FeatureConfig, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> registerStructure(String id, ConfiguredStructureFeature<FC, F> configured, int spacing, int separation) {
		Identifier identifier = new Identifier(Terrestria.MOD_ID, id);

		LibStructure.registerStructure(identifier,
				configured.feature,
				GenerationStep.Feature.SURFACE_STRUCTURES,
				new StructureConfig(spacing, separation, 21345),
				configured);

		return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, identifier, configured);
	}

	private static void registerConfiguredStructure(String id, ConfiguredStructureFeature<?, ?> configured) {
		Identifier identifier = new Identifier(Terrestria.MOD_ID, id);

		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, identifier, configured);
	}
}
