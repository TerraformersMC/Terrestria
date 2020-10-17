package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.feature.CattailFeature;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.misc.DumDumHeadFeature;
import com.terraformersmc.terrestria.feature.tree.extended.SandyTreeFeature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaFeatures {

	public static CattailFeature CATTAIL;
	public static Feature<DefaultFeatureConfig> DUM_DUM_HEAD;
	public static Feature<TreeFeatureConfig> SANDY_TREE;

	public static void init() {
		CATTAIL = register("cattail", new CattailFeature(ProbabilityConfig.CODEC, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL));
		DUM_DUM_HEAD = register("dum_dum_head", new DumDumHeadFeature(DefaultFeatureConfig.CODEC));
		SANDY_TREE = register("sandy_tree", new SandyTreeFeature(TreeFeatureConfig.CODEC));
	}

	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registry.FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
	}
}
