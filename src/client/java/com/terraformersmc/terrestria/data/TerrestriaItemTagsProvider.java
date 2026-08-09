package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.init.TerrestriaBlockItemIds;
import com.terraformersmc.terrestria.init.TerrestriaItemIds;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

@NullMarked
public class TerrestriaItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
	protected TerrestriaItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, BlockTagsProvider blockTagProvider) {
		super(output, registriesFuture, blockTagProvider);
	}

	@Override
	public void addTags(HolderLookup.Provider registries) {
		/*
		 * Vanilla item tags
		 */
		copy(BlockTags.CEILING_HANGING_SIGNS, ItemTags.HANGING_SIGNS);

		copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);


		/*
		 * Local item tags
		 */
		tag(TerrestriaItemTags.MOSSY_INGREDIENTS)
				.add(BlockItemIds.MOSS_BLOCK.item())
				.add(BlockItemIds.VINE.item());


		/*
		 * Stone items
		 */
		addStone(TerrestriaItems.VOLCANIC_ROCK, TerrestriaBlockItemIds.VOLCANIC_ROCK);

		/*
		 * Wood items
		 */
		addWood(TerrestriaItems.CYPRESS, TerrestriaItemIds.CYPRESS);
		addWood(TerrestriaItems.HEMLOCK, TerrestriaItemIds.HEMLOCK);
		addWood(TerrestriaItems.JAPANESE_MAPLE, TerrestriaItemIds.JAPANESE_MAPLE);
		addWood(TerrestriaItems.RAINBOW_EUCALYPTUS, TerrestriaItemIds.RAINBOW_EUCALYPTUS);
		addWood(TerrestriaItems.REDWOOD, TerrestriaItemIds.REDWOOD);
		addWood(TerrestriaItems.RUBBER, TerrestriaItemIds.RUBBER);
		addWood(TerrestriaItems.SAKURA, TerrestriaItemIds.SAKURA);
		addWood(TerrestriaItems.WILLOW, TerrestriaItemIds.WILLOW);
		addWood(TerrestriaItems.YUCCA_PALM, TerrestriaItemIds.YUCCA_PALM);

		/*
		 * Run BlockItem item tags
		 */
		new TerrestriaBlockItemTagsProvider(tagId -> BlockItemTagsProvider.wrapForItems(this.tag(tagId.item()))).run();
	}

	private void addStone(StoneItems stoneItem, TerrestriaBlockItemIds.StoneBlockItemIds stoneItemIds) {
		if (stoneItem.cobblestone != null) {
			// Add any cobble variant to vanilla crafting tags.
			tag(ItemTags.STONE_CRAFTING_MATERIALS).add(stoneItemIds.cobblestone().full().item());
			tag(ItemTags.STONE_TOOL_MATERIALS).add(stoneItemIds.cobblestone().full().item());
		}
	}

	@SuppressWarnings("unused")
	private void addWood(WoodItems woodItem, TerrestriaItemIds.WoodItemIds woodItemIds) {
		// Add boats if they exist via the WoodItem.
		if (woodItemIds.boat() != null) {
			tag(ItemTags.BOATS).add(woodItemIds.boat());
		}
		if (woodItemIds.chestBoat() != null) {
			tag(ItemTags.CHEST_BOATS).add(woodItemIds.chestBoat());
		}
		if (woodItemIds.raft() != null) {
			tag(ItemTags.BOATS).add(woodItemIds.raft());
		}
		if (woodItemIds.chestRaft() != null) {
			tag(ItemTags.CHEST_BOATS).add(woodItemIds.chestRaft());
		}
	}

	@Override
	public String getName() {
		return "Terrestria Item Tags";
	}
}
