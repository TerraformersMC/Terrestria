package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.shapes.api.Position;
import com.terraformersmc.shapes.impl.Shapes;
import com.terraformersmc.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.shapes.impl.validator.AirValidator;
import com.terraformersmc.terrestria.feature.helpers.shapes.SetFiller;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class CanopyFoliagePlacer extends FoliagePlacer {

	public static final Codec<CanopyFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			method_28846(instance).apply(instance, CanopyFoliagePlacer::new));

	public CanopyFoliagePlacer(int radius, int randomRadius, int offset, int randomOffset) {
		super(radius, randomRadius, offset, randomOffset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.CANOPY;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int i, BlockBox blockBox) {

		radius = treeNode.getFoliageRadius();
		BlockPos pos = treeNode.getCenter();

		Shapes.hemiEllipsoid(radius, radius, 5)
				.applyLayer(new SubtractLayer(Shapes.hemiEllipsoid(radius - 1, radius - 1, 2)))
				.applyLayer(TranslateLayer.of(Position.of(pos.down())))
				.stream().filter(AirValidator.of(world))
				.forEach(SetFiller.of(world, config.leavesProvider.getBlockState(random, pos), leaves));
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
