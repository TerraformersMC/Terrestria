package net.coderbot.terrestria.block;

import net.coderbot.terrestria.init.TerrestriaItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CustomTallSeagrassBlock extends TallSeagrassBlock {
	public CustomTallSeagrassBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getPickStack(BlockView view, BlockPos pos, BlockState state) {
		return new ItemStack(TerrestriaItems.CATTAIL);
	}

	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockPos pos = context.getBlockPos();

		return pos.getY() < 255 && context.getWorld().getBlockState(pos.up()).canReplace(context) ? this.getDefaultState() : null;
	}

	public FluidState getFluidState(BlockState state) {
		return state.get(HALF) == DoubleBlockHalf.UPPER ? Fluids.EMPTY.getDefaultState() : super.getFluidState(state);
	}
}
