package com.terraformersmc.terrestria.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CattailFeature extends Feature<ProbabilityFeatureConfiguration> {
	private final Block normal;
	private final Block tall;

	// TODO: Migrate to feature config
	public CattailFeature(Codec<ProbabilityFeatureConfiguration> codec, Block normal, Block tall) {
		super(codec);

		this.normal = normal;
		this.tall = tall;
	}

	@Override
	public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> context) {
		WorldGenLevel level = context.level();
		RandomSource random = context.random();
		BlockPos origin = context.origin();

		int x = random.nextInt(8) - random.nextInt(8);
		int z = random.nextInt(8) - random.nextInt(8);
		int y = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, new BlockPos(origin.getX() + x, 0, origin.getZ() + z)).getY();

		BlockPos candidate = new BlockPos(origin.getX() + x, y, origin.getZ() + z);

		if (level.getBlockState(candidate).getBlock() == Blocks.WATER) {
			boolean tall = random.nextDouble() < context.config().probability;
			BlockState grass = tall ? this.tall.defaultBlockState() : this.normal.defaultBlockState();

			if (grass.canSurvive(level, candidate)) {
				if (tall) {
					BlockState grassTop = grass.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER);
					BlockPos upper = candidate.above();

					if (level.getBlockState(upper).getBlock() == Blocks.AIR) {
						level.setBlock(candidate, grass, 2);
						level.setBlock(upper, grassTop, 2);
					}
				} else {
					level.setBlock(candidate, grass, 2);
				}
				return true;
			}
		}

		return false;
	}
}
