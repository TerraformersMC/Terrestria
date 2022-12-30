package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class TerrestriaSurfaceBuilders {
	private static final HashMap<Identifier, BiolithSurfaceBuilder> builders = new HashMap<>(8);

	public static void init() {
		builders.put(Identifier.of(Terrestria.MOD_ID, "surface/caldera"),
				new CalderaSurfaceBuilder(
						Blocks.GRASS_BLOCK.getDefaultState(),
						Blocks.DIRT.getDefaultState(),
						Blocks.STONE.getDefaultState(),
						Blocks.SAND.getDefaultState()
				).setBiomeKey(TerrestriaBiomes.CALDERA));

		builders.put(Identifier.of(Terrestria.MOD_ID, "surface/canyon"),
				new CanyonSurfaceBuilder(
						Blocks.SMOOTH_SANDSTONE.getDefaultState(),
						Blocks.SAND.getDefaultState(),
						Blocks.TERRACOTTA.getDefaultState()
				).setBiomeKey(TerrestriaBiomes.CANYON));

		builders.put(Identifier.of(Terrestria.MOD_ID, "surface/dunes"),
				new DuneSurfaceBuilder(Blocks.SAND.getDefaultState()).setBiomeKey(TerrestriaBiomes.DUNES));

		builders.put(Identifier.of(Terrestria.MOD_ID, "surface/volcanic_island"),
				new OceanIslandSurfaceBuilder(
						TerrestriaBlocks.ANDISOL.getGrassBlock().getDefaultState(),
						TerrestriaBlocks.ANDISOL.getDirt().getDefaultState(),
						TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState(),
						TerrestriaBlocks.BLACK_SAND.getDefaultState(),
						Blocks.SAND.getDefaultState(),
						true
				).setBiomeKey(TerrestriaBiomes.VOLCANIC_ISLAND));
	}

	public static HashMap<Identifier, BiolithSurfaceBuilder> getBuilders() {
		return builders;
	}
}
