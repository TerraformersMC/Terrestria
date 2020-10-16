package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.block.QuarterLogBlock;
import com.terraformersmc.terraform.util.DirectionHelper;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuarteredMegaCanopyTrunkPlacer extends MegaTrunkPlacer {

	public static final Codec<QuarteredMegaCanopyTrunkPlacer> CODEC = RecordCodecBuilder.create(quarteredMegaCanopyTrunkPlacerInstance ->
			method_28904(quarteredMegaCanopyTrunkPlacerInstance).apply(quarteredMegaCanopyTrunkPlacerInstance, QuarteredMegaCanopyTrunkPlacer::new));

	public QuarteredMegaCanopyTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.QUARTERED_MEGA_CANOPY;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		// Determine the number of branched branchLayers to have
		int branchLayers = 5 + random.nextInt(3);

		// Generate the trunk from the MegaTrunkPlacer, but ignore its chosen foliage location
		// This also grows the roots
		super.generate(world, random, trunkHeight + branchLayers, pos, set, blockBox, treeFeatureConfig);

		// Create our list of foliage nodes
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.UP, trunkHeight);

		// Place branches on each branch layer
		for (int i = 0; i < branchLayers; i++) {
			// Place a branch in a random direction
			Direction direction = Direction.Type.HORIZONTAL.random(random);

			// Place the branch logs
			BlockPos branch = placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, treeFeatureConfig, direction);

			// Add the end of the branch to the foliage locations
			foliageNodes.add(new FoliagePlacer.TreeNode(branch, random.nextInt(2) + 4, true));

			// Move the position up
			currentPosition.move(Direction.UP);
		}

		//Make sure the top of the tree has leaf locations
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, treeFeatureConfig, Direction.NORTH), random.nextInt(2) + 4, true));
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, treeFeatureConfig, Direction.SOUTH), random.nextInt(2) + 4, true));
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, treeFeatureConfig, Direction.EAST), random.nextInt(2) + 4, true));
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, treeFeatureConfig, Direction.WEST), random.nextInt(2) + 4, true));

		// Return an immutable version of the foliage node list
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeBranch(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig, Direction direction) {
		//Create the Mutable version of our block position so that we can procedurally create the branch
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Pick weather this branch should go diagonal or straight
		Direction diagonalDirection = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());

		//Determine the length of the branch
		int length = 4 + random.nextInt(3);

		//Place a branch with length in the diagonalDirection, with an upwards angle
		for (int i = 0; i < length; i++) {
			if (random.nextBoolean()) {
				method_27402(world, random, currentPosition.move(direction), set, blockBox, treeFeatureConfig);
				method_27402(world, random, currentPosition.move(diagonalDirection), set, blockBox, treeFeatureConfig);
			}
			method_27402(world, random, currentPosition.move(Direction.UP), set, blockBox, treeFeatureConfig);
		}

		//Return the end of the branch as a valid foliage placement location
		return currentPosition.toImmutable();
	}
}
