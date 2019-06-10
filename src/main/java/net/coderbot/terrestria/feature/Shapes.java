package net.coderbot.terrestria.feature;

import net.minecraft.util.math.BlockPos;

import java.util.function.Consumer;

public class Shapes {
	/**
	 * Iterates over the positions contained with in a circle defined by origin and radius. The circle is two dimensional,
	 * perpendicular to the Y axis.
	 * @param origin The center sapling of the circle
	 * @param radius The radius of the circle
	 * @param consumer The target of the positions; it passes the same BlockPos.Mutable object each time
	 */
	public static void circle(BlockPos.Mutable origin, double radius, Consumer<BlockPos.Mutable> consumer) {
		int x = origin.getX();
		int z = origin.getZ();

		double radiusSq = radius * radius;
		int radiusCeil = (int)Math.ceil(radius);

		for(int dz = -radiusCeil; dz <= radiusCeil; dz++) {
			int dzSq = dz*dz;

			for(int dx = -radiusCeil; dx <= radiusCeil; dx++) {
				int dxSq = dx * dx;

				if(dzSq + dxSq <= radiusSq) {
					origin.set(x + dx, origin.getY(), z + dz);
					consumer.accept(origin);
				}
			}
		}
	}

	/**
	 * Iterates over the positions contained with in a circle defined by origin and radius, but not within the circle with
	 * a radius of two blocks less - effectively, this creates an open canopy shape when stacked. The circle is two
	 * dimensional, perpendicular to the Y axis.
	 * @param origin The center sapling of the circle
	 * @param radius The radius of the circle
	 * @param consumer The target of the positions; it passes the same BlockPos.Mutable object each time
	 */
	public static void canopyCircle(BlockPos.Mutable origin, double radius, double innerRadius, Consumer<BlockPos.Mutable> consumer) {
		int x = origin.getX();
		int z = origin.getZ();

		int radiusCeil = (int)Math.ceil(radius);
		double maxDistSq = radius * radius;
		double minDistSq = innerRadius * innerRadius;

		for(int dz = -radiusCeil; dz <= radiusCeil; dz++) {
			int dzSq = dz*dz;

			for(int dx = -radiusCeil; dx <= radiusCeil; dx++) {
				int dxSq = dx * dx;
				int distSq = dzSq + dxSq;

				if(distSq <= maxDistSq && distSq >= minDistSq) {
					origin.set(x + dx, origin.getY(), z + dz);
					consumer.accept(origin);
				}
			}
		}
	}
}
