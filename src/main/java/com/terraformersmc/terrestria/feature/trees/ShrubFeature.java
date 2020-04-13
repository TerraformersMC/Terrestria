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
		//only generate on grass
		if (world.getBlockState(pos.down()) != Blocks.GRASS_BLOCK.getDefaultState()) return false;

		//height can be 1 or 2 blocks
		int height = random.nextInt(2) + 1;
		for (int y = 0; y < height; y++) {
			world.setBlockState(pos.add(0, y, 0), config.woodState, 2);
		}

		//top of the shrub
		setIfAir(world, pos.add(0, height, 0), config.leafState);

		//the other 4 blocks on the side
		setIfAir(world, pos.add(0, height - 1, 1), config.leafState);
		setIfAir(world, pos.add(0, height - 1, -1), config.leafState);
		setIfAir(world, pos.add(-1, height - 1, 0), config.leafState);
		setIfAir(world, pos.add(1, height - 1, 0), config.leafState);
		return true;
	}

	private static void setIfAir(IWorld world, BlockPos pos, BlockState state) {
		if (world.getBlockState(pos).isAir()) world.setBlockState(pos, state, 2);
	}
}
