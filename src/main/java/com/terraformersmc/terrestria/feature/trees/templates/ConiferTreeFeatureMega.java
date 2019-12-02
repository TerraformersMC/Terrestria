package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.ExtendedLeavesBlock;
import com.terraformersmc.terraform.block.QuarterLogBlock;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.PortUtil;
import com.terraformersmc.terrestria.feature.trees.components.Roots;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ConiferTreeFeatureMega extends AbstractTreeFeature<BranchedTreeFeatureConfig> implements Roots {
	private static final int EXTRA_LEAVES_HEIGHT = 2;
	private TreeDefinition.Mega tree;
	private int height;
	private int bareTrunkHeight;

	public ConiferTreeFeatureMega(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Mega tree) {
		super(function);

		this.tree = tree;
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total trunk height
		int height = getHeight(rand);

		// How much "bare trunk" there will be.
		int bareTrunkHeight = getBareTrunkHeight(rand);

		// Maximum leaf radius.
		// Note: Old EBXL had a maximum radius of 10, but unfortunately that would cause cascading world generation.
		// Hey, the trees are pretty massive already.
		int maxRadius = 2 + rand.nextInt(6);

		if (origin.getY() + height + 1 + EXTRA_LEAVES_HEIGHT > 256 || origin.getY() < 1) {
			return false;
		}

		for (int dZ = 0; dZ < 2; dZ++) {
			for (int dX = 0; dX < 2; dX++) {
				BlockPos below = origin.add(dX, -1, dZ);

				if (!isNaturalDirtOrGrass(world, below)) {
					return false;
				}
			}
		}

		if (!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		for (int dZ = 0; dZ < 2; dZ++) {
			for (int dX = 0; dX < 2; dX++) {
				setToDirt(world, origin.add(dX, -1, dZ));
			}
		}

		growLeaves(logs, world, origin, height, bareTrunkHeight, maxRadius, box);
		growTrunk(logs, world, new BlockPos.Mutable(origin), height, box);
		growRoots(logs, world, new BlockPos.Mutable(origin), bareTrunkHeight + 2, rand, box);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int i = 0; i < bareTrunkHeight; i++) {
			boolean canReplaceAll =
					canTreeReplace(world, pos.set(origin.getX(), origin.getY() + i, origin.getZ())) &&
							canTreeReplace(world, pos.set(origin.getX() + 1, origin.getY() + i, origin.getZ())) &&
							canTreeReplace(world, pos.set(origin.getX(), origin.getY() + i, origin.getZ() + 1)) &&
							canTreeReplace(world, pos.set(origin.getX() + 1, origin.getY() + i, origin.getZ() + 1));

			if (!canReplaceAll) {
				return false;
			}
		}

		for (int dY = bareTrunkHeight; dY < height + EXTRA_LEAVES_HEIGHT; dY++) {
			for (int dZ = -radius; dZ <= radius + 1; dZ++) {
				for (int dX = -radius; dX <= radius + 1; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	private void growLeaves(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius, BlockBox box) {
		int radius = 0;
		int radiusTarget = 1;
		boolean topCone = true;

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int dY = height + EXTRA_LEAVES_HEIGHT; dY >= bareTrunkHeight; dY--) {
			for (int dZ = -radius; dZ <= radius + 1; dZ++) {
				for (int dX = -radius; dX <= radius + 1; dX++) {
					int aZ = dZ < 1 ? -dZ : dZ - 1;
					int aX = dX < 1 ? -dX : dX - 1;

					if (radius > 0 && aZ == radius && aX == radius) {
						// Cull corners
						continue;
					}

					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						int distance = aZ + aX;
						int extra = dY - height + 1;

						if(extra > 0) {
							distance += extra;
						}

						PortUtil.setBlockState(logs, world, pos, tree.getLeaves().with(ExtendedLeavesBlock.DISTANCE, Math.max(distance, 1)), box);
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

	private void growTrunk(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, BlockBox box) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int i = 0; i < height; i++) {
			pos.set(x, y + i, z);
			PortUtil.setBlockState(logs, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHWEST), box);

			pos.set(x + 1, y + i, z);
			PortUtil.setBlockState(logs, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHEAST), box);

			pos.set(x, y + i, z + 1);
			PortUtil.setBlockState(logs, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHWEST), box);

			pos.set(x + 1, y + i, z + 1);
			PortUtil.setBlockState(logs, world, pos, tree.getLogQuarter().with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHEAST), box);
		}
	}

	public int getHeight(Random rand) {
		return rand.nextInt(16) + 32;
	}

	public int getBareTrunkHeight(Random rand) {
		return 1 + rand.nextInt(12);
	}

	@Override
	public void growRoots(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, BlockBox box) {

	}

	@Override
	public void tryGrowRoot(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable bottom, int baseTrunkHeight, Random rand, BlockBox box) {

	}
}
