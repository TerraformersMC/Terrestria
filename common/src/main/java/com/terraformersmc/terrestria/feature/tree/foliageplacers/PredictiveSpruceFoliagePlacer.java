package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.leaves.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;

public class PredictiveSpruceFoliagePlacer extends SpruceFoliagePlacer {
	// Copied from SpruceFoliagePlacer. There doesn't appear to be a convenient way to turn a SpruceFoliagePlacer into a
	// PredictiveSpruceFoliagePlacer.
	public static final MapCodec<PredictiveSpruceFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
		fillFoliagePlacerFields(instance).and(
				IntProvider.createValidatingCodec(0, 28)
						.fieldOf("trunk_height")
						.forGetter(placer -> placer.trunkHeight)
		).apply(instance, PredictiveSpruceFoliagePlacer::new)
	);

	private final IntProvider trunkHeight;

	public PredictiveSpruceFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeight) {
		super(radius, offset, trunkHeight);

		this.trunkHeight = trunkHeight;
	}

	@Override
	protected FoliagePlacerType<PredictiveSpruceFoliagePlacer> getType() {
		return TerrestriaFoliagePlacerTypes.PREDICTIVE_SPRUCE;
	}

	@Override
	protected void generateSquare(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos blockPos, int radius, int offsetY, boolean giantTrunk) {
		int giantTrunkOffset = giantTrunk ? 1 : 0;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		int actualDistance;

		for (int offsetX = -radius; offsetX <= radius + giantTrunkOffset; ++offsetX) {
			for (int offsetZ = -radius; offsetZ <= radius + giantTrunkOffset; ++offsetZ) {
				if (!this.isPositionInvalid(random, offsetX, offsetY, offsetZ, radius, giantTrunk)) {
					mutable.set(blockPos, offsetX, offsetY, offsetZ);
					if (TreeFeature.canReplace(world, mutable)) {
						actualDistance = calculateActualDistance(offsetX, offsetY, offsetZ, giantTrunk);
						BlockState baseState = config.foliageProvider.get(random, mutable);
						placer.placeBlock(mutable.toImmutable(), withDistance(baseState, actualDistance));
					}
				}
			}
		}
	}

	private static int calculateActualDistance(int offsetX, int offsetY, int offsetZ, boolean isGiantTrunk) {
		int distance;

		if (isGiantTrunk) {
			distance = Math.min(Math.abs(offsetX), Math.abs(offsetX - 1)) + Math.min(Math.abs(offsetZ), Math.abs(offsetZ - 1));
		} else {
			distance = Math.abs(offsetX) + Math.abs(offsetZ);
		}

		if (offsetY >= 0) {
			distance += offsetY + 1;
		}

		// If the distance is 0, the leaves wouldn't be there to begin with?
		if (distance == 0) {
			return 1;
		}

		return distance;
	}

	private static BlockState withDistance(BlockState state, int distance) {
		if (state.getBlock() instanceof ExtendedLeavesBlock) {
			return state.with(LeavesBlock.DISTANCE, Math.min(distance, ExtendedLeavesBlock.MAX_DISTANCE));
		} else if (state.contains(LeavesBlock.DISTANCE)) {
			return state.with(LeavesBlock.DISTANCE, Math.min(distance, LeavesBlock.MAX_DISTANCE));
		}

		return state;
	}
}
