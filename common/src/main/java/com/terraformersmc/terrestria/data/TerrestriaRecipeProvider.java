package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlockFamilies;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.StoneVariantItems;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class TerrestriaRecipeProvider extends FabricRecipeProvider {
	protected TerrestriaRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
		return new RecipeGenerator(registryLookup, exporter) {
			@Override
			public void generate() {
				// misc. recipes
				createShapeless(RecipeCategory.DECORATIONS, TerrestriaItems.BRYCE_SAPLING, 1)
						.input(Items.OAK_SAPLING)
						.input(Items.STICK)
						.criterion("has_bryce_sapling", this.conditionsFromItem(TerrestriaItems.BRYCE_SAPLING))
						.offerTo(exporter, "bryce_sapling_from_oak_sapling");

				CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(TerrestriaItems.SAGUARO_CACTUS), RecipeCategory.MISC, Items.GREEN_DYE, 1.0f, 200)
						.criterion("has_cactus", this.conditionsFromItem(TerrestriaItems.SAGUARO_CACTUS))
						.offerTo(exporter);

				CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(TerrestriaItems.TINY_CACTUS), RecipeCategory.MISC, Items.LIME_DYE, 1.0f, 200)
						.criterion("has_tiny_cactus", this.conditionsFromItem(TerrestriaItems.TINY_CACTUS))
						.offerTo(exporter);

				createShaped(RecipeCategory.TOOLS, TerrestriaItems.LOG_TURNER, 1)
						.pattern("ss")
						.pattern(" s")
						.pattern("ss")
						.input('s', Items.STICK)
						.criterion("has_sticks", this.conditionsFromItem(Items.STICK))
						.offerTo(exporter);

				offerSingleOutputShapelessRecipe(Items.RED_DYE, TerrestriaItems.INDIAN_PAINTBRUSH, "dyes");


				// wood building block recipes
				generateWood(exporter, TerrestriaBlockFamilies.CYPRESS, TerrestriaItems.CYPRESS, TerrestriaItemTags.CYPRESS_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.HEMLOCK, TerrestriaItems.HEMLOCK, TerrestriaItemTags.HEMLOCK_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.JAPANESE_MAPLE, TerrestriaItems.JAPANESE_MAPLE, TerrestriaItemTags.JAPANESE_MAPLE_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.RAINBOW_EUCALYPTUS, TerrestriaItems.RAINBOW_EUCALYPTUS, TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.REDWOOD, TerrestriaItems.REDWOOD, TerrestriaItemTags.REDWOOD_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.RUBBER, TerrestriaItems.RUBBER, TerrestriaItemTags.RUBBER_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.SAKURA, TerrestriaItems.SAKURA, TerrestriaItemTags.SAKURA_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.WILLOW, TerrestriaItems.WILLOW, TerrestriaItemTags.WILLOW_LOGS);
				generateWood(exporter, TerrestriaBlockFamilies.YUCCA_PALM, TerrestriaItems.YUCCA_PALM, TerrestriaItemTags.YUCCA_PALM_LOGS);

				// stone building block recipes
				generateStone(exporter, TerrestriaItems.VOLCANIC_ROCK);
			}

			private void generateWood(RecipeExporter exporter, BlockFamily blockFamily, WoodItems woodItem, TagKey<Item> logsTag) {
				// We don't really use feature sets, so this is good enough...
				FeatureSet enabledFeatures = FeatureSet.of(FeatureFlags.VANILLA);

				generateFamily(blockFamily, enabledFeatures);

				offerPlanksRecipe(woodItem.planks, logsTag, 4);

				// Some WoodItems with no real wood have wood set to log
				if (woodItem.hasWood()) {
					offerBarkBlockRecipe(woodItem.wood, woodItem.log);
					offerBarkBlockRecipe(woodItem.strippedWood, woodItem.strippedLog);
				}

				// Boats are an optional wood feature
				if (woodItem.hasBoat()) {
					offerBoatRecipe(woodItem.boat, woodItem.planks);
					offerChestBoatRecipe(woodItem.chestBoat, woodItem.boat);
				}

				offerHangingSignRecipe(woodItem.hangingSign, woodItem.strippedLog);
				offerShelfRecipe(woodItem.shelf, woodItem.strippedLog);

				// Leaf piles are an optional wood feature
				if (woodItem.hasLeafPile()) {
					createShaped(RecipeCategory.DECORATIONS, woodItem.leafPile, 16)
							.pattern("LL")
							.input('L', woodItem.leaves)
							.criterion("has_leaves", this.conditionsFromItem(woodItem.leaves))
							.offerTo(exporter);
				}
			}

			private void generateStone(RecipeExporter exporter, StoneItems stoneItem) {
				if (stoneItem.bricks != null) {
					generateStoneVariant(exporter, stoneItem.bricks, stoneItem.plain.full);

					createShaped(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, 4)
							.group("bricks")
							.pattern("SS")
							.pattern("SS")
							.input('S', stoneItem.plain.full)
							.criterion("has_stone", this.conditionsFromItem(stoneItem.plain.full))
							.offerTo(exporter);
					offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, stoneItem.plain.full);

					offerChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.slab);
					offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.full);
					offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.plain.full);

					offerCrackingRecipe(stoneItem.crackedBricks, stoneItem.bricks.full);
				}
				if (stoneItem.cobblestone != null) {
					generateStoneVariant(exporter, stoneItem.cobblestone, null);
				}
				if (stoneItem.mossyBricks != null) {
					generateStoneVariant(exporter, stoneItem.mossyBricks, null);

					createShapeless(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyBricks.full, 1)
							.group("mossy_bricks")
							.input(stoneItem.bricks.full)
							.input(TerrestriaItemTags.MOSSY_INGREDIENTS)
							.criterion("has_mossy_ingredients", this.conditionsFromTag(TerrestriaItemTags.MOSSY_INGREDIENTS))
							.offerTo(exporter);
				}
				if (stoneItem.mossyCobblestone != null) {
					generateStoneVariant(exporter, stoneItem.mossyCobblestone, null);

					createShapeless(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyCobblestone.full, 1)
							.group("mossy_cobblestone")
							.input(stoneItem.cobblestone.full)
							.input(TerrestriaItemTags.MOSSY_INGREDIENTS)
							.criterion("has_mossy_ingredients", this.conditionsFromTag(TerrestriaItemTags.MOSSY_INGREDIENTS))
							.offerTo(exporter);
				}
				if (stoneItem.plain != null) {
					generateStoneVariant(exporter, stoneItem.plain, null);

					if (stoneItem.cobblestone != null) {
						offerSmelting(Collections.singletonList(stoneItem.cobblestone.full),
								RecipeCategory.BUILDING_BLOCKS,
								stoneItem.plain.full,
								0.1f, 200, "stone");
					}

					createShapeless(RecipeCategory.REDSTONE, stoneItem.button, 1)
							.group("stone_button")
							.input(stoneItem.plain.full)
							.criterion("has_stone", this.conditionsFromItem(stoneItem.plain.full))
							.offerTo(exporter);

					createShaped(RecipeCategory.REDSTONE, stoneItem.pressurePlate, 1)
							.group("stone_pressure_plate")
							.pattern("SS")
							.input('S', stoneItem.plain.full)
							.criterion("has_stone", this.conditionsFromItem(stoneItem.plain.full))
							.offerTo(exporter);
				}
				if (stoneItem.smooth != null) {
					generateStoneVariant(exporter, stoneItem.smooth, null);

					if (stoneItem.plain != null) {
						offerSmelting(Collections.singletonList(stoneItem.plain.full),
								RecipeCategory.BUILDING_BLOCKS,
								stoneItem.smooth.full,
								0.1f, 200, "stone");
					}
				}
			}

			private void generateStoneVariant(RecipeExporter exporter, StoneVariantItems stoneVariantItem, @Nullable BlockItem cutPlainItem) {
				offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, stoneVariantItem.full);
				createStairsRecipe(stoneVariantItem.stairs, Ingredient.ofItems(stoneVariantItem.full))
						.criterion("has_stone", this.conditionsFromItem(stoneVariantItem.full))
						.offerTo(exporter);  // ?? so lame there is no offerStairsRecipe() !!
				offerWallRecipe(RecipeCategory.DECORATIONS, stoneVariantItem.wall, stoneVariantItem.full);

				offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, stoneVariantItem.full, 2);
				offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.stairs, stoneVariantItem.full);
				offerStonecuttingRecipe(RecipeCategory.DECORATIONS, stoneVariantItem.wall, stoneVariantItem.full);

				if (cutPlainItem != null) {
					offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, cutPlainItem, 2);
					offerStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.stairs, cutPlainItem);
					offerStonecuttingRecipe(RecipeCategory.DECORATIONS, stoneVariantItem.wall, cutPlainItem);
				}
			}
		};
	}

	@Override
	public String getName() {
		return "Terrestria Recipes";
	}

	@Override
	protected Identifier getRecipeIdentifier(Identifier identifier) {
		return Identifier.of(Terrestria.MOD_ID, identifier.getPath());
	}
}
