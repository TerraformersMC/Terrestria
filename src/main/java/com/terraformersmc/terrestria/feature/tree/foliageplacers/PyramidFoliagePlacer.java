package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class PyramidFoliagePlacer extends FoliagePlacer {

	public static final Codec<PyramidFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			method_28846(instance).apply(instance, PyramidFoliagePlacer::new));

	public PyramidFoliagePlacer(int radius, int randomRadius, int offset, int randomOffset) {
		super(radius, randomRadius, offset, randomOffset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.PYRAMID;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int i, BlockBox blockBox) {

		radius = treeNode.getFoliageRadius();
		int offset = treeNode.isGiantTrunk() ? 1 : 0;
		int layerHeight = radius * 2;
		BlockPos.Mutable pos = treeNode.getCenter().mutableCopy();

		//Set the position to the northwest corner (-x,y,-z)
		pos.move(-radius, 0, -radius);

		int max = ((radius*2) + 1 + offset);
		//Place a layer
		for (int layer = 0; layer < layerHeight; layer++) {
			//place a square
			for (int x = 0; x < max; x++) {
				for (int z = 0; z < max; z++) {
					//Cull corners
					if ((x != 0 || (z != 0 && z != max - 1)) && (x != max - 1 || (z != 0 && z != max - 1))) {
						checkAndSetBlockState(world, random, pos, leaves, blockBox, config);
					}
					//Move the position on the z row once
					pos.move(0, 0, 1);
				}
				//After every z row move x once and reset z
				pos.move(1, 0, -((radius*2) + 1 + offset));
			}
			//After every layer move the starting point up once and in in both directions
			pos.move(-max + 1, 1, 1);
			radius--;
			max = ((radius*2) + 1 + offset);
		}
	}

	private void checkAndSetBlockState(ModifiableTestableWorld world, Random random, BlockPos currentPosition, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			world.setBlockState(currentPosition, config.leavesProvider.getBlockState(random, currentPosition), 19, 0);
			blockBox.encompass(new BlockBox(currentPosition, currentPosition));
			set.add(currentPosition.toImmutable());
		}
	}

	@Override
	public int getHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return false;
	}
}
