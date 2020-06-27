package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.helpers.DirectionHelper;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SmallBranchingTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SmallBranchingTrunkPlacer> CODEC = RecordCodecBuilder.create(smallBranchingTrunkPlacerInstance ->
			method_28904(smallBranchingTrunkPlacerInstance).apply(smallBranchingTrunkPlacerInstance, SmallBranchingTrunkPlacer::new));

	public SmallBranchingTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SMALL_BRANCHING;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Create the placer storage
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		//The trunk height before branches
		int baseHeight = (int)((trunkHeight / 2) + 0.5);
		//The trunk height after branches
		int restHeight = trunkHeight - baseHeight - 2;

		//Place the base trunk
		for (int base = 0; base < baseHeight; base++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);
		}

		//The First branch direction
		Direction mainBranchDirection = Direction.Type.HORIZONTAL.random(random);

		//Sometimes I want to have small branches on the end of the branch
		if (random.nextBoolean()) {
			//Place a long branch and save it's end location
			BlockPos end = placeBranch(treeFeatureConfig, random, set, world, currentPosition.mutableCopy(), mainBranchDirection, 3 + random.nextInt(1), blockBox);
			//Place 2 small branches going in the same general direction as the main branch
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(treeFeatureConfig, random, set, world, end.mutableCopy(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, mainBranchDirection.getOpposite()), 1 + random.nextInt(1), blockBox), 1, false));
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(treeFeatureConfig, random, set, world, end.mutableCopy(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, mainBranchDirection.getOpposite()), 2 + random.nextInt(1), blockBox), 1, false));
		} else {
			foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(treeFeatureConfig, random, set, world, currentPosition.mutableCopy(), mainBranchDirection, 3 + random.nextInt(1), blockBox), 1, false));
		}

		//50% of the time, do it again, but on one of the other 3 sides of the tree
		if (random.nextBoolean()) {
			Direction secondaryBranchDirection = DirectionHelper.randomHorizontalDirectionAwayFrom(random, mainBranchDirection);
			//Sometimes I want to have small branches on the end of the branch
			if (random.nextBoolean()) {
				//Place a long branch and save it's end location
				BlockPos end = placeBranch(treeFeatureConfig, random, set, world, currentPosition.mutableCopy(), secondaryBranchDirection, 3 + random.nextInt(1), blockBox);
				//Place 2 small branches going in the same general direction as the main branch
				foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(treeFeatureConfig, random, set, world, end.mutableCopy(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, secondaryBranchDirection.getOpposite()), 1 + random.nextInt(1), blockBox), 1, false));
				foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(treeFeatureConfig, random, set, world, end.mutableCopy(), DirectionHelper.randomHorizontalDirectionAwayFrom(random, secondaryBranchDirection.getOpposite()), 2 + random.nextInt(1), blockBox), 1, false));
			} else {
				foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(treeFeatureConfig, random, set, world, currentPosition.mutableCopy(), secondaryBranchDirection, 3 + random.nextInt(1), blockBox), 1, false));
			}
		}

		//Place the remainder of the tree
		for (int rest = 0; rest < restHeight; rest++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);
		}

		foliageNodes.add(new FoliagePlacer.TreeNode(currentPosition, 1, false));

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(TreeFeatureConfig config, Random random, Set<BlockPos> set, ModifiableTestableWorld world, BlockPos.Mutable origin, Direction direction, int length, BlockBox blockBox) {
		//Place the supporting branch in the correct direction
		setBlockStateAndUpdate(config, random, set, world, origin.move(direction), direction, blockBox);
		//Place the rest of the branch upwards
		for (int position = 0; position < length; position++) {
			setBlockStateAndUpdate(config, random, set, world, origin.move(Direction.UP), Direction.UP, blockBox);
		}
		return origin;
	}
}
