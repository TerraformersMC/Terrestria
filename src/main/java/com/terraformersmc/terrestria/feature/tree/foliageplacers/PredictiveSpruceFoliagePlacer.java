package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.block.ExtendedLeavesBlock;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;

public class PredictiveSpruceFoliagePlacer extends SpruceFoliagePlacer {
	// Copied from SpruceFoliagePlacer. There doesn't appear to be a convenient way to turn a SpruceFoliagePlacer into a
	// PredictiveSpruceFoliagePlacer.
	public static final Codec<PredictiveSpruceFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
		fillFoliagePlacerFields(instance).and(
				UniformIntDistribution.createValidatedCodec(0, 16, 12)
						.fieldOf("trunk_height")
						.forGetter(placer -> placer.trunkHeight)
		).apply(instance, PredictiveSpruceFoliagePlacer::new)
	);

	private final UniformIntDistribution trunkHeight;

	public PredictiveSpruceFoliagePlacer(UniformIntDistribution radius, UniformIntDistribution offset, UniformIntDistribution trunkHeight) {
		super(radius, offset, trunkHeight);

		this.trunkHeight = trunkHeight;
	}

	@Override
	protected FoliagePlacerType<PredictiveSpruceFoliagePlacer> getType() {
		return TerrestriaFoliagePlacerTypes.PREDICTIVE_SPRUCE;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, BlockPos blockPos, int radius, Set<BlockPos> set, int offsetY, boolean giantTrunk, BlockBox blockBox) {
		int giantTrunkOffset = giantTrunk ? 1 : 0;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		int actualDistance;

		for (int offsetX = -radius; offsetX <= radius + giantTrunkOffset; ++offsetX) {
			for (int offsetZ = -radius; offsetZ <= radius + giantTrunkOffset; ++offsetZ) {
				if (!this.method_27387(random, offsetX, offsetY, offsetZ, radius, giantTrunk)) {
					mutable.set(blockPos, offsetX, offsetY, offsetZ);
					if (TreeFeature.canReplace(world, mutable)) {
						actualDistance = calculateActualDistance(offsetX, offsetY, offsetZ, giantTrunk);
						BlockState baseState = config.leavesProvider.getBlockState(random, mutable);

						world.setBlockState(mutable, withDistance(baseState, actualDistance), 19);

						blockBox.encompass(new BlockBox(mutable, mutable));
						set.add(mutable.toImmutable());
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
