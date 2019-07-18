package net.coderbot.terrestria.init;

import io.github.terraformersmc.terraform.block.*;
import io.github.terraformersmc.terraform.util.TerraformLargeSaplingGenerator;
import io.github.terraformersmc.terraform.util.TerraformSaplingGenerator;
import net.coderbot.terrestria.Terrestria;
import net.coderbot.terrestria.block.BasaltFlowerBlock;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.JungleTreeFeature;

// This class exports public block constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaBlocks {
	public static WoodBlocks REDWOOD;
	public static WoodBlocks HEMLOCK;
	public static WoodBlocks RUBBER;
	public static WoodBlocks CYPRESS;
	public static WoodBlocks WILLOW;
	public static WoodBlocks JAPANESE_MAPLE;
	public static WoodBlocks RAINBOW_EUCALYPTUS;
	public static WoodBlocks SAKURA;

	public static LeavesBlock JAPANESE_MAPLE_SHRUB_LEAVES;
	public static LeavesBlock DARK_JAPANESE_MAPLE_LEAVES;
	public static LeafPileBlock SAKURA_LEAF_PILE;

	public static SeagrassBlock CATTAIL;
	public static TallSeagrassBlock TALL_CATTAIL;

	public static QuarterLogBlock REDWOOD_QUARTER_LOG;
	public static QuarterLogBlock HEMLOCK_QUARTER_LOG;
	public static QuarterLogBlock CYPRESS_QUARTER_LOG;
	public static QuarterLogBlock RAINBOW_EUCALYPTUS_QUARTER_LOG;
	public static QuarterLogBlock STRIPPED_REDWOOD_QUARTER_LOG;
	public static QuarterLogBlock STRIPPED_HEMLOCK_QUARTER_LOG;
	public static QuarterLogBlock STRIPPED_CYPRESS_QUARTER_LOG;
	public static QuarterLogBlock STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG;

	public static TerraformSaplingBlock REDWOOD_SAPLING;
	public static TerraformSaplingBlock HEMLOCK_SAPLING;
	public static TerraformSaplingBlock RUBBER_SAPLING;
	public static TerraformSaplingBlock CYPRESS_SAPLING;
	public static TerraformSaplingBlock WILLOW_SAPLING;
	public static TerraformSaplingBlock JAPANESE_MAPLE_SAPLING;
	public static TerraformSaplingBlock JAPANESE_MAPLE_SHRUB_SAPLING;
	public static TerraformSaplingBlock DARK_JAPANESE_MAPLE_SAPLING;
	public static TerraformSaplingBlock RAINBOW_EUCALYPTUS_SAPLING;
	public static TerraformSaplingBlock SAKURA_SAPLING;
	public static TerraformSaplingBlock PALM_SAPLING;

	public static FlowerPotBlock POTTED_REDWOOD_SAPLING;
	public static FlowerPotBlock POTTED_HEMLOCK_SAPLING;
	public static FlowerPotBlock POTTED_RUBBER_SAPLING;
	public static FlowerPotBlock POTTED_CYPRESS_SAPLING;
	public static FlowerPotBlock POTTED_WILLOW_SAPLING;
	public static FlowerPotBlock POTTED_JAPANESE_MAPLE_SAPLING;
	public static FlowerPotBlock POTTED_JAPANESE_MAPLE_SHRUB_SAPLING;
	public static FlowerPotBlock POTTED_DARK_JAPANESE_MAPLE_SAPLING;
	public static FlowerPotBlock POTTED_RAINBOW_EUCALYPTUS_SAPLING;
	public static FlowerPotBlock POTTED_SAKURA_SAPLING;
	public static FlowerPotBlock POTTED_PALM_SAPLING;

	// Volcanic Island blocks
	public static SandBlock BASALT_SAND;
	public static Block BASALT_DIRT;
	public static Block BASALT_GRASS_BLOCK;
	public static Block BASALT;
	public static PlantBlock INDIAN_PAINTBRUSH;
	public static PlantBlock MONSTERAS;
	public static FlowerPotBlock POTTED_INDIAN_PAINTBRUSH;
	public static FlowerPotBlock POTTED_MONSTERAS;

	public static void init() {
		FlammableBlockRegistry flammable = FlammableBlockRegistry.getDefaultInstance();

		REDWOOD = WoodBlocks.registerExtendedLeaves("redwood", WoodColors.REDWOOD, flammable);
		HEMLOCK = WoodBlocks.registerExtendedLeaves("hemlock", WoodColors.HEMLOCK, flammable);
		RUBBER = WoodBlocks.register("rubber", WoodColors.RUBBER, flammable);
		CYPRESS = WoodBlocks.register("cypress", WoodColors.CYPRESS, flammable);
		WILLOW = WoodBlocks.register("willow", WoodColors.WILLOW, flammable);
		JAPANESE_MAPLE = WoodBlocks.register("japanese_maple", WoodColors.JAPANESE_MAPLE, flammable);
		RAINBOW_EUCALYPTUS = WoodBlocks.register("rainbow_eucalyptus", WoodColors.RAINBOW_EUCALYPTUS, flammable);

		SAKURA = WoodBlocks.registerManufactured("sakura", WoodColors.SAKURA, flammable);
		SAKURA.leaves = register("sakura_leaves", new TransparentLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(WoodColors.SAKURA.leaves).build()));
		SAKURA.log = register("sakura_log", new SmallLogBlock(SAKURA.leaves, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(WoodColors.SAKURA.bark).build()));
		SAKURA.strippedLog = register("stripped_sakura_log", new SmallLogBlock(SAKURA.leaves, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(WoodColors.SAKURA.planks).build()));
		SAKURA.wood = SAKURA.log;
		SAKURA.strippedWood = SAKURA.strippedLog;
		SAKURA.addTreeFireInfo(flammable);

		JAPANESE_MAPLE_SHRUB_LEAVES = register("japanese_maple_shrub_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));

		DARK_JAPANESE_MAPLE_LEAVES = register("dark_japanese_maple_leaves",
				new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(MaterialColor.RED_TERRACOTTA).build())
		);

		SAKURA_LEAF_PILE = register("sakura_leaf_pile", new LeafPileBlock(Block.Settings.copy(SAKURA.leaves).noCollision()));

		flammable.add(JAPANESE_MAPLE_SHRUB_LEAVES, 30, 60);
		flammable.add(SAKURA_LEAF_PILE, 30, 60);

		TALL_CATTAIL = register("tall_cattail", new TallCattailBlock(() -> TerrestriaItems.CATTAIL, Block.Settings.copy(Blocks.SEAGRASS)));
		CATTAIL = register("cattail", new TerraformSeagrassBlock(TALL_CATTAIL, Block.Settings.copy(Blocks.SEAGRASS)));

		REDWOOD_QUARTER_LOG = REDWOOD.registerQuarterLog(flammable);
		HEMLOCK_QUARTER_LOG = HEMLOCK.registerQuarterLog(flammable);
		CYPRESS_QUARTER_LOG = CYPRESS.registerQuarterLog(flammable);
		RAINBOW_EUCALYPTUS_QUARTER_LOG = RAINBOW_EUCALYPTUS.registerQuarterLog(flammable);
		STRIPPED_REDWOOD_QUARTER_LOG = REDWOOD.registerStrippedQuarterLog(flammable);
		STRIPPED_HEMLOCK_QUARTER_LOG = HEMLOCK.registerStrippedQuarterLog(flammable);
		STRIPPED_CYPRESS_QUARTER_LOG = CYPRESS.registerStrippedQuarterLog(flammable);
		STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG = RAINBOW_EUCALYPTUS.registerStrippedQuarterLog(flammable);

		// Saplings

		REDWOOD_SAPLING = register("redwood_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.REDWOOD_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_REDWOOD_TREE.sapling()
				)
		));

		HEMLOCK_SAPLING = register("hemlock_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.HEMLOCK_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_HEMLOCK_TREE.sapling()
				)
		));

		RUBBER_SAPLING = register("rubber_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(
						() -> TerrestriaFeatures.RUBBER_TREE.sapling()
				)
		));

		CYPRESS_SAPLING = register("cypress_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.CYPRESS_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_CYPRESS_TREE.sapling()
				)
		));

		WILLOW_SAPLING = register("willow_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.WILLOW_TREE.sapling())
		));

		JAPANESE_MAPLE_SAPLING = register("japanese_maple_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JAPANESE_MAPLE_TREE.sapling())
		));

		JAPANESE_MAPLE_SHRUB_SAPLING = register("japanese_maple_shrub_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JAPANESE_MAPLE_SHRUB.sapling())
		));

		DARK_JAPANESE_MAPLE_SAPLING = register("dark_japanese_maple_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE.sapling())
		));

		RAINBOW_EUCALYPTUS_SAPLING = register("rainbow_eucalyptus_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> new JungleTreeFeature(DefaultFeatureConfig::deserialize, true, 5,
								TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState(),
								TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState(),
								true
						),
						() -> TerrestriaFeatures.RAINBOW_EUCALYPTUS_TREE.sapling()
				)
		));

		SAKURA_SAPLING = register("sakura_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.SAKURA_TREE.sapling())
		));

		PALM_SAPLING = register("palm_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.PALM_TREE.sapling())
		));

		// Volcanic Island Blocks

		BASALT_SAND = register("basalt_sand", new SandBlock(0x202020, FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.BLACK).build()));
		BASALT_DIRT = register("basalt_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.BLACK).build()));
		BASALT_GRASS_BLOCK = register("basalt_grass_block", new TerraformGrassBlock(BASALT_DIRT, Block.Settings.copy(Blocks.GRASS_BLOCK)));
		BASALT = register("basalt", new Block(FabricBlockSettings.copy(Blocks.STONE).materialColor(MaterialColor.BLACK).build()));

		INDIAN_PAINTBRUSH = register("indian_paintbrush", new BasaltFlowerBlock(StatusEffects.SATURATION, 6, Block.Settings.copy(Blocks.POPPY)));
		MONSTERAS = register("monsteras", new BasaltFlowerBlock(StatusEffects.REGENERATION, 2, Block.Settings.copy(Blocks.POPPY)));

		POTTED_INDIAN_PAINTBRUSH = register("potted_indian_paintbrush", new FlowerPotBlock(INDIAN_PAINTBRUSH, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_MONSTERAS = register("potted_monsteras", new FlowerPotBlock(MONSTERAS, Block.Settings.copy(Blocks.POTTED_POPPY)));

		POTTED_REDWOOD_SAPLING = register("potted_redwood_sapling", new FlowerPotBlock(REDWOOD_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_HEMLOCK_SAPLING = register("potted_hemlock_sapling", new FlowerPotBlock(HEMLOCK_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RUBBER_SAPLING = register("potted_rubber_sapling", new FlowerPotBlock(RUBBER_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_CYPRESS_SAPLING = register("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_WILLOW_SAPLING = register("potted_willow_sapling", new FlowerPotBlock(WILLOW_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JAPANESE_MAPLE_SAPLING = register("potted_japanese_maple_sapling", new FlowerPotBlock(JAPANESE_MAPLE_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JAPANESE_MAPLE_SHRUB_SAPLING = register("potted_japanese_maple_shrub_sapling", new FlowerPotBlock(JAPANESE_MAPLE_SHRUB_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_DARK_JAPANESE_MAPLE_SAPLING = register("potted_dark_japanese_maple_sapling", new FlowerPotBlock(DARK_JAPANESE_MAPLE_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RAINBOW_EUCALYPTUS_SAPLING = register("potted_rainbow_eucalyptus_sapling", new FlowerPotBlock(RAINBOW_EUCALYPTUS_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_SAKURA_SAPLING = register("potted_sakura_sapling", new FlowerPotBlock(SAKURA_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_PALM_SAPLING = register("potted_palm_sapling", new FlowerPotBlock(PALM_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		// TODO: Stripped Logs, Stripped Wood
	}

	public static <T extends Block> T register(String name, T block) {
		return Registry.register(Registry.BLOCK, new Identifier(Terrestria.MOD_ID, name), block);
	}

	public static class WoodColors {
		public static final WoodColors REDWOOD;
		public static final WoodColors HEMLOCK;
		public static final WoodColors RUBBER;
		public static final WoodColors CYPRESS;
		public static final WoodColors WILLOW;
		public static final WoodColors JAPANESE_MAPLE;
		public static final WoodColors RAINBOW_EUCALYPTUS;
		public static final WoodColors SAKURA;

		static {
			REDWOOD = new WoodColors();
			REDWOOD.bark = MaterialColor.RED_TERRACOTTA;
			REDWOOD.planks = MaterialColor.WOOD;

			HEMLOCK = new WoodColors();
			HEMLOCK.bark = MaterialColor.BROWN;
			HEMLOCK.planks = MaterialColor.WOOD;

			RUBBER = new WoodColors();
			RUBBER.bark = MaterialColor.WHITE_TERRACOTTA;
			RUBBER.planks = MaterialColor.SAND;

			CYPRESS = new WoodColors();
			CYPRESS.bark = MaterialColor.WHITE_TERRACOTTA;
			CYPRESS.planks = MaterialColor.LIGHT_GRAY;

			WILLOW = new WoodColors();
			WILLOW.bark = MaterialColor.WHITE_TERRACOTTA;
			WILLOW.planks = MaterialColor.GRAY;
			WILLOW.leaves = MaterialColor.LIGHT_GRAY;

			JAPANESE_MAPLE = new WoodColors();
			JAPANESE_MAPLE.bark = MaterialColor.BROWN;
			JAPANESE_MAPLE.planks = MaterialColor.MAGENTA_TERRACOTTA;
			JAPANESE_MAPLE.leaves = MaterialColor.RED;

			RAINBOW_EUCALYPTUS = new WoodColors();
			RAINBOW_EUCALYPTUS.bark = MaterialColor.BLUE;
			RAINBOW_EUCALYPTUS.planks = MaterialColor.LAPIS;

			SAKURA = new WoodColors();
			SAKURA.bark = MaterialColor.SPRUCE;
			SAKURA.planks = MaterialColor.BROWN;
			SAKURA.leaves = MaterialColor.PINK;
		}

		public MaterialColor bark;
		public MaterialColor planks;
		public MaterialColor leaves = MaterialColor.FOLIAGE;
	}

	public static class WoodBlocks {
		public Block log;
		public Block wood;
		public Block leaves;
		public Block planks;
		public SlabBlock slab;
		public TerraformStairsBlock stairs;
		public FenceBlock fence;
		public FenceGateBlock fenceGate;
		public TerraformDoorBlock door;
		public TerraformButtonBlock button;
		public TerraformPressurePlateBlock pressurePlate;
		public TerraformSignBlock sign;
		public TerraformWallSignBlock wallSign;
		public Block strippedLog;
		public Block strippedWood;
		private String name;
		private WoodColors colors;

		private WoodBlocks() {
		}

		public static WoodBlocks register(String name, WoodColors colors, FlammableBlockRegistry registry) {
			WoodBlocks blocks = registerManufactured(name, colors, registry);

			blocks.log = TerrestriaBlocks.register(name + "_log", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
			blocks.wood = TerrestriaBlocks.register(name + "_wood", new LogBlock(colors.bark, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
			blocks.leaves = TerrestriaBlocks.register(name + "_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(colors.leaves).build()));
			blocks.strippedLog = TerrestriaBlocks.register("stripped_" + name + "_log", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));
			blocks.strippedWood = TerrestriaBlocks.register("stripped_" + name + "_wood", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));

			blocks.addTreeFireInfo(registry);

			return blocks;
		}

		public static WoodBlocks registerExtendedLeaves(String name, WoodColors colors, FlammableBlockRegistry registry) {
			WoodBlocks blocks = registerManufactured(name, colors, registry);

			blocks.log = TerrestriaBlocks.register(name + "_log", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
			blocks.wood = TerrestriaBlocks.register(name + "_wood", new LogBlock(colors.bark, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.bark).build()));
			blocks.leaves = TerrestriaBlocks.register(name + "_leaves", new ExtendedLeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(colors.leaves).build()));
			blocks.strippedLog = TerrestriaBlocks.register("stripped_" + name + "_log", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));
			blocks.strippedWood = TerrestriaBlocks.register("stripped_" + name + "_wood", new LogBlock(colors.planks, FabricBlockSettings.copy(Blocks.OAK_LOG).materialColor(colors.planks).build()));

			blocks.addTreeFireInfo(registry);

			return blocks;
		}

		public static WoodBlocks registerManufactured(String name, WoodColors colors, FlammableBlockRegistry registry) {
			WoodBlocks blocks = new WoodBlocks();
			blocks.name = name;
			blocks.colors = colors;

			blocks.planks = TerrestriaBlocks.register(name + "_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).materialColor(colors.planks).build()));
			blocks.slab = TerrestriaBlocks.register(name + "_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB).materialColor(colors.planks).build()));
			blocks.stairs = TerrestriaBlocks.register(name + "_stairs", new TerraformStairsBlock(blocks.planks, FabricBlockSettings.copy(Blocks.OAK_STAIRS).materialColor(colors.planks).build()));
			blocks.fence = TerrestriaBlocks.register(name + "_fence", new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE).materialColor(colors.planks).build()));
			blocks.fenceGate = TerrestriaBlocks.register(name + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).materialColor(colors.planks).build()));
			blocks.door = TerrestriaBlocks.register(name + "_door", new TerraformDoorBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE).materialColor(colors.planks).build()));
			blocks.button = TerrestriaBlocks.register(name + "_button", new TerraformButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON).materialColor(colors.planks).build()));
			blocks.pressurePlate = TerrestriaBlocks.register(name + "_pressure_plate", new TerraformPressurePlateBlock(FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE).materialColor(colors.planks).build()));

			Identifier signTexture = new Identifier(Terrestria.MOD_ID, "textures/entity/signs/" + name + ".png");

			blocks.sign = TerrestriaBlocks.register(name + "_sign", new TerraformSignBlock(signTexture, FabricBlockSettings.copy(Blocks.OAK_SIGN).materialColor(colors.planks).build()));
			blocks.wallSign = TerrestriaBlocks.register(name + "_wall_sign", new TerraformWallSignBlock(signTexture, FabricBlockSettings.copy(Blocks.OAK_SIGN).materialColor(colors.planks).build()));

			blocks.addManufacturedFireInfo(registry);

			return blocks;
		}

		public QuarterLogBlock registerQuarterLog(FlammableBlockRegistry registry) {
			QuarterLogBlock quarterLog = TerrestriaBlocks.register(name + "_quarter_log", new QuarterLogBlock(colors.planks, Block.Settings.copy(log)));

			registry.add(quarterLog, 5, 5);

			return quarterLog;
		}

		public QuarterLogBlock registerStrippedQuarterLog(FlammableBlockRegistry registry) {
			QuarterLogBlock quarterLog = TerrestriaBlocks.register("stripped_" + name + "_quarter_log", new QuarterLogBlock(colors.planks, Block.Settings.copy(strippedLog)));

			registry.add(quarterLog, 5, 5);

			return quarterLog;
		}

		public void addTreeFireInfo(FlammableBlockRegistry registry) {
			registry.add(log, 5, 5);
			registry.add(strippedLog, 5, 5);

			if (wood != log) {
				registry.add(wood, 5, 5);
			}

			if (strippedWood != strippedLog) {
				registry.add(strippedWood, 5, 5);
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
			return new TreeDefinition.Basic(this.log.getDefaultState(), this.leaves.getDefaultState());
		}
	}
}
