package com.terraformersmc.terrestria.feature.structure.volcano;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.biolith.api.biomeperimeters.BiomePerimeters;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaStructures;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class VolcanoStructure extends Structure {
    public static final MapCodec<VolcanoStructure> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(VolcanoStructure.settingsCodec(instance),
			IntProvider.NON_NEGATIVE_CODEC.fieldOf("height").forGetter(arg -> arg.height),
			Codec.INT.fieldOf("base_y").forGetter(arg -> arg.baseY),
			Codec.BOOL.fieldOf("thin_if_tall").forGetter(arg -> arg.thinIfTall)
		).apply(instance, VolcanoStructure::new));

	public final IntProvider height;
	public final int baseY;
	public final boolean thinIfTall;

	public VolcanoStructure(VolcanoStructure.StructureSettings config, IntProvider height, int baseY, boolean thinIfTall) {
		super(config);

		this.height = height;
		this.baseY = baseY;
		this.thinIfTall = thinIfTall;
	}

	@Override
	public Optional<GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
		int x = context.chunkPos().getMiddleBlockX();
		int z = context.chunkPos().getMiddleBlockZ();
		int y = context.chunkGenerator().getFirstOccupiedHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());
		int seaLevel = context.chunkGenerator().getSeaLevel();
		Holder<Biome> biome = context.chunkGenerator().getBiomeSource().getNoiseBiome(QuartPos.fromBlock(x),
				QuartPos.fromBlock(y), QuartPos.fromBlock(z), context.randomState().sampler());

		if (biome.is(TerrestriaBiomes.VOLCANIC_ISLAND)) {
			// Shore volcanoes at the edges and regular volcanoes in the center.
			int distance = BiomePerimeters.getOrCreateInstance(
					context.registryAccess().lookupOrThrow(Registries.BIOME).getValueOrThrow(TerrestriaBiomes.VOLCANIC_ISLAND), 40)
					.getPerimeterDistance(new BiomeManager(
							new BiomeAccessStorage(context.biomeSource(), context.randomState().sampler()),
							context.seed()), new BlockPos(x, seaLevel, z));

			if (this.baseY < seaLevel - 10 && distance >= 20) {
				return Optional.empty();
			}
			if (this.baseY >= seaLevel - 10 && distance < 20) {
				return Optional.empty();
			}
		} else if (!Terrestria.getConfigManager().getGeneralConfig().areOceanVolcanoesEnabled()) {
			// No need to consider starting a structure outside Volcanic Island unless sea volcanoes are enabled.
			return Optional.empty();
		}

		return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, collector -> this.addPieces(collector, context));
	}

	private void addPieces(StructurePiecesBuilder collector, Structure.GenerationContext context) {
		collector.addPiece(new VolcanoGenerator(context.random(), context.chunkPos().getMiddleBlockX(), context.chunkPos().getMiddleBlockZ(), height, baseY, thinIfTall));
	}

	@Override
	public StructureType<?> type() {
		return TerrestriaStructures.VOLCANO_STRUCTURE_TYPE;
	}

	// Shim class to instantiate a BiomeAccess.Storage from available information.
	private record BiomeAccessStorage(BiomeSource biomeSource, Climate.Sampler noiseSampler) implements BiomeManager.NoiseBiomeSource {
		@Override
		public Holder<Biome> getNoiseBiome(int biomeX, int biomeY, int biomeZ) {
			return biomeSource.getNoiseBiome(biomeX, biomeY, biomeZ, noiseSampler);
		}
	}
}
