package net.coderbot.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.DefaultSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class BeachySurfaceBuilder extends DefaultSurfaceBuilder {
	private int seaLevel;
	private BlockState sand;

	public BeachySurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function, int seaLevel, BlockState sand) {
		super(function);

		this.seaLevel = seaLevel;
		this.sand = sand;
	}

	protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int unknown3, double unknown4, BlockState stone, BlockState water, BlockState top, BlockState filler, BlockState underwater, int unknown5) {
		BlockPos.Mutable pos = new BlockPos.Mutable(x & 15, seaLevel - 1, z & 15);

		int height = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG).get(pos.getX(), pos.getZ()) - 1;

		if(height >= seaLevel && height <= seaLevel + 1) {
			pos.set(pos.getX(), height, pos.getZ());

			while(pos.getY() > seaLevel - 3) {
				if(chunk.getBlockState(pos).getBlock() == Blocks.STONE) {
					chunk.setBlockState(pos, sand, false);
				}

				pos.setOffset(Direction.DOWN);
			}
		} else if(height > seaLevel + 2) {
			//top = Blocks.GRASS_BLOCK.getDefaultState();
			//filler = Blocks.DIRT.getDefaultState();
		} else {
			int solid = 0;

			while(pos.getY() > 0) {
				if(chunk.getBlockState(pos).isAir()) {
					chunk.setBlockState(pos, water, false);
					solid = 0;
				} else {
					if(solid < 3) {
						if(chunk.getBlockState(pos).getBlock() == Blocks.STONE) {
							chunk.setBlockState(pos, sand, false);
						}
					}

					solid++;
				}

				pos.setOffset(Direction.DOWN);
			}
		}

		// Sea Level = 100
		super.generate(random, chunk, biome, x, z, unknown3, unknown4, stone, water, top, filler, underwater, seaLevel);
	}
}
