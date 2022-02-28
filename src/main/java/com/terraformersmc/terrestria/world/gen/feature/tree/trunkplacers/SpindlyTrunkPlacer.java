package com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import com.terraformersmc.terrestria.world.gen.feature.tree.trunkplacers.templates.SmallTrunkPlacer;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import static net.minecraft.world.gen.feature.Feature.isAir;

public class SpindlyTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SpindlyTrunkPlacer> CODEC = RecordCodecBuilder.create(spindlyTrunkPlacerInstance ->
			fillTrunkPlacerFields(spindlyTrunkPlacerInstance).apply(spindlyTrunkPlacerInstance, SpindlyTrunkPlacer::new));

	public SpindlyTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SPINDLY;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height_1, BlockPos origin, TreeFeatureConfig treeFeatureConfig) {

		//Pick a direction for the tree to lean
		Direction direction = Direction.Type.HORIZONTAL.random(random);

		//Allocate a for the random direction
		Direction randDir;

		//Create the placer storage
		ArrayList<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

		//Determine the tree height
		int height = random.nextInt(3) + 7;

		//Create a mutable version of the origin for procedural tree placement
		BlockPos.Mutable currentPosition = origin.mutableCopy().move(Direction.DOWN);

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
				BlockPos branchEnd = placeBranch(world, random, currentPosition.toImmutable(), replacer, treeFeatureConfig, direction, 2 + random.nextInt(3));

				if (random.nextInt(3) != 0) {
					foliageNodes.add(new FoliagePlacer.TreeNode(branchEnd, 1, false));
				}
			}

			Direction originalDirection = Direction.Type.HORIZONTAL.random(random);
			// Randomly generate up to two roots if the height is less than 4 blocks above the origin
			if (i < 2 && random.nextInt(3) < 3) {
				placeRoot(treeFeatureConfig, random, replacer, world, currentPosition.toImmutable(), random.nextInt(5), originalDirection);
			}
			if (i < 5 && i > 2 && random.nextInt(3) < 3) {
				placeRoot(treeFeatureConfig, random, replacer, world, currentPosition.toImmutable(), random.nextInt(5), originalDirection.getOpposite());
			}
		}

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	public BlockPos placeBranch(TestableWorld world, Random random, BlockPos origin, BiConsumer<BlockPos, BlockState> replacer, TreeFeatureConfig config, Direction direction, int length) {
		Direction offset;
		BlockPos.Mutable pos = origin.mutableCopy();
		for (int i = 0; i < length; i++) {
			offset = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());
			pos.move(offset);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(config, random, replacer, world, pos, offset);
				if (random.nextBoolean()) {
					pos.move(Direction.UP);
					if (isAir(world, pos)) {
						setBlockStateAndUpdate(config, random, replacer, world, pos, Direction.UP);
					} else {
						pos.move(Direction.DOWN);
					}
				}
			} else {
				break;
			}
		}
		return pos.toImmutable();
	}

	public void placeRoot(TreeFeatureConfig config, Random random, BiConsumer<BlockPos, BlockState> replacer, TestableWorld world, BlockPos origin, int rootLength, Direction originalDirection) {
		BlockPos.Mutable pos = origin.mutableCopy();
		Direction direction;
		for (int i = 0; i < rootLength; i++) {
			// Place block and block down and to the side
			direction = DirectionHelper.randomHorizontalDirectionAwayFrom(random, originalDirection.getOpposite());
			pos.move(direction);
			if (isAir(world, pos)) {
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
			if (isAir(world, pos)) {
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
