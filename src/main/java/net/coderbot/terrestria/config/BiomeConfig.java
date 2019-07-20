package net.coderbot.terrestria.config;

import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {

	private short version;
	private Map<String, BiomeConfigNode> biomes = new HashMap<>();

	public BiomeConfig() {}

	public BiomeConfig(short version, Map<String, BiomeConfigNode> biomes) {
		this.version = version;
		this.biomes = biomes;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public BiomeConfigNode getNode(String name) {
		return this.biomes.get(name);
	}

	public Map<String, BiomeConfigNode> getBiomes() {
		return biomes;
	}

	public void addNode(String name, BiomeConfigNode config) {
		this.biomes.put(name, config);
	}
}
