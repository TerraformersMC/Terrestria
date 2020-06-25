package com.terraformersmc.terrestria.init;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.placer.PlacerTypes;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.feature.foliageplacers.PalmFanFoliagePlacer;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class TerrestriaFoliagePlacerTypes {

	public static FoliagePlacerType<PalmFanFoliagePlacer> PALM_TOP_FOLIAGE_PLACER;

	public static void init() {
		PALM_TOP_FOLIAGE_PLACER = register("palm_top_foliage_placer", PalmFanFoliagePlacer.CODEC);
	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Codec<P> codec) {
		return PlacerTypes.registerFoliagePlacer(new Identifier(Terrestria.MOD_ID, name), codec);
	}

}
