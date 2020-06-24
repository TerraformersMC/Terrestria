package com.terraformersmc.terrestria.feature.trees;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.trees.templates.JapaneseTreeFeature;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;

public class SakuraTreeFeature extends JapaneseTreeFeature {

	public SakuraTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config, Direction direction, int length) {
		for (int i = 0; i < length - 1; i++) {
			pos.move(direction);
			setLogBlockState(world, rand, pos, logs, box, config);
		}

		pos.move(direction);
		tryPlaceLeaves(world, rand, pos, logs, leaves, box, config);
	}
}
