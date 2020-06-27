package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.feature.CattailFeature;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SeagrassFeatureConfig;

// This class exports public feature constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaFeatures {

	public static CattailFeature CATTAIL;

	public static void init() {
		CATTAIL = register("cattail", new CattailFeature(SeagrassFeatureConfig.CODEC, TerrestriaBlocks.CATTAIL, TerrestriaBlocks.TALL_CATTAIL));
	}

	public static <T extends Feature<FC>, FC extends FeatureConfig> T register(String name, T feature) {
		return Registry.register(Registry.FEATURE, new Identifier(Terrestria.MOD_ID, name), feature);
	}
}
