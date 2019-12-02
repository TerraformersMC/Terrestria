package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.JapaneseTreeFeature;
import net.minecraft.block.LogBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class JapaneseMapleTreeFeature extends JapaneseTreeFeature {
	public JapaneseMapleTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Basic tree) {
		super(function, tree);
	}

	@Override
	public void placeGroundCover(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, BlockBox box) {
		setToDirt(world, origin.down());
	}

	@Override
	public void placeBranch(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, BlockBox box) {
		for (int i = 0; i < length - 1; i++) {
			pos.setOffset(direction);
			PortUtil.setBlockState(logs, world, pos, tree.getLog().with(LogBlock.AXIS, direction.getAxis()), box);
		}

		pos.setOffset(direction);
		tryPlaceLeaves(logs, world, pos, box);
	}

	@Override
	public void correctLogStates(Set<BlockPos> logs, ModifiableTestableWorld world, BlockBox box) {
	}
}
