package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.foliageplacers.PalmFanFoliagePlacer;
import com.terraformersmc.terrestria.mixin.FoliagePlacerTypeAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class TerrestriaFoliagePlacerTypes {

	public static FoliagePlacerType<PalmFanFoliagePlacer> PALM_TOP_FOLIAGE_PLACER;

	public static void init() {
		PALM_TOP_FOLIAGE_PLACER = register("palm_top_foliage_placer", PalmFanFoliagePlacer.CODEC);
	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Codec<P> codec) {
		return Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(Terrestria.MOD_ID, name), FoliagePlacerTypeAccessor.createFoliagePlacerType(codec));
	}

}
