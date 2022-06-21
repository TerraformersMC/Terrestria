package com.terraformersmc.terrestria.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TerrestriaDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		dataGenerator.addProvider(TerrestriaBiomeTagProvider::new);
		dataGenerator.addProvider(TerrestriaBlockLootTableProvider::new);
		dataGenerator.addProvider(TerrestriaBlockTagProvider::new);
		dataGenerator.addProvider(TerrestriaItemTagProvider::new);
		//dataGenerator.addProvider(TerrestriaRecipeProvider::new);
	}
}
