package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terraform.dirt.api.block.TerraformPathBlock;
import com.terraformersmc.terraform.dirt.api.block.TerraformFarmlandBlock;
import com.terraformersmc.terraform.dirt.api.block.TerraformGrassBlock;
import com.terraformersmc.terraform.dirt.api.block.TerraformSnowyBlock;
import com.terraformersmc.terraform.dirt.api.registry.TerraformDirtRegistry;
import com.terraformersmc.terraform.leaves.api.block.ColoredParticleLeavesBlock;
import com.terraformersmc.terraform.tree.api.block.TerraformDesertSaplingBlock;
import com.terraformersmc.terraform.wood.api.block.BareSmallLogBlock;
import com.terraformersmc.terraform.wood.api.block.PillarLogHelper;
import com.terraformersmc.terraform.wood.api.block.SmallLogBlock;
import com.terraformersmc.terrestria.block.*;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodColors;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Objects;

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
	public static WoodBlocks YUCCA_PALM;

	public static SmallLogBlock SMALL_OAK_LOG;
	public static SmallLogBlock STRIPPED_SMALL_OAK_LOG;
	public static BareSmallLogBlock SAGUARO_CACTUS;

	public static Block STACKED_OAK_LOGS;
	public static Block STACKED_SAKURA_LOGS;
	public static Block STACKED_YUCCA_PALM_LOGS;
	public static Block STACKED_STRIPPED_OAK_LOGS;
	public static Block STACKED_STRIPPED_SAKURA_LOGS;
	public static Block STACKED_STRIPPED_YUCCA_PALM_LOGS;

	public static LeavesBlock JAPANESE_MAPLE_SHRUB_LEAVES;
	public static LeavesBlock DARK_JAPANESE_MAPLE_LEAVES;
	public static LeavesBlock JUNGLE_PALM_LEAVES;

	public static SeagrassBlock CATTAIL;
	public static TallSeagrassBlock TALL_CATTAIL;

	public static SaplingBlock BRYCE_SAPLING;
	public static SaplingBlock REDWOOD_SAPLING;
	public static SaplingBlock HEMLOCK_SAPLING;
	public static SaplingBlock RUBBER_SAPLING;
	public static SaplingBlock CYPRESS_SAPLING;
	public static SaplingBlock WILLOW_SAPLING;
	public static SaplingBlock JAPANESE_MAPLE_SAPLING;
	public static SaplingBlock JAPANESE_MAPLE_SHRUB_SAPLING;
	public static SaplingBlock DARK_JAPANESE_MAPLE_SAPLING;
	public static SaplingBlock RAINBOW_EUCALYPTUS_SAPLING;
	public static SaplingBlock SAKURA_SAPLING;
	public static SaplingBlock JUNGLE_PALM_SAPLING;
	public static SaplingBlock SAGUARO_CACTUS_SAPLING;
	public static SaplingBlock YUCCA_PALM_SAPLING;

	public static FlowerPotBlock POTTED_BRYCE_SAPLING;
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
	public static FlowerPotBlock POTTED_SAGUARO_CACTUS_SAPLING;
	public static FlowerPotBlock POTTED_YUCCA_PALM_SAPLING;

	// Volcanic Island blocks
	public static ColoredFallingBlock VOLCANIC_SAND;
	public static DirtBlocks ANDISOL;
	public static StoneBlocks VOLCANIC_ROCK;
	public static VegetationBlock INDIAN_PAINTBRUSH;
	public static VegetationBlock MONSTERAS;
	public static FlowerPotBlock POTTED_INDIAN_PAINTBRUSH;
	public static FlowerPotBlock POTTED_MONSTERAS;

	// Desert Plants
	public static VegetationBlock TINY_CACTUS;
	public static VegetationBlock AGAVE;
	public static VegetationBlock ALOE_VERA;
	public static VegetationBlock DEAD_GRASS;
	public static FlowerPotBlock POTTED_TINY_CACTUS;
	public static FlowerPotBlock POTTED_AGAVE;
	public static FlowerPotBlock POTTED_ALOE_VERA;

	public static void init() {
		// quartered mega
		CYPRESS = WoodBlocks.register(TerrestriaBlockIds.CYPRESS, WoodColors.CYPRESS, WoodBlocks.LogSize.NORMAL, false, true, false, true);
		HEMLOCK = WoodBlocks.register(TerrestriaBlockIds.HEMLOCK, WoodColors.HEMLOCK, WoodBlocks.LogSize.NORMAL, false, true, true, true);
		RAINBOW_EUCALYPTUS = WoodBlocks.register(TerrestriaBlockIds.RAINBOW_EUCALYPTUS, WoodColors.RAINBOW_EUCALYPTUS, WoodBlocks.LogSize.NORMAL, false, true, false, true);
		REDWOOD = WoodBlocks.register(TerrestriaBlockIds.REDWOOD, WoodColors.REDWOOD, WoodBlocks.LogSize.NORMAL, false, true, true, true);

		// normal trunk
		JAPANESE_MAPLE = WoodBlocks.register(TerrestriaBlockIds.JAPANESE_MAPLE, WoodColors.JAPANESE_MAPLE, WoodBlocks.LogSize.NORMAL, false, false, false, false);
		RUBBER = WoodBlocks.register(TerrestriaBlockIds.RUBBER, WoodColors.RUBBER);
		WILLOW = WoodBlocks.register(TerrestriaBlockIds.WILLOW, WoodColors.WILLOW);

		// small trunk
		SAKURA = WoodBlocks.register(TerrestriaBlockIds.SAKURA, WoodColors.SAKURA, WoodBlocks.LogSize.SMALL, true, false, false, false);
		YUCCA_PALM = WoodBlocks.register(TerrestriaBlockIds.YUCCA_PALM, WoodColors.YUCCA_PALM, WoodBlocks.LogSize.SMALL, false, false, false, false);

		SAGUARO_CACTUS = TerrestriaRegistry.register(TerrestriaBlockItemIds.SAGUARO_CACTUS, SaguaroCactusBlock::new, SaguaroCactusBlock.createSettings(Blocks.CACTUS.defaultMapColor()));
		SMALL_OAK_LOG = TerrestriaRegistry.register(TerrestriaBlockItemIds.SMALL_OAK_LOG, settings -> new SmallLogBlock(Blocks.OAK_LEAVES, settings), PillarLogHelper.createSmallLogProperties(Blocks.OAK_LEAVES, Blocks.STRIPPED_OAK_WOOD.defaultMapColor(), Blocks.OAK_WOOD.defaultMapColor()));
		STRIPPED_SMALL_OAK_LOG = TerrestriaRegistry.register(TerrestriaBlockItemIds.STRIPPED_SMALL_OAK_LOG, settings -> new SmallLogBlock(Blocks.OAK_LEAVES, settings), PillarLogHelper.createSmallLogProperties(Blocks.OAK_LEAVES, Blocks.STRIPPED_OAK_WOOD.defaultMapColor()));

		// stacked small logs
		STACKED_OAK_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_OAK_LOGS, RotatedPillarBlock::new, PillarLogHelper.createProperties(MapColor.WOOD, MapColor.PODZOL));
		STACKED_SAKURA_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_SAKURA_LOGS, RotatedPillarBlock::new, PillarLogHelper.createProperties(WoodColors.SAKURA.planks, WoodColors.SAKURA.bark));
		STACKED_YUCCA_PALM_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_YUCCA_PALM_LOGS, RotatedPillarBlock::new, PillarLogHelper.createProperties(WoodColors.YUCCA_PALM.planks, WoodColors.YUCCA_PALM.bark));
		STACKED_STRIPPED_OAK_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_STRIPPED_OAK_LOGS, RotatedPillarBlock::new, PillarLogHelper.createProperties(MapColor.WOOD));
		STACKED_STRIPPED_SAKURA_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_STRIPPED_SAKURA_LOGS, RotatedPillarBlock::new, PillarLogHelper.createProperties(WoodColors.SAKURA.planks));
		STACKED_STRIPPED_YUCCA_PALM_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_STRIPPED_YUCCA_PALM_LOGS, RotatedPillarBlock::new, PillarLogHelper.createProperties(WoodColors.YUCCA_PALM.planks));

		// strange leaves
		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.register(TerrestriaBlockItemIds.DARK_JAPANESE_MAPLE_LEAVES, settings -> new ColoredParticleLeavesBlock(0.01f, 0x351829, AmbientLeavesBlockSoundPlayer.noAmbientSound(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(MapColor.TERRACOTTA_RED).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));
		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.register(TerrestriaBlockItemIds.JAPANESE_MAPLE_SHRUB_LEAVES, settings -> new ColoredParticleLeavesBlock(0.01f, 0x4e001f, AmbientLeavesBlockSoundPlayer.noAmbientSound(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));
		JUNGLE_PALM_LEAVES = TerrestriaRegistry.register(TerrestriaBlockItemIds.JUNGLE_PALM_LEAVES, settings -> new ColoredParticleLeavesBlock(0.02f, 0x225723, AmbientLeavesBlockSoundPlayer.noAmbientSound(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).isValidSpawn(TerrestriaBlocks::canSpawnOnLeaves).isSuffocating(TerrestriaBlocks::never).isViewBlocking(TerrestriaBlocks::never));

		// swamp blocks
		CATTAIL = TerrestriaRegistry.register(TerrestriaBlockItemIds.CATTAIL, CattailBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SEAGRASS));
		TALL_CATTAIL = TerrestriaRegistry.register(TerrestriaBlockIds.TALL_CATTAIL, TallCattailBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_SEAGRASS));

		// Saplings
		BlockBehaviour.Properties saplingSettings = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED);

		BRYCE_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.BRYCE_SAPLING, settings -> new TerraformDesertSaplingBlock(new TreeGrower("bryce", WeightedList.of(TerrestriaFeatures.BRYCE_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.BRYCE_TREE), settings), saplingSettings);
		CYPRESS_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.CYPRESS_SAPLING, settings -> new SaplingBlock(new TreeGrower("cypress", WeightedList.of(TerrestriaFeatures.CYPRESS_TREE), WeightedList.of(TerrestriaFeatures.MEGA_CYPRESS_TREE), WeightedList.of(), TerrestriaFeatures.CYPRESS_TREE), settings), saplingSettings.mapColor(WoodColors.CYPRESS.leaves));
		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.DARK_JAPANESE_MAPLE_SAPLING, settings -> new SaplingBlock(new TreeGrower("dark_japanese_maple", WeightedList.of(TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.DARK_JAPANESE_MAPLE_TREE), settings), saplingSettings.mapColor(MapColor.TERRACOTTA_RED));
		HEMLOCK_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.HEMLOCK_SAPLING, settings -> new SaplingBlock(new TreeGrower("hemlock", WeightedList.of(TerrestriaFeatures.HEMLOCK_TREE), WeightedList.of(TerrestriaFeatures.MEGA_HEMLOCK_TREE), WeightedList.of(), TerrestriaFeatures.SMALL_HEMLOCK_TREE), settings), saplingSettings.mapColor(WoodColors.HEMLOCK.leaves));
		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.JAPANESE_MAPLE_SAPLING, settings -> new SaplingBlock(new TreeGrower("japanese_maple", WeightedList.of(TerrestriaFeatures.JAPANESE_MAPLE_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.JAPANESE_MAPLE_TREE), settings), saplingSettings.mapColor(WoodColors.JAPANESE_MAPLE.leaves));
		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.JAPANESE_MAPLE_SHRUB_SAPLING, settings -> new SaplingBlock(new TreeGrower("japanese_maple_shrub", WeightedList.of(TerrestriaFeatures.JAPANESE_MAPLE_SHRUB), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.JAPANESE_MAPLE_SHRUB), settings), saplingSettings);
		JUNGLE_PALM_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.JUNGLE_PALM_SAPLING, settings -> new TerraformDesertSaplingBlock(new TreeGrower("jungle_palm", WeightedList.of(TerrestriaFeatures.JUNGLE_PALM_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.JUNGLE_PALM_TREE), settings), saplingSettings);
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.RAINBOW_EUCALYPTUS_SAPLING, settings -> new SaplingBlock(new TreeGrower("rainbow_eucalyptus", WeightedList.of(TerrestriaFeatures.SMALL_RAINBOW_EUCALYPTUS_TREE), WeightedList.of(TerrestriaFeatures.RAINBOW_EUCALYPTUS_TREE), WeightedList.of(), TerrestriaFeatures.SMALL_RAINBOW_EUCALYPTUS_TREE), settings), saplingSettings.mapColor(WoodColors.RAINBOW_EUCALYPTUS.leaves));
		REDWOOD_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.REDWOOD_SAPLING, settings -> new SaplingBlock(new TreeGrower("redwood", WeightedList.of(TerrestriaFeatures.REDWOOD_TREE), WeightedList.of(TerrestriaFeatures.MEGA_REDWOOD_TREE), WeightedList.of(), TerrestriaFeatures.SMALL_REDWOOD_TREE), settings), saplingSettings.mapColor(WoodColors.REDWOOD.leaves));
		RUBBER_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.RUBBER_SAPLING, settings -> new SaplingBlock(new TreeGrower("rubber", WeightedList.of(TerrestriaFeatures.RUBBER_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.RUBBER_TREE), settings), saplingSettings.mapColor(WoodColors.RUBBER.leaves));
		SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.SAGUARO_CACTUS_SAPLING, settings -> new TerraformDesertSaplingBlock(new TreeGrower("saguaro_cactus", WeightedList.of(TerrestriaFeatures.SAGUARO_CACTUS), WeightedList.of(), WeightedList.of(), null), true, settings), saplingSettings.mapColor(Blocks.CACTUS.defaultMapColor()));
		SAKURA_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.SAKURA_SAPLING, settings -> new SaplingBlock(new TreeGrower("sakura", WeightedList.of(TerrestriaFeatures.SAKURA_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.SAKURA_TREE), settings), saplingSettings.mapColor(WoodColors.SAKURA.leaves));
		WILLOW_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.WILLOW_SAPLING, settings -> new TerraformDesertSaplingBlock(new TreeGrower("willow", WeightedList.of(TerrestriaFeatures.WILLOW_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.WILLOW_TREE), settings), saplingSettings.mapColor(WoodColors.WILLOW.leaves));
		YUCCA_PALM_SAPLING = TerrestriaRegistry.register(TerrestriaBlockItemIds.YUCCA_PALM_SAPLING, settings -> new TerraformDesertSaplingBlock(new TreeGrower("yucca_palm", WeightedList.of(TerrestriaFeatures.YUCCA_PALM_TREE), WeightedList.of(), WeightedList.of(), TerrestriaFeatures.YUCCA_PALM_TREE), settings), saplingSettings.mapColor(WoodColors.YUCCA_PALM.leaves));

		// Volcanic Island blocks

		Block andisol = TerrestriaRegistry.register(TerrestriaBlockItemIds.ANDISOL.dirtBlock(), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).mapColor(MapColor.COLOR_BLACK));
		TerraformPathBlock andisolPath = TerrestriaRegistry.register(TerrestriaBlockItemIds.ANDISOL.dirtPathBlock(), settings -> new TerraformPathBlock(andisol, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH));
		TerraformFarmlandBlock andisolFarmland = TerrestriaRegistry.register(TerrestriaBlockItemIds.ANDISOL.farmBlock(), settings -> new TerraformFarmlandBlock(andisol, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.FARMLAND).mapColor(MapColor.COLOR_BLACK));
		TerraformGrassBlock andisolGrassBlock = TerrestriaRegistry.register(TerrestriaBlockItemIds.ANDISOL.grassBlock(), settings -> new BasaltGrassBlock(andisol, () -> Objects.requireNonNull(ANDISOL.pathBlock()), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK));
		TerraformSnowyBlock andisolPodzol = TerrestriaRegistry.register(TerrestriaBlockItemIds.ANDISOL.podzolBlock(), TerraformSnowyBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL));
		ANDISOL = TerraformDirtRegistry.register(new DirtBlocks(andisol, null, andisolFarmland, andisolGrassBlock, null, andisolPath, andisolPodzol, null));

		VOLCANIC_ROCK = StoneBlocks.register(TerrestriaBlockIds.VOLCANIC_ROCK, MapColor.COLOR_BLACK);
		VOLCANIC_SAND = TerrestriaRegistry.register(TerrestriaBlockItemIds.VOLCANIC_SAND, settings -> new ColoredFallingBlock(new ColorRGBA(0x202020), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(MapColor.COLOR_BLACK));

		INDIAN_PAINTBRUSH = TerrestriaRegistry.register(TerrestriaBlockItemIds.INDIAN_PAINTBRUSH, settings -> new BasaltFlowerBlock(MobEffects.SATURATION, 4, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).mapColor(MapColor.TERRACOTTA_ORANGE));
		MONSTERAS = TerrestriaRegistry.register(TerrestriaBlockItemIds.MONSTERAS, settings -> new BasaltFlowerBlock(MobEffects.REGENERATION, 2, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.FERN).mapColor(MapColor.COLOR_GREEN));

		POTTED_INDIAN_PAINTBRUSH = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_INDIAN_PAINTBRUSH, settings -> new FlowerPotBlock(INDIAN_PAINTBRUSH, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY));
		POTTED_MONSTERAS = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_MONSTERAS, settings -> new FlowerPotBlock(MONSTERAS, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_FERN));

		// potted saplings
		POTTED_BRYCE_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_BRYCE_SAPLING, settings -> new FlowerPotBlock(BRYCE_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_REDWOOD_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_REDWOOD_SAPLING, settings -> new FlowerPotBlock(REDWOOD_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_HEMLOCK_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_HEMLOCK_SAPLING, settings -> new FlowerPotBlock(HEMLOCK_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_RUBBER_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_RUBBER_SAPLING, settings -> new FlowerPotBlock(RUBBER_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_CYPRESS_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_CYPRESS_SAPLING, settings -> new FlowerPotBlock(CYPRESS_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_WILLOW_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_WILLOW_SAPLING, settings -> new FlowerPotBlock(WILLOW_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_JAPANESE_MAPLE_SAPLING, settings -> new FlowerPotBlock(JAPANESE_MAPLE_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING, settings -> new FlowerPotBlock(JAPANESE_MAPLE_SHRUB_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_DARK_JAPANESE_MAPLE_SAPLING, settings -> new FlowerPotBlock(DARK_JAPANESE_MAPLE_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_RAINBOW_EUCALYPTUS_SAPLING, settings -> new FlowerPotBlock(RAINBOW_EUCALYPTUS_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_SAKURA_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_SAKURA_SAPLING, settings -> new FlowerPotBlock(SAKURA_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_JUNGLE_PALM_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_JUNGLE_PALM_SAPLING, settings -> new FlowerPotBlock(JUNGLE_PALM_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_SAGUARO_CACTUS_SAPLING, settings -> new FlowerPotBlock(SAGUARO_CACTUS_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));
		POTTED_YUCCA_PALM_SAPLING = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_YUCCA_PALM_SAPLING, settings -> new FlowerPotBlock(YUCCA_PALM_SAPLING, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING));

		// desert plants
		TINY_CACTUS = TerrestriaRegistry.register(TerrestriaBlockItemIds.TINY_CACTUS, settings -> new PricklyDesertPlantBlock(true, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH).mapColor(MapColor.GRASS));
		AGAVE = TerrestriaRegistry.register(TerrestriaBlockItemIds.AGAVE, TerraformDesertPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).mapColor(MapColor.GRASS));
		ALOE_VERA = TerrestriaRegistry.register(TerrestriaBlockItemIds.ALOE_VERA, TerraformDesertPlantBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).mapColor(MapColor.GRASS));
		DEAD_GRASS = TerrestriaRegistry.register(TerrestriaBlockItemIds.DEAD_GRASS, settings -> new TerraformDesertPlantBlock(true, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).mapColor(MapColor.TERRACOTTA_BROWN));

		POTTED_TINY_CACTUS = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_TINY_CACTUS, settings -> new FlowerPotBlock(TINY_CACTUS, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY));
		POTTED_AGAVE = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_AGAVE, settings -> new FlowerPotBlock(AGAVE, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY));
		POTTED_ALOE_VERA = TerrestriaRegistry.register(TerrestriaBlockIds.POTTED_ALOE_VERA, settings -> new FlowerPotBlock(ALOE_VERA, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY));

		addFlammables();
		addFlattenables();
		addStrippables();
	}

	private static void addFlammables() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();

		flammableRegistry.add(SMALL_OAK_LOG, 5, 5);
		flammableRegistry.add(STRIPPED_SMALL_OAK_LOG, 5, 5);

		flammableRegistry.add(STACKED_OAK_LOGS, 5, 5);
		flammableRegistry.add(STACKED_SAKURA_LOGS, 5, 5);
		flammableRegistry.add(STACKED_YUCCA_PALM_LOGS, 5, 5);
		flammableRegistry.add(STACKED_STRIPPED_OAK_LOGS, 5, 5);
		flammableRegistry.add(STACKED_STRIPPED_SAKURA_LOGS, 5, 5);
		flammableRegistry.add(STACKED_STRIPPED_YUCCA_PALM_LOGS, 5, 5);

		flammableRegistry.add(JAPANESE_MAPLE_SHRUB_LEAVES, 30, 60);
		flammableRegistry.add(DARK_JAPANESE_MAPLE_LEAVES, 30, 60);
		flammableRegistry.add(JUNGLE_PALM_LEAVES, 30, 60);

		flammableRegistry.add(DEAD_GRASS, 30, 60);
	}

	private static void addFlattenables() {
		if (ANDISOL.pathBlock() != null) {
			BlockTransformerHelper.registerFlattening(ANDISOL.dirtBlock(), ANDISOL.pathBlock().defaultBlockState());
			if (ANDISOL.grassBlock() != null) {
				BlockTransformerHelper.registerFlattening(ANDISOL.grassBlock(), ANDISOL.pathBlock().defaultBlockState());
			}
			if (ANDISOL.podzolBlock() != null) {
				BlockTransformerHelper.registerFlattening(ANDISOL.podzolBlock(), ANDISOL.pathBlock().defaultBlockState());
			}
		}
	}

	private static void addStrippables() {
		BlockTransformerHelper.registerStripping(SMALL_OAK_LOG, STRIPPED_SMALL_OAK_LOG);
	}

	// BlockPredicate always returning false
	public static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
		return false;
	}

	// BlockArgumentPredicate always returning false
	public static <A> boolean never(BlockState state, BlockGetter level, BlockPos pos, A a) {
		return false;
	}

	public static Boolean canSpawnOnLeaves(BlockState state, BlockGetter world, BlockPos pos, EntityType<?> type) {
		return type == EntityTypes.OCELOT || type == EntityTypes.PARROT;
	}
}
