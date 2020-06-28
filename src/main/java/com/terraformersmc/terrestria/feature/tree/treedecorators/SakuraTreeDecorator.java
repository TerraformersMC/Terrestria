package com.terraformersmc.terrestria.feature.tree.treedecorators;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;

import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecoratorType;

public class SakuraTreeDecorator extends TreeDecorator {
	public static Codec<SakuraTreeDecorator> CODEC = Codec.unit(new SakuraTreeDecorator());

	@Override
	protected TreeDecoratorType<?> getType() {
		return TerrestriaTreeDecorators.SAKURA;
	}

	@Override
	public void generate(WorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> set, BlockBox box) {
		for (BlockPos pos : leavesPositions) {
			// 1/3 positions have leaf piles
			if (random.nextInt(3) > 0) continue;

			BlockPos.Mutable mutable = pos.down().mutableCopy();
			if (world.getBlockState(mutable).isAir() || world.getBlockState(mutable).getBlock() instanceof SmallLogBlock) {
				while(mutable.getY() > 0) {
					//check for a solid block and place on top
					if (world.getBlockState(mutable).isOpaque() || world.getFluidState(mutable).isIn(FluidTags.WATER)) {
						world.setBlockState(mutable.up(), TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState(), 3);
						break;
					}

					//move down the column
					mutable.move(Direction.DOWN);
				}
			}
		}
	}
}
