package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SpindlyTrunkPlacer extends SmallTrunkPlacer {
	public static final MapCodec<SpindlyTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(spindlyTrunkPlacerInstance ->
			trunkPlacerParts(spindlyTrunkPlacerInstance).apply(spindlyTrunkPlacerInstance, SpindlyTrunkPlacer::new));

	public SpindlyTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> type() {
		return TerrestriaTrunkPlacerTypes.SPINDLY;
	}

	@Override
	public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> replacer, RandomSource random, int height_1, BlockPos origin, TreeConfiguration treeFeatureConfig) {

		//Pick a direction for the tree to lean
		Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

		//Allocate a for the random direction
		Direction randDir;

		//Create the placer storage
		ArrayList<FoliagePlacer.FoliageAttachment> foliageNodes = new ArrayList<>();

		//Determine the tree height
		int height = random.nextInt(3) + 7;

		//Create a mutable version of the origin for procedural tree placement
		BlockPos.MutableBlockPos currentPosition = origin.mutable().move(Direction.DOWN);

		for (int i = 1; i <= height; i++) {

			//Place a block one block up
			setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(Direction.UP), Direction.UP);

			// Randomly change direction and place a block (sometimes)
			if (random.nextInt(4) == 0) {
				randDir = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());
				setBlockStateAndUpdate(treeFeatureConfig, random, replacer, world, currentPosition.move(randDir), randDir);
			}

			// Randomly generate a branch if the height is greater than half and 66% of the time assign a leaf location to the end
			if (i > (height / 2) && random.nextBoolean()) {
				BlockPos branchEnd = placeBranch(world, random, currentPosition.immutable(), replacer, treeFeatureConfig, direction, 2 + random.nextInt(3));

				if (random.nextInt(3) != 0) {
					foliageNodes.add(new FoliagePlacer.FoliageAttachment(branchEnd, 1, false));
				}
			}

			Direction originalDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
			// Randomly generate up to two roots if the height is less than 4 blocks above the origin
			if (i < 2 && random.nextInt(3) < 3) {
				placeRoot(treeFeatureConfig, random, replacer, world, currentPosition.immutable(), random.nextInt(5), originalDirection);
			}
			if (i < 5 && i > 2 && random.nextInt(3) < 3) {
				placeRoot(treeFeatureConfig, random, replacer, world, currentPosition.immutable(), random.nextInt(5), originalDirection.getOpposite());
			}
		}

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	public BlockPos placeBranch(LevelSimulatedReader world, RandomSource random, BlockPos origin, BiConsumer<BlockPos, BlockState> replacer, TreeConfiguration config, Direction direction, int length) {
		Direction offset;
		BlockPos.MutableBlockPos pos = origin.mutable();
		for (int i = 0; i < length; i++) {
			offset = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());
			pos.move(offset);
			if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir)) {
				setBlockStateAndUpdate(config, random, replacer, world, pos, offset);
				if (random.nextBoolean()) {
					pos.move(Direction.UP);
					if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir)) {
						setBlockStateAndUpdate(config, random, replacer, world, pos, Direction.UP);
					} else {
						pos.move(Direction.DOWN);
					}
				}
			} else {
				break;
			}
		}
		return pos.immutable();
	}

	public void placeRoot(TreeConfiguration config, RandomSource random, BiConsumer<BlockPos, BlockState> replacer, LevelSimulatedReader world, BlockPos origin, int rootLength, Direction originalDirection) {
		BlockPos.MutableBlockPos pos = origin.mutable();
		Direction direction;
		for (int i = 0; i < rootLength; i++) {
			// Place block and block down and to the side
			direction = DirectionHelper.randomHorizontalDirectionAwayFrom(random, originalDirection.getOpposite());
			pos.move(direction);
			if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir)) {
				setBlockStateAndUpdate(config, random, replacer, world, pos, direction);
				if (random.nextBoolean()) {
					pos.move(Direction.DOWN);
					setBlockStateAndUpdate(config, random, replacer, world, pos, Direction.DOWN);
				}
			} else {
				break;
			}
		}

		for (int j = 0; j < 3; j++) {
			pos.move(Direction.DOWN);
			if (world.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir)) {
				// Place a single block of the root
				setBlockStateAndUpdate(config, random, replacer, world, pos, Direction.DOWN);
			} else {
				// Connect the root to the ground
				pos.move(Direction.UP);
				addSmallLogConnection(config, random, replacer, world, pos, Direction.DOWN);
				break;
			}
		}
	}
}
