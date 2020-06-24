package com.terraformersmc.terrestria.feature.trees;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeatureMega;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class HemlockTreeFeatureMega extends ConiferTreeFeatureMega {
	public HemlockTreeFeatureMega(Codec<TreeFeatureConfig> codec, TreeDefinition.Mega tree) {
		super(codec, tree);
	}
}
