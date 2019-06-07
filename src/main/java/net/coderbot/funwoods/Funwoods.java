package net.coderbot.funwoods;

import net.coderbot.funwoods.block.RubberSaplingBlock;
import net.coderbot.funwoods.block.RubberSaplingGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Funwoods implements ModInitializer {
	public static ItemGroup ITEM_GROUP;
	public static RubberSaplingBlock RUBBER_SAPLING;
	public static BlockItem RUBBER_SAPLING_ITEM;


	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("funwoods", "items"), () -> new ItemStack(RUBBER_SAPLING));

		RubberSaplingGenerator.TreeDefinition rubber = new RubberSaplingGenerator.TreeDefinition();
		rubber.leaves = Blocks.OAK_LEAVES.getDefaultState();
		rubber.wood = Blocks.OAK_LOG.getDefaultState();

		RUBBER_SAPLING = Registry.register(Registry.BLOCK, "funwoods:rubber_sapling", new RubberSaplingBlock(rubber, Block.Settings.copy(Blocks.OAK_SAPLING)));
		RUBBER_SAPLING_ITEM = Registry.register(Registry.ITEM, "funwoods:rubber_sapling", new BlockItem(RUBBER_SAPLING, new Item.Settings().itemGroup(ITEM_GROUP)));
	}
}
