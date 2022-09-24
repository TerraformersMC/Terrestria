package com.terraformersmc.terrestria.surfacebuilders;

import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.minecraft.block.Blocks;

import java.util.ArrayList;

public class TerrestriaSurfaceBuilders {
	private static final ArrayList<TerrestriaSurfaceBuilder> builders = new ArrayList<>(2);

	public static void init() {
		builders.add(new CalderaSurfaceBuilder(
				Blocks.GRASS_BLOCK.getDefaultState(),
				Blocks.DIRT.getDefaultState(),
				Blocks.STONE.getDefaultState(),
				Blocks.SAND.getDefaultState()
			).setBiomeKey(TerrestriaBiomes.CALDERA));

		builders.add(new CanyonSurfaceBuilder(
				Blocks.SMOOTH_SANDSTONE.getDefaultState(),
				Blocks.SAND.getDefaultState(),
				Blocks.TERRACOTTA.getDefaultState()
			).setBiomeKey(TerrestriaBiomes.CANYON));

		builders.add(new CliffedSurfaceBuilder(
				Blocks.GRASS_BLOCK.getDefaultState(),
				Blocks.DIRT.getDefaultState(),
				Blocks.CALCITE.getDefaultState(),
				Blocks.SAND.getDefaultState(),
				Blocks.GRAVEL.getDefaultState(),
				80
		).setBiomeKey(TerrestriaBiomes.CHALK_ISLAND));

		builders.add(new DuneSurfaceBuilder(Blocks.SAND.getDefaultState()).setBiomeKey(TerrestriaBiomes.DUNES));

		builders.add(new OceanIslandSurfaceBuilder(
				TerrestriaBlocks.ANDISOL.getGrassBlock().getDefaultState(),
				TerrestriaBlocks.ANDISOL.getDirt().getDefaultState(),
				TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState(),
				TerrestriaBlocks.BLACK_SAND.getDefaultState(),
				Blocks.SAND.getDefaultState(),
				true
			).setBiomeKey(TerrestriaBiomes.VOLCANIC_ISLAND));
	}

	public static ArrayList<TerrestriaSurfaceBuilder> getBuilders() {
		return builders;
	}
}
