package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.config.BiomeConfig;
import com.terraformersmc.terraform.config.BiomeConfigHandler;
import com.terraformersmc.terrestria.config.TerrestriaConfigManager;
import com.terraformersmc.terrestria.init.*;
import com.terraformersmc.terrestria.item.LogTurnerItem;
import com.terraformersmc.terrestria.surfacerules.TerrestriaSurfaceRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Terrestria implements ModInitializer/*, TerraBlenderApi*/ {
	public static final String MOD_ID = "terrestria";
	public static ItemGroup itemGroup;
	public static BiomeConfigHandler biomeConfigHandler;
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static final TerrestriaConfigManager CONFIG_MANAGER = new TerrestriaConfigManager();

//	// WTF is the right way to deal with this?
//	private static boolean coordinateWithTerraBlender = false;

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
		TerrestriaSurfaceRules.register();
		TerrestriaFoliagePlacerTypes.init();
		TerrestriaTrunkPlacerTypes.init();
		TerrestriaTreeDecorators.init();
		TerrestriaFeatures.init();
		TerrestriaConfiguredFeatures.init();
		TerrestriaPlacedFeatures.init();
		//TerrestriaStructures.init();
		TerrestriaBiomes.init();
//		TerrestriaGeneration.init(config, enabledBiomes);
		TerrestriaVillagerTypes.init();

		biomeConfigHandler.save();

//		if (coordinateWithTerraBlender) {
//			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, TerrestriaSurfaceRules.createRules());
//		} else {
//			coordinateWithTerraBlender = true;
//		}

		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "log_turner"), new LogTurnerItem(new Item.Settings().group(itemGroup)));
	}

//	@Override
//	public void onTerraBlenderInitialized() {
//		Regions.register(new TerrestriaGeneration(new Identifier(MOD_ID, "overworld"), 20));
//		if (coordinateWithTerraBlender) {
//			SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, TerrestriaSurfaceRules.createRules());
//		} else {
//			coordinateWithTerraBlender = true;
//		}
//	}

	public static TerrestriaConfigManager getConfigManager() {
		return CONFIG_MANAGER;
	}
}
