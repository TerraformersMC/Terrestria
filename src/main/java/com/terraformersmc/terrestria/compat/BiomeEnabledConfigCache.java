package com.terraformersmc.terrestria.compat;

import java.util.HashMap;

import com.google.common.collect.Maps;

public class BiomeEnabledConfigCache {
	private final HashMap<String, Boolean> cache = Maps.newHashMap();

	public void cache(String name, boolean enabled) {
		cache.put(name, enabled);
	}

	public boolean isEnabled(String name) {
		return cache.getOrDefault(name, false);
	}
}
