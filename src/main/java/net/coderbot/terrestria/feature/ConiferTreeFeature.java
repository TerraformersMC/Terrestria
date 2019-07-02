package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
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

public class ConiferTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public ConiferTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	public ConiferTreeFeature sapling() {
		return new ConiferTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height
		int height = rand.nextInt(12) + 32;

		// How much "bare trunk" there will be.
		int bareTrunkHeight = 8 + rand.nextInt(12);

		// Maximum leaf radius.
		int maxRadius = 14 + rand.nextInt(8);

		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		BlockPos below = origin.down();

		if(!isNaturalDirtOrGrass(world, below)) {
			return false;
		}

		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);
		

		BlockPos.Mutable pos;
		//Small lil frill at the bottom
		pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, height/4);
		growLeaves(blocks, world, pos, height/2, bareTrunkHeight, maxRadius/3, boundingBox);

		//big boi on bottom
		pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, (height/4)+3);
		growLeaves(blocks, world, pos, height/2, bareTrunkHeight, maxRadius, boundingBox);

		//medium boi
		pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, height/2);
		growLeaves(blocks, world, pos, height/2, bareTrunkHeight, maxRadius - (maxRadius / 3), boundingBox);

		//smol boi on the tip
		pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, height/3);
		growLeaves(blocks, world, pos, height/2, bareTrunkHeight, maxRadius/2, boundingBox);

		return true;
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, int bareTrunkHeight, int maxRadius, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double radius;
		double innerRadius;

		for(int dy = 0; dy < height; dy++) {

			pos.set(x, y + dy, z);

			radius = maxRadius * radiusFactor(dy + bareTrunkHeight, height + bareTrunkHeight);
			innerRadius = (maxRadius/3.0) * radiusFactor(dy + bareTrunkHeight, height + bareTrunkHeight);
			if (innerRadius < 0) {
				innerRadius = 0;
			}

			if(radius < 0) {
				continue;
			}

			io.github.terraformersmc.terraform.util.Shapes.canopyCircle(pos, radius, innerRadius, position -> {
				if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
					setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
				}
			});
		}
	}

	private double radiusFactor(double x, double height) {
		//makes the polynomial apply to values from 0-the height
		x = x / height;

		// A 3rd-degree polynomial approximating the shape of a Conifer tree. from 0-1
		return -0.6 * (x*x*x) + 1.96 * (x*x) - 2.37 * x + 1;
	}

	private double innerRadiusFactor(double x, double height) {
		//makes the polynomial apply to values from 0-the height
		x = x / height;

		// A 3rd-degree polynomial approximating the inner shape of a Conifer tree. from 0-1
		return -3.24 * (x*x*x) + .25 * (x*x) - 2.98 * x + 1;
	}

	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < height; i++) {
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);

			pos.setOffset(Direction.UP);
		}
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

		return true;
	}
}
