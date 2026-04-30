package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.helpers.StoneBlocks;
import com.terraformersmc.terrestria.init.helpers.WoodBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@NullMarked
public class TerrestriaBlockLootTableProvider extends FabricBlockLootSubProvider {
	protected TerrestriaBlockLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate() {
		HolderLookup.RegistryLookup<Enchantment> enchantmentRegistry = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

		// simple blocks
		dropSelf(TerrestriaBlocks.AGAVE);
		dropSelf(TerrestriaBlocks.ALOE_VERA);
		dropSelf(TerrestriaBlocks.VOLCANIC_SAND);
		dropSelf(TerrestriaBlocks.BRYCE_SAPLING);
		add(TerrestriaBlocks.CATTAIL, this::createShearsOnlyDrop);
		dropSelf(TerrestriaBlocks.CYPRESS_SAPLING);
		dropSelf(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING);
		dropSelf(TerrestriaBlocks.DEAD_GRASS);
		dropSelf(TerrestriaBlocks.HEMLOCK_SAPLING);
		dropSelf(TerrestriaBlocks.INDIAN_PAINTBRUSH);
		dropSelf(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		dropSelf(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		dropSelf(TerrestriaBlocks.JUNGLE_PALM_SAPLING);
		add(TerrestriaBlocks.MONSTERAS, this::createGrassDrops);
		dropSelf(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		dropSelf(TerrestriaBlocks.REDWOOD_SAPLING);
		dropSelf(TerrestriaBlocks.RUBBER_SAPLING);
		dropSelf(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING);
		dropSelf(TerrestriaBlocks.SAKURA_SAPLING);
		dropSelf(TerrestriaBlocks.SMALL_OAK_LOG);
		dropSelf(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);
		add(TerrestriaBlocks.TALL_CATTAIL, createShearsOnlyDrop(TerrestriaBlocks.CATTAIL));
		dropSelf(TerrestriaBlocks.TINY_CACTUS);
		dropSelf(TerrestriaBlocks.WILLOW_SAPLING);
		dropSelf(TerrestriaBlocks.YUCCA_PALM_SAPLING);

		// dirt blocks
		addDirtDrops(TerrestriaBlocks.ANDISOL);

		// stone building blocks
		addStoneDrops(TerrestriaBlocks.VOLCANIC_ROCK);

		// wood building blocks
		addWoodDrops(TerrestriaBlocks.CYPRESS, TerrestriaBlocks.CYPRESS_SAPLING);
		addWoodDrops(TerrestriaBlocks.HEMLOCK, TerrestriaBlocks.HEMLOCK_SAPLING);
		addWoodDrops(TerrestriaBlocks.JAPANESE_MAPLE, TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		addWoodDrops(TerrestriaBlocks.RAINBOW_EUCALYPTUS, TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		addWoodDrops(TerrestriaBlocks.REDWOOD, TerrestriaBlocks.REDWOOD_SAPLING);
		addWoodDrops(TerrestriaBlocks.RUBBER, TerrestriaBlocks.RUBBER_SAPLING);
		addWoodDrops(TerrestriaBlocks.SAKURA, TerrestriaBlocks.SAKURA_SAPLING);
		addWoodDrops(TerrestriaBlocks.WILLOW, TerrestriaBlocks.WILLOW_SAPLING);
		addWoodDrops(TerrestriaBlocks.YUCCA_PALM, null);

		// potted things
		dropPottedContents(TerrestriaBlocks.POTTED_AGAVE);
		dropPottedContents(TerrestriaBlocks.POTTED_ALOE_VERA);
		dropPottedContents(TerrestriaBlocks.POTTED_BRYCE_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_CYPRESS_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_HEMLOCK_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH);
		dropPottedContents(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_MONSTERAS);
		dropPottedContents(TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_REDWOOD_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_RUBBER_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_SAKURA_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_TINY_CACTUS);
		dropPottedContents(TerrestriaBlocks.POTTED_WILLOW_SAPLING);
		dropPottedContents(TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING);

		// specialty tree leaves
		add(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, createLeavesDrops(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		add(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, createLeavesDrops(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, 0.05f, 0.0625f, 0.083333336f, 0.1f));
		add(TerrestriaBlocks.JUNGLE_PALM_LEAVES, createLeavesDrops(TerrestriaBlocks.JUNGLE_PALM_LEAVES, TerrestriaBlocks.JUNGLE_PALM_SAPLING, 0.07f, 0.0875f, 0.116666667f, 0.14f));
		add(TerrestriaBlocks.YUCCA_PALM.leaves, createLeavesDrops(TerrestriaBlocks.YUCCA_PALM.leaves, TerrestriaBlocks.YUCCA_PALM_SAPLING, 0.15f, 0.1875f, 0.24f, 0.333333333f));

		// even more specialty leaf-like drop thingy
		add(TerrestriaBlocks.SAGUARO_CACTUS,
				createSilkTouchDispatchTable(TerrestriaBlocks.SAGUARO_CACTUS,
						applyExplosionCondition(TerrestriaBlocks.SAGUARO_CACTUS,
								LootItem.lootTableItem(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING))
								.when(BonusLevelTableCondition.bonusLevelFlatChance(enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 0.2f, 0.24285715f, 0.5f, 2.0f))));
	}

	private void addDirtDrops(DirtBlocks dirtBlock) {
		dropSelf(dirtBlock.dirtBlock());
		if (dirtBlock.dirtPathBlock() != null) {
			dropOther(dirtBlock.dirtPathBlock(), dirtBlock.dirtBlock());
		}
		if (dirtBlock.farmBlock() != null) {
			dropOther(dirtBlock.farmBlock(), dirtBlock.dirtBlock());
		}
		if (dirtBlock.grassBlock() != null) {
			add(dirtBlock.grassBlock(), block -> createSingleItemTableWithSilkTouch(block, dirtBlock.dirtBlock()));
		}
		/* TODO: When mycelium support is added to DirtBlocks...
		if (dirtBlock.myceliumBlock() != null) {
			add(dirtBlock.myceliumBlock(), block -> createSingleItemTableWithSilkTouch(block, dirtBlock.dirtBlock()));
		}
		*/
		if (dirtBlock.podzolBlock() != null) {
			add(dirtBlock.podzolBlock(), block -> createSingleItemTableWithSilkTouch(block, dirtBlock.dirtBlock()));
		}
	}

	private void addStoneDrops(StoneBlocks stoneBlock) {
		if (stoneBlock.bricks != null) {
			dropSelf(stoneBlock.bricks.full);
			add(stoneBlock.bricks.slab, this::createSlabItemTable);
			dropSelf(stoneBlock.bricks.stairs);
			dropSelf(stoneBlock.bricks.wall);

			dropSelf(stoneBlock.chiseledBricks);
			dropSelf(stoneBlock.crackedBricks);
		}
		if (stoneBlock.cobblestone != null) {
			dropSelf(stoneBlock.cobblestone.full);
			add(stoneBlock.cobblestone.slab, this::createSlabItemTable);
			dropSelf(stoneBlock.cobblestone.stairs);
			dropSelf(stoneBlock.cobblestone.wall);
		}
		if (stoneBlock.mossyBricks != null) {
			dropSelf(stoneBlock.mossyBricks.full);
			add(stoneBlock.mossyBricks.slab, this::createSlabItemTable);
			dropSelf(stoneBlock.mossyBricks.stairs);
			dropSelf(stoneBlock.mossyBricks.wall);
		}
		if (stoneBlock.mossyCobblestone != null) {
			dropSelf(stoneBlock.mossyCobblestone.full);
			add(stoneBlock.mossyCobblestone.slab, this::createSlabItemTable);
			dropSelf(stoneBlock.mossyCobblestone.stairs);
			dropSelf(stoneBlock.mossyCobblestone.wall);
		}
		if (stoneBlock.plain != null) {
			if (stoneBlock.cobblestone != null) {
				add(stoneBlock.plain.full, createSingleItemTableWithSilkTouch(stoneBlock.plain.full, stoneBlock.cobblestone.full));
			} else {
				dropSelf(stoneBlock.plain.full);
			}
			add(stoneBlock.plain.slab, this::createSlabItemTable);
			dropSelf(stoneBlock.plain.stairs);
			dropSelf(stoneBlock.plain.wall);
		}
		if (stoneBlock.smooth != null) {
			dropSelf(stoneBlock.smooth.full);
			add(stoneBlock.smooth.slab, this::createSlabItemTable);
			dropSelf(stoneBlock.smooth.stairs);
			dropSelf(stoneBlock.smooth.wall);
		}

		dropSelf(stoneBlock.button);
		dropSelf(stoneBlock.pressurePlate);
	}

	private void addWoodDrops(WoodBlocks woodBlock, @Nullable SaplingBlock sapling) {
		dropSelf(woodBlock.button);
		add(woodBlock.door, this::createDoorTable);
		dropSelf(woodBlock.fence);
		dropSelf(woodBlock.fenceGate);
		dropSelf(woodBlock.hangingSign);
		dropSelf(woodBlock.log);
		dropSelf(woodBlock.planks);
		dropSelf(woodBlock.pressurePlate);
		dropSelf(woodBlock.shelf);
		dropSelf(woodBlock.sign);
		add(woodBlock.slab, this::createSlabItemTable);
		dropSelf(woodBlock.stairs);
		dropSelf(woodBlock.strippedLog);
		dropSelf(woodBlock.trapdoor);
		dropSelf(woodBlock.wallHangingSign);
		dropSelf(woodBlock.wallSign);

		if (woodBlock.hasWood()) {
			dropSelf(woodBlock.wood);
			dropSelf(woodBlock.strippedWood);
		}

		if (woodBlock.hasQuarterLog()) {
			dropSelf(woodBlock.quarterLog);
			dropSelf(woodBlock.strippedQuarterLog);
		}

		if (sapling != null) {
			add(woodBlock.leaves, createLeavesDrops(woodBlock.leaves, sapling, 0.05f, 0.0625f, 0.083333336f, 0.1f));
			if (woodBlock.hasLeafPile()) {
				add(woodBlock.leafPile, createLeavesDrops(woodBlock.leafPile, sapling, 0.00625f, 0.0078125f, 0.010416667f, 0.0125f));
			}
		}
	}

	@Override
	public String getName() {
		return "Terrestria Block Loot Tables";
	}
}
