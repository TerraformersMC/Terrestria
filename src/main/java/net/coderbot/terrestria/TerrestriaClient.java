package net.coderbot.terrestria;

import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.coderbot.terrestria.init.TerrestriaItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

// This class is an entrypoint
@SuppressWarnings("unused")
public class TerrestriaClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register(
				(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(),
				TerrestriaBlocks.RUBBER.leaves,
				TerrestriaBlocks.CYPRESS.leaves,
				TerrestriaBlocks.WILLOW.leaves,
				TerrestriaBlocks.BALD_CYPRESS.leaves,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves,
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES,
				TerrestriaBlocks.REDWOOD.leaves,
				TerrestriaBlocks.HEMLOCK.leaves
		);

		ColorProviderRegistry.BLOCK.register(
				(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0),
				TerrestriaBlocks.BASALT_GRASS_BLOCK
		);

		ColorProviderRegistry.ITEM.register(
				(item, layer) -> FoliageColors.getColor(0.5, 1.0),
				TerrestriaItems.RUBBER.leaves,
				TerrestriaItems.CYPRESS.leaves,
				TerrestriaItems.WILLOW.leaves,
				TerrestriaBlocks.BALD_CYPRESS.leaves,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves,
				TerrestriaItems.JAPANESE_MAPLE_SHRUB_LEAVES,
				TerrestriaItems.REDWOOD.leaves,
				TerrestriaItems.HEMLOCK.leaves
		);

		ColorProviderRegistry.ITEM.register(
				(item, layer) -> GrassColors.getColor(0.5, 1.0),
				TerrestriaItems.BASALT_GRASS_BLOCK
		);
	}
}
