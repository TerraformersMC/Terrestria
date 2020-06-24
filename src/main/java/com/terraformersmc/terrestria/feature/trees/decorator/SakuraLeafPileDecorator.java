package com.terraformersmc.terrestria.feature.trees.decorator;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.EmptyBlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.decorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SakuraLeafPileDecorator extends TreeDecorator {
	private int chance;

	public SakuraLeafPileDecorator(int chance) {
		this.chance = chance;
	}

	@Override
	public void generate(WorldAccess world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> placed, BlockBox box) {
		for (BlockPos pos : leaves) {
			if (rand.nextInt(chance) != 0) {
				continue;
			}

			BlockPos top = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

			if (!world.getBlockState(top).isAir()) {
				continue;
			}

			boolean valid = world.testBlockState(top.down(),
					state -> state.getFluidState().getFluid().isIn(FluidTags.WATER) ||
							state.isSideSolidFullSquare(EmptyBlockView.INSTANCE, top.down(), Direction.UP)
			);

			if (valid) {
				// setBlockState
				setBlockStateAndEncompassPosition(world, top, TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState(), placed, box);
			}
		}
	}

	@Override
	protected TreeDecoratorType<?> getType() {
		return TerrestriaFeatures.SAKURA_LEAF_PILE_DECORATOR;
	}
}
