package com.terraformersmc.terrestria.feature.placer.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.shapes.api.Position;
import com.terraformersmc.shapes.impl.Shapes;
import com.terraformersmc.shapes.impl.filler.SimpleFiller;
import com.terraformersmc.shapes.impl.layer.pathfinder.AddLayer;
import com.terraformersmc.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.shapes.impl.validator.AirValidator;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class PyramidFoliagePlacer extends FoliagePlacer {

	public static final Codec<PyramidFoliagePlacer> CODEC = RecordCodecBuilder.create(megaConiferFoliagePlacerInstance ->
			method_28846(megaConiferFoliagePlacerInstance).apply(megaConiferFoliagePlacerInstance, PyramidFoliagePlacer::new));

	public PyramidFoliagePlacer(int radius, int randomRadius, int offset, int randomOffset) {
		super(radius, randomRadius, offset, randomOffset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.PYRAMID;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int i, BlockBox blockBox) {

		radius = treeNode.getFoliageRadius();
		BlockPos pos = treeNode.getCenter();

		if (treeNode.isGiantTrunk()) {
			Shapes.rectangularPyramid(radius * 2, radius, radius * 2)
					.applyLayer(new AddLayer(Shapes.rectangularPyramid(radius * 2, radius, radius * 2).applyLayer(TranslateLayer.of(Position.of(0, 0, 1)))))
					.applyLayer(new AddLayer(Shapes.rectangularPyramid(radius * 2, radius, radius * 2).applyLayer(TranslateLayer.of(Position.of(1, 0, 0)))))
					.applyLayer(new AddLayer(Shapes.rectangularPyramid(radius * 2, radius, radius * 2).applyLayer(TranslateLayer.of(Position.of(1, 0, 1)))))
					.applyLayer(TranslateLayer.of(Position.of(pos)))
					.stream().filter(AirValidator.of(world))
					.forEach(SimpleFiller.of(world, config.leavesProvider.getBlockState(random, pos)));
		} else {
			Shapes.rectangularPyramid(radius * 2, radius, radius * 2)
					.applyLayer(TranslateLayer.of(Position.of(pos)))
					.stream().filter(AirValidator.of(world))
					.forEach(SimpleFiller.of(world, config.leavesProvider.getBlockState(random, pos)));
		}
	}

	@Override
	public int getHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return false;
	}
}
