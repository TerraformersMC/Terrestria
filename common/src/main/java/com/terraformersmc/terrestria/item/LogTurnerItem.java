package com.terraformersmc.terrestria.item;

import com.terraformersmc.terraform.wood.api.block.QuarterLogBlock;
import com.terraformersmc.terrestria.Terrestria;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class LogTurnerItem extends Item {
	public LogTurnerItem(Properties settings) {
		super(settings);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos pos = context.getClickedPos();
		Level world = context.getLevel();

		BlockState state = world.getBlockState(pos);

		if (!(state.getBlock() instanceof RotatedPillarBlock)) {
			return InteractionResult.PASS;
		}

		Direction.Axis currentAxis = state.getValue(RotatedPillarBlock.AXIS);

		if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
			if (state.getBlock() instanceof QuarterLogBlock) {
				state = state.cycle(QuarterLogBlock.BARK_SIDE);

				// First cycle the bark side. If we return to the start, then cycle the axis too.
				if (state.getValue(QuarterLogBlock.BARK_SIDE) == QuarterLogBlock.BarkSide.SOUTHWEST) {
					state = state.cycle(RotatedPillarBlock.AXIS);
				}

				world.setBlockAndUpdate(pos, state);
			} else {
				world.setBlockAndUpdate(pos, state.cycle(RotatedPillarBlock.AXIS));
			}

			return InteractionResult.SUCCESS;
		} else {
			Direction.Axis newAxis = context.getClickedFace().getAxis();

			if (currentAxis != newAxis) {
				world.setBlockAndUpdate(pos, state.setValue(RotatedPillarBlock.AXIS, newAxis));

				return InteractionResult.SUCCESS;
			}
		}

		if (state.getBlock() instanceof QuarterLogBlock) {
			world.setBlockAndUpdate(pos, state.cycle(QuarterLogBlock.BARK_SIDE));

			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.PASS;
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
		super.appendHoverText(stack, context, displayComponent, textConsumer, type);

		String translation = Language.getInstance().getOrDefault("item." + Terrestria.MOD_ID + ".log_turner.tooltip");

		for (String line: translation.split("\n")) {
			textConsumer.accept(Component.literal(line.trim()).withStyle(ChatFormatting.GRAY));
		}
	}
}
