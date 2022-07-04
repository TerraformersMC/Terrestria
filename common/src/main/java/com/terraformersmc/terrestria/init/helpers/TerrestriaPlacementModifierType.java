package com.terraformersmc.terrestria.init.helpers;

import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class TerrestriaPlacementModifierType implements PlacementModifierType<SurfaceLevelFilterPlacementModifier> {
	public static final PlacementModifierType<SurfaceLevelFilterPlacementModifier> SURFACE_LEVEL_FILTER = register("surface_level_filter", SurfaceLevelFilterPlacementModifier.MODIFIER_CODEC);

	public static void init() {
	}

	@Override
	public Codec<SurfaceLevelFilterPlacementModifier> codec() {
		return null;
	}

	private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, Codec<P> codec) {
		return Registry.register(Registry.PLACEMENT_MODIFIER_TYPE, id, () -> codec);
	}
}
