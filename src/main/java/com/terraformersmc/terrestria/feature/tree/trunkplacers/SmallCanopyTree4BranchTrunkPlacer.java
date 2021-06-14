package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class SmallCanopyTree4BranchTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SmallCanopyTree4BranchTrunkPlacer> CODEC = RecordCodecBuilder.create(smallCanopyTree4BranchTrunkPlacerInstance ->
			fillTrunkPlacerFields(smallCanopyTree4BranchTrunkPlacerInstance).apply(smallCanopyTree4BranchTrunkPlacerInstance, SmallCanopyTree4BranchTrunkPlacer::new));

	public SmallCanopyTree4BranchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SMALL_CANOPY_4_BRANCHES;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int trunkHeight, BlockPos pos, TreeFeatureConfig treeFeatureConfig) {

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Determine the radius
		int radius = (int)((trunkHeight / 2) + 0.5);

		//Place the trunk
		for (int height = 0; height < trunkHeight; height++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		//save the current position as the leaf origin
		BlockPos origin = currentPosition.toImmutable();

		//Place the branches
		Direction.Type.HORIZONTAL.forEach((direction) -> placeBranch(treeFeatureConfig, random, replacer, world, origin, direction, radius + 1));

		//Place the rest of the trunk
		for (int height = 0; height < trunkHeight; height++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		//Return the leaf origin
		return ImmutableList.of(new FoliagePlacer.TreeNode(origin, radius, false));
	}

	private void placeBranch(TreeFeatureConfig config, Random random, BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos origin, Direction direction, int length) {
		for (int position = 0; position < length; position++) {
			setBlockStateAndUpdate(config, random, replacer, world, origin.offset(direction, position + 1), direction);
		}
	}
}
