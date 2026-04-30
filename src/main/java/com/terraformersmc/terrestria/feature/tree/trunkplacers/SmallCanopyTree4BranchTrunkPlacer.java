package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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
public class SmallCanopyTree4BranchTrunkPlacer extends SmallTrunkPlacer {
	public static final MapCodec<SmallCanopyTree4BranchTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(smallCanopyTree4BranchTrunkPlacerInstance ->
			trunkPlacerParts(smallCanopyTree4BranchTrunkPlacerInstance).apply(smallCanopyTree4BranchTrunkPlacerInstance, SmallCanopyTree4BranchTrunkPlacer::new));

	public SmallCanopyTree4BranchTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.SMALL_CANOPY_4_BRANCHES;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int trunkHeight, BlockPos pos, TreeConfiguration treeFeatureConfig) {

		// Create the Mutable version of our block position so that we can procedurally create the trunk
		BlockPos.MutableBlockPos currentPosition = pos.mutable().move(Direction.DOWN);

		// Determine the radius
		int radius = (int)((trunkHeight / 2f) + 0.5f);

		// Place the trunk
		for (int height = 0; height < trunkHeight; height++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		// Save the current position as the leaf origin
		BlockPos origin = currentPosition.immutable();

		// Place the branches
		Direction.Plane.HORIZONTAL.forEach((direction) -> placeBranch(treeFeatureConfig, random, replacer, world, origin, direction, radius + 1));

		// Place the rest of the trunk
		for (int height = 0; height < trunkHeight; height++) {
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);
		}

		// Return the leaf origin
		return ImmutableList.of(new FoliagePlacer.FoliageAttachment(origin, radius, false));
	}

	private void placeBranch(TreeConfiguration config, RandomSource random, BiConsumer<BlockPos, BlockState> replacer, WorldGenLevel world, BlockPos origin, Direction direction, int length) {
		for (int position = 0; position < length; position++) {
			setBlockStateAndUpdate(config, random, replacer, world, origin.relative(direction, position + 1), direction);
		}
	}
}
