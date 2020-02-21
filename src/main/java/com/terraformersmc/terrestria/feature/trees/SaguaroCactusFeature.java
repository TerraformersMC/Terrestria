package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.feature.trees.components.Branches;
import com.terraformersmc.terrestria.feature.trees.components.Trunk;
import com.terraformersmc.terrestria.feature.trees.templates.SmallLogTree;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class SaguaroCactusFeature extends SmallLogTree implements Trunk, Branches {
	public SaguaroCactusFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> configDeserializer, boolean updateNeighbors, BlockState log, BlockState leaves) {
		super(configDeserializer, updateNeighbors, log, leaves);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random rand, BlockPos origin, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox boundingBox, BranchedTreeFeatureConfig treeFeatureConfig) {
		int height = rand.nextInt(2) + 4;

		// If the tree can pass the max build height
		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		if (!isNaturalDirtOrGrass(world, below) && !world.testBlockState(below, state -> state.matches(BlockTags.SAND))) {
			return false;
		}

		if (!checkForObstructions(world, origin, height, 2)) {
			return false;
		}

		growTrunk(logs, leaves, world, new BlockPos.Mutable(origin), height, boundingBox, treeFeatureConfig);
		return true;
	}

	public SaguaroCactusFeature sapling() {
		return new SaguaroCactusFeature(BranchedTreeFeatureConfig::deserialize, true, this.getLog(), this.getLeaves());
	}

	@Override
	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> blocks, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, int length) {
		pos.setOffset(direction);
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), direction, box);
			} else {
				setBlockStateAndUpdate(blocks, world, pos, this.getLog(), Direction.UP, box);
			}
			pos.setOffset(Direction.UP);
		}
	}

	@Override
	public void growTrunk(Set<BlockPos> logs, Set<BlockPos> leaves, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, BlockBox box, TreeFeatureConfig config) {
		Random random = new Random();
		Direction armDir = randomHorizontalDirection(random);
		for (int i = 0; i < height; i++) {

			if (i == 0) {
				setBlockState(world, pos, this.getLog().with(SmallLogBlock.DOWN, true));
			} else {
				setBlockStateAndUpdate(logs, world, pos, this.getLog(), Direction.UP, box);
			}

			// Always place an arm half way up ish
			if (i == ((int) height / 2) - 1) {
				placeBranch(world, random, new BlockPos.Mutable(pos), logs, null, box, config, armDir, random.nextInt(1) + 2);
			}
			// Half of the time place a second arm one block higher in a different direction
			if (i == ((int) height / 2)) {
				if (random.nextBoolean()) {
					placeBranch(world, random, new BlockPos.Mutable(pos), logs, null, box, config, randomHorizontalDirectionAwayFrom(random, armDir), random.nextInt(1) + 2);
				}
			}
			pos.setOffset(Direction.UP);
		}
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
