package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeature;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class RedwoodTreeFeature extends ConiferTreeFeature {
	public RedwoodTreeFeature(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Basic tree) {
		super(function, tree);
	}

	@Override
	public int getBareTrunkHeight(Random rand) {
		return 12 + rand.nextInt(8);
	}

	//For use in ConiferTreeFeatureNew
	/*
	@Override
	public int getLeafLayers(Random rand) {
		return rand.nextInt(3) + 6;
	}
	 */

	@Override
	public int getMaxLeafRadius(Random rand) {
		return 5 + rand.nextInt(3);
	}
}
