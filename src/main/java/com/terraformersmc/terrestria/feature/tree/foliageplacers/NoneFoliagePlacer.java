package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class NoneFoliagePlacer extends FoliagePlacer {
	private static final IntProvider ZERO = ConstantInt.of(0);
	// This has to come last, because static initialization order is important!
	public static final MapCodec<NoneFoliagePlacer> CODEC = MapCodec.unit(new NoneFoliagePlacer());

	public NoneFoliagePlacer() {
		super(ZERO, ZERO);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.NONE;
	}

	@Override
	protected void createFoliage(WorldGenLevel world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
		// It's the "None" placer, after all...
	}

	@Override
	public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return false;
	}
}
