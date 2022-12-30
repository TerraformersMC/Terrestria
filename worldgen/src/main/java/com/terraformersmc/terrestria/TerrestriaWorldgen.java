package com.terraformersmc.terrestria;

import com.terraformersmc.terrestria.biomegen.TerrestriaBiolithGeneration;
import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilders;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TerrestriaWorldgen implements ModInitializer {

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isModLoaded("terrablender")) {
			Terrestria.LOGGER.info("Enabling Terrestria's TerraBlender worldgen module.");
		} else if (FabricLoader.getInstance().isModLoaded("biolith")) {
			Terrestria.LOGGER.info("Enabling Terrestria's Biolith worldgen module.");

			Terrestria.callbackWhenInitialized(TerrestriaSurfaceBuilders::init);
			Terrestria.callbackWhenInitialized(new TerrestriaBiolithGeneration());
		} else {
			Terrestria.LOGGER.warn("Terrestria world generation disabled; neither Biolith nor TerraBlender is present.");
		}
	}
}
