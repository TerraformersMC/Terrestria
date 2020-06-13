package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

public class SaguaroCactusBlock extends BareSmallLogBlock {

	public SaguaroCactusBlock(Supplier<Block> stripped, Settings settings) {
		super(stripped, settings);
	}

	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0F);
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			world.breakBlock(pos, true);
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (!state.canPlaceAt(world, pos)) {
			world.getBlockTickScheduler().schedule(pos, this, 1);
		}

		return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	public boolean isSupportedBlock(Block block) {
		return (block == TerrestriaBlocks.SAGUARO_CACTUS || block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Iterator horizontalBlocks = Direction.Type.HORIZONTAL.iterator();

		Direction direction;
		BlockState blockState;
		do {
			if (!horizontalBlocks.hasNext()) {
				Block block = world.getBlockState(pos.down()).getBlock();
				return isSupportedBlock(block) && !world.getBlockState(pos.up()).getMaterial().isLiquid();
			}

			direction = (Direction)horizontalBlocks.next();
			blockState = world.getBlockState(pos.offset(direction));
		} while ((blockState.getBlock() == TerrestriaBlocks.SAGUARO_CACTUS || !blockState.getMaterial().isSolid()) && !world.getFluidState(pos.offset(direction)).matches(FluidTags.LAVA));

		return false;
	}
}
