package net.coderbot.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.util.Shapes;
import net.coderbot.terrestria.feature.TreeDefinition;
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
		// Total tree height
		int height = rand.nextInt(5) + 12;

		// Maximum leaf radius.
		double maxRadius = 1.25 + 2 * rand.nextDouble();

		//If the tree can pass the max build height
		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();
		if(!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if(!checkForObstructions(world, origin, height, (int)Math.ceil(maxRadius))) {
			return false;
		}

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);

		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);

		BlockPos.Mutable pos = new BlockPos.Mutable(origin);
		growLeaves(blocks, world, pos, height, maxRadius, boundingBox);

		return true;
	}

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int radius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int dY = origin.getY(); dY < height; dY++) {
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

	// Grows the center trunk.
	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < (height * .6) ; i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			pos.setOffset(Direction.UP);
		}
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, double maxRadius, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double radius;

		for(int dy = 0; dy < height; dy++) {
			pos.set(x, y + dy, z);

			radius = maxRadius * radiusFactor(dy, height);

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
	private double radiusFactor(double x, double height) {
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a cypress tree - increasing rapidly, and then tapering off.
		return 6.25 * (x*x*x) - 12.5 * (x*x) + 6.25 * x;
	}
}
