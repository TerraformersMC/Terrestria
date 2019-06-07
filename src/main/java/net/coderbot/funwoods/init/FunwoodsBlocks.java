package net.coderbot.funwoods.init;

import net.coderbot.funwoods.block.CustomSaplingBlock;
import net.coderbot.funwoods.block.CustomSaplingGenerator;
import net.coderbot.funwoods.feature.CypressTreeFeature;
import net.coderbot.funwoods.feature.RubberTreeFeature;
import net.coderbot.funwoods.feature.TreeDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class FunwoodsBlocks {
	public static CustomSaplingBlock RUBBER_SAPLING;
	public static CustomSaplingBlock CYPRESS_SAPLING;

	public static void init() {
		TreeDefinition.Basic oak = new TreeDefinition.Basic();
		oak.leaves = Blocks.OAK_LEAVES.getDefaultState();
		oak.wood = Blocks.OAK_LOG.getDefaultState();

		RUBBER_SAPLING = Registry.register(Registry.BLOCK, "funwoods:rubber_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new RubberTreeFeature(DefaultFeatureConfig::deserialize, true, oak))
				)
		);

		CYPRESS_SAPLING = Registry.register(Registry.BLOCK, "funwoods:cypress_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, oak))
				)
		);
	}
}
