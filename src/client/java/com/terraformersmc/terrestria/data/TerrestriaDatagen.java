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
		pack.addProvider(TerrestriaBiomeTagsProvider::new);
		pack.addProvider(TerrestriaBlockLootSubProvider::new);
		TerrestriaBlockTagsProvider blockTagProvider = pack.addProvider(TerrestriaBlockTagsProvider::new);
		pack.addProvider((output, registries) -> new TerrestriaItemTagsProvider(output, registries, blockTagProvider));
		pack.addProvider(TerrestriaVillagerTradeKeyTagProvider::new);
		pack.addProvider(TerrestriaEntityTypeTagsProvider::new);
		pack.addProvider(TerrestriaModelProvider::new);
		pack.addProvider(TerrestriaRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		TerrestriaDynamicRegistryProvider.buildRegistry(registryBuilder);
	}
}
