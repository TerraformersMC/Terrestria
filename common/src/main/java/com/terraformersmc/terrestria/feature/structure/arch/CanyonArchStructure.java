package com.terraformersmc.terrestria.feature.structure.arch;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.feature.helpers.placement.StructureCanGenerate;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class CanyonArchStructure extends Structure {
	public static final MapCodec<CanyonArchStructure> CODEC = CanyonArchStructure.simpleCodec(CanyonArchStructure::new);

	public CanyonArchStructure(Structure.StructureSettings config) {
		super(config);
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
		if (StructureCanGenerate.getMinCenteredCornerHeight(context, 96, 96) >= context.chunkGenerator().getSeaLevel()) {
			return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, collector -> this.addPieces(collector, context));
		} else {
			return Optional.empty();
		}
	}

	private void addPieces(StructurePiecesBuilder collector, Structure.GenerationContext context) {
		collector.addPiece(new CanyonArchGenerator(context.random(), context.chunkPos().getMiddleBlockX(), context.chunkPos().getMinBlockZ()));
	}

	@Override
	public StructureType<?> type() {
		return TerrestriaStructures.CANYON_ARCH_STRUCTURE_TYPE;
	}
}
