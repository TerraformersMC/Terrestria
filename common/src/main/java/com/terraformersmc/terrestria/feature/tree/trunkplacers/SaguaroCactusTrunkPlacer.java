package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class SaguaroCactusTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SaguaroCactusTrunkPlacer> CODEC = RecordCodecBuilder.create(saguaroCactusTrunkPlacerInstance ->
			fillTrunkPlacerFields(saguaroCactusTrunkPlacerInstance).apply(saguaroCactusTrunkPlacerInstance, SaguaroCactusTrunkPlacer::new));

	public SaguaroCactusTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SAGUARO_CACTUS;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos position, TreeFeatureConfig treeFeatureConfig) {

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.Mutable currentPosition = position.mutableCopy();

		// Determine a direction for the main arm
		Direction armDir = Direction.Type.HORIZONTAL.random(random);

		// Determine a height
		height = random.nextInt(1) + 5;

		// Place the first 2 blocks of the cactus
		placeSpecificBlockState(world, replacer, currentPosition, treeFeatureConfig.trunkProvider.get(random, currentPosition).with(BareSmallLogBlock.DOWN, true));
		setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);

		// Place one branch always
		placeBranch(world, random, currentPosition.mutableCopy(), replacer, treeFeatureConfig, armDir, random.nextInt(1) + 1);
		// 50% of the time place another one one block higher
		if (random.nextBoolean()) {
			placeBranch(world, random, currentPosition.mutableCopy(), replacer, treeFeatureConfig, DirectionHelper.randomHorizontalDirectionAwayFrom(random, armDir), random.nextInt(1) + 2);
			height--;
		}

		// Place the rest of the cactus
		for (int i = 0; i < height - 3; i++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		return ImmutableList.of();
	}

	public void placeBranch(TestableWorld world, Random rand, BlockPos.Mutable pos, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config, Direction direction, int length) {
		setBlockStateAndUpdate(config, rand, replacer, world, pos.move(direction), direction);
		for (int i = 0; i < length; i++) {
			setBlockStateAndUpdate(config, rand, replacer, world, pos.move(Direction.UP), Direction.UP);
		}
	}
}
