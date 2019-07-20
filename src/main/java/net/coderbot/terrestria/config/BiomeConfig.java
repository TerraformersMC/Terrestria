package net.coderbot.terrestria.config;

import java.util.HashMap;
import java.util.Map;

public class BiomeConfig {

	private short version;
	private Map<String, BiomeConfigNode> nodes = new HashMap<>();

	public BiomeConfig() {}

	public BiomeConfig(short version, Map<String, BiomeConfigNode> biomes) {
		this.version = version;
		this.nodes = biomes;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public BiomeConfigNode getNode(String name) {
		return this.nodes.get(name);
	}

	public Map<String, BiomeConfigNode> getNodes() {
		return nodes;
	}

	public void addNode(String name, BiomeConfigNode config) {
		this.nodes.put(name, config);
	}
}
