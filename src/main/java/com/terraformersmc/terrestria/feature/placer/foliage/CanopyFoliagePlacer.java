package com.terraformersmc.terrestria.feature.placer.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.shapes.api.Position;
import com.terraformersmc.shapes.impl.Shapes;
import com.terraformersmc.shapes.impl.filler.SimpleFiller;
import com.terraformersmc.shapes.impl.layer.pathfinder.SubtractLayer;
import com.terraformersmc.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
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

	public static final Codec<CanopyFoliagePlacer> CODEC = RecordCodecBuilder.create(droopyFoliagePlacerInstance ->
			method_28846(droopyFoliagePlacerInstance).apply(droopyFoliagePlacerInstance, CanopyFoliagePlacer::new));

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

		Shapes.ellipsoid(radius, radius, 4)
				.applyLayer(new SubtractLayer(Shapes.ellipsoid(radius -1, radius - 1, 2)))
				//.validate(AirValidator.of(world))
				.applyLayer(TranslateLayer.of(Position.of(pos)))
				.applyLayer(new SubtractLayer(Shapes.ellipticalPrism(radius, radius, 6).applyLayer(TranslateLayer.of(Position.of(pos.down(4))))))
				.fill(SimpleFiller.of(world, TerrestriaBlocks.WILLOW.leaves.getDefaultState()));
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
