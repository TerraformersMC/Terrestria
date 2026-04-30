package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.tag.TerrestriaBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

@NullMarked
public class TerrestriaBiomeTagProvider extends FabricTagsProvider<Biome> {
	protected TerrestriaBiomeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.BIOME, registriesFuture);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		/*
		 * Vanilla biome tags
		 */
		builder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

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
		builder(BiomeTags.HAS_DESERT_PYRAMID)
				.addOptional(TerrestriaBiomes.LUSH_DESERT);

		builder(BiomeTags.HAS_IGLOO)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		builder(BiomeTags.HAS_JUNGLE_TEMPLE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		builder(BiomeTags.HAS_MINESHAFT)
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

		builder(BiomeTags.HAS_RUINED_PORTAL_DESERT)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(BiomeTags.HAS_RUINED_PORTAL_JUNGLE)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.HAS_RUINED_PORTAL_MOUNTAIN)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		builder(BiomeTags.HAS_RUINED_PORTAL_STANDARD)
				.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		builder(BiomeTags.HAS_SWAMP_HUT)
				.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		builder(BiomeTags.HAS_SHIPWRECK_BEACHED)
				.addTag(BiomeTags.IS_BEACH);

		builder(BiomeTags.HAS_TRAIL_RUINS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		builder(BiomeTags.HAS_TRIAL_CHAMBERS)
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

		builder(BiomeTags.HAS_VILLAGE_DESERT)
				.addOptional(TerrestriaBiomes.LUSH_DESERT)
				.addOptional(TerrestriaBiomes.OASIS)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		builder(BiomeTags.HAS_VILLAGE_PLAINS)
				.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
				.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
				.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
				.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		builder(BiomeTags.HAS_VILLAGE_SAVANNA)
				.addOptional(TerrestriaBiomes.OUTBACK);

		builder(BiomeTags.HAS_VILLAGE_SNOWY)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
				.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		builder(BiomeTags.HAS_VILLAGE_TAIGA)
				.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
				.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);


		builder(TerrestriaBiomeTags.CANYON_ARCH_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.CANYON);

		builder(TerrestriaBiomeTags.OCEAN_VOLCANO_HAS_STRUCTURE)
				.add(Biomes.DEEP_FROZEN_OCEAN)
				.add(Biomes.DEEP_COLD_OCEAN)
				.add(Biomes.DEEP_OCEAN)
				.add(Biomes.DEEP_LUKEWARM_OCEAN);

		builder(TerrestriaBiomeTags.VOLCANO_HAS_STRUCTURE)
				.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);
	}

	@Override
	public String getName() {
		return "Terrestria Biome Tags";
	}
}
