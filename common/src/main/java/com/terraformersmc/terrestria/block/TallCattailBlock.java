package com.terraformersmc.terrestria.block;

import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * A custom tall seagrass block where the lower half is underwater, and the upper half is above water.
 */
@NullMarked
public class TallCattailBlock extends TallSeagrassBlock {
	public TallCattailBlock(Properties settings) {
		super(settings);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
		return new ItemStack(TerrestriaBlocks.CATTAIL);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		Level world = context.getLevel();

		if (pos.getY() < world.getMaxY() && world.getBlockState(pos.above()).canBeReplaced(context)) {
			return this.defaultBlockState();
		}

		return null;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(HALF) == DoubleBlockHalf.UPPER ? Fluids.EMPTY.defaultFluidState() : super.getFluidState(state);
	}

	@Override
	public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
		if (!world.isClientSide()) {
			if (player.isCreative()) {
				DoublePlantBlock.preventDropFromBottomPart(world, pos, state, player);
			} else {
				DoublePlantBlock.dropResources(state, world, pos, null, player, player.getMainHandItem());
			}
		}
		return super.playerWillDestroy(world, pos, state, player);
	}
}
