package com.terraformersmc.terrestria.block;

import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class TerraformDesertPlantBlock extends PlantBlock {
	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private final boolean onlySand;

	public TerraformDesertPlantBlock(Settings settings) {
		this(settings, false);
	}

	public TerraformDesertPlantBlock(Settings settings, boolean onlySand) {
		super(settings.offsetType(AbstractBlock.OffsetType.XYZ));
		this.onlySand = onlySand;
	}

	@Override
	public boolean canPlantOnTop(BlockState blockState, BlockView blockView, BlockPos pos) {
		if (onlySand) {
			return blockState.isIn(BlockTags.SAND);
		} else {
			return blockState.isIn(BlockTags.SAND) || super.canPlantOnTop(blockState, blockView, pos);
		}
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(world, pos);
		return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
	}
}
