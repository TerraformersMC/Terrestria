package com.terraformersmc.terrestria.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TerrestriaDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
		FabricDataGenerator.Pack pack = dataGenerator.createPack();

		pack.addProvider(TerrestriaDynamicRegistryProvider::new);
		pack.addProvider(TerrestriaBiomeTagProvider::new);
		pack.addProvider(TerrestriaBlockLootTableProvider::new);
		TerrestriaBlockTagProvider blockTagProvider = pack.addProvider(TerrestriaBlockTagProvider::new);
		pack.addProvider((output, registries) -> new TerrestriaItemTagProvider(output, registries, blockTagProvider));
		pack.addProvider(TerrestriaEntityTypeTagProvider::new);
		pack.addProvider(TerrestriaModelProvider::new);
		pack.addProvider(TerrestriaRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		TerrestriaDynamicRegistryProvider.buildRegistry(registryBuilder);
	}
}
