package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.tag.TerrestriaBlockTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;

// This class is an entrypoint
@Environment(EnvType.CLIENT)
public class TerrestriaClient implements ClientModInitializer {
	private static final ChunkSectionLayer GRASS_BLOCK_LAYER = ChunkSectionLayer.CUTOUT;
	private static final ChunkSectionLayer PLANT_BLOCK_LAYER = ChunkSectionLayer.CUTOUT;
	private static final ChunkSectionLayer DOOR_BLOCK_LAYER = ChunkSectionLayer.CUTOUT;

	private static final BlockColor FOLIAGE_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.FOLIAGE_DEFAULT;
	private static final BlockColor GRASS_BLOCK_COLORS =
			(block, world, pos, layer) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5, 1.0);

	@Override
	public void onInitializeClient() {
		// Load the client config if it hasn't been loaded already
		Terrestria.getConfigManager().getClientConfig();

		ParticleRenderEvents.ALLOW_BLOCK_DUST_TINT.register((state, world, pos) ->
				!state.is(TerrestriaBlocks.ANDISOL.getGrassBlock()) &&
				!state.is(TerrestriaBlockTags.SMALL_OAK_LOGS));

		ColorProviderRegistry.BLOCK.register(
				FOLIAGE_BLOCK_COLORS,
				TerrestriaBlocks.RUBBER.leaves,
				TerrestriaBlocks.CYPRESS.leaves,
				TerrestriaBlocks.WILLOW.leaves,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.leaves,
				TerrestriaBlocks.JAPANESE_MAPLE_SHRUB_LEAVES,
				TerrestriaBlocks.REDWOOD.leaves,
				TerrestriaBlocks.HEMLOCK.leaves,
				TerrestriaBlocks.SMALL_OAK_LOG,
				TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG
		);

		//noinspection ConstantConditions
		BlockRenderLayerMap.putBlock(TerrestriaBlocks.SAKURA.leafPile, ChunkSectionLayer.CUTOUT);

		BlockRenderLayerMap.putBlocks(
				DOOR_BLOCK_LAYER,
				TerrestriaBlocks.REDWOOD.door,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.door,
				TerrestriaBlocks.CYPRESS.door,
				TerrestriaBlocks.WILLOW.door,
				TerrestriaBlocks.JAPANESE_MAPLE.door,
				TerrestriaBlocks.RAINBOW_EUCALYPTUS.trapdoor,
				TerrestriaBlocks.CYPRESS.trapdoor,
				TerrestriaBlocks.WILLOW.trapdoor
		);

		BlockRenderLayerMap.putBlocks(
				PLANT_BLOCK_LAYER,
				// Needs to be transparent because of the log cutout part.
				// TODO: Edit the model so that it can be conditionally transparent like actual leaves!
				// Currently they will always be transparent.
				TerrestriaBlocks.SAKURA.log,
				TerrestriaBlocks.SAKURA.strippedLog,
				TerrestriaBlocks.SMALL_OAK_LOG,
				TerrestriaBlocks.STRIPPED_SMALL_OAK_LOG,
				TerrestriaBlocks.YUCCA_PALM.log,
				TerrestriaBlocks.YUCCA_PALM.strippedLog,
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
				TerrestriaBlocks.BRYCE_SAPLING,
				TerrestriaBlocks.YUCCA_PALM_SAPLING,
				TerrestriaBlocks.CATTAIL,
				TerrestriaBlocks.TALL_CATTAIL,
				TerrestriaBlocks.INDIAN_PAINTBRUSH,
				TerrestriaBlocks.MONSTERAS,
				TerrestriaBlocks.ALOE_VERA,
				TerrestriaBlocks.AGAVE,
				TerrestriaBlocks.TINY_CACTUS,
				TerrestriaBlocks.DEAD_GRASS,
				TerrestriaBlocks.SAGUARO_CACTUS_SAPLING
		);

		BlockRenderLayerMap.putBlocks(
				PLANT_BLOCK_LAYER,
				TerrestriaBlocks.POTTED_BRYCE_SAPLING,
				TerrestriaBlocks.POTTED_REDWOOD_SAPLING,
				TerrestriaBlocks.POTTED_HEMLOCK_SAPLING,
				TerrestriaBlocks.POTTED_RUBBER_SAPLING,
				TerrestriaBlocks.POTTED_CYPRESS_SAPLING,
				TerrestriaBlocks.POTTED_WILLOW_SAPLING,
				TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SAPLING,
				TerrestriaBlocks.POTTED_JAPANESE_MAPLE_SHRUB_SAPLING,
				TerrestriaBlocks.POTTED_DARK_JAPANESE_MAPLE_SAPLING,
				TerrestriaBlocks.POTTED_RAINBOW_EUCALYPTUS_SAPLING,
				TerrestriaBlocks.POTTED_SAKURA_SAPLING,
				TerrestriaBlocks.POTTED_JUNGLE_PALM_SAPLING,
				TerrestriaBlocks.POTTED_INDIAN_PAINTBRUSH,
				TerrestriaBlocks.POTTED_MONSTERAS,
				TerrestriaBlocks.POTTED_AGAVE,
				TerrestriaBlocks.POTTED_ALOE_VERA,
				TerrestriaBlocks.POTTED_TINY_CACTUS,
				TerrestriaBlocks.POTTED_SAGUARO_CACTUS_SAPLING,
				TerrestriaBlocks.POTTED_YUCCA_PALM_SAPLING
		);

		addColoredGrass(TerrestriaBlocks.ANDISOL.getGrassBlock());

		registerEntityRenderers();
	}

	private void registerEntityRenderers() {
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "redwood"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "hemlock"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "rubber"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "cypress"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "willow"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "japanese_maple"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "rainbow_eucalyptus"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "sakura"));
		TerraformBoatClientHelper.registerModelLayers(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "yucca_palm"));
	}

	private void addColoredGrass(Block grass) {
		BlockRenderLayerMap.putBlock(grass, GRASS_BLOCK_LAYER);
		ColorProviderRegistry.BLOCK.register(GRASS_BLOCK_COLORS, grass);
	}
}
