package com.terraformersmc.terrestria.feature.tree.trunkplacers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.DenseWoodlandTreeConfig;
import com.terraformersmc.terrestria.init.TerrestriaTrunkPlacerTypes;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class DenseWoodlandTrunkPlacer extends TrunkPlacer {
	public static final Codec<DenseWoodlandTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) ->
			fillTrunkPlacerFields(instance).apply(instance, DenseWoodlandTrunkPlacer::new));

	public DenseWoodlandTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return TerrestriaTrunkPlacerTypes.DENSE_WOODLAND;
	}

	@Override
	public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int trunkHeight, BlockPos pos, TreeFeatureConfig config) {
		// These two aren't really first-class citizens but we do get them via config...
		BlockState limbState = ((DenseWoodlandTreeConfig) config).limbProvider.getBlockState(random, pos);
		BlockState rootState = ((DenseWoodlandTreeConfig) config).rootsProvider.getBlockState(random, pos);

		setToDirt(world, replacer, random, pos.down(), config);

		// Setup values for generator.
		// A straight trunk is placed for 75% of the trunk height, then another 33% is used for the branching top.
		int baseHeight = (trunkHeight / 4) * 3;
		int branchedHeight = trunkHeight / 3;

		List<FoliagePlacer.TreeNode> nodes = new ArrayList<>();

		// Place base trunk
		for(int y = 0; y < baseHeight; ++y) {
			getAndSetState(world, replacer, random, pos.up(y), config);

			// Try to make a "trunk branch"
			if (y > 2 && random.nextInt(3) == 0) {
				// Use a random theta
				double theta = Math.PI * random.nextDouble() * 2;

				// Orient these horizontal limbs to their primary axis
				Direction.Axis axis = (Math.abs(Math.sin(theta)) > Math.abs(Math.cos(theta))) ?
						Direction.Axis.X :
						Direction.Axis.Z;

				// The branch length here is divided by 2, so it can be much larger.
				// That's done to make these branches less tall.
				int branchLength = 4 + random.nextInt(5);
				for (int y1 = 0; y1 < branchLength; y1++) {
					int x = (int) (Math.sin(theta) * y1);
					int z = (int) (Math.cos(theta) * y1);

					getAndSetState(world, replacer, random, pos.add(x, y + (y1 / 2), z), config,
							ignored -> limbState.with(Properties.AXIS, axis));

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

				getAndSetState(world, replacer, random, pos.add(x, baseHeight + y, z), config);

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
			setToDirt(world, replacer, random, pos.offset(direction).down(), config);

			// Generate roots using the configured root material
			for (int y = 0; y < height; y++) {
				replacer.accept(pos.offset(direction).up(y), rootState);
			}
		}

		// Randomly add a leaf cluster to the center where the branches split half of the time
		if (random.nextBoolean()) {
			nodes.add(new FoliagePlacer.TreeNode(pos.up(baseHeight + 1), 0, false));
		}

		return nodes;
	}
}
