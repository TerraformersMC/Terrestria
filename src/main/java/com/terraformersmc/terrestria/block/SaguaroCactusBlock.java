package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.block.BareSmallLogBlock;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.Terrestria;
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

	@Override
	@SuppressWarnings("deprecation")
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!isSupported(state, world, pos)) {
			world.breakBlock(pos, true);
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (!isSupported(state, world, pos)) {
			world.getBlockTickScheduler().schedule(pos, this, 1);
		}

		return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	public boolean isSupportedBlock(Block block) {
		return (block == TerrestriaBlocks.SAGUARO_CACTUS || block == Blocks.SAND || block == Blocks.RED_SAND || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT || block == Blocks.COARSE_DIRT);
	}

	private boolean isSupported(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockState;
		if (isSupportedBlock(world.getBlockState(pos.down()).getBlock())) {
			return true;
		}
		if (state.get(BareSmallLogBlock.DOWN)) {
			blockState = world.getBlockState(pos.down());
			return (blockState.getBlock() == TerrestriaBlocks.SAGUARO_CACTUS && blockState.get(BareSmallLogBlock.UP));
		}
		if (state.get(BareSmallLogBlock.SOUTH)) {
			blockState = world.getBlockState(pos.south());
			return (blockState.getBlock() == TerrestriaBlocks.SAGUARO_CACTUS && blockState.get(BareSmallLogBlock.NORTH));
		}
		if (state.get(BareSmallLogBlock.NORTH)) {
			blockState = world.getBlockState(pos.north());
			return (blockState.getBlock() == TerrestriaBlocks.SAGUARO_CACTUS && blockState.get(BareSmallLogBlock.SOUTH));
		}
		if (state.get(BareSmallLogBlock.WEST)) {
			blockState = world.getBlockState(pos.west());
			return (blockState.getBlock() == TerrestriaBlocks.SAGUARO_CACTUS && blockState.get(BareSmallLogBlock.EAST));
		}
		if (state.get(BareSmallLogBlock.EAST)) {
			blockState = world.getBlockState(pos.east());
			return (blockState.getBlock() == TerrestriaBlocks.SAGUARO_CACTUS && blockState.get(BareSmallLogBlock.WEST));
		}
		return false;
	}

	private boolean canBeSupported(WorldView world, BlockPos pos) {
		if (world.getBlockState(pos.north()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS ||
				world.getBlockState(pos.south()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS ||
				world.getBlockState(pos.east()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS ||
				world.getBlockState(pos.west()).getBlock() == TerrestriaBlocks.SAGUARO_CACTUS) {
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("deprecation")
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		if (isSupportedBlock(world.getBlockState(pos.down()).getBlock()) || canBeSupported(world, pos)) {
			return true;
		}
		return false;
	}
}
