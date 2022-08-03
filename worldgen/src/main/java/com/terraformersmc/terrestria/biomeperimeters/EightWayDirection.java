package com.terraformersmc.terrestria.biomeperimeters;

import com.google.common.collect.Sets;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;

import java.util.Arrays;
import java.util.Set;

// TODO: This is EightWayDirection from Minecraft 1.19; when we're on 1.19 we can just use the official one.
public enum EightWayDirection {
	NORTH(Direction.NORTH),
	NORTH_EAST(Direction.NORTH, Direction.EAST),
	EAST(Direction.EAST),
	SOUTH_EAST(Direction.SOUTH, Direction.EAST),
	SOUTH(Direction.SOUTH),
	SOUTH_WEST(Direction.SOUTH, Direction.WEST),
	WEST(Direction.WEST),
	NORTH_WEST(Direction.NORTH, Direction.WEST);

	private final Set<Direction> directions;
	private final Vec3i field_37995;

	private EightWayDirection(Direction ... directions) {
		this.directions = Sets.immutableEnumSet(Arrays.asList(directions));
		this.field_37995 = new Vec3i(0, 0, 0);
		for (Direction lv : directions) {
			this.field_37995.setX(this.field_37995.getX() + lv.getOffsetX()).setY(this.field_37995.getY() + lv.getOffsetY()).setZ(this.field_37995.getZ() + lv.getOffsetZ());
		}
	}

	public Set<Direction> getDirections() {
		return this.directions;
	}

	public int method_42015() {
		return this.field_37995.getX();
	}

	public int method_42016() {
		return this.field_37995.getZ();
	}
}
