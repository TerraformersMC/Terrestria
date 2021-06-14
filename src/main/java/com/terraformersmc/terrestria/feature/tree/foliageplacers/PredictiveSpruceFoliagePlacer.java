package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import java.util.Random;
import java.util.function.BiConsumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.leaves.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;

public class PredictiveSpruceFoliagePlacer extends SpruceFoliagePlacer {
	// Copied from SpruceFoliagePlacer. There doesn't appear to be a convenient way to turn a SpruceFoliagePlacer into a
	// PredictiveSpruceFoliagePlacer.
	public static final Codec<PredictiveSpruceFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
		fillFoliagePlacerFields(instance).and(
				IntProvider.createValidatingCodec(0, 16)
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
	protected void generateSquare(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, BlockPos blockPos, int radius, int offsetY, boolean giantTrunk) {
		int giantTrunkOffset = giantTrunk ? 1 : 0;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		int actualDistance;

		for (int offsetX = -radius; offsetX <= radius + giantTrunkOffset; ++offsetX) {
			for (int offsetZ = -radius; offsetZ <= radius + giantTrunkOffset; ++offsetZ) {
				if (!this.isPositionInvalid(random, offsetX, offsetY, offsetZ, radius, giantTrunk)) {
					mutable.set(blockPos, offsetX, offsetY, offsetZ);
					if (TreeFeature.canReplace(world, mutable)) {
						actualDistance = calculateActualDistance(offsetX, offsetY, offsetZ, giantTrunk);
						BlockState baseState = config.foliageProvider.getBlockState(random, mutable);

						replacer.accept(mutable.toImmutable(), withDistance(baseState, actualDistance));
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
		if (state.contains(ExtendedLeavesBlock.DISTANCE)) {
			return state.with(ExtendedLeavesBlock.DISTANCE, Math.min(distance, ExtendedLeavesBlock.MAX_DISTANCE));
		}

		if (state.contains(Properties.DISTANCE_1_7)) {
			return state.with(Properties.DISTANCE_1_7, Math.min(distance, 7));
		}

		return state;
	}
}
