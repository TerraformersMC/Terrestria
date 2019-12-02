package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeatureMega;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.function.Function;

public class HemlockTreeFeatureMega extends ConiferTreeFeatureMega {
	public HemlockTreeFeatureMega(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Mega tree) {
		super(function, tree);
	}
}
