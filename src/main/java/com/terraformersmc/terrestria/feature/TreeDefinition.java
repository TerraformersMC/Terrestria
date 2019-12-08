package com.terraformersmc.terrestria.feature;

import net.minecraft.block.BlockState;


public final class TreeDefinition {
	// wrapper class kthxbye
	private TreeDefinition() {
	}

	// This class exports public utilities / constants, these methods should be public
	@SuppressWarnings("WeakerAccess")
	public static class Mega {
		private BlockState log;
		private BlockState leaves;
		private BlockState bark;
		private BlockState logQuarter;

		public Mega(BlockState log, BlockState leaves, BlockState logQuarter, BlockState bark) {
			this.log = log;
			this.leaves = leaves;
			this.logQuarter = logQuarter;
			this.bark = bark;
		}

		public BlockState getLog() {
			return this.log;
		}

		public BlockState getLeaves() {
			return this.leaves;
		}

		public BlockState getLogQuarter() {
			return this.logQuarter;
		}

		public BlockState getBark() {
			return this.bark;
		}
	}
}
