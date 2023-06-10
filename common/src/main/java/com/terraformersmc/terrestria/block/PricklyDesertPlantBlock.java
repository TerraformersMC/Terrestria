package com.terraformersmc.terrestria.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import org.joml.Vector3d;

public class PricklyDesertPlantBlock extends TerraformDesertPlantBlock implements Fertilizable {

	public PricklyDesertPlantBlock(Settings settings) {
		super(settings, false);
	}

	public PricklyDesertPlantBlock(Settings settings, boolean onlySand) {
		super(settings, onlySand);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		for (Direction direction : DIRECTIONS) {
			if (world.getBlockState(pos.offset(direction)).isOf(this)) {
				return false;
			}
		}

		return world.getBlockState(pos).isAir() && super.canPlaceAt(state, world, pos);
	}

	@Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
		return world.getBlockState(pos.down()).isIn(BlockTags.SAND);
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		for (int tries = 30; tries > 0; --tries) {
			double rotation = random.nextDouble() * 2d * Math.PI;
			double distance = random.nextDouble() * 8 + 0.5d;

			Vector3d newCoord = new Vector3d(distance, 0d, 0d).rotateY(rotation).add(pos.getX(), pos.getY(), pos.getZ());
			BlockPos testPos = BlockPos.ofFloored(newCoord.x, newCoord.y, newCoord.z);

			// Lucky guess
			if (this.canPlaceAt(this.getDefaultState(), world, testPos)) {
				world.setBlockState(testPos, this.getDefaultState());

				continue;
			}

			// Try a ways up and a ways down
			for (int yDelta = 0; yDelta < 5; ++yDelta) {
				if (this.canPlaceAt(this.getDefaultState(), world, testPos.up(yDelta))) {
					world.setBlockState(testPos.up(yDelta), this.getDefaultState());

					break;
				}

				if (this.canPlaceAt(this.getDefaultState(), world, testPos.down(yDelta))) {
					world.setBlockState(testPos.down(yDelta), this.getDefaultState());

					break;
				}
			}
		}
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(world.getDamageSources().cactus(), 1.0f);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}
}
