package com.terraformersmc.terrestria.block;

import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jspecify.annotations.NullMarked;

@NullMarked
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
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean initial) {
		if (level instanceof ServerLevel serverLevel) {
			entity.hurtServer(serverLevel, level.damageSources().cactus(), 1.0f);
		}
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!isSupported(state, level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

	@Override
	public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (level instanceof ServerLevel serverLevel && !isSupported(state, level, pos)) {
			serverLevel.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, level, tickAccess, pos, direction, neighborPos, neighborState, random);
	}

	private boolean isSupportedBlock(BlockState state) {
		return state.is(TerrestriaBlocks.SAGUARO_CACTUS) || state.is(BlockTags.SAND);
	}

	private boolean isSupported(BlockState state, LevelReader level, BlockPos pos) {
		BlockState blockState;

		if (isSupportedBlock(level.getBlockState(pos.below()))) {
			return true;
		}

		if (state.getValue(BareSmallLogBlock.DOWN)) {
			blockState = level.getBlockState(pos.below());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.UP));
		}
		if (state.getValue(BareSmallLogBlock.SOUTH)) {
			blockState = level.getBlockState(pos.south());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.NORTH));
		}
		if (state.getValue(BareSmallLogBlock.NORTH)) {
			blockState = level.getBlockState(pos.north());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.SOUTH));
		}
		if (state.getValue(BareSmallLogBlock.WEST)) {
			blockState = level.getBlockState(pos.west());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.EAST));
		}
		if (state.getValue(BareSmallLogBlock.EAST)) {
			blockState = level.getBlockState(pos.east());
			return (blockState.is(TerrestriaBlocks.SAGUARO_CACTUS) && blockState.getValue(BareSmallLogBlock.WEST));
		}

		return false;
	}

	private boolean canBeSupported(LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.north()).is(TerrestriaBlocks.SAGUARO_CACTUS) ||
				level.getBlockState(pos.south()).is(TerrestriaBlocks.SAGUARO_CACTUS) ||
				level.getBlockState(pos.east()).is(TerrestriaBlocks.SAGUARO_CACTUS) ||
				level.getBlockState(pos.west()).is(TerrestriaBlocks.SAGUARO_CACTUS);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return isSupportedBlock(level.getBlockState(pos.below())) || canBeSupported(level, pos);
	}
}
