package net.coderbot.terrestria.config;

public class BiomeConfigNode {

	private boolean enable;

	public BiomeConfigNode(boolean enable) {
		this.enable = enable;
	}

	public BiomeConfigNode() {}

	public boolean isEnabled() {
		return enable;
	}

	public void setEnabled(boolean enable) {
		this.enable = enable;
	}
	
	public static class Variant extends BiomeConfigNode {
		private float chance;

		public Variant(boolean enable, float chance) {
			super(enable);
			
			this.chance = chance;
		}

		public Variant() {}

		public float getVariantChance() {
			return chance;
		}

		public void setVariantChance(float chance) {
			this.chance = chance;
		}
	}

	public static class Continental extends BiomeConfigNode {
		private float weight;

		public Continental(boolean enable, float weight, float chance) {
			super(enable);
			
			this. weight = weight;
		}

		public Continental() {}

		public float getWeight() {
			return weight;
		}

		public void setWeight(float weight) {
			this.weight = weight;
		}
	}
}
