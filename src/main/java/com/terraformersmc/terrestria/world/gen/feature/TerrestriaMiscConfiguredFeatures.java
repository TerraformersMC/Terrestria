package com.terraformersmc.terrestria.world.gen.feature;

import com.terraformersmc.terrestria.Terrestria;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class TerrestriaMiscConfiguredFeatures {

	public static ConfiguredFeature<DefaultFeatureConfig, ?> DUM_DUM_HEAD;


	public static void init() {
		DUM_DUM_HEAD = BuiltinRegistries.add(
				BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(Terrestria.MOD_ID, "dum_dum_head"),
				TerrestriaFeature.DUM_DUM_HEAD.configure(DefaultFeatureConfig.INSTANCE));
	}

}
