package com.terraformersmc.terrestria.block;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * A custom tall seagrass block where the lower half is underwater, and the upper half is above water.
 */
public class TallCattailBlock extends TallSeagrassBlock {
	private Supplier<Item> pickItem;

	public TallCattailBlock(Supplier<Item> pickItem, Settings settings) {
		super(settings);

		this.pickItem = pickItem;

	}

	@Override
	public ItemStack getPickStack(BlockView view, BlockPos pos, BlockState state) {
		return new ItemStack(pickItem.get());
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockPos pos = context.getBlockPos();

		return pos.getY() < 255 && context.getWorld().getBlockState(pos.up()).canReplace(context) ? this.getDefaultState() : null;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(HALF) == DoubleBlockHalf.UPPER ? Fluids.EMPTY.getDefaultState() : super.getFluidState(state);
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient && !player.isCreative()) {
			DoubleBlockHalf half = state.get(HALF);
			BlockPos other = half == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
			BlockState otherState = world.getBlockState(other);

			if (otherState.isOf(this)) {
				if ((half == DoubleBlockHalf.UPPER && otherState.get(HALF) == DoubleBlockHalf.LOWER)
						|| (half == DoubleBlockHalf.LOWER && otherState.get(HALF) == DoubleBlockHalf.UPPER)) {
					dropStacks(otherState, world, other, null, player, player.getMainHandStack());
				}
			}
		}

		super.onBreak(world, pos, state, player);
	}
}