package com.terraformersmc.terrestria.feature.tree.treedecorators;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.wood.block.SmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;

import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.EmptyBlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.tree.TreeDecoratorType;

public class SakuraTreeDecorator extends TreeDecorator {
	public static Codec<SakuraTreeDecorator> CODEC = Codec.unit(new SakuraTreeDecorator());

	@Override
	protected TreeDecoratorType<?> getType() {
		return TerrestriaTreeDecorators.SAKURA;
	}

	@Override
	public void generate(StructureWorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> set, BlockBox box) {
		for (BlockPos pos : leavesPositions) {
			// 1/6 positions have leaf piles
			// As this executes for every single leaf block and there is usually 3-4 leaf blocks in a column, it ends up working out to 50%, usually.
			if (random.nextInt(6) > 0) {
				continue;
			}

			// We can't use WORLD_SURFACE here, because that includes leaves. MOTION_BLOCKING_NO_LEAVES seems to be what
			// we want, because it doesn't include leaves, but it will get us the surface of the terrain and the surface
			// of the water.
			//
			// This seems to work in both worldgen and when growing saplings.
			BlockPos top = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

			boolean valid = world.testBlockState(top.down(),
					state -> state.getFluidState().getFluid().isIn(FluidTags.WATER) ||
							state.isSideSolidFullSquare(EmptyBlockView.INSTANCE, top.down(), Direction.UP)
			);

			if (world.getBlockState(top).getBlock() instanceof SmallLogBlock) {
				continue;
			}

			// It's quite important that we don't replace other blocks that aren't supposed to be touched by trees.
			// Otherwise, you get very destructive sakura trees.
			if (valid && TreeFeature.canReplace(world, top)) {
				world.setBlockState(top, TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState(), 3);
			}
		}
	}
}
