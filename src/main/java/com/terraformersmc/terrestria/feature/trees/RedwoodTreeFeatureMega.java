package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.components.Roots;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeatureMega;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class RedwoodTreeFeatureMega extends ConiferTreeFeatureMega implements Roots {
	private TreeDefinition.Mega tree;

	public RedwoodTreeFeatureMega(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Mega tree) {
		super(function, notify, tree);
		this.tree = tree;
	}

	public void growRoots(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(blocks, world, pos.set(x - 1, y, z + rand.nextInt(2)), baseTrunkHeight, rand, boundingBox);
		tryGrowRoot(blocks, world, pos.set(x + 2, y, z + rand.nextInt(2)), baseTrunkHeight, rand, boundingBox);
		tryGrowRoot(blocks, world, pos.set(x + rand.nextInt(2), y, z - 1), baseTrunkHeight, rand, boundingBox);
		tryGrowRoot(blocks, world, pos.set(x + rand.nextInt(2), y, z + 2), baseTrunkHeight, rand, boundingBox);
	}

	public void tryGrowRoot(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable bottom, int baseTrunkHeight, Random rand, MutableIntBoundingBox boundingBox) {
		if (rand.nextInt(5) == 0) {
			return;
		}

		int height = rand.nextInt(4) + 1;

		for (int i = 0; i < height; i++) {
			if (canTreeReplace(world, bottom) || AbstractTreeFeature.isReplaceablePlant(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				setBlockState(blocks, world, bottom, tree.getBark(), boundingBox);
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
