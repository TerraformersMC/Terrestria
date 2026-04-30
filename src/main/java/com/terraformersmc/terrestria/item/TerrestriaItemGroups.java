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

public class TerrestriaItemGroups {
	private static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "items"));
	private static final HashMap<ResourceKey<CreativeModeTab>, HashMap<ItemLike, ItemGroupEntries>> ITEM_GROUP_ENTRY_MAPS;

	/*
	 * These items are the last Vanilla item of a "similar" type to items we add to Vanilla groups.
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
		ITEM_GROUP_ENTRY_MAPS = new HashMap<>(8);

		/*
		 * For each Vanilla item group, add the same kinds of items Vanilla adds.
		 * Since Minecraft 1.19.3, items are often in multiple item groups...
		 */

		// BUILDING BLOCKS

		// Wood Items
		addGroupEntry(TerrestriaBlocks.SMALL_OAK_LOG, CreativeModeTabs.BUILDING_BLOCKS, Items.OAK_WOOD);
		addGroupEntry(TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG, CreativeModeTabs.BUILDING_BLOCKS, Items.STRIPPED_OAK_WOOD);


		// NATURAL

		// Wood Items
		addGroupEntry(TerrestriaBlocks.SMALL_OAK_LOG, CreativeModeTabs.NATURAL_BLOCKS, Items.OAK_LOG);

		// Sand and Sandstone
		addGroupEntry(TerrestriaBlocks.VOLCANIC_SAND, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAND);

		// Leaves
		addGroupEntry(TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		addGroupEntry(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		addGroupEntry(TerrestriaBlocks.JUNGLE_PALM_LEAVES, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);

		// Saplings
		addGroupEntry(TerrestriaBlocks.BRYCE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.CYPRESS_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.HEMLOCK_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.JAPANESE_MAPLE_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.JUNGLE_PALM_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.REDWOOD_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.RUBBER_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.SAGUARO_CACTUS_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.SAKURA_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.WILLOW_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);
		addGroupEntry(TerrestriaBlocks.YUCCA_PALM_SAPLING, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_SAPLING);

		// Cactuses
		addGroupEntry(TerrestriaBlocks.SAGUARO_CACTUS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addGroupEntry(TerrestriaBlocks.TINY_CACTUS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addGroupEntry(TerrestriaBlocks.AGAVE, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addGroupEntry(TerrestriaBlocks.ALOE_VERA, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_CACTUS);
		addGroupEntry(TerrestriaBlocks.DEAD_GRASS, CreativeModeTabs.NATURAL_BLOCKS, Items.SHORT_GRASS);

		// Vegetation
		addGroupEntry(TerrestriaBlocks.INDIAN_PAINTBRUSH, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_VEGETATION);
		addGroupEntry(TerrestriaBlocks.MONSTERAS, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_VEGETATION);

		// Tall Plants
		addGroupEntry(TerrestriaBlocks.CATTAIL, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_TALL_VEGETATION);


		// FUNCTIONAL


		// REDSTONE


		// HOTBAR


		// SEARCH


		// TOOLS

		// Misc. Hand Tools
		addGroupEntry(TerrestriaItems.LOG_TURNER, CreativeModeTabs.TOOLS_AND_UTILITIES, Items.FISHING_ROD);


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
		for (ResourceKey<CreativeModeTab> group : ITEM_GROUP_ENTRY_MAPS.keySet()) {
			CreativeModeTabEvents.modifyOutputEvent(group).register((output) -> {
				FeatureFlagSet featureSet = output.getEnabledFeatures();
				HashMap<ItemLike, ItemGroupEntries> entryMap = ITEM_GROUP_ENTRY_MAPS.get(group);

				for (ItemLike relative : entryMap.keySet()) {
					ItemGroupEntries entries = entryMap.get(relative);

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
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricCreativeModeTab.builder()
				.title(Component.literal("Terrestria"))
				.icon(() -> TerrestriaBlocks.RUBBER_SAPLING.asItem().getDefaultInstance())
				.displayItems((context, output) ->
					ITEM_GROUP_ENTRY_MAPS.values().stream()
							.map(HashMap::values).flatMap(Collection::stream)
							.flatMap(ItemGroupEntries::getItemStream).distinct()
							.sorted(Comparator.comparing(ItemLike::toString))
							.forEach(output::accept)
				).build()
		);
	}

	private static void addDirtEntries(DirtBlocks blocks) {
		// NATURAL

		// Dirt Items
		addGroupEntry(blocks.grassBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.podzolBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.dirtPathBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.dirtBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
		addGroupEntry(blocks.farmBlock(), CreativeModeTabs.NATURAL_BLOCKS, NATURAL_DIRT_ITEMS);
	}

	private static void addStoneEntries(StoneItems items) {
		// BUILDING BLOCKS

		// Stone Items
		addGroupEntry(items.plain.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.plain.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.plain.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.plain.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.pressurePlate, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.button, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.cobblestone.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyCobblestone.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.smooth.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.crackedBricks, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.bricks.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.chiseledBricks, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.full, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);
		addGroupEntry(items.mossyBricks.wall, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_STONE_ITEMS);


		// NATURAL

		// Stone Items
		addGroupEntry(items.plain.full, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_STONE);
	}

	private static void addWoodEntries(WoodItems items) {
		// BUILDING BLOCKS

		// Wood Items
		addGroupEntry(items.log, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		if (items.hasQuarterLog()) {
			addGroupEntry(items.quarterLog, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		if (items.hasWood()) {
			addGroupEntry(items.wood, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		addGroupEntry(items.strippedLog, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		if (items.hasQuarterLog()) {
			addGroupEntry(items.strippedQuarterLog, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		if (items.hasWood()) {
			addGroupEntry(items.strippedWood, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		}
		addGroupEntry(items.planks, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.stairs, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.slab, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.fence, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.fenceGate, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.door, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.trapdoor, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.pressurePlate, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);
		addGroupEntry(items.button, CreativeModeTabs.BUILDING_BLOCKS, BUILDING_WOOD_ITEMS);


		// NATURAL

		// Wood Items
		addGroupEntry(items.log, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LOG);
		if (items.hasQuarterLog()) {
			addGroupEntry(items.quarterLog, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LOG);
			if (items.hasWood()) {
				// At the moment, wood generates naturally on all quartered trees...
				addGroupEntry(items.wood, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LOG);
			}
		}

		// Leaves
		addGroupEntry(items.leaves, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		if (items.hasLeafPile()) {
			addGroupEntry(items.leafPile, CreativeModeTabs.NATURAL_BLOCKS, NATURAL_LEAVES);
		}


		// FUNCTIONAL

		// Wood Items
		addGroupEntry(items.shelf, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_SHELF);
		addGroupEntry(items.sign, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_SIGN);
		addGroupEntry(items.hangingSign, CreativeModeTabs.FUNCTIONAL_BLOCKS, FUNCTIONAL_SIGN);


		// TOOLS

		// Boats
		if (items.hasBoat()) {
			addGroupEntry(items.boat, CreativeModeTabs.TOOLS_AND_UTILITIES, TOOLS_BOAT);
			addGroupEntry(items.chestBoat, CreativeModeTabs.TOOLS_AND_UTILITIES, TOOLS_BOAT);
		}
	}

	public static void addGroupEntry(ItemLike item, ResourceKey<CreativeModeTab> group) {
		// Appends the item to the bottom of the group.
		addGroupEntry(item, group, null);
	}

	public static void addGroupEntry(ItemLike item, ResourceKey<CreativeModeTab> group, @Nullable ItemLike relative) {
		HashMap<ItemLike, ItemGroupEntries> entryMap = ITEM_GROUP_ENTRY_MAPS.computeIfAbsent(group, (key) -> new HashMap<>(32));
		ItemGroupEntries entries = entryMap.computeIfAbsent(relative, ItemGroupEntries::empty);
		entries.addItem(item);
	}

	public static void init() { }
}
