package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class NoneFoliagePlacer extends FoliagePlacer {
	private static final IntProvider ZERO = ConstantIntProvider.create(0);
	// This has to come last, because static initialization order is important!
	public static final Codec<NoneFoliagePlacer> CODEC = Codec.unit(new NoneFoliagePlacer());

	public NoneFoliagePlacer() {
		super(ZERO, ZERO);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.NONE;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {

	}

	@Override
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return false;
	}
}
