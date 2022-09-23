package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.function.BiConsumer;

public class PyramidFoliagePlacer extends FoliagePlacer {

	public static final Codec<PyramidFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, PyramidFoliagePlacer::new));

	public PyramidFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.PYRAMID;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {

		// Add 0.25 to make it not a square and also not a single block
		Shapes.ellipticalPyramid(2 * radius + 0.25,2 * radius + 0.25,trunkHeight + 0.25)
				.applyLayer(TranslateLayer.of(Position.of(treeNode.getCenter()).move(0, -trunkHeight, 0)))
				.stream()
				.forEach((block) -> {
					checkAndSetBlockState(world, random, block.toBlockPos(), replacer, config);
				});
	}

	private void checkAndSetBlockState(TestableWorld world, Random random, BlockPos currentPosition, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			replacer.accept(currentPosition.toImmutable(), config.foliageProvider.getBlockState(random, currentPosition));
		}
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
