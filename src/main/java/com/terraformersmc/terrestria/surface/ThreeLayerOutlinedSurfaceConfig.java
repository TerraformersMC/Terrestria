package com.terraformersmc.terrestria.surface;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ThreeLayerOutlinedSurfaceConfig extends TernarySurfaceConfig {

	private final BlockState topMiddle;
	private final float middleNoisePoint;
	private final BlockState topOuter;
	private final float outerNoisePoint;

	public ThreeLayerOutlinedSurfaceConfig(BlockState topMaterial, BlockState topMiddle, float middleNoisePoint, BlockState topOuter, float outerNoisePoint, BlockState underMaterial, BlockState underwaterMaterial) {
		super(topMaterial, underMaterial, underwaterMaterial);
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

	public static Codec<ThreeLayerOutlinedSurfaceConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockState.CODEC.fieldOf("top_material").forGetter(TernarySurfaceConfig::getTopMaterial),
			BlockState.CODEC.fieldOf("top_middle").forGetter(config -> config.topMiddle),
			Codec.FLOAT.fieldOf("middle_noise_point").forGetter(config -> config.middleNoisePoint),
			BlockState.CODEC.fieldOf("top_outer").forGetter(config -> config.topOuter),
			Codec.FLOAT.fieldOf("outer_noise_point").forGetter(config -> config.outerNoisePoint),
			BlockState.CODEC.fieldOf("under_material").forGetter(TernarySurfaceConfig::getUnderMaterial),
			BlockState.CODEC.fieldOf("underwater_material").forGetter(TernarySurfaceConfig::getUnderwaterMaterial))
			.apply(instance, ThreeLayerOutlinedSurfaceConfig::new));
}
