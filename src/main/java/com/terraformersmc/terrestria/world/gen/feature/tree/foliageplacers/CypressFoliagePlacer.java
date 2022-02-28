package com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers;

import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CypressFoliagePlacer extends FoliagePlacer {
	public static final Codec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, CypressFoliagePlacer::new));

	public CypressFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.CYPRESS;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
		double maxRadius = 1.5 + 1.5 * random.nextDouble();

		BlockPos.Mutable pos = treeNode.getCenter().mutableCopy();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double treeRadius;

		int extra = (int) ((trunkHeight * 1.67777) - trunkHeight);

		for (int dy = -trunkHeight; dy < extra; dy++) {
			pos.set(x, y + dy, z);

			treeRadius = maxRadius * radiusFactor(trunkHeight + dy, (trunkHeight + extra));

			if (treeRadius < 0) {
				continue;
			}

			circle(pos.mutableCopy(), treeRadius, position -> {
				if (TreeFeature.isAirOrLeaves(world, position)) {
					replacer.accept(position.toImmutable(), config.foliageProvider.getBlockState(random, position));
				}
			});
		}
	}

	@Override
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return false;
	}

	// Provides the factor to the radius, where x is a double from 0.0 to 1.0 that represents the progress along the trunk.
	private double radiusFactor(double x, double height) {
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a cypress tree - increasing rapidly, and then tapering off.
		return 6.25 * (x * x * x) - 12.5 * (x * x) + 6.25 * x;
	}

	/**
	 * Iterates over the positions contained with in a circle defined by origin and radius. The circle is two dimensional,
	 * perpendicular to the Y axis.
	 *
	 * @param origin The center block of the circle; this function clobbers the variable, and it must be reset afterwards
	 * @param radius The radius of the circle
	 * @param consumer The target of the positions; it passes the same BlockPos.Mutable object each time
	 */
	private static void circle(BlockPos.Mutable origin, double radius, Consumer<BlockPos.Mutable> consumer) {
		int x = origin.getX();
		int z = origin.getZ();

		double radiusSq = radius * radius;
		int radiusCeil = (int) Math.ceil(radius);

		for (int dz = -radiusCeil; dz <= radiusCeil; dz++) {
			int dzSq = dz * dz;

			for (int dx = -radiusCeil; dx <= radiusCeil; dx++) {
				int dxSq = dx * dx;

				if (dzSq + dxSq <= radiusSq) {
					origin.set(x + dx, origin.getY(), z + dz);
					consumer.accept(origin);
				}
			}
		}
	}
}
