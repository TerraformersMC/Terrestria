package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SmallCanopyTree4BranchTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SmallCanopyTree4BranchTrunkPlacer> CODEC = RecordCodecBuilder.create(smallCanopyTree4BranchTrunkPlacerInstance ->
			method_28904(smallCanopyTree4BranchTrunkPlacerInstance).apply(smallCanopyTree4BranchTrunkPlacerInstance, SmallCanopyTree4BranchTrunkPlacer::new));

	public SmallCanopyTree4BranchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SMALL_CANOPY_4_BRANCHES;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = pos.mutableCopy().move(Direction.DOWN);

		//Determine the radius
		int radius = (int)((trunkHeight / 2) + 0.5);

		//Place the trunk
		for (int height = 0; height < trunkHeight; height++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);
		}

		//save the current position as the leaf origin
		BlockPos origin = currentPosition.toImmutable();

		//Place the branches
		Direction.Type.HORIZONTAL.forEach((direction) -> placeBranch(treeFeatureConfig, random, set, world, origin, direction, radius + 1, blockBox));

		//Place the rest of the trunk
		for (int height = 0; height < trunkHeight; height++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);
		}

		//Return the leaf origin
		return ImmutableList.of(new FoliagePlacer.TreeNode(origin, radius, false));
	}

	private void placeBranch(TreeFeatureConfig config, Random random, Set<BlockPos> set, ModifiableTestableWorld world, BlockPos origin, Direction direction, int length, BlockBox blockBox) {
		for (int position = 0; position < length; position++) {
			setBlockStateAndUpdate(config, random, set, world, origin.offset(direction, position + 1), direction, blockBox);
		}
	}
}
