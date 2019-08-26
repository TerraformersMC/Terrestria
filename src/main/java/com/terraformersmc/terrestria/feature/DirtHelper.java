package com.terraformersmc.terrestria.feature;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;

import java.util.function.Predicate;

public class DirtHelper {
	public static BlockState getCorrespondingDirt(TestableWorld world, BlockPos pos) {
		if(world instanceof BlockView) {
			return getCorrespondingDirt(((BlockView) world).getBlockState(pos));
		}

		// Hacky code for non-BlockView worlds
		GetBlockState getter = new GetBlockState();

		world.testBlockState(pos, getter);

		return getCorrespondingDirt(getter.state);
	}

	public static BlockState getCorrespondingDirt(BlockState state) {
		Block block = state.getBlock();

		if(block == Blocks.DIRT || block == Blocks.COARSE_DIRT || block == Blocks.PODZOL || block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND) {
			return Blocks.DIRT.getDefaultState();
		}

		if(block == TerrestriaBlocks.BASALT_DIRT || block == TerrestriaBlocks.BASALT_GRASS_BLOCK) {
			return TerrestriaBlocks.BASALT_DIRT.getDefaultState();
		}

		return state;
	}

	/**
	 * Mojang doesn't let us call getBlockState in AbstractTreeFeature, let's bypass that.
	 */
	protected static class GetBlockState implements Predicate<BlockState> {
		BlockState state;

		@Override
		public boolean test(BlockState state) {
			this.state = state;

			return false;
		}
	}
}
