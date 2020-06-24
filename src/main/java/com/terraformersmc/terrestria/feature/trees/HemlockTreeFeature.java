package com.terraformersmc.terrestria.feature.trees;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.function.Function;

public class HemlockTreeFeature extends ConiferTreeFeature {
	public HemlockTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}
}
