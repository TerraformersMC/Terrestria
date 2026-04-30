package com.terraformersmc.terrestria.item;

import com.terraformersmc.terraform.dirt.api.DirtBlocks;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

public class TerrestriaCreativeModeTabs {
	private static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "items"));
	private static final HashMap<ResourceKey<CreativeModeTab>, HashMap<ItemLike, CreativeModeTabEntries>> CREATIVE_MODE_TAB_MAPS;

	/*
	 * These items are the last Vanilla item of a "similar" type to items we add to Vanilla tabs.
	 * Each is used to build a collection of items which will be inserted below the Vanilla item.
	 */
	private static final Item BUILDING_STONE_ITEMS = Items.MOSSY_STONE_BRICK_WALL;
	private static final Item BUILDING_WOOD_ITEMS = Items.PALE_OAK_BUTTON;
	private static final Item FUNCTIONAL_SHELF = Items.PALE_OAK_SHELF;
	private static final Item FUNCTIONAL_SIGN = Items.PALE_OAK_HANGING_SIGN;
	private static final Item NATURAL_CACTUS = Items.CACTUS;
	private static final Item NATURAL_DIRT_ITEMS = Items.FARMLAND;
	private static final Item NATURAL_LEAVES = Items.FLOWERING_AZALEA_LEAVES;
	private static final Item NATURAL_LOG = Items.PALE_OAK_LOG;
	private static final Item NATURAL_SAPLING = Items.PALE_OAK_SAPLING;
	private static final Item NATURAL_SAND = Items.RED_SANDSTONE;
	private static final Item NATURAL_STONE = Items.STONE;
	private static final Item NATURAL_TALL_VEGETATION = Items.LARGE_FERN;
	private static final Item NATURAL_VEGETATION = Items.FERN;
	private static final Item TOOLS_BOAT = Items.PALE_OAK_CHEST_BOAT;

	static {
		CREATIVE_MODE_TAB_MAPS = new HashMap<>(8);

		/*
		 * For each Vanilla item group, add the same kinds of items Vanilla adds.
		 * Since Minecraft 1.19.3, items are often in multiple item groups...
		 */

		// BUILDING BLOCKS

		// Wood Items
		addTabEntry(TerrestriaBlocks.SMALL_OAK_LOG, CreativeModeTabs.BUILDING_BLOCKS, Items.OAK_WOOD);
		addTabEntry(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG, CreativeModeTabs.BUILDING_BLOCKS, Items.STRIPPED_OAK_WOOD);


		// NATURAL

		// Wood Items
		addTabEntry(TerrestriaBlocks.SMALL_OAK_LOG, CreativeModeTabs.NATURAL_BLOCKS, Items.OAK_LOG);

		// Sand and Sandstone
		addTabEntry(TerrestriaBlocks.VOLCANIC_SAND, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAND);

		// Leaves
		addTabEntry(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		addTabEntry(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		addTabEntry(TerrestriaBlocks.JUNGLE_PALM_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);

		// Saplings
		addTabEntry(TerrestriaBlocks.BRYCE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.CYPRESS_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.HEMLOCK_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.JUNGLE_PALM_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.REDWOOD_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.RUBBER_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.SAKURA_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.WILLOW_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addTabEntry(TerrestriaBlocks.YUCCA_PALM_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);

		// Cactuses
		addTabEntry(TerrestriaBlocks.SAGUARO_CACTUS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addTabEntry(TerrestriaBlocks.TINY_CACTUS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addTabEntry(TerrestriaBlocks.AGAVE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addTabEntry(TerrestriaBlocks.ALOE_VERA, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addTabEntry(TerrestriaBlocks.DEAD_GRASS, CreativeModeTabs.NATURAL_BLOCKS, Items.SHORT_GRASS);

		// Vegetation
		addTabEntry(TerrestriaBlocks.INDIAN_PAINTBRUSH, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_VEGETATION);
		addTabEntry(TerrestriaBlocks.MONSTERAS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_VEGETATION);

		// Tall Plants
		addTabEntry(TerrestriaBlocks.CATTAIL, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_TALL_VEGETATION);


		// FUNCTIONAL


		// REDSTONE


		// HOTBAR


		// SEARCH


		// TOOLS

		// Misc. Hand Tools
		addTabEntry(TerrestriaItems.LOG_TURNER, CreativeModeTabs.TOOLS_AND_UTILITIES, Items.FISHING_ROD);


		// COMBAT


		// CONSUMABLES


		// CRAFTING


		// SPAWN EGGS


		// INVENTORY


		// Add DirtBlocks
		addDirtEntries(TerrestriaBlocks.ANDISOL);

		// Add StoneItems
		addStoneEntries(TerrestriaItems.VOLCANIC_ROCK);

		// Add WoodItems
		addWoodEntries(TerrestriaItems.CYPRESS);
		addWoodEntries(TerrestriaItems.HEMLOCK);
		addWoodEntries(TerrestriaItems.JAPANESE_MAPLE);
		addWoodEntries(TerrestriaItems.RAINBOW_EUCALYPTUS);
		addWoodEntries(TerrestriaItems.REDWOOD);
		addWoodEntries(TerrestriaItems.RUBBER);
		addWoodEntries(TerrestriaItems.SAKURA);
		addWoodEntries(TerrestriaItems.WILLOW);
		addWoodEntries(TerrestriaItems.YUCCA_PALM);


		/*
		 * Add the items configured above to the Vanilla item groups.
		 */
		for (ResourceKey<CreativeModeTab> group : CREATIVE_MODE_TAB_MAPS.keySet()) {
			CreativeModeTabEvents.modifyOutputEvent(group).register((output) -> {
				FeatureFlagSet featureSet = output.getEnabledFeatures();
				HashMap<ItemLike, CreativeModeTabEntries> entryMap = CREATIVE_MODE_TAB_MAPS.get(group);

				for (ItemLike relative : entryMap.keySet()) {
					CreativeModeTabEntries entries = entryMap.get(relative);

					// FAPI does not give us a way to add at a feature-flag-disabled location.
					// So, below we have to adjust for any items which may be disabled.
					if (relative == null) {
						// Target the end of the Item Group
						output.acceptAll(entries.getStackCollection());
					} else {
						//Terrestria.LOGGER.warn("About to add to Vanilla Item Group '{}' after Item '{}': '{}'", group.getId(), relative, entries.getCollection().stream().map(ItemStack::getItem).collect(Collectors.toList()));
						output.insertAfter(relative, entries.getStackCollection());
					}
				}
			});
		}


		/*
		 * Also add all the items to Terrestria's own item group.
		 */
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB, FabricCreativeModeTab.builder()
				.title(Component.literal("Terrestria"))
				.icon(() -> TerrestriaBlocks.RUBBER_SAPLING.asItem().getDefaultInstance())
				.displayItems((context, output) ->
					CREATIVE_MODE_TAB_MAPS.values().stream()
							.map(HashMap::values).flatMap(Collection::stream)
							.flatMap(CreativeModeTabEntries::getItemStream).distinct()
							.sorted(Comparator.comparing(ItemLike::toString))
							.forEach(output::accept)
				).build()
		);
	}

	private static void addDirtEntries(DirtBlocks blocks) {
		// NATURAL

		// Dirt Items
		addTabEntry(blocks.grassBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addTabEntry(blocks.podzolBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addTabEntry(blocks.dirtPathBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addTabEntry(blocks.dirtBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addTabEntry(blocks.farmBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
	}

	private static void addStoneEntries(StoneItems items) {
		// BUILDING BLOCKS

		// Stone Items
		addTabEntry(items.plain.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.plain.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.plain.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.plain.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.pressurePlate, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.button, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.cobblestone.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.cobblestone.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.cobblestone.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.cobblestone.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyCobblestone.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyCobblestone.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyCobblestone.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyCobblestone.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.smooth.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.smooth.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.smooth.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.smooth.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.bricks.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.crackedBricks, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.bricks.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.bricks.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.bricks.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.chiseledBricks, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyBricks.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyBricks.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyBricks.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addTabEntry(items.mossyBricks.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);


		// NATURAL

		// Stone Items
		addTabEntry(items.plain.full, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_STONE);
	}

	private static void addWoodEntries(WoodItems items) {
		// BUILDING BLOCKS

		// Wood Items
		addTabEntry(items.log, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		if (items.hasQuarterLog()) {
			addTabEntry(items.quarterLog, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		if (items.hasWood()) {
			addTabEntry(items.wood, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		addTabEntry(items.strippedLog, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		if (items.hasQuarterLog()) {
			addTabEntry(items.strippedQuarterLog, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		if (items.hasWood()) {
			addTabEntry(items.strippedWood, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		addTabEntry(items.planks, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.fence, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.fenceGate, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.door, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.trapdoor, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.pressurePlate, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addTabEntry(items.button, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);


		// NATURAL

		// Wood Items
		addTabEntry(items.log, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LOG);
		if (items.hasQuarterLog()) {
			addTabEntry(items.quarterLog, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LOG);
			if (items.hasWood()) {
				// At the moment, wood generates naturally on all quartered trees...
				addTabEntry(items.wood, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LOG);
			}
		}

		// Leaves
		addTabEntry(items.leaves, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		if (items.hasLeafPile()) {
			addTabEntry(items.leafPile, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		}


		// FUNCTIONAL

		// Wood Items
		addTabEntry(items.shelf, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_SHELF);
		addTabEntry(items.sign, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_SIGN);
		addTabEntry(items.hangingSign, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_SIGN);


		// TOOLS

		// Boats
		if (items.hasBoat()) {
			addTabEntry(items.boat, CreativeModeTabs.TOOLS_AND_UTILITIES, TOOLS_BOAT);
			addTabEntry(items.chestBoat, CreativeModeTabs.TOOLS_AND_UTILITIES, TOOLS_BOAT);
		}
	}

	public static void addTabEntry(ItemLike item, ResourceKey<CreativeModeTab> group) {
		// Appends the item to the bottom of the group.
		addTabEntry(item, group, null);
	}

	public static void addTabEntry(ItemLike item, ResourceKey<CreativeModeTab> group, @Nullable ItemLike relative) {
		HashMap<ItemLike, CreativeModeTabEntries> entryMap = CREATIVE_MODE_TAB_MAPS.computeIfAbsent(group, (key) -> new HashMap<>(32));
		CreativeModeTabEntries entries = entryMap.computeIfAbsent(relative, CreativeModeTabEntries::empty);
		entries.addItem(item);
	}

	public static void init() { }
}
