package com.terraformersmc.terrestria.feature.placer.trunk;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.block.QuarterLogBlock;
import com.terraformersmc.terrestria.feature.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.block.BlockState;
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

public class QuarteredMegaIncrementedStraightTrunkPlacer extends TrunkPlacer {

	public static final Codec<QuarteredMegaIncrementedStraightTrunkPlacer> CODEC = RecordCodecBuilder.create(quarteredMegaTreeTrunkPlacerInstance ->
			method_28904(quarteredMegaTreeTrunkPlacerInstance).apply(quarteredMegaTreeTrunkPlacerInstance, QuarteredMegaIncrementedStraightTrunkPlacer::new));

	public QuarteredMegaIncrementedStraightTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.QUARTERED_MEGA_TREE;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
		//Determine the tree type
		//Terrestria Conifers can either be straight up or the layers can get smaller as it reaches the top
		boolean converge = random.nextBoolean();

		//Set the radius
		//If the tree converges give it a larger base radius but vary both
		int radius = (converge ? random.nextInt(4) : random.nextInt(2) + 1) + 3;

		//Determine the number of layers to place
		//This varies between the two types of conifer here
		int layers = converge ? (radius - 2) : (6 + random.nextInt(4));

		//Check and set the block below to dirt
		method_27400(world, pos.down());
		method_27400(world, pos.down().east());
		method_27400(world, pos.down().south());
		method_27400(world, pos.down().south().east());

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Create the placer storage
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		//Place the trunk
		for (int i = 0; i < trunkHeight; i++) {
			placeLayer(world, random, currentPosition.move(Direction.UP).toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig));
		}

		//Place the layers
		for (int i = 0; i < layers; i++) {
			//Place the layers
			for (int j = 0; j < (converge ? (radius - i) : 3); j++) {
				placeLayer(world, random, currentPosition.move(Direction.UP).toImmutable(), set, blockBox, ((QuarteredMegaTreeConfig)treeFeatureConfig));
			}
			//Add locations for leaves to be placed at the top of the layer
			//The radius is determined by weather it converges or not, if it does subtract one from it every layer
			foliageNodes.add(new FoliagePlacer.TreeNode(currentPosition.toImmutable(), (converge ? (radius - i) : radius), true));
		}

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	private void placeLayer(ModifiableTestableWorld world, Random random, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, QuarteredMegaTreeConfig treeFeatureConfig) {
		checkAndPlaceSpecificBlockState(world, random, pos.south(), 		set, blockBox, treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHWEST));
		checkAndPlaceSpecificBlockState(world, random, pos.east(), 			set, blockBox, treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHEAST));
		checkAndPlaceSpecificBlockState(world, random, pos.south().east(), 	set, blockBox, treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.SOUTHEAST));
		checkAndPlaceSpecificBlockState(world, random, pos, 				set, blockBox, treeFeatureConfig.quarterLogBlock.with(QuarterLogBlock.BARK_SIDE, QuarterLogBlock.BarkSide.NORTHWEST));
	}

	private static void checkAndPlaceSpecificBlockState(ModifiableTestableWorld modifiableTestableWorld, Random random, BlockPos blockPos, Set<BlockPos> set, BlockBox blockBox, BlockState blockState) {
		if (TreeFeature.canReplace(modifiableTestableWorld, blockPos)) {
			method_27404(modifiableTestableWorld, blockPos, blockState, blockBox);
			set.add(blockPos.toImmutable());
		}
	}
}
