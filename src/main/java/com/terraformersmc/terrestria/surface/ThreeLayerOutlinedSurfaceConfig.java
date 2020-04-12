package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ThreeLayerOutlinedSurfaceConfig extends TernarySurfaceConfig {

	private final BlockState topMiddle;
	private final float middleNoisePoint;
	private final BlockState topOuter;
	private final float outerNoisePoint;

	public ThreeLayerOutlinedSurfaceConfig(BlockState topInner, BlockState topMiddle, float middleNoisePoint, BlockState topOuter, float outerNoisePoint, BlockState underground, BlockState underwater) {
		super(topInner, underground, underwater);
		this.topMiddle = topMiddle;
		this.middleNoisePoint = middleNoisePoint;
		this.topOuter = topOuter;
		this.outerNoisePoint = outerNoisePoint;
	}

	public BlockState getMiddleMaterial() {
		return topMiddle;
	}

	public float getMiddleNoisePoint() {
		return middleNoisePoint;
	}

	public BlockState getOuterMaterial() {
		return topOuter;
	}

	public float getOuterNoisePoint() {
		return outerNoisePoint;
	}

	public static ThreeLayerOutlinedSurfaceConfig deserialize(Dynamic<?> dynamic) {
		TernarySurfaceConfig ternary = TernarySurfaceConfig.deserialize(dynamic);
		BlockState topMiddle = (BlockState)dynamic.get("top_middle").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		float middleNoise = dynamic.get("middle_noise_point").asFloat(0.5F);
		BlockState topEdge = (BlockState)dynamic.get("top_outer").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		float outerNoise = dynamic.get("outer_noise_point").asFloat(0.7F);
		return new ThreeLayerOutlinedSurfaceConfig(ternary.getTopMaterial(), topMiddle, middleNoise, topEdge, outerNoise, ternary.getUnderMaterial(), ternary.getUnderwaterMaterial());
	}
}
