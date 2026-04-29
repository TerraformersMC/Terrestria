package com.terraformersmc.terrestria.villager;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;

public class TerrestriaVillagerTrades {
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_CYPRESS_LOG = resourceKey("wandering_trader/emerald_cypress_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_CYPRESS_QUARTER_LOG = resourceKey("wandering_trader/emerald_cypress_quarter_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_HEMLOCK_LOG = resourceKey("wandering_trader/emerald_hemlock_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_HEMLOCK_QUARTER_LOG = resourceKey("wandering_trader/emerald_hemlock_quarter_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_LOG = resourceKey("wandering_trader/emerald_japanese_maple_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_LOG = resourceKey("wandering_trader/emerald_rainbow_eucalyptus_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_QUARTER_LOG = resourceKey("wandering_trader/emerald_rainbow_eucalyptus_quarter_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_REDWOOD_LOG = resourceKey("wandering_trader/emerald_redwood_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_REDWOOD_QUARTER_LOG = resourceKey("wandering_trader/emerald_redwood_quarter_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_RUBBER_LOG = resourceKey("wandering_trader/emerald_rubber_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SAKURA_LOG = resourceKey("wandering_trader/emerald_sakura_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SMALL_OAK_LOG = resourceKey("wandering_trader/emerald_small_oak_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_WILLOW_LOG = resourceKey("wandering_trader/emerald_willow_log");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_YUCCA_PALM_LOG = resourceKey("wandering_trader/emerald_yucca_palm_log");

	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_BRYCE_SAPLING = resourceKey("wandering_trader/emerald_bryce_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_CYPRESS_SAPLING = resourceKey("wandering_trader/emerald_cypress_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_DARK_JAPANESE_MAPLE_SAPLING = resourceKey("wandering_trader/emerald_dark_japanese_maple_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_HEMLOCK_SAPLING = resourceKey("wandering_trader/emerald_hemlock_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_SAPLING = resourceKey("wandering_trader/emerald_japanese_maple_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_SHRUB_SAPLING = resourceKey("wandering_trader/emerald_japanese_maple_shrub_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_JUNGLE_PALM_SAPLING = resourceKey("wandering_trader/emerald_jungle_palm_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_SAPLING = resourceKey("wandering_trader/emerald_rainbow_eucalyptus_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_REDWOOD_SAPLING = resourceKey("wandering_trader/emerald_redwood_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_RUBBER_SAPLING = resourceKey("wandering_trader/emerald_rubber_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SAGUARO_CACTUS_SAPLING = resourceKey("wandering_trader/emerald_saguaro_cactus_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_SAKURA_SAPLING = resourceKey("wandering_trader/emerald_sakura_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_WILLOW_SAPLING = resourceKey("wandering_trader/emerald_willow_sapling");
	public static final ResourceKey<VillagerTrade> WANDERING_TRADER_EMERALD_YUCCA_PALM_SAPLING = resourceKey("wandering_trader/emerald_yucca_palm_sapling");

	public static void bootstrap(BootstrapContext<VillagerTrade> context) {
		HolderGetter<Item> items = context.lookup(Registries.ITEM);

		for (Pair<ResourceKey<VillagerTrade>, Item> pair : List.of(
			Pair.of(WANDERING_TRADER_EMERALD_CYPRESS_LOG, TerrestriaItems.CYPRESS.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_CYPRESS_QUARTER_LOG, TerrestriaItems.CYPRESS.quarterLog.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_HEMLOCK_LOG, TerrestriaItems.HEMLOCK.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_HEMLOCK_QUARTER_LOG, TerrestriaItems.HEMLOCK.quarterLog.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_LOG, TerrestriaItems.JAPANESE_MAPLE.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_LOG, TerrestriaItems.RAINBOW_EUCALYPTUS.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_QUARTER_LOG, TerrestriaItems.RAINBOW_EUCALYPTUS.quarterLog.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_REDWOOD_LOG, TerrestriaItems.REDWOOD.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_REDWOOD_QUARTER_LOG, TerrestriaItems.REDWOOD.quarterLog.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_RUBBER_LOG, TerrestriaItems.RUBBER.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_SAKURA_LOG, TerrestriaItems.SAKURA.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_SMALL_OAK_LOG, TerrestriaItems.SMALL_OAK_LOG.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_WILLOW_LOG, TerrestriaItems.WILLOW.log.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_YUCCA_PALM_LOG, TerrestriaItems.YUCCA_PALM.log.asItem())
		)) {
			context.register(pair.getLeft(), new VillagerTrade(
				new TradeCost(Items.EMERALD, 1),
				new ItemStackTemplate(pair.getRight(), 8),
				4,
				1,
				0.05F,
				Optional.empty(),
				List.of()
			));
		}

		for (Pair<ResourceKey<VillagerTrade>, Item> entry : List.of(
			Pair.of(WANDERING_TRADER_EMERALD_BRYCE_SAPLING, TerrestriaItems.BRYCE_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_CYPRESS_SAPLING, TerrestriaItems.CYPRESS_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_DARK_JAPANESE_MAPLE_SAPLING, TerrestriaItems.DARK_JAPANESE_MAPLE_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_HEMLOCK_SAPLING, TerrestriaItems.HEMLOCK_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_SAPLING, TerrestriaItems.JAPANESE_MAPLE_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_SHRUB_SAPLING, TerrestriaItems.JAPANESE_MAPLE_SHRUB_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_JUNGLE_PALM_SAPLING, TerrestriaItems.JUNGLE_PALM_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_SAPLING, TerrestriaItems.RAINBOW_EUCALYPTUS_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_REDWOOD_SAPLING, TerrestriaItems.REDWOOD_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_RUBBER_SAPLING, TerrestriaItems.RUBBER_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_SAGUARO_CACTUS_SAPLING, TerrestriaItems.SAGUARO_CACTUS_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_SAKURA_SAPLING, TerrestriaItems.SAKURA_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_WILLOW_SAPLING, TerrestriaItems.WILLOW_SAPLING.asItem()),
			Pair.of(WANDERING_TRADER_EMERALD_YUCCA_PALM_SAPLING, TerrestriaItems.YUCCA_PALM_SAPLING.asItem())
		)) {
			context.register(entry.getLeft(), new VillagerTrade(
				new TradeCost(Items.EMERALD, 5),
				new ItemStackTemplate(entry.getRight()),
				8,
				1,
				0.05F,
				Optional.empty(),
				List.of()
			));
		}
	}

	public static ResourceKey<VillagerTrade> resourceKey(final String path) {
		return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, path));
	}
}
