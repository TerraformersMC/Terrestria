package net.coderbot.funwoods.block;

import net.coderbot.funwoods.feature.RubberTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.Random;

public class RubberSaplingGenerator extends SaplingGenerator {
	private TreeDefinition definition;

	public RubberSaplingGenerator(TreeDefinition definition) {
		super();

		this.definition = definition;
	}

	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random random_1) {
		return new RubberTreeFeature(DefaultFeatureConfig::deserialize, true, definition.wood, definition.leaves);
	}

	public static class TreeDefinition {
		public BlockState wood;
		public BlockState leaves;
	}
}
