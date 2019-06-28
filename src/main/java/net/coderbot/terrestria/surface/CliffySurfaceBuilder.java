package net.coderbot.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import net.coderbot.terrestria.init.TerrestriaBlocks;
import net.coderbot.terrestria.init.TerrestriaSurfaces;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class CliffySurfaceBuilder extends DefaultSurfaceBuilder {
	private int seaLevel;

	public CliffySurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function, int seaLevel) {
		super(function);

		this.seaLevel = seaLevel;
	}

	protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int unknown3, double noise, BlockState stone, BlockState water, BlockState top, BlockState filler, BlockState underwater, int unknown5) {
		x &= 15;
		z &= 15;
		int height = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG).get(x, z) - 1;

		if(noise > 0.5D && height > seaLevel + 1 && height < seaLevel + 12) {
			if (height > seaLevel + 5) {
				height = seaLevel + 5;
			}

			BlockPos.Mutable pos = new BlockPos.Mutable(x, height, z);

			int basaltLayers = 3;

			if(noise > 1.0D) {
				basaltLayers += 1;
			}

			if(noise > 1.5D) {
				basaltLayers += 1;
			}

			if(height >= seaLevel + 7) {
				basaltLayers += (seaLevel + 5 - height) / 2;
			}

			for(int i = 0; i < basaltLayers; i++) {
				chunk.setBlockState(pos, TerrestriaBlocks.BASALT.getDefaultState(), false);
				pos.setOffset(Direction.UP);
			}

			for(int i = 0; i < 3; i++) {
				chunk.setBlockState(pos, TerrestriaBlocks.BASALT_DIRT.getDefaultState(), false);
				pos.setOffset(Direction.UP);
			}

			chunk.setBlockState(pos, TerrestriaBlocks.BASALT_GRASS_BLOCK.getDefaultState(), false);
		} else {
			TerrestriaSurfaces.BASALT_SURFACE.generate(random, chunk, biome, x, z, unknown3, noise, stone, water, top, filler, underwater, unknown5);
		}
	}
}
