package com.terraformersmc.terrestria.surface.builders;

import com.terraformersmc.biolith.api.surface.BiolithSurfaceBuilder;
import com.terraformersmc.terrestria.Terrestria;
import com.terraformersmc.terrestria.init.TerrestriaBiomes;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class TerrestriaSurfaceBuilders {
	private static final HashMap<Identifier, BiolithSurfaceBuilder> builders = new HashMap<>(8);

	private static ServerLevel OVERWORLD = null;

	public static void init() {
		// We need access to the Overworld for some surface builders.
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			OVERWORLD = server.overworld();
		});
		ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
			OVERWORLD = null;
		});

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
						Objects.requireNonNull(TerrestriaBlocks.ANDISOL.grassBlock()).defaultBlockState(),
						TerrestriaBlocks.ANDISOL.dirtBlock().defaultBlockState(),
						TerrestriaBlocks.VOLCANIC_ROCK.plain.full.defaultBlockState(),
						TerrestriaBlocks.VOLCANIC_SAND.defaultBlockState(),
						Blocks.SAND.defaultBlockState()
				).setBiomeKey(TerrestriaBiomes.VOLCANIC_ISLAND));
	}

	public static HashMap<Identifier, BiolithSurfaceBuilder> getBuilders() {
		return builders;
	}

	public static @Nullable ServerLevel getOverworld() {
		return OVERWORLD;
	}
}
