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
		private double chance;

		public Variant(boolean enable, double chance) {
			super(enable);
			
			this.chance = chance;
		}

		public Variant() {}

		public double getVariantChance() {
			return chance;
		}

		public void setVariantChance(double chance) {
			this.chance = chance;
		}
	}

	public static class Continental extends BiomeConfigNode {
		private double weight;

		public Continental(boolean enable, double weight) {
			super(enable);
			
			this. weight = weight;
		}

		public Continental() {}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}
	}
}
