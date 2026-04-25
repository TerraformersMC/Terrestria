package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageSetter;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class DotShrubPlacer extends FoliagePlacer {
	public static final MapCodec<DotShrubPlacer> CODEC = RecordCodecBuilder.mapCodec(dotShrubPlacerInstance ->
			foliagePlacerParts(dotShrubPlacerInstance).apply(dotShrubPlacerInstance, DotShrubPlacer::new));

	public DotShrubPlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.DOT_SHRUB;
	}

	@Override
	protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
		checkAndSetBlockState(world, random, treeNode.pos(), placer, config);
		Direction.Plane.HORIZONTAL.forEach((direction) -> checkAndSetBlockState(world, random, treeNode.pos().below().relative(direction), placer, config));
	}

	private void checkAndSetBlockState(LevelSimulatedReader world, RandomSource random, BlockPos currentPosition, FoliageSetter placer, TreeConfiguration config) {
		if (TreeFeature.validTreePos(world, currentPosition)) {
			placer.set(currentPosition.immutable(), config.foliageProvider.getState(random, currentPosition));
		}
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
