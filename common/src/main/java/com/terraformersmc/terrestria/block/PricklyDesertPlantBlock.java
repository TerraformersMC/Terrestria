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
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		for (Direction direction : UPDATE_SHAPE_ORDER) {
			if (world.getBlockState(pos.relative(direction)).is(this)) {
				return false;
			}
		}

		return world.getBlockState(pos).isAir() && super.canSurvive(state, world, pos);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
		return world.getBlockState(pos.below()).is(BlockTags.SAND);
	}

	@Override
	public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
		for (int tries = 30; tries > 0; --tries) {
			double rotation = random.nextDouble() * 2d * Math.PI;
			double distance = random.nextDouble() * 8 + 0.5d;

			Vector3d newCoord = new Vector3d(distance, 0d, 0d).rotateY(rotation).add(pos.getX(), pos.getY(), pos.getZ());
			BlockPos testPos = BlockPos.containing(newCoord.x, newCoord.y, newCoord.z);

			// Lucky guess
			if (this.canSurvive(this.defaultBlockState(), world, testPos)) {
				world.setBlockAndUpdate(testPos, this.defaultBlockState());

				continue;
			}

			// Try a ways up and a ways down
			for (int yDelta = 0; yDelta < 5; ++yDelta) {
				if (this.canSurvive(this.defaultBlockState(), world, testPos.above(yDelta))) {
					world.setBlockAndUpdate(testPos.above(yDelta), this.defaultBlockState());

					break;
				}

				if (this.canSurvive(this.defaultBlockState(), world, testPos.below(yDelta))) {
					world.setBlockAndUpdate(testPos.below(yDelta), this.defaultBlockState());

					break;
				}
			}
		}
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
}
