package com.terraformersmc.terrestria.feature;

import net.minecraft.block.BlockState;


public final class TreeDefinition {
	// wrapper class kthxbye
	private TreeDefinition() {
	}

	public static class Basic {
		private BlockState log;
		private BlockState leaves;

		public Basic(BlockState log, BlockState leaves) {
			this.log = log;
			this.leaves = leaves;
		}

		public BlockState getLog() {
			return this.log;
		}

		public BlockState getLeaves() {
			return this.leaves;
		}

		public Sakura toSakura(BlockState woodLeaves, BlockState leafPile) {
			return new Sakura(this, woodLeaves, leafPile);
		}

		public Mega toMega(BlockState logQuarter, BlockState bark) {
			return new Mega(this.withBark(bark), logQuarter);
		}

		public WithBark withBark(BlockState bark) {
			return new WithBark(this, bark);
		}
	}

	// This class exports public utilities / constants, these methods should be public
	@SuppressWarnings("WeakerAccess")
	public static class Sakura extends Basic {
		private BlockState logLeaves;
		private BlockState leafPile;

		Sakura(TreeDefinition.Basic parent, BlockState logLeaves, BlockState leafPile) {
			super(parent.log, parent.leaves);

			this.logLeaves = logLeaves;
			this.leafPile = leafPile;
		}

		public BlockState getLogLeaves() {
			return this.logLeaves;
		}

		public BlockState getLeafPile() {
			return this.leafPile;
		}
	}

	// This class exports public utilities / constants, these methods should be public
	@SuppressWarnings("WeakerAccess")
	public static class WithBark extends Basic {
		private BlockState bark;

		WithBark(TreeDefinition.Basic parent, BlockState bark) {
			super(parent.log, parent.leaves);

			this.bark = bark;
		}

		public BlockState getBark() {
			return this.bark;
		}
	}

	// This class exports public utilities / constants, these methods should be public
	@SuppressWarnings("WeakerAccess")
	public static class Mega extends WithBark {
		private BlockState logQuarter;

		Mega(TreeDefinition.WithBark parent, BlockState logQuarter) {
			super(parent, parent.getBark());

			this.logQuarter = logQuarter;
		}

		public BlockState getLogQuarter() {
			return this.logQuarter;
		}
	}
}
