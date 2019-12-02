package com.terraformersmc.terrestria.foliage;

import com.mojang.datafixers.Dynamic;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

import java.util.function.Function;

public class TerrestriaFoliagePlacers {
	public static FoliagePlacerType<CypressFoliagePlacer> CYPRESS_FOLIAGE_PLACER;

	public static void init() {
		CYPRESS_FOLIAGE_PLACER = register("cypress", CypressFoliagePlacer::new);
	}

	private static <P extends FoliagePlacer> FoliagePlacerType<P> register(String name, Function<Dynamic<?>, P> function) {
		// TODO

		return Registry.register(Registry.FOLIAGE_PLACER_TYPE, new Identifier(Terrestria.MOD_ID, name + "_foliage_placer"), /*new FoliagePlacerType(function)*/ null);
	}
}
