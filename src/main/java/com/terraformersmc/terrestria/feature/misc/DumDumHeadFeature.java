package com.terraformersmc.terrestria.feature.misc;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class DumDumHeadFeature extends Feature<NoneFeatureConfiguration> {
	private static final BlockState PRIMARY_BLOCK = TerrestriaBlocks.VOLCANIC_ROCK.plain.full.defaultBlockState();
	private static final BlockState MOSS_BLOCK = TerrestriaBlocks.VOLCANIC_ROCK.mossyCobblestone.full.defaultBlockState();
	private static final BlockState FEATURE_BLOCK = TerrestriaBlocks.VOLCANIC_ROCK.smooth.full.defaultBlockState();

	public DumDumHeadFeature(Codec<NoneFeatureConfiguration> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos blockPos = context.origin();

		// Check that we won't pass build height
		if (blockPos.getY() + 8 > level.getMaxY() || blockPos.getY() < level.getMinY()) {
			return false;
		}

		// Check to see if the block underneath is good for placement
		if (!level.isStateAtPosition(blockPos.below(), blockState -> blockState.is(BlockTags.SAND))) {
			return false;
		}

		// Generate the head base rectangle prism
		BlockPos.MutableBlockPos pos = blockPos.mutable();
		pos.move(-1, 0, -1);
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 3; x++) {
				for (int z = 0; z < 3; z++) {
					if ((float) random.nextInt(y + 1) / 3 < .15) {
						level.setBlock(pos, MOSS_BLOCK, 1);
					} else {
						level.setBlock(pos, PRIMARY_BLOCK, 1);
					}
					pos.move(0, 0, 1);
				}
				pos.move(0, 0, -3);
				pos.move(1, 0, 0);
			}
			pos.move(-3, 0, 0);
			pos.move(Direction.UP);
		}


		// Put a face on it
		pos = blockPos.mutable();
		pos.move(Direction.UP, 4);

		// Get a random Direction for the face to be placed on
		Direction direction;
		do {
			direction = Direction.getRandom(random);
		} while (direction.getAxis().equals(Direction.Axis.Y));

		// Get the opposite axis for generating features on the directed face
		Direction invAxisDirection;
		if (direction.getAxis().equals(Direction.Axis.X)) {
			invAxisDirection = Direction.NORTH;
		} else {
			invAxisDirection = Direction.WEST;
		}

		// Generate Brow
		pos.move(direction);
		pos.move(invAxisDirection.getOpposite(), 2);
		for (int i = 0; i < 3; i++) {
			pos.move(invAxisDirection, 1);
			level.setBlock(pos, FEATURE_BLOCK, 1);
		}

		// Generate Eyes
		pos.move(Direction.DOWN);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 0);
		pos.move(invAxisDirection.getOpposite(), 2);
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), 0);

		// Generate Nose
		pos.move(direction);
		pos.move(invAxisDirection);
		pos.move(Direction.DOWN);
		level.setBlock(pos, FEATURE_BLOCK, 0);
		pos.move(Direction.DOWN);
		level.setBlock(pos, FEATURE_BLOCK, 0);

		return true;
	}
}
