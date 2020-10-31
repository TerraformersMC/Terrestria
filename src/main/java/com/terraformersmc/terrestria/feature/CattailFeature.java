package com.terraformersmc.terrestria.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallSeagrassBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CattailFeature extends Feature<ProbabilityConfig> {
	private Block normal;
	private Block tall;

	// TODO: Migrate to feature config
	public CattailFeature(Codec<ProbabilityConfig> codec, Block normal, Block tall) {
		super(codec);

		this.normal = normal;
		this.tall = tall;
	}

	@Override
	public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos origin, ProbabilityConfig config) {

		int x = random.nextInt(8) - random.nextInt(8);
		int z = random.nextInt(8) - random.nextInt(8);
		int y = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, new BlockPos(origin.getX() + x, 0, origin.getZ() + z)).getY();

		BlockPos candidate = new BlockPos(origin.getX() + x, y, origin.getZ() + z);

		if (world.getBlockState(candidate).getBlock() == Blocks.WATER) {
			boolean tall = random.nextDouble() < config.probability;
			BlockState grass = tall ? this.tall.getDefaultState() : this.normal.getDefaultState();

			if (grass.canPlaceAt(world, candidate)) {
				if (tall) {
					BlockState grassTop = grass.with(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
					BlockPos upper = candidate.up();

					if (world.getBlockState(upper).getBlock() == Blocks.AIR) {
						world.setBlockState(candidate, grass, 2);
						world.setBlockState(upper, grassTop, 2);
					}
				} else {
					world.setBlockState(candidate, grass, 2);
				}
				return true;
			}
		}

		return false;
	}
}
