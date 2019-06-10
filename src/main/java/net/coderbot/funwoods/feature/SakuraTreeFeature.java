package net.coderbot.funwoods.feature;

import com.mojang.datafixers.Dynamic;
import net.coderbot.funwoods.block.SakuraLogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class SakuraTreeFeature extends JapaneseTreeFeature {
	private TreeDefinition.Sakura tree;

	public SakuraTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Sakura tree) {
		super(function, notify, tree);

		this.tree = tree;
	}

	@Override
	protected void placeGroundCover(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, MutableIntBoundingBox boundingBox) {
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

			if(!AbstractTreeFeature.isAirOrLeaves(world, top)) {
				return;
			}

			if(AbstractTreeFeature.isNaturalDirtOrGrass(world, top.down()) ||
					world.testBlockState(top.down(), state -> state.getFluidState().getFluid().matches(FluidTags.WATER))) {
				setBlockState(blocks, world, top, tree.leafPile, boundingBox);
			}
		});
	}

	@Override
	protected void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < length; i++) {
			pos.setOffset(direction);
			setBlockState(blocks, world, pos, tree.wood, boundingBox);
		}
	}

	@Override
	protected void correctLogStates(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox) {
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
	}

	@Override
	protected void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {
		if(world.testBlockState(pos, candidate -> candidate.getBlock() instanceof SakuraLogBlock)) {
			setBlockState(blocks, world, pos, tree.woodLeaves, boundingBox);
		} else {
			super.tryPlaceLeaves(blocks, world, pos, boundingBox);
		}
	}
}
