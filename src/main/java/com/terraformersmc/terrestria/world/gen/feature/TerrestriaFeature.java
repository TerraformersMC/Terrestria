package com.terraformersmc.terrestria.world.gen.feature;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.world.gen.feature.misc.DumDumHeadFeature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaFeature {

	public static Feature<ProbabilityConfig> CATTAIL;
	public static Feature<DefaultFeatureConfig> DUM_DUM_HEAD;
	public static Feature<TreeFeatureConfig> QUARTERED_MEGA_TREE;


	@SuppressWarnings({"rawtypes, unchecked"})
	public static void init() {
		CATTAIL = register("cattail", new CattailFeature(ProbabilityConfig.CODEC, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL));
		DUM_DUM_HEAD = register("dum_dum_head", new DumDumHeadFeature(DefaultFeatureConfig.CODEC));
		QUARTERED_MEGA_TREE = register("quartered_mega_tree", new TreeFeature((Codec)QuarteredMegaTreeConfig.CODEC));
	}


	public static <FC extends FeatureConfig, F extends Feature<FC>> F register(String name, F feature) {
		return Registry.register(Registry.FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
	}
}
