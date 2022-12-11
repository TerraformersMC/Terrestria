package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.tag.TerrestriaBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
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
		 * Vanilla biome categories
		 */
		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_BEACH.id()))
			.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_FOREST.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
			.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_HILL.id()))
			.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_JUNGLE.id()))
			.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_MOUNTAIN.id()))
			.addOptional(TerrestriaBiomes.CALDERA);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_SAVANNA.id()))
			.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IS_TAIGA.id()))
			.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
			.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
			.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
			.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.SPAWNS_COLD_VARIANT_FROGS.id()))
			.addOptional(TerrestriaBiomes.CALDERA)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.SPAWNS_WARM_VARIANT_FROGS.id()))
			.addOptional(TerrestriaBiomes.CANYON)
			.addOptional(TerrestriaBiomes.DUNES)
			.addOptional(TerrestriaBiomes.LUSH_DESERT)
			.addOptional(TerrestriaBiomes.OASIS)
			.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.WATER_ON_MAP_OUTLINES.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);


		/*
		 * Common biome categories
		 */
		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.CLIMATE_TEMPERATE.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
			.addOptional(TerrestriaBiomes.CYPRESS_SWAMP)
			.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
			.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.DEAD.id()))
			.addOptional(TerrestriaBiomes.DUNES);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.DESERT.id()))
			.addOptional(TerrestriaBiomes.CANYON)
			.addOptional(TerrestriaBiomes.DUNES)
			.addOptional(TerrestriaBiomes.LUSH_DESERT)
			.addOptional(TerrestriaBiomes.OASIS);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.EXTREME_HILLS.id()))
			.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.IN_OVERWORLD.id()))
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

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.MESA.id()))
			.addOptional(TerrestriaBiomes.CANYON);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.SAVANNA.id()))
			.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.SNOWY.id()))
			.addOptional(TerrestriaBiomes.CALDERA)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.SWAMP.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.TREE_CONIFEROUS.id()))
			.addOptional(TerrestriaBiomes.CALDERA)
			.addOptional(TerrestriaBiomes.CYPRESS_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, ConventionalBiomeTags.TREE_DECIDUOUS.id()))
			.addOptional(TerrestriaBiomes.DENSE_WOODLANDS)
			.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
			.addOptional(TerrestriaBiomes.SAKURA_FOREST);


		/*
		 * Biome structure generation tags
		 */
		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.LUSH_DESERT)
			.addOptional(TerrestriaBiomes.OASIS);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.IGLOO_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.JUNGLE_TEMPLE_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.MINESHAFT_HAS_STRUCTURE.id()))
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

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.RUINED_PORTAL_DESERT_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.LUSH_DESERT)
			.addOptional(TerrestriaBiomes.OASIS)
			.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.RUINED_PORTAL_JUNGLE_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.RAINBOW_RAINFOREST)
			.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.RUINED_PORTAL_MOUNTAIN_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
			.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.RUINED_PORTAL_STANDARD_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_FOREST)
			.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.SAKURA_FOREST)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.SWAMP_HUT_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.CYPRESS_SWAMP);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.SHIPWRECK_BEACHED_HAS_STRUCTURE.id()))
			.addTag(BiomeTags.IS_BEACH);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.STRONGHOLD_HAS_STRUCTURE.id()))
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

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.VILLAGE_DESERT_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.LUSH_DESERT)
			.addOptional(TerrestriaBiomes.OASIS)
			.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.addOptional(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.addOptional(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.REDWOOD_FOREST)
			.addOptional(TerrestriaBiomes.SAKURA_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.VILLAGE_SAVANNA_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.OUTBACK);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.addOptional(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, BiomeTags.VILLAGE_TAIGA_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.HEMLOCK_TREELINE)
			.addOptional(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, TerrestriaBiomeTags.CANYON_ARCH_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.CANYON);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, TerrestriaBiomeTags.OCEAN_VOLCANO_HAS_STRUCTURE.id()))
			.add(BiomeKeys.DEEP_FROZEN_OCEAN)
			.add(BiomeKeys.DEEP_COLD_OCEAN)
			.add(BiomeKeys.DEEP_OCEAN)
			.add(BiomeKeys.DEEP_LUKEWARM_OCEAN);

		getOrCreateTagBuilder(TagKey.of(RegistryKeys.BIOME, TerrestriaBiomeTags.VOLCANO_HAS_STRUCTURE.id()))
			.addOptional(TerrestriaBiomes.VOLCANIC_ISLAND);
	}
}
