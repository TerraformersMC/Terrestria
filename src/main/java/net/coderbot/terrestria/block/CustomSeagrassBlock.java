package net.coderbot.terrestria.block;

import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CustomSeagrassBlock extends SeagrassBlock {
	public CustomSeagrassBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public void grow(World world, Random random, BlockPos pos, BlockState state) {
		BlockState tall = TerrestriaBlocks.TALL_CATTAIL.getDefaultState();
		BlockState tallTop = tall.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
		BlockPos upper = pos.up();

		if (world.getBlockState(upper).getBlock() == Blocks.AIR) {
			world.setBlockState(pos, tall, 2);
			world.setBlockState(upper, tallTop, 2);
		}
	}
}
