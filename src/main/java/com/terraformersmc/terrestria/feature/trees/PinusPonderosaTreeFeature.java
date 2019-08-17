package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

// Based on RTG's Pinus Ponderosa tree
public class PinusPonderosaTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public PinusPonderosaTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	public PinusPonderosaTreeFeature sapling() {
		return new PinusPonderosaTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int heightBare = rand.nextInt(10) + 10;
		int height = heightBare + rand.nextInt(15) + 15;

		if (origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if (!isNaturalDirtOrGrass(world, below)
			|| !isNaturalDirtOrGrass(world, below.east())
			|| !isNaturalDirtOrGrass(world, below.west())
			|| !isNaturalDirtOrGrass(world, below.north())
			|| !isNaturalDirtOrGrass(world, below.south())) {
			return false;
		}

		/*if (!checkForObstructions(world, origin, height)) {
			return false;
		}*/

		BlockPos.Mutable position = new BlockPos.Mutable(origin);
		position.setOffset(Direction.DOWN);

		setBlockState(blocks, world, position, Blocks.DIRT.getDefaultState(), boundingBox);
		growBaseTrunk(blocks, world, rand, position.set(origin).setOffset(Direction.EAST), heightBare, boundingBox);
		growBaseTrunk(blocks, world, rand, position.set(origin).setOffset(Direction.WEST), heightBare, boundingBox);
		growBaseTrunk(blocks, world, rand, position.set(origin).setOffset(Direction.NORTH), heightBare, boundingBox);
		growBaseTrunk(blocks, world, rand, position.set(origin).setOffset(Direction.SOUTH), heightBare, boundingBox);

		position.set(origin);

		// Place the plain bare trunk (5 blocks)
		for(int dY = 0; dY < 5; dY++) {
			setBlockState(blocks, world, position, tree.getLog(), boundingBox);
			position.setOffset(Direction.UP);
		}

		// Place a sparsely covered trunk
		for(int dY = 5; dY < heightBare; dY++) {
			position.set(origin.getX(), origin.getY() + dY, origin.getZ());

			setBlockState(blocks, world, position, tree.getLog(), boundingBox);

			if(rand.nextInt(7) == 0) {
				placeBranch(blocks, world, rand, position, boundingBox, 2, 1);
			}
		}

		// Place the foliage-packed top part
		for(int dY = heightBare; dY < height; dY++) {
			position.set(origin.getX(), origin.getY() + dY, origin.getZ());

			setBlockState(blocks, world, position, tree.getLog(), boundingBox);

			if(dY < height - 2) {
				if(dY > height - 12 || dY < heightBare + 2 || rand.nextBoolean()) {
					placeBranch(blocks, world, rand, position, boundingBox, 3, 2);
				}

				for(int d = 0; d < 4; d++) {
					if(rand.nextBoolean()) {
						Direction direction = Direction.fromHorizontal(d);

						position.setOffset(direction);

						if(isAirOrLeaves(world, position)) {
							setBlockState(blocks, world, position, tree.getLeaves(), boundingBox);
						}

						position.setOffset(direction.getOpposite());
					}
				}
			}
		}

		// Make sure the top of the trunk is covered
		position.set(origin.getX(), origin.getY() + height - 1, origin.getZ());

		placeLeaves(blocks, world, position, boundingBox, 2);

		return true;
	}

	private void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos.Mutable origin, MutableIntBoundingBox boundingBox, int radius, int branchLength) {
		int x = origin.getX();
		int y = origin.getY();
		int z = origin.getZ();

		int offsetX = -1 + rand.nextInt(3);
		int offsetZ = -1 + rand.nextInt(3);

		if(offsetX == 0 && offsetZ == 0) {
			offsetX = -1 + rand.nextInt(3);
			offsetZ = -1 + rand.nextInt(3);
		}

		for(int i = 0; i < branchLength; i++) {
			origin.setOffset(offsetX, 0, offsetZ);

			if(canTreeReplace(world, origin)) {
				setBlockState(blocks, world, origin, tree.getLog(), boundingBox);

				continue;
			}

			// state is not replaceable
			if(i == 0) {
				origin.set(x, y, z);

				return;
			} else {
				origin.setOffset(-offsetX, 0, -offsetZ);

				break;
			}
		}

		offsetX *= branchLength;
		offsetZ *= branchLength;

		origin.set(x + offsetX, y, z + offsetZ);
		placeLeaves(blocks, world, origin, boundingBox, radius);

		origin.set(x, y, z);
	}

	private void placeLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, MutableIntBoundingBox boundingBox, int radius) {
		int x = origin.getX();
		int y = origin.getY();
		int z = origin.getZ();

		for(int dX = -1; dX <= 1; dX++) {
			int aX = Math.abs(dX);

			for(int dZ = -1; dZ <= 1; dZ++) {
				int aZ = Math.abs(dZ);

				for(int dY = 0; dY < 2; dY++) {
					origin.set(x + dX, y + dY, z + dZ);

					if(aX + aZ + dY < radius && isAirOrLeaves(world, origin)) {
						setBlockState(blocks, world, origin, tree.getLeaves(), boundingBox);
					}
				}
			}
		}

		origin.set(x, y, z);
	}

	// TODO
	private void growBaseTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos.Mutable origin, int height, MutableIntBoundingBox boundingBox) {
		int logHeight = (int) Math.ceil(height / 4.0);
		logHeight += rand.nextInt(logHeight * 2);

		origin.setOffset(Direction.DOWN);

		for(int dY = -1; dY < logHeight; dY++) {
			setBlockState(blocks, world, origin, tree.getLog(), boundingBox);
			origin.setOffset(Direction.UP);
		}
	}
}
