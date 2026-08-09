package com.terraformersmc.terrestria;

import com.terraformersmc.terraform.boat.api.TerraformBoatClientHelper;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.tag.TerrestriaBlockItemTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Objects;

// This class is an entrypoint
@NullMarked
public class TerrestriaClient implements ClientModInitializer {
	private static final List<BlockTintSource> FOLIAGE_BLOCK_COLORS = List.of(new BlockTintSource() {
		@Override
		public int color(BlockState state) {
			return FoliageColor.FOLIAGE_DEFAULT;
		}

		@Override
		public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
			return BiomeColors.getAverageFoliageColor(level, pos);
		}
	});
	private static final List<BlockTintSource> GRASS_BLOCK_COLORS = List.of(new BlockTintSource() {
		@Override
		public int color(BlockState state) {
			return GrassColor.get(0.5, 1.0);
		}

		@Override
		public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
			return BiomeColors.getAverageGrassColor(level, pos);
		}
	});

	@Override
	public void onInitializeClient() {
		Objects.requireNonNull(TerrestriaBlocks.ANDISOL.grassBlock());

		// Load the client config if it hasn't been loaded already
		Terrestria.getConfigManager().getClientConfig();

		ParticleRenderEvents.ALLOW_TERRAIN_PARTICLE_TINT.register((state, level, pos) ->
				!state.is(TerrestriaBlocks.ANDISOL.grassBlock()) &&
				!state.is(TerrestriaBlockItemTags.SMALL_OAK_LOGS.block()));

		BlockColorRegistry.register(
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

		BlockColorRegistry.register(
				GRASS_BLOCK_COLORS,
				TerrestriaBlocks.ANDISOL.grassBlock()
		);

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
}
