package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.util.Shapes;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WillowTreeFeature extends AbstractTreeFeature<BranchedTreeFeatureConfig> {
	public WillowTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total tree height
		int height = rand.nextInt(3) + 8;

		// Maximum leaf radius.
		double maxRadius = 5 + 3 * rand.nextDouble();

		// Minimum leaf radius
		double minRadius = 1.25 + 2 * rand.nextDouble();

		// If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		// Make sure the tree can grow where the origin is
		BlockPos below = origin.down();

		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}
		if (!checkForObstructions(world, origin, height, (int) Math.ceil(maxRadius))) {
			return false;
		}

		setToDirt(world, below);

		// Grow the trunk
		growTrunk(world, rand, origin.mutableCopy(), logs, leaves, box, config, height);
		growBranches(world, rand, origin.offset(Direction.UP, height / 3).mutableCopy(), logs, box, config, (int) maxRadius - 1);
		growBranches(world, rand, origin.offset(Direction.UP, (height / 2) + 1).mutableCopy(), logs, box, config, (int) (maxRadius * radiusFactor(height / 2, height)));

		// Grow the leaves
		BlockPos.Mutable pos = origin.mutableCopy();
		growLeaves(world, rand, pos, leaves, box, config, height, maxRadius, minRadius);

		return true;
	}

	// Grows the center trunk.
	private void growTrunk(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config, int height) {
		// Grows the trunk at 80% of it's total height (so the trunk doesn't poke out)
		for (int i = 0; i < (height * .8); i++) {
			setLogBlockState(world, rand, pos, logs, box, config);
			pos.move(Direction.UP);
		}

		// Make sure there are leaf blocks on the top of the tree
		Shapes.circle(pos, 1.5, position -> {
			setLeavesBlockState(world, rand, pos, leaves, box, config);
		});
	}

	private void growBranches(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, BlockBox box, BranchedTreeFeatureConfig config, int maxRadius) {
		// Save original origin for use in z branch

		BlockPos origin = pos.toImmutable();
		int branchLength = maxRadius - 2;

		pos.move(Direction.WEST, branchLength);
		for (int x = -branchLength; x <= branchLength; x++) {
			if (isAirOrLeaves(world, pos)) {
				BlockState state = config.trunkProvider.getBlockState(rand, pos);

				PortUtil.setBlockState(logs, world, pos, state.with(PillarBlock.AXIS, Direction.WEST.getAxis()), box);
			}
			pos.move(Direction.EAST);
		}

		pos.set(origin).move(Direction.NORTH, branchLength);
		for (int z = -branchLength; z <= branchLength; z++) {
			if (isAirOrLeaves(world, pos)) {
				BlockState state = config.trunkProvider.getBlockState(rand, pos);

				PortUtil.setBlockState(logs, world, pos, state.with(PillarBlock.AXIS, Direction.NORTH.getAxis()), box);
			}
			pos.move(Direction.SOUTH);
		}

		// Reset the branches
		pos.set(origin);
	}

	private void growDangingBit(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		int randHolder;

		// 33% chance of a dangling bit generating.
		if (rand.nextInt(3) == 1) {
			// Length of the bit

			randHolder = rand.nextInt(3);
			for (int d = 0; d < randHolder; d++) {
				pos.move(Direction.DOWN);
				setLeavesBlockState(world, rand, pos, leaves, box, config);
			}

			pos.move(Direction.UP, randHolder);
		}
	}

	// ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config

	private void growLeaves(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config, int height, double maxRadius, double minRadius) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double radius;
		double innerRadius;

		for (int dy = 0; dy < height; dy++) {

			// If the leaves aren't 1/3 way up the trunk
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
				if (setLeavesBlockState(world, rand, pos, leaves, box, config) && finalDy == (height / 3)) {
					growDangingBit(world, rand, pos, leaves, box, config);
				}
			});
		}
	}

	// Provides the factor to the radius, where x is a double from 0.0 to the height that represents the progress along the trunk.
	private double radiusFactor(double x, double height) {
		// Makes the polynomial apply to values from 0-the height
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a willow tree. from 0-1
		return 1.88 * (x * x * x) - 6.52 * (x * x) + 4.63 * x;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int radius) {
		BlockPos.Mutable pos = origin.mutableCopy();

		for (int dY = height / 3; dY < height; dY++) {
			for (int dZ = -radius; dZ <= radius; dZ++) {
				for (int dX = -radius; dX <= radius; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		pos.set(origin.getX(), origin.getY(), origin.getZ());

		for (int i = 0; i < height / 3; i++) {
			if (!canTreeReplace(world, pos.move(Direction.UP))) {
				return false;
			}
		}

		return true;
	}
}
