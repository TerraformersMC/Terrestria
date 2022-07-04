package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;

public class TerrestriaBiomeTagProvider extends FabricTagProvider.DynamicRegistryTagProvider<Biome> {
	public TerrestriaBiomeTagProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator, BuiltinRegistries.BIOME.getKey(), "worldgen/biome", "Biome Tags");
	}

	@Override
	protected void generateTags() {
		// biome categories
		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_BEACH.id()))
			.add(TerrestriaBiomes.CALDERA_BEACH);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_FOREST.id()))
			.add(TerrestriaBiomes.CYPRESS_FOREST)
			.add(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.add(TerrestriaBiomes.SAKURA_FOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_HILL.id()))
			.add(TerrestriaBiomes.CALDERA_FOOTHILLS)
			.add(TerrestriaBiomes.VOLCANIC_ISLAND);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_JUNGLE.id()))
			.add(TerrestriaBiomes.RAINBOW_RAINFOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_MOUNTAIN.id()))
			.add(TerrestriaBiomes.CALDERA_RIDGE);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_OCEAN.id()))
			.add(TerrestriaBiomes.CALDERA);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.IS_TAIGA.id()))
			.add(TerrestriaBiomes.DENSE_WOODLANDS)
			.add(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.add(TerrestriaBiomes.HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.add(TerrestriaBiomes.REDWOOD_FOREST)
			.add(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);


		// biome structure generation tags
		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.LUSH_DESERT)
			.add(TerrestriaBiomes.OASIS);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.JUNGLE_TEMPLE_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.RAINBOW_RAINFOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.MINESHAFT_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.CALDERA)
			.add(TerrestriaBiomes.CALDERA_BEACH)
			.add(TerrestriaBiomes.CALDERA_FOOTHILLS)
			.add(TerrestriaBiomes.CALDERA_RIDGE)
			.add(TerrestriaBiomes.CANYON)
			.add(TerrestriaBiomes.CYPRESS_FOREST)
			.add(TerrestriaBiomes.CYPRESS_SWAMP)
			.add(TerrestriaBiomes.DENSE_WOODLANDS)
			.add(TerrestriaBiomes.DUNES)
			.add(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.add(TerrestriaBiomes.HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.add(TerrestriaBiomes.LUSH_DESERT)
			.add(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.add(TerrestriaBiomes.OASIS)
			.add(TerrestriaBiomes.OUTBACK)
			.add(TerrestriaBiomes.RAINBOW_RAINFOREST)
			.add(TerrestriaBiomes.REDWOOD_FOREST)
			.add(TerrestriaBiomes.SAKURA_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.VOLCANIC_ISLAND)
			.add(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.RUINED_PORTAL_DESERT_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.LUSH_DESERT)
			.add(TerrestriaBiomes.OASIS)
			.add(TerrestriaBiomes.OUTBACK);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.RUINED_PORTAL_JUNGLE_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.RAINBOW_RAINFOREST)
			.add(TerrestriaBiomes.VOLCANIC_ISLAND);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.RUINED_PORTAL_MOUNTAIN_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.RUINED_PORTAL_STANDARD_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.CYPRESS_FOREST)
			.add(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.add(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.add(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.add(TerrestriaBiomes.REDWOOD_FOREST)
			.add(TerrestriaBiomes.SAKURA_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.SWAMP_HUT_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.CYPRESS_SWAMP);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.SHIPWRECK_BEACHED_HAS_STRUCTURE.id()))
			.addTag(BiomeTags.IS_BEACH);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.STRONGHOLD_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.CALDERA)
			.add(TerrestriaBiomes.CALDERA_BEACH)
			.add(TerrestriaBiomes.CALDERA_FOOTHILLS)
			.add(TerrestriaBiomes.CALDERA_RIDGE)
			.add(TerrestriaBiomes.CANYON)
			.add(TerrestriaBiomes.CYPRESS_FOREST)
			.add(TerrestriaBiomes.CYPRESS_SWAMP)
			.add(TerrestriaBiomes.DENSE_WOODLANDS)
			.add(TerrestriaBiomes.DUNES)
			.add(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.add(TerrestriaBiomes.HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.add(TerrestriaBiomes.LUSH_DESERT)
			.add(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.add(TerrestriaBiomes.OASIS)
			.add(TerrestriaBiomes.OUTBACK)
			.add(TerrestriaBiomes.RAINBOW_RAINFOREST)
			.add(TerrestriaBiomes.REDWOOD_FOREST)
			.add(TerrestriaBiomes.SAKURA_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.VOLCANIC_ISLAND)
			.add(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.VILLAGE_DESERT_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.LUSH_DESERT)
			.add(TerrestriaBiomes.OASIS)
			.add(TerrestriaBiomes.VOLCANIC_ISLAND);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.VILLAGE_PLAINS_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.HEMLOCK_RAINFOREST)
			.add(TerrestriaBiomes.JAPANESE_MAPLE_FOREST)
			.add(TerrestriaBiomes.LUSH_REDWOOD_FOREST)
			.add(TerrestriaBiomes.REDWOOD_FOREST)
			.add(TerrestriaBiomes.SAKURA_FOREST);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.VILLAGE_SAVANNA_HAS_STRUCTURE.id()))
				.add(TerrestriaBiomes.OUTBACK);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.VILLAGE_SNOWY_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_FOREST)
			.add(TerrestriaBiomes.SNOWY_HEMLOCK_TREELINE);

		this.getOrCreateTagBuilder(TagKey.of(this.registry.getKey(), BiomeTags.VILLAGE_TAIGA_HAS_STRUCTURE.id()))
			.add(TerrestriaBiomes.HEMLOCK_TREELINE)
			.add(TerrestriaBiomes.WINDSWEPT_REDWOOD_FOREST);
	}
}
