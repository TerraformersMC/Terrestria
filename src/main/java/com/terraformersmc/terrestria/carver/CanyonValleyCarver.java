package com.terraformersmc.terrestria.carver;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class CanyonValleyCarver extends Carver<ProbabilityConfig> {

	public CanyonValleyCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int heightLimit) {
		super(config, heightLimit);
	}

	private void carveAtPoint(Chunk chunk, int x, int z, int y, CarverConfig config) {
		chunk.setBlockState(new BlockPos.Mutable(x, y, z), Blocks.CAVE_AIR.getDefaultState(), false);
	}

	@Override
	public boolean carve(Chunk chunk, Random random, int i, int i1, int i2, int i3, int i4, BitSet bitSet, ProbabilityConfig probabilityConfig) {
		carveAtPoint(chunk, i, i2, i3, probabilityConfig);
		return true;
	}

	@Override
	public boolean shouldCarve(Random random, int i, int i1, ProbabilityConfig probabilityConfig) {
		return true;
	}

	@Override
	protected boolean isPositionExcluded(double v, double v1, double v2, int i) {
		return false;
	}
}
