package net.coderbot.terrestria.config;

import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {

	private short version;
	private boolean freeze;
	private Map<String, BiomeConfigNode> biomes = new HashMap<>();

	public BiomeConfig() {}

	public BiomeConfig(short version, boolean freeze, Map<String, BiomeConfigNode> biomes) {
		this.version = version;
		this.freeze = freeze;
		this.biomes = biomes;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public boolean isFrozen(boolean freeze) {
		return freeze;
	}

	public Map<String, BiomeConfigNode> getBiomes() {
		return biomes;
	}

	public BiomeConfigNode node(String name, BiomeConfigNode defaultNode) {
		return this.biomes.putIfAbsent(name, defaultNode);
	}
}
