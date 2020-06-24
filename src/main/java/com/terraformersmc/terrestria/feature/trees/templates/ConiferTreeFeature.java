package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.trees.AbstractTreeFeature;
import com.terraformersmc.terrestria.feature.trees.PortUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;

public class ConiferTreeFeature extends AbstractTreeFeature<TreeFeatureConfig> {

	public ConiferTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config) {

		int height = getLeafHeight(rand);
		int bareTrunkHeight = getBareTrunkHeight(rand);
		int maxLeafRadius = getMaxLeafRadius(rand);

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, bareTrunkHeight, maxLeafRadius)) {
			return false;
		}

		setToDirt(world, origin.down());
		growTrunk(world, rand, origin.mutableCopy(), logs, box, config, height);
		growLeaves(world, rand, origin, leaves, box, config, height, bareTrunkHeight, maxLeafRadius);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.Mutable pos = origin.mutableCopy();

		for (int i = 0; i < bareTrunkHeight; i++) {
			if (!canTreeReplace(world, pos.move(Direction.UP))) {
				return false;
			}
		}

		for (int dY = bareTrunkHeight; dY < height; dY++) {
			for (int dZ = -radius; dZ <= radius; dZ++) {
				for (int dX = -radius; dX <= radius; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void growLeaves(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, int height, int bareTrunkHeight, int maxRadius) {
		int radius = 0;
		int radiusTarget = 1;
		boolean topCone = true;

		BlockPos.Mutable pos = origin.mutableCopy();

		for (int dY = height; dY >= bareTrunkHeight; dY--) {
			for (int dZ = -radius; dZ <= radius; dZ++) {
				for (int dX = -radius; dX <= radius; dX++) {
					int aZ = Math.abs(dZ);
					int aX = Math.abs(dX);

					if (radius > 0 && aZ == radius && aX == radius) {
						// Cull corners
						continue;
					}

					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						int distance = Math.max(aZ + aX, 1);

						PortUtil.setLeavesWithDistance(world, rand, pos, leaves, box, config, distance);
					}
				}
			}

			radius += 1;

			if (radius > radiusTarget) {
				if (topCone) {
					radius = 0;
					radiusTarget = Math.min(2, maxRadius);
					topCone = false;
				} else {
					radius = 1;
					radiusTarget = Math.min(radiusTarget + 1, maxRadius);
				}
			}
		}
	}

	private void growTrunk(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, BlockBox box, TreeFeatureConfig config, int height) {
		for (int i = 0; i < height; i++) {
			setLogBlockState(world, rand, pos, logs, box, config);

			pos.move(Direction.UP);
		}
	}

	public int getLeafHeight(Random rand) {
		return rand.nextInt(8) + 24;
	}

	public int getBareTrunkHeight(Random rand) {
		return 1 + rand.nextInt(12);
	}

	public int getMaxLeafRadius(Random rand) {
		return 2 + rand.nextInt(6);
	}
}
