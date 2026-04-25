package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.dirt.api.block.TerraformGrassBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

import java.util.function.Supplier;

public class BasaltGrassBlock extends TerraformGrassBlock {
	public BasaltGrassBlock(Block dirt, Supplier<Block> path, Properties settings) {
		super(dirt, path, settings);
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource random, BlockPos centerPos, BlockState grassState) {
		BlockPos above = centerPos.above();

		BlockState grass = TerrestriaBlocks.MONSTERAS.defaultBlockState();
		BlockState flower = TerrestriaBlocks.INDIAN_PAINTBRUSH.defaultBlockState();

		// 33% chance of normal grass
		if (random.nextInt(3) == 0) {
			grass = Blocks.SHORT_GRASS.defaultBlockState();
		}

		outer:
		for (int tries = 0; tries < 128; tries++) {
			BlockPos pos = above;
			BlockState block = random.nextInt(8) == 0 ? flower : grass;

			// Steps only a few blocks at a time in moving to the random position.
			// Likely to avoid growing on the other side of walls.
			for (int moves = 0; moves < 8; moves++) {
				pos = pos.offset(
						random.nextInt(3) - 1,
						(random.nextInt(3) - 1) * random.nextInt(3) / 2,
						random.nextInt(3) - 1
				);

				// Check if the block is a valid block
				if (!block.canSurvive(world, pos) || world.getBlockState(pos).isCollisionShapeFullBlock(world, pos)) {
					continue outer;
				}
			}

			BlockState state = world.getBlockState(pos);

			// NB: this just converts short grass to tall grass, this isn't specific to GRASS_BLOCK
			if (state.is(Blocks.SHORT_GRASS) && random.nextInt(10) == 0) {
				((BonemealableBlock) state.getBlock()).performBonemeal(world, random, pos, state);
			}

			if (!state.isAir()) {
				continue;
			}

			if (block.canSurvive(world, pos)) {
				world.setBlock(pos, block, 3);
			}
		}
	}
}
