package com.terraformersmc.terrestria.feature.trees.decorator;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.terraformersmc.terraform.block.SmallLogBlock;
import com.terraformersmc.terrestria.init.TerrestriaFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.decorator.TreeDecorator;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class FixSmallLogsDecorator extends TreeDecorator {
	public FixSmallLogsDecorator() {
		super(TerrestriaFeatures.FIX_SMALL_LOGS_DECORATOR);
	}

	public <T> FixSmallLogsDecorator(Dynamic<T> dynamic) {
		this();
	}

	@Override
	public void generate(IWorld world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> placed, BlockBox box) {
		for (BlockPos log : logs) {
			BlockState existing = world.getBlockState(log);

			if(!(existing.getBlock() instanceof SmallLogBlock)) {
				continue;
			}

			boolean hasLeaves = existing.get(SmallLogBlock.HAS_LEAVES);

			Predicate<BlockState> tester =
					tested ->
							tested.getBlock() instanceof SmallLogBlock ||
							(!hasLeaves && tested.getBlock() instanceof LeavesBlock) ||
							tested.isOpaque();

			setBlockStateAndEncompassPosition(
					world,
					log,
					existing
							.with(SmallLogBlock.UP, world.testBlockState(log.up(), tester))
							.with(SmallLogBlock.DOWN, world.testBlockState(log.down(), tester))
							.with(SmallLogBlock.NORTH, world.testBlockState(log.north(), tester))
							.with(SmallLogBlock.EAST, world.testBlockState(log.east(), tester))
							.with(SmallLogBlock.SOUTH, world.testBlockState(log.south(), tester))
							.with(SmallLogBlock.WEST, world.testBlockState(log.west(), tester)),
					placed,
					box
			);
		}
	}

	public <T> T serialize(DynamicOps<T> ops) {
		return new Dynamic<T>(
				ops,
				ops.createMap(ImmutableMap.of(
						ops.createString("type"),
						ops.createString(Registry.TREE_DECORATOR_TYPE.getId(this.type).toString())
				))
		).getValue();
	}
}
