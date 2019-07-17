package net.coderbot.terrestria.init;

import net.coderbot.terrestria.Terrestria;
import net.coderbot.terrestria.surface.BeachySurfaceBuilder;
import net.coderbot.terrestria.surface.CliffySurfaceBuilder;
import net.coderbot.terrestria.surface.GlacierSurfaceBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

// This class exports public surface constants, these fields have to be public
@SuppressWarnings("WeakerAccess")
public class TerrestriaSurfaces {
	public static BeachySurfaceBuilder CALDERA_SURFACE;
	public static BeachySurfaceBuilder BASALT_SURFACE;
	public static CliffySurfaceBuilder CLIFF_SURFACE;
	public static GlacierSurfaceBuilder ALPS_GLACIER_SURFACE;
	public static GlacierSurfaceBuilder MOUNTAIN_GLACIER_SURFACE;
	public static TernarySurfaceConfig BASALT_CONFIG;
	public static TernarySurfaceConfig ALPS_CONFIG;

	public static void init() {
		CALDERA_SURFACE = register("caldera", new BeachySurfaceBuilder(TernarySurfaceConfig::deserialize, 100, v -> Blocks.SAND.getDefaultState()));

		BASALT_SURFACE = register("basalt", new BeachySurfaceBuilder(
				TernarySurfaceConfig::deserialize,
				63,
				v -> v > 1.0 ? TerrestriaBlocks.BASALT_SAND.getDefaultState() : Blocks.SAND.getDefaultState()
		));

		CLIFF_SURFACE = register("cliff", new CliffySurfaceBuilder(TernarySurfaceConfig::deserialize, 63));
		
		ALPS_CONFIG = new TernarySurfaceConfig(
				Blocks.SNOW_BLOCK.getDefaultState(),
				Blocks.SNOW_BLOCK.getDefaultState(),
				Blocks.STONE.getDefaultState()
		);
		
		ALPS_GLACIER_SURFACE = register("alps_glacier", new GlacierSurfaceBuilder((noise) -> ALPS_CONFIG));
		MOUNTAIN_GLACIER_SURFACE = register("mountain_glacier", new GlacierSurfaceBuilder((noise) -> noise > 1.0 ? SurfaceBuilder.STONE_CONFIG : SurfaceBuilder.GRASS_CONFIG));
		
		BASALT_CONFIG = new TernarySurfaceConfig(
				TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState(),
				TerrestriaBlocks.BASALT_DIRT.getDefaultState(),
				TerrestriaBlocks.BASALT_DIRT.getDefaultState()
		);
	}

	public static <T extends SurfaceBuilder<SC>, SC extends SurfaceConfig> T register(String name, T surface) {
		return Registry.register(Registry.SURFACE_BUILDER, new Identifier(Terrestria.MOD_ID, name), surface);
	}
}
