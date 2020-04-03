package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ThreeLayerOutlinedSurfaceConfig extends TernarySurfaceConfig {

	private final BlockState middle;
	private final float middleNoisePoint;
	private final BlockState outer;
	private final float outerNoisePoint;

	public ThreeLayerOutlinedSurfaceConfig(BlockState inner, BlockState topMiddle, float middleNoisePoint, BlockState topEdge, float outerNoisePoint, BlockState under, BlockState underwater) {
		super(inner, under, underwater);
		this.middle = topMiddle;
		this.middleNoisePoint = middleNoisePoint;
		this.outer = topEdge;
		this.outerNoisePoint = outerNoisePoint;
	}

	public BlockState getMiddleMaterial() {
		return middle;
	}

	public float getMiddleNoisePoint() {
		return middleNoisePoint;
	}

	public BlockState getOuterMaterial() {
		return outer;
	}

	public float getOuterNoisePoint() {
		return outerNoisePoint;
	}

	public static ThreeLayerOutlinedSurfaceConfig deserialize(Dynamic<?> dynamic) {
		TernarySurfaceConfig ternary = TernarySurfaceConfig.deserialize(dynamic);
		BlockState topMiddle = (BlockState)dynamic.get("middle_top_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		float middleNoise = dynamic.get("middle_top_noise").asFloat(0.5F);
		BlockState topEdge = (BlockState)dynamic.get("edge_top_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		float outerNoise = dynamic.get("middle_top_noise").asFloat(0.7F);
		return new ThreeLayerOutlinedSurfaceConfig(ternary.getTopMaterial(), topMiddle, middleNoise, topEdge, outerNoise, ternary.getUnderMaterial(), ternary.getUnderwaterMaterial());
	}
}
