package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.structure.*;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class VolcanoStructure extends Structure {
    public static final Codec<VolcanoStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(VolcanoStructure.configCodecBuilder(instance),
			IntProvider.NON_NEGATIVE_CODEC.fieldOf("height").forGetter(arg -> arg.height),
			Codec.INT.fieldOf("base_y").forGetter(arg -> arg.baseY),
			Codec.BOOL.fieldOf("thin_if_tall").forGetter(arg -> arg.thinIfTall)
		).apply(instance, VolcanoStructure::new));

	public final IntProvider height;
	public final int baseY;
	public final boolean thinIfTall;

	public VolcanoStructure(VolcanoStructure.Config config, IntProvider height, int baseY, boolean thinIfTall) {
		super(config);

		this.height = height;
		this.baseY = baseY;
		this.thinIfTall = thinIfTall;
	}

	@Override
	public Optional<StructurePosition> getStructurePosition(Structure.Context context) {
		int x = context.chunkPos().getCenterX();
		int z = context.chunkPos().getCenterZ();
		int y = context.chunkGenerator().getHeightInGround(x, z, Heightmap.Type.OCEAN_FLOOR_WG, context.world(), context.noiseConfig());
		RegistryEntry<Biome> biome = context.chunkGenerator().getBiomeSource().getBiome(BiomeCoords.fromBlock(x),
				BiomeCoords.fromBlock(y), BiomeCoords.fromBlock(z), context.noiseConfig().getMultiNoiseSampler());

		if (!biome.matchesKey(TerrestriaBiomes.VOLCANIC_ISLAND) &&
				!Terrestria.getConfigManager().getGeneralConfig().areOceanVolcanoesEnabled()) {
			return Optional.empty();
		}

		return getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG, collector -> this.addPieces(collector, context));
	}

	private void addPieces(StructurePiecesCollector collector, Structure.Context context) {
		collector.addPiece(new VolcanoGenerator(context.random(), context.chunkPos().getCenterX(), context.chunkPos().getCenterZ(), height, baseY, thinIfTall));
	}

	@Override
	public StructureType<?> getType() {
		return TerrestriaStructures.VOLCANO_STRUCTURE_TYPE;
	}
}
