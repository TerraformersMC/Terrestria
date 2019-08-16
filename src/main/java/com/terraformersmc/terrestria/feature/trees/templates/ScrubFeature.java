package com.terraformersmc.terrestria.feature.trees.templates;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terraform.block.LeafPileBlock;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class ScrubFeature extends AbstractTreeFeature<DefaultFeatureConfig> {
	private TreeDefinition.Basic tree;

	public ScrubFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function, boolean notify, TreeDefinition.Basic tree) {
		super(function, notify);
		this.tree = tree;
	}

	public ScrubFeature sapling() {
		return new ScrubFeature(DefaultFeatureConfig::deserialize, true, tree);
	}

	public boolean generate(Set<BlockPos> blocks, ModifiableTestableWorld world, Random rand, BlockPos pos, MutableIntBoundingBox boundingBox) {
		pos = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).down();
		int height = rand.nextInt(2) + 1;
		
		if (isNaturalDirtOrGrass(world, pos)) {
			pos = pos.up();
			this.setBlockState(blocks, world, pos, this.tree.getLog(), boundingBox);
			
			// leaves
			BlockPos leavesOrigin = new BlockPos(pos.getX(), pos.getY() + height - 1, pos.getZ());
			BlockPos[] leavesCandidates = new BlockPos[] {leavesOrigin.north(), leavesOrigin.south(), leavesOrigin.east(), leavesOrigin.west()};
			
			for (BlockPos candidate : leavesCandidates) {
				if (isAirOrLeaves(world, candidate) || world.testBlockState(candidate, state -> state.getBlock() instanceof LeafPileBlock)) {
					this.setBlockState(blocks, world, candidate, this.tree.getLeaves(), boundingBox);
				}
			}
			
			// log and top leaf
			for (int y = pos.getY(); y <= pos.getY() + height; ++y) {
				
				BlockPos candidate = new BlockPos(pos.getX(), y, pos.getZ());
				
				if (isAirOrLeaves(world, candidate) || world.testBlockState(candidate, state -> state.getBlock() instanceof LeafPileBlock)) {
					this.setBlockState(blocks, world, candidate, y == (pos.getY() + height) ? this.tree.getLeaves() : this.tree.getLog(), boundingBox);
				}
			}
		}

		return true;
	}
}