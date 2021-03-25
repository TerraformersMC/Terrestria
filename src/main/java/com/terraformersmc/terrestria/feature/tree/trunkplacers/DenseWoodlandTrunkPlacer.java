package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class DenseWoodlandTrunkPlacer extends TrunkPlacer {
	public static final Codec<DenseWoodlandTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
			method_28904(instance).apply(instance, DenseWoodlandTrunkPlacer::new));

	public DenseWoodlandTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.DENSE_WOODLAND;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig config) {
		method_27400(world, pos.down());

		// Setup values for generator.
		// A straight trunk is placed for 75% of the trunk height, then another 33% is used for the branching top.
		int baseHeight = (trunkHeight / 4) * 3;
		int branchedHeight = trunkHeight / 3;

		List<FoliagePlacer.TreeNode> nodes = new ArrayList<>();

		// Place base trunk
		for(int y = 0; y < baseHeight; ++y) {
			method_27402(world, random, pos.up(y), set, blockBox, config);

			// Try to make a "trunk branch"
			if (y > 2 && random.nextInt(3) == 0) {
				// Use a random theta
				double theta = Math.PI * random.nextDouble() * 2;

				// The branch length here is divided by 2, so it can be much larger.
				// That's done to make these branches less tall.
				int branchLength = 4 + random.nextInt(5);
				for (int y1 = 0; y1 < branchLength; y1++) {
					int x = (int) (Math.sin(theta) * y1);
					int z = (int) (Math.cos(theta) * y1);

					method_27402(world, random, pos.add(x, y + (y1 / 2), z), set, blockBox, config);

					// If we're on the last node, then mark for foliage
					if (y1 == branchLength - 1) {
						nodes.add(new FoliagePlacer.TreeNode(pos.add(x, y + (y1 / 2), z), 0, false));
					}
				}
			}
		}

		// Setup values for the branch generator
		// We can have 2-4 branches. It's set as a double here to prevent inorganic looking shapes
		double branchCount = 2 + random.nextDouble() * 3;
		double theta = (Math.PI * 2) / branchCount;

		// Offset value to make the trig more random
		double offset = random.nextDouble() + random.nextDouble();

		// Generate top branches
		for (int i = 0; i < (int) branchCount; i++) {
			// Create a branch by using trig to find the x and z, and iterating upwards.
			int branchLength = Math.max(branchedHeight, 2) + random.nextInt(4);
			for (int y = 0; y < branchLength; y++) {
				int x = (int) (Math.sin(theta * i + offset) * y);
				int z = (int) (Math.cos(theta * i + offset) * y);

				method_27402(world, random, pos.add(x, baseHeight + y, z), set, blockBox, config);

				// If we're on the last segment, place a leaf node
				if (y == branchLength - 1) {
					nodes.add(new FoliagePlacer.TreeNode(pos.add(x, baseHeight + y, z), 0, false));
				}
			}
		}


		// Place roots
		for (Direction direction : Direction.Type.HORIZONTAL) {
			// Roots can be 0 to 2 blocks tall
			int height = random.nextInt(3);
			if (height == 0) {
				continue;
			}

			// Set the ground below to dirt
			method_27400(world, pos.offset(direction).down());

			for (int y = 0; y < height; y++) {

				//TODO: cleanup, use the config here

				// If we're on the last segment, then use the wood instead of the log to make it look better.
				BlockState state = (y == height - 1) ? Blocks.OAK_WOOD.getDefaultState() : config.trunkProvider.getBlockState(random, pos);
				method_27404(world, pos.offset(direction).up(y), state, blockBox);
			}

		}

		// Randomly add a leaf cluster to the center where the branches split half of the time
		if (random.nextBoolean()) {
			nodes.add(new FoliagePlacer.TreeNode(pos.up(baseHeight + 1), 0, false));
		}

		return nodes;
	}
}
