package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.BiConsumer;

@NullMarked
public class SaguaroCactusTrunkPlacer extends SmallTrunkPlacer {
	public static final MapCodec<SaguaroCactusTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(saguaroCactusTrunkPlacerInstance ->
			trunkPlacerParts(saguaroCactusTrunkPlacerInstance).apply(saguaroCactusTrunkPlacerInstance, SaguaroCactusTrunkPlacer::new));

	public SaguaroCactusTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.SAGUARO_CACTUS;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height, BlockPos position, TreeConfiguration treeFeatureConfig) {

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.MutableBlockPos currentPosition = position.mutable();

		// Determine a direction for the main arm
		Direction armDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

		// Determine a height
		height = random.nextInt(1) + 5;

		// Place the first 2 blocks of the cactus
		placeSpecificBlockState(level, replacer, currentPosition, treeFeatureConfig.trunkProvider.getState(level, random, currentPosition).setValue(BareSmallLogBlock.DOWN, true));
		setBlockStateAndUpdate(treeFeatureConfig, random, replacer, level, currentPosition.move(Direction.UP), Direction.UP);

		// Place one branch always
		placeBranch(level, random, currentPosition.mutable(), replacer, treeFeatureConfig, armDir, random.nextInt(1) + 1);
		// 50% of the time place another one one block higher
		if (random.nextBoolean()) {
			placeBranch(level, random, currentPosition.mutable(), replacer, treeFeatureConfig, DirectionHelper.randomHorizontalDirectionAwayFrom(random, armDir), random.nextInt(1) + 2);
			height--;
		}

		// Place the rest of the cactus
		for (int i = 0; i < height - 3; i++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, level, currentPosition.move(Direction.UP), Direction.UP);
		}

		return ImmutableList.of();
	}

	public void placeBranch(WorldGenLevel level, RandomSource random, BlockPos.MutableBlockPos pos, BiConsumer<BlockPos, BlockState> replacer, TreeConfiguration config, Direction direction, int length) {
		setBlockStateAndUpdate(config, random, replacer, level, pos.move(direction), direction);
		for (int i = 0; i < length; i++) {
			setBlockStateAndUpdate(config, random, replacer, level, pos.move(Direction.UP), Direction.UP);
		}
	}
}
