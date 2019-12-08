package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.components.Roots;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeatureMega;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class RedwoodTreeFeatureMega extends ConiferTreeFeatureMega implements Roots {
	private TreeDefinition.Mega tree;

	public RedwoodTreeFeatureMega(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Mega tree) {
		super(function, tree);
		this.tree = tree;
	}

	public void growRoots(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, BlockBox box) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(logs, world, pos.set(x - 1, y, z + rand.nextInt(2)), baseTrunkHeight, rand, box);
		tryGrowRoot(logs, world, pos.set(x + 2, y, z + rand.nextInt(2)), baseTrunkHeight, rand, box);
		tryGrowRoot(logs, world, pos.set(x + rand.nextInt(2), y, z - 1), baseTrunkHeight, rand, box);
		tryGrowRoot(logs, world, pos.set(x + rand.nextInt(2), y, z + 2), baseTrunkHeight, rand, box);
	}

	public void tryGrowRoot(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable bottom, int baseTrunkHeight, Random rand, BlockBox box) {
		if (rand.nextInt(5) == 0) {
			return;
		}

		int height = rand.nextInt(4) + 1;

		for (int i = 0; i < height; i++) {
			if (canTreeReplace(world, bottom) || AbstractTreeFeature.isReplaceablePlant(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				PortUtil.setBlockState(logs, world, bottom, tree.getBark(), box);
			}

			bottom.setOffset(Direction.UP);
		}
	}

	@Override
	public int getHeight(Random rand) {
		return rand.nextInt(16) + 32;
	}

	@Override
	public int getBareTrunkHeight(Random rand) {
		return 12 + rand.nextInt(8);
	}
}
