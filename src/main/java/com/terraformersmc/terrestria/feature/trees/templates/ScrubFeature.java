package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.LeafPileBlock;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ScrubFeature extends AbstractTreeFeature<TreeFeatureConfig> {

	public ScrubFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> function, boolean notify) {
		super(function);
	}

	public ScrubFeature sapling() {
		return new ScrubFeature(TreeFeatureConfig::deserialize, true);
	}

	@Override
	protected boolean generate(ModifiableTestableWorld world, Random rand, BlockPos pos, Set<BlockPos> logs, Set<BlockPos> leaves, BlockBox box, TreeFeatureConfig config) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		int height = rand.nextInt(2) + 1;

		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			setLogBlockState(world, rand, pos, logs, box, config);

			// leaves
			BlockPos leavesOrigin = new BlockPos(pos.getX(), pos.getY() + height - 1, pos.getZ());
			BlockPos[] leavesCandidates = new BlockPos[] {leavesOrigin.north(), leavesOrigin.south(), leavesOrigin.east(), leavesOrigin.west()};

			for (BlockPos candidate : leavesCandidates) {
				if (isAirOrLeaves(world, candidate) || world.testBlockState(candidate, state -> state.getBlock() instanceof LeafPileBlock)) {
					setLeavesBlockState(world, rand, candidate, leaves, box, config);
				}
			}

			// log and top leaf
			for (int y = pos.getY(); y <= pos.getY() + height; ++y) {

				BlockPos candidate = new BlockPos(pos.getX(), y, pos.getZ());

				if (isAirOrLeaves(world, candidate) || world.testBlockState(candidate, state -> state.getBlock() instanceof LeafPileBlock)) {
					if (y == (pos.getY() + height)) {
						setLeavesBlockState(world, rand, candidate, leaves, box, config);
					} else {
						setLogBlockState(world, rand, candidate, logs, box, config);
					}
				}
			}
		}

		return true;
	}
}
