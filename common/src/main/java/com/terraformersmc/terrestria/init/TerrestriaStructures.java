package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchGenerator;
import com.terraformersmc.terrestria.feature.structure.arch.CanyonArchStructure;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoGenerator;
import com.terraformersmc.terrestria.feature.structure.volcano.VolcanoStructure;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

/**
 * Terrestria structures are procedurally generated, but configured and placed via JSON.
 * generation:    (starts here)
 * configuration: resources/data/terrestria/worldgen/structure
 * placement:     resources/data/terrestria/worldgen/structure_set
 */
public class TerrestriaStructures {
	public static final StructureType<CanyonArchStructure> CANYON_ARCH_STRUCTURE_TYPE = registerStructureType("canyon_arch", CanyonArchStructure.CODEC);
	public static final StructureType<VolcanoStructure> VOLCANO_STRUCTURE_TYPE = registerStructureType("volcano", VolcanoStructure.CODEC);

	public static final StructurePieceType CANYON_ARCH_PIECE = registerStructurePiece("canyon_arch", CanyonArchGenerator::new);
	public static final StructurePieceType VOLCANO_PIECE = registerStructurePiece("volcano", VolcanoGenerator::new);

	public static void init() {
	}

	private static <S extends Structure> StructureType<S> registerStructureType(String id, MapCodec<S> codec) {
		return Registry.register(Registries.STRUCTURE_TYPE, new Identifier(Terrestria.MOD_ID, id), () -> codec);
	}

	private static StructurePieceType registerStructurePiece(String id, StructurePieceType piece) {
		return Registry.register(Registries.STRUCTURE_PIECE, new Identifier(Terrestria.MOD_ID, id), piece);
	}
}
