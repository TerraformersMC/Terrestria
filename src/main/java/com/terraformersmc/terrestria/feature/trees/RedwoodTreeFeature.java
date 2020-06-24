package com.terraformersmc.terrestria.feature.trees;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.trees.templates.ConiferTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class RedwoodTreeFeature extends ConiferTreeFeature {

	public RedwoodTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
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
