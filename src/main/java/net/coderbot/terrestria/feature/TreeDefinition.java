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

		public Mega toMega(BlockState woodQuarter, BlockState bark) {
			Mega mega = new Mega();

			mega.wood = wood;
			mega.leaves = leaves;
			mega.woodQuarter = woodQuarter;
			mega.bark = bark;

			return mega;
		}

		public Palm toPalm(BlockState bark) {
			Palm palm = new Palm();

			palm.wood = wood;
			palm.leaves = leaves;
			palm.bark = bark;

			return palm;
		}
	}

	public static class Sakura extends Basic {
		public BlockState woodLeaves;
		public BlockState leafPile;
	}

	public static class Mega extends Basic {
		public BlockState woodQuarter;
		public BlockState bark;
	}

	public static class Palm extends Basic {
		public BlockState bark;
	}
}
