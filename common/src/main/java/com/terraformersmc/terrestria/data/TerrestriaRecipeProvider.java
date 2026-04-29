package com.terraformersmc.terrestria.data;

import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBlockFamilies;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import com.terraformersmc.terrestria.init.helpers.StoneItems;
import com.terraformersmc.terrestria.init.helpers.StoneVariantItems;
import com.terraformersmc.terrestria.init.helpers.WoodItems;
import com.terraformersmc.terrestria.tag.TerrestriaItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@NullMarked
public class TerrestriaRecipeProvider extends FabricRecipeProvider {
	protected TerrestriaRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
		return new RecipeProvider(registryLookup, exporter) {
			@Override
			public void buildRecipes() {
				// misc. recipes
				shapeless(RecipeCategory.DECORATIONS, TerrestriaItems.BRYCE_SAPLING, 1)
						.requires(Items.OAK_SAPLING)
						.requires(Items.STICK)
						.unlockedBy("has_bryce_sapling", this.has(TerrestriaItems.BRYCE_SAPLING))
						.save(output, "bryce_sapling_from_oak_sapling");

				SimpleCookingRecipeBuilder.smelting(Ingredient.of(TerrestriaItems.SAGUARO_CACTUS), RecipeCategory.MISC, CookingBookCategory.MISC, Items.GREEN_DYE, 1.0f, 200)
						.unlockedBy("has_cactus", this.has(TerrestriaItems.SAGUARO_CACTUS))
						.save(output);

				SimpleCookingRecipeBuilder.smelting(Ingredient.of(TerrestriaItems.TINY_CACTUS), RecipeCategory.MISC, CookingBookCategory.MISC, Items.LIME_DYE, 1.0f, 200)
						.unlockedBy("has_tiny_cactus", this.has(TerrestriaItems.TINY_CACTUS))
						.save(output);

				shaped(RecipeCategory.TOOLS, TerrestriaItems.LOG_TURNER, 1)
						.pattern("ss")
						.pattern(" s")
						.pattern("ss")
						.define('s', Items.STICK)
						.unlockedBy("has_sticks", this.has(Items.STICK))
						.save(output);

				oneToOneConversionRecipe(Items.RED_DYE, TerrestriaItems.INDIAN_PAINTBRUSH, "dyes");


				// wood building block recipes
				generateWood(output, TerrestriaBlockFamilies.CYPRESS, TerrestriaItems.CYPRESS, TerrestriaItemTags.CYPRESS_LOGS);
				generateWood(output, TerrestriaBlockFamilies.HEMLOCK, TerrestriaItems.HEMLOCK, TerrestriaItemTags.HEMLOCK_LOGS);
				generateWood(output, TerrestriaBlockFamilies.JAPANESE_MAPLE, TerrestriaItems.JAPANESE_MAPLE, TerrestriaItemTags.JAPANESE_MAPLE_LOGS);
				generateWood(output, TerrestriaBlockFamilies.RAINBOW_EUCALYPTUS, TerrestriaItems.RAINBOW_EUCALYPTUS, TerrestriaItemTags.RAINBOW_EUCALYPTUS_LOGS);
				generateWood(output, TerrestriaBlockFamilies.REDWOOD, TerrestriaItems.REDWOOD, TerrestriaItemTags.REDWOOD_LOGS);
				generateWood(output, TerrestriaBlockFamilies.RUBBER, TerrestriaItems.RUBBER, TerrestriaItemTags.RUBBER_LOGS);
				generateWood(output, TerrestriaBlockFamilies.SAKURA, TerrestriaItems.SAKURA, TerrestriaItemTags.SAKURA_LOGS);
				generateWood(output, TerrestriaBlockFamilies.WILLOW, TerrestriaItems.WILLOW, TerrestriaItemTags.WILLOW_LOGS);
				generateWood(output, TerrestriaBlockFamilies.YUCCA_PALM, TerrestriaItems.YUCCA_PALM, TerrestriaItemTags.YUCCA_PALM_LOGS);

				// stone building block recipes
				generateStone(output, TerrestriaItems.VOLCANIC_ROCK);
			}

			private void generateWood(RecipeOutput exporter, BlockFamily blockFamily, WoodItems woodItem, TagKey<Item> logsTag) {
				// We don't really use feature sets, so this is good enough...
				FeatureFlagSet enabledFeatures = FeatureFlagSet.of(FeatureFlags.VANILLA);

				generateRecipes(blockFamily, enabledFeatures);

				planksFromLogs(woodItem.planks, logsTag, 4);

				// Some WoodItems with no real wood have wood set to log
				if (woodItem.hasWood()) {
					woodFromLogs(woodItem.wood, woodItem.log);
					woodFromLogs(woodItem.strippedWood, woodItem.strippedLog);
				}

				// Boats are an optional wood feature
				if (woodItem.hasBoat()) {
					woodenBoat(woodItem.boat, woodItem.planks);
					chestBoat(woodItem.chestBoat, woodItem.boat);
				}

				hangingSign(woodItem.hangingSign, woodItem.strippedLog);
				shelf(woodItem.shelf, woodItem.strippedLog);

				// Leaf piles are an optional wood feature
				if (woodItem.hasLeafPile()) {
					shaped(RecipeCategory.DECORATIONS, woodItem.leafPile, 16)
							.pattern("LL")
							.define('L', woodItem.leaves)
							.unlockedBy("has_leaves", this.has(woodItem.leaves))
							.save(exporter);
				}
			}

			private void generateStone(RecipeOutput exporter, StoneItems stoneItem) {
				if (stoneItem.bricks != null) {
					generateStoneVariant(exporter, stoneItem.bricks, stoneItem.plain.full);

					shaped(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, 4)
							.group("bricks")
							.pattern("SS")
							.pattern("SS")
							.define('S', stoneItem.plain.full)
							.unlockedBy("has_stone", this.has(stoneItem.plain.full))
							.save(exporter);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneItem.bricks.full, stoneItem.plain.full);

					chiseled(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.slab);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.bricks.full);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneItem.chiseledBricks, stoneItem.plain.full);

					smeltingResultFromBase(stoneItem.crackedBricks, stoneItem.bricks.full);
				}
				if (stoneItem.cobblestone != null) {
					generateStoneVariant(exporter, stoneItem.cobblestone, null);
				}
				if (stoneItem.mossyBricks != null) {
					generateStoneVariant(exporter, stoneItem.mossyBricks, null);

					shapeless(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyBricks.full, 1)
							.group("mossy_bricks")
							.requires(stoneItem.bricks.full)
							.requires(TerrestriaItemTags.MOSSY_INGREDIENTS)
							.unlockedBy("has_mossy_ingredients", this.has(TerrestriaItemTags.MOSSY_INGREDIENTS))
							.save(exporter);
				}
				if (stoneItem.mossyCobblestone != null) {
					generateStoneVariant(exporter, stoneItem.mossyCobblestone, null);

					shapeless(RecipeCategory.BUILDING_BLOCKS, stoneItem.mossyCobblestone.full, 1)
							.group("mossy_cobblestone")
							.requires(stoneItem.cobblestone.full)
							.requires(TerrestriaItemTags.MOSSY_INGREDIENTS)
							.unlockedBy("has_mossy_ingredients", this.has(TerrestriaItemTags.MOSSY_INGREDIENTS))
							.save(exporter);
				}
				if (stoneItem.plain != null) {
					generateStoneVariant(exporter, stoneItem.plain, null);

					if (stoneItem.cobblestone != null) {
						oreSmelting(Collections.singletonList(stoneItem.cobblestone.full),
								RecipeCategory.BUILDING_BLOCKS,
								CookingBookCategory.BLOCKS,
								stoneItem.plain.full,
								0.1f, 200, "stone");
					}

					shapeless(RecipeCategory.REDSTONE, stoneItem.button, 1)
							.group("stone_button")
							.requires(stoneItem.plain.full)
							.unlockedBy("has_stone", this.has(stoneItem.plain.full))
							.save(exporter);

					shaped(RecipeCategory.REDSTONE, stoneItem.pressurePlate, 1)
							.group("stone_pressure_plate")
							.pattern("SS")
							.define('S', stoneItem.plain.full)
							.unlockedBy("has_stone", this.has(stoneItem.plain.full))
							.save(exporter);
				}
				if (stoneItem.smooth != null) {
					generateStoneVariant(exporter, stoneItem.smooth, null);

					if (stoneItem.plain != null) {
						oreSmelting(Collections.singletonList(stoneItem.plain.full),
								RecipeCategory.BUILDING_BLOCKS,
							CookingBookCategory.BLOCKS,
								stoneItem.smooth.full,
								0.1f, 200, "stone");
					}
				}
			}

			private void generateStoneVariant(RecipeOutput exporter, StoneVariantItems stoneVariantItem, @Nullable BlockItem cutPlainItem) {
				slab(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, stoneVariantItem.full);
				stairBuilder(stoneVariantItem.stairs, Ingredient.of(stoneVariantItem.full))
						.unlockedBy("has_stone", this.has(stoneVariantItem.full))
						.save(exporter);  // ?? so lame there is no offerStairsRecipe() !!
				wall(RecipeCategory.DECORATIONS, stoneVariantItem.wall, stoneVariantItem.full);

				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, stoneVariantItem.full, 2);
				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.stairs, stoneVariantItem.full);
				stonecutterResultFromBase(RecipeCategory.DECORATIONS, stoneVariantItem.wall, stoneVariantItem.full);

				if (cutPlainItem != null) {
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.slab, cutPlainItem, 2);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stoneVariantItem.stairs, cutPlainItem);
					stonecutterResultFromBase(RecipeCategory.DECORATIONS, stoneVariantItem.wall, cutPlainItem);
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
		return Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, identifier.getPath());
	}
}
