package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class RandomSurfaceConfig extends TernarySurfaceConfig {

	private final BlockState topMiddle;
	private final BlockState

	public RandomSurfaceConfig(BlockState top, BlockState topSecondary, BlockState under, BlockState underwater) {
		super(top, under, underwater);
		this.topMiddle = topSecondary;
	}

	public BlockState getTopMiddle() {
		return topMiddle;
	}

	public static RandomSurfaceConfig deserialize(Dynamic<?> dynamic) {
		TernarySurfaceConfig ternary = TernarySurfaceConfig.deserialize(dynamic);
		BlockState topSecondary = (BlockState)dynamic.get("secondary_top_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new RandomSurfaceConfig(ternary.getTopMaterial(), ternary.getUnderMaterial(), ternary.getUnderwaterMaterial(), topSecondary);
	}

	/*
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
	 */

}
