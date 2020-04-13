package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;
import java.util.function.Function;

public class ShrubFeature extends Feature<ShrubFeatureConfig> {

	public ShrubFeature(Function<Dynamic<?>, ? extends ShrubFeatureConfig> configDeserializer) {
		super(configDeserializer);
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, ShrubFeatureConfig config) {
		// Only generate on grass
		if (world.getBlockState(pos.down()) != Blocks.GRASS_BLOCK.getDefaultState()) {
			return false;
		}

		// Set the base trunk block
		world.setBlockState(pos, config.woodState, 2);

		// 50% of shrubs are twice as tall
		if (random.nextBoolean()) {
			pos = pos.up();
			world.setBlockState(pos, config.woodState, 2);
		}

		// Top of the shrub
		setIfAir(world, pos.up(), config.leafState);

		// The other 4 blocks on the side
		setIfAir(world, pos.north(), config.leafState);
		setIfAir(world, pos.south(), config.leafState);
		setIfAir(world, pos.east(), config.leafState);
		setIfAir(world, pos.west(), config.leafState);
		return true;
	}

	private static void setIfAir(IWorld world, BlockPos pos, BlockState state) {
		if (world.getBlockState(pos).isAir()) world.setBlockState(pos, state, 2);
	}
}
