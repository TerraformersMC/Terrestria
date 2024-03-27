package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.AirValidator;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CanopyFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<CanopyFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
			fillFoliagePlacerFields(instance).apply(instance, CanopyFoliagePlacer::new));

	public CanopyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.CANOPY;
	}

	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {

		radius = treeNode.getFoliageRadius();
		BlockPos centerPos = treeNode.getCenter();

		Shapes.hemiEllipsoid(radius * 2, radius * 2, radius * 2.5)
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(radius * 2  - 2, radius * 2 - 2, radius * 1.5)))
				.applyLayer(TranslateLayer.of(Position.of(centerPos.down())))
				.stream().filter(AirValidator.of(world))
				.forEach(position -> {
					BlockPos pos = position.toBlockPos();
					placer.placeBlock(pos, config.foliageProvider.get(random, pos));
				});
	}

	@Override
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return baseHeight == dz && dy == dz;
	}
}
