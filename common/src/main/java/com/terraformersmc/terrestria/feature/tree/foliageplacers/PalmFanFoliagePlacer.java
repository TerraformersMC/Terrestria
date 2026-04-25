package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageSetter;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFanFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<PalmFanFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) ->
			foliagePlacerParts(instance).apply(instance, PalmFanFoliagePlacer::new));

	public PalmFanFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.PALM_TOP;
	}

	@Override
	protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {

		// The origin of this leaf piece
		BlockPos center = treeNode.pos().immutable();

		// The working mutable position
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		// Determine weather this tree should have it's spiral flipped to make it have more variation among trees
		boolean flipSpiral = random.nextBoolean();

		// Place the top blocks
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, 0), placer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(1, 1, 0), placer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, 1), placer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(-1, 1, 0), placer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, -1), placer, config);

		// Place supports for dangly bits
		for (int dZ = -1; dZ < 2; dZ++) {
			for (int dX = -1; dX < 2; dX++) {
				checkAndSetBlockState(world, random, pos.set(center).move(dZ, 0, dX), placer, config);
			}
		}

		// Place 2 dangly bits in each direction
		for (int d = 0; d < 4; d++) {
			Direction direction = Direction.from2DDataValue(d);

			pos.set(center).move(direction, 2);
			placeSpiral(world, random, pos, placer, config, direction, !flipSpiral);

			pos.set(center).move(direction, 3);
			placeSpiral(world, random, pos, placer, config, direction, flipSpiral);
		}
	}

	private void placeSpiral(LevelSimulatedReader world, RandomSource rand, BlockPos.MutableBlockPos pos, FoliageSetter placer, TreeConfiguration config, Direction direction, boolean invertLeafSpiral) {
		// Base of dangly bit
		checkAndSetBlockState(world, rand, pos, placer, config);

		// Get the direction of the twist from the direction of the branch then place a block there
		Direction spiral = spiral(direction, invertLeafSpiral);
		checkAndSetBlockState(world, rand, pos.move(spiral), placer, config);

		// Continue the branch all the way down
		for (int i = 0; i < 2; i++) {
			checkAndSetBlockState(world, rand, pos.move(Direction.DOWN), placer, config);
		}
	}

	private void checkAndSetBlockState(LevelSimulatedReader world, RandomSource random, BlockPos.MutableBlockPos currentPosition, FoliageSetter placer, TreeConfiguration config) {
		if (TreeFeature.validTreePos(world, currentPosition)) {
			placer.set(currentPosition.immutable(), config.foliageProvider.getState(random, currentPosition));
		}
	}

	/**
	 * @param direction the direction from the trunk the current spiraling piece needs to be calculated for
	 * @param invert whether the direction of the spiral is to be inverted (this is to make variation in the trees)
	 * @return the direction of the resulting twist in the direction provided
	 */
	private static Direction spiral(Direction direction, boolean invert) {
		switch (direction) {
			case EAST:
				return invert ? Direction.NORTH : Direction.SOUTH;
			case WEST:
				return invert ? Direction.SOUTH : Direction.NORTH;
			case NORTH:
				return invert ? Direction.WEST : Direction.EAST;
			case SOUTH:
			default:
				return invert ? Direction.EAST : Direction.WEST;
		}
	}

	@Override
	public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return baseHeight == dz && dy == dz;
	}
}
