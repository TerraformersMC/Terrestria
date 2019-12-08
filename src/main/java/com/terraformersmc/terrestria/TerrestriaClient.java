package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.block.TerraformSaplingBlock;
import com.terraformersmc.terraform.entity.TerraformBoatEntity;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaEntities;
import com.terraformersmc.terrestria.init.TerrestriaItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

// This class is an entrypoint
@SuppressWarnings("unused")
public class TerrestriaClient implements ClientModInitializer {
	private static final RenderLayer LEAVES_ITEM_LAYER = TexturedRenderLayers.getEntityCutout();
	private static final RenderLayer GRASS_BLOCK_LAYER = RenderLayer.getCutoutMipped();
	private static final RenderLayer PLANT_BLOCK_LAYER = RenderLayer.getCutout();

	private static final BlockColorProvider FOLIAGE_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor();
	private static final BlockColorProvider GRASS_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0);
	private static final ItemColorProvider FOLIAGE_ITEM_COLORS =
			(item, layer) -> FoliageColors.getColor(0.5, 1.0);
	private static final ItemColorProvider GRASS_ITEM_COLORS =
			(item, layer) -> GrassColors.getColor(0.5, 1.0);

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.BLOCK.register(
				FOLIAGE_BLOCK_COLORS,
				TerrestriaBlocks.RUBBER.leaves,
				TerrestriaBlocks.CYPRESS.leaves,
				TerrestriaBlocks.WILLOW.leaves,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves,
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES,
				TerrestriaBlocks.REDWOOD.leaves,
				TerrestriaBlocks.HEMLOCK.leaves
		);

		BlockRenderLayerMap.INSTANCE.putBlock(TerrestriaBlocks.SAKURA_LEAF_PILE, RenderLayer.getCutoutMipped());

		BlockRenderLayerMap.INSTANCE.putBlocks(
				PLANT_BLOCK_LAYER,
				TerrestriaBlocks.REDWOOD_SAPLING,
				TerrestriaBlocks.HEMLOCK_SAPLING,
				TerrestriaBlocks.RUBBER_SAPLING,
				TerrestriaBlocks.CYPRESS_SAPLING,
				TerrestriaBlocks.WILLOW_SAPLING,
				TerrestriaBlocks.JAPANESE_MAPLE_SAPLING,
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_SAPLING,
				TerrestriaBlocks.DARK_JAPANESE_MAPLE_SAPLING,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS_SAPLING,
				TerrestriaBlocks.SAKURA_SAPLING,
				TerrestriaBlocks.JUNGLE_PALM_SAPLING,
				TerrestriaBlocks.CATTAIL,
				TerrestriaBlocks.TALL_CATTAIL,
				TerrestriaBlocks.INDIAN_PAINTBRUSH,
				TerrestriaBlocks.MONSTERAS
		);
		
		addColoredGrass(TerrestriaBlocks.BASALT_GRASS_BLOCK);

		ColorProviderRegistry.ITEM.register(
				FOLIAGE_ITEM_COLORS,
				TerrestriaItems.RUBBER.leaves,
				TerrestriaItems.CYPRESS.leaves,
				TerrestriaItems.WILLOW.leaves,
				TerrestriaItems.RAINBOW_EUCALYPTUS.leaves,
				TerrestriaItems.JAPANESE_MAPLE_SHRUB_LEAVES,
				TerrestriaItems.REDWOOD.leaves,
				TerrestriaItems.HEMLOCK.leaves
		);

		BlockRenderLayerMap.INSTANCE.putItem(TerrestriaItems.SAKURA_LEAF_PILE, RenderLayer.getCutoutMipped());

		ColorProviderRegistry.ITEM.register(
				GRASS_ITEM_COLORS,
				TerrestriaItems.BASALT_GRASS_BLOCK
		);

		addBoatRenderer(TerrestriaEntities.REDWOOD_BOAT);
		addBoatRenderer(TerrestriaEntities.HEMLOCK_BOAT);
		addBoatRenderer(TerrestriaEntities.RUBBER_BOAT);
		addBoatRenderer(TerrestriaEntities.CYPRESS_BOAT);
		addBoatRenderer(TerrestriaEntities.WILLOW_BOAT);
		addBoatRenderer(TerrestriaEntities.JAPANESE_MAPLE_BOAT);
		addBoatRenderer(TerrestriaEntities.RAINBOW_EUCALYPTUS_BOAT);
		addBoatRenderer(TerrestriaEntities.SAKURA_BOAT);
	}

	private void addColoredGrass(Block grass) {
		BlockRenderLayerMap.INSTANCE.putBlock(grass, GRASS_BLOCK_LAYER);
		ColorProviderRegistry.BLOCK.register(GRASS_BLOCK_COLORS, grass);
	}
	
	private void addBoatRenderer(EntityType<TerraformBoatEntity> boat) {
		EntityRendererRegistry.INSTANCE.register(boat, (dispatcher, context) -> new BoatEntityRenderer(dispatcher));
	}
}
