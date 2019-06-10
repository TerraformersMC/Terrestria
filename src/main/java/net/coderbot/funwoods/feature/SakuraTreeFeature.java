package net.coderbot.funwoods.feature;

import com.mojang.datafixers.Dynamic;
import net.coderbot.funwoods.block.SakuraLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class SakuraTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private BlockState wood;
	private BlockState leaves;
	private TreeDefinition.Sakura tree;

	public SakuraTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Sakura tree) {
		super(function, notify);

		this.wood = tree.wood;
		this.leaves = tree.leaves;
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

		// TODO: Not for Sakura setBlockState(blocks, world, origin.down(), Blocks.DIRT.getDefaultState(), boundingBox);

		placeGroundCover(blocks, world, new BlockPos.Mutable(origin), maxRadius, rand, boundingBox);

		growTrunk(blocks, world, new BlockPos.Mutable(origin), height, boundingBox);

		BlockPos.Mutable pos = new BlockPos.Mutable(origin).setOffset(Direction.UP, bareTrunkHeight);
		growLeaves(blocks, world, pos, height - bareTrunkHeight, maxRadius, rand, boundingBox);


		for(BlockPos log: blocks) {
			boolean leaves = world.testBlockState(log, tested -> tested.getBlock() instanceof SakuraLogBlock && tested.get(SakuraLogBlock.HAS_LEAVES));

			Predicate<BlockState> tester = tested -> tested.getBlock() instanceof SakuraLogBlock || (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();

			setBlockState(
					blocks,
					world,
					log,
					tree.wood
						.with(SakuraLogBlock.UP, world.testBlockState(log.up(), tester))
						.with(SakuraLogBlock.DOWN, world.testBlockState(log.down(), tester))
						.with(SakuraLogBlock.NORTH, world.testBlockState(log.north(), tester))
						.with(SakuraLogBlock.EAST, world.testBlockState(log.east(), tester))
						.with(SakuraLogBlock.SOUTH, world.testBlockState(log.south(), tester))
						.with(SakuraLogBlock.WEST, world.testBlockState(log.west(), tester))
						.with(SakuraLogBlock.HAS_LEAVES, leaves),
					boundingBox
			);
		}

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
			setBlockState(blocks, world, pos, wood, boundingBox);
			pos.setOffset(Direction.UP);
		}
	}

	private void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {
		if(world.testBlockState(pos, candidate -> candidate.getBlock() instanceof SakuraLogBlock)) {
			setBlockState(blocks, world, pos, tree.woodLeaves, boundingBox);
			return;
		}

		if(AbstractTreeFeature.isAirOrLeaves(world, pos)) {
			setBlockState(blocks, world, pos, leaves, boundingBox);
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

	private Direction placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < length; i++) {
			pos.setOffset(direction);
			setBlockState(blocks, world, pos, tree.wood, boundingBox);
		}

		return direction;
	}

	private void placeGroundCover(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, MutableIntBoundingBox boundingBox) {
		int x = origin.getX();
		int z = origin.getZ();

		Shapes.circle(origin, maxRadius, pos -> {
			if(pos.getX() == x && pos.getZ() == z) {
				// TODO: Consider replacing the center log with a leaf pile + log block?
				return;
			}

			if(rand.nextInt(2) != 0) {
				return;
			}

			BlockPos top = world.getTopPosition(Heightmap.Type.WORLD_SURFACE_WG, pos);

			if(AbstractTreeFeature.isAirOrLeaves(world, top) && AbstractTreeFeature.isNaturalDirtOrGrass(world, top.down())) {
				setBlockState(blocks, world, top, tree.leafPile, boundingBox);
			}
		});
	}
}
