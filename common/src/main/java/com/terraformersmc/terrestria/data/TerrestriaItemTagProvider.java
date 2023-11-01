package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.*;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class TerrestriaItemTagProvider extends FabricTagProvider.ItemTagProvider {
	protected TerrestriaItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture, BlockTagProvider blockTagProvider) {
		super(output, registriesFuture, blockTagProvider);
	}

	@Override
	public void configure(RegistryWrapper.WrapperLookup registries) {
		copy(BlockTags.BUTTONS, ItemTags.BUTTONS);

		copy(BlockTags.DIRT, ItemTags.DIRT);

		copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);

		copy(BlockTags.FENCE_GATES, ItemTags.FENCE_GATES);

		copy(BlockTags.LEAVES, ItemTags.LEAVES);

		copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);

		copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);

		copy(BlockTags.PLANKS, ItemTags.PLANKS);

		copy(BlockTags.SAND, ItemTags.SAND);

		copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);

		copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);

		copy(BlockTags.SLABS, ItemTags.SLABS);

		copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);

		copy(BlockTags.STAIRS, ItemTags.STAIRS);

		copy(BlockTags.WALLS, ItemTags.WALLS);

		copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);

		copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);

		copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);

		copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);

		copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);

		copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);

		copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);


		copy(TerrestriaBlockTags.BLACK_SAND, TerrestriaItemTags.BLACK_SAND);

		copy(TerrestriaBlockTags.COBBLESTONE, TerrestriaItemTags.COBBLESTONE);

		getOrCreateTagBuilder(TerrestriaItemTags.MOSSY_INGREDIENTS)
			.add(Items.MOSS_BLOCK)
			.add(Items.VINE);

		copy(TerrestriaBlockTags.PLANKS_THAT_BURN, TerrestriaItemTags.PLANKS_THAT_BURN);

		copy(TerrestriaBlockTags.SMALL_OAK_LOGS, TerrestriaItemTags.SMALL_OAK_LOGS);

		copy(TerrestriaBlockTags.STONE, TerrestriaItemTags.STONE);

		copy(TerrestriaBlockTags.STRIPPED_LOGS, TerrestriaItemTags.STRIPPED_LOGS);

		copy(TerrestriaBlockTags.STRIPPED_WOOD, TerrestriaItemTags.STRIPPED_WOOD);


		// dirt type tags
		copy(TerrestriaBlockTags.DIRT, TerrestriaItemTags.DIRT);

		// sand type tags
		copy(TerrestriaBlockTags.SAND, TerrestriaItemTags.SAND);

		// stone type tags
		copy(TerrestriaBlockTags.BASALT, TerrestriaItemTags.BASALT);

		// stone items
		addStone(TerrestriaItems.VOLCANIC_ROCK);

		// wood type tags
		copy(TerrestriaBlockTags.CYPRESS_LOGS, TerrestriaItemTags.CYPRESS_LOGS);
		copy(TerrestriaBlockTags.HEMLOCK_LOGS, TerrestriaItemTags.HEMLOCK_LOGS);
		copy(TerrestriaBlockTags.JAPANESE_MAPLE_LOGS, TerrestriaItemTags.JAPANESE_MAPLE_LOGS);
		copy(TerrestriaBlockTags.RAINBOW_EUCALYPTUS_LOGS, TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS);
		copy(TerrestriaBlockTags.REDWOOD_LOGS, TerrestriaItemTags.REDWOOD_LOGS);
		copy(TerrestriaBlockTags.RUBBER_LOGS, TerrestriaItemTags.RUBBER_LOGS);
		copy(TerrestriaBlockTags.SAKURA_LOGS, TerrestriaItemTags.SAKURA_LOGS);
		copy(TerrestriaBlockTags.WILLOW_LOGS, TerrestriaItemTags.WILLOW_LOGS);
		copy(TerrestriaBlockTags.YUCCA_PALM_LOGS, TerrestriaItemTags.YUCCA_PALM_LOGS);

		// wood items
		addWood(TerrestriaItems.CYPRESS);
		addWood(TerrestriaItems.HEMLOCK);
		addWood(TerrestriaItems.JAPANESE_MAPLE);
		addWood(TerrestriaItems.RAINBOW_EUCALYPTUS);
		addWood(TerrestriaItems.REDWOOD);
		addWood(TerrestriaItems.RUBBER);
		addWood(TerrestriaItems.SAKURA);
		addWood(TerrestriaItems.WILLOW);
		addWood(TerrestriaItems.YUCCA_PALM);
	}

	private void addStone(StoneItems stoneItem) {
		if (stoneItem.cobblestone != null) {
			// Add any cobble variant to vanilla crafting tags.
			getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS).add(stoneItem.cobblestone.full);
			getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS).add(stoneItem.cobblestone.full);
		}
	}

	private void addWood(WoodItems woodItem) {
		// Add boats if they exist via the WoodItem.
		if (woodItem.boat != null) {
			getOrCreateTagBuilder(ItemTags.BOATS).add(woodItem.boat);
		}
		if (woodItem.chestBoat != null) {
			getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(woodItem.chestBoat);
		}
	}
}
