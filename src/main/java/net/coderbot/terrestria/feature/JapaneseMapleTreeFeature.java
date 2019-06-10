package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class JapaneseMapleTreeFeature extends JapaneseTreeFeature {
	public JapaneseMapleTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify, tree);
	}

	@Override
	protected void placeGroundCover(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, MutableIntBoundingBox boundingBox) {
		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
	}

	@Override
	protected void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < length - 1; i++) {
			pos.setOffset(direction);
			setBlockState(blocks, world, pos, tree.wood.with(LogBlock.AXIS, direction.getAxis()), boundingBox);
		}

		pos.setOffset(direction);
		tryPlaceLeaves(blocks, world, pos, boundingBox);
	}

	@Override
	protected void correctLogStates(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox) {}
}
