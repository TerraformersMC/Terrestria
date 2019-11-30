package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class PalmTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.WithBark tree;

	public PalmTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.WithBark tree) {
		super(function, notify);

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

	public PalmTreeFeature sapling() {
		return new PalmTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, BlockBox boundingBox) {
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
		growTrunk(blocks, world, pos, height, rand, boundingBox);
		growLeaves(blocks, world, pos, rand, boundingBox);

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
	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, Random rand, BlockBox boundingBox) {
		for (int i = 0; i < 2; i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
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
				setBlockState(blocks, world, pos, tree.getBark(), boundingBox);

				if (rand.nextBoolean()) {
					pos.setOffset(velocityX, 0, 0);
				} else {
					pos.setOffset(0, 0, velocityZ);
				}
				run = 0;

				if(!canTreeReplace(world, pos)) {
					return;
				}

				setBlockState(blocks, world, pos, tree.getBark(), boundingBox);
			} else {
				setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			}

			pos.setOffset(Direction.UP);
		}
	}

	private void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, BlockBox boundingBox) {
		if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
			setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
		}
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, Random rand, BlockBox boundingBox) {
		BlockPos center = pos.toImmutable();

		if(canTreeReplace(world, pos)) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
		}

		tryPlaceLeaves(blocks, world, pos.set(center).setOffset(0, 1, 0), boundingBox);
		tryPlaceLeaves(blocks, world, pos.set(center).setOffset(1, 1, 0), boundingBox);
		tryPlaceLeaves(blocks, world, pos.set(center).setOffset(0, 1, 1), boundingBox);
		tryPlaceLeaves(blocks, world, pos.set(center).setOffset(-1, 1, 0), boundingBox);
		tryPlaceLeaves(blocks, world, pos.set(center).setOffset(0, 1, -1), boundingBox);

		boolean invertLeafSpiral = rand.nextBoolean();

		for (int dZ = -1; dZ < 2; dZ++) {
			for (int dX = -1; dX < 2; dX++) {
				tryPlaceLeaves(blocks, world, pos.set(center).setOffset(dZ, 0, dX), boundingBox);
			}
		}

		for (int d = 0; d < 4; d++) {
			Direction direction = Direction.fromHorizontal(d);

			pos.set(center).setOffset(direction, 2);
			placeSpiral(blocks, world, pos, direction, !invertLeafSpiral, boundingBox);

			pos.set(center).setOffset(direction, 3);
			placeSpiral(blocks, world, pos, direction, invertLeafSpiral, boundingBox);
		}
	}

	private void placeSpiral(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, Direction direction, boolean invertLeafSpiral, BlockBox boundingBox) {
		tryPlaceLeaves(blocks, world, pos, boundingBox);

		Direction spiral = spiral(direction, invertLeafSpiral);
		tryPlaceLeaves(blocks, world, pos.setOffset(spiral), boundingBox);

		for (int i = 0; i < 2; i++) {
			tryPlaceLeaves(blocks, world, pos.setOffset(Direction.DOWN), boundingBox);
		}
	}
}
