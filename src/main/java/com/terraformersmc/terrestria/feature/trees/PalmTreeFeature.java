package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class PalmTreeFeature extends AbstractTreeFeature<BranchedTreeFeatureConfig> {
	private TreeDefinition.WithBark tree;

	public PalmTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.WithBark tree) {
		super(function);

		this.tree = tree;
	}

	private static Direction spiral(Direction direction, boolean invert) {
		switch (direction) {
			case EAST:
				return invert ? Direction.NORTH : Direction.SOUTH;
			case WEST:
				return invert ? Direction.SOUTH : Direction.NORTH;
			case NORTH:
				return invert ? Direction.WEST : Direction.EAST;
			case SOUTH:
			default:
				return invert ? Direction.EAST : Direction.WEST;
		}
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total trunk height
		int height = rand.nextInt(5) + 8;

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		boolean sand = false;

		if (!isNaturalDirtOrGrass(world, below)) {
			if(world.testBlockState(below, state -> state.matches(BlockTags.SAND))) {
				sand = true;
			} else {
				return false;
			}
		}

		if(!check(world, origin, height)) {
			return false;
		}

		if(!sand) {
			setToDirt(world, below);
		}

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		growTrunk(logs, world, pos, height, rand, box);
		growLeaves(logs, world, pos, rand, box);

		return true;
	}

	private boolean check(TestableWorld world, BlockPos origin, int height) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int y = 0; y < height; y++) {
			int radius = y >= 2 ? 1 : 0;

			for(int z = -radius; z <= radius; z++) {
				for(int x = -radius; x <= radius; x++) {
					pos.set(origin).setOffset(x, y, z);

					if(!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	// Grows the bent trunk of the tree.
	private void growTrunk(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, Random rand, BlockBox box) {
		for (int i = 0; i < 2; i++) {
			PortUtil.setBlockState(logs, world, pos, tree.getLog(), box);
			pos.setOffset(Direction.UP);
		}

		int run = 0;

		int velocityX = rand.nextBoolean() ? 1 : -1;
		int velocityZ = rand.nextBoolean() ? 1 : -1;

		for (int i = 2; i < height; i++) {
			if(!canTreeReplace(world, pos)) {
				return;
			}

			if (run++ == 3) {
				PortUtil.setBlockState(logs, world, pos, tree.getBark(), box);

				if (rand.nextBoolean()) {
					pos.setOffset(velocityX, 0, 0);
				} else {
					pos.setOffset(0, 0, velocityZ);
				}
				run = 0;

				if(!canTreeReplace(world, pos)) {
					return;
				}

				PortUtil.setBlockState(logs, world, pos, tree.getBark(), box);
			} else {
				PortUtil.setBlockState(logs, world, pos, tree.getLog(), box);
			}

			pos.setOffset(Direction.UP);
		}
	}

	private void tryPlaceLeaves(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, BlockBox box) {
		if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
			PortUtil.setBlockState(logs, world, pos, tree.getLeaves(), box);
		}
	}

	private void growLeaves(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, Random rand, BlockBox box) {
		BlockPos center = pos.toImmutable();

		if(canTreeReplace(world, pos)) {
			PortUtil.setBlockState(logs, world, pos, tree.getLog(), box);
		}

		tryPlaceLeaves(logs, world, pos.set(center).setOffset(0, 1, 0), box);
		tryPlaceLeaves(logs, world, pos.set(center).setOffset(1, 1, 0), box);
		tryPlaceLeaves(logs, world, pos.set(center).setOffset(0, 1, 1), box);
		tryPlaceLeaves(logs, world, pos.set(center).setOffset(-1, 1, 0), box);
		tryPlaceLeaves(logs, world, pos.set(center).setOffset(0, 1, -1), box);

		boolean invertLeafSpiral = rand.nextBoolean();

		for (int dZ = -1; dZ < 2; dZ++) {
			for (int dX = -1; dX < 2; dX++) {
				tryPlaceLeaves(logs, world, pos.set(center).setOffset(dZ, 0, dX), box);
			}
		}

		for (int d = 0; d < 4; d++) {
			Direction direction = Direction.fromHorizontal(d);

			pos.set(center).setOffset(direction, 2);
			placeSpiral(logs, world, pos, direction, !invertLeafSpiral, box);

			pos.set(center).setOffset(direction, 3);
			placeSpiral(logs, world, pos, direction, invertLeafSpiral, box);
		}
	}

	private void placeSpiral(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, Direction direction, boolean invertLeafSpiral, BlockBox box) {
		tryPlaceLeaves(logs, world, pos, box);

		Direction spiral = spiral(direction, invertLeafSpiral);
		tryPlaceLeaves(logs, world, pos.setOffset(spiral), box);

		for (int i = 0; i < 2; i++) {
			tryPlaceLeaves(logs, world, pos.setOffset(Direction.DOWN), box);
		}
	}
}
