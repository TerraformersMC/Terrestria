package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.feature.CattailFeature;
import com.terraformersmc.terrestria.feature.misc.DumDumHeadFeature;
import com.terraformersmc.terrestria.feature.tree.treeconfigs.QuarteredMegaTreeConfig;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;

public class TerrestriaFeatures {

	public static CattailFeature CATTAIL;
	public static Feature<NoneFeatureConfiguration> DUM_DUM_HEAD;
	public static TreeFeature QUARTERED_MEGA_TREE;

	@SuppressWarnings({"rawtypes, unchecked"})
	public static void init() {
		CATTAIL = TerrestriaRegistry.register("cattail", new CattailFeature(ProbabilityFeatureConfiguration.CODEC, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL));
		DUM_DUM_HEAD = TerrestriaRegistry.register("dum_dum_head", new DumDumHeadFeature(NoneFeatureConfiguration.CODEC));

		// Super hacky casts, but it works
		QUARTERED_MEGA_TREE = TerrestriaRegistry.register("quartered_mega_tree", new TreeFeature((Codec) QuarteredMegaTreeConfig.CODEC));
	}
}
