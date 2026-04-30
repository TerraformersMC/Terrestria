package com.terraformersmc.terrestria;

import com.terraformersmc.terrestria.biomegen.TerrestriaBiolithGeneration;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.init.*;
import com.terraformersmc.terrestria.init.helpers.TerrestriaPlacementModifierType;
import com.terraformersmc.terrestria.item.TerrestriaCreativeModeTabs;
import com.terraformersmc.terrestria.surface.builders.TerrestriaSurfaceBuilders;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terrestria implements ModInitializer {
	public static final String MOD_ID = "terrestria";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static final TerrestriaConfigManager CONFIG_MANAGER = new TerrestriaConfigManager();

	@Override
	public void onInitialize() {
		TerrestriaBlocks.init();
		TerrestriaItems.init();
		TerrestriaPlacementModifierType.init();
		TerrestriaFoliagePlacerTypes.init();
		TerrestriaTrunkPlacerTypes.init();
		TerrestriaTreeDecorators.init();
		TerrestriaFeatures.init();
		TerrestriaStructures.init();
		TerrestriaVillagerTypes.init();
		TerrestriaCreativeModeTabs.init();
		TerrestriaRegistryAliases.init();

		if (FabricLoader.getInstance().isModLoaded("biolith")) {
			Terrestria.LOGGER.info("Enabling Terrestria's Biolith worldgen module.");
			TerrestriaSurfaceBuilders.init();
			TerrestriaBiolithGeneration.init();
		} else {
			Terrestria.LOGGER.warn("Terrestria world generation disabled; Biolith is not present.");
		}
	}

	public static TerrestriaConfigManager getConfigManager() {
		return CONFIG_MANAGER;
	}
}
