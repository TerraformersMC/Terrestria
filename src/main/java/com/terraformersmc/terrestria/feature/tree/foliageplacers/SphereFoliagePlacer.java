package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shapes;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SphereFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<SphereFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
			foliagePlacerParts(instance).apply(instance, SphereFoliagePlacer::new));

	public SphereFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.SPHERE;
	}

	@Override
	protected void createFoliage(WorldGenLevel level, FoliageSetter setter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {

		// Add 0.25 to make it not a square and also not a single block
		Shapes.ellipsoid(radius + 0.25,radius + 0.25,radius + 0.25)
				.applyLayer(Layer.translate(Position.of(treeNode.pos())))
				.stream()
				.forEach((block) -> {
					checkAndSetBlockState(level, random, block.toBlockPos(), setter, config);
				});
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
