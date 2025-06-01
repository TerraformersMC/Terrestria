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
		builder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		builder(BiomeTags.HAS_CLOSER_WATER_FOG)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		builder(BiomeTags.INCREASED_FIRE_BURNOUT)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		builder(BiomeTags.IS_BEACH)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.IS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		builder(BiomeTags.IS_HILL)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.IS_JUNGLE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		builder(BiomeTags.IS_MOUNTAIN)
				.addOptional(TerrestriaBiomes.CALDERA);

		builder(BiomeTags.IS_OVERWORLD)
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

		builder(BiomeTags.IS_SAVANNA)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(BiomeTags.IS_TAIGA)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		builder(BiomeTags.SNOW_GOLEM_MELTS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(BiomeTags.SPAWNS_COLD_VARIANT_FROGS)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		builder(BiomeTags.SPAWNS_GOLD_RABBITS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT);

		builder(BiomeTags.SPAWNS_SNOW_FOXES)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		builder(BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.STRONGHOLD_BIASED_TO)
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

		builder(BiomeTags.WATER_ON_MAP_OUTLINES)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);


		/*
		 * Conventional biome tags
		 */
		builder(ConventionalBiomeTags.IS_BADLANDS)
				.addOptional(TerrestriaBiomes.CANYON);

		builder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		builder(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST);

		builder(ConventionalBiomeTags.IS_DEAD)
				.addOptional(TerrestriaBiomes.DUNES);

		builder(ConventionalBiomeTags.IS_DECIDUOUS_TREE)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		builder(ConventionalBiomeTags.IS_DESERT)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.DUNES)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS);

		builder(ConventionalBiomeTags.IS_HOT_OVERWORLD)
				.addOptional(TerrestriaBiomes.CANYON)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(ConventionalBiomeTags.IS_SAVANNA)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(ConventionalBiomeTags.IS_SNOWY)
				.addOptional(TerrestriaBiomes.CALDERA)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		builder(ConventionalBiomeTags.IS_SWAMP)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		builder(ConventionalBiomeTags.IS_TEMPERATE_OVERWORLD)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
				.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		builder(ConventionalBiomeTags.IS_VEGETATION_DENSE_OVERWORLD)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		builder(ConventionalBiomeTags.IS_WINDSWEPT)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);


		/*
		 * Biome structure generation tags
		 */
		builder(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.LUSH_DESERT);

		builder(BiomeTags.IGLOO_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		builder(BiomeTags.JUNGLE_TEMPLE_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		builder(BiomeTags.MINESHAFT_HAS_STRUCTURE)
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

		builder(BiomeTags.RUINED_PORTAL_DESERT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(BiomeTags.RUINED_PORTAL_JUNGLE_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.RUINED_PORTAL_MOUNTAIN_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		builder(BiomeTags.RUINED_PORTAL_STANDARD_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		builder(BiomeTags.SWAMP_HUT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		builder(BiomeTags.SHIPWRECK_BEACHED_HAS_STRUCTURE)
				.addTag(BiomeTags.IS_BEACH);

		builder(BiomeTags.TRAIL_RUINS_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		builder(BiomeTags.TRIAL_CHAMBERS_HAS_STRUCTURE)
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

		builder(BiomeTags.VILLAGE_DESERT_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		builder(BiomeTags.VILLAGE_SAVANNA_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		builder(BiomeTags.VILLAGE_TAIGA_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);


		builder(TerrestriaBiomeTags.CANYON_ARCH_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CANYON);

		builder(TerrestriaBiomeTags.OCEAN_VOLCANO_HAS_STRUCTURE)
				.add(BiomeKeys.DEEP_FROZEN_OCEAN)
				.add(BiomeKeys.DEEP_COLD_OCEAN)
				.add(BiomeKeys.DEEP_OCEAN)
				.add(BiomeKeys.DEEP_LUKEWARM_OCEAN);

		builder(TerrestriaBiomeTags.VOLCANO_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);
	}

	@Override
	public String getName() {
		return "Terrestria Biome Tags";
	}
}
