package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.QuarterLogBlock;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.PortUtil;
import com.terraformersmc.terrestria.feature.trees.components.Roots;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeagrassBlock;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class CanopyTreeFeatureMega extends AbstractTreeFeature<BranchedTreeFeatureConfig> implements Roots {
	private TreeDefinition.Mega tree;

	public CanopyTreeFeatureMega(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Mega tree) {
		super(function);

		this.tree = tree;
	}

	protected static boolean canTreeReplace(TestableWorld world, BlockPos pos) {
		return AbstractTreeFeature.canTreeReplace(world, pos) || world.testBlockState(pos,
				state -> state.getFluidState().getFluid().matches(FluidTags.WATER) || state.getBlock() instanceof SeagrassBlock
		);
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total trunk height
		int height = getHeight(rand);

		// How much "bare trunk" there will be. (2-3)
		int bareTrunkHeight = getBareTrunkHeight(rand);

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		int down = 0;
		while (world.testBlockState(below, state -> state.getFluidState().getFluid().matches(FluidTags.WATER) || state.getBlock() instanceof SeagrassBlock)) {
			below = below.down();
			down++;
		}

		height += down * 3 / 2;
		bareTrunkHeight += down * 3 / 2;

		origin = below.up();

		for (int dZ = 0; dZ < 2; dZ++) {
			for (int dX = 0; dX < 2; dX++) {
				below = origin.add(dX, -1, dZ);

				if (!isNaturalDirtOrGrass(world, below)) {
					return false;
				}
			}
		}

		if (!checkForObstructions(world, origin, height, bareTrunkHeight)) {
			return false;
		}

		for (int dZ = 0; dZ < 2; dZ++) {
			for (int dX = 0; dX < 2; dX++) {
				setToDirt(world, origin.add(dX, -1, dZ));
			}
		}

		growTrunk(logs, world, new BlockPos.Mutable(origin), height / 2, box);

		BlockPos.Mutable pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight);
		growBranches(logs, leaves, world, pos, height - bareTrunkHeight, height / 2 - bareTrunkHeight, rand, box);

		pos.set(origin).setOffset(Direction.DOWN, 2);
		growRoots(logs, world, pos, bareTrunkHeight + 2, rand, box);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int dY = 0; dY < bareTrunkHeight; dY++) {
			for (int dZ = 0; dZ < 2; dZ++) {
				for (int dX = 0; dX < 2; dX++) {

					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		for (int dY = bareTrunkHeight; dY < height + 1; dY++) {
			for (int dZ = -7; dZ < 8; dZ++) {
				for (int dX = -7; dX < 8; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if (!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
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

	private void growBranches(Set<BlockPos> logs, Set<BlockPos> leaves, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, int trunkHeight, Random rand, BlockBox box) {
		int branches = rand.nextInt(10) + 15;

		int[] offset = new int[3];
		BlockPos origin = pos.toImmutable();

		for (int branch = 0; branch < branches; branch++) {
			int startOffsetY = rand.nextInt(height);

			offset[Shapes.X] = MathHelper.clamp(rand.nextInt(13) - 6, -5, 5);
			offset[Shapes.Y] = rand.nextInt(height - 3) + trunkHeight / 2;
			offset[Shapes.Z] = MathHelper.clamp(rand.nextInt(13) - 6, -5, 5);

			int alignX = offset[Shapes.X] > 0 ? 1 : 0;
			int alignZ = offset[Shapes.Z] > 0 ? 1 : 0;

			pos.set(origin);
			pos.add(alignX, startOffsetY, alignZ);

			Shapes.line(pos, offset, position -> {
				if (!world.testBlockState(position, candidate -> candidate.getBlock() instanceof QuarterLogBlock)) {
					PortUtil.setBlockState(logs, world, pos, tree.getLog(), box);
				}
			});

			int maxRadius = Math.min(
					Math.min(
							Math.min(8 - offset[Shapes.X], 8 - offset[Shapes.Z]),
							Math.min(8 + offset[Shapes.X] - 1, 8 + offset[Shapes.Z] - 1)
					),
					4
			);

			int x = pos.getX();
			int z = pos.getZ();
			int radius = maxRadius == 4 ? 3 + rand.nextInt(2) : 2 + rand.nextInt(maxRadius - 1);

			pos.setOffset(Direction.DOWN);

			for (int i = -1; i <= 1; i++) {
				pos.set(x, pos.getY(), z);

				int layerRadius = (int) Math.floor(radius * Math.cos(i * Math.PI / 6));

				Shapes.circle(pos, layerRadius, position -> {
					if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						PortUtil.setBlockState(leaves, world, pos, tree.getLeaves(), box);
					}
				});

				pos.setOffset(Direction.UP);
			}
		}
	}

	public void growRoots(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, BlockBox box) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(logs, world, pos.set(x - 1, y, z + rand.nextInt(2)), baseTrunkHeight, rand, box);
		tryGrowRoot(logs, world, pos.set(x + 2, y, z + rand.nextInt(2)), baseTrunkHeight, rand, box);
		tryGrowRoot(logs, world, pos.set(x + rand.nextInt(2), y, z - 1), baseTrunkHeight, rand, box);
		tryGrowRoot(logs, world, pos.set(x + rand.nextInt(2), y, z + 2), baseTrunkHeight, rand, box);
	}

	public void tryGrowRoot(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable bottom, int baseTrunkHeight, Random rand, BlockBox box) {
		if (rand.nextInt(5) == 0) {
			return;
		}

		int height = baseTrunkHeight + rand.nextInt(2) - 3;

		for (int i = 0; i < height; i++) {
			if (canTreeReplace(world, bottom) || AbstractTreeFeature.isReplaceablePlant(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				PortUtil.setBlockState(logs, world, bottom, tree.getBark(), box);
			}

			bottom.setOffset(Direction.UP);
		}
	}

	public int getHeight(Random rand) {
		return rand.nextInt(8) + 19;
	}

	public int getBareTrunkHeight(Random rand) {
		return 2 + rand.nextInt(2);
	}
}
