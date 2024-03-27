package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class SphereFoliagePlacer extends FoliagePlacer {
	public static final MapCodec<SphereFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
			fillFoliagePlacerFields(instance).apply(instance, SphereFoliagePlacer::new));

	public SphereFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.SPHERE;
	}

	@Override
	protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {

		// Add 0.25 to make it not a square and also not a single block
		Shapes.ellipsoid(radius + 0.25,radius + 0.25,radius + 0.25)
				.applyLayer(TranslateLayer.of(Position.of(treeNode.getCenter())))
				.stream()
				.forEach((block) -> {
					checkAndSetBlockState(world, random, block.toBlockPos(), placer, config);
				});
	}

	private void checkAndSetBlockState(TestableWorld world, Random random, BlockPos currentPosition, BlockPlacer placer, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			placer.placeBlock(currentPosition.toImmutable(), config.foliageProvider.get(random, currentPosition));
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
