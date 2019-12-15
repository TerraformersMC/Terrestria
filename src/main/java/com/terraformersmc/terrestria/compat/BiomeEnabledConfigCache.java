package com.terraformersmc.terrestria.compat;

import java.util.Set;

import com.google.common.collect.Sets;

public class BiomeEnabledConfigCache {
	private final Set<String> cache = Sets.newHashSet();

	public void cache(String name) {
		cache.add(name);
	}

	public boolean isEnabled(String name) {
		return cache.contains(name);
	}
}
