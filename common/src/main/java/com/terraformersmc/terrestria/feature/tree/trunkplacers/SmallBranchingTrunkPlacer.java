package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@NullMarked
public class SmallBranchingTrunkPlacer extends SmallTrunkPlacer {
	public static final MapCodec<SmallBranchingTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(smallBranchingTrunkPlacerInstance ->
			trunkPlacerParts(smallBranchingTrunkPlacerInstance).apply(smallBranchingTrunkPlacerInstance, SmallBranchingTrunkPlacer::new));

	public SmallBranchingTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.SMALL_BRANCHING;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.DOWN);

		// Create the placer storage
		ArrayList<FoliagePlacer.FoliageAttachment> foliageNodes = new ArrayList<>();

		// The trunk height before branches
		int baseHeight = (int)((trunkHeight / 2f) + 0.5f);
		// The trunk height after branches
		int restHeight = trunkHeight - baseHeight - 2;

		// Place the base trunk
		for (int base = 0; base < baseHeight; base++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		// The First branch direction
		Direction mainBranchDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);

		// Sometimes I want to have small branches on the end of the branch
		if (random.nextBoolean()) {
			// Place a long branch and save it's end location
			BlockPos end = placeBranch(treeFeatureConfig, random, replacer, world, currentPosition.mutable(), mainBranchDirection, 3 + random.nextInt(1));
			// Place 2 small branches going in the same general direction as the main branch
			foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(treeFeatureConfig, random, replacer, world, end.mutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, mainBranchDirection.getOpposite()), 1 + random.nextInt(1)), 1, false));
			foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(treeFeatureConfig, random, replacer, world, end.mutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, mainBranchDirection.getOpposite()), 2 + random.nextInt(1)), 1, false));
		} else {
			foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(treeFeatureConfig, random, replacer, world, currentPosition.mutable(), mainBranchDirection, 3 + random.nextInt(1)), 1, false));
		}

		// 50% of the time, do it again, but on one of the other 3 sides of the tree
		if (random.nextBoolean()) {
			Direction secondaryBranchDirection = DirectionHelper.randomHorizontalDirectionAwayFrom(random, mainBranchDirection);
			// Sometimes I want to have small branches on the end of the branch
			if (random.nextBoolean()) {
				// Place a long branch and save it's end location
				BlockPos end = placeBranch(treeFeatureConfig, random, replacer, world, currentPosition.mutable(), secondaryBranchDirection, 3 + random.nextInt(1));
				// Place 2 small branches going in the same general direction as the main branch
				foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(treeFeatureConfig, random, replacer, world, end.mutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, secondaryBranchDirection.getOpposite()), 1 + random.nextInt(1)), 1, false));
				foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(treeFeatureConfig, random, replacer, world, end.mutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, secondaryBranchDirection.getOpposite()), 2 + random.nextInt(1)), 1, false));
			} else {
				foliageNodes.add(new FoliagePlacer.FoliageAttachment(placeBranch(treeFeatureConfig, random, replacer, world, currentPosition.mutable(), secondaryBranchDirection, 3 + random.nextInt(1)), 1, false));
			}
		}

		// Place the remainder of the tree
		for (int rest = 0; rest < restHeight; rest++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		foliageNodes.add(new FoliagePlacer.FoliageAttachment(currentPosition, 1, false));

		// Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(TreeConfiguration config, RandomSource random, BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel world, BlockPos.MutableBlockPos origin, Direction direction, int length) {
		// Place the supporting branch in the correct direction
		setBlockStateAndUpdate(config, random, replacer, world, origin.move(direction), direction);
		// Place the rest of the branch upwards
		for (int position = 0; position < length; position++) {
			setBlockStateAndUpdate(config, random, replacer, world, origin.move(Direction.UP), Direction.UP);
		}
		return origin;
	}
}
