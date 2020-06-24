package com.terraformersmc.terrestria.feature.trees;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.trees.templates.JapaneseTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;

public class JapaneseMapleTreeFeature extends JapaneseTreeFeature {

	public JapaneseMapleTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, int length) {
		for (int i = 0; i < length - 1; i++) {
			pos.move(direction);
			BlockState state = config.trunkProvider.getBlockState(rand, pos).with(PillarBlock.AXIS, direction.getAxis());
			PortUtil.setBlockState(logs, world, pos, state, box);
		}

		pos.move(direction);
		tryPlaceLeaves(world, rand, pos, logs, leaves, box, config);
	}
}
