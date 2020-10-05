package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class SphereFoliagePlacer extends FoliagePlacer {

	public static final Codec<SphereFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, SphereFoliagePlacer::new));

	public SphereFoliagePlacer(UniformIntDistribution radius, UniformIntDistribution offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.SPHERE;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int i, BlockBox blockBox) {

		//I add 0.5 to make it not a square and also not a single block
		Shapes.ellipsoid(radius + 0.25,radius + 0.25,radius + 0.25)
				.applyLayer(TranslateLayer.of(Position.of(treeNode.getCenter())))
				.stream()
				.forEach((block) -> {
					checkAndSetBlockState(world, random, block.toBlockPos(), leaves, blockBox, config);
				});
	}

	private void checkAndSetBlockState(ModifiableTestableWorld world, Random random, BlockPos currentPosition, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig config) {
		if (TreeFeature.canReplace(world, currentPosition)) {
			world.setBlockState(currentPosition, config.leavesProvider.getBlockState(random, currentPosition), 19);
			blockBox.encompass(new BlockBox(currentPosition, currentPosition));
			set.add(currentPosition.toImmutable());
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
