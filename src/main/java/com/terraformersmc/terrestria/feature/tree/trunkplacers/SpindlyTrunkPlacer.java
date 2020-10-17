package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.util.DirectionHelper;
import com.terraformersmc.terrestria.feature.tree.trunkplacers.templates.SmallTrunkPlacer;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static net.minecraft.world.gen.feature.Feature.isAir;

public class SpindlyTrunkPlacer extends SmallTrunkPlacer {

	public static final Codec<SpindlyTrunkPlacer> CODEC = RecordCodecBuilder.create(spindlyTrunkPlacerInstance ->
			method_28904(spindlyTrunkPlacerInstance).apply(spindlyTrunkPlacerInstance, SpindlyTrunkPlacer::new));

	public SpindlyTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.SPINDLY;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos origin, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {

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
			setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(Direction.UP), Direction.UP, blockBox);

			// Randomly change direction and place a block (sometimes)
			if (random.nextInt(4) == 0) {
				randDir = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());
				setBlockStateAndUpdate(treeFeatureConfig, random, set, world, currentPosition.move(randDir), randDir, blockBox);
			}

			// Randomly generate a branch if the height is greater than half and 66% of the time assign a leaf location to the end
			if (i > (height / 2) && random.nextBoolean()) {
				BlockPos branchEnd = placeBranch(world, random, currentPosition.toImmutable(), set, blockBox, treeFeatureConfig, direction, 2 + random.nextInt(3));

				if (random.nextInt(3) != 0) {
					foliageNodes.add(new FoliagePlacer.TreeNode(branchEnd, 1, false));
				}
			}

			Direction originalDirection = Direction.Type.HORIZONTAL.random(random);
			// Randomly generate up to two roots if the height is less than 4 blocks above the origin
			if (i < 2 && random.nextInt(3) < 3) {
				placeRoot(treeFeatureConfig, random, set, world, currentPosition.toImmutable(), random.nextInt(5), blockBox, originalDirection);
			}
			if (i < 5 && i > 2 && random.nextInt(3) < 3) {
				placeRoot(treeFeatureConfig, random, set, world, currentPosition.toImmutable(), random.nextInt(5), blockBox, originalDirection.getOpposite());
			}
		}

		//Return the nodes as an Immutable List to be placed later
		return ImmutableList.copyOf(foliageNodes);
	}

	public BlockPos placeBranch(ModifiableTestableWorld world, Random random, BlockPos origin, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig config, Direction direction, int length) {
		Direction offset;
		BlockPos.Mutable pos = origin.mutableCopy();
		for (int i = 0; i < length; i++) {
			offset = DirectionHelper.randomHorizontalDirectionAwayFrom(random, direction.getOpposite());
			pos.move(offset);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(config, random, set, world, pos, offset, blockBox);
				if (random.nextBoolean()) {
					pos.move(Direction.UP);
					if (isAir(world, pos)) {
						setBlockStateAndUpdate(config, random, set, world, pos, Direction.UP, blockBox);
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

	public void placeRoot(TreeFeatureConfig config, Random random, Set<BlockPos> set, ModifiableTestableWorld world, BlockPos origin, int rootLength, BlockBox blockBox, Direction originalDirection) {
		BlockPos.Mutable pos = origin.mutableCopy();
		Direction direction;
		for (int i = 0; i < rootLength; i++) {
			// Place block and block down and to the side
			direction = DirectionHelper.randomHorizontalDirectionAwayFrom(random, originalDirection.getOpposite());
			pos.move(direction);
			if (isAir(world, pos)) {
				setBlockStateAndUpdate(config, random, set, world, pos, direction, blockBox);
				if (random.nextBoolean()) {
					pos.move(Direction.DOWN);
					setBlockStateAndUpdate(config, random, set, world, pos, Direction.DOWN, blockBox);
				}
			} else {
				break;
			}
		}

		for (int j = 0; j < 3; j++) {
			pos.move(Direction.DOWN);
			if (isAir(world, pos)) {
				// Place a single block of the root
				setBlockStateAndUpdate(config, random, set, world, pos, Direction.DOWN, blockBox);
			} else {
				// Connect the root to the ground
				pos.move(Direction.UP);
				addSmallLogConnection(config, random, set, world, pos, Direction.DOWN, blockBox);
				break;
			}
		}
	}
}
