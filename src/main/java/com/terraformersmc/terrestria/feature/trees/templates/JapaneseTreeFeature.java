package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.feature.trees.PortUtil;
import com.terraformersmc.terrestria.feature.trees.components.Branches;
import com.terraformersmc.terrestria.feature.trees.components.SmallLogs;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.*;
import java.util.function.Function;

public abstract class JapaneseTreeFeature extends AbstractTreeFeature<BranchedTreeFeatureConfig> implements Branches, SmallLogs {
	public JapaneseTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function) {
		super(function);
	}

	@Override
	public boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config) {
		// Total trunk height (8-11)
		int height = rand.nextInt(4) + 8;

		// How much "bare trunk" there will be. (2-4)
		int bareTrunkHeight = 2 + rand.nextInt(3);

		// Maximum leaf radius.
		double maxRadius = 4.0 + 2.5 * rand.nextDouble();

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if (!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, bareTrunkHeight, (int) Math.ceil(maxRadius))) {
			return false;
		}

		// Place the log blocks making up the main trunk
		growTrunk(world, rand, new BlockPos.Mutable(origin), logs, box, config, height);

		// Grow leaves and branches from the trunk
		BlockPos.Mutable pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight);
		growLeaves(world, rand, pos, logs, leaves, box, config, height - bareTrunkHeight, maxRadius);

		// Fix up log block states if needed, such as with mini logs
		correctLogStates(logs, world, box);

		// TODO: Better check
		if(!world.testBlockState(origin, state -> state.getBlock() instanceof SmallLogBlock)) {
			setToDirt(world, below);
		}

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for (int i = 0; i < bareTrunkHeight; i++) {
			if (!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		for (int dY = bareTrunkHeight; dY < height + 1; dY++) {
			int radius = maxRadius * (int) Math.ceil(Math.cos((dY - bareTrunkHeight) * 1.3 / height));

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

	// Grows the center trunk and top leaves of the tree.
	private void growTrunk(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, BlockBox box, BranchedTreeFeatureConfig config, int height) {
		for (int i = 0; i < height; i++) {
			setLogBlockState(world, rand, pos, logs, box, config);
			pos.setOffset(Direction.UP);
		}
	}

	protected void tryPlaceLeaves(ModifiableTestableWorld world, Random rand, BlockPos pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config) {
		Block reference = config.trunkProvider.getBlockState(rand, pos).getBlock();

		if (reference instanceof SmallLogBlock && world.testBlockState(pos, candidate -> candidate.getBlock() == reference)) {
			world.testBlockState(pos, candidate ->
					PortUtil.setBlockState(logs, world, pos, candidate.with(SmallLogBlock.HAS_LEAVES, true), box)
			);
		} else {
			setLeavesBlockState(world, rand, pos, leaves, box, config);
		}
	}

	private void growLeaves(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, BranchedTreeFeatureConfig config, int height, double maxRadius) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		BlockPos origin = pos.toImmutable();

		List<Direction> directionSet = Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
		Collections.shuffle(directionSet);
		Iterator<Direction> directions = directionSet.iterator();

		for (int layer = 0; layer < 2; layer++) {
			int chance = 2 + layer * 5;
			double radius = maxRadius * Math.cos(layer * 1.3 / height);
			double innerRadius = Math.max(radius, 2) - 2;

			for (int i = 0; i < 2; i++) {
				pos.set(x, y + layer, z);

				Direction direction = directions.next();
				placeBranch(world, rand, pos, logs, leaves, box, config, direction, (int) Math.ceil(innerRadius));

				pos.setOffset(direction);

				tryPlaceLeaves(world, rand, pos, logs, leaves, box, config);
			}

			pos.set(x, y + layer, z);
			Shapes.canopyCircle(pos, radius, innerRadius,
					(position) -> {
						if (rand.nextInt(chance) == 0 && position.getX() != origin.getX() && position.getZ() != origin.getZ()) {
							return;
						}

						tryPlaceLeaves(world, rand, position, logs, leaves, box, config);
					}
			);
		}

		for (int layer = 2; layer < height + 1; layer++) {
			double radius = maxRadius * Math.cos(layer * 1.3 / height);
			double innerRadius = maxRadius * Math.max(Math.cos(layer * (Math.PI / 4.0) / height + (Math.PI / 3.0)), 0);

			if (layer < height) {
				Direction direction;

				switch (rand.nextInt(4)) {
					case 0:
						direction = Direction.EAST;
						break;
					case 1:
						direction = Direction.WEST;
						break;
					case 2:
						direction = Direction.SOUTH;
						break;
					default:
						direction = Direction.NORTH;
						break;
				}

				pos.set(x, y + layer, z);
				placeBranch(world, rand, pos, logs, leaves, box, config, direction, (int) Math.ceil(innerRadius));
			}

			pos.set(x, y + layer, z);
			Shapes.canopyCircle(pos, radius, innerRadius,
					(position) -> tryPlaceLeaves(world, rand, pos, logs, leaves, box, config)
			);
		}
	}
}
