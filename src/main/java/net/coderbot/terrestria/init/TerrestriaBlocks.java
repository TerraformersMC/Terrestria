package net.coderbot.terrestria.init;

import io.github.terraformersmc.terraform.block.*;
import io.github.terraformersmc.terraform.util.TerraformLargeSaplingGenerator;
import io.github.terraformersmc.terraform.util.TerraformSaplingGenerator;
import net.coderbot.terrestria.Terrestria;
import net.coderbot.terrestria.block.BasaltFlowerBlock;
import net.coderbot.terrestria.feature.TreeDefinition;
import net.coderbot.terrestria.init.helpers.TerrestriaRegistry;
import net.coderbot.terrestria.init.helpers.WoodBlocks;
import net.coderbot.terrestria.init.helpers.WoodColors;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.JungleTreeFeature;

import java.util.function.Supplier;

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
	public static WoodBlocks PALM;

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

		REDWOOD = WoodBlocks.register("redwood", WoodColors.REDWOOD, flammable, true);
		HEMLOCK = WoodBlocks.register("hemlock", WoodColors.HEMLOCK, flammable, true);
		RUBBER = WoodBlocks.register("rubber", WoodColors.RUBBER, flammable);
		CYPRESS = WoodBlocks.register("cypress", WoodColors.CYPRESS, flammable);
		WILLOW = WoodBlocks.register("willow", WoodColors.WILLOW, flammable);
		JAPANESE_MAPLE = WoodBlocks.register("japanese_maple", WoodColors.JAPANESE_MAPLE, flammable);
		RAINBOW_EUCALYPTUS = WoodBlocks.register("rainbow_eucalyptus", WoodColors.RAINBOW_EUCALYPTUS, flammable);
		PALM = WoodBlocks.register("palm", WoodColors.CYPRESS, flammable);
		SAKURA = WoodBlocks.register("sakura", WoodColors.SAKURA, flammable, WoodBlocks.LogSize.SMALL);

		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.registerBlock("japanese_maple_shrub_leaves", new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES)));

		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.registerBlock("dark_japanese_maple_leaves",
				new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).materialColor(MaterialColor.RED_TERRACOTTA).build())
		);

		SAKURA_LEAF_PILE = TerrestriaRegistry.registerBlock("sakura_leaf_pile", new LeafPileBlock(Block.Settings.copy(SAKURA.leaves).noCollision()));

		flammable.add(JAPANESE_MAPLE_SHRUB_LEAVES, 30, 60);
		flammable.add(SAKURA_LEAF_PILE, 30, 60);

		TALL_CATTAIL = TerrestriaRegistry.registerBlock("tall_cattail", new TallCattailBlock(() -> TerrestriaItems.CATTAIL, Block.Settings.copy(Blocks.SEAGRASS)));
		CATTAIL = TerrestriaRegistry.registerBlock("cattail", new TerraformSeagrassBlock(TALL_CATTAIL, Block.Settings.copy(Blocks.SEAGRASS)));

		REDWOOD_QUARTER_LOG = REDWOOD.registerQuarterLog(() -> STRIPPED_REDWOOD_QUARTER_LOG, flammable);
		HEMLOCK_QUARTER_LOG = HEMLOCK.registerQuarterLog(() -> STRIPPED_HEMLOCK_QUARTER_LOG, flammable);
		CYPRESS_QUARTER_LOG = CYPRESS.registerQuarterLog(() -> STRIPPED_CYPRESS_QUARTER_LOG, flammable);
		RAINBOW_EUCALYPTUS_QUARTER_LOG = RAINBOW_EUCALYPTUS.registerQuarterLog(() -> STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG, flammable);
		STRIPPED_REDWOOD_QUARTER_LOG = REDWOOD.registerStrippedQuarterLog(flammable);
		STRIPPED_HEMLOCK_QUARTER_LOG = HEMLOCK.registerStrippedQuarterLog(flammable);
		STRIPPED_CYPRESS_QUARTER_LOG = CYPRESS.registerStrippedQuarterLog(flammable);
		STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG = RAINBOW_EUCALYPTUS.registerStrippedQuarterLog(flammable);

		// Saplings

		REDWOOD_SAPLING = TerrestriaRegistry.registerBlock("redwood_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.REDWOOD_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_REDWOOD_TREE.sapling()
				)
		));

		HEMLOCK_SAPLING = TerrestriaRegistry.registerBlock("hemlock_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.HEMLOCK_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_HEMLOCK_TREE.sapling()
				)
		));

		RUBBER_SAPLING = TerrestriaRegistry.registerBlock("rubber_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(
						() -> TerrestriaFeatures.RUBBER_TREE.sapling()
				)
		));

		CYPRESS_SAPLING = TerrestriaRegistry.registerBlock("cypress_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> TerrestriaFeatures.CYPRESS_TREE.sapling(),
						() -> TerrestriaFeatures.MEGA_CYPRESS_TREE.sapling()
				)
		));

		WILLOW_SAPLING = TerrestriaRegistry.registerBlock("willow_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.WILLOW_TREE.sapling())
		));

		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlock("japanese_maple_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JAPANESE_MAPLE_TREE.sapling())
		));

		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.registerBlock("japanese_maple_shrub_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.JAPANESE_MAPLE_SHRUB.sapling())
		));

		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlock("dark_japanese_maple_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE.sapling())
		));

		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.registerBlock("rainbow_eucalyptus_sapling", new TerraformSaplingBlock(
				new TerraformLargeSaplingGenerator(
						() -> new JungleTreeFeature(DefaultFeatureConfig::deserialize, true, 5,
								TerrestriaBlocks.RAINBOW_EUCALYPTUS.log.getDefaultState(),
								TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves.getDefaultState(),
								true
						),
						() -> TerrestriaFeatures.RAINBOW_EUCALYPTUS_TREE.sapling()
				)
		));

		SAKURA_SAPLING = TerrestriaRegistry.registerBlock("sakura_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.SAKURA_TREE.sapling())
		));

		PALM_SAPLING = TerrestriaRegistry.registerBlock("palm_sapling", new TerraformSaplingBlock(
				new TerraformSaplingGenerator(() -> TerrestriaFeatures.PALM_TREE.sapling())
		));

		// Volcanic Island Blocks

		BASALT_SAND = TerrestriaRegistry.registerBlock("basalt_sand", new SandBlock(0x202020, FabricBlockSettings.copy(Blocks.SAND).materialColor(MaterialColor.BLACK).build()));
		BASALT_DIRT = TerrestriaRegistry.registerBlock("basalt_dirt", new Block(FabricBlockSettings.copy(Blocks.DIRT).materialColor(MaterialColor.BLACK).build()));
		BASALT_GRASS_BLOCK = TerrestriaRegistry.registerBlock("basalt_grass_block", new TerraformGrassBlock(BASALT_DIRT, Block.Settings.copy(Blocks.GRASS_BLOCK)));
		BASALT = TerrestriaRegistry.registerBlock("basalt", new Block(FabricBlockSettings.copy(Blocks.STONE).materialColor(MaterialColor.BLACK).build()));

		INDIAN_PAINTBRUSH = TerrestriaRegistry.registerBlock("indian_paintbrush", new BasaltFlowerBlock(StatusEffects.SATURATION, 6, Block.Settings.copy(Blocks.POPPY)));
		MONSTERAS = TerrestriaRegistry.registerBlock("monsteras", new BasaltFlowerBlock(StatusEffects.REGENERATION, 2, Block.Settings.copy(Blocks.POPPY)));

		POTTED_INDIAN_PAINTBRUSH = TerrestriaRegistry.registerBlock("potted_indian_paintbrush", new FlowerPotBlock(INDIAN_PAINTBRUSH, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_MONSTERAS = TerrestriaRegistry.registerBlock("potted_monsteras", new FlowerPotBlock(MONSTERAS, Block.Settings.copy(Blocks.POTTED_POPPY)));

		POTTED_REDWOOD_SAPLING = TerrestriaRegistry.registerBlock("potted_redwood_sapling", new FlowerPotBlock(REDWOOD_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_HEMLOCK_SAPLING = TerrestriaRegistry.registerBlock("potted_hemlock_sapling", new FlowerPotBlock(HEMLOCK_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RUBBER_SAPLING = TerrestriaRegistry.registerBlock("potted_rubber_sapling", new FlowerPotBlock(RUBBER_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_CYPRESS_SAPLING = TerrestriaRegistry.registerBlock("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_WILLOW_SAPLING = TerrestriaRegistry.registerBlock("potted_willow_sapling", new FlowerPotBlock(WILLOW_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlock("potted_japanese_maple_sapling", new FlowerPotBlock(JAPANESE_MAPLE_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.registerBlock("potted_japanese_maple_shrub_sapling", new FlowerPotBlock(JAPANESE_MAPLE_SHRUB_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlock("potted_dark_japanese_maple_sapling", new FlowerPotBlock(DARK_JAPANESE_MAPLE_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.registerBlock("potted_rainbow_eucalyptus_sapling", new FlowerPotBlock(RAINBOW_EUCALYPTUS_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_SAKURA_SAPLING = TerrestriaRegistry.registerBlock("potted_sakura_sapling", new FlowerPotBlock(SAKURA_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		POTTED_PALM_SAPLING = TerrestriaRegistry.registerBlock("potted_palm_sapling", new FlowerPotBlock(PALM_SAPLING, Block.Settings.copy(Blocks.POTTED_POPPY)));
		// TODO: Stripped Logs, Stripped Wood
	}
}
