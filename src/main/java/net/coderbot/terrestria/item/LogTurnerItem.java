package net.coderbot.terrestria.item;

import io.github.terraformersmc.terraform.block.QuarterLogBlock;
import net.coderbot.terrestria.Terrestria;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class LogTurnerItem extends Item {
	public LogTurnerItem(Settings settings) {
		super(settings);
	}

	public ActionResult useOnBlock(ItemUsageContext context) {
		BlockPos pos = context.getBlockPos();
		World world = context.getWorld();

		BlockState state = world.getBlockState(pos);

		if(!(state.getBlock() instanceof PillarBlock)) {
			return ActionResult.PASS;
		}

		Direction.Axis currentAxis = state.get(PillarBlock.AXIS);

		if(context.getPlayer() != null && context.getPlayer().isSneaking()) {
			if(state.getBlock() instanceof QuarterLogBlock) {
				QuarterLogBlock.BarkSide cycled = cycleBarkSide(state.get(QuarterLogBlock.BARK_SIDE));
				Direction.Axis newAxis = currentAxis;

				// First cycle the bark side. If we return to the start, then cycle the axis too.
				if(cycled == QuarterLogBlock.BarkSide.SOUTHWEST) {
					newAxis = cycleAxis(currentAxis);
				}

				world.setBlockState(pos, state
						.with(PillarBlock.AXIS, newAxis)
						.with(QuarterLogBlock.BARK_SIDE, cycled)
				);

				return ActionResult.SUCCESS;
			} else {
				world.setBlockState(pos, state.with(PillarBlock.AXIS, cycleAxis(currentAxis)));

				return ActionResult.SUCCESS;
			}
		} else {
			Direction.Axis newAxis = context.getPlayerFacing().getAxis();

			if(currentAxis != newAxis) {
				world.setBlockState(pos, state.with(PillarBlock.AXIS, newAxis));

				return ActionResult.SUCCESS;
			}
		}

		if(state.getBlock() instanceof QuarterLogBlock) {
			world.setBlockState(pos, state.cycle(QuarterLogBlock.BARK_SIDE));

			return ActionResult.SUCCESS;
		} else {
			return ActionResult.PASS;
		}
	}

	private static Direction.Axis cycleAxis(Direction.Axis axis) {
		switch(axis) {
			case field_11048: return Direction.Axis.field_11052;
			case field_11052: return Direction.Axis.field_11051;
			default: return Direction.Axis.field_11048;
		}
	}

	private static QuarterLogBlock.BarkSide cycleBarkSide(QuarterLogBlock.BarkSide side) {
		switch (side) {
			case SOUTHWEST: return QuarterLogBlock.BarkSide.NORTHWEST;
			case NORTHWEST: return QuarterLogBlock.BarkSide.NORTHEAST;
			case NORTHEAST: return QuarterLogBlock.BarkSide.SOUTHEAST;
			default: return QuarterLogBlock.BarkSide.SOUTHWEST;
		}
	}

	@Environment(EnvType.CLIENT)
	public void buildTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);

		for(int i = 0; i < 8; i++) {
			tooltip.add(new TranslatableText("item." + Terrestria.MOD_ID + ".log_turner.tooltip.line" + i).setStyle(new Style().setColor(Formatting.GRAY)));
		}
	}
}
