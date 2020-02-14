package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ScrubFeature;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.function.Function;

public class OutbackScrubFeature extends ScrubFeature {
	public OutbackScrubFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, boolean notify) {
		super(function, notify);
	}
}
