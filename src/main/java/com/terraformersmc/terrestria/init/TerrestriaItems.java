package com.terraformersmc.terrestria.init;

import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.TerrestriaRegistry;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.item.LogTurnerItem;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

// This class exports public item constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaItems {
	public static WoodItems REDWOOD;
	public static WoodItems HEMLOCK;
	public static WoodItems RUBBER;
	public static WoodItems CYPRESS;
	public static WoodItems WILLOW;
	public static WoodItems JAPANESE_MAPLE;
	public static WoodItems RAINBOW_EUCALYPTUS;
	public static WoodItems SAKURA;
	public static WoodItems YUCCA_PALM;

	public static BlockItem SMALL_OAK_LOG;
	public static BlockItem STRIPPED_SMALL_OAK_LOG;
	public static BlockItem SAGUARO_CACTUS;

	public static BlockItem STACKED_OAK_LOGS;
	public static BlockItem STACKED_SAKURA_LOGS;
	public static BlockItem STACKED_YUCCA_PALM_LOGS;
	public static BlockItem STACKED_STRIPPED_OAK_LOGS;
	public static BlockItem STACKED_STRIPPED_SAKURA_LOGS;
	public static BlockItem STACKED_STRIPPED_YUCCA_PALM_LOGS;

	public static BlockItem JAPANESE_MAPLE_SHRUB_LEAVES;
	public static BlockItem DARK_JAPANESE_MAPLE_LEAVES;
	public static BlockItem JUNGLE_PALM_LEAVES;

	public static BlockItem CATTAIL;

	public static BlockItem BRYCE_SAPLING;
	public static BlockItem REDWOOD_SAPLING;
	public static BlockItem HEMLOCK_SAPLING;
	public static BlockItem RUBBER_SAPLING;
	public static BlockItem CYPRESS_SAPLING;
	public static BlockItem WILLOW_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SAPLING;
	public static BlockItem JAPANESE_MAPLE_SHRUB_SAPLING;
	public static BlockItem DARK_JAPANESE_MAPLE_SAPLING;
	public static BlockItem RAINBOW_EUCALYPTUS_SAPLING;
	public static BlockItem SAKURA_SAPLING;
	public static BlockItem JUNGLE_PALM_SAPLING;
	public static BlockItem SAGUARO_CACTUS_SAPLING;
	public static BlockItem YUCCA_PALM_SAPLING;

	public static StoneItems VOLCANIC_ROCK;
	public static BlockItem VOLCANIC_SAND;
	public static BlockItem ANDISOL;
	public static BlockItem ANDISOL_GRASS_BLOCK;
	public static BlockItem ANDISOL_DIRT_PATH;
	public static BlockItem ANDISOL_PODZOL;
	public static BlockItem ANDISOL_FARMLAND;
	public static BlockItem INDIAN_PAINTBRUSH;
	public static BlockItem MONSTERAS;

	public static BlockItem TINY_CACTUS;
	public static BlockItem AGAVE;
	public static BlockItem ALOE_VERA;
	public static BlockItem DEAD_GRASS;

	public static LogTurnerItem LOG_TURNER;

	public static void init() {
		// Because DirtBlocks are nullable.
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.dirtBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.pathBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.grassBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.podzolBlock());
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.farmBlock());

		REDWOOD = WoodItems.register(TerrestriaBlockItemIds.REDWOOD, TerrestriaBlocks.REDWOOD);
		HEMLOCK = WoodItems.register(TerrestriaBlockItemIds.HEMLOCK, TerrestriaBlocks.HEMLOCK);
		RUBBER = WoodItems.register(TerrestriaBlockItemIds.RUBBER, TerrestriaBlocks.RUBBER);
		CYPRESS = WoodItems.register(TerrestriaBlockItemIds.CYPRESS, TerrestriaBlocks.CYPRESS);
		WILLOW = WoodItems.register(TerrestriaBlockItemIds.WILLOW, TerrestriaBlocks.WILLOW);
		JAPANESE_MAPLE = WoodItems.register(TerrestriaBlockItemIds.JAPANESE_MAPLE, TerrestriaBlocks.JAPANESE_MAPLE);
		RAINBOW_EUCALYPTUS = WoodItems.register(TerrestriaBlockItemIds.RAINBOW_EUCALYPTUS, TerrestriaBlocks.RAINBOW_EUCALYPTUS);
		SAKURA = WoodItems.register(TerrestriaBlockItemIds.SAKURA, TerrestriaBlocks.SAKURA);
		YUCCA_PALM = WoodItems.register(TerrestriaBlockItemIds.YUCCA_PALM, TerrestriaBlocks.YUCCA_PALM);

		SMALL_OAK_LOG = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.SMALL_OAK_LOG, TerrestriaBlocks.SMALL_OAK_LOG);
		STRIPPED_SMALL_OAK_LOG = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.STRIPPED_SMALL_OAK_LOG, TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG);
		SAGUARO_CACTUS = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.SAGUARO_CACTUS, TerrestriaBlocks.SAGUARO_CACTUS);

		STACKED_OAK_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_OAK_LOGS, settings -> new BlockItem(TerrestriaBlocks.STACKED_OAK_LOGS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_SAKURA_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_SAKURA_LOGS, settings -> new BlockItem(TerrestriaBlocks.STACKED_SAKURA_LOGS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_YUCCA_PALM_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_YUCCA_PALM_LOGS, settings -> new BlockItem(TerrestriaBlocks.STACKED_YUCCA_PALM_LOGS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_STRIPPED_OAK_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_STRIPPED_OAK_LOGS, settings -> new BlockItem(TerrestriaBlocks.STACKED_STRIPPED_OAK_LOGS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_STRIPPED_SAKURA_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_STRIPPED_SAKURA_LOGS, settings -> new BlockItem(TerrestriaBlocks.STACKED_STRIPPED_SAKURA_LOGS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STACKED_STRIPPED_YUCCA_PALM_LOGS = TerrestriaRegistry.register(TerrestriaBlockItemIds.STACKED_STRIPPED_YUCCA_PALM_LOGS, settings -> new BlockItem(TerrestriaBlocks.STACKED_STRIPPED_YUCCA_PALM_LOGS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

		JAPANESE_MAPLE_SHRUB_LEAVES = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.JAPANESE_MAPLE_SHRUB_LEAVES, TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES);
		DARK_JAPANESE_MAPLE_LEAVES = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.DARK_JAPANESE_MAPLE_LEAVES, TerrestriaBlocks.DARK_JAPANESE_MAPLE_LEAVES);
		JUNGLE_PALM_LEAVES = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.JUNGLE_PALM_LEAVES, TerrestriaBlocks.JUNGLE_PALM_LEAVES);

		CATTAIL = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.CATTAIL, TerrestriaBlocks.CATTAIL);

		BRYCE_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.BRYCE_SAPLING, TerrestriaBlocks.BRYCE_SAPLING);
		REDWOOD_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.REDWOOD_SAPLING, TerrestriaBlocks.REDWOOD_SAPLING);
		HEMLOCK_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.HEMLOCK_SAPLING, TerrestriaBlocks.HEMLOCK_SAPLING);
		RUBBER_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.RUBBER_SAPLING, TerrestriaBlocks.RUBBER_SAPLING);
		CYPRESS_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.CYPRESS_SAPLING, TerrestriaBlocks.CYPRESS_SAPLING);
		WILLOW_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.WILLOW_SAPLING, TerrestriaBlocks.WILLOW_SAPLING);
		JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.JAPANESE_MAPLE_SAPLING, TerrestriaBlocks.JAPANESE_MAPLE_SAPLING);
		JAPANESE_MAPLE_SHRUB_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.JAPANESE_MAPLE_SHRUB_SAPLING, TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING);
		DARK_JAPANESE_MAPLE_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.DARK_JAPANESE_MAPLE_SAPLING, TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING);
		RAINBOW_EUCALYPTUS_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.RAINBOW_EUCALYPTUS_SAPLING, TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING);
		SAKURA_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.SAKURA_SAPLING, TerrestriaBlocks.SAKURA_SAPLING);
		JUNGLE_PALM_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.JUNGLE_PALM_SAPLING, TerrestriaBlocks.JUNGLE_PALM_SAPLING);
		SAGUARO_CACTUS_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.SAGUARO_CACTUS_SAPLING, TerrestriaBlocks.SAGUARO_CACTUS_SAPLING);
		YUCCA_PALM_SAPLING = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.YUCCA_PALM_SAPLING, TerrestriaBlocks.YUCCA_PALM_SAPLING);

		ANDISOL = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.ANDISOL.dirtBlock(), TerrestriaBlocks.ANDISOL.dirtBlock());
		ANDISOL_DIRT_PATH = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.ANDISOL.dirtPathBlock(), TerrestriaBlocks.ANDISOL.pathBlock());
		ANDISOL_FARMLAND = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.ANDISOL.farmBlock(), TerrestriaBlocks.ANDISOL.farmBlock());
		ANDISOL_GRASS_BLOCK = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.ANDISOL.grassBlock(), TerrestriaBlocks.ANDISOL.grassBlock());
		ANDISOL_PODZOL = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.ANDISOL.podzolBlock(), TerrestriaBlocks.ANDISOL.podzolBlock());

		VOLCANIC_ROCK = StoneItems.register(TerrestriaBlockItemIds.VOLCANIC_ROCK, TerrestriaBlocks.VOLCANIC_ROCK);
		VOLCANIC_SAND = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.VOLCANIC_SAND, TerrestriaBlocks.VOLCANIC_SAND);

		INDIAN_PAINTBRUSH = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.INDIAN_PAINTBRUSH, TerrestriaBlocks.INDIAN_PAINTBRUSH);
		MONSTERAS = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.MONSTERAS, TerrestriaBlocks.MONSTERAS);

		TINY_CACTUS = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.TINY_CACTUS, TerrestriaBlocks.TINY_CACTUS);
		AGAVE = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.AGAVE, TerrestriaBlocks.AGAVE);
		ALOE_VERA = TerrestriaRegistry.registerBlockItem(TerrestriaBlockItemIds.ALOE_VERA, TerrestriaBlocks.ALOE_VERA);
		DEAD_GRASS = TerrestriaRegistry.register(TerrestriaBlockItemIds.DEAD_GRASS, settings -> new BlockItem(TerrestriaBlocks.DEAD_GRASS, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));

		LOG_TURNER = TerrestriaRegistry.register(TerrestriaItemIds.LOG_TURNER, LogTurnerItem::new, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_SMALL));

		addCompostables();
	}

	private static void addCompostables() {
		Collection<Item> leaves = List.of(
			DARK_JAPANESE_MAPLE_LEAVES,
			JAPANESE_MAPLE_SHRUB_LEAVES,
			JUNGLE_PALM_LEAVES
		);

		Collection<Item> cacti = List.of(
			SAGUARO_CACTUS,
			TINY_CACTUS
		);

		Collection<Item> seagrasses = List.of(
			CATTAIL
		);

		Collection<Item> flowers = List.of(
			AGAVE,
			ALOE_VERA,
			INDIAN_PAINTBRUSH
		);

		Collection<Item> dryGrasses = List.of(
			DEAD_GRASS
		);

		Collection<Item> largeFerns = List.of(
			MONSTERAS
		);

		Collection<Item> saplings = List.of(
			BRYCE_SAPLING,
			CYPRESS_SAPLING,
			DARK_JAPANESE_MAPLE_SAPLING,
			HEMLOCK_SAPLING,
			JAPANESE_MAPLE_SAPLING,
			JAPANESE_MAPLE_SHRUB_SAPLING,
			JUNGLE_PALM_SAPLING,
			RAINBOW_EUCALYPTUS_SAPLING,
			REDWOOD_SAPLING,
			RUBBER_SAPLING,
			SAGUARO_CACTUS_SAPLING,
			SAKURA_SAPLING,
			WILLOW_SAPLING,
			YUCCA_PALM_SAPLING
		);

		DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
			modifyContext.modify(leaves, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_LOW))
			);

			modifyContext.modify(cacti, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_LOW_MEDIUM))
			);

			modifyContext.modify(seagrasses, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_LOW))
			);

			modifyContext.modify(flowers, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_MEDIUM))
			);

			modifyContext.modify(dryGrasses, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_LOW))
			);

			modifyContext.modify(largeFerns, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_MEDIUM))
			);

			modifyContext.modify(saplings, (builder, _, _) ->
				builder.set(DataComponents.COMPOSTABLE,
					new Compostable(ContextIntProviders.COMPOSTABLE_LOW))
			);
		});
	}
}
