package com.terraformersmc.terrestria.feature.trees;

import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ScrubFeature;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class OutbackScrubFeature extends ScrubFeature {
	public OutbackScrubFeature(boolean notify) {
		super(DefaultFeatureConfig::deserialize, notify, new TreeDefinition.Basic(Blocks.OAK_LOG.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState()));
	}
}
