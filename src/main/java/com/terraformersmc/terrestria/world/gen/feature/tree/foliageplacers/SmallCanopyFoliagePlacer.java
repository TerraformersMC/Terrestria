package com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import com.terraformersmc.terrestria.world.gen.feature.tree.foliageplacers.templates.SmallFoliagePlacer;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class SmallCanopyFoliagePlacer extends SmallFoliagePlacer {

	public static final Codec<SmallCanopyFoliagePlacer> CODEC = RecordCodecBuilder.create(smallCanopyFoliagePlacerInstance ->
			fillFoliagePlacerFields(smallCanopyFoliagePlacerInstance).apply(smallCanopyFoliagePlacerInstance, SmallCanopyFoliagePlacer::new));

	public SmallCanopyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.SMALL_CANOPY;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {

		int diameter = treeNode.getFoliageRadius() * 2;
		BlockPos pos = treeNode.getCenter();

		Shapes.hemiEllipsoid(diameter * 1.1, diameter * 1.1, diameter * 1.8)
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(diameter - 2, diameter - 2, diameter - 1)))
				.applyLayer(TranslateLayer.of(Position.of(pos.down(2))))
				.stream()
				.forEach((position) -> {
					//On the bottom layer only place 50% of the blocks
					if (position.getY() != (pos.getY() - 1) || random.nextBoolean()) {
						tryPlaceLeaves(world, position.toBlockPos(), random, replacer, config);
					}
				});

	}

	@Override
	public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return false;
	}
}
