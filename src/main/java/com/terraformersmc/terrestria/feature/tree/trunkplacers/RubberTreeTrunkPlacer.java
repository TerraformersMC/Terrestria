package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.util.DirectionHelper;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RubberTreeTrunkPlacer extends TrunkPlacer {

	public static final Codec<RubberTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(rubberTreeTrunkPlacerInstance ->
			method_28904(rubberTreeTrunkPlacerInstance).apply(rubberTreeTrunkPlacerInstance, RubberTreeTrunkPlacer::new));

	public RubberTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.RUBBER_TREE;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Create the placer storage
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		//Place the trunk
		for (int i = 0; i < trunkHeight; i++) {
			method_27402(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
		}

		//Instance a direction before the loop so it no be slow mkay
		Direction branchDirection;

		//Place the rest of the trunk and branches
		for (int j = 0; j < trunkHeight + 3; j++) {
			method_27402(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
			branchDirection = Direction.Type.HORIZONTAL.random(random);
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), branchDirection, set, blockBox, treeFeatureConfig), 1, false));
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, branchDirection), set, blockBox, treeFeatureConfig), 1, false));
		}

		//Make sure the top gets some love
		foliageNodes.add(new FoliagePlacer.TreeNode(currentPosition, 1, false));

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(ModifiableTestableWorld world, Random random, BlockPos pos, Direction direction, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		BlockPos.Mutable currentPosition = pos.mutableCopy();
		//Place a block in the branch direction
		method_27402(world, random, currentPosition.move(direction), set, blockBox, treeFeatureConfig);
		//50% of the time place another block in the same general direction
		if (random.nextBoolean()) {
			//50% of the time make the branch move upwards
			if (random.nextBoolean()) {
				currentPosition.move(Direction.UP);
			}
			method_27402(world, random, currentPosition.move(DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite())), set, blockBox, treeFeatureConfig);
		}
		return currentPosition;
	}
}
