package com.terraformersmc.terrestria.data;

import com.terraformersmc.terraform.dirt.DirtBlocks;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.*;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;

public class TerrestriaItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public TerrestriaItemTagProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void generateTags() {
		this.getOrCreateTagBuilder(ItemTags.LEAVES)
			.add(TerrestriaItems.DARK_JAPANESE_MAPLE_LEAVES)
			.add(TerrestriaItems.JAPANESE_MAPLE_SHRUB_LEAVES)
			.add(TerrestriaItems.JUNGLE_PALM_LEAVES);

		this.getOrCreateTagBuilder(ItemTags.OAK_LOGS)
			.addTag(TerrestriaItemTags.SMALL_OAK_LOGS);

		this.getOrCreateTagBuilder(ItemTags.SAPLINGS)
			.add(TerrestriaItems.BRYCE_SAPLING)
			.add(TerrestriaItems.CYPRESS_SAPLING)
			.add(TerrestriaItems.DARK_JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaItems.HEMLOCK_SAPLING)
			.add(TerrestriaItems.JAPANESE_MAPLE_SAPLING)
			.add(TerrestriaItems.JAPANESE_MAPLE_SHRUB_SAPLING)
			.add(TerrestriaItems.JUNGLE_PALM_SAPLING)
			.add(TerrestriaItems.RAINBOW_EUCALYPTUS_SAPLING)
			.add(TerrestriaItems.REDWOOD_SAPLING)
			.add(TerrestriaItems.RUBBER_SAPLING)
			.add(TerrestriaItems.SAKURA_SAPLING)
			.add(TerrestriaItems.SAGUARO_CACTUS_SAPLING)
			.add(TerrestriaItems.WILLOW_SAPLING)
			.add(TerrestriaItems.YUCCA_PALM_SAPLING);

		this.getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
			.add(TerrestriaItems.INDIAN_PAINTBRUSH)
			.add(TerrestriaItems.MONSTERAS);


		this.getOrCreateTagBuilder(TerrestriaItemTags.BLACK_SAND)
			.add(TerrestriaItems.BLACK_SAND);

		this.getOrCreateTagBuilder(TerrestriaItemTags.PLANKS_THAT_BURN)
			.add(TerrestriaItems.CYPRESS.planks)
			.add(TerrestriaItems.HEMLOCK.planks)
			.add(TerrestriaItems.JAPANESE_MAPLE.planks)
			.add(TerrestriaItems.RAINBOW_EUCALYPTUS.planks)
			.add(TerrestriaItems.REDWOOD.planks)
			.add(TerrestriaItems.RUBBER.planks)
			.add(TerrestriaItems.SAKURA.planks)
			.add(TerrestriaItems.WILLOW.planks)
			.add(TerrestriaItems.YUCCA_PALM.planks);

		this.getOrCreateTagBuilder(TerrestriaItemTags.SMALL_OAK_LOGS)
			.add(TerrestriaItems.SMALL_OAK_LOG)
			.add(TerrestriaItems.STRIPPED_SMALL_OAK_LOG);


		// custom dirt item tags (no nice convenient item container?)
		addDirt(TerrestriaBlocks.ANDISOL);

		// custom sand item tags
		addSand(TerrestriaItems.BLACK_SAND);

		// stone building item tags
		addStone(TerrestriaItemTags.BASALT, TerrestriaItems.VOLCANIC_ROCK);

		// wood building item tags (sadly no QuarteredWoodItems available)
		addWood(TerrestriaItemTags.CYPRESS_LOGS, TerrestriaItems.CYPRESS)
			.add(TerrestriaItems.CYPRESS_QUARTER_LOG)
			.add(TerrestriaItems.STRIPPED_CYPRESS_QUARTER_LOG);
		addWood(TerrestriaItemTags.HEMLOCK_LOGS, TerrestriaItems.HEMLOCK)
			.add(TerrestriaItems.HEMLOCK_QUARTER_LOG)
			.add(TerrestriaItems.STRIPPED_HEMLOCK_QUARTER_LOG);
		addWood(TerrestriaItemTags.JAPANESE_MAPLE_LOGS, TerrestriaItems.JAPANESE_MAPLE);
		addWood(TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS, TerrestriaItems.RAINBOW_EUCALYPTUS)
			.add(TerrestriaItems.RAINBOW_EUCALYPTUS_QUARTER_LOG)
			.add(TerrestriaItems.STRIPPED_RAINBOW_EUCALYPTUS_QUARTER_LOG);
		addWood(TerrestriaItemTags.REDWOOD_LOGS, TerrestriaItems.REDWOOD)
			.add(TerrestriaItems.REDWOOD_QUARTER_LOG)
			.add(TerrestriaItems.STRIPPED_REDWOOD_QUARTER_LOG);
		addWood(TerrestriaItemTags.RUBBER_LOGS, TerrestriaItems.RUBBER);
		addWood(TerrestriaItemTags.SAKURA_LOGS, TerrestriaItems.SAKURA);
		addWood(TerrestriaItemTags.WILLOW_LOGS, TerrestriaItems.WILLOW);
		addWood(TerrestriaItemTags.YUCCA_PALM_LOGS, TerrestriaItems.YUCCA_PALM);

		this.getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
			.addTag(TerrestriaItemTags.CYPRESS_LOGS)
			.addTag(TerrestriaItemTags.HEMLOCK_LOGS)
			.addTag(TerrestriaItemTags.JAPANESE_MAPLE_LOGS)
			.addTag(TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS)
			.addTag(TerrestriaItemTags.REDWOOD_LOGS)
			.addTag(TerrestriaItemTags.RUBBER_LOGS)
			.addTag(TerrestriaItemTags.SAKURA_LOGS)
			.addTag(TerrestriaItemTags.SMALL_OAK_LOGS)
			.addTag(TerrestriaItemTags.WILLOW_LOGS)
			.addTag(TerrestriaItemTags.YUCCA_PALM_LOGS);
	}

	private void addDirt(DirtBlocks dirtBlock) {
		getOrCreateTagBuilder(ItemTags.DIRT)
			.add(dirtBlock.getDirt().asItem())
			.add(dirtBlock.getGrassBlock().asItem())
			.add(dirtBlock.getPodzol().asItem());
	}

	private void addSand(BlockItem sandItem) {
		getOrCreateTagBuilder(ItemTags.SAND).add(sandItem);
	}

	private void addStone(TagKey<Item> stoneTag, StoneItems stoneItem) {
		FabricTagBuilder<Item> stoneBuilder = getOrCreateTagBuilder(stoneTag);
		if (stoneItem.bricks != null) {
			stoneBuilder
				.add(stoneItem.bricks.full)

				.add(stoneItem.chiseledBricks)
				.add(stoneItem.crackedBricks);

			addStoneVariant(stoneItem.bricks);
		}
		if (stoneItem.cobblestone != null) {
			stoneBuilder.add(stoneItem.cobblestone.full);
			addStoneVariant(stoneItem.cobblestone);
		}
		if (stoneItem.mossyBricks != null) {
			stoneBuilder.add(stoneItem.mossyBricks.full);
			addStoneVariant(stoneItem.mossyBricks);
		}
		if (stoneItem.mossyCobblestone != null) {
			stoneBuilder.add(stoneItem.mossyCobblestone.full);
			addStoneVariant(stoneItem.mossyCobblestone);
		}
		if (stoneItem.plain != null) {
			stoneBuilder.add(stoneItem.plain.full);
			addStoneVariant(stoneItem.plain);
		}
		if (stoneItem.smooth != null) {
			stoneBuilder.add(stoneItem.smooth.full);
			addStoneVariant(stoneItem.smooth);
		}

		getOrCreateTagBuilder(ItemTags.BUTTONS).add(stoneItem.button);
		// There is no item tag for stone pressure plates...
	}

	private void addStoneVariant(StoneVariantItems stoneVariantItem) {
		getOrCreateTagBuilder(ItemTags.SLABS).add(stoneVariantItem.slab);
		getOrCreateTagBuilder(ItemTags.STAIRS).add(stoneVariantItem.stairs);
		getOrCreateTagBuilder(ItemTags.WALLS).add(stoneVariantItem.wall);
	}

	private FabricTagBuilder<Item> addWood(TagKey<Item> logTag, WoodItems woodItem) {
		FabricTagBuilder<Item> woodBuilder = getOrCreateTagBuilder(logTag);
		woodBuilder
			.add(woodItem.log)
			.add(woodItem.strippedLog);

		if (woodItem.strippedWood != null) {
			woodBuilder.add(woodItem.strippedWood);
		}
		if (woodItem.wood != null) {
			woodBuilder.add(woodItem.wood);
		}

		// Add boats if they exist via the WoodItem.
		if (woodItem.boat != null) {
			this.getOrCreateTagBuilder(ItemTags.BOATS).add(woodItem.boat);
		}
		if (woodItem.chestBoat != null) {
			this.getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(woodItem.chestBoat);
		}

		// There is no item tag for fence gates...
		getOrCreateTagBuilder(ItemTags.LEAVES).add(woodItem.leaves);
		getOrCreateTagBuilder(ItemTags.PLANKS).add(woodItem.planks);
		getOrCreateTagBuilder(ItemTags.SLABS).add(woodItem.slab);
		getOrCreateTagBuilder(ItemTags.STAIRS).add(woodItem.stairs);
		getOrCreateTagBuilder(ItemTags.SIGNS).add(woodItem.sign);
		getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(woodItem.button);
		getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(woodItem.door);
		getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(woodItem.fence);
		getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(woodItem.pressurePlate);
		getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(woodItem.slab);
		getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(woodItem.stairs);
		getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(woodItem.trapdoor);

		return(woodBuilder);
	}
}
