package com.terraformersmc.terrestria.feature.trees.decorator;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.terraformersmc.terrestria.init.TerrestriaBlocks;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.EmptyBlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.decorator.TreeDecorator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SakuraLeafPileDecorator extends TreeDecorator {
	private int chance;

	public SakuraLeafPileDecorator(int chance) {
		super(TerrestriaFeatures.SAKURA_LEAF_PILE_DECORATOR);

		this.chance = chance;
	}
	
	public <T> SakuraLeafPileDecorator(Dynamic<T> dynamic) {
		this(dynamic.get("chance").asInt(1));
	}
	
	@Override
	public void generate(IWorld world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> placed, BlockBox box) {
		for(BlockPos pos: leaves) {
			if (rand.nextInt(chance) != 0) {
				continue;
			}

			BlockPos top = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);

			if (!AbstractTreeFeature.isAir(world, top)) {
				continue;
			}

			boolean valid = world.testBlockState(top.down(),
					state -> state.getFluidState().getFluid().matches(FluidTags.WATER) ||
							state.isSideSolidFullSquare(EmptyBlockView.INSTANCE, top.down(), Direction.UP)
			);

			if (valid) {
				// setBlockState
				method_23470(world, top, TerrestriaBlocks.SAKURA_LEAF_PILE.getDefaultState(), placed, box);
			}
		}
	}

	public <T> T serialize(DynamicOps<T> ops) {
		return new Dynamic<T>(
				ops,
				ops.createMap(ImmutableMap.of(
						ops.createString("type"),
						ops.createString(Registry.TREE_DECORATOR_TYPE.getId(this.field_21319).toString()),
						ops.createString("chance"),
						ops.createInt(chance)
				))
		).getValue();
	}
}
