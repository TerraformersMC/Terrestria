package com.terraformersmc.terrestria.block;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.jspecify.annotations.NullMarked;

/**
 * A custom seagrass block that allows the specification of a custom tall variant.
 */
@NullMarked
public class CattailBlock extends SeagrassBlock {
	public CattailBlock(Block.Properties settings) {
		super(settings);
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		BlockState tallBottom = TerrestriaBlocks.TALL_CATTAIL.defaultBlockState();
		BlockState tallTop = tallBottom.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
		BlockPos upper = pos.above();

		if (level.getBlockState(upper).isAir()) {
			level.setBlock(pos, tallBottom, 2);
			level.setBlock(upper, tallTop, 2);
		}
	}
}
