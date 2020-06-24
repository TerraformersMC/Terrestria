package com.terraformersmc.terrestria.item;

import com.terraformersmc.terraform.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class LogTurnerItem extends Item {
	public LogTurnerItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		BlockPos pos = context.getBlockPos();
		World world = context.getWorld();

		BlockState state = world.getBlockState(pos);

		if (!(state.getBlock() instanceof PillarBlock)) {
			return ActionResult.PASS;
		}

		Direction.Axis currentAxis = state.get(PillarBlock.AXIS);

		if (context.getPlayer() != null && context.getPlayer().isSneaking()) {
			if (state.getBlock() instanceof QuarterLogBlock) {
				state = state.cycle(QuarterLogBlock.BARK_SIDE);

				// First cycle the bark side. If we return to the start, then cycle the axis too.
				if (state.get(QuarterLogBlock.BARK_SIDE) == QuarterLogBlock.BarkSide.SOUTHWEST) {
					state = state.cycle(PillarBlock.AXIS);
				}

				world.setBlockState(pos, state);

				return ActionResult.SUCCESS;
			} else {
				world.setBlockState(pos, state.cycle(PillarBlock.AXIS));

				return ActionResult.SUCCESS;
			}
		} else {
			Direction.Axis newAxis = context.getSide().getAxis();

			if (currentAxis != newAxis) {
				world.setBlockState(pos, state.with(PillarBlock.AXIS, newAxis));

				return ActionResult.SUCCESS;
			}
		}

		if (state.getBlock() instanceof QuarterLogBlock) {
			world.setBlockState(pos, state.cycle(QuarterLogBlock.BARK_SIDE));

			return ActionResult.SUCCESS;
		} else {
			return ActionResult.PASS;
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);

		String translation = Language.getInstance().get("item." + Terrestria.MOD_ID + ".log_turner.tooltip");

		for(String line: translation.split("\n")) {
			tooltip.add(new LiteralText(line.trim()).formatted(Formatting.GRAY));
		}
	}
}
