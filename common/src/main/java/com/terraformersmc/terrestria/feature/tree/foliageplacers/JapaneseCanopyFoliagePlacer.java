package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.function.BiConsumer;

public class JapaneseCanopyFoliagePlacer extends FoliagePlacer {

	public static final Codec<JapaneseCanopyFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, JapaneseCanopyFoliagePlacer::new));

	public JapaneseCanopyFoliagePlacer(IntProvider radius, IntProvider offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.JAPANESE_CANOPY;
	}

	@Override
	protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {

		double width = treeNode.getFoliageRadius() * 2.25 + (random.nextFloat() - 0.5);
		double height = width * 1.75 + (random.nextFloat() - 0.5);
		BlockPos center = treeNode.getCenter();

		Shapes.hemiEllipsoid(width, width, height)
				.applyLayer(new AddLayer(Shapes.ellipticalPyramid(width * 0.707, width * 0.707, height / 4) // 0.707 is approximately sqrt(2)/2
						.applyLayer(new TranslateLayer(Position.of(0, height * 2/3, 0)))))
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(width - 2, width - 2, 5)))
				.applyLayer(TranslateLayer.of(Position.of(center.down(2))))
				.fill((position) -> {
					// On the bottom layer only place 50% of the blocks
					if (position.getY() - center.getY() >= 0 || random.nextBoolean()) {
						tryPlaceLeaves(world, position.toBlockPos(), random, replacer, config);
					}
				});
	}

	protected void tryPlaceLeaves(TestableWorld world, BlockPos pos, Random random, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config) {
		if (world.testBlockState(pos, BlockState::isAir)) {
			replacer.accept(pos, config.foliageProvider.get(random, pos));
		}
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
