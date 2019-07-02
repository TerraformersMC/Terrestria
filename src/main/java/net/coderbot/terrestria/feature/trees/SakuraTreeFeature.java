package net.coderbot.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import io.github.terraformersmc.terraform.block.SmallLogBlock;
import io.github.terraformersmc.terraform.util.Shapes;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.feature.trees.components.Branches;
import net.coderbot.terrestria.feature.trees.components.SmallLogs;
import net.coderbot.terrestria.feature.trees.templates.JapaneseTreeFeature;
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

public class SakuraTreeFeature extends JapaneseTreeFeature implements SmallLogs, Branches {
	private TreeDefinition.Sakura tree;
	private boolean worldgen;

	public SakuraTreeFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Sakura tree) {
		super(function, notify, tree);

		this.tree = tree;
		this.worldgen = !notify;
	}

	public SakuraTreeFeature sapling() {
		return new SakuraTreeFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	@Override
	public void placeGroundCover(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable origin, double maxRadius, Random rand, MutableIntBoundingBox boundingBox) {
		int x = origin.getX();
		int z = origin.getZ();

		Shapes.circle(origin, maxRadius, pos -> {
			if(pos.getX() == x && pos.getZ() == z) {
				return;
			}

			if(rand.nextInt(2) != 0) {
				return;
			}

			BlockPos top = world.getTopPosition(worldgen ? Heightmap.Type.WORLD_SURFACE_WG : Heightmap.Type.WORLD_SURFACE, pos);

			if(!AbstractTreeFeature.isAirOrLeaves(world, top)) {
				return;
			}

			if(AbstractTreeFeature.isNaturalDirtOrGrass(world, top.down()) ||
					world.testBlockState(top.down(), state -> state.getFluidState().getFluid().matches(FluidTags.WATER))) {
				setBlockState(blocks, world, top, tree.getLeafPile(), boundingBox);
			}
		});
	}

	@Override
	public void placeBranch(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, int length, Direction direction, MutableIntBoundingBox boundingBox) {
		for(int i = 0; i < length; i++) {
			pos.setOffset(direction);
			setBlockState(blocks, world, pos, tree.getLog(), boundingBox);
		}
	}

	@Override
	public void correctLogStates(Set<BlockPos> blocks, ModifiableTestableWorld world, MutableIntBoundingBox boundingBox) {
		for(BlockPos log: blocks) {
			boolean leaves = world.testBlockState(log, tested -> tested.getBlock() instanceof SmallLogBlock && tested.get(SmallLogBlock.HAS_LEAVES));

			Predicate<BlockState> tester = tested -> tested.getBlock() instanceof SmallLogBlock || (!leaves && tested.getBlock() instanceof LeavesBlock) || tested.isOpaque();

			setBlockState(
					blocks,
					world,
					log,
					tree.getLog()
							.with(SmallLogBlock.UP, world.testBlockState(log.up(), tester))
							.with(SmallLogBlock.DOWN, world.testBlockState(log.down(), tester))
							.with(SmallLogBlock.NORTH, world.testBlockState(log.north(), tester))
							.with(SmallLogBlock.EAST, world.testBlockState(log.east(), tester))
							.with(SmallLogBlock.SOUTH, world.testBlockState(log.south(), tester))
							.with(SmallLogBlock.WEST, world.testBlockState(log.west(), tester))
							.with(SmallLogBlock.HAS_LEAVES, leaves),
					boundingBox
			);
		}
	}

	@Override
	protected void tryPlaceLeaves(Set<BlockPos> blocks, ModifiableTestableWorld world, BlockPos.Mutable pos, MutableIntBoundingBox boundingBox) {
		if(world.testBlockState(pos, candidate -> candidate.getBlock() instanceof SmallLogBlock)) {
			setBlockState(blocks, world, pos, tree.getLogLeaves(), boundingBox);
		} else {
			super.tryPlaceLeaves(blocks, world, pos, boundingBox);
		}
	}
}
