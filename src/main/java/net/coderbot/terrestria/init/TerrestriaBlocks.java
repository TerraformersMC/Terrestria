package net.coderbot.terrestria.init;

import net.coderbot.terrestria.block.TransparentLeavesBlock;
import net.coderbot.terrestria.block.*;
import net.coderbot.terrestria.feature.RubberTreeFeature;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class TerrestriaBlocks {
	public static WoodBlocks RUBBER;
	public static WoodBlocks CYPRESS;
	public static WoodBlocks BALD_CYPRESS;
	public static WoodBlocks JAPANESE_MAPLE;
	public static WoodBlocks RAINBOW_EUCALYPTUS;
	public static WoodBlocks SAKURA;

	public static LeavesBlock JAPANESE_MAPLE_SHRUB_LEAVES;
	public static LeafPileBlock SAKURA_LEAF_PILE;

	public static SeagrassBlock CATTAIL;
	public static TallSeagrassBlock TALL_CATTAIL;

	public static QuarterLogBlock BALD_CYPRESS_QUARTER_LOG;
	public static QuarterLogBlock RAINBOW_EUCALYPTUS_QUARTER_LOG;

	public static CustomSaplingBlock RUBBER_SAPLING;
	public static CustomSaplingBlock CYPRESS_SAPLING;
	public static CustomSaplingBlock BALD_CYPRESS_SAPLING;
	public static CustomSaplingBlock JAPANESE_MAPLE_SAPLING;
	public static CustomSaplingBlock JAPANESE_MAPLE_SHRUB_SAPLING;
	public static CustomSaplingBlock RAINBOW_EUCALYPTUS_SAPLING;
	public static CustomSaplingBlock SAKURA_SAPLING;

	// Volcanic Island blocks
	public static SandBlock BASALT_SAND;
	public static Block BASALT_DIRT;
	public static Block BASALT_GRASS_BLOCK;
	public static Block BASALT;

	public static void init() {
		FlammableBlockRegistry flammable = FlammableBlockRegistry.getDefaultInstance();

		RUBBER = WoodBlocks.register("rubber", MaterialColor.BROWN, flammable);
		CYPRESS = WoodBlocks.register("cypress", MaterialColor.LIGHT_GRAY, flammable);
		BALD_CYPRESS = WoodBlocks.register("bald_cypress", MaterialColor.LIGHT_GRAY, flammable);
		JAPANESE_MAPLE = WoodBlocks.register("japanese_maple", MaterialColor.RED, flammable);
		RAINBOW_EUCALYPTUS = WoodBlocks.register("rainbow_eucalyptus", MaterialColor.RED, flammable);

		SAKURA = WoodBlocks.registerManufactured("sakura", flammable);
		SAKURA.log = register("sakura_log", new SakuraLogBlock(Block.Settings.copy(Blocks.OAK_LOG)));
		SAKURA.wood = SAKURA.log;
		SAKURA.leaves = register("sakura_leaves", new TransparentLeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));
		SAKURA.addTreeFireInfo(flammable);

		JAPANESE_MAPLE_SHRUB_LEAVES = register("japanese_maple_shrub_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));
		SAKURA_LEAF_PILE = register("sakura_leaf_pile", new LeafPileBlock(Block.Settings.copy(Blocks.OAK_LEAVES).noCollision()));

		flammable.add(JAPANESE_MAPLE_SHRUB_LEAVES, 30, 60);
		flammable.add(SAKURA_LEAF_PILE, 30, 60);

		CATTAIL = register("cattail", new CustomSeagrassBlock(Block.Settings.copy(Blocks.SEAGRASS)));
		TALL_CATTAIL = register("tall_cattail", new CustomTallSeagrassBlock(Block.Settings.copy(Blocks.SEAGRASS)));

		BALD_CYPRESS_QUARTER_LOG = register("bald_cypress_log_quarter", new QuarterLogBlock(Block.Settings.copy(Blocks.OAK_LOG)));
		RAINBOW_EUCALYPTUS_QUARTER_LOG = register("rainbow_eucalyptus_log_quarter", new QuarterLogBlock(Block.Settings.copy(Blocks.OAK_LOG)));

		flammable.add(BALD_CYPRESS_QUARTER_LOG, 5, 5);
		flammable.add(RAINBOW_EUCALYPTUS_QUARTER_LOG, 5, 5);

		RUBBER_SAPLING = register("rubber_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (
						rand -> new RubberTreeFeature(DefaultFeatureConfig::deserialize, true, RUBBER.getBasicDefinition())
				)
		));

		CYPRESS_SAPLING = register("cypress_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (rand -> TerrestriaBiomes.CYPRESS_TREE)
		));

		BALD_CYPRESS_SAPLING = register("bald_cypress_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (rand -> TerrestriaBiomes.BALD_CYPRESS_TREE)
		));

		JAPANESE_MAPLE_SAPLING = register("japanese_maple_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (rand -> TerrestriaBiomes.JAPANESE_MAPLE_TREE)
		));

		JAPANESE_MAPLE_SHRUB_SAPLING = register("japanese_maple_shrub_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (rand -> TerrestriaBiomes.JAPANESE_MAPLE_SHRUB)
		));

		RAINBOW_EUCALYPTUS_SAPLING = register("rainbow_eucalyptus_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator (rand -> TerrestriaBiomes.RAINBOW_EUCALYPTUS_TREE)
		));

		SAKURA_SAPLING = register("sakura_sapling", new CustomSaplingBlock (
				new CustomSaplingGenerator(rand -> TerrestriaBiomes.SAKURA_TREE)
		));

		BASALT_SAND = register("basalt_sand", new SandBlock(0x202020, Block.Settings.copy(Blocks.SAND)));
		BASALT_DIRT = register("basalt_dirt", new Block(Block.Settings.copy(Blocks.DIRT)));
		BASALT_GRASS_BLOCK = register("basalt_grass_block", new BasaltGrassBlock(Block.Settings.copy(Blocks.GRASS_BLOCK)));
		BASALT = register("basalt", new Block(Block.Settings.copy(Blocks.STONE)));

		// TODO: Stripped Logs, Stripped Wood
	}

	public static <T extends Block> T register(String name, T block) {
		return Registry.register(Registry.BLOCK, new Identifier("terrestria", name), block);
	}

	public static class WoodBlocks {
		public Block log;
		public Block wood;
		public LeavesBlock leaves;

		public Block planks;
		public SlabBlock slab;
		public CustomStairsBlock stairs;
		public FenceBlock fence;
		public FenceGateBlock fenceGate;
		public CustomDoorBlock door;

		private WoodBlocks() {}

		// TODO: Map colors
		public static WoodBlocks register(String name, MaterialColor wood, FlammableBlockRegistry registry) {
			WoodBlocks blocks = registerManufactured(name, registry);

			blocks.log = TerrestriaBlocks.register(name + "_log", new LogBlock(wood, Block.Settings.copy(Blocks.OAK_LOG)));
			blocks.wood = TerrestriaBlocks.register(name + "_wood", new LogBlock(wood, Block.Settings.copy(Blocks.OAK_LOG)));
			blocks.leaves = TerrestriaBlocks.register(name + "_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));

			blocks.addTreeFireInfo(registry);

			return blocks;
		}

		public static WoodBlocks registerManufactured(String name, FlammableBlockRegistry registry) {
			WoodBlocks blocks = new WoodBlocks();

			blocks.planks = TerrestriaBlocks.register(name + "_planks", new Block(Block.Settings.copy(Blocks.OAK_PLANKS)));
			blocks.slab = TerrestriaBlocks.register(name + "_slab", new SlabBlock(Block.Settings.copy(Blocks.OAK_SLAB)));
			blocks.stairs = TerrestriaBlocks.register(name + "_stairs", new CustomStairsBlock(blocks.planks.getDefaultState(), Block.Settings.copy(Blocks.OAK_STAIRS)));
			blocks.fence = TerrestriaBlocks.register(name + "_fence", new FenceBlock(Block.Settings.copy(Blocks.OAK_FENCE)));
			blocks.fenceGate = TerrestriaBlocks.register(name + "_fence_gate", new FenceGateBlock(Block.Settings.copy(Blocks.OAK_FENCE_GATE)));
			blocks.door = TerrestriaBlocks.register(name + "_door", new CustomDoorBlock(Block.Settings.copy(Blocks.OAK_FENCE_GATE)));

			blocks.addManufacturedFireInfo(registry);

			return blocks;
		}

		public void addTreeFireInfo(FlammableBlockRegistry registry) {
			registry.add(log, 5, 5);

			if(wood != log) {
				registry.add(wood, 5, 5);
			}

			registry.add(leaves, 30, 60);
		}

		public void addManufacturedFireInfo(FlammableBlockRegistry registry) {
			registry.add(planks, 5, 20);
			registry.add(slab, 5, 20);
			registry.add(stairs, 5, 20);
			registry.add(fence, 5, 20);
			registry.add(fenceGate, 5, 20);
		}

		public TreeDefinition.Basic getBasicDefinition() {
			TreeDefinition.Basic basic = new TreeDefinition.Basic();

			basic.leaves = this.leaves.getDefaultState();
			basic.wood = this.log.getDefaultState();

			return basic;
		}
	}
}
