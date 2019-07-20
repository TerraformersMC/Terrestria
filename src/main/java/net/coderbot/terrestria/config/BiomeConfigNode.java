package net.coderbot.terrestria.config;

public class BiomeConfigNode {

	private boolean enable;
	private float weight;
	private float variantChance;

	public BiomeConfigNode(boolean enable, float weight, float variantChance) {
		this.enable = enable;
		this. weight = weight;
		this.variantChance = variantChance;
	}

	public BiomeConfigNode() {}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getVariantChance() {
		return variantChance;
	}

	public void setVariantChance(float variantChance) {
		this.variantChance = variantChance;
	}
}
