package com.terraformersmc.terrestria.block;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

/**
 * A custom tall seagrass block where the lower half is underwater, and the upper half is above water.
 */
public class TallCattailBlock extends TallSeagrassBlock {
	public TallCattailBlock(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
		return new ItemStack(TerrestriaBlocks.CATTAIL);
	}

	@Override
	public @Nullable BlockState getPlacementState(ItemPlacementContext context) {
		BlockPos pos = context.getBlockPos();
		World world = context.getWorld();

		if (pos.getY() < world.getTopY() && world.getBlockState(pos.up()).canReplace(context)) {
			return this.getDefaultState();
		}

		return null;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(HALF) == DoubleBlockHalf.UPPER ? Fluids.EMPTY.getDefaultState() : super.getFluidState(state);
	}

	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient) {
			if (player.isCreative()) {
				TallPlantBlock.onBreakInCreative(world, pos, state, player);
			} else {
				TallPlantBlock.dropStacks(state, world, pos, null, player, player.getMainHandStack());
			}
		}
		return super.onBreak(world, pos, state, player);
	}
}
