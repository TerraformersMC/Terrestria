package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.villager.TerrestriaVillagerTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

// TODO: Add: cartographer biome maps
@NullMarked
public class TerrestriaVillagerTradeKeyTagProvider extends FabricTagsProvider<VillagerTrade> {
	protected TerrestriaVillagerTradeKeyTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, Registries.VILLAGER_TRADE, lookupProvider);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		this.tag(VillagerTradeTags.WANDERING_TRADER_UNCOMMON)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_CYPRESS_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_CYPRESS_QUARTER_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_HEMLOCK_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_HEMLOCK_QUARTER_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_QUARTER_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_REDWOOD_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_REDWOOD_QUARTER_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_RUBBER_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_SAKURA_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_SMALL_OAK_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_WILLOW_LOG)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_YUCCA_PALM_LOG);

		this.tag(VillagerTradeTags.WANDERING_TRADER_COMMON)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_BRYCE_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_CYPRESS_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_DARK_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_HEMLOCK_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_JAPANESE_MAPLE_SHRUB_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_JUNGLE_PALM_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_RAINBOW_EUCALYPTUS_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_REDWOOD_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_RUBBER_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_SAGUARO_CACTUS_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_SAKURA_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_WILLOW_SAPLING)
			.add(TerrestriaVillagerTrades.WANDERING_TRADER_EMERALD_YUCCA_PALM_SAPLING);
	}

	@Override
	public String getName() {
		return "Terrestria Villager Trade Tags";
	}
}
