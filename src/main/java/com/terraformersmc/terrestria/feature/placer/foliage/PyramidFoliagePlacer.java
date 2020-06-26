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
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class PyramidFoliagePlacer extends FoliagePlacer {

	public static final Codec<PyramidFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			method_28846(instance).apply(instance, PyramidFoliagePlacer::new));

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
		int offset = treeNode.isGiantTrunk() ? 1 : 0;
		int layerHeight = radius * 2;
		BlockPos pos = treeNode.getCenter();

		//Place a layer
		for (int layer = 0; layer < layerHeight; layer++) {
			//place a square
			for (int x = -radius; x < (radius + 1 + offset); x++) {
				for (int z = -radius; z < (radius + 1 + offset); z++) {
					//Cull corners
					if ((z != (radius + offset) || (x != -radius && x != (radius + offset))) && (z != -radius || (x != -radius && x != (radius + offset)))) {
						checkAndSetBlockState(world, random, pos.add(x, layer, z), leaves, blockBox, config);
					}
				}
			}
			radius--;
		}
	}

	private void checkAndSetBlockState(ModifiableTestableWorld world, Random random, BlockPos currentPosition, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			world.setBlockState(currentPosition, config.leavesProvider.getBlockState(random, currentPosition), 19);
			blockBox.encompass(new BlockBox(currentPosition, currentPosition));
			set.add(currentPosition.toImmutable());
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
