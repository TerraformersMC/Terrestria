package com.terraformersmc.terrestria.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

// Based on Reoseah's ShrublingGrower class.
// Used under the terms of CC0.
public class ShrublingGenerator extends SaplingGenerator {
	protected final TreeFeatureConfig config;

	public ShrublingGenerator(TreeFeatureConfig config) {
		this.config = config;
	}

	@Override
	protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
		throw new UnsupportedOperationException("Shrublings don't have tree features, they have bush features instead");
	}

	protected ConfiguredFeature<TreeFeatureConfig, ?> createBushFeature(Random random) {
		return Feature.JUNGLE_GROUND_BUSH.configure(config);
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<?> generator, BlockPos pos, BlockState state, Random random) {
		ConfiguredFeature<?, ?> feature = this.createBushFeature(random);

		if (feature == null) {
			return false;
		}

		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

		if (!feature.generate(world, generator, random, pos)) {
			world.setBlockState(pos, state, 4);
			return false;
		}

		return true;
	}
}
