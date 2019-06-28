package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.util.Shapes;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class CypressTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public CypressTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	public CypressTreeFeature sapling() {
		return new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int height = rand.nextInt(10) + 8;

		// How much "bare trunk" there will be. (1-3)
		int bareTrunkHeight = 1 + rand.nextInt(2);

		// Maximum leaf radius.
		double maxRadius = 1.5 + 2.0 * rand.nextDouble();

		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if(!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if(!checkForObstructions(world, origin, height, bareTrunkHeight, (int)Math.ceil(maxRadius))) {
			return false;
		}

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);

		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);

		BlockPos.Mutable pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight);
		growLeaves(blocks, world, pos, height - bareTrunkHeight, maxRadius, boundingBox);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			if(!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height; dY++) {
			for(int dZ = -radius; dZ <= radius; dZ++) {
				for(int dX = -radius; dX <= radius; dX++) {
					pos.set(origin.getX() + dX, origin.getY() + dY, origin.getZ() + dZ);

					if(!canTreeReplace(world, pos)) {
						return false;
					}
				}
			}
		}

		pos.set(origin.getX(), origin.getY() + height, origin.getZ());

		for(int i = 0; i < 4; i++) {
			if(!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		return true;
	}

	// Grows the center trunk and top leaves of the tree.
	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < height; i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			pos.setOffset(Direction.UP);
		}

		for(int i = 0; i < 4; i++) {
			setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
			pos.setOffset(Direction.UP);
		}
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, double maxRadius, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double yScale = 16.0 / (height + 2);

		for(int dy = 0; dy < height + 4; dy++) {
			pos.set(x, y + dy, z);

			double progress = dy * yScale;
			double radius = maxRadius * radiusFactor(progress);

			if(radius < 0) {
				continue;
			}

			Shapes.circle(pos, radius, position -> {
				if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
				}
			});
		}
	}

	// Provides the factor to the radius, where x is a double from 0.0 to 1.0 that represents the progress along the trunk.
	private double radiusFactor(double x) {
		double x2 = x*x;
		double x3 = x2*x;

		// A 3rd-degree polynomial approximating the shape of a cypress tree - increasing rapidly, and then tapering off.
		return 0.00142 * x3 - 0.0517 * x2 + 0.5085 * x - 0.4611;
	}
}
