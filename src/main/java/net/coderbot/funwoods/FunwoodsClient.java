package net.coderbot.funwoods;

import net.coderbot.funwoods.init.FunwoodsBlocks;
import net.coderbot.funwoods.init.FunwoodsItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;

public class FunwoodsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register(
				(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
				FunwoodsBlocks.RUBBER.leaves, FunwoodsBlocks.CYPRESS.leaves, FunwoodsBlocks.BALD_CYPRESS.leaves, FunwoodsBlocks.RAINBOW_EUCALYPTUS.leaves, FunwoodsBlocks.JAPANESE_MAPLE_SHRUB_LEAVES
		);

		ColorProviderRegistry.ITEM.register(
				(item, layer) -> FoliageColors.getColor(0.5, 1.0),
				FunwoodsItems.RUBBER.leaves, FunwoodsItems.CYPRESS.leaves, FunwoodsBlocks.BALD_CYPRESS.leaves, FunwoodsBlocks.RAINBOW_EUCALYPTUS.leaves, FunwoodsItems.JAPANESE_MAPLE_SHRUB_LEAVES
		);
	}
}
