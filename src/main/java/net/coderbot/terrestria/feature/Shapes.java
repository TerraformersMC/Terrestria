package net.coderbot.terrestria.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.function.Consumer;

public class Shapes {
	/**
	 * Iterates over the positions contained with in a circle defined by origin and radius. The circle is two dimensional,
	 * perpendicular to the Y axis.
	 * @param origin The center block of the circle; this function clobbers the variable, and it must be reset afterwards
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
	 * @param origin The center block of the circle; this function clobbers the variable, and it must be reset afterwards
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

	public static final int X = 0;
	public static final int Y = 1;
	public static final int Z = 2;

	public static void line(BlockPos.Mutable origin, int[] offset, Consumer<BlockPos.Mutable> consumer) {
		if(offset.length != 3) {
			throw new IllegalArgumentException("Shape::line: offset must be an array of 3 integers corresponding to the X, Y, and Z offsets");
		}

		int originX = origin.getX();
		int originY = origin.getY();
		int originZ = origin.getZ();

		int maxLength = 0;

		for(int i = 0; i < 3; i++) {
			maxLength = Math.max(maxLength, Math.abs(offset[i]));
		}

		if(maxLength == 0) {
			// Avoid dividing by zero
			return;
		}

		double[] position = new double[3];
		double[] velocity = new double[] {
				offset[X] / ((double)maxLength),
				offset[Y] / ((double)maxLength),
				offset[Z] / ((double)maxLength)
		};

		consumer.accept(origin);

		for(int i = 0; i < maxLength - 1; i++) {
			position[X] += velocity[X];
			position[Y] += velocity[Y];
			position[Z] += velocity[Z];

			origin.set (
					originX + MathHelper.fastFloor(position[X]),
					originY + MathHelper.fastFloor(position[Y]),
					originZ + MathHelper.fastFloor(position[Z])
			);

			consumer.accept(origin);
		}
	}
}
