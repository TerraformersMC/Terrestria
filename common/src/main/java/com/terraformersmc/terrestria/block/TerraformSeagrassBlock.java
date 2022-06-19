package com.terraformersmc.terrestria.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeagrassBlock;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

/**
 * A custom seagrass block that allows the specification of a custom tall variant.
 */
public class TerraformSeagrassBlock extends SeagrassBlock {
	private Block tall;

	public TerraformSeagrassBlock(Block tall, Block.Settings settings) {
		super(settings);

		this.tall = tall;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		BlockState tallBottom = tall.getDefaultState();
		BlockState tallTop = tallBottom.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
		BlockPos upper = pos.up();

		if (world.getBlockState(upper).getBlock() == Blocks.AIR) {
			world.setBlockState(pos, tallBottom, 2);
			world.setBlockState(upper, tallTop, 2);
		}
	}
}
