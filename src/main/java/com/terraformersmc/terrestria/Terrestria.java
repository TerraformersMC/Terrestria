package com.terraformersmc.terrestria;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.terraformersmc.terraform.config.BiomeConfig;
import com.terraformersmc.terraform.config.BiomeConfigHandler;
import com.terraformersmc.terrestria.command.LocateAny;
import com.terraformersmc.terrestria.compat.ClimaticWorldTypeCompat;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaEntities;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import com.terraformersmc.terrestria.init.TerrestriaGeneration;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.TerrestriaSurfaces;
import com.terraformersmc.terrestria.item.LogTurnerItem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Terrestria implements ModInitializer {
	public static final String MOD_ID = "terrestria";
	public static ItemGroup ITEM_GROUP;
	public static BiomeConfigHandler BIOME_CONFIG_HANDLER;
	public static final Logger log = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "items"), () -> new ItemStack(TerrestriaItems.RUBBER_SAPLING));
		BIOME_CONFIG_HANDLER = new BiomeConfigHandler(MOD_ID);

		BiomeConfig config = BIOME_CONFIG_HANDLER.getBiomeConfig();

		Set<String> configCache = new HashSet<>();

		TerrestriaBlocks.init();
		TerrestriaItems.init();
		TerrestriaEntities.init();
		TerrestriaFeatures.init();
		TerrestriaSurfaces.init();
		TerrestriaBiomes.init();
		TerrestriaGeneration.init(config, configCache);

		if (FabricLoader.getInstance().isModLoaded("cwt")) {
			log.info("Terrestria has detected ClimaticWorldType is installed... loading compatibility!");
			ClimaticWorldTypeCompat.init(configCache);
		}

		BIOME_CONFIG_HANDLER.save();

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "log_turner"), new LogTurnerItem(new Item.Settings().group(ITEM_GROUP)));
		LocateAny.register();
	}
}
