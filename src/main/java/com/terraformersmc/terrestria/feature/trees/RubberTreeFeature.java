package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class RubberTreeFeature extends AbstractTreeFeature<BranchedTreeFeatureConfig> {
	public RubberTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function) {
		super(function);
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
		growTrunk(world, rand, origin.mutableCopy(), logs, box, config, height);
		growBranches(world, rand, origin.mutableCopy(), logs, leaves, box, config, height);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height) {
		BlockPos.Mutable pos = origin.mutableCopy();

		for (int dY = 0; dY < height; dY++) {
			pos.setY(origin.getY() + dY);

			if (!canTreeReplace(world, pos)) {
				return false;
			}
		}

		return true;
	}

	private void growTrunk(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, BlockBox box, BranchedTreeFeatureConfig config, int height) {
		for (int i = 0; i < height; i++) {
			setLogBlockState(world, rand, pos, logs, box, config);
			pos.move(Direction.UP);
		}
	}

	private void growBranches(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config, int height) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int branch = 0; branch < 16; branch++) {
			int baseY = rand.nextInt(height - 2) + 4;

			float length = rand.nextFloat() * 7 + 2;
			float angle = rand.nextFloat() * (float) Math.PI * 2;

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

				setLogBlockState(world, rand, pos, logs, box, config);

				for (Direction direction : Direction.values()) {
					pos.set(x + movedX, y + baseY + offsetY, z + movedZ);
					pos.move(direction);

					setLeavesBlockState(world, rand, pos, leaves, box, config);
				}
			}
		}
	}
}
