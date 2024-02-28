package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaConfiguredFeatures;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TerrestriaDynamicRegistryProvider extends FabricDynamicRegistryProvider {
	protected TerrestriaDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, TerrestriaConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, TerrestriaPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, TerrestriaBiomes::bootstrap);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
		addAll(entries, registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE), Terrestria.MOD_ID);
		addAll(entries, registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE), Terrestria.MOD_ID);
		addAll(entries, registries.getWrapperOrThrow(RegistryKeys.BIOME), Terrestria.MOD_ID);
	}

	@Override
	public String getName() {
		return "Terrestria";
	}

	/**
	 * Version of FabricDynamicRegistryProvider.Entries.addAll() using specified mod ID.
	 */
	@SuppressWarnings("UnusedReturnValue")
	public <T> List<RegistryEntry<T>> addAll(Entries entries, RegistryWrapper.Impl<T> registry, String modId) {
		return registry.streamKeys()
				.filter(registryKey -> registryKey.getValue().getNamespace().equals(modId))
				.map(key -> entries.add(registry, key))
				.toList();
	}
}
