package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shapes;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terrestria.feature.tree.foliageplacers.templates.SmallFoliagePlacer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class SmallCanopyFoliagePlacer extends SmallFoliagePlacer {
	public static final MapCodec<SmallCanopyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(smallCanopyFoliagePlacerInstance ->
			foliagePlacerParts(smallCanopyFoliagePlacerInstance).apply(smallCanopyFoliagePlacerInstance, SmallCanopyFoliagePlacer::new));

	public SmallCanopyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> type() {
		return TerrestriaFoliagePlacerTypes.SMALL_CANOPY;
	}

	@Override
	protected void createFoliage(WorldGenLevel world, FoliageSetter placer, RandomSource random, TreeConfiguration config, int trunkHeight, FoliagePlacer.FoliageAttachment treeNode, int foliageHeight, int radius, int offset) {
		int diameter = treeNode.radiusOffset() * 2;
		BlockPos pos = treeNode.pos();

		Shapes.hemiEllipsoid(diameter * 1.1, diameter * 1.1, diameter * 1.8)
				.applyLayer(Layer.subtract(Shapes.hemiEllipsoid(diameter - 2, diameter - 2, diameter - 1)))
				.applyLayer(Layer.translate(Position.of(pos.below(2))))
				.stream()
				.forEach((position) -> {
					//On the bottom layer only place 50% of the blocks
					if (position.getY() != (pos.getY() - 1) || random.nextBoolean()) {
						tryPlaceLeaves(world, position.toBlockPos(), random, placer, config);
					}
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
