package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.leaves.api.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PredictiveSpruceFoliagePlacer extends SpruceFoliagePlacer {
	// Copied from SpruceFoliagePlacer. There doesn't appear to be a convenient way to turn
	// a SpruceFoliagePlacer into a PredictiveSpruceFoliagePlacer.
	public static final MapCodec<PredictiveSpruceFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
		foliagePlacerParts(instance).and(
				IntProviders.codec(0, 28)
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
	protected FoliagePlacerType<PredictiveSpruceFoliagePlacer> type() {
		return TerrestriaFoliagePlacerTypes.PREDICTIVE_SPRUCE;
	}

	@Override
	protected void placeLeavesRow(WorldGenLevel world, FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos blockPos, int radius, int offsetY, boolean giantTrunk) {
		int giantTrunkOffset = giantTrunk ? 1 : 0;
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
		int actualDistance;

		for (int offsetX = -radius; offsetX <= radius + giantTrunkOffset; ++offsetX) {
			for (int offsetZ = -radius; offsetZ <= radius + giantTrunkOffset; ++offsetZ) {
				if (!this.shouldSkipLocationSigned(random, offsetX, offsetY, offsetZ, radius, giantTrunk)) {
					mutable.setWithOffset(blockPos, offsetX, offsetY, offsetZ);
					if (TreeFeature.validTreePos(world, mutable)) {
						actualDistance = calculateActualDistance(offsetX, offsetY, offsetZ, giantTrunk);
						BlockState baseState = config.foliageProvider.getState(world, random, mutable);
						placer.set(mutable.immutable(), withDistance(baseState, actualDistance));
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
		if (!state.hasProperty(ExtendedLeavesBlock.EXTENDED_DISTANCE)) {
			distance = Math.min(distance, LeavesBlock.DECAY_DISTANCE);
		}

		return ExtendedLeavesBlock.setExtendedDistance(state, distance);
	}
}
