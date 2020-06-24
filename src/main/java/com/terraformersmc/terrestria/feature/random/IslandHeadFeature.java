package com.terraformersmc.terrestria.feature.random;

import com.mojang.serialization.Dynamic;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class IslandHeadFeature extends TreeFeature {

	private static final BlockState PRIMARY_BLOCK = TerrestriaBlocks.BASALT.plain.full.getDefaultState();
	private static final BlockState MOSS_BLOCK = TerrestriaBlocks.BASALT.mossyCobblestone.full.getDefaultState();
	private static final BlockState FEATURE_BLOCK = TerrestriaBlocks.BASALT.smooth.full.getDefaultState();

	public IslandHeadFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> function) {
		super(function);
	}

	private boolean checkObstructions(TestableWorld world, BlockPos origin) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int y = 0; y < 8; y++) {
			int radius = 2;

			for(int z = -radius; z <= radius; z++) {
				for(int x = -radius; x <= radius; x++) {
					pos.set(origin).setOffset(x, y, z);

					if(!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random random, BlockPos blockPos, Set<BlockPos> set, Set<BlockPos> set2, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

		//Check that we wont pass build height
		if (blockPos.getY() + 8 > 256 || blockPos.getY() < 1) {
			return false;
		}

		//Check that it is on a block tagged sand
		BlockPos below = blockPos.down();
		if (!isNaturalDirtOrGrass(world, below)) {
			if(!world.testBlockState(below, state -> state.matches(BlockTags.SAND))) {
				return false;
			}
		}

		//Check for other obstructions
		if(!checkObstructions(world, blockPos)) {
			return false;
		}

		//Generate the head base rectangle prism
		BlockPos.Mutable pos = new BlockPos.Mutable(blockPos);
		pos.setOffset(-1, 0, -1);
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
					if ((float) random.nextInt(y + 1) / 3 < .15) {
						world.setBlockState(pos, MOSS_BLOCK, 1);
					} else {
						world.setBlockState(pos, PRIMARY_BLOCK, 1);
					}
					pos.setOffset(0, 0, 1);
				}
				pos.setOffset(0, 0, -3);
				pos.setOffset(1, 0, 0);
			}
			pos.setOffset(-3, 0, 0);
			pos.setOffset(Direction.UP);
		}


		//Put a face on it
		pos = new BlockPos.Mutable(blockPos);
		pos.setOffset(Direction.UP, 4);

		//Get a random Direction for the face to be placed on
		Direction direction;
		do {
			direction = Direction.random(random);
		} while (direction.getAxis().equals(Direction.Axis.Y));

		//Get the opposite axis for generating features on the directed face
		Direction invAxisDirection;
		if (direction.getAxis().equals(Direction.Axis.X)) {
			invAxisDirection = Direction.NORTH;
		} else {
			invAxisDirection = Direction.WEST;
		}

		//Generate Brow
		pos.setOffset(direction);
		pos.setOffset(invAxisDirection.getOpposite(), 2);
		for (int i = 0; i < 3; i++) {
			pos.setOffset(invAxisDirection, 1);
			world.setBlockState(pos, FEATURE_BLOCK, 1);
		}

		//Generate Eyes
		pos.setOffset(Direction.DOWN);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
		pos.setOffset(invAxisDirection.getOpposite(), 2);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);

		//Generate Nose
		pos.setOffset(direction);
		pos.setOffset(invAxisDirection);
		pos.setOffset(Direction.DOWN);
		world.setBlockState(pos, FEATURE_BLOCK, 0);
		pos.setOffset(Direction.DOWN);
		world.setBlockState(pos, FEATURE_BLOCK, 0);

		return true;
	}
}

