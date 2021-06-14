package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class PalmFanFoliagePlacer extends FoliagePlacer {

	public static final Codec<PalmFanFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) ->
			fillFoliagePlacerFields(instance).apply(instance, PalmFanFoliagePlacer::new));

	public PalmFanFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.PALM_TOP;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {

		//The origin of this leaf piece
		BlockPos center = treeNode.getCenter().toImmutable();

		//The working mutable position
		BlockPos.Mutable pos = new BlockPos.Mutable();

		//Determine weather this tree should have it's spiral flipped to make it have more variation among trees
		boolean flipSpiral = random.nextBoolean();

		//Place the top blocks
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, 0), replacer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(1, 1, 0), replacer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, 1), replacer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(-1, 1, 0), replacer, config);
		checkAndSetBlockState(world, random, pos.set(center).move(0, 1, -1), replacer, config);

		//Place supports for dangly bits
		for (int dZ = -1; dZ < 2; dZ++) {
			for (int dX = -1; dX < 2; dX++) {
				checkAndSetBlockState(world, random, pos.set(center).move(dZ, 0, dX), replacer, config);
			}
		}

		//Place 2 dangly bits in each direction
		for (int d = 0; d < 4; d++) {
			Direction direction = Direction.fromHorizontal(d);

			pos.set(center).move(direction, 2);
			placeSpiral(world, random, pos, replacer, config, direction, !flipSpiral);

			pos.set(center).move(direction, 3);
			placeSpiral(world, random, pos, replacer, config, direction, flipSpiral);
		}
	}

	private void placeSpiral(TestableWorld world, Random rand, BlockPos.Mutable pos, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config, Direction direction, boolean invertLeafSpiral) {
		//Base of dangly bit
		checkAndSetBlockState(world, rand, pos, replacer, config);

		//Get the direction of the twist from the direction of the branch then place a block there
		Direction spiral = spiral(direction, invertLeafSpiral);
		checkAndSetBlockState(world, rand, pos.move(spiral), replacer, config);

		//Continue the branch all the way down
		for (int i = 0; i < 2; i++) {
			checkAndSetBlockState(world, rand, pos.move(Direction.DOWN), replacer, config);
		}
	}

	private void checkAndSetBlockState(TestableWorld world, Random random, BlockPos.Mutable currentPosition, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			replacer.accept(currentPosition.toImmutable(), config.foliageProvider.getBlockState(random, currentPosition));
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
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return baseHeight == dz && dy == dz;
	}
}
