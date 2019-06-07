package net.coderbot.funwoods;

import net.coderbot.funwoods.init.FunwoodsBlocks;
import net.coderbot.funwoods.init.FunwoodsItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Funwoods implements ModInitializer {
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("funwoods", "items"), () -> new ItemStack(FunwoodsItems.RUBBER_SAPLING));

		FunwoodsBlocks.init();
		FunwoodsItems.init();
	}
}
