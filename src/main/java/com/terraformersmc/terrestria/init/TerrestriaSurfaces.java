package com.terraformersmc.terrestria.init;
/*
import com.terraformersmc.terraform.surface.BeachSurfaceBuilder;
import com.terraformersmc.terraform.surface.CliffSurfaceBuilder;
import com.terraformersmc.terraform.surface.CliffSurfaceConfig;
import com.terraformersmc.terraform.surface.FloodingBeachSurfaceBuilder;
import com.terraformersmc.terrestria.surface.DuneSurfaceBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.surface.CanyonSurfaceBuilder;
import com.terraformersmc.terrestria.surface.PatchyGrassSurfaceBuilder;
import com.terraformersmc.terrestria.surface.ThreeLayerOutlineSurfaceBuilder;
import com.terraformersmc.terrestria.surface.ThreeLayerOutlinedSurfaceConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
*/

// This class exports public surface constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaSurfaces {
/*
	public static ThreeLayerOutlineSurfaceBuilder THREE_LAYER_OUTLINE;
	public static FloodingBeachSurfaceBuilder CALDERA;
	public static BeachSurfaceBuilder BASALT_BEACH;
	public static BeachSurfaceBuilder BEACH;
	public static DuneSurfaceBuilder DUNES;
	public static CliffSurfaceBuilder BASALT_CLIFF;
	public static CanyonSurfaceBuilder CANYON_CLIFF;
	public static CliffSurfaceConfig BASALT_CONFIG;
	public static CliffSurfaceBuilder SANDSTONE_CLIFF;
	public static CliffSurfaceConfig SANDSTONE_CLIFF_CONFIG;
	public static CliffSurfaceConfig SANDSTONE_CONFIG;
	public static TernarySurfaceConfig ALPS_CONFIG;
	public static TernarySurfaceConfig DUNES_CONFIG;
	public static ThreeLayerOutlinedSurfaceConfig OASIS_CONFIG;
	public static TernarySurfaceConfig OUTBACK_ULURU_CONFIG;
	public static PatchyGrassSurfaceBuilder PATCHY_GRASS;
	public static TernarySurfaceConfig OUTBACK_CONFIG;
*/

	public static void init() {
/*
		CALDERA = register("caldera", new FloodingBeachSurfaceBuilder(TernarySurfaceConfig.CODEC, 100, v -> Blocks.SAND.getDefaultState()));

		BASALT_BEACH = register("basalt_beach", new BeachSurfaceBuilder(
				TernarySurfaceConfig.CODEC,
				62,
				v -> v > 1.0 ? TerrestriaBlocks.BLACK_SAND.getDefaultState() : Blocks.SAND.getDefaultState()
		));

		BEACH = register("beach", new BeachSurfaceBuilder(TernarySurfaceConfig.CODEC, 62, v -> Blocks.SAND.getDefaultState()));

		BASALT_CLIFF = register("basalt_cliff", new CliffSurfaceBuilder(CliffSurfaceConfig.CODEC, 62, BASALT_BEACH));

		DUNES = register("dunes", new DuneSurfaceBuilder(TernarySurfaceConfig.CODEC));

		SANDSTONE_CLIFF = register("canyon_cliff", new CliffSurfaceBuilder(CliffSurfaceConfig.CODEC, 62, BEACH));

		CANYON_CLIFF = register("sandstone_cliff", new CanyonSurfaceBuilder(CliffSurfaceConfig.CODEC, 62, BEACH));

		THREE_LAYER_OUTLINE = register("random", new ThreeLayerOutlineSurfaceBuilder(ThreeLayerOutlinedSurfaceConfig.CODEC));

		PATCHY_GRASS = register("patchy_grass", new PatchyGrassSurfaceBuilder(TernarySurfaceConfig.CODEC));

		OASIS_CONFIG = new ThreeLayerOutlinedSurfaceConfig(
				Blocks.SAND.getDefaultState(),
				Blocks.COARSE_DIRT.getDefaultState(), 0.5F,
				Blocks.GRASS_BLOCK.getDefaultState(), 0.7F,
				Blocks.SANDSTONE.getDefaultState(),
				Blocks.SAND.getDefaultState()
		);

		BASALT_CONFIG = new CliffSurfaceConfig(
				TerrestriaBlocks.ANDISOL.getGrassBlock().getDefaultState(),
				TerrestriaBlocks.ANDISOL.getDirt().getDefaultState(),
				Blocks.SAND.getDefaultState(),
				TerrestriaBlocks.VOLCANIC_ROCK.plain.full.getDefaultState()
		);

		SANDSTONE_CLIFF_CONFIG = new CliffSurfaceConfig(
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

		DUNES_CONFIG = new TernarySurfaceConfig(
			Blocks.SAND.getDefaultState(),
			Blocks.SANDSTONE.getDefaultState(),
			Blocks.SAND.getDefaultState()
		);

		OUTBACK_ULURU_CONFIG = new TernarySurfaceConfig(
			Blocks.RED_SANDSTONE.getDefaultState(),
			Blocks.RED_SANDSTONE.getDefaultState(),
			Blocks.RED_SAND.getDefaultState()
		);

		OUTBACK_CONFIG = new TernarySurfaceConfig(
			Blocks.RED_SAND.getDefaultState(),
			Blocks.RED_SANDSTONE.getDefaultState(),
			Blocks.RED_SAND.getDefaultState()
		);

	}

	public static <T extends SurfaceBuilder<SC>, SC extends SurfaceConfig> T register(String name, T surface) {
		return Registry.register(Registry.SURFACE_BUILDER, new Identifier(Terrestria.MOD_ID, name), surface);
*/
	}

}
