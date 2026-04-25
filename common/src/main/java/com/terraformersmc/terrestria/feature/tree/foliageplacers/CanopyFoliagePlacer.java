package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.AirValidator;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageSetter;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CanopyFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<CanopyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
			foliagePlacerParts(instance).apply(instance, CanopyFoliagePlacer::new));

	public CanopyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.CANOPY;
	}

	@Override
	protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {

		radius = treeNode.radiusOffset();
		BlockPos centerPos = treeNode.pos();

		Shapes.hemiEllipsoid(radius * 2, radius * 2, radius * 2.5)
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(radius * 2  - 2, radius * 2 - 2, radius * 1.5)))
				.applyLayer(TranslateLayer.of(Position.of(centerPos.below())))
				.stream().filter(AirValidator.of(world))
				.forEach(position -> {
					BlockPos pos = position.toBlockPos();
					placer.set(pos, config.foliageProvider.getState(random, pos));
				});
	}

	@Override
	public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
		return 0;
	}

	@Override
	protected boolean shouldSkipLocation(RandomSource random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return baseHeight == dz && dy == dz;
	}
}
