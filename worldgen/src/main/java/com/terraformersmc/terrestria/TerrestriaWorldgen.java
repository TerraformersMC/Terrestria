package com.terraformersmc.terrestria;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TerrestriaWorldgen implements ModInitializer {

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isModLoaded("terrablender")) {
			Terrestria.LOGGER.info("Enabling Terrestria's TerraBlender worldgen module.");
		} else {
			Terrestria.LOGGER.warn("Terrestria world generation disabled; TerraBlender is not present.");
		}
	}
}
