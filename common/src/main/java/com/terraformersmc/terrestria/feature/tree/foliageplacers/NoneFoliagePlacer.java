package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class NoneFoliagePlacer extends FoliagePlacer {
	private static final IntProvider ZERO = ConstantIntProvider.create(0);
	// This has to come last, because static initialization order is important!
	public static final MapCodec<NoneFoliagePlacer> CODEC = MapCodec.unit(new NoneFoliagePlacer());

	public NoneFoliagePlacer() {
		super(ZERO, ZERO);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.NONE;
	}

	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
		// It's the "None" placer, after all...
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
