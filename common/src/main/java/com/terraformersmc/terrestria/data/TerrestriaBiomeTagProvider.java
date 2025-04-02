package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.tag.TerrestriaBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class TerrestriaBiomeTagProvider extends FabricTagProvider<Biome> {
	protected TerrestriaBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, RegistryKeys.BIOME, registriesFuture);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries) {
		/*
		 * Vanilla biome tags
		 */
		getOrCreateTagBuilder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(BiomeTags.INCREASED_FIRE_BURNOUT)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		getOrCreateTagBuilder(BiomeTags.IS_BEACH)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(BiomeTags.IS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(BiomeTags.IS_HILL)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(BiomeTags.IS_JUNGLE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		getOrCreateTagBuilder(BiomeTags.IS_MOUNTAIN)
				.addOptional(TerrestriaBiomes.CALDERA);

		getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.IS_SAVANNA)
				.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(BiomeTags.IS_TAIGA)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.SNOW_GOLEM_MELTS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(BiomeTags.SPAWNS_GOLD_RABBITS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT);

		getOrCreateTagBuilder(BiomeTags.SPAWNS_SNOW_FOXES)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.OUTBACK)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.WATER_ON_MAP_OUTLINES)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);


		/*
		 * Conventional biome tags
		 */
		getOrCreateTagBuilder(ConventionalBiomeTags.IS_BADLANDS)
				.addOptional(TerrestriaBiomes.CANYON);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_DEAD)
				.addOptional(TerrestriaBiomes.DUNES);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_DECIDUOUS_TREE)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_DESERT)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_HOT_OVERWORLD)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_SAVANNA)
				.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_SNOWY)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_SWAMP)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_TEMPERATE_OVERWORLD)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_DENSE_OVERWORLD)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		getOrCreateTagBuilder(ConventionalBiomeTags.IS_WINDSWEPT)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);


		/*
		 * Biome structure generation tags
		 */
		getOrCreateTagBuilder(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.LUSH_DESERT);

		getOrCreateTagBuilder(BiomeTags.IGLOO_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		getOrCreateTagBuilder(BiomeTags.JUNGLE_TEMPLE_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		getOrCreateTagBuilder(BiomeTags.MINESHAFT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.RUINED_PORTAL_DESERT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(BiomeTags.RUINED_PORTAL_JUNGLE_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(BiomeTags.RUINED_PORTAL_MOUNTAIN_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.RUINED_PORTAL_STANDARD_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		getOrCreateTagBuilder(BiomeTags.SWAMP_HUT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(BiomeTags.SHIPWRECK_BEACHED_HAS_STRUCTURE)
				.addTag(BiomeTags.IS_BEACH);

		getOrCreateTagBuilder(BiomeTags.TRAIL_RUINS_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.TRIAL_CHAMBERS_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(BiomeTags.VILLAGE_DESERT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(BiomeTags.VILLAGE_SAVANNA_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(BiomeTags.VILLAGE_TAIGA_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);


		getOrCreateTagBuilder(TerrestriaBiomeTags.CANYON_ARCH_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CANYON);

		getOrCreateTagBuilder(TerrestriaBiomeTags.OCEAN_VOLCANO_HAS_STRUCTURE)
				.add(BiomeKeys.DEEP_FROZEN_OCEAN)
				.add(BiomeKeys.DEEP_COLD_OCEAN)
				.add(BiomeKeys.DEEP_OCEAN)
				.add(BiomeKeys.DEEP_LUKEWARM_OCEAN);

		getOrCreateTagBuilder(TerrestriaBiomeTags.VOLCANO_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);
	}

	@Override
	public String getName() {
		return "Terrestria Biome Tags";
	}
}
