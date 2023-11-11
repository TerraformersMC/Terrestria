package com.terraformersmc.terrestria.block;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeagrassBlock;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

/**
 * A custom seagrass block that allows the specification of a custom tall variant.
 */
public class CattailBlock extends SeagrassBlock {
	public CattailBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		BlockState tallBottom = TerrestriaBlocks.TALL_CATTAIL.getDefaultState();
		BlockState tallTop = tallBottom.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
		BlockPos upper = pos.up();

		if (world.getBlockState(upper).getBlock() == Blocks.AIR) {
			world.setBlockState(pos, tallBottom, 2);
			world.setBlockState(upper, tallTop, 2);
		}
	}
}
