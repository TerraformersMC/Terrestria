package com.terraformersmc.terrestria.init.helpers;

import com.terraformersmc.terraform.entity.TerraformBoatEntity;
import com.terraformersmc.terraform.item.TerraformBoatItem;
import com.terraformersmc.terraform.util.RecipeUtil;
import com.terraformersmc.terrestria.Terrestria;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class TerrestriaRegistry {

	public static BlockItem registerBlockItem(String name, Block block) {
		BlockItem item = new BlockItem(block, new Item.Settings().group(Terrestria.itemGroup));
		item.appendBlocks(Item.BLOCK_ITEMS, item);

		RecipeUtil.registerCompostableBlock(block);

		return Registry.register(Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), item);
	}

	public static SignItem registerSignItem(String name, Block standing, Block wall) {
		return Registry.register(Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), new SignItem(new Item.Settings().group(Terrestria.itemGroup), standing, wall));
	}

	public static TerraformBoatItem registerBoatItem(String name, Supplier<EntityType<TerraformBoatEntity>> boatType) {
		return Registry.register(Registry.ITEM, new Identifier(Terrestria.MOD_ID, name), new TerraformBoatItem(boatType, new Item.Settings().group(Terrestria.itemGroup).maxCount(1)));
	}

	public static <T extends Block> T register(String name, T block) {
		return Registry.register(Registry.BLOCK, new Identifier(Terrestria.MOD_ID, name), block);
	}
}
