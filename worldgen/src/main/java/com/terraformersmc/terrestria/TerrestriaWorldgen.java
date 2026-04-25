package com.terraformersmc.terrestria;

import com.terraformersmc.terrestria.biomegen.TerrestriaBiolithGeneration;
import com.terraformersmc.terrestria.surfacebuilders.TerrestriaSurfaceBuilders;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.Nullable;

public class TerrestriaWorldgen implements ModInitializer {
	private static ServerLevel OVERWORLD = null;

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isModLoaded("biolith")) {
			Terrestria.LOGGER.info("Enabling Terrestria's Biolith worldgen module.");

			Terrestria.callbackWhenInitialized(TerrestriaSurfaceBuilders::init);
			Terrestria.callbackWhenInitialized(new TerrestriaBiolithGeneration());
		} else {
			Terrestria.LOGGER.warn("Terrestria world generation disabled; Biolith is not present.");
		}

		// We need access to the Overworld for some surface builders.
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			OVERWORLD = server.overworld();
		});
		ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
			OVERWORLD = null;
		});
	}

	public static @Nullable ServerLevel getOverworld() {
		return OVERWORLD;
	}
}
