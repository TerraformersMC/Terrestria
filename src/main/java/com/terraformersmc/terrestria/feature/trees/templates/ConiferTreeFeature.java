package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ConiferTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public ConiferTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	public ConiferTreeFeature sapling() {
		return new ConiferTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {

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

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);
		growLeaves(blocks, world, origin, height, bareTrunkHeight, maxLeafRadius, boundingBox);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int i = 0; i < bareTrunkHeight; i++) {
			if (!canTreeReplace(world, pos.setOffset(Direction.UP))) {
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

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius, MutableIntBoundingBox boundingBox) {
		int radius = 0;
		int radiusTarget = 1;
		boolean topCone = true;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

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
						setBlockState(blocks, world, pos, tree.getLeaves().with(ExtendedLeavesBlock.DISTANCE, Math.max(aZ + aX, 1)), boundingBox);
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

	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		for (int i = 0; i < height; i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);

			pos.setOffset(Direction.UP);
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