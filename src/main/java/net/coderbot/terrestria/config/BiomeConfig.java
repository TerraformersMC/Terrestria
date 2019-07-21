package net.coderbot.terrestria.config;

import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {

	private boolean freeze;
	private Map<String, BiomeConfigNode.Continental> continental = new HashMap<>();
	private Map<String, BiomeConfigNode.Variant> variants = new HashMap<>();

	public BiomeConfig() {}

	public BiomeConfig(boolean freeze) {
		this.freeze = freeze;
	}

	public boolean isFrozen() {
		return freeze;
	}

	public BiomeConfigNode.Continental continental(String name, BiomeConfigNode.Continental defaultNode) {
		return this.continental.computeIfAbsent(name, name_ -> defaultNode);
	}

	public BiomeConfigNode.Variant variant(String name, BiomeConfigNode.Variant defaultNode) {
		return this.variants.computeIfAbsent(name, name_ -> defaultNode);
	}
}
