package com.terraformersmc.terrestria.feature.tree.treedecorators;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaTreeDecorators;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator.Context;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class SakuraTreeDecorator extends TreeDecorator {
	public static MapCodec<SakuraTreeDecorator> CODEC = MapCodec.unit(new SakuraTreeDecorator());

	@Override
	protected TreeDecoratorType<?> type() {
		return TerrestriaTreeDecorators.SAKURA;
	}

	@Override
	public void place(Context generator) {
		RandomSource random = generator.random();
		LevelSimulatedReader world = generator.level();

		for (BlockPos pos : generator.leaves()) {
			// 1/6 positions have leaf piles
			// As this executes for every single leaf block and there is usually 3-4 leaf blocks in a column,
			// it ends up working out to 50%, usually.
			if (random.nextInt(6) > 0) {
				continue;
			}

			// We can't use WORLD_SURFACE here, because that includes leaves. MOTION_BLOCKING_NO_LEAVES seems to be what
			// we want, because it doesn't include leaves, but it will get us the surface of the terrain and the surface
			// of the water.
			//
			// This seems to work in both worldgen and when growing saplings.
			BlockPos top = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);

			boolean valid = world.isStateAtPosition(top.below(),
					state -> !(state.getBlock() instanceof BareSmallLogBlock) &&
							state.isFaceSturdy(EmptyBlockGetter.INSTANCE, top.below(), Direction.UP) ||
							state.getFluidState().isSource() &&
							state.getFluidState().is(FluidTags.WATER)
			);

			// It's quite important that we don't replace other blocks that aren't supposed to be touched by trees.
			// Otherwise, you get very destructive sakura trees.
			if (valid && TreeFeature.validTreePos(world, top)) {
				generator.setBlock(top, TerrestriaBlocks.SAKURA.leafPile.defaultBlockState());
			}
		}
	}
}
