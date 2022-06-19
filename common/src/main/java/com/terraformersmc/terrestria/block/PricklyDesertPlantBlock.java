package com.terraformersmc.terrestria.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PricklyDesertPlantBlock extends TerraformDesertPlantBlock {

	public PricklyDesertPlantBlock(Settings settings) {
		super(settings, false);
	}

	public PricklyDesertPlantBlock(Settings settings, boolean onlySand) {
		super(settings, onlySand);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		entity.damage(DamageSource.CACTUS, 1.0F);
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}

	// For Lithium - https://github.com/jellysquid3/lithium-fabric/blob/1.16.x/dev/src/main/java/me/jellysquid/mods/lithium/api/pathing/BlockPathingBehavior.java
	public PathNodeType getPathNodeType(BlockState state) {
		return PathNodeType.DAMAGE_CACTUS;
	}

	// For Lithium - https://github.com/jellysquid3/lithium-fabric/blob/1.16.x/dev/src/main/java/me/jellysquid/mods/lithium/api/pathing/BlockPathingBehavior.java
	public PathNodeType getNeighborPathNodeType(BlockState state) {
		return PathNodeType.DAMAGE_CACTUS;
	}
}
