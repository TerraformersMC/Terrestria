package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NullMarked;

@NullMarked
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
	protected void createFoliage(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
		checkAndSetBlockState(level, random, treeNode.pos(), setter, config);
		Direction.Plane.HORIZONTAL.forEach((direction) -> checkAndSetBlockState(level, random, treeNode.pos().below().relative(direction), setter, config));
	}

	private void checkAndSetBlockState(WorldGenLevel level, RandomSource random, BlockPos currentPosition, FoliageSetter setter, TreeConfiguration config) {
		if (TreeFeature.validTreePos(level, currentPosition)) {
			setter.set(currentPosition.immutable(), config.foliageProvider.getState(level, random, currentPosition));
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
