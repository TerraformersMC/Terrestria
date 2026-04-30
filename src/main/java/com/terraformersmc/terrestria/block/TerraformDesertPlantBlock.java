package com.terraformersmc.terrestria.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
	public static final MapCodec<TerraformDesertPlantBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codec.BOOL.fieldOf("onlySand").forGetter(args -> args.onlySand), TerraformDesertPlantBlock.propertiesCodec()).apply(instance, TerraformDesertPlantBlock::new));
	protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
	private final boolean onlySand;

	public TerraformDesertPlantBlock(Properties settings) {
		this(false, settings);
	}

	public TerraformDesertPlantBlock(boolean onlySand, Properties settings) {
		super(settings.offsetType(BlockBehaviour.OffsetType.XYZ));
		this.onlySand = onlySand;
	}

	@Override
	protected MapCodec<? extends VegetationBlock> codec() {
		return CODEC;
	}

	@Override
	public boolean mayPlaceOn(BlockState blockState, BlockGetter blockView, BlockPos pos) {
		if (onlySand) {
			return blockState.is(BlockTags.SAND);
		} else {
			return blockState.is(BlockTags.SAND) || super.mayPlaceOn(blockState, blockView, pos);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 vec3d = state.getOffset(pos);
		return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
	}
}
