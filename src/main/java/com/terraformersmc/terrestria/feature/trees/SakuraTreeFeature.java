package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.components.Branches;
import com.terraformersmc.terrestria.feature.trees.components.SmallLogs;
import com.terraformersmc.terrestria.feature.trees.templates.JapaneseTreeFeature;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.EmptyBlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class SakuraTreeFeature extends JapaneseTreeFeature implements SmallLogs, Branches {
	private BlockState leafPile;

	public SakuraTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, boolean notify, BlockState leafPile) {
		super(function);

		this.leafPile = leafPile;
	}

	@Override
	public void placeGroundCover(ModifiableTestableWorld world, Random rand, BlockPos.Mutable origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, double maxRadius) {
		int x = origin.getX();
		int z = origin.getZ();

		Shapes.circle(origin, maxRadius, pos -> {
			if (pos.getX() == x && pos.getZ() == z) {
				return;
			}

			if (rand.nextInt(2) != 0) {
				return;
			}

			BlockPos top = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);

			if (!AbstractTreeFeature.isAir(world, top)) {
				return;
			}

			boolean valid = world.testBlockState(top.down(),
				state -> state.getFluidState().getFluid().matches(FluidTags.WATER) ||
					     state.isSideSolidFullSquare(EmptyBlockView.INSTANCE, top.down(), Direction.UP)
			);

			if (valid) {
				PortUtil.setBlockState(leaves, world, top, leafPile, box);
			}
		});
	}

	@Override
	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, int length) {
		for (int i = 0; i < length; i++) {
			pos.setOffset(direction);
			setLogBlockState(world, rand, pos, logs, box, config);
		}
	}

	@Override
	public void correctLogStates(Set<BlockPos> logs, ModifiableTestableWorld world, BlockBox box) {
		for (BlockPos log : logs) {
			boolean leaves = world.testBlockState(log, tested -> tested.getBlock() instanceof SmallLogBlock && tested.get(SmallLogBlock.HAS_LEAVES));

			Predicate<BlockState> tester = tested -> tested.getBlock() instanceof SmallLogBlock || (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();

			world.testBlockState(log, state -> {
				if(state.getBlock() instanceof SmallLogBlock) {
					setBlockState(
							world,
							log,
							state
									.with(SmallLogBlock.UP, world.testBlockState(log.up(), tester))
									.with(SmallLogBlock.DOWN, world.testBlockState(log.down(), tester))
									.with(SmallLogBlock.NORTH, world.testBlockState(log.north(), tester))
									.with(SmallLogBlock.EAST, world.testBlockState(log.east(), tester))
									.with(SmallLogBlock.SOUTH, world.testBlockState(log.south(), tester))
									.with(SmallLogBlock.WEST, world.testBlockState(log.west(), tester))
									.with(SmallLogBlock.HAS_LEAVES, leaves),
							box
					);
				}

				return true;
			});
		}
	}
}
