package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class TerrestriaPlacementModifierType implements PlacementModifierType<SurfaceLevelFilterPlacementModifier> {
	public static final PlacementModifierType<SurfaceLevelFilterPlacementModifier> SURFACE_LEVEL_FILTER = register("surface_level_filter", SurfaceLevelFilterPlacementModifier.MODIFIER_CODEC);

	public static void init() {
	}

	@Override
	public MapCodec<SurfaceLevelFilterPlacementModifier> codec() {
		return null;
	}

	private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, MapCodec<P> codec) {
		return Registry.register(Registries.PLACEMENT_MODIFIER_TYPE, id, () -> codec);
	}
}
