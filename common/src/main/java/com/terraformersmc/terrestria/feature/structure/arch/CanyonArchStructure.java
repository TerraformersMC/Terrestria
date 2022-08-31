package com.terraformersmc.terrestria.feature.structure.arch;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.helpers.placement.StructureCanGenerate;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.structure.*;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class CanyonArchStructure extends Structure {
	public static final Codec<CanyonArchStructure> CODEC = CanyonArchStructure.createCodec(CanyonArchStructure::new);

	public CanyonArchStructure(Structure.Config config) {
		super(config);
	}

	@Override
	public Optional<StructurePosition> getStructurePosition(Structure.Context context) {
		if (StructureCanGenerate.getMinCenteredCornerHeight(context, 96, 96) >= context.chunkGenerator().getSeaLevel()) {
			return getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG, collector -> this.addPieces(collector, context));
		} else {
			return Optional.empty();
		}
	}

	private void addPieces(StructurePiecesCollector collector, Structure.Context context) {
		collector.addPiece(new CanyonArchGenerator(context.random(), context.chunkPos().getCenterX(), context.chunkPos().getStartZ()));
	}

	@Override
	public StructureType<?> getType() {
		return TerrestriaStructures.CANYON_ARCH_STRUCTURE_TYPE;
	}
}
