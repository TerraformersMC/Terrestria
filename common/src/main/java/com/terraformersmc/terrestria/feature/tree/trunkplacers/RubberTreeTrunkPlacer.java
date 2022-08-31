package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class RubberTreeTrunkPlacer extends TrunkPlacer {

	public static final Codec<RubberTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(rubberTreeTrunkPlacerInstance ->
			fillTrunkPlacerFields(rubberTreeTrunkPlacerInstance).apply(rubberTreeTrunkPlacerInstance, RubberTreeTrunkPlacer::new));

	public RubberTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.RUBBER_TREE;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int trunkHeight, BlockPos pos, TreeFeatureConfig treeFeatureConfig) {
		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		// Create the placer storage
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		// Place the trunk
		for (int i = 0; i < trunkHeight; i++) {
			getAndSetState(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
		}

		// Instance a direction before the loop so it no be slow mkay
		Direction branchDirection;

		// Place the rest of the trunk and branches
		for (int j = 0; j < trunkHeight + 3; j++) {
			getAndSetState(world, replacer, random, currentPosition.move(Direction.UP), treeFeatureConfig);
			branchDirection = Direction.Type.HORIZONTAL.random(random);
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), branchDirection, replacer, treeFeatureConfig), 1, false));
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, branchDirection), replacer, treeFeatureConfig), 1, false));
		}

		// Make sure the top gets some love
		foliageNodes.add(new FoliagePlacer.TreeNode(currentPosition, 1, false));

		// Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(TestableWorld world, Random random, BlockPos pos, Direction direction, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig treeFeatureConfig) {
		BlockPos.Mutable currentPosition = pos.mutableCopy();
		// Place a block in the branch direction
		getAndSetState(world, replacer, random, currentPosition.move(direction), treeFeatureConfig);
		// 50% of the time place another block in the same general direction
		if (random.nextBoolean()) {
			// 50% of the time make the branch move upwards
			if (random.nextBoolean()) {
				currentPosition.move(Direction.UP);
			}
			getAndSetState(world, replacer, random, currentPosition.move(DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite())), treeFeatureConfig);
		}
		return currentPosition;
	}
}
