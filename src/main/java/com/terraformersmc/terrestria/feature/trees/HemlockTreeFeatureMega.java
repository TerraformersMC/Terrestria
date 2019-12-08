package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeatureMega;
import net.minecraft.world.gen.feature.MegaTreeFeatureConfig;

import java.util.function.Function;

public class HemlockTreeFeatureMega extends ConiferTreeFeatureMega {
	public HemlockTreeFeatureMega(Function<Dynamic<?>, ? extends MegaTreeFeatureConfig> function, TreeDefinition.Mega tree) {
		super(function, tree);
	}
}
