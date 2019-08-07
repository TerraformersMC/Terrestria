package com.terraformersmc.terrestria.surface;

import java.util.Random;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class RandomSurfaceConfig implements SurfaceConfig {

	private final RandomNoiseFunction<BlockState> top;
	private final RandomNoiseFunction<BlockState> under;
	private final RandomNoiseFunction<BlockState> underwater;

	public RandomSurfaceConfig(RandomNoiseFunction<BlockState> topStateProvider, RandomNoiseFunction<BlockState> underStateProvider, RandomNoiseFunction<BlockState> underwaterStateProvider) {
		this.top = topStateProvider;
		this.under = underStateProvider;
		this.underwater = underwaterStateProvider;
	}

	public BlockState getTopMaterial(Random rand, double noiseVal) {
		return this.top.apply(rand, noiseVal);
	}

	public BlockState getUnderMaterial(Random rand, double noiseVal) {
		return this.under.apply(rand, noiseVal);
	}

	public BlockState getUnderwaterMaterial(Random rand, double noiseVal) {
		return this.underwater.apply(rand, noiseVal);
	}

	@Override
	@Deprecated
	public BlockState getTopMaterial() {
		return this.top.apply(new Random(), 0);
	}

	@Override
	@Deprecated
	public BlockState getUnderMaterial() {
		return this.under.apply(new Random(), 0);
	}

	public static interface RandomNoiseFunction<T> {
		public T apply(Random rand, double noiseVal);
	}

	public static RandomSurfaceConfig deserialize(Dynamic<?> dynamic_1) {
		BlockState blockState_1 = (BlockState)dynamic_1.get("top_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState blockState_2 = (BlockState)dynamic_1.get("under_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState blockState_3 = (BlockState)dynamic_1.get("underwater_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new RandomSurfaceConfig((m, n) -> blockState_1, (m, n) -> blockState_2, (m, n) -> blockState_3);
	}

}
