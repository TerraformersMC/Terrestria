package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer.FoliageSetter;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class JapaneseCanopyFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<JapaneseCanopyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
			foliagePlacerParts(instance).apply(instance, JapaneseCanopyFoliagePlacer::new));

	public JapaneseCanopyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.JAPANESE_CANOPY;
	}

	@Override
	protected void createFoliage(LevelSimulatedReader world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {

		double width = treeNode.radiusOffset() * 2.25 + (random.nextFloat() - 0.5);
		double height = width * 1.75 + (random.nextFloat() - 0.5);
		BlockPos center = treeNode.pos();

		Shapes.hemiEllipsoid(width, width, height)
				.applyLayer(new AddLayer(Shapes.ellipticalPyramid(width * 0.707, width * 0.707, height / 4) // 0.707 is approximately sqrt(2)/2
						.applyLayer(new TranslateLayer(Position.of(0, height * 2/3, 0)))))
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(width - 2, width - 2, 5)))
				.applyLayer(TranslateLayer.of(Position.of(center.below(2))))
				.fill((position) -> {
					// On the bottom layer only place 50% of the blocks
					if (position.getY() - center.getY() >= 0 || random.nextBoolean()) {
						tryPlaceLeaves(world, position.toBlockPos(), random, placer, config);
					}
				});
	}

	protected void tryPlaceLeaves(LevelSimulatedReader world, BlockPos pos, RandomSource random, FoliageSetter placer, TreeConfiguration config) {
		if (world.isStateAtPosition(pos, BlockState::isAir)) {
			placer.set(pos, config.foliageProvider.getState(random, pos));
		}
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
