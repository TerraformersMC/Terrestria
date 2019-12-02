package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.util.Shapes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class CypressTreeFeature extends AbstractTreeFeature<BranchedTreeFeatureConfig> {
	public CypressTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total tree height
		int height = rand.nextInt(5) + 12;

		// Maximum leaf radius.
		double maxRadius = 1.5 + 1.5 * rand.nextDouble();

		//If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, (int) Math.ceil(maxRadius))) {
			return false;
		}

		setToDirt(world, below);

		growTrunk(logs, rand, world, new BlockPos.Mutable(origin), height, box, config);

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		growLeaves(leaves, rand, world, pos, height, maxRadius, box, config);

		return true;
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

	// Grows the center trunk.
	private void growTrunk(Set<BlockPos> logs, Random rand, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, BlockBox box, BranchedTreeFeatureConfig config) {
		for (int i = 0; i < (height * .6); i++) {
			PortUtil.setBlockState(logs, world, pos, config.trunkProvider.getBlockState(rand, pos), box);
			pos.setOffset(Direction.UP);
		}
	}

	private void growLeaves(Set<BlockPos> leaves, Random rand, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, double maxRadius, BlockBox box, BranchedTreeFeatureConfig config) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double radius;

		for (int dy = 0; dy < height; dy++) {
			pos.set(x, y + dy, z);

			radius = maxRadius * radiusFactor(dy, height);

			if (radius < 0) {
				continue;
			}

			Shapes.circle(pos, radius, position -> {
				if (AbstractTreeFeature.isAirOrLeaves(world, position)) {
					PortUtil.setBlockState(leaves, world, pos, config.leavesProvider.getBlockState(rand, position), box);
				}
			});
		}
	}

	// Provides the factor to the radius, where x is a double from 0.0 to 1.0 that represents the progress along the trunk.
	private double radiusFactor(double x, double height) {
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a cypress tree - increasing rapidly, and then tapering off.
		return 6.25 * (x * x * x) - 12.5 * (x * x) + 6.25 * x;
	}
}
