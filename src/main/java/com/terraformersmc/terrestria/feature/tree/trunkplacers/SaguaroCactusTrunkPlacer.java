package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
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

public class SaguaroCactusTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SaguaroCactusTrunkPlacer> CODEC = RecordCodecBuilder.create(saguaroCactusTrunkPlacerInstance ->
			method_28904(saguaroCactusTrunkPlacerInstance).apply(saguaroCactusTrunkPlacerInstance, SaguaroCactusTrunkPlacer::new));

	public SaguaroCactusTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SAGUARO_CACTUS;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int height, BlockPos position, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

		//Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = position.mutableCopy();

		//Determine a direction for the main arm
		Direction armDir = Direction.Type.HORIZONTAL.random(random);

		//Determine a height
		height = random.nextInt(1) + 5;

		//Place the first 2 blocks of the cactus
		placeSpecificBlockState(world, currentPosition, set, blockBox, treeFeatureConfig.trunkProvider.getBlockState(random, currentPosition).with(BareSmallLogBlock.DOWN, true));
		setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);

		//place one branch always
		placeBranch(world, random, currentPosition.mutableCopy(), set, blockBox, treeFeatureConfig, armDir, random.nextInt(1) + 1);
		//50% of the time place another one one block higher
		if (random.nextBoolean()) {
			placeBranch(world, random, currentPosition.mutableCopy(), set, blockBox, treeFeatureConfig, DirectionHelper.randomHorizontalDirectionAwayFrom(random, armDir), random.nextInt(1) + 2);
			height--;
		}

		//Place the rest of the cactus
		for (int i = 0; i < height - 3; i++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);
		}

		return ImmutableList.of();
	}

	public void placeBranch(ModifiableTestableWorld world, Random rand, BlockPos.Mutable pos, Set<BlockPos> blocks, BlockBox box, TreeFeatureConfig config, Direction direction, int length) {
		setBlockStateAndUpdate(config, rand, blocks, world, pos.move(direction), direction, box);
		for (int i = 0; i < length; i++) {
			setBlockStateAndUpdate(config, rand, blocks, world, pos.move(Direction.UP), Direction.UP, box);
		}
	}
}
