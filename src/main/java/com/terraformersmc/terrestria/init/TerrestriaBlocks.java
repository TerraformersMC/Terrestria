package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.block.BasaltFlowerBlock;
import com.terraformersmc.terraform.block.*;
import com.terraformersmc.terraform.util.TerraformLargeSaplingGenerator;
import com.terraformersmc.terraform.util.TerraformSaplingGenerator;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodColors;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
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
	public static LeavesBlock JUNGLE_PALM_LEAVES;
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
	public static TerraformSaplingBlock JUNGLE_PALM_SAPLING;

	public static FlowerPotBlock POTTED_REDWOOD_SAPLING;
	public static FlowerPotBlock POTTED_HEMLOCK_SAPLING;
	public static FlowerPotBlock POTTED_JUNGLE_PALM_SAPLING;
	public static FlowerPotBlock POTTED_RUBBER_SAPLING;
	public static FlowerPotBlock POTTED_CYPRESS_SAPLING;
	public static FlowerPotBlock POTTED_WILLOW_SAPLING;
	public static FlowerPotBlock POTTED_JAPANESE_MAPLE_SAPLING;
	public static FlowerPotBlock POTTED_JAPANESE_MAPLE_SHRUB_SAPLING;
	public static FlowerPotBlock POTTED_DARK_JAPANESE_MAPLE_SAPLING;
	public static FlowerPotBlock POTTED_RAINBOW_EUCALYPTUS_SAPLING;
	public static FlowerPotBlock POTTED_SAKURA_SAPLING;

	// Volcanic Island blocks
	public static SandBlock BASALT_SAND;
	public static Block BASALT_DIRT;
	public static Block BASALT_GRASS_BLOCK;
	public static Block BASALT_GRASS_PATH;
	public static StoneBlocks BASALT;
	public static PlantBlock INDIAN_PAINTBRUSH;
	public static PlantBlock MONSTERAS;
	public static FlowerPotBlock POTTED_INDIAN_PAINTBRUSH;
	public static FlowerPotBlock POTTED_MONSTERAS;

	public static void init() {
		FlammableBlockRegistry flammable = FlammableBlockRegistry.getDefaultInstance();

		REDWOOD = WoodBlocks.register("redwood", WoodColors.REDWOOD, flammable, true);
		HEMLOCK = WoodBlocks.register("hemlock", WoodColors.HEMLOCK, flammable, true);
		RUBBER = WoodBlocks.register("rubber", WoodColors.RUBBER, flammable);
		CYPRESS = WoodBlocks.register("cypress", WoodColors.CYPRESS, flammable);
		WILLOW = WoodBlocks.register("willow", WoodColors.WILLOW, flammable);
		JAPANESE_MAPLE = WoodBlocks.register("japanese_maple", WoodColors.JAPANESE_MAPLE, flammable);
		RAINBOW_EUCALYPTUS = WoodBlocks.register("rainbow_eucalyptus", WoodColors.RAINBOW_EUCALYPTUS, flammable);
		SAKURA = WoodBlocks.register("sakura", WoodColors.SAKURA, flammable, WoodBlocks.LogSize.SMALL);

		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.register("japanese_maple_shrub_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));

		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.register("dark_japanese_maple_leaves",
				new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(MaterialColor.RED_TERRACOTTA).build())
		);

		JUNGLE_PALM_LEAVES = TerrestriaRegistry.register("jungle_palm_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));

		SAKURA_LEAF_PILE = TerrestriaRegistry.register("sakura_leaf_pile", new LeafPileBlock(Block.Settings.copy(SAKURA.leaves).noCollision()));

		flammable.add(JAPANESE_MAPLE_SHRUB_LEAVES, 30, 60);
		flammable.add(DARK_JAPANESE_MAPLE_LEAVES, 30, 60);
		flammable.add(JUNGLE_PALM_LEAVES, 30, 60);
		flammable.add(SAKURA_LEAF_PILE, 30, 60);

		TALL_CATTAIL = TerrestriaRegistry.register("tall_cattail", new TallCattailBlock(() -> TerrestriaItems.CATTAIL, Block.Settings.copy(Blocks.SEAGRASS)));
		CATTAIL = TerrestriaRegistry.register("cattail", new TerraformSeagrassBlock(TALL_CATTAIL, Block.Settings.copy(Blocks.SEAGRASS)));

		REDWOOD_QUARTER_LOG = REDWOOD.registerQuarterLog(() -> STRIPPED_REDWOOD_QUARTER_LOG, flammable);
		HEMLOCK_QUARTER_LOG = HEMLOCK.registerQuarterLog(() -> STRIPPED_HEMLOCK_QUARTER_LOG, flammable);
		CYPRESS_QUARTER_LOG = CYPRESS.registerQuarterLog(() -> STRIPPED_CYPRESS_QUARTER_LOG, flammable);
		RAINBOW_EUCALYPTUS_QUARTER_LOG = RAINBOW_EUCALYPTUS.registerQuarterLog(() -> STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG, flammable);
		STRIPPED_REDWOOD_QUARTER_LOG = REDWOOD.registerStrippedQuarterLog(flammable);
		STRIPPED_HEMLOCK_QUARTER_LOG = HEMLOCK.registerStrippedQuarterLog(flammable);
		STRIPPED_CYPRESS_QUARTER_LOG = CYPRESS.registerStrippedQuarterLog(flammable);
		STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG = RAINBOW_EUCALYPTUS.registerStrippedQuarterLog(flammable);

		// Saplings

		REDWOOD_SAPLING = TerrestriaRegistry.register("redwood_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.REDWOOD_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_REDWOOD_TREE.sapling()
				)
		));

		HEMLOCK_SAPLING = TerrestriaRegistry.register("hemlock_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.HEMLOCK_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_HEMLOCK_TREE.sapling()
				)
		));

		RUBBER_SAPLING = TerrestriaRegistry.register("rubber_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(
						() -> TerrestriaFeatures.RUBBER_TREE.sapling()
				)
		));

		CYPRESS_SAPLING = TerrestriaRegistry.register("cypress_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.CYPRESS_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_CYPRESS_TREE.sapling()
				)
		));

		WILLOW_SAPLING = TerrestriaRegistry.register("willow_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.WILLOW_TREE.sapling())
		));

		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register("japanese_maple_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JAPANESE_MAPLE_TREE.sapling())
		));

		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.register("japanese_maple_shrub_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JAPANESE_MAPLE_SHRUB.sapling())
		));

		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register("dark_japanese_maple_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE.sapling())
		));

		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register("rainbow_eucalyptus_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> new JungleTreeFeature(DefaultFeatureConfig::deserialize, true, 5,
								TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState(),
								TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState(),
								true
						),
						() -> TerrestriaFeatures.RAINBOW_EUCALYPTUS_TREE.sapling()
				)
		));

		SAKURA_SAPLING = TerrestriaRegistry.register("sakura_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.SAKURA_TREE.sapling())
		));

		JUNGLE_PALM_SAPLING = TerrestriaRegistry.register("jungle_palm_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JUNGLE_PALM_TREE.sapling())
		));

		// Volcanic Island Blocks

		BASALT_SAND = TerrestriaRegistry.register("basalt_sand", new SandBlock(0x202020, FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.BLACK).breakByTool(FabricToolTags.SHOVELS, 0).build()));
		BASALT_DIRT = TerrestriaRegistry.register("basalt_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.BLACK).breakByTool(FabricToolTags.SHOVELS, 0).build()));
		BASALT_GRASS_BLOCK = TerrestriaRegistry.register("basalt_grass_block", new TerraformGrassBlock(BASALT_DIRT, () -> BASALT_GRASS_PATH, FabricBlockSettings.copy(Blocks.GRASS_BLOCK).breakByTool(FabricToolTags.SHOVELS, 0).build()));
		BASALT_GRASS_PATH = TerrestriaRegistry.register("basalt_grass_path", new TerraformGrassPathBlock(BASALT_DIRT, FabricBlockSettings.copy(Blocks.GRASS_PATH).breakByTool(FabricToolTags.SHOVELS, 0).build()));
		BASALT = StoneBlocks.register("basalt", MaterialColor.BLACK);

		INDIAN_PAINTBRUSH = TerrestriaRegistry.register("indian_paintbrush", new BasaltFlowerBlock(StatusEffects.SATURATION, 6, Block.Settings.copy(Blocks.POPPY)));
		MONSTERAS = TerrestriaRegistry.register("monsteras", new BasaltFlowerBlock(StatusEffects.REGENERATION, 2, Block.Settings.copy(Blocks.POPPY)));

		POTTED_INDIAN_PAINTBRUSH = TerrestriaRegistry.register("potted_indian_paintbrush", new FlowerPotBlock(INDIAN_PAINTBRUSH, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_MONSTERAS = TerrestriaRegistry.register("potted_monsteras", new FlowerPotBlock(MONSTERAS, Block.Settings.copy(Blocks.POTTED_POPPY)));

		POTTED_REDWOOD_SAPLING = TerrestriaRegistry.register("potted_redwood_sapling", new FlowerPotBlock(REDWOOD_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_HEMLOCK_SAPLING = TerrestriaRegistry.register("potted_hemlock_sapling", new FlowerPotBlock(HEMLOCK_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RUBBER_SAPLING = TerrestriaRegistry.register("potted_rubber_sapling", new FlowerPotBlock(RUBBER_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_CYPRESS_SAPLING = TerrestriaRegistry.register("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_WILLOW_SAPLING = TerrestriaRegistry.register("potted_willow_sapling", new FlowerPotBlock(WILLOW_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register("potted_japanese_maple_sapling", new FlowerPotBlock(JAPANESE_MAPLE_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.register("potted_japanese_maple_shrub_sapling", new FlowerPotBlock(JAPANESE_MAPLE_SHRUB_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register("potted_dark_japanese_maple_sapling", new FlowerPotBlock(DARK_JAPANESE_MAPLE_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register("potted_rainbow_eucalyptus_sapling", new FlowerPotBlock(RAINBOW_EUCALYPTUS_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_SAKURA_SAPLING = TerrestriaRegistry.register("potted_sakura_sapling", new FlowerPotBlock(SAKURA_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JUNGLE_PALM_SAPLING = TerrestriaRegistry.register("potted_jungle_palm_sapling", new FlowerPotBlock(JUNGLE_PALM_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
	}
}
