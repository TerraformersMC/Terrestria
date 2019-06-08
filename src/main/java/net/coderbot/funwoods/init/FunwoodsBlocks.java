package net.coderbot.funwoods.init;

import net.coderbot.funwoods.block.CustomDoorBlock;
import net.coderbot.funwoods.block.CustomSaplingBlock;
import net.coderbot.funwoods.block.CustomSaplingGenerator;
import net.coderbot.funwoods.block.CustomStairsBlock;
import net.coderbot.funwoods.feature.CypressTreeFeature;
import net.coderbot.funwoods.feature.RubberTreeFeature;
import net.coderbot.funwoods.feature.TreeDefinition;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class FunwoodsBlocks {
	public static WoodBlocks RUBBER;
	public static WoodBlocks CYPRESS;
	public static WoodBlocks BALD_CYPRESS;
	public static WoodBlocks JAPANESE_MAPLE;
	public static WoodBlocks RAINBOW_EUCALYPTUS;
	public static WoodBlocks SAKURA;

	public static CustomSaplingBlock RUBBER_SAPLING;
	public static CustomSaplingBlock CYPRESS_SAPLING;
	public static CustomSaplingBlock BALD_CYPRESS_SAPLING;
	public static CustomSaplingBlock JAPANESE_MAPLE_SAPLING;
	public static CustomSaplingBlock RAINBOW_EUCALYPTUS_SAPLING;
	public static CustomSaplingBlock SAKURA_SAPLING;

	public static void init() {
		//TreeDefinition.Basic oak = new TreeDefinition.Basic();
		//oak.leaves = Blocks.OAK_LEAVES.getDefaultState();
		//oak.wood = Blocks.OAK_LOG.getDefaultState();

		RUBBER = WoodBlocks.register("rubber");
		CYPRESS = WoodBlocks.register("cypress");
		BALD_CYPRESS = WoodBlocks.register("bald_cypress");
		JAPANESE_MAPLE = WoodBlocks.register("japanese_maple");
		RAINBOW_EUCALYPTUS = WoodBlocks.register("rainbow_eucalyptus");
		SAKURA = WoodBlocks.register("sakura");

		RUBBER_SAPLING = register("rubber_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new RubberTreeFeature(DefaultFeatureConfig::deserialize, true, RUBBER.getBasicDefinition()))
				)
		);

		CYPRESS_SAPLING = register("cypress_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, CYPRESS.getBasicDefinition()))
				)
		);

		// TODO: These saplings need to be configured with proper tree generators
		BALD_CYPRESS_SAPLING = register("bald_cypress_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, BALD_CYPRESS.getBasicDefinition()))
				)
		);

		JAPANESE_MAPLE_SAPLING = register("japanese_maple_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, JAPANESE_MAPLE.getBasicDefinition()))
				)
		);

		RAINBOW_EUCALYPTUS_SAPLING = register("rainbow_eucalyptus_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, RAINBOW_EUCALYPTUS.getBasicDefinition()))
				)
		);

		SAKURA_SAPLING = register("sakura_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new CypressTreeFeature(DefaultFeatureConfig::deserialize, true, SAKURA.getBasicDefinition()))
				)
		);
	}

	public static <T extends Block> T register(String name, T block) {
		return Registry.register(Registry.BLOCK, new Identifier("funwoods", name), block);
	}

	public static class WoodBlocks {
		LogBlock log;
		LeavesBlock leaves;
		Block planks;
		SlabBlock slab;
		CustomStairsBlock stairs;
		FenceBlock fence;
		FenceGateBlock fenceGate;
		CustomDoorBlock door;

		private WoodBlocks() {}

		// TODO: Map colors
		public static WoodBlocks register(String name) {
			WoodBlocks blocks = new WoodBlocks();

			blocks.log = FunwoodsBlocks.register(name + "_log", new LogBlock(MaterialColor.SPRUCE, Block.Settings.copy(Blocks.OAK_LOG)));
			blocks.leaves = FunwoodsBlocks.register(name + "_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));
			blocks.planks = FunwoodsBlocks.register(name + "_planks", new Block(Block.Settings.copy(Blocks.OAK_PLANKS)));
			blocks.slab = FunwoodsBlocks.register(name + "_slab", new SlabBlock(Block.Settings.copy(Blocks.OAK_SLAB)));
			blocks.stairs = FunwoodsBlocks.register(name + "_stairs", new CustomStairsBlock(blocks.planks.getDefaultState(), Block.Settings.copy(Blocks.OAK_STAIRS)));
			blocks.fence = FunwoodsBlocks.register(name + "_fence", new FenceBlock(Block.Settings.copy(Blocks.OAK_FENCE)));
			blocks.fenceGate = FunwoodsBlocks.register(name + "_fence_gate", new FenceGateBlock(Block.Settings.copy(Blocks.OAK_FENCE_GATE)));
			blocks.door = FunwoodsBlocks.register(name + "_door", new CustomDoorBlock(Block.Settings.copy(Blocks.OAK_FENCE_GATE)));

			return blocks;
		}

		public TreeDefinition.Basic getBasicDefinition() {
			TreeDefinition.Basic basic = new TreeDefinition.Basic();

			basic.leaves = this.leaves.getDefaultState();
			basic.wood = this.log.getDefaultState();

			return basic;
		}
	}
}
