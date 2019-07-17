package net.coderbot.terrestria.surface;

import java.util.Random;
import java.util.function.DoubleFunction;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.SimplexNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class GlacierSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig>
{
	protected final BlockState WATER = Blocks.WATER.getDefaultState();
	protected final BlockState SAND = Blocks.SAND.getDefaultState();
	protected final BlockState pICE = Blocks.PACKED_ICE.getDefaultState();
	protected final BlockState bICE = Blocks.BLUE_ICE.getDefaultState();
	
	private SimplexNoiseSampler noiseGenerator;
	private long currentSeed = 0L;
	private DoubleFunction<TernarySurfaceConfig> configProvider;
	
	public GlacierSurfaceBuilder(DoubleFunction<TernarySurfaceConfig> config)
	{
		super(TernarySurfaceConfig::deserialize);
		
		configProvider = config;
	}
	
	private BlockState getChunkBlock(Chunk chunk, int difference, int worldHeight, BlockPos pos) {
		return (pos.getY() < 63 || difference == 0 || pos.getY() + 1 > worldHeight) ? chunk.getBlockState(pos) : chunk.getBlockState(pos.up());
	}
	
	@Override
	public void generate(Random rand, Chunk chunk, Biome biome, int x, int z, int worldHeight, double noiseVal, BlockState var9, BlockState var10, int var11, long seed, TernarySurfaceConfig configToIgnore) {
		TernarySurfaceConfig config = configProvider.apply(noiseVal);
		
		if (noiseGenerator == null || seed != currentSeed) {
			noiseGenerator = new SimplexNoiseSampler(new Random(seed));
		}
		double noise = noiseGenerator.sample((double) x / 100D, (double) z / 100D);
		int glacierDifference = (int) ((noise > 0.22D && noise < 0.3D) ? (1171875 * Math.pow(noise - 0.26, 4)) - 3 : 0);
		
		int localX = x & 15;
		int localZ = z & 15;
		
		BlockState top = config.getTopMaterial();
		BlockState under = config.getUnderMaterial();
		BlockState underwater = config.getUnderwaterMaterial();
		
		int tempYLevel = 0;
		
		for (int y = worldHeight; y >= 0; --y)
		{
			BlockPos pos = new BlockPos(localX, y, localZ);
			BlockState chunkBlock = getChunkBlock(chunk, glacierDifference, worldHeight, pos);
			
			if (chunkBlock == STONE) {
				BlockState toSet = STONE;
				
				if (y < 255) {
					BlockState up = getChunkBlock(chunk, glacierDifference, worldHeight, pos.up());
					if (up == AIR) {
						toSet = glacierDifference > 0 ? pICE: top;
						tempYLevel = y - 1;
					} else if (up == WATER) {
						toSet = underwater;
					} else if (y < 253) {
						if (getChunkBlock(chunk, glacierDifference, worldHeight, pos.up(3)) == AIR) {
							toSet = glacierDifference >= (tempYLevel - y) ? bICE : under;
						}
					}
				}

				chunk.setBlockState(pos, toSet, false);

			} else {
				chunk.setBlockState(pos, chunkBlock, false);
			}
		}
	}

}
