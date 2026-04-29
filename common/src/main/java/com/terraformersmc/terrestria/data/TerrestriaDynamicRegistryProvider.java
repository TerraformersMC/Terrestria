package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaConfiguredFeatures;
import com.terraformersmc.terrestria.init.TerrestriaPlacedFeatures;
import com.terraformersmc.terrestria.villager.TerrestriaVillagerTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@NullMarked
public class TerrestriaDynamicRegistryProvider extends FabricDynamicRegistryProvider {
	protected TerrestriaDynamicRegistryProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, TerrestriaConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, TerrestriaPlacedFeatures::bootstrap);
		registryBuilder.add(Registries.BIOME, TerrestriaBiomes::bootstrap);
		registryBuilder.add(Registries.VILLAGER_TRADE, TerrestriaVillagerTrades::bootstrap);
	}

	@Override
	public void configure(HolderLookup.Provider registries, Entries entries) {
		addAll(entries, registries.lookupOrThrow(Registries.CONFIGURED_FEATURE), Terrestria.MOD_ID);
		addAll(entries, registries.lookupOrThrow(Registries.PLACED_FEATURE), Terrestria.MOD_ID);
		addAll(entries, registries.lookupOrThrow(Registries.BIOME), Terrestria.MOD_ID);
		addAll(entries, registries.lookupOrThrow(Registries.VILLAGER_TRADE), Terrestria.MOD_ID);
	}

	@Override
	public String getName() {
		return "Terrestria Dynamic Registries";
	}

	/**
	 * Version of FabricDynamicRegistryProvider.Entries.addAll() using specified mod ID.
	 */
	@SuppressWarnings("UnusedReturnValue")
	public <T> List<Holder<T>> addAll(Entries entries, HolderLookup.RegistryLookup<T> registry, String modId) {
		return registry.listElementIds()
				.filter(registryKey -> registryKey.identifier().getNamespace().equals(modId))
				.map(key -> entries.add(registry, key))
				.toList();
	}
}
