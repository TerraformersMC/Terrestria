package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
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

public class RubberTreeFeature extends AbstractTreeFeature<BranchedTreeFeatureConfig> {
	private TreeDefinition.Basic tree;

	public RubberTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Basic tree) {
		super(function);

		this.tree = tree;
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total trunk height
		int height = rand.nextInt(4) + 12;

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if (!checkForObstructions(world, origin, height)) {
			return false;
		}

		setToDirt(world, below);
		growTrunk(logs, world, new BlockPos.Mutable(origin), height, box);
		growBranches(logs, world, new BlockPos.Mutable(origin), height, rand, box);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int dY = 0; dY < height; dY++) {
			pos.setY(origin.getY() + dY);

			if (!canTreeReplace(world, pos)) {
				return false;
			}
		}

		return true;
	}

	private void growTrunk(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, BlockBox box) {
		int x = pos.getX();
		int z = pos.getZ();

		for (int i = 0; i < height; i++) {
			pos.set(x, pos.getY(), z);
			PortUtil.setBlockState(logs, world, pos, tree.getLog(), box);
			pos.setOffset(Direction.UP);
		}
	}

	private void growBranches(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, Random random, BlockBox box) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int branch = 0; branch < 16; branch++) {
			int baseY = random.nextInt(height - 2) + 4;

			float length = random.nextFloat() * 7 + 2;
			float angle = random.nextFloat() * (float) Math.PI * 2;

			int offsetX = (int) (MathHelper.cos(angle) * length);
			int offsetZ = (int) (MathHelper.sin(angle) * length);

			int moveX = offsetX > 0 ? 1 : -1;
			int moveZ = offsetZ > 0 ? 1 : -1;

			int absX = Math.abs(offsetX);
			int absZ = Math.abs(offsetZ);

			int movedX = 0;
			int movedZ = 0;

			float stepX = 1.0F / absX;
			float stepZ = 1.0F / absZ;

			for (int movement = 0; movement < absX + absZ; movement++) {
				if (Math.abs(movedX * stepX) < Math.abs(movedZ * stepZ) && Math.abs(movedX) < absX) {
					movedX += moveX;
				} else {
					movedZ += moveZ;
				}

				int offsetY = (int) (Math.sqrt(movedX * movedX + movedZ * movedZ) * 0.4);

				if (movedX > 2 || movedX < -2 || movedZ > 2 || movedZ < -2) {
					continue;
				}

				pos.set(x + movedX, y + baseY + offsetY, z + movedZ);

				if (!canTreeReplace(world, pos)) {
					break;
				}

				PortUtil.setBlockState(logs, world, pos, tree.getLog(), box);

				for (Direction direction : Direction.values()) {
					pos.set(x + movedX, y + baseY + offsetY, z + movedZ);
					pos.setOffset(direction);

					if (AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						PortUtil.setBlockState(logs, world, pos, tree.getLeaves(), box);
					}
				}
			}
		}
	}
}
