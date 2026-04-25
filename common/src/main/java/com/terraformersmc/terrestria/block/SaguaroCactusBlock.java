package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;

public class SaguaroCactusBlock extends BareSmallLogBlock {
	public SaguaroCactusBlock(Properties settings) {
		super(settings);
	}

	public static BlockBehaviour.Properties createSettings(MapColor color) {
		return BlockBehaviour.Properties.of()
				.mapColor(color)
				.strength(0.4F)
				.sound(SoundType.WOOL)
				.pushReaction(PushReaction.DESTROY);
	}

	public static BlockBehaviour.Properties createSettings(MapColor flesh, MapColor skin) {
		return BlockBehaviour.Properties.of()
				.mapColor((state) -> state.getValue(UP) ? flesh : skin)
				.strength(0.4F)
				.sound(SoundType.WOOL)
				.pushReaction(PushReaction.DESTROY);
	}

	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean initial) {
		if (world instanceof ServerLevel serverWorld) {
			entity.hurtServer(serverWorld, world.damageSources().cactus(), 1.0f);
		}
	}

	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}

	@Override
	public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		if (!isSupported(state, world, pos)) {
			world.destroyBlock(pos, true);
		}
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (world instanceof ServerLevel serverWorld && !isSupported(state, world, pos)) {
			serverWorld.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	private boolean isSupportedBlock(BlockState state) {
		return state.is(TerrestriaBlocks.SAGUARO_CACTUS) || state.is(BlockTags.SAND);
	}

	private boolean isSupported(BlockState state, LevelReader world, BlockPos pos) {
		BlockState blockState;

		if (isSupportedBlock(world.getBlockState(pos.below()))) {
			return true;
		}

		if (state.getValue(BareSmallLogBlock.DOWN)) {
			blockState = world.getBlockState(pos.below());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.UP));
		}
		if (state.getValue(BareSmallLogBlock.SOUTH)) {
			blockState = world.getBlockState(pos.south());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.NORTH));
		}
		if (state.getValue(BareSmallLogBlock.NORTH)) {
			blockState = world.getBlockState(pos.north());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.SOUTH));
		}
		if (state.getValue(BareSmallLogBlock.WEST)) {
			blockState = world.getBlockState(pos.west());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.EAST));
		}
		if (state.getValue(BareSmallLogBlock.EAST)) {
			blockState = world.getBlockState(pos.east());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.WEST));
		}

		return false;
	}

	private boolean canBeSupported(LevelReader world, BlockPos pos) {
		return world.getBlockState(pos.north()).is(TerrestriaBlocks.SAGUARO_CACTUS) ||
				world.getBlockState(pos.south()).is(TerrestriaBlocks.SAGUARO_CACTUS) ||
				world.getBlockState(pos.east()).is(TerrestriaBlocks.SAGUARO_CACTUS) ||
				world.getBlockState(pos.west()).is(TerrestriaBlocks.SAGUARO_CACTUS);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return isSupportedBlock(world.getBlockState(pos.below())) || canBeSupported(world, pos);
	}
}
