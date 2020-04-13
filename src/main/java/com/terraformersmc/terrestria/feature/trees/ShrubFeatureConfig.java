package com.terraformersmc.terrestria.feature.trees;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ShrubFeatureConfig implements FeatureConfig {
	public final BlockState woodState;
	public final BlockState leafState;

	public ShrubFeatureConfig(BlockState woodState, BlockState leafState) {
		this.woodState = woodState;
		this.leafState = leafState.with(Properties.DISTANCE_1_7, 1);
	}

	@Override
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
		builder.put(ops.createString("wood_state"), BlockState.serialize(ops, this.woodState).getValue());
		builder.put(ops.createString("leaf_state"),  BlockState.serialize(ops, this.leafState).getValue());
		return new Dynamic(ops, ops.createMap(builder.build()));
	}

	public static <T> ShrubFeatureConfig deserialize(Dynamic<T> dynamic) {
		return new ShrubFeatureConfig(dynamic.get("wood_state")
			.map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState()), dynamic.get("leaf_state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState()));
	}
}
