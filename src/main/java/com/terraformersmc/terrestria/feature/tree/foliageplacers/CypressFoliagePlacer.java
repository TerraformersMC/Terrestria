package com.terraformersmc.terrestria.feature.tree.foliageplacers;

import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.util.Shapes;
import com.terraformersmc.terrestria.init.TerrestriaFoliagePlacerTypes;

import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class CypressFoliagePlacer extends FoliagePlacer {
	public static final Codec<CypressFoliagePlacer> CODEC = RecordCodecBuilder.create(instance ->
			fillFoliagePlacerFields(instance).apply(instance, CypressFoliagePlacer::new));

	public CypressFoliagePlacer(UniformIntDistribution radius, UniformIntDistribution offset) {
		super(radius, offset);
	}

	@Override
	protected FoliagePlacerType<?> getType() {
		return TerrestriaFoliagePlacerTypes.CYPRESS;
	}

	@Override
	protected void generate(ModifiableTestableWorld world, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, Set<BlockPos> leaves, int i, BlockBox box) {
		double maxRadius = 1.5 + 1.5 * random.nextDouble();

		BlockPos.Mutable pos = treeNode.getCenter().mutableCopy();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double treeRadius;

		int extra = (int) ((trunkHeight * 1.67777) - trunkHeight);

		for (int dy = -trunkHeight; dy < extra; dy++) {
			pos.set(x, y + dy, z);

			treeRadius = maxRadius * radiusFactor(trunkHeight + dy, (trunkHeight + extra));

			if (treeRadius < 0) {
				continue;
			}

			Shapes.circle(pos.mutableCopy(), treeRadius, position -> {
				if (TreeFeature.isAirOrLeaves(world, position)) {
					world.setBlockState(position, config.leavesProvider.getBlockState(random, position), 19);
					box.encompass(new BlockBox(position, position));
					leaves.add(position.toImmutable());
				}
			});
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

	// Provides the factor to the radius, where x is a double from 0.0 to 1.0 that represents the progress along the trunk.
	private double radiusFactor(double x, double height) {
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a cypress tree - increasing rapidly, and then tapering off.
		return 6.25 * (x * x * x) - 12.5 * (x * x) + 6.25 * x;
	}
}
