package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.templates.SmallFoliagePlacer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageSetter;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SmallLogSphereFoliagePlacer extends SmallFoliagePlacer {
	public static final MapCodec<SmallLogSphereFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(smallLogSphereFoliagePlacerInstance ->
			foliagePlacerParts(smallLogSphereFoliagePlacerInstance).apply(smallLogSphereFoliagePlacerInstance, SmallLogSphereFoliagePlacer::new));

	public SmallLogSphereFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.SMALL_LOG_SPHERE;
	}

	@Override
	protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {

		// Add 0.25 to make it not a square and also not a single block
		Shapes.ellipsoid(radius + 0.25,radius + 0.25,radius + 0.25)
				.applyLayer(TranslateLayer.of(Position.of(treeNode.pos())))
				.stream()
				.forEach((block) -> {
					tryPlaceLeaves(world, block.toBlockPos(), random, placer, config);
				});

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
