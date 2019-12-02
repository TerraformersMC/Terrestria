package com.terraformersmc.terrestria.feature.trees;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.feature.TreeDefinition;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class HemlockTreeFeatureTiny extends ConiferTreeFeature {
	public HemlockTreeFeatureTiny(Function<Dynamic<?>, ? extends BranchedTreeFeatureConfig> function, TreeDefinition.Basic tree) {
		super(function, tree);
	}

	@Override
	public int getLeafHeight(Random rand) {
		return rand.nextInt(4) + 6;
	}

	@Override
	public int getBareTrunkHeight(Random rand) {
		return 1 + rand.nextInt(2);
	}

	@Override
	public int getMaxLeafRadius(Random rand) {
		return 3;
	}
}
