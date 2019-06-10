package net.coderbot.terrestria.feature;

import net.minecraft.block.BlockState;

public final class TreeDefinition {
	// wrapper class kthxbye
	private TreeDefinition() {}

	public static class Basic {
		public BlockState wood;
		public BlockState leaves;

		public Sakura toSakura(BlockState woodLeaves, BlockState leafPile) {
			Sakura sakura = new Sakura();

			sakura.wood = wood;
			sakura.leaves = leaves;
			sakura.woodLeaves = woodLeaves;
			sakura.leafPile = leafPile;

			return sakura;
		}
	}

	public static class Sakura extends Basic {
		public BlockState woodLeaves;
		public BlockState leafPile;
	}
}
