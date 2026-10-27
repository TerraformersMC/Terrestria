package com.terraformersmc.terrestria.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerraformDesertPlantBlock extends VegetationBlock {
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private final boolean onlyDry;

	public TerraformDesertPlantBlock(Properties settings) {
		this(false, settings);
	}

	public TerraformDesertPlantBlock(boolean onlyDry, Properties settings) {
		super(settings.offsetType(BlockBehaviour.OffsetType.XYZ));
		this.onlyDry = onlyDry;
	}

	@Override
	public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos pos) {
		if (onlyDry) {
			return blockState.is(BlockTags.SUPPORTS_DRY_VEGETATION);
		} else {
			return blockState.is(BlockTags.SAND) || super.mayPlaceOn(blockState, blockGetter, pos);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
	}
}
