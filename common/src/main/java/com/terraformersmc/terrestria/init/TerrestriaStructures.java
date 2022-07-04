package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchStructureFeature;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoFeatureConfig;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoStructureFeature;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.feature.*;

public class TerrestriaStructures {
	public static final StructurePieceType CANYON_ARCH_PIECE = registerStructurePiece("canyon_arch", CanyonArchGenerator::new);
	public static final StructurePieceType VOLCANO_PIECE = registerStructurePiece("volcano", VolcanoGenerator::new);

	public static final StructureFeature<DefaultFeatureConfig> CANYON_ARCH_FEATURE = registerStructureFeature("canyon_arch", new CanyonArchStructureFeature(DefaultFeatureConfig.CODEC));
	public static final StructureFeature<VolcanoFeatureConfig> VOLCANO_FEATURE = registerStructureFeature("volcano", new VolcanoStructureFeature(VolcanoFeatureConfig.CODEC));

	public static void init() {
	}

	private static StructurePieceType registerStructurePiece(String id, StructurePieceType piece) {
		return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, id), piece);
	}

	private static <T extends FeatureConfig> StructureFeature<T> registerStructureFeature(String id, StructureFeature<T> feature) {
		return Registry.register(Registry.STRUCTURE_FEATURE, new Identifier(Terrestria.MOD_ID, id), feature);
	}
}
