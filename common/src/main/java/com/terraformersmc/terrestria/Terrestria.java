package com.terraformersmc.terrestria;

import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.init.*;
import com.terraformersmc.terrestria.init.helpers.TerrestriaPlacementModifierType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Terrestria implements ModInitializer {
	public static final String MOD_ID = "terrestria";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static final TerrestriaConfigManager CONFIG_MANAGER = new TerrestriaConfigManager();

	private static Boolean initialized = false;
	private static final ArrayList<Runnable> runnables = new ArrayList<>(1);

	private static void register() {
		// Load the general config if it hasn't been loaded already
		CONFIG_MANAGER.getGeneralConfig();

		TerrestriaBlocks.init();
		TerrestriaBoats.init();
		TerrestriaItems.init();
		TerrestriaPlacementModifierType.init();
		TerrestriaFoliagePlacerTypes.init();
		TerrestriaTrunkPlacerTypes.init();
		TerrestriaTreeDecorators.init();
		TerrestriaFeatures.init();
		TerrestriaConfiguredFeatures.init();
		TerrestriaPlacedFeatures.init();
		TerrestriaStructures.init();
		StructureFeature.init();
		TerrestriaBiomes.init();
		TerrestriaVillagerTypes.init();

		// This must be after TerrestriaBiomes.init()
		CONFIG_MANAGER.getBiomeConfig();

		FabricItemGroupBuilder.create(new Identifier(MOD_ID, "items")).icon(() -> TerrestriaBlocks.RUBBER_SAPLING.asItem().getDefaultStack()).appendItems(stacks -> Registry.ITEM.forEach(item -> {
			if (Registry.ITEM.getId(item).getNamespace().equals(MOD_ID)) {
				item.appendStacks(item.getGroup(), (DefaultedList<ItemStack>) stacks);
			}
		})).build();
	}

	@Override
	public void onInitialize() {
		register();

		if (!FabricLoader.getInstance().isModLoaded("terrestria-worldgen")) {
			Terrestria.LOGGER.info("No Terrestria worldgen module present; Terrestria biomes will not generate.");
		}

		// At this point Terrestria is completely initialized.
		initialized = true;
		for (Runnable callback : runnables) {
			callback.run();
		}
	}

	public static void callbackWhenInitialized(Runnable callback) {
		if (initialized) {
			callback.run();
		} else {
			runnables.add(callback);
		}
	}

	public static TerrestriaConfigManager getConfigManager() {
		return CONFIG_MANAGER;
	}
}
