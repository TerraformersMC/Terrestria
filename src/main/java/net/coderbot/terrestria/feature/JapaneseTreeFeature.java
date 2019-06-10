package net.coderbot.terrestria.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.*;
import java.util.function.Function;

public abstract class JapaneseTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	protected TreeDefinition.Basic tree;

	public JapaneseTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);

		this.tree = tree;
	}

	@Override
	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos origin, MutableIntBoundingBox boundingBox) {
		// Total trunk height (8-11)
		int height = rand.nextInt(4) + 8;

		// How much "bare trunk" there will be. (2-4)
		int bareTrunkHeight = 2 + rand.nextInt(3);

		// Maximum leaf radius.
		double maxRadius = 4.0 + 2.5 * rand.nextDouble();

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

		// Place ground cover (leaf piles, turn grass under log to dirt, etc)
		placeGroundCover(blocks, world, new BlockPos.Mutable(origin), maxRadius, rand, boundingBox);

		// Place the log blocks making up the main trunk
		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);

		// Grow leaves and branches from the trunk
		BlockPos.Mutable pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight);
		growLeaves(blocks, world, pos, height - bareTrunkHeight, maxRadius, rand, boundingBox);

		// Fix up log block states if needed, such as with mini logs
		correctLogStates(blocks, world, boundingBox);

		return true;
	}

	protected abstract void placeGroundCover(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, MutableIntBoundingBox boundingBox);
	protected abstract void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox);
	protected abstract void correctLogStates(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox);

	private boolean checkForObstructions(TestableWorld world, BlockPos origin, int height, int bareTrunkHeight, int maxRadius) {
		BlockPos.Mutable pos = new BlockPos.Mutable(origin);

		for(int i = 0; i < bareTrunkHeight; i++) {
			if(!canTreeReplace(world, pos.setOffset(Direction.UP))) {
				return false;
			}
		}

		for(int dY = bareTrunkHeight; dY < height + 1; dY++) {
			int radius = maxRadius * (int)Math.ceil(Math.cos((dY - bareTrunkHeight) * 1.3 / height));

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

	// Grows the center trunk and top leaves of the tree.
	private void growTrunk(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < height; i++) {
			setBlockState(blocks, world, pos, tree.wood, boundingBox);
			pos.setOffset(Direction.UP);
		}
	}

	protected void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {
		if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
			setBlockState(blocks, world, pos, tree.leaves, boundingBox);
		}
	}

	private void growLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int height, double maxRadius, Random rand, MutableIntBoundingBox boundingBox) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		BlockPos origin = pos.toImmutable();

		List<Direction> directionSet = Arrays.asList(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
		Collections.shuffle(directionSet);
		Iterator<Direction> directions = directionSet.iterator();

		for(int layer = 0; layer < 2; layer++) {
			int chance = 2 + layer * 5;
			double radius = maxRadius * Math.cos(layer * 1.3 / height);
			double innerRadius = Math.max(radius, 2) - 2;

			for(int i = 0; i < 2; i++) {
				pos.set(x, y + layer, z);

				Direction direction = directions.next();
				placeBranch(blocks, world, pos, (int)Math.ceil(innerRadius), direction, boundingBox);

				pos.setOffset(direction);
				tryPlaceLeaves(blocks, world, pos, boundingBox);
			}

			pos.set(x, y + layer, z);
			Shapes.canopyCircle(pos, radius, innerRadius,
				(position) -> {
					if(rand.nextInt(chance) == 0 && pos.getX() != origin.getX() && pos.getZ() != origin.getZ()) {
						return;
					}

					tryPlaceLeaves(blocks, world, pos, boundingBox);
				}
			);
		}

		for(int layer = 2; layer < height + 1; layer++) {
			double radius = maxRadius * Math.cos(layer * 1.3 / height);
			double innerRadius = maxRadius * Math.max(Math.cos(layer * (Math.PI / 4.0) / height + (Math.PI/3.0)), 0);

			if(layer < height) {
				Direction direction;

				switch(rand.nextInt(4)) {
					case 0: direction = Direction.EAST; break;
					case 1: direction = Direction.WEST; break;
					case 2: direction = Direction.SOUTH; break;
					default: direction = Direction.NORTH; break;
				}

				pos.set(x, y + layer, z);
				placeBranch(blocks, world, pos, (int)Math.ceil(innerRadius), direction, boundingBox);
			}

			pos.set(x, y + layer, z);
			Shapes.canopyCircle(pos, radius, innerRadius,
				(position) -> tryPlaceLeaves(blocks, world, pos, boundingBox)
			);
		}
	}
}
