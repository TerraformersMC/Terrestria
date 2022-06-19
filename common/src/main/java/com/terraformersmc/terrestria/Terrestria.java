package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.config.BiomeConfig;
import com.terraformersmc.terraform.config.BiomeConfigHandler;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.init.*;
import com.terraformersmc.terrestria.item.LogTurnerItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Terrestria implements ModInitializer {
	public static final String MOD_ID = "terrestria";
	public static ItemGroup itemGroup;
	public static BiomeConfigHandler biomeConfigHandler;
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static final TerrestriaConfigManager CONFIG_MANAGER = new TerrestriaConfigManager();

	private static Boolean initialized = false;
	private static final ArrayList<Runnable> runnables = new ArrayList<>(1);

	@Override
	public void onInitialize() {
		// Load the general config if it hasn't been loaded already
		CONFIG_MANAGER.getGeneralConfig();

		itemGroup = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "items"), () -> new ItemStack(TerrestriaItems.RUBBER_SAPLING));
		biomeConfigHandler = new BiomeConfigHandler(MOD_ID);

		BiomeConfig config = biomeConfigHandler.getBiomeConfig();

		Set<String> enabledBiomes = new HashSet<>();

		TerrestriaBlocks.init();
		TerrestriaBoats.init();
		TerrestriaItems.init();
		TerrestriaFoliagePlacerTypes.init();
		TerrestriaTrunkPlacerTypes.init();
		TerrestriaTreeDecorators.init();
		TerrestriaFeatures.init();
		TerrestriaConfiguredFeatures.init();
		TerrestriaPlacedFeatures.init();
		//TerrestriaStructures.init();
		TerrestriaBiomes.init();
		TerrestriaVillagerTypes.init();

		biomeConfigHandler.save();

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "log_turner"), new LogTurnerItem(new Item.Settings().group(itemGroup)));

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
