package com.terraformersmc.terrestria.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class TwoBlockWeightedRandomSurfaceConfig extends TernarySurfaceConfig {

	private final BlockState top2;
	private final int primaryFactor;

	public TwoBlockWeightedRandomSurfaceConfig(BlockState primary, int primaryFactor, BlockState secondary, BlockState underGround, BlockState underWater) {
		super(primary, underGround, underWater);
		this.top2 = secondary;
		this.primaryFactor = primaryFactor;
	}

	public BlockState getPrimaryMaterial() {
		return getTopMaterial();
	}

	public BlockState getSecondaryMaterial() {
		return top2;
	}

	public static TwoBlockWeightedRandomSurfaceConfig deserialize(Dynamic<?> dynamic_1) {
		//This line is the issue
		BlockState top = (BlockState)dynamic_1.get("top_material_1").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		int primaryFactor = dynamic_1.get("primary_factor").asInt(1);
		BlockState top2 = (BlockState)dynamic_1.get("top_material_1").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState underGround = (BlockState)dynamic_1.get("under_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		BlockState underWater = (BlockState)dynamic_1.get("underwater_material").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new TwoBlockWeightedRandomSurfaceConfig(top, primaryFactor, top2, underGround, underWater);
	}

	public int getPrimaryFactor() {
		return primaryFactor;
	}
}
