package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.block.TerraformGrassBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Supplier;

public class BasaltGrassBlock extends TerraformGrassBlock {
	public BasaltGrassBlock(Block dirt, Supplier<Block> path, Settings settings) {
		super(dirt, path, settings);
	}

	// TODO: Refactor to be like vanilla grass
	@Override
	public void grow(ServerWorld world, Random random, BlockPos centerPos, BlockState grassState) {
		BlockPos above = centerPos.up();

		BlockState grass = TerrestriaBlocks.MONSTERAS.getDefaultState();
		BlockState flower = TerrestriaBlocks.INDIAN_PAINTBRUSH.getDefaultState();

		// 33% chance of normal grass
		if(random.nextInt(3) == 0) {
			grass = Blocks.GRASS.getDefaultState();
		}

		outer:
		for(int tries = 0; tries < 128; tries++) {
			BlockPos pos = above;
			BlockState block = random.nextInt(8) == 0 ? flower : grass;

			// Steps only a few blocks at a time in moving to the random position.
			// Likely to avoid growing on the other side of walls.
			for(int moves = 0; moves < 8; moves++) {
				pos = pos.add(
					 random.nextInt(3) - 1,
					(random.nextInt(3) - 1) * random.nextInt(3) / 2,
					 random.nextInt(3) - 1
				);

				// Check if the block is a valid block
				if (!block.canPlaceAt(world, pos) || world.getBlockState(pos).isFullCube(world, pos)) {
					continue outer;
				}
			}

			if (!world.getBlockState(pos).isAir()) {
				continue;
			}

			if (block.canPlaceAt(world, pos)) {
				world.setBlockState(pos, block, 3);
			}
		}
	}
}
