package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.trees.templates.JapaneseTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class JapaneseMapleTreeFeature extends JapaneseTreeFeature {
	public JapaneseMapleTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function) {
		super(function);
	}

	@Override
	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, int length) {
		for (int i = 0; i < length - 1; i++) {
			pos.setOffset(direction);
			BlockState state = config.trunkProvider.getBlockState(rand, pos).with(LogBlock.AXIS, direction.getAxis());
			PortUtil.setBlockState(logs, world, pos, state, box);
		}

		pos.setOffset(direction);
		tryPlaceLeaves(world, rand, pos, logs, leaves, box, config);
	}

	@Override
	public void correctLogStates(Set<BlockPos> logs, ModifiableTestableWorld world, BlockBox box) {
	}
}
