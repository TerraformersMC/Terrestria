package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.function.Consumer;

@NullMarked
public class CypressFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
			foliagePlacerParts(instance).apply(instance, CypressFoliagePlacer::new));

	public CypressFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.CYPRESS;
	}

	@Override
	protected void createFoliage(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
		double maxRadius = 1.5 + 1.5 * random.nextDouble();

		BlockPos.MutableBlockPos pos = treeNode.pos().mutable();

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

			circle(pos.mutable(), treeRadius, position -> {
				if (TreeFeature.isAirOrLeaves(level, position)) {
					setter.set(position.immutable(), config.foliageProvider.getState(level, random, position));
				}
			});
		}
	}

	@Override
	public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int baseHeight, int dx, int dy, int dz, boolean bl) {
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
	private static void circle(BlockPos.MutableBlockPos origin, double radius, Consumer<BlockPos.MutableBlockPos> consumer) {
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
