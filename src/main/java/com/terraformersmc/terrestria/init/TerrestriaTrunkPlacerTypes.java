package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.trunkplacers.BentTrunkPlacer;
import com.terraformersmc.terrestria.mixin.TrunkPlacerTypeAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TerrestriaTrunkPlacerTypes {

	public static TrunkPlacerType<BentTrunkPlacer> BENT_TRUNK_PLACER;

	private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
		return Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(Terrestria.MOD_ID, name), TrunkPlacerTypeAccessor.createTrunkPlacerType(codec));
	}

	public static void init() {
		BENT_TRUNK_PLACER = register("bent_trunk_placer", BentTrunkPlacer.CODEC);
	}

}
