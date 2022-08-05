package com.terraformersmc.terrestria.config;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.Map;
import java.util.stream.Collectors;

public class TerrestriaBiomeConfig {
	private final Map<String, Boolean> biomes;

	TerrestriaBiomeConfig() {
		biomes = TerrestriaBiomes.biomes.keySet().stream().collect(Collectors.toMap(k -> k,
			k -> !(k.equals("caldera") || k.equals("volcanic_island"))));
	}

	public boolean isBiomeEnabled(String name) {
		return !biomes.containsKey(name) || biomes.get(name);
	}

	public boolean isBiomeEnabled(Identifier identifier) {
		return isBiomeEnabled(identifier.getPath());
	}

	public boolean isBiomeEnabled(RegistryKey<Biome> biomeKey) {
		return isBiomeEnabled(biomeKey.getValue());
	}
}
