package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.util.Shapes;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
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
		// Total leaf height
		int height = rand.nextInt(12) + 32;

		// How much "bare trunk" there will be below the leaves.
		int bareTrunkHeight = 8 + rand.nextInt(12);

		// Maximum leaf radius.
		int maxRadius = 6 + rand.nextInt(4);

		//Number of layers of leaves
		int layers = rand.nextInt(4) + 4;

		//The number blocks that a layer will shrink in radius per step.
		double shrinkAmount = 1.0; //1.0 for 1 block

		//If the tree doesn't have enough room with it's current height before build limit
		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		//If the ground below the sapling is good enough for a tree
		if(!isNaturalDirtOrGrass(world, origin.down())) {
			return false;
		}

		//If there is room for the tree
		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxRadius)) {
			return false;
		}

		//Set the block below the trunk to dirt (because vanilla does it)
		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height+bareTrunkHeight, boundingBox);
		growBranches(blocks, world, new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight), height, maxRadius, shrinkAmount, layers, boundingBox);
		growLeaves(blocks, world, new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight), height, maxRadius, shrinkAmount, layers, boundingBox);

		return true;
	}

	private void growBranches(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, int maxRadius, double shrinkAmount, int layers, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int layerHeight = (height/layers);
		int majorBranchLength = maxRadius - 2; //don't ask why, but this number just works for some reason
		int minotBranchLength = (int)((majorBranchLength / 2.0)); //half of the major branch length

		for (int layer = 0; layer < layers; layer++) {
			pos.set(x, y + (layer*layerHeight), z);
			majorBranchLength = (int) (majorBranchLength - (layer * (shrinkAmount/3)));
			for (int maBr = 0; maBr < majorBranchLength; maBr++) {
				BlockPos origin = pos.toImmutable();
				pos.setOffset(Direction.WEST, majorBranchLength);
				for(int xb = -majorBranchLength; xb <= majorBranchLength; xb++) {
					if (isAirOrLeaves(world, pos)) {
						setBlockState(blocks, world, pos, tree.getLog().with(LogBlock.AXIS, Direction.WEST.getAxis()), boundingBox);
					}
					pos.setOffset(Direction.EAST);
				}
				pos.set(origin);
				pos.setOffset(Direction.NORTH, majorBranchLength);
				for(int yb = -majorBranchLength; yb <= majorBranchLength; yb++) {
					if (isAirOrLeaves(world, pos)) {
						setBlockState(blocks, world, pos, tree.getLog().with(LogBlock.AXIS, Direction.NORTH.getAxis()), boundingBox);
					}
					pos.setOffset(Direction.SOUTH);
				}
				//reset the branches
				pos.set(origin);
			}

			if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
				setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
			}
		}
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, int maxRadius, double shrinkAmmount, int layers, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int layerHeight = (height/layers);
		int leafLayerHeight = 2*(height/layers);

		for (int layer = 0; layer < layers; layer++) {
			for(int currentHeight = 0; currentHeight < leafLayerHeight; currentHeight++) {

				pos.set(x, y + (layer*layerHeight) + currentHeight, z);

				Shapes.canopyCircle(pos,
						outerRadius(maxRadius - (layer * shrinkAmmount), currentHeight, leafLayerHeight),
						innerRadius(maxRadius - (layer * shrinkAmmount), currentHeight, layerHeight),
						position -> {
					if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
						setBlockState(blocks, world, pos, tree.getLeaves(), boundingBox);
					}
				});
			}
		}
	}

	/**
	 * @param maxRadius the maximum radius for the leaves to generate
	 * @param currentHeight the progress from 0 to the height that the leaves have generated (the x value of the polynomial)
	 * @param height the target height for the leaf layer
	 * @return the radius of the outside leaf layer at it's current y-height
	 */
	private double outerRadius(double maxRadius, double currentHeight, double height) {
		double x = currentHeight / height;
		// A 3rd-degree polynomial approximating the shape of a Conifer tree. from 0-1
		return maxRadius * ((-0.6 * (x*x*x) + 1.96 * (x*x) - 2.37 * x) + 1);
	}

	/**
	 * @param maxRadius the maximum radius for the leaves to generate
	 * @param currentHeight the progress from 0 to the height that the leaves have generated (the x value of the polynomial)
	 * @param height the target height for the leaf layer
	 * @return the radius of the inner leaf layer at it's current y-height
	 */
	private double innerRadius(double maxRadius, double currentHeight, double height) {
		double x = currentHeight / height;
		x = maxRadius * ((-3.24 * (x*x*x) + .25 * (x*x) - 2.98 * x) + 1) - 2.5;
		return x < 0 ? 0 : x;
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
