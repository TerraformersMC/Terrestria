package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

public class TerrestriaPlacementModifierType implements PlacementModifierType<SurfaceLevelFilterPlacementModifier> {
	public static final PlacementModifierType<SurfaceLevelFilterPlacementModifier> SURFACE_LEVEL_FILTER = register("surface_level_filter", SurfaceLevelFilterPlacementModifier.MODIFIER_CODEC);

	public static void init() {
	}

	@Override
	public MapCodec<SurfaceLevelFilterPlacementModifier> codec() {
		return null;
	}

	private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, MapCodec<P> codec) {
		return Registry.register(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, id, () -> codec);
	}
}
