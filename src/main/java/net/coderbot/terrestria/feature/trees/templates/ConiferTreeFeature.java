package net.coderbot.terrestria.feature.trees.templates;

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

public class ConiferTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;
	private int height;
	private int bareTrunkHeight;
	private int maxLeafRadius;
	private int leafLayers;
	private double shrinkAmount;

	public ConiferTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	public ConiferTreeFeature sapling() {
		return new ConiferTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		height = getLeafHeight(rand);
		bareTrunkHeight = getBareTrunkHeight(rand);
		maxLeafRadius = getMaxLeafRadius(rand);
		leafLayers = getLeafLayers(rand);
		shrinkAmount = getShrinkAmount();

		//If the tree doesn't have enough room with it's current height before build limit
		if(origin.getY() + height + 1 > 256 || origin.getY() < 1) {
			return false;
		}

		//If the ground below the sapling is good enough for a tree
		if(!isNaturalDirtOrGrass(world, origin.down())) {
			return false;
		}

		//If there is room for the tree
		if(!checkForObstructions(world, origin, height, bareTrunkHeight, maxLeafRadius)) {
			return false;
		}

		//Set the block below the trunk to dirt (because vanilla does it)
		setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height+bareTrunkHeight, boundingBox);
		growLeaves(blocks, world, new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight), height, maxLeafRadius, shrinkAmount, leafLayers, boundingBox);

		return true;
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, int maxRadius, double shrinkAmmount, int layers, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int layerHeight = (height/layers);
		int leafLayerHeight = 2*(height/layers);

		for (int layer = 0; layer < layers; layer++) {
			if (layer == (layers-1)) { //Limit the top layer to not be too pointy
				leafLayerHeight = layerHeight;
			}
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
		for(int i = 0; i < (height*0.83); i++) {
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

	public int getLeafHeight(Random rand) {
		return rand.nextInt(12) + 32;
	}

	public int getBareTrunkHeight(Random rand) {
		return 8 + rand.nextInt(12);
	}

	public int getMaxLeafRadius(Random rand) {
		return 6 + rand.nextInt(4);
	}

	public int getLeafLayers(Random rand) {
		return rand.nextInt(4) + 4;
	}

	public double getShrinkAmount() {
		return 1.0; //1.0 for 1 block
	}
}
