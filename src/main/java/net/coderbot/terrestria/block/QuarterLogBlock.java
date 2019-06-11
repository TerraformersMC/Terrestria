package net.coderbot.terrestria.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class QuarterLogBlock extends LogBlock {
	public static final EnumProperty<BarkSide> BARK_SIDE = EnumProperty.create("bark_side", BarkSide.class);

	public QuarterLogBlock(Settings settings) {
		super(MaterialColor.BROWN, settings);
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);

		builder.add(BARK_SIDE);
	}

	public BlockState getPlacementState(ItemPlacementContext context) {
		Vec3d pos = context.getPos();
		BlockPos blockPos = context.getBlockPos();

		float hitX = (float)(pos.getX() - blockPos.getX());
		float hitY = (float)(pos.getY() - blockPos.getY());
		float hitZ = (float)(pos.getZ() - blockPos.getZ());

		BarkSide side = BarkSide.fromHit(context.getFacing().getAxis(), hitX, hitY, hitZ);

		return super.getPlacementState(context).with(BARK_SIDE, side);
	}

	public enum BarkSide implements StringIdentifiable {
		SOUTHWEST("southwest"),
		NORTHWEST("northwest"),
		NORTHEAST("northeast"),
		SOUTHEAST("southeast");

		final String name;

		BarkSide(String name) {
			this.name = name;
		}

		public static BarkSide fromHit(Direction.Axis axis, float hitX, float hitY, float hitZ) {
			boolean hitEast;
			boolean hitSouth;

			switch(axis) {
				case Y:
					hitEast = hitX >= 0.5;
					hitSouth = hitZ >= 0.5;
					break;
				case X:
					hitEast = hitY <= 0.5;
					hitSouth = hitZ >= 0.5;
					break;
				default:
					hitEast = hitX >= 0.5;
					hitSouth = hitY >= 0.5;
					break;
			}

			// Logic of placement: The quadrant the player clicks on should be the one farthest from the bark sides.
			return BarkSide.fromHalves(!hitEast, !hitSouth);
		}

		public static BarkSide fromHalves(boolean east, boolean south) {
			if (east) {
				if (south) {
					return BarkSide.SOUTHEAST;
				} else {
					return BarkSide.NORTHEAST;
				}
			} else {
				if (south) {
					return BarkSide.SOUTHWEST;
				} else {
					return BarkSide.NORTHWEST;
				}
			}
		}

		public String toString() {
			return this.name;
		}

		public String asString() {
			return this.name;
		}
	}
}
