package com.terraformersmc.terrestria.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class TerrestriaDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(TerrestriaDynamicRegistryProvider::new);
		pack.addProvider(TerrestriaBiomeTagProvider::new);
		pack.addProvider(TerrestriaBlockLootTableProvider::new);
		TerrestriaBlockTagProvider blockTagProvider = pack.addProvider(TerrestriaBlockTagProvider::new);
		pack.addProvider((output, registries) -> new TerrestriaItemTagProvider(output, registries, blockTagProvider));
		pack.addProvider(TerrestriaRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		TerrestriaDynamicRegistryProvider.buildRegistry(registryBuilder);
	}
}
