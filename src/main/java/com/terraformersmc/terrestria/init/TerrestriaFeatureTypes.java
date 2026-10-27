package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.misc.CattailFeature;
import com.terraformersmc.terrestria.feature.misc.DumDumHeadFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaFeatureTypes {
	public static final ResourceKey<MapCodec<? extends Feature>> CATTAIL = resourceKey("cattail");
	public static final ResourceKey<MapCodec<? extends Feature>> DUM_DUM_HEAD = resourceKey("dum_dum_head");

	public static void init() {
		Registry.register(BuiltInRegistries.FEATURE_TYPE, CATTAIL, CattailFeature.CODEC);
		Registry.register(BuiltInRegistries.FEATURE_TYPE, DUM_DUM_HEAD, DumDumHeadFeature.CODEC);
	}

	private static ResourceKey<MapCodec<? extends Feature>> resourceKey(String path) {
		return ResourceKey.create(Registries.FEATURE_TYPE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}
}
