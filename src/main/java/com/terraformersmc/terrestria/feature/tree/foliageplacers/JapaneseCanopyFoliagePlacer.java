package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.AirValidator;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.feature.helpers.shapes.SetFiller;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class JapaneseCanopyFoliagePlacer extends FoliagePlacer {

	public static final Codec<JapaneseCanopyFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			method_28846(instance).apply(instance, JapaneseCanopyFoliagePlacer::new));

	public JapaneseCanopyFoliagePlacer(int radius, int randomRadius, int offset, int randomOffset) {
		super(radius, randomRadius, offset, randomOffset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.CANOPY;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int diameter, Set<BlockPos> leaves, int i, BlockBox blockBox) {

		diameter = treeNode.getFoliageRadius() * 2;
		BlockPos pos = treeNode.getCenter();

		Shapes.hemiEllipsoid(diameter, diameter, diameter * 1.8)
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(diameter - 2, diameter - 2, diameter - 1)))
				.applyLayer(TranslateLayer.of(Position.of(pos.down(2))))
				.stream()
				.forEach((position) -> {
					//On the bottom layer only place 50% of the blocks
					if (position.getY() != (pos.getY() - 1) || random.nextBoolean()) {
						tryPlaceLeaves(world, position.toBlockPos(), random, config);
					}
				});
	}

	protected void tryPlaceLeaves(ModifiableTestableWorld world, BlockPos pos, Random random, TreeFeatureConfig config) {
		if (world.testBlockState(pos, BlockState::isAir)) {
			world.setBlockState(pos, config.leavesProvider.getBlockState(random, pos), 0);
		}
	}

	@Override
	public int getHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
		return 0;
	}

	@Override
	protected boolean isInvalidForLeaves(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
		return baseHeight == dz && dy == dz;
	}
}
