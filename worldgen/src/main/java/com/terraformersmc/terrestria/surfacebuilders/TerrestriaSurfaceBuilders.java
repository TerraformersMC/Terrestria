package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.Identifier;

import java.util.HashMap;

public class TerrestriaSurfaceBuilders {
	private static final HashMap<Identifier, BiolithSurfaceBuilder> builders = new HashMap<>(8);

	public static void init() {
		builders.put(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "surface/caldera"),
				new CalderaSurfaceBuilder(
						Blocks.GRASS_BLOCK.defaultBlockState(),
						Blocks.DIRT.defaultBlockState(),
						Blocks.STONE.defaultBlockState(),
						Blocks.SAND.defaultBlockState()
				).setBiomeKey(TerrestriaBiomes.CALDERA));

		builders.put(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "surface/canyon"),
				new CanyonSurfaceBuilder(
						Blocks.SMOOTH_SANDSTONE.defaultBlockState(),
						Blocks.SAND.defaultBlockState(),
						Blocks.TERRACOTTA.defaultBlockState()
				).setBiomeKey(TerrestriaBiomes.CANYON));

		builders.put(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "surface/dunes"),
				new DuneSurfaceBuilder(Blocks.SAND.defaultBlockState()).setBiomeKey(TerrestriaBiomes.DUNES));

		builders.put(Identifier.fromNamespaceAndPath(Terrestria.MOD_ID, "surface/volcanic_island"),
				new OceanIslandSurfaceBuilder(
						TerrestriaBlocks.ANDISOL.getGrassBlock().defaultBlockState(),
						TerrestriaBlocks.ANDISOL.getDirt().defaultBlockState(),
						TerrestriaBlocks.VOLCANIC_ROCK.plain.full.defaultBlockState(),
						TerrestriaBlocks.VOLCANIC_SAND.defaultBlockState(),
						Blocks.SAND.defaultBlockState()
				).setBiomeKey(TerrestriaBiomes.VOLCANIC_ISLAND));
	}

	public static HashMap<Identifier, BiolithSurfaceBuilder> getBuilders() {
		return builders;
	}
}
