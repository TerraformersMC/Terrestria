package com.terraformersmc.terrestria.init;

import com.mojang.serialization.MapCodec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.helpers.SurfaceLevelFilterPlacementModifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaPlacementModifierTypes {
	public static final ResourceKey<MapCodec<? extends PlacementModifier>> SURFACE_LEVEL_FILTER = resourceKey("surface_level_filter");

	public static void init() {
		Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, SURFACE_LEVEL_FILTER, SurfaceLevelFilterPlacementModifier.CODEC);
	}

	private static ResourceKey<MapCodec<? extends PlacementModifier>> resourceKey(String path) {
		return ResourceKey.create(Registries.PLACEMENT_MODIFIER_TYPE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}
}
