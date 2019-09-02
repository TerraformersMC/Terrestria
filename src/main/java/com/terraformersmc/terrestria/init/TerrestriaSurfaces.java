package com.terraformersmc.terrestria.init;

import com.terraformersmc.terraform.surface.BeachSurfaceBuilder;
import com.terraformersmc.terraform.surface.CliffSurfaceBuilder;
import com.terraformersmc.terraform.surface.CliffSurfaceConfig;
import com.terraformersmc.terraform.surface.FloodingBeachSurfaceBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.surface.RandomSurfaceBuilder;
import com.terraformersmc.terrestria.surface.RandomSurfaceConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

// This class exports public surface constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaSurfaces {
	public static RandomSurfaceBuilder RANDOM_BUILDER;
	public static FloodingBeachSurfaceBuilder CALDERA;
	public static BeachSurfaceBuilder BASALT_BEACH;
	public static BeachSurfaceBuilder BEACH;
	public static CliffSurfaceBuilder CLIFF;
	public static CliffSurfaceConfig BASALT_CONFIG;
	public static TernarySurfaceConfig ALPS_CONFIG;
	public static RandomSurfaceConfig OASIS_CONFIG;

	public static void init() {
		CALDERA = register("caldera", new FloodingBeachSurfaceBuilder(TernarySurfaceConfig::deserialize, 100, v -> Blocks.SAND.getDefaultState()));

		BASALT_BEACH = register("basalt_beach", new BeachSurfaceBuilder(
				TernarySurfaceConfig::deserialize,
				62,
				v -> v > 1.0 ? TerrestriaBlocks.BASALT_SAND.getDefaultState() : Blocks.SAND.getDefaultState()
		));

		BEACH = register("beach", new BeachSurfaceBuilder(TernarySurfaceConfig::deserialize, 62, v -> Blocks.SAND.getDefaultState()));

		CLIFF = register("cliff", new CliffSurfaceBuilder(CliffSurfaceConfig::deserialize, 62, BASALT_BEACH));

		RANDOM_BUILDER = register("random", new RandomSurfaceBuilder());

		OASIS_CONFIG = new RandomSurfaceConfig(
			(rand, noise) -> noise > 0.5 ? noise > 0.7 ? Blocks.GRASS_BLOCK.getDefaultState() : Blocks.COARSE_DIRT.getDefaultState() : Blocks.SAND.getDefaultState(),
			(rand, noise) -> Blocks.SANDSTONE.getDefaultState(),
			(rand, noise) -> Blocks.SAND.getDefaultState()
		);

		BASALT_CONFIG = new CliffSurfaceConfig(
				TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState(),
				TerrestriaBlocks.BASALT_DIRT.getDefaultState(),
				Blocks.SAND.getDefaultState(),
				TerrestriaBlocks.BASALT.plain.full.getDefaultState()
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
