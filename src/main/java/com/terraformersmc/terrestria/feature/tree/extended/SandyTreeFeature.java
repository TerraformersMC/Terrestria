package com.terraformersmc.terrestria.feature.tree.extended;

import com.mojang.serialization.Codec;

import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SandyTreeFeature extends TreeFeature implements ExtendedTreeGeneration {
	public SandyTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean canGenerateOn(TestableWorld world, BlockPos pos) {
		return (world.testBlockState(pos, (state) -> BlockTags.SAND.contains(state.getBlock()))) || Feature.isSoil(world, pos);
	}
}
