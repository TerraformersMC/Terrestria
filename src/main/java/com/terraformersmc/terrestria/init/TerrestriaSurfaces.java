package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.surface.BeachSurfaceBuilder;
import com.terraformersmc.terraform.surface.CliffSurfaceBuilder;
import com.terraformersmc.terraform.surface.CliffSurfaceConfig;
import com.terraformersmc.terraform.surface.FloodingBeachSurfaceBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.surface.CanyonSurfaceBuilder;
import com.terraformersmc.terrestria.surface.CanyonSurfaceConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

// This class exports public surface constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaSurfaces {
	public static FloodingBeachSurfaceBuilder CALDERA;
	public static BeachSurfaceBuilder BASALT_BEACH;
	public static BeachSurfaceBuilder BEACH;
	public static CliffSurfaceBuilder BASALT_CLIFF;
	public static CanyonSurfaceBuilder CANYON_CLIFF;
	public static CliffSurfaceConfig BASALT_CONFIG;
	public static CliffSurfaceBuilder SANDSTONE_CLIFF;
	public static CanyonSurfaceConfig SANDSTONE_CLIFF_CONFIG;
	public static CliffSurfaceConfig SANDSTONE_CONFIG;
	public static TernarySurfaceConfig ALPS_CONFIG;

	public static void init() {
		CALDERA = register("caldera", new FloodingBeachSurfaceBuilder(TernarySurfaceConfig::deserialize, 100, v -> Blocks.SAND.getDefaultState()));

		BASALT_BEACH = register("basalt_beach", new BeachSurfaceBuilder(
				TernarySurfaceConfig::deserialize,
				62,
				v -> v > 1.0 ? TerrestriaBlocks.BASALT_SAND.getDefaultState() : Blocks.SAND.getDefaultState()
		));

		BEACH = register("beach", new BeachSurfaceBuilder(TernarySurfaceConfig::deserialize, 62, v -> Blocks.SAND.getDefaultState()));

		BASALT_CLIFF = register("basalt_cliff", new CliffSurfaceBuilder(CliffSurfaceConfig::deserialize, 62, BASALT_BEACH));

		SANDSTONE_CLIFF = register("canyon_cliff", new CliffSurfaceBuilder(CliffSurfaceConfig::deserialize, 62, BEACH));

		CANYON_CLIFF = register("sandstone_cliff", new CanyonSurfaceBuilder(CanyonSurfaceConfig::deserialize, 62, BEACH));

		BASALT_CONFIG = new CliffSurfaceConfig(
				TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState(),
				TerrestriaBlocks.BASALT_DIRT.getDefaultState(),
				Blocks.SAND.getDefaultState(),
				TerrestriaBlocks.BASALT.plain.full.getDefaultState()
		);

		SANDSTONE_CLIFF_CONFIG = new CanyonSurfaceConfig(
			Blocks.SAND.getDefaultState(),
			Blocks.TERRACOTTA.getDefaultState(),
			Blocks.SAND.getDefaultState(),
			Blocks.SMOOTH_SANDSTONE.getDefaultState()
		);

		SANDSTONE_CONFIG = new CliffSurfaceConfig(
			Blocks.SAND.getDefaultState(),
			Blocks.TERRACOTTA.getDefaultState(),
			Blocks.SAND.getDefaultState(),
			Blocks.SMOOTH_SANDSTONE.getDefaultState()
		);

		ALPS_CONFIG = new TernarySurfaceConfig(
				Blocks.SNOW_BLOCK.getDefaultState(),
				Blocks.SNOW_BLOCK.getDefaultState(),
				Blocks.STONE.getDefaultState()
		);
	}

	public static <T extends SurfaceBuilder<SC>, SC extends SurfaceConfig> T register(String name, T surface) {
		return Registry.register(Registry.SURFACE_BUILDER, new Identifier(Terrestria.MOD_ID, name), surface);
	}
}
