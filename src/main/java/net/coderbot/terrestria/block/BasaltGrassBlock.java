package net.coderbot.terrestria.block;

import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.*;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.light.ChunkLightProvider;

import java.util.Random;

public class BasaltGrassBlock extends GrassBlock {
	public BasaltGrassBlock(Block.Settings settings) {
		super(settings);
	}

	private static boolean canSurvive(BlockState state, ViewableWorld world, BlockPos pos) {
		BlockPos above = pos.up();
		BlockState aboveState = world.getBlockState(above);
		
		if (aboveState.getBlock() == Blocks.SNOW && aboveState.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else {
			int lightingAt = ChunkLightProvider.method_20049(world, state, pos, aboveState, above, Direction.UP, aboveState.getLightSubtracted(world, above));
			return lightingAt < world.getMaxLightLevel();
		}
	}

	private static boolean canSpread(BlockState state, ViewableWorld world, BlockPos pos) {
		BlockPos above = pos.up();
		return canSurvive(state, world, pos) && !world.getFluidState(above).matches(FluidTags.WATER);
	}

	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random) {
		if (!world.isClient) {
			if (!canSurvive(state, world, pos)) {
				world.setBlockState(pos, TerrestriaBlocks.BASALT_DIRT.getDefaultState());
			} else if (world.getLightLevel(pos.up()) >= 4) {
				if (world.getLightLevel(pos.up()) >= 9) {
					BlockState defaultState = this.getDefaultState();

					for(int int_1 = 0; int_1 < 4; ++int_1) {
						BlockPos blockPos_2 = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

						if (world.getBlockState(blockPos_2).getBlock() == TerrestriaBlocks.BASALT_DIRT && canSpread(defaultState, world, blockPos_2)) {
							world.setBlockState(blockPos_2, defaultState.with(SNOWY, world.getBlockState(blockPos_2.up()).getBlock() == Blocks.SNOW));
						}
					}
				}

			}
		}
	}
}
