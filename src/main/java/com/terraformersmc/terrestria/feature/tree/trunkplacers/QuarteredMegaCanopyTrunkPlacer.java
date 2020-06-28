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

public class QuarteredMegaCanopyTrunkPlacer extends TrunkPlacer {

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

		//Check and set the block below to dirt
		method_27400(world, pos.down());
		method_27400(world, pos.down().east());
		method_27400(world, pos.down().south());
		method_27400(world, pos.down().south().east());

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Create the placer storage
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		//Place the trunk without branches
		for (int i = 0; i < trunkHeight; i++) {
			placeLayer(world, random, currentPosition.move(Direction.UP).toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig), false);
		}

		//Determine the number of branched layers to have
		int layers = 5 + random.nextInt(3);

		//Place the branched pieces
		for (int i = 0; i < layers; i++) {
			//Place the layers with branches and add the end of the branch to the foliage locations
			foliageNodes.add(new FoliagePlacer.TreeNode(placeLayer(world, random, currentPosition.move(Direction.UP).toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig), true), random.nextInt(2) + 4, true));
		}

		//Make sure the top of the tree has leaf locations
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig), Direction.NORTH), random.nextInt(2) + 4, true));
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig), Direction.SOUTH), random.nextInt(2) + 4, true));
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig), Direction.EAST), random.nextInt(2) + 4, true));
		foliageNodes.add(new FoliagePlacer.TreeNode(placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig), Direction.WEST), random.nextInt(2) + 4, true));

		//Generate the roots
		growRoots(set, world, pos.mutableCopy(), random, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig));

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private BlockPos placeLayer(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, QuarteredMegaTreeConfig treeFeatureConfig, boolean placeBranches) {
		checkAndPlaceSpecificBlockState(world, random, pos.south(), set, blockBox, TerrestriaConfigManager.getGeneralConfig().areQuarterLogsEnabled() ? treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHWEST) : treeFeatureConfig.logBlock);
		checkAndPlaceSpecificBlockState(world, random, pos.east(), set, blockBox, TerrestriaConfigManager.getGeneralConfig().areQuarterLogsEnabled() ? treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHEAST): treeFeatureConfig.logBlock);
		checkAndPlaceSpecificBlockState(world, random, pos.south().east(), set, blockBox, TerrestriaConfigManager.getGeneralConfig().areQuarterLogsEnabled() ? treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHEAST): treeFeatureConfig.logBlock);
		checkAndPlaceSpecificBlockState(world, random, pos, set, blockBox, TerrestriaConfigManager.getGeneralConfig().areQuarterLogsEnabled() ? treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHWEST): treeFeatureConfig.logBlock);

		//Place a branch if we need one
		if (placeBranches) {
			//Determine the branch direction
			Direction direction = Direction.Type.HORIZONTAL.random(random);
			return placeBranch(world, random, pos, set, blockBox, treeFeatureConfig, direction);
		} else {
			//This should never get used but we have to return something
			return pos;
		}
	}

	private BlockPos placeBranch(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, QuarteredMegaTreeConfig treeFeatureConfig, Direction direction) {
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

	public void growRoots(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable pos, Random random, BlockBox box, QuarteredMegaTreeConfig treeFeatureConfig) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		tryGrowRoot(logs, world, pos.set(x - 1, y, z + random.nextInt(2)), random, box, treeFeatureConfig);
		tryGrowRoot(logs, world, pos.set(x + 2, y, z + random.nextInt(2)), random, box, treeFeatureConfig);
		tryGrowRoot(logs, world, pos.set(x + random.nextInt(2), y, z - 1), random, box, treeFeatureConfig);
		tryGrowRoot(logs, world, pos.set(x + random.nextInt(2), y, z + 2), random, box, treeFeatureConfig);
	}

	public void tryGrowRoot(Set<BlockPos> logs, ModifiableTestableWorld world, BlockPos.Mutable bottom, Random random, BlockBox box, QuarteredMegaTreeConfig treeFeatureConfig) {
		//Determine the root length
		if (random.nextInt(5) == 0) {
			return;
		}

		//Determine how high up on the tree it should be placed
		int height = random.nextInt(4) + 1;

		//Place the root
		for (int i = 0; i < height; i++) {
			if (TreeFeature.canTreeReplace(world, bottom) || TreeFeature.canReplace(world, bottom) || world.testBlockState(bottom, state -> state.getBlock() instanceof TallSeagrassBlock)) {
				checkAndPlaceSpecificBlockState(world, random, bottom, logs, box, treeFeatureConfig.woodBlock);
			}

			bottom.move(Direction.UP);
		}
	}

	private static void checkAndPlaceSpecificBlockState(ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos, Set<BlockPos> set, BlockBox blockBox, BlockState blockState) {
		if (TreeFeature.canReplace(modifiableTestableWorld, blockPos)) {
			method_27404(modifiableTestableWorld, blockPos, blockState, blockBox);
			set.add(blockPos.toImmutable());
		}
	}
}
