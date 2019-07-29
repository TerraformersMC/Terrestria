package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.carver.CanyonValleyCarver;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;

public class TerrestriaCarvers {

	public static CanyonValleyCarver CANYON_VALLEY;

	public static void init() {
		CANYON_VALLEY = register("canyon_valley", new CanyonValleyCarver(ProbabilityConfig::deserialize, 256));
	}

	private static <C extends CarverConfig, F extends Carver<C>> F register(String string_1, F carver_1) {
		return (F) Registry.register(Registry.CARVER, string_1, carver_1);
	}
}
