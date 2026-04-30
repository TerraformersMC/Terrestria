package com.terraformersmc.terrestria.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.joml.Vector3d;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PricklyDesertPlantBlock extends TerraformDesertPlantBlock implements BonemealableBlock {
	public PricklyDesertPlantBlock(Properties settings) {
		super(false, settings);
	}

	public PricklyDesertPlantBlock(boolean onlySand, Properties settings) {
		super(onlySand, settings);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		for (Direction direction : UPDATE_SHAPE_ORDER) {
			if (level.getBlockState(pos.relative(direction)).is(this)) {
				return false;
			}
		}

		return level.getBlockState(pos).isAir() && super.canSurvive(state, level, pos);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
		return level.getBlockState(pos.below()).is(BlockTags.SAND);
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		for (int tries = 30; tries > 0; --tries) {
			double rotation = random.nextDouble() * 2d * Math.PI;
			double distance = random.nextDouble() * 8 + 0.5d;

			Vector3d newCoord = new Vector3d(distance, 0d, 0d).rotateY(rotation).add(pos.getX(), pos.getY(), pos.getZ());
			BlockPos testPos = BlockPos.containing(newCoord.x, newCoord.y, newCoord.z);

			// Lucky guess
			if (this.canSurvive(this.defaultBlockState(), level, testPos)) {
				level.setBlockAndUpdate(testPos, this.defaultBlockState());

				continue;
			}

			// Try a ways up and a ways down
			for (int yDelta = 0; yDelta < 5; ++yDelta) {
				if (this.canSurvive(this.defaultBlockState(), level, testPos.above(yDelta))) {
					level.setBlockAndUpdate(testPos.above(yDelta), this.defaultBlockState());

					break;
				}

				if (this.canSurvive(this.defaultBlockState(), level, testPos.below(yDelta))) {
					level.setBlockAndUpdate(testPos.below(yDelta), this.defaultBlockState());

					break;
				}
			}
		}
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean initial) {
		if (level instanceof ServerLevel serverLevel) {
			entity.hurtServer(serverLevel, level.damageSources().cactus(), 1.0f);
		}
	}

	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}
}
