package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import io.github.terraformersmc.terraform.util.*;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WillowTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public WillowTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);
		this.tree = tree;
	}

	public WillowTreeFeature sapling() {
		return new WillowTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total tree height
		int height = rand.nextInt(3) + 8;

		// Maximum leaf radius.
		double maxRadius = 5 + 3 * rand.nextDouble();

		//Minimum leaf radius
		double minRadius = 1.25 + 2 * rand.nextDouble();

		//If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		//Make sure the tree can grow where the origin is
		BlockPos below = origin.down();
		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}
		if (!checkForObstructions(world, origin, height, (int) Math.ceil(maxRadius))) {
			return false;
		}
		//If it can, place dirt below it.
		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);

		//Grow the trunk
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);
		growBranches(blocks, world, new BlockPos.Mutable(origin.offset(Direction.UP, height / 3)), (int) maxRadius - 1, boundingBox);
		growBranches(blocks, world, new BlockPos.Mutable(origin.offset(Direction.UP, (height / 2) + 1)), (int) (maxRadius * radiusFactor(height / 2, height)), boundingBox);
		//Grow the leaves
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		growLeaves(blocks, world, pos, height, maxRadius, minRadius, boundingBox, rand);

		return true;
	}

	// Grows the center trunk.
	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		//Grows the trunk at 80% of it's total height (so the trunk doesn't poke out)
		for (int i = 0; i < (height * .8); i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			pos.setOffset(Direction.UP);
		}
		//Make sure there are leaf blocks on the top of the tree
		Shapes.circle(pos, 1.5, position -> {
			if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
				setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
			}
		});
	}

	private void growBranches(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int maxRadius, MutableIntBoundingBox boundingBox) {
		//save original origin for use in z branch
		BlockPos origin = pos.toImmutable();
		int branchLength = maxRadius - 2;
		pos.setOffset(Direction.WEST, branchLength);
		for (int x = -branchLength; x <= branchLength; x++) {
			if (isAirOrLeaves(world, pos)) {
				setBlockState(blocks, world, pos, tree.getLog().with(LogBlock.AXIS, Direction.WEST.getAxis()), boundingBox);
			}
			pos.setOffset(Direction.EAST);
		}
		pos.set(origin);
		pos.setOffset(Direction.NORTH, branchLength);
		for (int y = -branchLength; y <= branchLength; y++) {
			if (isAirOrLeaves(world, pos)) {
				setBlockState(blocks, world, pos, tree.getLog().with(LogBlock.AXIS, Direction.NORTH.getAxis()), boundingBox);
			}
			pos.setOffset(Direction.SOUTH);
		}
		//reset the branches
		pos.set(origin);
	}

	private void growDangingBit(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox, Random random) {
		int randHolder;
		//33% chance of a dangling bit generating.
		if (random.nextInt(3) == 1) {
			//length of the bit
			randHolder = random.nextInt(3);
			for (int d = 0; d < randHolder; d++) {
				pos.setOffset(Direction.DOWN);
				setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
			}
			pos.setOffset(Direction.UP, randHolder);
		}
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, double maxRadius, double minRadius, MutableIntBoundingBox boundingBox, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double radius;
		double innerRadius;

		for (int dy = 0; dy < height; dy++) {

			//if the leaves aren't 1/3 way up the trunk
			if (dy < (height / 3)) {
				continue;
			}

			pos.set(x, y + dy, z);

			radius = maxRadius * radiusFactor(dy, height);
			innerRadius = minRadius * radiusFactor(dy, height);


			if (radius < 0) {
				continue;
			}

			int finalDy = dy;
			Shapes.canopyCircle(pos, radius, innerRadius, position -> {
				if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
					if (finalDy == (height / 3)) {
						growDangingBit(blocks, world, pos, boundingBox, rand);
					}
				}
			});
		}
	}

	// Provides the factor to the radius, where x is a double from 0.0 to the height that represents the progress along the trunk.
	private double radiusFactor(double x, double height) {
		//makes the polynomial apply to values from 0-the height
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a willow tree. from 0-1
		return 1.88 * (x * x * x) - 6.52 * (x * x) + 4.63 * x;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int dY = origin.getY(); dY < height; dY++) {
			for (int dZ = -radius; dZ <= radius; dZ++) {
				for (int dX = -radius; dX <= radius; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		pos.set(origin.getX(), origin.getY() + height, origin.getZ());

		for (int i = 0; i < 4; i++) {
			if (!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		return true;
	}
}