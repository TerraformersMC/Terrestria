package com.terraformersmc.terrestria.feature.misc;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class DumDumHeadFeature extends Feature<DefaultFeatureConfig> {

	private static final BlockState PRIMARY_BLOCK = TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState();
	private static final BlockState MOSS_BLOCK = TerrestriaBlocks.VOLCANIC_ROCK.mossyCobblestone.full.getDefaultState();
	private static final BlockState FEATURE_BLOCK = TerrestriaBlocks.VOLCANIC_ROCK.smooth.full.getDefaultState();

	public DumDumHeadFeature(Codec<DefaultFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		Random random = context.getRandom();
		BlockPos blockPos = context.getOrigin();

		//Check that we wont pass build height
		if (blockPos.getY() + 8 > 256 || blockPos.getY() < 1) {
			return false;
		}

		//Check to see if the block underneath is good for placement
		if (!world.testBlockState(blockPos.down(), blockState -> blockState.isIn(BlockTags.SAND))) {
			return false;
		}

		//Generate the head base rectangle prism
		BlockPos.Mutable pos = blockPos.mutableCopy();
		pos.move(-1, 0, -1);
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
					if ((float) random.nextInt(y + 1) / 3 < .15) {
						world.setBlockState(pos, MOSS_BLOCK, 1);
					} else {
						world.setBlockState(pos, PRIMARY_BLOCK, 1);
					}
					pos.move(0, 0, 1);
				}
				pos.move(0, 0, -3);
				pos.move(1, 0, 0);
			}
			pos.move(-3, 0, 0);
			pos.move(Direction.UP);
		}


		//Put a face on it
		pos = blockPos.mutableCopy();
		pos.move(Direction.UP, 4);

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
		pos.move(direction);
		pos.move(invAxisDirection.getOpposite(), 2);
		for (int i = 0; i < 3; i++) {
			pos.move(invAxisDirection, 1);
			world.setBlockState(pos, FEATURE_BLOCK, 1);
		}

		//Generate Eyes
		pos.move(Direction.DOWN);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);
		pos.move(invAxisDirection.getOpposite(), 2);
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), 0);

		//Generate Nose
		pos.move(direction);
		pos.move(invAxisDirection);
		pos.move(Direction.DOWN);
		world.setBlockState(pos, FEATURE_BLOCK, 0);
		pos.move(Direction.DOWN);
		world.setBlockState(pos, FEATURE_BLOCK, 0);

		return true;
	}
}
