package net.coderbot.terrestria.config;

import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {

	private short version;
	private boolean freeze;
	private Map<String, BiomeConfigNode.Continental> continental = new HashMap<>();
	private Map<String, BiomeConfigNode.Variant> variants = new HashMap<>();

	public BiomeConfig() {}

	public BiomeConfig(short version, boolean freeze) {
		this.version = version;
		this.freeze = freeze;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
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
